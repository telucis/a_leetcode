package complete.link.traversal;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 移除链表元素
 *
    删除链表中等于给定值 val 的所有节点。

    示例:

    输入: 1->2->6->3->4->5->6, val = 6
    输出: 1->2->3->4->5

 */
public class remove_linked_list_elements_203 {

    public ListNode removeElements(ListNode head, int val) {
        if (head == null) {
            return head;
        }
        while (head != null && head.val == val) {
            head = head.next;
        }
        ListNode node = head;
        while (node!=null) {
            while (node.next!=null && node.next.val == val) {
                node.next = node.next.next;
            }
            node = node.next;
        }
        return head;
    }
}
