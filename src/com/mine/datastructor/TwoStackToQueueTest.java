package com.mine.datastructor;

import java.util.Stack;

/**
 * @author CaoY
 * @date 2023-02-28 21:52
 * @description 232. 用栈实现队列
 * 连接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof/
 */
public class TwoStackToQueueTest {

    public static void main(String[] args) {
        MyQueue myQueue = new MyQueue();
        myQueue.push(1);
        myQueue.push(2);
        myQueue.push(3);
        myQueue.push(4);
        myQueue.push(5);
        myQueue.push(6);

        System.out.println("弹出元素遍历前是否为空：" + myQueue.empty());

        System.out.println("取第一个元素：" + myQueue.peek());

        while (!myQueue.empty()) {
            System.out.println("【" + myQueue.pop() + "】");
        }

        System.out.println("弹元素遍历后是否为空：" + myQueue.empty());

    }
}

// Integer数据版本
class MyQueue {
    // 两个用于实现队列的栈
    private Stack<Integer> stack1;
    private Stack<Integer> stack2;

    public MyQueue() {
        stack1 = new Stack<>();
        stack2 = new Stack<>();
    }

    public void push(int x) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack1.push(x);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public int pop() {
        if (this.empty()) {
            throw new RuntimeException("此队列为空，无法弹出元素！");
        }
        return stack1.pop();
    }

    public int peek() {
        if (this.empty()) {
            throw new RuntimeException("此队列为空，无法取出顶部元素！");
        }
        return stack1.peek();
    }

    public boolean empty() {
        return stack1.isEmpty();
    }
}
