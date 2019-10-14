### 分布式系统

* Distributed File System
    - 怎么有效的存储数据
    - No SQL 底层需要一个存储系统
* Map Reduce
    - 怎么快速处理数据
* BigTable
    - 怎么连接底层存储与上层数据

### Scenario

* 用户写入和读取文件
    - 支持的文件越大越好， >1P
* 多台机器存储文件
    - 支持的机器越多越好， >10万台

### Service

* Master/Slave Pattern
    - Hdfs
        + Master: 管理者，不存数据
        + Slave: 被管理者，存储数据，partition关系
    - DB
        + Master: 存储数据
        + Slave: BackUp

### Storage

* 单机怎么存储文件
    - Metadata：描述其他数据(name,size,createTime)而存储的信息，访问比文件多
    - 文件：
        + Linux分开存储： 1block=1k， metadata存索引和offset
        + Win连续存储
* 单机怎么存大文件
    - 1chunk=64M/128M
    - 减少了metadata大小
    - 增大了小文件的空间浪费
* 多机怎么存超大文件
    - **One Master + many ChunkServer**
    - Master 不存储 chunk 的 diskoffset 信息
        + 降低了Master中metadata的大小
        + 降低了Master/Slave直接的通讯 (chunk大小改变不需要通知master)
* Master 存储 10P 的metadata需要多少空间
    - 1chunk 64M 需要 64B 的metadata(经验值)
    - 10P=16*10^6chunk 需要10G

### Write

* 拆分成多次写入
    - 传输过程出错，只需要重新传一小份
    - 文件的存储和传输都是以chunk为单位
    - client 可以自己拆分chunk, 不需通知master
* 写入chunkServer的过程
    - client向master发请求写入某文件的某chunk
    - **Master分配ChunkServer给Client的每个chunk, 并记录chunklist**
    - client传输chunk到chunkServer
    - chunkServer发送finish信息给master和client
* 修改文件
    - 删掉文件，重新写入

### Read

* 读取文件
    - client向master发请求读取某文件
    - master返回chunklist
    - client根据chunklist向chunkServer请求文件
* Master的任务
    - 存储各个文件数据的metadata
    - 存储Map(fileName+chunkIndex -> chunkServer)
    - 为什么不把数据直接给master写
        + Master bottleneck

### Scale - Failure and Recover

* 单Master够不够
    - 90%的系统都使用单master, simple is perfect
    - Double Master: `Apache Hadoop Goes Realtime at Facebook`
    - Multi Master: `Paxos Algorithm`
* 怎么识别磁盘上的chunk数据是否是坏的
    - **checkSum** (MD5, SHA1, SHA256, SHA512)
    - 1 checkSum size: 4bytes=32bit
    - 什么时候写入checkSum
        + 写入一块chunk的时候顺便写入
    - 什么时候检查checkSum
        + 重新读数据并且计算现在的checkSum
        + 比较现在的checkSum和之前存的checkSum是否一样
* 怎么避免chunkData丢失(当chunkServer挂掉)
    - **Replica**
    - 三个备份，两个相对较近，一个相对较远(近的方便同步/恢复数据，远的容灾)
* 选chunkSrver的时候有什么策略
    - 最近写入比较少的 - LRU
    - 磁盘存储比较低的
* chunk数据坏了怎么恢复
    - **Ask master for help**
    - 从replica中恢复数据
* 怎么发现chunkServer挂了
    - **HeartBeat**
    - chunkServers定期向master发心跳，超过一定时间未发就是挂了
* client问题
    - client只写一次是否安全
        + client会写3个replica
    - 怎么解决client的写瓶颈
        + 选队长，client只写队长，队长写其他replica
    - 怎么选队长
        + 找距离最近的(快)
        + 找现在不干活的(平衡traffic)
    - 怎么解决replica传输中chunkServer挂掉
        + **retry**
        + 找master重新要chunkserver, master和chunkServer有心跳


