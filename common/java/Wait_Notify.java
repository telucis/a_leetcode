package common.java;

import java.util.LinkedList;

/**
 * @author karl.wy
 * @date 2019/07/26
 */
public class Wait_Notify {

    public class K {
        private Object lock;
        private int now, need;
        public void produce(int num) {
            synchronized (lock) {
                while (now < need) {
                    try {
                        wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("我被唤醒了!");
            }
        }
    }

    /**
     * 生产者消费者的问题
     */
    public interface AbstractStorage {
        void consume(int num);
        void produce(int num);
    }
    /**
     * 仓库类
     */
    public class Storage implements  AbstractStorage {
        private final int MAX_SIZE = 100;
        private LinkedList list = new LinkedList();
        @Override
        public void produce(int num) {
            synchronized (list) {
                while (list.size()+num > MAX_SIZE) {
                    System.out.println("【要生产的产品数量】:"+num+"\t【库存量】:"+list.size()+"\t暂时不能执行生产任务！");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0; i<num; i++) {
                    list.add(new Object());
                }
                System.out.println("【已经生产产品数】:" + num + "\t【现仓储量为】:" + list.size());
                list.notifyAll();
            }
        }
        @Override
        public void consume(int num) {
            synchronized (list) {
                while (num>list.size()) {
                    System.out.println("【要消费的产品数量】:" + num + "\t【库存量】:" + list.size() + "\t暂时不能执行消费任务!");
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                for (int i=0; i<num; i++) {
                    list.remove();
                }
                System.out.println("【已经消费产品数】:" + num + "\t【现仓储量为】:" + list.size());
                list.notifyAll();
            }
        }
    }
    /**
     * 生产者类
     */
    public class Producer extends Thread {
        private int num;
        public AbstractStorage abstractStorage;
        public Producer(AbstractStorage abstractStorage) {
            this.abstractStorage = abstractStorage;
        }
        public void setNum(int num) {
            this.num = num;
        }
        @Override
        public void run() {
            produce(num);
        }
        public void produce(int num) {
            abstractStorage.produce(num);
        }
    }
    /**
     * 消费者类
     */
    public class Consumer extends Thread {
        private int num;
        private AbstractStorage abstractStorage;
        public Consumer(AbstractStorage abstractStorage) {
            this.abstractStorage = abstractStorage;
        }
        public void run() {
            consum(num);
        }
        public void consum(int num) {
            abstractStorage.consume(num);
        }
        public void setNum(int num) {
            this.num = num;
        }
    }
    /**
     * 测试
     */
    public void test() {
        AbstractStorage abstractStorage = new Storage();

        Producer p1 = new Producer(abstractStorage);
        Producer p2 = new Producer(abstractStorage);
        Producer p3 = new Producer(abstractStorage);
        Producer p4 = new Producer(abstractStorage);
        Producer p5 = new Producer(abstractStorage);
        Producer p6 = new Producer(abstractStorage);
        Producer p7 = new Producer(abstractStorage);

        Consumer c1 = new Consumer(abstractStorage);
        Consumer c2 = new Consumer(abstractStorage);
        Consumer c3 = new Consumer(abstractStorage);

        p1.setNum(10);
        p2.setNum(10);
        p3.setNum(10);
        p4.setNum(10);
        p5.setNum(10);
        p6.setNum(10);
        p7.setNum(90);

        c1.setNum(50);
        c2.setNum(20);
        c3.setNum(30);

        c1.start();
        c2.start();
        c3.start();

        p1.start();
        p2.start();
        p3.start();
        p4.start();
        p5.start();
        p6.start();
        p7.start();
    }
    public static void main(String[] args) {
        Wait_Notify wait_notify = new Wait_Notify();
        wait_notify.test();
    }
}
