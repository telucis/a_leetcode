* Scenario
    - Enumerate
        + Register / Login
        + User Profile Display / Edit
        + Upload Image / Video
        + Search
        + Post / Share a tweet
        + Timeline / News Feed
        + Follow / Unfollow a user
    - Sort
        + Post a Tweet
        + Timeline
        + News Feed
        + Follow / Unfollow a user
        + Register / Login
    - DAU: 150M
        + qps: 150M * 60 / 86400 = 100K
        + peak: 100K * 3 = 300K
        + writeQps: 5k
* Service
    - User Service
        + Register
        + Login
    - Tweet Service
        + Post a tweet
        + News Feed
        + Timeline
    - Media Service
        + Upload Image
        + Upload Video
    - FriendShip Service
        + Follow
        + Unfollow
* Storage
    - User Service
    ```sql
        CREATE TABLE User (
            `id`    Integer,
            `username`  varchar,
            `email`     varchar,
            `password`  varchar
        );
    ```
    - Friendship Service
    ```sql
        CREATE TABLE Friendship (
            `from_user_id`  Foreign Key,
            `to_user_id`    Foreign Key
        );
    ```
    - Tweet
    ```sql
        CREATE TABLE Tweet (
            `id`    integer,
            `user_id`   Foreign Key,
            `content`   text,
            `create_at` timestamp
        )
    ```

* New Feed
    - 登录后看到的信息流，所有follow的信息集合
    - 核心因素
        + 关注与被关注
        + 每个人看到的新鲜事都是不同的
* Pull Model
    - 算法
        + 获取每个好友前100条tweets, 合并出前100条tweets
        + K路归并算法
    - 复杂度分析
        + News Feed: N个follow对象，N次DB查询
        + Post a tweet: 1次DB write的时间
    - 问题
        + N次DB请求非常慢，并且发生在请求过程中
* Push Model
    - 算法
        + 为每个用户建一个list存储他的News Feed信息
        + 用户发一个tweet后，逐个推送到每个用户的list - **Fanout**
        + 用户需要查看时，从该News Feed中读取最新100条
    - 复杂度分析
        + News Feed: 1次DB read
        + Post a tweet: N个fans，N次DB write (可异步，用户无需等待)
    ```sql
        CREATE TABLE NewsFeed (
            `id`    integer,
            `owner_id`  Foreign Key,
            `tweet_id`  Foreign Key,
            `create_at` timestamp
        )
    ```
    - 问题
        + 异步执行，不实时
        + followers数量可能很大
* Scale - Optimize
    - Pull缺陷
        + 在DB访问之前加入Cache
        + Cache每个用户的Timeline
            * N次DB请求 -> N次Cache请求
            * Trade off: Cache所有的？Cache最近1000条
        + Cache每个用户的News Feed
            * 没有Cache News Feed的用户：归并N个用户最近的100条tweet，然后取前100
            * 有Cache News Feed的用户：归并N个用户的在某个时间戳之后的所有tweet
    - Push缺陷
        + 浪费更多存储空间 Disk - Disk is cheap
        + 不活跃用户 Inactive Users
            * 粉丝排序 Rank followers by weight (last login time)
        + 粉丝数目 >> 关注数目 following - Lady Gaga 问题
            * 普通用户仍然 push
            * 标记明星用户，需要时来明星的Timeline取，合并到news feeds里
        + 摇摆问题
            * 异步处理明星标记问题
    - 小结
        + push
            * 资源少
            * 少写代码
            * **实时性要求不高**
            * 用户发帖少
            * **双向好友关系，没有明星问题**
        + pull
            * 资源充足
            * **实时性要求高**
            * 用户发帖很多
            * **单向好友关系，有明星问题**
    - follow/unfollow 问题
        + follow 一个用户后，异步将他的 Timeline 合并到 News Feed 中
        + Unfollow 一个用户之后，异步地将他发的 Tweets 从你的 News Feed 中移除
        + 为什么需要异步 Async - 因为这个过程一点都不快
        + 异步的好处
            * 用户迅速得到反馈，似乎马上就 follow / unfollow 成功了
        + 异步的坏处
            * Unfollow 之后刷新 News Feed，发现好像他的信息还在
            * 不过最终还是会被删掉的
    - 如何存储 Like
        ```sql
            CREATE TABLE Like (
                `id`    integer,
                `user_id`   Foreign Key,
                `tweet_id`  Foreign Key,
                `create_at` timestamp
            )
            CREATE TABLE Tweet (
                `id`    integer,
                `user_id`   Foreign Key,
                `content`   text,
                `create_at` timestamp,
                # De-normalize
                `like_nums` integer,
                `comment_nums`  integer,
                `retweet_nums`  integer
            )
        ```
* Scale - Maintenance
    - 惊群效应 Thundering Herd
        + 对于同一条数据短时间出现大量请求
        + Cache 的解决方案
        + Follow up 1: 点赞、转发、评论，都会修改这条tweet
            * write through, write back, look aside
        + Follow up 2: Cache失效怎么办
    - 数据库挂了怎么办

* 小结
    - 缓存问题
    - pull/push模型问题
    - 异步服务
    - De-normalize数据结构
    



