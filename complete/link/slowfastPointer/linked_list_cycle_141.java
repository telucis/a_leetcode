package complete.link.slowfastPointer;

import dataStruct.ListNode;

import java.util.HashSet;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 环形链表
 *
    给定一个链表，判断链表中是否有环。

    为了表示给定链表中的环，我们使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。 如果 pos 是 -1，则在该链表中没有环。

    示例 1：

    输入：hard = [3,2,0,-4], pos = 1
    输出：true
    解释：链表中有一个环，其尾部连接到第二个节点。


    示例 2：

    输入：hard = [1,2], pos = 0
    输出：true
    解释：链表中有一个环，其尾部连接到第一个节点。


    示例 3：

    输入：hard = [1], pos = -1
    输出：false
    解释：链表中没有环。




    进阶：

    你能用 O(1)（即，常量）内存解决此问题吗？


 */
public class linked_list_cycle_141 {
    /**
     * 快慢指针
     */
    public boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode l = head;
        ListNode f = head;
        while ((l.next != null) && (f.next != null) && (f.next.next != null)) {
            f = f.next.next;
            l = l.next;
            if (l.equals(f)) {
                return true;
            }
        }
        return false;
    }

    /**
     * hash
     */
    public boolean hasCycle2(ListNode head) {
        HashSet<ListNode> set = new HashSet<>();
        while (head != null) {
            if (set.contains(head)) {
                return true;
            }
            set.add(head);
            head = head.next;
        }
        return false;
    }
}
