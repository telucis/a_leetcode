package complete.design;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/**
 * @author karl.wy
 * @date 2019/05/27
 *
 * 全 O(1) 的数据结构
 *
    实现一个数据结构支持以下操作：

    Inc(key) - 插入一个新的值为 1 的 key。或者使一个存在的 key 增加一，保证 key 不为空字符串。
    Dec(key) - 如果这个 key 的值是 1，那么把他从数据结构中移除掉。否者使一个存在的 key 值减一。如果这个 key 不存在，这个函数不做任何事情。key 保证不为空字符串。
    GetMaxKey() - 返回 key 中值最大的任意一个。如果没有元素存在，返回一个空字符串""。
    GetMinKey() - 返回 key 中值最小的任意一个。如果没有元素存在，返回一个空字符串""。
    挑战：以 O(1) 的时间复杂度实现所有操作。


 */
public class all_oone_data_structure_432 {

    class AllOne {
        private valueNode head;
        private valueNode tail;
        private valueNode curNode;
        private HashMap<String, valueNode> keyMap;
        /** Initialize your data structure here. */
        public AllOne() {
            keyMap = new HashMap<>();
        }

        /** Inserts a new key <Key> with value 1. Or increments an existing key by 1. */
        public void inc(String key) {
            if (tail==null) {
                curNode = new valueNode(1, key);
                head = curNode;
                tail = curNode;
                keyMap.put(key, curNode);
            } else if (!keyMap.containsKey(key)) {
                if (tail.value==1) {
                    tail.curKeys.add(key);
                    keyMap.put(key, tail);
                } else {
                    curNode = new valueNode(1, key);
                    curNode.pre = tail;
                    tail.next = curNode;
                    tail = curNode;
                    keyMap.put(key, curNode);
                }
            } else {
                curNode = keyMap.get(key);
                if (curNode.pre != null) {
                    if (curNode.pre.value == curNode.value+1) {
                        curNode.pre.curKeys.add(key);
                        curNode.curKeys.remove(key);
                        keyMap.put(key, curNode.pre);
                        checkEmpty(curNode);
                    } else {
                        valueNode newNode = new valueNode(curNode.value+1, key);
                        newNode.pre = curNode.pre;
                        newNode.next = curNode;
                        newNode.pre.next = newNode;
                        curNode.pre = newNode;
                        curNode.curKeys.remove(key);
                        keyMap.put(key, newNode);
                        checkEmpty(curNode);
                    }
                } else {
                    head = new valueNode(curNode.value+1, key);
                    head.next = curNode;
                    curNode.pre = head;
                    curNode.curKeys.remove(key);
                    keyMap.put(key, head);
                    checkEmpty(curNode);
                }
            }
            System.out.println(head.value + " " + head.curKeys.get(0));
        }

        /** Decrements an existing key by 1. If Key's value is 1, remove it from the data structure. */
        public void dec(String key) {
            if (head==null || !keyMap.containsKey(key)) return;
            curNode = keyMap.get(key);
            if (curNode.next != null) {
                if (curNode.next.value==curNode.value-1) {
                    curNode.next.curKeys.add(key);
                    curNode.curKeys.remove(key);
                    keyMap.put(key, curNode.next);
                    checkEmpty(curNode);
                } else {
                    valueNode newNode = new valueNode(curNode.value-1, key);
                    newNode.next = curNode.next;
                    newNode.pre = curNode;
                    newNode.next.pre = newNode;
                    curNode.next = newNode;
                    curNode.curKeys.remove(key);
                    keyMap.put(key, newNode);
                    checkEmpty(curNode);
                }
            } else {
                if (curNode.value==1) {
                    curNode.curKeys.remove(key);
                    keyMap.remove(key);
                    checkEmpty(curNode);
                } else {
                    tail = new valueNode(curNode.value-1, key);
                    tail.pre = curNode;
                    curNode.next = tail;
                    curNode.curKeys.remove(key);
                    keyMap.put(key, tail);
                    checkEmpty(curNode);
                }
            }
        }

        /** Returns one of the keys with maximal value. */
        public String getMaxKey() {
            if (head==null) return "";
            return head.curKeys.get(0);
        }

        /** Returns one of the keys with Minimal value. */
        public String getMinKey() {
            if (tail==null) return "";
            return tail.curKeys.get(0);
        }
        private void checkEmpty(valueNode checkNode) {
            if (checkNode.curKeys.size()!=0) return;
            if (checkNode.pre==null && checkNode.next==null) {
                head=null;
                tail=null;
            } else if (checkNode.pre==null && checkNode.next!=null) {
                head = checkNode.next;
                head.pre=null;
            } else if (checkNode.next==null && checkNode.pre!=null) {
                tail = checkNode.pre;
                tail.next = null;
            } else {
                checkNode.pre.next = checkNode.next;
                checkNode.next.pre = checkNode.pre;
            }
        }
        private class valueNode {
            valueNode pre, next;
            int value;
            List<String> curKeys;

            public valueNode(int value, String key) {
                this.value = value;
                curKeys = new LinkedList<>();
                curKeys.add(key);
            }
        }
    }
}
