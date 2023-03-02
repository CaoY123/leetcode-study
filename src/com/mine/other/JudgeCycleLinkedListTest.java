package com.mine.other;

import java.util.HashSet;
import java.util.Set;

/**
 * @author CaoY
 * @date 2023-03-02 23:04
 * @description 141. 环形链表
 * 链接：https://leetcode.cn/problems/linked-list-cycle/
 */
public class JudgeCycleLinkedListTest {

    public static void main(String[] args) {
        // 注意：给定的单链表的head结点是直接指向第一个结点，而不是其next指向第一个结点
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        ListNode head = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
//        node5.next = node3;
        node5.next = null;

        System.out.println("是否为环型链表：" + hasCycle2(head));
    }

    // 解决思路1：设置一个set，存储遍历过的 ListNode 的引用
    public static boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        Set<ListNode> set = new HashSet<>();
        ListNode node = head;
        while (node != null) {
            if (set.contains(node)) {
                return true;
            }
            set.add(node);
            node = node.next;
        }
        return false;
    }

    // 解决思路2：快慢指针法 —— 是一种思想
    // 类似地，也可以解决单链表的如获取倒数第k个元素，获取中间位置的元素，
    // 判断链表是否存在环，判断环的长度等和长度与位置有关的问题。
    public static boolean hasCycle2(ListNode head) {
        if (head == null || head.next == null) {
            return false;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (fast != null) {
            fast = fast.next;
            if (fast != null) {
                fast = fast.next;
            } else {
                break;
            }
            slow = slow.next;
            if (slow == fast) {
                return true;
            }
        }
        return false;
    }
}

class ListNode {
    int val;
    ListNode next;
    ListNode(int x) {
        val = x;
        next = null;
    }
}