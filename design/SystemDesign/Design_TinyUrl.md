### Requirements

* Functional
    - Give a URL, generate a shorter and unique alias of it
    - Give a short URL, redirect to original link
    - pick custom alias
    - expire after a timespan or set a specify time
* non-Functional
    - highly available
    - redirect should in real-time with min latency
    - shorturl should not be guessable
* extended
    - how many times a redirection happened
    - REST APIs

### Capacity Estimation and Constraints

* read and write ratio : **100:1**

* Traffic estimates:
    - 500M new URLs per month
        + 500m / (30 * 24 * 3600) ~= **200URL/s**
    - 500M*100 = 50B redirect
        + 50b / (30 * 24 * 3600) ~= **19K/s**
* Storage estimates:
    - 500M new URLs per month and keep 5 years
        + 500m * 5 * 12 = **30billion**
    - each object be 500 bytes
        + 30b * 500byte = **15TB**
* Bandwidth estimates:
    - incoming data: 200 new URL per sec
        + 200 * 500bytes = **100KB/s**
    - outgoing data: 19K URL redirection per sec
        + 19k * 500bytes = **9MB/s**
* Memory estimates
    - cache some hot URL, follow 80-20 rule
    - 19k request per sec, and 1.7b request per day
        + 19k * 3600sec * 24hours ~= **1.7billion**
    - cache 20% of these URL
        + 0.2 * 1.7b * 500bytes ~= **170GB**

### System APIs

```
    createURL (
        api_dev_key, 
        original_url, 
        custom_alias=None, 
        user_name=None, 
        expire_data=None
    )
    deleteURL (
        api_dev_key,
        url_key
    )
```

### Dadabase Design

1. store billion of records
2. each object store is small
3. no relationships between records
4. read-heavy

Database Schema

1. URL - Nosql
    * Hash: varchar(16) - PK
    * FullUrl: text?
    * CreationDate: datetime
    * ExpirationData: datetime
    * UserID: int (if nosql: no use)
2. User - Sql
    * UserId: int - PK
    * Name: varchar(20)
    * Email: varchar(32)
    * CreationDate: datetime
    * LastLogin: datetime

### Basic System Design and Algorithm

generate a short and unique key for the given URL

#### a. Encoding actual URL
* compute a unique hash(MD5 or SHA256, etc) of given URL
    - produce a 128-bit hash code (16 byte)
* encoded for display(base64)
    - encode more than 20 characters (3->4)
    - take first 6 letters for the key
    - duplication: choose other characters or swap some characters
* length of the short key
    - 6 letter = 64^6 ~= 68.7 billion
    - 8 letter = 64^8 ~= 281 trillion

problem:

* multiple users enter same URL, get the same shortUrl
    - increasing sequence number (may be userId)
    - even after this if have a conflict, we have to generating a key until get a unique key

#### b. Generating keys offline

* have a standalone Key Generation Service (KGS) that generates random six letter
* beforhand and stores in a database (key-DB)

problem:

* concurrency problems: multiple servers reading keys concurrently, will read same key
    - KGS use two tables to store keys, noUsed/used
    - **KGS give keys to servers from noUsed table**
    - **KGS can keep some key in memory**
    - as soon as move these key, move them to used table
    - if server or KGS die, some key will be **wasting**
    - KGS has to not give the same key to multiple server, for that, it must synchronize the data structing holding the keys before removing keys from it and give them to a server
* what would be the key-DB size
    - with base64 encoding, will generate 68.7B unique 6 letter keys
    - if we need one byte to store one alpha-numeric character
        + 6(characters per key) & 68.7B(unique keys) => 412 GB
* Isn't KGS the single point of failure?
    - hava a standby replica of KGS
    - whenever primary server die, it take over to generate and provide key
* Can each app server cache some keys from key-DB
    - yes, this can surely speed things up
    - when the server dies, keys wiil be wasting, it could be acceptable
* How would we perform a key lookup
    - can loopup the key in our database or k-v store to get the full url
    - if present, then issue a "HTTP 302 Redirect"
    - if not present, issue a "HTTP 404 Not Found", redirect to homepage
