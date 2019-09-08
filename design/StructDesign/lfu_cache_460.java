package complete.design.cache;

import java.util.HashMap;
import java.util.Map;

/**
 * @author karl.wy
 * @date 2019/05/27
 *
 * LFU缓存
 *
    设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。

    get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
    put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。

    进阶：
    你是否可以在 O(1) 时间复杂度内执行两项操作？

    示例：

    LFUCache cache = new LFUCache( 2 // capacity (缓存容量) );

        cache.put(1, 1);
        cache.put(2, 2);
        cache.get(1);       // 返回 1
        cache.put(3, 3);    // 去除 key 2
        cache.get(2);       // 返回 -1 (未找到key 2)
        cache.get(3);       // 返回 3
        cache.put(4, 4);    // 去除 key 1
        cache.get(1);       // 返回 -1 (未找到 key 1)
        cache.get(3);       // 返回 3
        cache.get(4);       // 返回 4

    */
public class lfu_cache_460 {

    class LFUCache {
        int capacity, size, min;
        Map<Integer, Node> nodeMap;
        Map<Integer, DLList> countMap;
        public LFUCache(int capacity) {
            this.capacity = capacity;
            nodeMap = new HashMap<>();
            countMap = new HashMap<>();
        }

        public int get(int key) {
            Node node = nodeMap.get(key);
            if (node==null) return -1;
            update(node);
            return node.val;
        }

        public void put(int key, int value) {
            if (capacity==0) return;
            Node node;
            if (nodeMap.containsKey(key)) {
                node = nodeMap.get(key);
                node.val = value;
                update(node);
            } else {
                node = new Node(key, value);
                nodeMap.put(key, node);
                if (size==capacity) {
                    DLList lastList = countMap.get(min);
                    nodeMap.remove(lastList.removeLast().key);
                    size--;
                }
                size++;
                min=1;
                DLList newList = countMap.getOrDefault(node.cnt, new DLList());
                newList.add(node);
                countMap.put(node.cnt, newList);
            }
        }
        private void update(Node node) {
            DLList oldList = countMap.get(node.cnt);
            oldList.remove(node);
            if (node.cnt==min && oldList.size==0) min++;
            node.cnt++;
            DLList newList = countMap.getOrDefault(node.cnt, new DLList());
            newList.add(node);
            countMap.put(node.cnt, newList);
        }
        private class Node {
            int key, val, cnt;
            Node prev, next;

            public Node(int key, int val) {
                this.key = key;
                this.val = val;
                cnt=1;
            }
        }
        private class DLList {
            Node head, tail;
            int size;

            public DLList() {
                this.head = new Node(0, 0);
                this.tail = new Node(0, 0);
                head.next = tail;
                tail.prev = head;
            }
            void add(Node node) {
                node.next = head.next;
                node.prev = head;
                head.next.prev = node;
                head.next = node;
                size++;
            }
            void remove(Node node) {
                node.prev.next = node.next;
                node.next.prev = node.prev;
                size--;
            }
            Node removeLast() {
                if (size>0) {
                    Node node = tail.prev;
                    remove(node);
                    return node;
                } else return null;
            }
        }
    }
}
