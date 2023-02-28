package com.mine.datastructor;

import java.sql.SQLOutput;
import java.util.LinkedList;
import java.util.Queue;

/**
 * @author CaoY
 * @date 2023-02-28 23:27
 * @description 225. 用队列实现栈
 * 链接：https://leetcode.cn/problems/implement-stack-using-queues/
 * 思路，在插入的时候利用两个栈保持队列的特性，其他操作就会方便很多。
 * 改进思路：一个队列也可以实现，即在插入的时候先插入新元素，然后再将其他元素出队再马上入队，
 * 可以将一个元素从队头移到队尾
 */
public class TwoQueueToStackTest {

    public static void main(String[] args) {

//        MyStack myStack = new MyStack();
        MyStack2 myStack = new MyStack2();
        myStack.push(1);
        myStack.push(2);
        myStack.push(3);
        myStack.push(4);
        myStack.push(5);
        myStack.push(6);

        System.out.println("弹出遍历前是否为空：" + myStack.empty());
        System.out.println("首元素：" + myStack.top());
        while (!myStack.empty()) {
            System.out.println("【" + myStack.pop() + "】");
        }
        System.out.println("弹出遍历后是否为空：" + myStack.empty());

    }
}

class MyStack {

    Queue<Integer> queue1;
    Queue<Integer> queue2;

    public MyStack() {
        queue1 = new LinkedList<>();
        queue2 = new LinkedList<>();
    }

    public void push(int x) {
        if (queue1.isEmpty()) {
            queue1.add(x);
            while (!queue2.isEmpty()) {
                queue1.add(queue2.poll());
            }
        } else {
            queue2.add(x);
            while (!queue1.isEmpty()) {
                queue2.add(queue1.poll());
            }
        }

    }

    public int pop() {
        if (this.empty()) {
            throw new RuntimeException("该栈为空！");
        }
        if (queue1.isEmpty()) {
            return queue2.poll();
        } else {
            return queue1.poll();
        }
    }

    public int top() {
        if (this.empty()) {
            throw new RuntimeException("该栈为空！");
        }
        if (queue1.isEmpty()) {
            return queue2.peek();
        } else {
            return queue1.peek();
        }
    }

    public boolean empty() {
        return queue1.isEmpty() && queue2.isEmpty();
    }
}

class MyStack2 {

    Queue<Integer> queue;

    public MyStack2() {
        queue = new LinkedList<>();
    }

    public void push(int x) {
        queue.add(x);
        if (queue.size() != 1) {
            int i = 0;
            while (i < queue.size() - 1) {
                queue.add(queue.poll());
                i++;
            }
        }
    }

    public int pop() {
        if (this.empty()) {
            throw new RuntimeException("该栈为空！");
        }
        return queue.poll();
    }

    public int top() {
        if (this.empty()) {
            throw new RuntimeException("该栈为空！");
        }
        return queue.peek();
    }

    public boolean empty() {
        return queue.isEmpty();
    }
}