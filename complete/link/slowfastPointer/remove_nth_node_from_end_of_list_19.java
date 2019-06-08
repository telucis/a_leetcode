package complete.link.slowfastPointer;

import dataStruct.ListNode;

/**
 * Created by telucis on 2019/5/11.
 *
 * 删除链表的倒数第N个节点
 *
     给定一个链表，删除链表的倒数第 n 个节点，并且返回链表的头结点。

     示例：

     给定一个链表: 1->2->3->4->5, 和 n = 2.

     当删除了倒数第二个节点后，链表变为 1->2->3->5.
     说明：

     给定的 n 保证是有效的。

     进阶：

     你能尝试使用一趟扫描实现吗？


 */
public class remove_nth_node_from_end_of_list_19 {

    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode slow=head, fast=head;
        while (n>0) {
            fast=fast.next;
            n--;
        }
        if (fast==null) {
            return head.next;
        }
        while (fast.next!=null) {
            fast=fast.next;
            slow=slow.next;
        }
        slow.next=slow.next.next;
        return head;
    }
}
