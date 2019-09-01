/**
 * 382. 链表随机节点
	 给定一个单链表，随机选择链表的一个节点，并返回相应的节点值。保证每个节点被选的概率一样。

	进阶:
	如果链表十分大且长度未知，如何解决这个问题？你能否使用常数级空间复杂度实现？

	示例:

	// 初始化一个单链表 [1,2,3].
	ListNode head = new ListNode(1);
	head.next = new ListNode(2);
	head.next.next = new ListNode(3);
	Solution solution = new Solution(head);

	// getRandom()方法应随机返回1,2,3中的一个，保证每个元素被返回的概率相等。
	solution.getRandom();
 */

class Solution {

	private ListNode head;
    public Solution(ListNode head) {
    	this.head = head;
    }
    
    /** Returns a random node's value. */
    public int getRandom() {
        Random r = new Random();
        ListNode n = head, res = n;
        int num = 1;
        while (n!=null) {
        	if (r.nextInt() % num == 0) {
        		res = n;
        	}
        	num++;
        	n = n.next;
        }
        return res.val;
    }
}