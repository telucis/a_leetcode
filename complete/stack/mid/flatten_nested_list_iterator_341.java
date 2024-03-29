package complete.stack.mid;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/05/15
 *
 * 扁平化嵌套列表迭代器
 *
    给定一个嵌套的整型列表。设计一个迭代器，使其能够遍历这个整型列表中的所有整数。

    列表中的项或者为一个整数，或者是另一个列表。

    示例 1:

    输入: [[1,1],2,[1,1]]
    输出: [1,1,2,1,1]
    解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,1,2,1,1]。
    示例 2:

    输入: [1,[4,[6]]]
    输出: [1,4,6]
    解释: 通过重复调用 next 直到 hasNext 返回false，next 返回的元素的顺序应该是: [1,4,6]。

 */
public class flatten_nested_list_iterator_341 {

    public class NestedIterator implements Iterator<Integer> {

        Stack<NestedInteger> stack;

        public NestedIterator(List<NestedInteger> nestedList) {
            stack = new Stack<NestedInteger>();
            for(int i=nestedList.size()-1; i>=0; i--){
                stack.push(nestedList.get(i));
            }
        }

        @Override
        public Integer next() {
            return stack.pop().getInteger();
        }

        @Override
        public boolean hasNext() {
            while (!stack.isEmpty()) {
                if(stack.peek().isInteger()) return true;
                List<NestedInteger> cur = stack.pop().getList();
                for(int i=cur.size()-1; i>=0; i--){
                    stack.push(cur.get(i));
                }
            }
            return false;
        }
    }

      // This is the interface that allows for creating nested lists
      // You should not implement it, or speculate about its implementation
    public interface NestedInteger {

          // @return true if this NestedInteger holds a single integer, rather than a nested list.
          public boolean isInteger();

          // @return the single integer that this NestedInteger holds, if it holds a single integer
          // Return null if this NestedInteger holds a nested list
          public Integer getInteger();

          // @return the nested list that this NestedInteger holds, if it holds a nested list
          // Return null if this NestedInteger holds a single integer
          public List<NestedInteger> getList();
    }
}
