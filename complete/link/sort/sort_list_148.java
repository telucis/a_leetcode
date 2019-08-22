package complete.link.sort;

import dataStruct.ListNode;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 排序链表
 *
    在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序。

    示例 1:

    输入: 4->2->1->3
    输出: 1->2->3->4
    示例 2:

    输入: -1->5->3->4->0
    输出: -1->0->3->4->5

 */
public class sort_list_148 {

    ListNode quickSort(final ListNode h){
        if(h == null || h.next == null)
            return h;
        /*split into three list*/
        ListNode fakesmall = new ListNode(0), small = fakesmall;
        ListNode fakelarge = new ListNode(0), large = fakelarge;
        ListNode fakeequal = new ListNode(0), equal = fakeequal;

        ListNode cur = h; // pivot is h.
        while(cur != null){
            if(cur.val < h.val){
                small.next = cur;
                small = small.next;
            }
            else if(cur.val == h.val){
                equal.next = cur;
                equal = equal.next;
            }
            else{
                large.next = cur;
                large = large.next;
            }
            cur = cur.next;
        }
        // put an end.
        small.next = equal.next = large.next = null;
        // merge them and return . merge reusing below one. merge for quicksort should be simplified.
        return merge(merge(quickSort(fakesmall.next), quickSort(fakelarge.next)),fakeequal.next) ;
    }

    /*mrege sort*/
    ListNode mergeSort(ListNode h){
        if(h == null || h.next == null)
            return h;
        /*find cutting point*/
        ListNode slow = h, cut = null, fast = h;
        while(fast != null && fast.next != null){
            cut = slow;
            slow = slow.next;
            fast = fast.next.next;
        }
        /*cut*/
        cut.next = null;
        return merge(mergeSort(h),mergeSort(slow));
    }

    ListNode merge(ListNode h, ListNode m){
        ListNode fake = new ListNode(0), cur = fake;
        while(h != null && m != null){
            if(h.val < m.val){
                cur.next = h;
                h = h.next;
            }
            else{
                cur.next = m;
                m = m.next;
            }
            cur = cur.next;
        }
        cur.next = (h == null ? m : h);
        return fake.next;
    }


    /**
     * quick sort
     */
    public ListNode sortList(ListNode head) {
        if (head==null || head.next==null) {
            return head;
        }
        ListNode left=new ListNode(0), right=new ListNode(0);
        ListNode node=head.next, l=left, r=right;
        while (node!=null) {
            if (node.val<head.val) {
                l.next = node;
                l=l.next;
            } else {
                r.next = node;
                r=r.next;
            }
            node=node.next;
        }
        l.next=null;
        r.next=null;
        ListNode lChild = sortList(left.next);
        ListNode rChild = sortList(right.next);
        if (lChild==null) {
            node=head;
        } else {
            node = lChild;
            while (node.next!=null) node = node.next;
        }
        node.next = head;
        head.next = rChild;
        return lChild==null ? head : lChild;
    }
}
