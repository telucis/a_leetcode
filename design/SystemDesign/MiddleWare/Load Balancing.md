### 负载均衡

负载均衡(Load Balance)，指由多台服务器以对称的方式组成一个服务器集合，每台服务器都具有等价的地位，都可以单独对外提供服务而无须其他服务器的辅助。

通过某种负载分担技术，将外部发送来的请求均匀分配到对称结构中的某一台服务器上，而接收到请求的服务器独立地回应客户的请求。

负载均衡能够平均分配客户请求到服务器阵列，借此提供快速获取重要数据，解决大量并发访问服务问题，这种集群技术可以用最少的投资获得接近于大型主机的性能。

负载均衡分为软件负载均衡和硬件负载均衡，前者的代表是章文嵩博士研发的LVS，后者则是均衡服务器比如F5。

* 负载均衡算法：
    - 轮询（Round Robin）法
    - 随机（Random）法
    - 源地址哈希（Hash）法
    - 加权轮询（Weight Round Robin）法
    - 加权随机（Weight Random）法
    - 最小连接数（Least Connections）法

模拟IP列表

```java
    public class IpMap {
        // 待路由的Ip列表，Key代表Ip，Value代表该Ip的权重
        public static HashMap serverWeightMap = new HashMap();
        static {
            serverWeightMap.put("192.168.1.100", 1);
            serverWeightMap.put("192.168.1.101", 1);
            // 权重为4
            serverWeightMap.put("192.168.1.102", 4);
            serverWeightMap.put("192.168.1.103", 1);
            serverWeightMap.put("192.168.1.104", 1);
            // 权重为3
            serverWeightMap.put("192.168.1.105", 3);
            serverWeightMap.put("192.168.1.106", 1);
            // 权重为2
            serverWeightMap.put("192.168.1.107", 2);
            serverWeightMap.put("192.168.1.108", 1);
            serverWeightMap.put("192.168.1.109", 1);
            serverWeightMap.put("192.168.1.110", 1);
        }
    }
```

* **轮询法(Round Robin)**

```java
    public class RoundRobin{
        private static Integer pos = 0;
        public static String getServer() {
            // 重建一个Map，避免服务器的上下线导致的并发问题
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);
            // 取得Ip地址List
            Set<String> keySet = serverMap.keySet();
            ArrayList<String> keyList = new ArrayList<String>();
            keyList.addAll(keySet);
            String server = null;
            synchronized (pos) {
                if (pos > keySet.size())
                pos = 0;
                server = keyList.get(pos);
                pos ++;
            }
            return server;
        }
    }
```

由于serverWeightMap中的地址列表是动态的，随时可能有机器上线、下线或者宕机

因此为了避免可能出现的并发问题，方法内部要新建局部变量serverMap，先将serverMap中的内容复制到线程本地，以避免被多个线程修改。

而这样可能会引入新的问题，复制以后serverWeightMap的修改无法反映给serverMap也就是说，这一轮选择服务器的过程中， 新增服务器或者下线服务器，负载均衡算法将无法获知。

新增无所谓，如果有服务器下线或者宕机，那么可能会访问到不存在的地址。

因此，服务调用端需要有相应的容错处理，比如重新发起一次server选择并调用。

对于当前轮询的位置变量pos，为了保证服务器选择的顺序性，需要在操作时对其加锁，使得同一时刻只能有一个线程可以修改pos的值否则当pos变量被并发修改，则无法保证服务器选择的顺序性，甚至有可能导致keyList数组越界。

轮询法的优点：**试图做到请求转移的绝对均衡。**
轮询法的缺点：**为了做到请求转移的绝对均衡，必须付出相当大的代价，因为为了保证pos变量修改的互斥性，需要引入重量级的悲观锁synchronized，这将会导致该段轮询代码的并发吞吐量发生明显的下降。**

* **随机法(Random)**

```java
    public class Random {
        public static String getServer() {
            // 重建一个Map，避免服务器的上下线导致的并发问题
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);
            // 取得Ip地址List
            Set<String> keySet = serverMap.keySet();
            ArrayList<String> keyList = new ArrayList<String>();
            keyList.addAll(keySet);
            java.util.Random random = new java.util.Random();
            int randomPos = random.nextInt(keyList.size());
            return keyList.get(randomPos);
        }
    }
```
整体代码思路和轮询法一致，先重建serverMap，再获取到server列表。

