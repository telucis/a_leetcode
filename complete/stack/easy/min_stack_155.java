package complete.stack.easy;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 * 最小栈
 *
    设计一个支持 push，pop，top 操作，并能在常数时间内检索到最小元素的栈。

    push(x) -- 将元素 x 推入栈中。
    pop() -- 删除栈顶的元素。
    top() -- 获取栈顶元素。
    getMin() -- 检索栈中的最小元素。
    示例:

    MinStack minStack = new MinStack();
    minStack.push(-2);
    minStack.push(0);
    minStack.push(-3);
    minStack.getMin();   --> 返回 -3.
    minStack.pop();
    minStack.top();      --> 返回 0.
    minStack.getMin();   --> 返回 -2.

 */
public class min_stack_155 {
    class MinStack {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        /** initialize your data structure here. */
        public MinStack() {
            stack1 = new Stack<>();
            stack2 = new Stack<>();
        }

        public void push(int x) {
            stack1.push(x);
            if (stack2.isEmpty() || stack2.peek() > x) {
                stack2.push(x);
            } else {
                stack2.push(stack2.peek());
            }
        }

        public void pop() {
            if (stack1.isEmpty()) {
                return;
            }
            stack1.pop();
            stack2.pop();
        }

        public int top() {
            if (stack1.isEmpty()) {
                return 0;
            }
            return stack1.peek();
        }

        public int getMin() {
            if (stack2.isEmpty()) {
                return 0;
            }
            return stack2.peek();
        }
    }

}
