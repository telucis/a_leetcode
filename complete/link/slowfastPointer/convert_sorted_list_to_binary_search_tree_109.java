package complete.link.slowfastPointer;

import dataStruct.ListNode;
import dataStruct.TreeNode;

/**
 * @author karl.wy
 * @date 2019/05/14
 *
 * 有序链表转换二叉搜索树
 *
    给定一个单链表，其中的元素按升序排序，将其转换为高度平衡的二叉搜索树。

    本题中，一个高度平衡二叉树是指一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1。

    示例:

    给定的有序链表： [-10, -3, 0, 5, 9],

    一个可能的答案是：[0, -3, 9, -10, null, 5], 它可以表示下面这个高度平衡二叉搜索树：

    0
    / \
    -3   9
    /   /
    -10  5

 */
public class convert_sorted_list_to_binary_search_tree_109 {

    public TreeNode sortedListToBST(ListNode head) {
        if (head==null) return null;
        if (head.next==null) return new TreeNode(head.val);
        ListNode slow=new ListNode(0), fast=slow;
        slow.next = head;
        while (fast.next!=null && fast.next.next!=null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        TreeNode root = new TreeNode(slow.next.val);
        ListNode right = slow.next.next;
        slow.next=null;
        root.left = sortedListToBST(head);
        root.right = sortedListToBST(right);
        return root;
    }
}
