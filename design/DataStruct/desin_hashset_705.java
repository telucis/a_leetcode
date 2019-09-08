package complete.design.baseicDataStruct;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 设计哈希集合
 *
    不使用任何内建的哈希表库设计一个哈希集合

    具体地说，你的设计应该包含以下的功能

    add(value)：向哈希集合中插入一个值。
    contains(value) ：返回哈希集合中是否存在这个值。
    remove(value)：将给定值从哈希集合中删除。如果哈希集合中没有这个值，什么也不做。

    示例:

    MyHashSet hashSet = new MyHashSet();
    hashSet.add(1);
    hashSet.add(2);
    hashSet.contains(1);    // 返回 true
    hashSet.contains(3);    // 返回 false (未找到)
    hashSet.add(2);
    hashSet.contains(2);    // 返回 true
    hashSet.remove(2);
    hashSet.contains(2);    // 返回  false (已经被删除)

    注意：

    所有的值都在 [1, 1000000]的范围内。
    操作的总数目在[1, 10000]范围内。
    不要使用内建的哈希集合库。

 */
public class desin_hashset_705 {

    class MyHashSet {
        class Entity {
            public int key;
            public Entity next;
            public Entity(int key) {
                this.key = key;
            }
        }
        private int bucketCount = 1000;
        private int length = 1000;
        private Entity[] buckets;
        /** Initialize your data structure here. */
        public MyHashSet() {
            buckets = new Entity[bucketCount];
        }

        public void add(int key) {
            int hashCode = key%length;
            if (buckets[hashCode] == null) {
                buckets[hashCode] = new Entity(key);
            } else {
                Entity cur = buckets[hashCode];
                while (cur.next != null && cur.key != key) {
                    cur = cur.next;
                }
                if (cur.key == key) {
                    return;
                }
                if (cur.next == null) {
                    cur.next = new Entity(key);
                }
            }
        }

        public void remove(int key) {
            int hashCode = key%length;
            if (buckets[hashCode] == null) {
                return;
            } else {
                Entity cur = buckets[hashCode];
                if (cur.key == key) {
                    buckets[hashCode] = cur.next;
                }
                while (cur.next != null && cur.next.key == key) {
                    cur = cur.next;
                }
                if (cur.next == null) {
                    return;
                }
                if (cur.next.key == key) {
                    cur.next = cur.next.next;
                }
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            int hashCode = key%length;
            if (buckets[hashCode] == null) {
                return false;
            } else {
                Entity cur = buckets[hashCode];
                while (cur.next != null && cur.key != key) {
                    cur = cur.next;
                }
                if (cur.key == key) {
                    return true;
                }
                return false;
            }
        }
    }

    class MyHashSet2 {
        List<LinkedList<Integer>> list;
        int k=10;   //桶数
        /** Initialize your data structure here. */
        public MyHashSet2() {
            list=new ArrayList<>();
            for(int i=0;i<k;i++){
                list.add(new LinkedList<>());
            }
        }

        public void add(int key) {
            if(!contains(key))
                list.get(key%k).add(key);
        }

        public void remove(int key) {
            if(contains(key)){
                System.out.println(list.get(key%k).get(0));
                list.get(key%k).removeFirstOccurrence(key);
            }
        }

        /** Returns true if this set contains the specified element */
        public boolean contains(int key) {
            List<Integer> l=list.get(key%k);
            for(int i:l){
                if(i==key)
                    return true;
            }
            return false;

        }
    }
}
