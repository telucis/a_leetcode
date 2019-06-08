package complete.link;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by telucis on 2019/5/13.
 *
 * 复制带随机指针的链表
 *
     给定一个链表，每个节点包含一个额外增加的随机指针，该指针可以指向链表中的任何节点或空节点。

     要求返回这个链表的深拷贝。



     示例：



     输入：
     {"$id":"1","next":{"$id":"2","next":null,"random":{"$ref":"2"},"val":2},"random":{"$ref":"2"},"val":1}

     解释：
     节点 1 的值是 1，它的下一个指针和随机指针都指向节点 2 。
     节点 2 的值是 2，它的下一个指针指向 null，随机指针指向它自己。


     提示：

     你必须返回给定头的拷贝作为对克隆列表的引用。

 */
public class copy_list_with_random_pointer_138 {
    public Node copyRandomList(Node head) {
        if (head==null) return null;
        HashMap<Node, Node> map = new HashMap<>();
        Queue<Node> q = new LinkedList<>();
        Node root = new Node(head.val, null, null);
        map.put(head, root);
        q.offer(head);
        while (!q.isEmpty()) {
            Node cur = q.poll();
            if (cur.next!=null && !map.containsKey(cur.next)) {
                map.put(cur.next, new Node(cur.next.val, null, null));
                q.offer(cur.next);
            }
            if (cur.random!=null && !map.containsKey(cur.random)) {
                map.put(cur.random, new Node(cur.random.val, null, null));
                q.offer(cur.random);
            }
            Node node = map.get(cur);
            node.next = cur.next==null ? null : map.get(cur.next);
            node.random = cur.random==null ? null : map.get(cur.random);
        }
        return root;
    }

    class Node {
        public int val;
        public Node next;
        public Node random;

        public Node() {}

        public Node(int _val,Node _next,Node _random) {
            val = _val;
            next = _next;
            random = _random;
        }
    };
}
