
* Scenario
    - 基本功能
        + 用户登录注册
        + 通讯录
        + **两个用户相互发消息**
        + **群聊**
        + **用户在线状态**
    - 附加功能
        + 历史消息
        + 多机登录
    - MAU: 1B
    - 75% 日活跃/月活跃
    - DAU: 750M(假设100M)
    - QPS:
        + 平均20条/人日
        + Average QPS = 100M * 20 / 86400 ~ 20K
        + Peak QPS = 20K * 5 = 100K
    - Storage
        + 平均10条/人日
        + storage = 1B * 30bytes = 30G
* Service
    - Message Service (信息管理)
    - Real-time Service  (实时推送)
* Storage
    - Message Table
    ```sql
        create table MessageTable (
            `id`    int,
            `thread_id` int,
            `from_user_id`  int,
            `to_user_id`    int,
            `content`   int,
            `create_at` timestamp
        )
        select * from MessageTable 
        where 
            from_user_id=A and to_user_id=B 
            or to_user_id=A and from_user_id=B
        order by create_at desc;

        create table ThreadTable (
            `owner_id`  int,
            `thread_id` int,
            `participant_ids`   text,
            `is_muted`  bool,
            `nickname`  string,
            `create_at` timestamp,
            `update_at` timestamp,
            index updateIndex(update_at)
        )
    ```
    - 存储结构
        + MessageTable (NoSQL)
            * 数据量很大， 不需要球盖，同Log
        + ThreadTable (SQL)
            * owner_id + thread_id (primary key)
            * owner_id + update_at (按照更新时间倒序排列)
        + nosql对 secondary index 支持不好
* 可行解
    - 发消息
        + Client 把消息和接受者发给 Server
        + Server 为每个接受者创建一个 Thread
        + 创建一条 message
    - 接消息
        + 每隔10秒轮询一次最新的indox
        + 有新消息提示用户
* 扩展
    - 更多功能： 群聊，在线状态
    - 更好性能： 支持更多用户，更快相应速度
        + message nosql 自动扩容
        + thread 按userid sharding
* socket
    - 
