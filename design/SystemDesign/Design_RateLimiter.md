### RateLimiter 访问限制器

* Scenario
    - 根据网络请求的特征进行限制(feature的选取)
        + IP(未登录时的行为), User(登录后的行为), Email(注册，登录，激活)
    - 系统需要做到什么程度
        + 如果在某个时间段内超过一定数目，就拒绝该请求
* Service
* Storage
    - 需要记录(log)某个特征(feature)在哪个时刻(time)做了什么事情(event)
    - 该记录最多保留一天(对于rate=5/m，一分钟后就没有保存的意义)
    - 需要可以高效存取的结构(本身就是为了限制对数据库的读写太多)
    - 所以使用Memcached作为存储(无需持久化)

* 算法描述
    - event+feature+timestamp 作为 key
    - 记录一次访问
        + 代码：memcached.increment(key, ttl=60s)
        + 解释：对应bucket的访问次数+1，设置60s失效
    - 查询是否超过限制
        + 代码：
        ```
            for t in 0~59 do
                key = event+feature+(current_timestamp-t)
                sum+=memcached.get(key, default=0)
        ```
        + Check sum is in limitation
        + 解释：把最近一分钟的记录加和
* scale
    - 对于一天86400秒，检查一次就86k次cache访问，如何 优化
        + 分级存储
            * 1分钟为单位，每个bucket是1秒，最多60次读
            * 1天为单位，每个bucket是1小时，最多24次读
    - 上述方法存在误差，如何解决
        + 不需解决，RateLimiter不需绝对精确
        + 可以将每次log信息分存入3级的bucket
            • 在秒的bucket里加和 23:30:00 ~ 23:30:33（计34次查询）
            • 在分的bucket里加和 23:00 ~ 23:29（计30次查询）
            • 在时的bucket里加和 00 ~ 22（计23次查询）
            • 在秒的bucket里加和昨天 23:30:34 ~ 23:30:59 （计26次查询）
            • 在分的bucket里加和昨天 23:31 ~ 23:59（计29次查询）
            • 总计耗费 34 + 30 + 23 + 26 + 29 = 142 次cache查询，可以接受


### DataDog

* Scenario
    - 对于用户对于某个链接的每次访问，记录为一次访问
    - 可以查询某个链接的被访问次数
    - 知道总共多少次访问
    - 知道最近x小时/x天/x月/x年的访问曲线
    - 假设TinyURL的读请求约2K的QPS
* Service
* Storage
    - 基本全是写操作，读操作很少
    - 需要持久化存储
    - SQL or NoSQL or File System
        + 都可以，Graphite用的就是文件系统存储
    - NoSQL的话，key就是tiny url的short_key，value是这个key的所有访问记录
        + 问题：value怎么存下一个key的所有访问数据(比如1年)
    - 核心点
        + 今天的数据，以分钟为单位存储
        + 昨天的数据，以5分钟诶单位存储
        + 上个月的数据，以1小时为单位存储
        + 去年的数据，就以周为单位存储
        + 。。。
        + **用户的查询操作通常是查询某个时刻到当前时刻的曲线图**
        + **意味着，对于去年的数据，没必要分钟为单位进行保存**
    - 多级bucket的思路，与Rate Limiter如出一辙
* Scale
    - 2k的QPS，往NoSQL的写入操作也这么多么
        + 可以将最近15次访问记录 **Aggregate**到一起，写在内存里
        + 每隔15次将记录写入NoSQL，QPS降到100多
    - 如何将昨天的数据按照5分钟bucket整理
        + 发现一个key的value较多时，触发一次瘦身
        + 整理旧数据记录的专业名称：**Retention**


