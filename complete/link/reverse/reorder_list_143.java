package complete.link.reverse;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 重排链表
 *
    给定一个单链表 L：L0→L1→…→Ln-1→Ln ，
    将其重新排列后变为： L0→Ln→L1→Ln-1→L2→Ln-2→…

    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

    示例 1:

    给定链表 1->2->3->4, 重新排列为 1->4->2->3.
    示例 2:

    给定链表 1->2->3->4->5, 重新排列为 1->5->2->4->3.

 */
public class reorder_list_143 {

    public void reorderList(ListNode head) {
        if (head==null || head.next==null) return;
        ListNode slow=head, fast=head;
        while (fast.next!=null && fast.next.next!=null) {
            fast=fast.next.next;
            slow=slow.next;
        }
        ListNode h2 = slow.next, h1=head;
        slow.next = null;
        // reverse h2
        ListNode cur=h2, prev=null;
        while (cur!= null) {
            ListNode tmp = cur.next;
            cur.next = prev;
            prev=cur;
            cur=tmp;
        }
        h2 = prev;
        while (h2!=null) {
            ListNode t1=h1.next, t2=h2.next;
            h1.next=h2;
            h2.next=t1;
            h1=t1;
            h2=t2;
        }
    }
}
