package dataStruct;

import java.util.List;

/**
 * @author karl.wy
 * @date 2019/04/18
 */
public class Node {
    public int val;
    public List<Node> children;
    public Node(){}
    public Node(int _val, List<Node> _children) {
        val = _val;
        children = _children;
    }
}
