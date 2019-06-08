package complete.link.traversal;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/16
 *
 * 删除排序链表中的重复元素 II
 *
    给定一个排序链表，删除所有含有重复数字的节点，只保留原始链表中 没有重复出现 的数字。

    示例 1:

    输入: 1->2->3->3->4->4->5
    输出: 1->2->5
    示例 2:

    输入: 1->1->1->2->3
    输出: 2->3

 */
public class remove_duplicates_from_sorted_list_ii_82 {

    public ListNode deleteDuplicates(ListNode head) {
        if (head==null || head.next==null) return head;
        ListNode dummy = new ListNode(0), root=dummy;
        dummy.next=head;
        int value;
        while (dummy.next!=null && dummy.next.next!=null) {
            if (dummy.next.val==dummy.next.next.val) {
                value=dummy.next.val;
                while (dummy.next!=null && dummy.next.val==value) {
                    dummy.next = dummy.next.next;
                }
            } else {
                dummy=dummy.next;
            }
        }
        return root.next;
    }
}
