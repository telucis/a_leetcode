#### 限流器
1. 计数器算法
```java
    AtomicLong#incrementAndGet()
```
2. 漏桶算法
```java
// 维护一个队列，保存请求，另外通过一个线程池定期从队列中获取请求并执行
```
3. 令牌桶算法