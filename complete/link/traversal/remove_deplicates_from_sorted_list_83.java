package complete.link.traversal;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/04/25
 *
 * 删除排序链表中的重复元素
 *
    给定一个排序链表，删除所有重复的元素，使得每个元素只出现一次。

    示例 1:

    输入: 1->1->2
    输出: 1->2
    示例 2:

    输入: 1->1->2->3->3
    输出: 1->2->3

 */
public class remove_deplicates_from_sorted_list_83 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            while (node.next != null && node.val == node.next.val) {
                node.next = node.next.next;
            }
            if (node.next != null) {
                node = node.next;
            }
        }
        return head;
    }
}
