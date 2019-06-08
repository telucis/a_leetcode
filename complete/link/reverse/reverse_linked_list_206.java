package complete.link.reverse;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 反转链表
 *
    反转一个单链表。

    示例:

    输入: 1->2->3->4->5->NULL
    输出: 5->4->3->2->1->NULL
    进阶:
    你可以迭代或递归地反转链表。你能否用两种方法解决这道题？

 */
public class reverse_linked_list_206 {

    /**
     * 迭代
     */
    public ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = temp;
        }
        return prev;
    }

    /**
     * 递归
     */
    public ListNode reverseList2(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode prev;
        if (head.next != null) {
            prev = reverseList2(head.next);
            ListNode curr = prev;
            while (curr.next != null) {
                curr = curr.next;
            }
            curr.next = head;
            head.next = null;
        } else {
            prev = head;
        }
        return prev;
    }
}
