package complete.link.traversal;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/11
 *
 * 旋转链表
 *
    给定一个链表，旋转链表，将链表每个节点向右移动 k 个位置，其中 k 是非负数。

    示例 1:

    输入: 1->2->3->4->5->NULL, k = 2
    输出: 4->5->1->2->3->NULL
    解释:
    向右旋转 1 步: 5->1->2->3->4->NULL
    向右旋转 2 步: 4->5->1->2->3->NULL
    示例 2:

    输入: 0->1->2->NULL, k = 4
    输出: 2->0->1->NULL
    解释:
    向右旋转 1 步: 2->0->1->NULL
    向右旋转 2 步: 1->2->0->NULL
    向右旋转 3 步: 0->1->2->NULL
    向右旋转 4 步: 2->0->1->NULL

 */
public class rotate_list_61 {

    public ListNode rotateRight(ListNode head, int k) {
        if (head==null) return head;
        ListNode tail=head, newT=head;
        int len=1;
        while (tail.next!=null) {
            tail=tail.next;
            len++;
        }
        tail.next = head;
        k = k%len;
        int n=len-k-1;
        while (n>0) {
            newT=newT.next;
            n--;
        }
        ListNode newH = newT.next;
        newT.next=null;
        return newH;
    }
}
