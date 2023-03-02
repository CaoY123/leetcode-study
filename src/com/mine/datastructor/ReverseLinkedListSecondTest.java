package com.mine.datastructor;

/**
 * @author CaoYang
 * @create 2023-03-01-10:20 PM
 * @description 92. 反转链表 II
 * 链接：https://leetcode.cn/problems/reverse-linked-list-ii/
 * 思路整理：
 */
public class ReverseLinkedListSecondTest {
    // 这里只展示理想的一般情况，其他情况也没问题，可以自行验证
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
        node5.next = null;

        int left = 2;
        int right = 4;

        System.out.print("逆置前：");
        showLinkedList(head);
        // 这里发生了引用的拷贝，而不是对原引用的值进行变更，故需要将处理后的head地址重新
        // 赋给外面的head，才能保证运行正确。
        head = reverseBetween(head, left, right);
        System.out.print("逆置后：");
        showLinkedList(head);
    }

    // 迭代法
    public static ListNode reverseBetween(ListNode head, int left, int right) {
        // 对下列特殊情况的链表不作局部逆置处理
        if (head == null || head.next == null || right - left <= 0) {
            return head;
        }

        // 找到左边界
        int index = 1;
        ListNode preLeft = head;
        if (left == 1) {
            preLeft = new ListNode();
            preLeft.next = head;
        } else {
            while (index < left - 1) {
                preLeft = preLeft.next;
                index++;
            }
            index++;
        }

        ListNode leftNode = preLeft.next;
        while (index < right) {
            ListNode node = leftNode.next;
            leftNode.next = node.next;
            node.next = preLeft.next;
            preLeft.next = node;
            node = null;
            index++;
        }

        if (left == 1) {
            head = preLeft.next;
            preLeft.next = null;
        }
        preLeft = null;

        return head;
    }

    // 递归法
    // 递归方法1：逆置以head为头结点的链表的前n个元素（n >= 1）
    // 记录后继结点
    private static ListNode successor = null;

    public static ListNode reverseFirstN(ListNode head, int n) {
        if (n == 1) {
            successor = head.next;
            return head;
        }
        ListNode newHead = reverseFirstN(head.next, n - 1);
        head.next.next = head;
        head.next = successor;
        return newHead;
    }

    // 递归方法2：
    // 下面的函数是局部翻转以head为头结点的链表，范围为索引left - right，其中索引是从1开始的
    public static ListNode reverseBetween2(ListNode head, int left, int right) {
        if(left == 1) {
            return reverseFirstN(head, right);
        }
        head.next = reverseBetween2(head.next, left - 1, right - 1);
        return head;
    }

    // 给定头结点打印单链表
    public static void showLinkedList(ListNode head) {
        if (head == null) {
            System.out.println("该链表为空，没有元素！");
        }
        System.out.print(head.val);
        ListNode node = head.next;
        while (node != null) {
            System.out.print(" -> " + node.val);
            node = node.next;
        }
        System.out.println();
    }
}

// 单链表结点的数据结构展示
//class ListNode {
//    int val;
//    ListNode next;
//    ListNode() {}
//    ListNode(int val) { this.val = val; }
//    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
//}


