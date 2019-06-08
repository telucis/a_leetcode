package complete.link.reverse;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/04/17
 *
 * 回文链表
 *
    请判断一个链表是否为回文链表。

    示例 1:

    输入: 1->2
    输出: false
    示例 2:

    输入: 1->2->2->1
    输出: true
    进阶：
    你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？

 */
public class palindrome_linked_list_234 {

    public boolean isPalindrome(ListNode head) {
        if (head == null) {
            return true;
        }
        ListNode l = head, f = head, pre = null;
        // find mid
        while (f != null) {
            l = l.next;
            f = f.next!=null ? f.next.next : f.next;
        }
        // reverse
        while (l != null) {
            ListNode ovn = l.next;
            l.next = pre;
            pre = l;
            l = ovn;
        }
        // check
        while (head!=null && pre!=null) {
            if (head.val != pre.val) {
                return false;
            }
            head = head.next;
            pre = pre.next;
        }
        return true;
    }
}
