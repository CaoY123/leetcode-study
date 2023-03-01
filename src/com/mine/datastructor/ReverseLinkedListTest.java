package com.mine.datastructor;

/**
 * @author CaoYang
 * @create 2023-03-01-10:20 PM
 * @description 206. 反转链表
 * 链接：https://leetcode.cn/problems/reverse-linked-list/
 * 思路整理：始终有一个指针指向第一个结点，这个指针维持不动，依次将这个结点的后面的结点插到
 * 第一个位置，即可实现逆置
 */
public class ReverseLinkedListTest {
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

        System.out.print("逆置前：");
        showLinkedList(head);
        // 这里发生了引用的拷贝，而不是对原引用的值进行变更，故需要将处理后的head地址重新
        // 赋给外面的head，才能保证运行正确。
        head = reverseList(head);
        System.out.print("逆置后：");
        showLinkedList(head);
    }

    public static ListNode reverseList(ListNode head) {
        // 空链表和只有一个结点的链表不需要逆置
        if (head == null || head.next == null) {
            return head;
        }
        ListNode node = head;
        while (node.next != null) {
            ListNode tmpNode = node.next;
            node.next = tmpNode.next;
            tmpNode.next = head;
            head = tmpNode;
            tmpNode = null;
        }

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

class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}


