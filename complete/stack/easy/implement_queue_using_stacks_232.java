package complete.stack.easy;

import java.util.Stack;

/**
 * @author karl.wy
 * @date 2019/04/18
 *
 *  用栈实现队列
 *
    使用栈实现队列的下列操作：

    push(x) -- 将一个元素放入队列的尾部。
    pop() -- 从队列首部移除元素。
    peek() -- 返回队列首部的元素。
    empty() -- 返回队列是否为空。
    示例:

    MyQueue heap = new MyQueue();

    heap.push(1);
    heap.push(2);
    heap.peek();  // 返回 1
    heap.pop();   // 返回 1
    heap.empty(); // 返回 false
    说明:

    你只能使用标准的栈操作 -- 也就是只有 push to top, peek/pop from top, size, 和 is empty 操作是合法的。
    你所使用的语言也许不支持栈。你可以使用 list 或者 deque（双端队列）来模拟一个栈，只要是标准的栈操作即可。
    假设所有操作都是有效的 （例如，一个空的队列不会调用 pop 或者 peek 操作）。

 */
public class implement_queue_using_stacks_232 {

    class MyQueue {
        private Stack<Integer> stack1;
        private Stack<Integer> stack2;
        /** Initialize your data structure here. */
        public MyQueue() {
            this.stack1 = new Stack<>();
            this.stack2 = new Stack<>();
        }

        /** Push element x to the back of heap. */
        public void push(int x) {
            this.stack1.push(x);
        }

        /** Removes the element from in front of heap and returns that element. */
        public int pop() {
            if (!stack2.isEmpty()) {
                return stack2.pop();
            }
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    int value = stack1.pop();
                    stack2.push(value);
                }
                return stack2.pop();
            }
            return -1;
        }

        /** Get the front element. */
        public int peek() {
            if (!stack2.isEmpty()) {
                return stack2.peek();
            }
            if (!stack1.isEmpty()) {
                while (!stack1.isEmpty()) {
                    int value = stack1.pop();
                    stack2.push(value);
                }
                return stack2.peek();
            }
            return -1;
        }

        /** Returns whether the heap is empty. */
        public boolean empty() {
            if (stack1.isEmpty() && stack2.isEmpty()) {
                return true;
            } else {
                return false;
            }
        }
    }
}
