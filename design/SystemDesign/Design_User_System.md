* Design User System
    - Memcached
    - Authentication
    - SQL vs NoSQL
    - Friendship
* How to scale
    - Sharding
    - Consistent Hashing
    - Replica

* Scenario
    - 注册、登录、查询、用户信息修改
    - 支持100M DAU
    - 注册、登录、信息修改 QPS 约
        + 100M * 0.1/86400 ~ 100
        + Peak: 100*3 = 300
    - 查询 QPS 约
        + 100M * 100 /86400 ~ 100k
        + 100 = 平均每个用户查询用户信息相关的操作
        + Peak: 100k*3 = 300k
* Service
    - AuthService 负责登录注册
    - UserService 负责用户信息存储于查询
    - FriendshipService 负责好友关系存储
    - 用户系统特点
        + 读非常多、写非常少
        + 读多写少的系统，一定需要缓存优化
* 缓存更新模式
    - Cache Aside
        + **先把数据存到数据库，成功后，再让缓存失效**
        + 需要设置缓存过期时间
        + 读写分离，缓存过期等会导致异常
    - Read/Write Through
        + 同步操作，一个数据存储
    - Write Behind Caching
        + 只更新缓存，不更新数据库，缓存异步更新数据库
        + 多次操作可以合并持久化到数据库
        + 数据不是强一致性的，而且可能会丢失
* Authentication Service
    - 会话表Session，实现登录与保持登录状态
    - 用户Login后
        + 创建一个session对象
        + 把session_key 作为 cookie 值返回给浏览器
        + 浏览器把值记录在浏览器cookie中
        + 用户每次发请求，自动带上网站所有的cookie
        + 服务器检测cookie中的session_key是有效的
    - 用户Logout后
        + 从session table 中删除对应数据
    - Session Table 存在哪儿
        + 都可以，可以存在数据库中，用Cache做优化
* FriendShip Service
    - 单向好友关系 from_user_id/to_user_id
    - 双向好友关系
        + 1. 存成两条信息，A关注B，B关注A
        ```sql
            SELECT * FROM friendship WHERE from_user_id=2;
        ```
        + 2. 存成一条信息，但查询是需要查两次
        ```sql
            SELECT * FROM friendship WHERE bigger_user_id=2 OR smaller_user_id=2;
        ```
    - NoSql中存储(FriendShip/NewsFeed)
        + RowKey: user_id, ColumnKey: friend_user_id, Value: data
        + RowKey: owner_id, ColumnKey: create_time,tweet_id, Value: tweet_data

* Scale
    - Horizontal Sharding
        + 按照一定规则，将数据拆分成不同的部分，保存在不同的机器
        + 就算挂了也不会导致网站100%不可用
        + 分摊读写请求，提升性能
    - Replica
        + NWR策略
        + 读写分离
        + SQL
            * Master-Slave
            * Write Ahead Log
        + NoSQL
            * 顺时针找3台机器
    - Vertical Sharding
        + User Table 结构
            * Email/Username/Password/push_preference/avatar
        + 根据使用频率拆分两个表
            * User_Table: email/username/password
            * User_Profile_Table: push_preference/avatar
* 小结
    - 

