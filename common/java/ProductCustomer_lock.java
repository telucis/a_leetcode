package common.java;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author karl.wy
 * @date 2019/09/02
 */
public class ProductCustomer_lock {

    public static class Depot {
        private int depotSize;
        private Lock lock;
        private int capaity;

        private Condition fullCondition;
        private Condition emptyCondition;

        public Depot() {
            this.depotSize = 0;
            this.lock = new ReentrantLock();
            this.capaity = 15;
            this.fullCondition = lock.newCondition();
            this.emptyCondition = lock.newCondition();
        }

        public void put(int value) {
            lock.lock();
            try {
                int left = value;
                while(left > 0){
                    //库存已满时，“生产者”等待“消费者”消费
                    while(depotSize >= capaity){
                        fullCondition.await();
                    }
                    //获取实际入库数量：预计库存（仓库现有库存 + 生产数量） > 仓库容量   ? 仓库容量 - 仓库现有库存     :    生产数量
                    //                  depotSize   left   capaity  capaity - depotSize     left
                    int inc = depotSize + left > capaity ? capaity - depotSize : left;
                    depotSize += inc;
                    left -= inc;
                    System.out.println(Thread.currentThread().getName() + "----要入库数量: " + value +";;实际入库数量：" + inc + ";;仓库货物数量：" + depotSize + ";;没有入库数量：" + left);

                    //通知消费者可以消费了
                    emptyCondition.signal();
                }
            } catch (InterruptedException e) {
            } finally{
                lock.unlock();
            }
        }

        public void get(int value) {
            lock.lock();
            try {
                int left = value;
                while(left > 0){
                    //仓库已空，“消费者”等待“生产者”生产货物
                    while(depotSize <= 0){
                        emptyCondition.await();
                    }
                    //实际消费      仓库库存数量     < 要消费的数量     ?   仓库库存数量     : 要消费的数量
                    int dec = depotSize < left ? depotSize : left;
                    depotSize -= dec;
                    left -= dec;
                    System.out.println(Thread.currentThread().getName() + "----要消费的数量：" + value +";;实际消费的数量: " + dec + ";;仓库现存数量：" + depotSize + ";;有多少件商品没有消费：" + left);

                    //通知生产者可以生产了
                    fullCondition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally{
                lock.unlock();
            }
        }
    }

    public static class Producer {
        private Depot depot;

        public Producer(Depot depot){
            this.depot = depot;
        }

        public void produce(final int value){
            new Thread(){
                public void run(){
                    depot.put(value);
                }
            }.start();
        }
    }

    public static class Customer {
        private Depot depot;

        public Customer(Depot depot){
            this.depot = depot;
        }

        public void consume(final int value){
            new Thread(){
                public void run(){
                    depot.get(value);
                }
            }.start();
        }
    }

    public static void main(String[] args) {
        Depot depot = new Depot();

        Producer producer = new Producer(depot);
        Customer customer = new Customer(depot);

        producer.produce(10);
        customer.consume(5);
        producer.produce(15);
        customer.consume(10);
        customer.consume(15);
        producer.produce(10);
    }
}
