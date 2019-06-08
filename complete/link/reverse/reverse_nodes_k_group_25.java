package complete.link.reverse;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/20
 *
 * k个一组翻转链表
 *
    给出一个链表，每 k 个节点一组进行翻转，并返回翻转后的链表。

    k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么将最后剩余节点保持原有顺序。

    示例 :

    给定这个链表：1->2->3->4->5

    当 k = 2 时，应当返回: 2->1->4->3->5

    当 k = 3 时，应当返回: 3->2->1->4->5

    说明 :

    你的算法只能使用常数的额外空间。
    你不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。

 */
public class reverse_nodes_k_group_25 {

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head==null || head.next==null) return head;
        ListNode dummy = new ListNode(0), node=head, pre=dummy, then;
        dummy.next = head;
        int len = 0;
        while (node!=null) {
            len++;
            node=node.next;
        }
        int n = len / k;
        node=head; then=node.next;
        while (n-->0) {
            int i=1;
            while (i++<k) {
                node.next = then.next;
                then.next = pre.next;
                pre.next = then;
                then = node.next;
            }
            pre=node;
            if (pre.next==null || pre.next.next==null) break;
            node=pre.next;then=node.next;
        }
        return dummy.next;
    }
}
