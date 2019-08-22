package complete.link.sort;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/10
 *
 * 分隔链表
 *
    给定一个链表和一个特定值 x，对链表进行分隔，使得所有小于 x 的节点都在大于或等于 x 的节点之前。

    你应当保留两个分区中每个节点的初始相对位置。

    示例:

    输入: hard = 1->4->3->2->5->2, x = 3
    输出: 1->2->2->4->3->5

 */
public class partition_list_86 {

    public ListNode partition(ListNode head, int x) {
        ListNode ahead = new ListNode(0), bhead = new ListNode(0);
        ListNode atail = ahead, btail = bhead;
        while (head != null) {
            if (head.val >= x) {
                btail.next = head;
                btail = btail.next;
            } else {
                atail.next = head;
                atail = atail.next;
            }
            head = head.next;
        }
        btail.next = null;
        atail.next = bhead.next;
        return ahead.next;
    }
}