* Should we impose size limits on custom aliased?
    - providing a custom alias is not mandatory
    - impose a size limit on a custom alias,to have a consistent URL database

### Data Partitioning and Replication

To scale out our DB, we need partition it so that it can store billion of URL

We need come up with a partitioning schema that divide and store data to different DB servers

#### a. Range Based Partitioning

#### b. Hash Based Partitioning
we take the hash of the 'key' or the actual URL to determine the partition to store the file.

### Cache

We can use off-the-shelf solutin like Memcache, that can store full URLs with their respective hashes.

problem:  
* How much cache should we have
    - start with 20% of daily traffic and based on clients's usage pattern we can adjust memory
    - as estimated above we need 170GB memory to cache 20% of daily traffic
    - we can fit all into one machine, or choose a couple of smaller servers to store all hot URLs
* Which cache eviction policy would best fit our needs
    - **Least Recently Used(LRU)** can be a reasonable policy
    - **Linked Hash Map** or a similar dataStructure also can keep track of which URLs are accessed recently
* How can each cache replica be updated?
    
### Load Balancer(LB)

we can add Load balancing layer at three places in our system:  
1. Between Client and Application servers
2. Between Application Servers and database servers
3. Between Application Servers and Cache servers

* Round Robin approach can be adopted
    - it's sample and does not introduce any overhead
    - if a server is dead, LB will take it out of the rotation
    - but it won't take server load into consideration

### Purging or DB cleanup

Should entries stick around forever or should they be purged?  
if a user-specified expiration time is reached, what should happend?

if we chose to actively search for expired links to remove them, it would put a lot of pressure on our database.  
we can slowly remove expired links and do a lazy cheanup.

* **Whenever a user tries to access an expired link, we can delete the link and return an error**
* A seperate Cleanup service can run periodically to remove links from storage and cache
* The service should be lightweight and scheduled to run only traffic is expected to be low
* We can have a default expiration time for each link, e.g., two years
* After removing an expired link, can put it's key back to key-DB

### Telemetry

problem:  
* How many times a short URL has been used
* what were user locations
* How would we store these statistices
* if it's part of a DB row that gets update on each view, what will happen when a popular URL is slammed with a large number of concurrent requests?

we should have statistics about the country of the visitor, date and time of access

### Security and Permissions

problem:  
* Can users create private URLs or allow a particular set of users to oaccess a URL?

* store permission level(public/private) with each URL
* also can create a table store UserIDs that have permission to see a specific URL
* store in a NoSQL wide-column database like Cassandra
    - key storing permissions would be the 'Hash'()
    - columns will store the UserIDs of those user have permission



* Scenario
    - Requirement
        + 根据 Long URL 生成一个 Short URL
        + 根据 Short URL 还原 Long URL，并跳转
    - DAU: 约100M
        + 发送
            * Average Write QPS = 100M * 0.1 / 86400 ~ 100
            * Peak Write QPS = 100 * 2 = 200
        + 点击
            * Average Read QPS = 100M * 1 / 86400 ~ 1k
            * Peak Read QPS = 2k
        + 存储
            * 100M * 0.1 ~ 10M 条
            * 每一条 URL 长度平均 100b 算，一共1G
            * 1T 的硬盘可以用 3 年
* Service
    - UrlService
    - 算法1：使用哈希函数
        + 取 LongUrl 的 MD5 后6位
        + 优点：快
        + 缺点：无法避免冲突
    - 算法2：随机生成 + 数据库去重
        + 随机生成6位 shortUrl
        + 优点：实现简单
        + 缺点：越来越慢
    - 算法3：进制转换 BASE62
        + 将6位shortUrl看做62进制整数
        + 每个shortUrl对应一个整数
        + 优点：效率高
        + 缺点：依赖全局的自增ID
* Storage
    - SQL 型数据库 用到自增id
    ```sql
        CREATE TABLE UrlTable (
            `id`    integer,
            `longUrl`   text
        )
    ```
* Scale - 提速
    - 利用缓存(Cache Aside)
        + long to short (查询新 short url 时需要)
        + short to long (查询 short url 时需要)
