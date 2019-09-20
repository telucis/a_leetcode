
### DataStruct
* HashMap
    - initCapacity: 0, defaultCapacity:16, LoadFactor:0.75, TreeifyThreshold: 8, resize: *2
    - **key** = (h=key.hashCode()) ^ (h>>>16)  目的减少碰撞
    - **resize**: index = (table.length-1) & hash  节省重新hash时间，均分节点
    - key可以为null
* HashSet
* ArrayList
    - initCapacity: 0, defaultCapacity:10, resize: *1.5
    - 读写高效，快随随机访问
    - 增删低效，需要扩容，空间效率低
* LinkedList
    - 增删高效，不需扩容，空间效率高
    - 随机访问效率低，随机访问会根据index判断前段还是后端，优化查询
* Queue
* Stack
* PriorityQueue
    - defaultCapacity: 11, maxCapacity: Integer.MAX_VALUE-8
    - **resize**: newC = oldC + ((oldC<64) ? oldC+2 : oldC>>1)
    - **数组二叉堆**: 
        + son: 2*i+1 / 2*i+2
        + farther: (i-1)>>>1
        + lastFarther: len/2-1 
* TreeMap
    - **红黑树**
        + 根和叶都为黑(叶子是NIL节点)
        + 红色节点的父子节点都为黑色
        + 从根到叶的黑色节点数目相同
    - key不可为null
* TreeSet
* SkipTable
* Tree
* **fail-fast**: 
    - 修改会增加modCount, 迭代器初始赋值给expectedModCount
    - modCount!=expectedModCount 抛出 ConcurrentModificationException

* Trie
* UnionFind
* SegmentTree

* B+Tree
* RTree
* GeoHash
* S2
* H3

### MultiThread DataStruct
* concurrentHashMap
* concurrentSkipListMap
    - 跳表/红黑树/Hash (KV存储) 优缺点
        + Hash表：插入、查找最快，为O(1)；如使用链表实现则可实现无锁；数据有序化需要显式的排序操作。
        + 红黑树：插入、查找为O(logn)，但常数项较小；无锁实现的复杂性很高，一般需要加锁；数据天然有序。
        + SkipList：插入、查找为O(logn)，但常数项比红黑树要大；底层结构为链表，可无锁实现；数据天然有序。
    - 多线程条件下不涉及红黑树的翻转问题，效率更高
* concurrent.BlockingQueue
    - 利用底层的插入、删除的CAS原子性操作，通过死循环不断获取最新的结点指针来保证不会出现竞态条件
    - 


### algorithm
* binarySearch
* partition
* merge
* bucket
* preSum
* twopoint
* sweepline
* slidingWindow
* lfu
* monotone
* dfs/memory
* backtrack
* bfs
* backpack
* sectionDp
* coordDp
* serializeDp
* reservoirSample
* bit
* greedy



