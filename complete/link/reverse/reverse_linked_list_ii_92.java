package complete.link.reverse;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 反转链表 II
 *
    反转从位置 m 到 n 的链表。请使用一趟扫描完成反转。

    说明:
    1 ≤ m ≤ n ≤ 链表长度。

    示例:

    输入: 1->2->3->4->5->NULL, m = 2, n = 4
    输出: 1->4->3->2->5->NULL

 */
public class reverse_linked_list_ii_92 {

    public ListNode reverseBetween(ListNode head, int m, int n) {
        if (head==null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next=head;
        ListNode pre = dummy;
        for (int i=0; i<m-1; i++) pre=pre.next;

        ListNode start = pre.next, then=start.next;

        for (int i=0; i<n-m; i++) {
            start.next = then.next;
            then.next = pre.next;
            pre.next = then;
            then = start.next;
        }

        return dummy.next;
    }
}