* Scale - **地理位置信息提速**
    - 优化服务器访问速度
        + 不同的地区，使用不同的web服务器
        + 通过DNS解析不同地区的用户到不同的服务器
    - 优化数据访问速度
        + 使用Centralized MySQL + Distributed Memcached
        + 一个MySQL配多个Memcached, Memcached跨地区分布
* Scale - 多台数据库服务器
    - 什么情况需要
        + Cache 资源不够
        + 写操作越来越多
        + 越来越多的请求无法通过 Cache 满足
    - 增加多台数据库服务器可优化什么
        + 解决"存不下"的问题 -- Storage 角度
        + 解决"忙不过"的问题 -- QPS 的角度
        + Tiny URL 主要问题是什么
    - Horizontal Sharding
        + LongURL 做 shard key
            * 查询的时候，只能广播给N台数据库查询
            * 不能解决每台QPS的问题
        + ID 做 shard key
            * 按照 ID%N 来分配存储
            * shortUrl to longUrl
                - 将shortUrl 转为ID
                - 根据ID找到数据库
                - 查出longUrl
            * longUrl to shortUrl
                - 先查询：广播 N 台数据库，查是否存在
                    + 也可行，因为数据库服务器不会太多
                - 再插入：如果不存在的话，获得下一个自增ID，插入对应数据库
* Scale - 全局自增ID
    - 如何获得在N台服务器中全局共享的一个自增ID是一个难点
    - 一种解决办法是，专门用一台数据库来做自增ID服务
        + 该数据库不存储真实数据，也不负责其他查询
        + 为了避免单点失效（Single Point Failure) 可能需要多台数据库
    - 另外一种解决办法是用 Zookeeper
* Scale - **扩展short key**
    - 如果最开始，short key 为6位，下面为short key增加 1 位前置位
        + AB1234 → 0AB1234
        + 还有一种做法是把第1位单独留出来做 sharding key，总共还是6位
    - 该前置位的值由 Hash(long_url) % 62 得到
    - 该位置则为sharding key
        + 将换分为62个区间
        + 每台机器在环上负责一段区间
    - 这样我们就可以同时通过 short_url 和 long_url 得到 Sharding Key
        + 无需广播
        + 无论是short2long还是long2short都可以直接找到数据所在服务器
* Scale - **Multi Region** 的进一步优化
    - 问题： 网站服务器 (Web Server) 与 数据库服务器 (Database) 之间的通信
        + 中心化的服务器集群（Centralized DB set）与 跨地域的 Web Server 之间通信较慢
    - 那么何不让中国的服务器访问中国的数据库？
        + 如果数据是重复写到中国的数据库，那么如何解决一致性问题？ - 很难解决
    - 想一想用户习惯
        + 中国的用户访问时，会被DNS分配中国的服务器
        + 中国的用户访问的网站一般都是中国的网站
        + 所以我们可以按照网站的 **地域信息**进行 Sharding
            * 如何获得网站的地域信息？只需要将用户比较常访问的网站弄一张表就好了
        + 中国的用户访问美国的网站怎么办？
            * 那就让中国的服务器访问美国的数据好了，反正也不会慢多少
            * 中国访问中国是主流需求，优化系统就是要优化主要的需求
* Scale - 自定义链接
    - 新建一张表存储自定义URL
        + CustomURL table
        ```sql
            CREATE TABLE CustomURL (
                `custom_url`    text,
                `long_url`      text,
                primary key `long_url`
            )
        ```
    - 查询长链接
        + 先查询CustomURL Table
        + 再查询URL Table
    - 根据长链接创建普通短连接
        + 先查询CustomURL是否存在
        + 再在URL table中查询和插入
    - 常见自定义短连接
        + 查询是否已经在URL Table中存在
        + 再在CustomURL 中查询和插入

* 小结
    - General Questions
        + How to sharding
        + How to replica
    - SQL vs NoSQL
    - 读多 - 用 Cache 优化
    - 写多 - 拆分数据库
    - Multi Region
        + 不同区域之间访问速度很慢
        + 用户跨区访问比服务器跨区访问更慢




