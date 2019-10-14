
核心： 票的预留和回收

* Scenario - requirement
    - 用户提交订票请求
    - 客户端等待订票
    - 预留票，用户完成支付
    - 票过期，回收票
    - 限制一场演唱会的票数
* Scenario - constraints
    - 500k qps
    - **响应时间** 不需要一瞬间就完成预订，500K的请求可以在若干分钟内完成，1秒内只需完成收到购票申请就可以
* Service
    - ReservationService - 用户提交一个预订请求，查询自己的预订状态
    - TicketService - 系统帮一个预订完成预订，生成具体的票
* Storage
    - ReservationService
        + 用户提交一个订票申请后，把一条预订的数据写到数据库里
        + 需要一个Reservation的表
        ```sql
            CREATE TABLE Reservation (
                `id`    bigint,
                `created_at`    timestamp,
                `concert_id`    Foreign Key,
                `user_id`   Foreign Key,
                `tickets_count` int,
                `status`    int
            )
        ```
        + 谁在什么时刻预订了哪个演唱会，预订了几张，当前预订状态是什么 (等待、成功、失败)
    - TicketService
        + 系统从数据库中按照顺序选出预定，完成预订，预订成功的生成对应的Ticket
        ```sql
            CREATE TABLE Ticket (
                `id`    bigint,
                `created_at`    timestamp,
                `user_id`       Foreign Key,
                `concert_id`    Foreign Key,
                `reservation_id`    Foreign Key,
                `status`        int #是否退票之类
            )
        ```
        + 另外，还需要一个Concert的table，主要记录总共几张票
        ```sql
            CREATE TABLE Concert (
                `id`    bigint,
                `title` string,
                `descrition`    text,
                `start_at`      timestamp,
                `tickets_amount`    int,
                `remain_tickets_amount` int
            )
        ```
* Work Solution
    1. 用户提交一个预订，ReservationService 收到预订，存在库里，status=pending
    2. 用户提交预订后，跳转到一个等待订票结果的界面，该界面每隔5-10秒发送一个请求查询当前的预订状态
    3. TicketService 是一个单独执行的程序，可以是一个死循环，不断检查数据库里是否有pending状态的票
    4. 取出一批票，比如1k张，然后顺利处理，创建对应的Tickets，修改对应的Reservation的status
* Scale
    - 分析一下上述的每个操作在 **500k qps**的情况下会发生什么，以及该如何解决
    1. 用户提交一个预定，ReservationService 收到预定，存在数据库里，status=pending
        + 也就是说，在一秒钟之内，我们要同时处理500k的预定请求，首先web server一台肯定搞不定，需要增加到大概500台，每台web server一秒钟同时处理1k的请求还是可以的。
        + 数据库如果只有一台的话，也很难承受这样大的请求。并且SQL和NoSQL这种数据库处理这个问题也会非常吃力。可以选用Redis这种既是内存级访问速度，又可以做持久化的key-value数据库。
        + 并且Redis自带一个队列的功能，非常适合我们订票的这个模型。
        + Redis的存取效率大概是每秒钟几十k，那么也就是我们要大概20台Redis应该就可以了。我们可以按照 user_id 作为 shard key，分配到各个redis上。
    2. 用户提交预定之后，跳转到一个等待订票结果的界面，该界面每隔5-10秒钟像服务器发送一个请求查询当前的预定状态
        + 使用了redis的队列之后，如何查询一个预定信息是否在队列里呢？
        + 方法是reservation的基本信息除了放到队列里，还需要同时继续存一份在redis里。队列里可以只放reservation_id。此时reservation_id可以用user_id+concert_id+timestamp来表示。
    3. TicketService是一个单独执行的程序，你可以认为是一个死循环，不断检查数据库里是否有pending状态的票，取出一批票，比如1k张，然后顺利处理，创建对应的Tickets，修改对应的Reservation的status。
        + 为每个Redis的数据库后面添加一个TicketService的程序（在某台机器上跑着），每个TicketService负责一个Redis数据库。
        + 该程序每次从Redis的队列中读出最多1k的数据，然后计算一下有需要多少张票，比如2k，然后访问Concert的数据库。问Concert要2k的票，如果还剩有那么多，那么就remain_tickets_amount - 2k，如果不够的话，就返回还有多少张票，并把remain_tickets_acount 清零。
        + 这个过程要对数据库进行加锁，可以用数据库自己带的锁，也可以用zookeeper之类的分布式锁。因为现在是1k为一组进行处理，所以这个过程不会很慢，存Concert的数据库也不需要很多，一台就够了。因为我们只有20个跑着TicketService的机器（对应20台Redis），也就是个20 qps的峰值，数据库处理起来绰绰有余额。
        + 假如得到了2k张票的额度之后，就顺序处理这1k个reservation，然后对每个reservation生成对应的tickets，并在redis中标记reservation的状态，这里的话，tickets的table大概就会产生2k条的insert，所以tickets的数据库需要大概能够承受 20 x 1k = 20k 的并发写。这个的话，大概 20 台 SQL数据库也就搞定了。
    - 总结
        1. 开放订票，500k的请求进来
        2. 通过Load Balancer分发给500台Web Server，每台1k/s的请求
        3. Web Server将1k的请求，按照user_id进行shard，丢给对应的redis服务器里的队列，并把Reservation信息也丢给Redis存储
        4. 此时，20台Redis，每台Redis约收到25k的排队订票记录
        5. 每台Redis背后对应一个TicketService程序，不断的查看Redis里的队列是否有订单记录，如果有的话，一次取1k个订票记录进行处理，问Concert要额度，然后把1k的reservation对应的创建出2k左右的tickets出来(假设一个reservation有2张票平均)。
        6. 假如这部分的处理能力是1k/s的话，那么这个过程应该低于10秒，因为当concert的票卖完了的时候，无需生成1-2k条新的tickets，速度会变快
        7. 存储tickets的数据库需要多台，因为需要处理的请求大概是20k的qps，大概20台左右的Ticket数据库
* Scale - 超时票的回收
    - 增加一个RecycleService, 不断访问Tickets的数据库，看有没有超时
    - 如果超时，就回收，并且去Concert的数据库把remain_tickets_amount增加
* 核心设计
    - 500k qps 只要做到收，不需要做处理，那么500台webServer+20台Redis就可以了
    - 处理的时候分成1k一组进行处理，用户等10秒中，需要的服务器数目就降低10-20倍，这是个tradeoff
* 继续改进
    - 500台Web服务器很多，而且除了订票的那几秒种，大部分的时候都是闲置浪费的，怎么办？
        + 用AWS的弹性计算服务，为每场演唱会的火爆指数进行评估，然后预先开好机器，用完之后就可以销毁掉。
    - 为什么不直接用Redis也来存储所有的数据信息？
        + 因为是针对通同一个Concert的预定，大家需要访问同一条数据（remain_tickets_acount)，shard是不管用的，Redis也承受不住500k QPS 对同一条数据进行读写，并且还要加锁之类的保证一致性。所以这个对 remain_tickets_acount 的值进行修改，创建对应的 tickets 的过程，是不能在用户请求的时候，实时完成的，需要延迟进行。
    - redis又用来做队列，又用来做Reservation 表的存储，是否有点乱？
        + 是的，所以一个更好的办法是，只把redis当做队列来用 和 Reservation 信息的Cache来用。当一个Reservation 被处理的时候，再到SQL数据库里生成对应的持久化记录。这样的好处是，Redis 这种结构其实不是很擅长做持久化数据的存储，我们一般都还是拿来当队列和cache用得比较多。