在选取server的时候，通过Random的nextInt方法取0~keyList.size()区间的一个随机值，从而从服务器列表中随机获取到一台服务器地址进行返回。

基于概率统计的理论，吞吐量越大，随机算法的效果越接近于轮询算法的效果。

* **源地址哈希法(Hash)**

```java
    public class Hash {
        public static String getServer() {
            // 重建一个Map，避免服务器的上下线导致的并发问题
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);
            // 取得Ip地址List
            Set<String> keySet = serverMap.keySet();
            ArrayList<String> keyList = new ArrayList<String>();
            keyList.addAll(keySet);
            // 在Web应用中可通过HttpServlet的getRemoteIp方法获取
            String remoteIp = "127.0.0.1";
            int hashCode = remoteIp.hashCode();
            int serverListSize = keyList.size();
            int serverPos = hashCode % serverListSize;
            return keyList.get(serverPos);
        }
    }
```

前两部分和轮询法、随机法一样就不说了，差别在于路由选择部分。

通过客户端的ip也就是remoteIp，取得它的Hash值，对服务器列表的大小取模，结果便是选用的服务器在服务器列表中的索引值。

源地址哈希法的优点：保证了相同客户端IP地址将会被哈希到同一台后端服务器，直到后端服务器列表变更。

根据此特性可以在服务消费者与服务提供者之间建立有状态的session会话。

源地址哈希算法的缺点：除非集群中服务器非常稳定，基本不会上下线。否则一旦有服务器上线、下线，那么通过源地址哈希算法路由到的服务器是服务器上线、下线前路由到的服务器的概率非常低。如果是session则取不到session，如果是缓存则可能引发"雪崩"。

* **加权轮询法(Weight Round Robin)**

```java
    public class WeightRoundRobin {
        private static Integer pos;
        public static String getServer() {
            // 重建一个Map，避免服务器的上下线导致的并发问题
            Map<String, Integer> serverMap = new HashMap<String, Integer>();
            serverMap.putAll(IpMap.serverWeightMap);
            // 取得Ip地址List
            Set<String> keySet = serverMap.keySet();
            Iterator<String> iterator = keySet.iterator();
            List<String> serverList = new ArrayList<String>();
                while (iterator.hasNext()) {
                String server = iterator.next();
                int weight = serverMap.get(server);
                for (int i = 0; i < weight; i++) serverList.add(server);
            }
            String server = null;
            synchronized (pos) {
                if (pos > keySet.size())
                pos = 0;
                server = serverList.get(pos);
                pos ++;
            }
            return server;
        }
    }
```
与轮询法类似，只是在获取服务器地址之前增加了一段权重计算的代码。

根据权重的大小，将地址重复地增加到服务器地址列表中，权重越大，该服务器每轮所获得的请求数量越多。

* **加权随机法(Weight Random)**

```java

public class WeightRandom {
    public static String getServer() {
        // 重建一个Map，避免服务器的上下线导致的并发问题
        Map<String, Integer> serverMap = new HashMap<String, Integer>();
        serverMap.putAll(IpMap.serverWeightMap);
        // 取得Ip地址List
        Set<String> keySet = serverMap.keySet();
        Iterator<String> iterator = keySet.iterator();
        List<String> serverList = new ArrayList<String>();
        while (iterator.hasNext()) {
            String server = iterator.next();
            int weight = serverMap.get(server);
            for (int i = 0; i < weight; i++) serverList.add(server);
        }
        java.util.Random random = new java.util.Random();
        int randomPos = random.nextInt(serverList.size());
        return serverList.get(randomPos);
    }
}
```

* **最小连接数法(Least Connections)**

前面几种方法费尽心思来实现服务消费者请求次数分配的均衡，当然这么做是没错的，可以为后端的多台服务器平均分配工作量，最大程度地提高服务器的利用率

但是实际情况是否真的如此？实际情况中，请求次数的均衡真的能代表负载的均衡吗？这是一个值得思考的问题。

上面的问题，再换一个角度来说就是：以后端服务器的视角来观察系统的负载，而非请求发起方来观察。

最小连接数法便属于此类。最小连接数算法比较灵活和智能，由于后端服务器的配置不尽相同，对于请求的处理有快有慢，它正是根据后端服务器当前的连接情况，动态地选取其中当前积压连接数最少的一台服务器来处理当前请求，尽可能地提高后端服务器的利用效率，将负载合理地分流到每一台机器。

由于最小连接数设计服务器连接数的汇总和感知，设计与实现较为繁琐，此处就不说它的实现了。



