package common.java;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author karl.wy
 * @date 2019/09/02
 *
 * 读写锁模拟缓存器
 */
public class ReentrantReadWriteLockTest {

    private Map<String, Object> map = new HashMap<>(); //缓存器
    private ReadWriteLock rwl = new ReentrantReadWriteLock();

    public  Object get(String id) {
        Object value = null;
        rwl.readLock().lock();  //首先开启读锁，从缓存中取
        try {
            value = map.get(id);
            if (value == null) {    //如果缓存中没有数据，上写锁
                rwl.readLock().unlock();
                rwl.writeLock().lock();
                try {
                    if (value == null) {
                        value = "aaa";
                    }
                } finally {
                    rwl.writeLock().unlock();
                }
                rwl.readLock().lock();
            }
        } finally {
            rwl.readLock().unlock();
        }
        return value;
    }

}
