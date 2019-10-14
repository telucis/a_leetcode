
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
* socket(实时性问题)
    - 新增Service - Push Service
    - Push Service 提供Socket连接，与Client保持TCP连接
* 支持群聊
    - 新增Service - Channel Service
    - 为每个Thread增加一个Channel信息
    - 较大群，在线用户需要先订阅到对应的Channel上
        + 用户上线，MessageService 找到用户所属Thread, 并通知ChannelService
        + 用户掉线，PushService知道哪些用户掉线，通知ChannelService
        + Channel就知道哪些频道哪些用户活着
        + 原来发送500条变成发送1条消息
* 在线状态(pull/push?)
    - push
        + 用户上下线告诉服务器状态
        + 问题1：服务器不知道什么时候下线，万一网络中断
        + 用户上下线，服务器告诉好友
        + 问题2：某片区域网络集体故障，集体上线会有网络堵塞
        + 问题3：大部分好友不在线
    - pull
        + 每个10秒告诉告诉服务器我还在，并要一下好友的状态
        + 服务器超过一分钟没收到信息，认为已经下线


## grokking

### Requirement
* Functional Requirements:
    1.  Messenger should support one-on-one conversations between users.
    2.  Messenger should keep track of online/offline statuses of its users.
    3.  Messenger should support persistent storage of chat history.
Non-functional Requirements:
    1.  Users should have real-time chat experience with minimum latency.
    2.  Our system should be highly consistent; users should be able to see the same chat history on all their devices.
    3.  Messenger’s high availability is desirable; we can tolerate lower availability in the interest of consistency.
Extended Requirements:
    ●   Group Chats: Messenger should support multiple people talking to each other in a group.
    ●   Push notifications: Messenger should be able to notify users of new messages when they are offline.

### Capacity Estimation and Constraints
Let’s assume that we have **500 million** daily active users and on average each user sends **40** messages daily; this gives us **20 billion** messages per day.

* Storage Estimation: 
    - Let’s assume that on average a message is **100 bytes**, so to store all the messages for one day we would need **2TB** of storage.
    - **20 billion messages * 100 bytes => 2 TB/day**
    - Although Facebook Messenger stores all previous chat history, but just for estimation to save five years of chat history, we would need 3.6 petabytes of storage.
    - **2 TB * 365 days * 5 years ~= 3.6 PB**
    - Other than the chat messages, we would also need to store users’ information, messages’ metadata (ID, Timestamp, etc.). Also, above calculations didn’t keep data **compression** and **replication** in consideration.
* Bandwidth Estimation: 
    - If our service is getting **2TB** of data every day, this will give us **25MB** of incoming data for each second.
    - **2 TB / 86400 sec ~= 25 MB/s**
    - Since each incoming message needs to go out to another user, we will need the same amount of bandwidth **25MB/s for both upload and download**.
