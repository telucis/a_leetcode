package complete.link;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 两数相加 II
 *
    给定两个非空链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储单个数字。将这两数相加会返回一个新的链表。



    你可以假设除了数字 0 之外，这两个数字都不会以零开头。

    进阶:

    如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。

    示例:

    输入: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出: 7 -> 8 -> 0 -> 7

 */
public class add_two_numbers_ii_445 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode h1 = copy(l1), h2=copy(l2), n1=h1, n2=h2;
        n1.val += n2.val;
        int pre=n1.val/10;
        n1.val %= 10;
        while (n1.next!=null && n2.next!=null) {
            n1.next.val = n1.next.val+n2.next.val+pre;
            pre = n1.next.val/10;
            n1.next.val %= 10;
            n1=n1.next;
            n2=n2.next;
        }
        if (n2.next!=null) {
            n1.next=n2.next;
        }
        while (pre>0 && n1.next!=null) {
            n1.next.val+=pre;
            pre=n1.next.val/10;
            n1.next.val%=10;
            n1=n1.next;
        }
        if (pre>0) n1.next = new ListNode(pre);
        return reverse(h1);
    }
    private ListNode copy(ListNode l) {
        if (l==null) return l;
        ListNode head = new ListNode(l.val), nl=head;
        l = l.next;
        while (l!=null) {
            nl.next = new ListNode(l.val);
            nl=nl.next;
            l=l.next;
        }
        return reverse(head);
    }
    private ListNode reverse(ListNode head) {
        ListNode cur=head, pre=null;
        while (cur!=null) {
            ListNode tmp = cur.next;
            cur.next=pre;
            pre=cur;
            cur=tmp;
        }
        return pre;
    }
}
