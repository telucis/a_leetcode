
### WordCount

* MapReduce: 一套实现分布式运算的框架
    - Input: 设定好输入文件
    - Split: 系统帮我们把文件尽量平分到每个机器
    - **Map**: 把文章拆分成单词
    - Shuffle: 传输整理
    - **Reduce**: 把单词次数合并在一起
    - Output: 设定输出文件
* 函数接口
    - Map输入： key:文章存储地址 Value:文章内容
    - Reduce输入： key:map输出的key Value:map输出的value
* Question
    - Map多少机器，Reduce多少机器
        + 全由自己觉得，一般1000map, 1000reduce规模
        + 机器越多越好么
            * 机器越多，每台处理的越少，总处理数据越快
            * 启动机器的时机变长
        + 不考虑启动时机，reduce机器越多越好么
            * key的数据就是reduce的上限
* Shuffle怎么设计
    - Partition & Sort
        + Map端做 **硬盘外排序**
        + 把输出做了对应reduce的partition和sort
    - Fetch & MergeSort
        + **K路归并**
        + Fetch对应partition的数据
        + mergeSort对应数据

### Design a MapReduce

* MapReduce 流程
    - Start: 用户程序开始master和worker
    - Assign Task: master分配任务给mapWorker和reduceworker
    - Split: master切分输入数据
    - Map Read: 每个mapWorker读取切分的输入
    - Map: 每个mapWorker处理`map`任务
    - Map Ouput: 每个mapWorker输出文件到本地磁盘
    - Reduce Fetch: 每个reduceWorder从mapWorker上拉取数据
    - Reduce: reduceWorker处理`reduce`任务
    - Reduce Output: reduceWorker 输出最终文件
* Question
    - Mapper和Reducer工作顺序
        + Mapper结束了后Reducer开始运行
    - 运行过程中一个Worder挂了怎么办
        + 重新分配一台机器做
    - Input和Output存在哪儿
        + HDFS
    - LocalDisk上的Mapper output data有没有必要存在HDFS上
        + 不需要，丢了重做
    - Mapper和Reducer可以放同一台机器么
        + 不建议，因为Mapper和Reducer之前都有很多需要预处理的工作，两台机器可以并行做
