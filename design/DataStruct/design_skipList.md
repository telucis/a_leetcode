```java
    
import java.util.Random;
/**
 * 不固定层级的跳跃表
 */
public class SkipList<T extends Comparable<? super T>> {
    private SkipListNode<T> head, tail;
    private int nodes;
    private int listLevel;
    private Random random;
    private static final double PROBABILITY=0.5;
    public SkipList() {
        random = new Random();
        clear();
    }
    public void clear() {
        head = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
        tail = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
        horizontalLink(head, tail);
        listLevel = 0;
        nodes = 0;
    }
    public boolean isEmpty() return nodes==0;
    public int size() return nodes;

    private SkipListNode<T> findNode(int key) {
        SkipListNode<T> p = head;
        while(true) {
            while(p.right.key != SkipListNode.TAIL_KEY && p.right.key<=key) {
                p = p.right;
            }
            if (p.down != null) p=p.down;
            else break;
        }
        return p;
    }

    public SkipListNode<T> search(int key) {
        SkipListNode<T> p = findNode(key);
        if (key == p.getKey()) return p;
        else return null;
    }

    public void put(int k, T v) {
        SkipListNode<T> p = findNode(k);
        if (k==p.getKey()) {
            p.value = v;
            return;
        }
        SkipListNode<T> q = new SkipListNode<T>(k, v);
        backLink(p, q);
        int currentLevel = 0;   //当前层级

        while(random.nextDouble() < PROBABILITY) {
            if (currentLevel>=listLevel) {
                listLevel++;
                SkipListNode<T> p1 = new SkipListNode<T>(SkipListNode.HEAD_KEY, null);
                SkipListNode<T> p2 = new SkipListNode<T>(SkipListNode.TAIL_KEY, null);
                horizontalLink(p1, p2);
                verticalLink(p1, head);
                verticalLink(p2, tail);
                head=p1;
                tail=p2;
            }
            while (p.up==null) p = p.left;
            p = p.up;

            SkipListNode<T> e = new SkipListNode<T>(k, null);
            backLink(p, e);
            verticalLink(e, q);
            q = e;
            currentLevel++;
        }
        nodes++;
    }

    private void backLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node2.left = node1;
        node2.right = node1.right;
        node1.right.left = node2;
        node1.right = node2;
    }
    private void horizontalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.right = node2;
        node2.left = node1;
    }
    private void verticalLink(SkipListNode<T> node1, SkipListNode<T> node2) {
        node1.down = node2;
        node2.up = node1;
    }
}

public class SkipListNode<T> {
    public int key;
    public T value;
    public SkipListNode<T> up, down, left, right;

    public static final int HEAD_KEY = Integer.MIN_VALUE;
    public static final int TAIL_KEY = Integer.MAX_VALUE;
    public SkipListNode(int k, T v) {
        key = k;
        value = v;
    }
    public int getKey() return key;
    public void setKey(int key) this.key = key;
    public T getValue() return value;
    public void setValue(T value) this.value = value;
    public boolean equal(Object o) {
        if (this==o) return true;
        if (o==null) return false;
        if (!(o instanceof SkipListNode<?>)) return false;
        SkipListNode<T> ent;
        try {
            ent = (SkipListNode<T>) o;
        } catch (ClassCastException ex) {
            return false;
        }
        return (ent.getKey() == key) && (ent.getValue() == value);
    }
    @Override
    public String toString() {
        return "key-value:"+key+"-"+value;
    }
}
```