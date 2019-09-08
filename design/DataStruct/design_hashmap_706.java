package complete.design.baseicDataStruct;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 设计哈希映射
 *
    不使用任何内建的哈希表库设计一个哈希映射

    具体地说，你的设计应该包含以下的功能

    put(key, value)：向哈希映射中插入(键,值)的数值对。如果键对应的值已经存在，更新这个值。
    get(key)：返回给定的键所对应的值，如果映射中不包含这个键，返回-1。
    remove(key)：如果映射中存在这个键，删除这个数值对。

    示例：

    MyHashMap mid = new MyHashMap();
    mid.put(1, 1);
    mid.put(2, 2);
    mid.get(1);            // 返回 1
    mid.get(3);            // 返回 -1 (未找到)
    mid.put(2, 1);         // 更新已有的值
    mid.get(2);            // 返回 1
    mid.remove(2);         // 删除键为2的数据
    mid.get(2);            // 返回 -1 (未找到)

    注意：

    所有的值都在 [1, 1000000]的范围内。
    操作的总数目在[1, 10000]范围内。
    不要使用内建的哈希库。

 */
public class design_hashmap_706 {

    class MyHashMap {
        private class Entity {
            public int key;
            public int val;
            public Entity next;
            public Entity(int key, int val) {
                this.key = key;
                this.val = val;
            }
        }
        private int bucketCount = 1000;
        private int length = 1000;
        private Entity[] buckets;
        /** Initialize your data structure here. */
        public MyHashMap() {
            buckets = new Entity[bucketCount];
        }

        /** value will always be non-negative. */
        public void put(int key, int value) {
            int hashCode = key % length;
            if (buckets[hashCode] == null) {
                buckets[hashCode] = new Entity(key, value);
                return;
            } else {
                Entity cur = buckets[hashCode];
                while (cur.next != null && cur.key != key) {
                    cur = cur.next;
                }
                if (cur.key == key) {
                    cur.val = value;
                    return;
                } else {
                    cur.next = new Entity(key, value);
                    return;
                }
            }
        }

        /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
        public int get(int key) {
            int hashCode = key % length;
            if (buckets[hashCode] == null) {
                return -1;
            } else {
                Entity cur = buckets[hashCode];
                while (cur.next != null && cur.key!=key) {
                    cur = cur.next;
                }
                if (cur.key == key) {
                    return cur.val;
                } else {
                    return -1;
                }
            }
        }

        /** Removes the mapping of the specified value key if this map contains a mapping for the key */
        public void remove(int key) {
            int hashCode = key % length;
            if (buckets[hashCode] == null) {
                return;
            } else {
                Entity cur = buckets[hashCode];
                if (cur.key == key) {
                    buckets[hashCode] = cur.next;
                    return;
                }
                while (cur.next != null && cur.next.key != key) {
                    cur = cur.next;
                }
                if (cur.next == null) {
                    return;
                }
                if (cur.next.key == key) {
                    cur.next = cur.next.next;
                    return;
                }
            }
        }
    }
}
