> Look up service
> 
> 设计一个只读的lookup service. 后台的数据是10 billion个key-value pair, 服务形式是接受用户输入的key，返回对应的value。
> 已知每个key的size是0.1kB，每个value的size是1kB。要求系统qps >= 5000，latency < 200ms.


### work solution

* 设计目的
    - read: **硬盘二分查找**
    - write: **append操作**
* **分块有序**
    - 每一块都是内部有序
    - 写的时候只有最后一块是无序的
* 块会越写越多，会有很多重复，每次查询所有的块非常消耗时间
    - 定期K路归并

### write

* 怎么把最后一个File从无序变成有序
    - 读入内存快速排序 no
    - 硬盘外部排序 no
    - **一开始就存在内存里** yes
* 内存使用 **Skip List**存储新写入的chunk
    - 机器挂了怎么办
        + **Write Ahead Log(WAL)**
        + WAL非常方便，不像重要数据需要整理
* **写入流程**
    - client 请求写入数据
    - append key: 加入到sorted list(内存)
    - serialize to disk: 写满一个chunk持久化到disk

### read

* 读取顺序
    - Sorted List(内存)
    - File按写入的 **倒序**读取(硬盘)
* 一个File里面怎么查询数据
    - 建立 **Index**
    - 把一些Key放入内存作为Index
    - Index有效减少磁盘读写次数
* 有没有更好的方法检查一个key是否在一个File里
    - **BloomFilter**
    - Bloom Filter精度
        + 哈希函数个数
        + 位数组长度
        + 加入的字符串数目
    - Bloom Filter误判率
        + 哈希函数15个
        + 位数组大小200w
        + 加入的字符串10w个
        + 判断2000w个新的字符串
        + 误判率：3-4%
* **读取流程**
    - client 请求读取数据
    - read Sorted List: 读取内存数据
    - check bloom filter: 倒序查找bloom，定位数据
    - use index: 使用索引查找具体位置
    - read sst(Sorted String Table): 在file里取出数据

### scale

* Horizontal Sharding
    - Master + Slave
    - Master has **Consistent Hash Map**(key, server address)
* 多机怎么读
    - client 请求 master(Consistent Hash Map)
    - master 使用 rowKey 找到 serverId
    - client 请求具体server取数据
    - server 返回数据
        + memory
            * Check Memory Skip List
            * Check Bloom Filter
            * Check Index
        + disk
            * Find the value in Sstable
* 多机怎么写
    - client 请求 master
    - master 使用 rowKey 找到serverId
    - client 请求具体server写数据
    - server 返回done
        + memory
            * 直接把数据写入内存 Skip List
            * 写一次 Write Ahead Log
        + disk
            * 如果 SkipList 满了同一 serialize 到磁盘
* 每台机器数据越写越多存不下怎么办
    - 所有数据存到GFS里 - 优点
        + Disk Size
        + Replica
        + Failure and Recovery
    - Bigtable 实现逻辑存储
    - GFS 实现物理存储
* 并发读写问题 - **Race Condition**
    - 分布式锁 - **Zookeeper**
    - ZK可以存储Metadata, 取锁同时获取Sharding的server

