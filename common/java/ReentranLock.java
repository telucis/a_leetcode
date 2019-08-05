package common.java;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author karl.wy
 * @date 2019/07/26
 */
public class ReentranLock {
    public void test() {
        Lock lock = new ReentrantLock();
        lock.lock();
        try {

        } finally {
            lock.unlock();
        }
    }

    public class Resource {
        private int resourceNum;
        public Lock lock = new ReentrantLock();

        public Resource(int resourceNum) {
            this.resourceNum = resourceNum;
        }

        public int getResourceNum() {return resourceNum;};
    }
    public class LockTest1 {
        public int getResource(Resource resourceA, Resource resourceB, long timeout, TimeUnit unit) {
            try {
                long stopTime = System.nanoTime();
                while (true) {
                    try {
                        if (resourceA.lock.tryLock()) {
                            try {
                                if (resourceB.lock.tryLock()) {
                                    return getSum(resourceA, resourceB);
                                }
                            } finally {
                                resourceB.lock.unlock();
                            }
                        }
                    } finally {
                        resourceA.lock.unlock();
                    }
                    if (System.nanoTime() > stopTime) return -1;
                    TimeUnit.SECONDS.sleep(1);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return -1;
        }
        public int getSum(Resource resourceA, Resource resourceB) {
            return resourceA.getResourceNum()+resourceB.getResourceNum();
        }
    }
}
