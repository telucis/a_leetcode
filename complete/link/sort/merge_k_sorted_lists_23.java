package complete.link.sort;

import dataStruct.ListNode;

import java.util.PriorityQueue;

/**
 * @author karl.wy
 * @date 2019/05/20
 *
 * 合并K个排序链表
 *
    合并 k 个排序链表，返回合并后的排序链表。请分析和描述算法的复杂度。

    示例:

    输入:
    [
    1->4->5,
    1->3->4,
    2->6
    ]
    输出: 1->1->2->3->4->4->5->6

 */
public class merge_k_sorted_lists_23 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists==null || lists.length==0) return null;
        ListNode root = new ListNode(0), node = root;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((a, b)->a.val-b.val);
        for (ListNode l : lists)
            if (l!=null) queue.offer(l);
        while (!queue.isEmpty()) {
            ListNode cur = queue.poll();
            if (cur.next!=null) queue.offer(cur.next);
            node.next = cur;
            node = node.next;
        }
        return root.next;
    }
}
