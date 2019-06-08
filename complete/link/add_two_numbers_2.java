package complete.link;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 两数相加
 *
    给出两个 非空 的链表用来表示两个非负的整数。其中，它们各自的位数是按照 逆序 的方式存储的，并且它们的每个节点只能存储 一位 数字。

    如果，我们将这两个数相加起来，则会返回一个新的链表来表示它们的和。

    您可以假设除了数字 0 之外，这两个数都不会以 0 开头。

    示例：

    输入：(2 -> 4 -> 3) + (5 -> 6 -> 4)
    输出：7 -> 0 -> 8
    原因：342 + 465 = 807

 */
public class add_two_numbers_2 {

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        if (l1==null) return l2;
        if (l2==null) return l1;
        ListNode n1=l1, n2=l2;
        n1.val+=n2.val;
        int pre = n1.val/10;
        n1.val%=10;
        while (n1.next!=null && n2.next!=null) {
            n1.next.val+=n2.next.val+pre;
            pre = n1.next.val/10;
            n1.next.val%=10;
            n1=n1.next;
            n2=n2.next;
        }
        if (n2.next!=null) n1.next=n2.next;
        while (pre>0 && n1.next!=null) {
            n1.next.val+=pre;
            pre=n1.next.val/10;
            n1.next.val%=10;
            n1=n1.next;
        }
        if (pre>0) n1.next=new ListNode(pre);
        return l1;
    }
}
