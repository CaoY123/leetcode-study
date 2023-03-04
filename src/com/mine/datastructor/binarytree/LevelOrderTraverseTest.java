package com.mine.datastructor.binarytree;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-03-04 10:55
 * @description 102. 二叉树的层序遍历 - 用队列辅助实现
 * 链接：https://leetcode.cn/problems/binary-tree-level-order-traversal/
 * 解题思路：需要标记每层的结点数目，因此需要用一个专门的变量来存储并控制，那么如何统计每层的数目呢？
 * 可以在放入每个子结点的时候顺便统计下一层的结点的数目。
 */
public class LevelOrderTraverseTest {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        System.out.println("二叉树的层序遍历：");
        List<List<Integer>> res = levelOrder(node1);
        for (List<Integer> list : res) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer> > res = new ArrayList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        int count = 1;// 每层的结点数
        int index = 0;
        int tmpCount = 0;// 下一层的结点数
        List<Integer> list = new ArrayList<> ();
        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null) {
                queue.add(node.left);
                tmpCount++;
            }
            if (node.right != null) {
                queue.add(node.right);
                tmpCount++;
            }
            index ++;
            list.add(node.val);
            if (index == count) {
                count = tmpCount;
                index = 0;
                tmpCount = 0;
                res.add(list);
                list = new ArrayList<>();
            }
        }
        list = null;
        return res;
    }
}
