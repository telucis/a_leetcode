package complete.link.traversal;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 两两交换链表中的节点
 *
    给定一个链表，两两交换其中相邻的节点，并返回交换后的链表。

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。



    示例:

    给定 1->2->3->4, 你应该返回 2->1->4->3.

 */
public class swap_nodes_in_pairs_24 {

    public ListNode swapPairs(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        if (head.next!=null) {
            ListNode tail = head.next.next;
            dummy.next = head.next;
            dummy.next.next = head;
            head.next = tail;
        }
        while (head.next!=null && head.next.next!=null) {
            ListNode left = head.next, right = head.next.next, tail = right.next;
            head.next = right;
            right.next = left;
            left.next = tail;
            head = left;
        }
        return dummy.next;
    }
}
