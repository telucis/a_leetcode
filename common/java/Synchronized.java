package common.java;

/**
 * @author karl.wy
 * @date 2019/07/24
 *
 * 验证：
 * 1. synchronize修饰的方法和 synchronize(this) 都是锁住自己本身的对象
 * 2. synchronize(class) synchronize(object) 都是锁别的对象
 */
public class Synchronized {

    public class HotProductService {
        /**
         * synchronized(this)
         */
        public void serviceMethodA() {
            try {
                synchronized (this) {
                    System.out.println("HotProductService: " + "A begin time = " + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("HotProductService: " + "A end time = " + System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void serviceMethodB(){
            synchronized (this) {
                System.out.println("HotProductService: "+"B begin time = "+System.currentTimeMillis());
                System.out.println("HotProductService: "+"B end   time = "+System.currentTimeMillis());
            }
        }
        /**
         * synchronized method
         */
        public synchronized void objectMethodA() {
            try {
                System.out.println("HotProductService: run----objectMethodA");
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public void objectMethodB() {
            synchronized (this) {
                try {
                    for (int i = 1; i <= 10; i++) {
                        System.out.println("HotProductService: synchronized thread name:"+Thread.currentThread().getName()+"-->i="+i);
                        Thread.sleep(1000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        /**
         * synchronized object
         */
        private String uname;
        private String pwd;
        String lock = new String();
        public void setUserNamePassWord(String userName, String passWord) {
            try {
                synchronized (lock) {
                    System.out.println("HotProductService: thread name" + Thread.currentThread().getName() + "进入代码块:l " + System.currentTimeMillis());
                    uname = userName;
                    Thread.sleep(3000);
                    pwd = passWord;
                    System.out.println("HotProductService: thread name" + Thread.currentThread().getName() + "进入代码块:l " + System.currentTimeMillis()
                    + "入参uname: "+uname+" 入参pwd: "+pwd);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        /**
         * synchronized class
         */
        public  void methodA(){
            try {
                synchronized (HotProductService.class){
                    System.out.println("HotProductService: "+"static methodA begin Name:" + Thread.currentThread().getName() + " times:" + System.currentTimeMillis());
                    Thread.sleep(2000);
                    System.out.println("HotProductService: "+"static methodA end   Name:"+Thread.currentThread().getName()+" times:"+System.currentTimeMillis());
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        public  void methodB(){
            synchronized (HotProductService.class){
                System.out.println("HotProductService: "+"static methodB begin Name:"+Thread.currentThread().getName()+" times:"+System.currentTimeMillis());
                System.out.println("HotProductService: "+"static methodB end   Name:"+Thread.currentThread().getName()+" times:"+System.currentTimeMillis());
            }
        }
    }
    public class ThreadA extends Thread {
        private HotProductService objectService;
        public ThreadA(HotProductService objectService) {
            super();
            this.objectService=objectService;
        }
        @Override
        public void run() {
            super.run();
            objectService.setUserNamePassWord("a", "aa");
        }
    }
    public class ThreadB extends Thread {
        private HotProductService objectService;
        public ThreadB(HotProductService objectService) {
            super();
            this.objectService=objectService;
        }
        @Override
        public void run() {
            super.run();
            objectService.setUserNamePassWord("b", "bb");
        }
    }
    public void test1() {
        HotProductService service = new HotProductService();
        ThreadA a = new ThreadA(service);
        ThreadB b = new ThreadB(service);
        a.start();
        b.start();
    }

    public static void main(String[] args) {
        Synchronized ss = new Synchronized();
        ss.test1();
    }
}
