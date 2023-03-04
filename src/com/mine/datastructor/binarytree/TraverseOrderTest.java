package com.mine.datastructor.binarytree;

import java.util.ArrayList;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-03-04 10:27
 * @description 二叉树的前中后序遍历 - 递归法
 * 144. 二叉树的前序遍历
 * 链接：https://leetcode.cn/problems/binary-tree-preorder-traversal/
 * 94. 二叉树的中序遍历
 * 链接：https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * 145. 二叉树的后序遍历
 * 链接：https://leetcode.cn/problems/binary-tree-postorder-traversal/
 */
public class TraverseOrderTest {

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

//        List<Integer> nodes = preorderTraversal(node1);
//        List<Integer> nodes = inorderTraversal(node1);
        List<Integer> nodes = postorderTraversal(node1);
        System.out.println(nodes);

    }

    public static List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.add(root.val);
        result.addAll(preorderTraversal(root.left));
        result.addAll(preorderTraversal(root.right));
        return result;
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(inorderTraversal(root.left));
        result.add(root.val);
        result.addAll(inorderTraversal(root.right));
        return result;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) {
            return result;
        }
        result.addAll(postorderTraversal(root.left));
        result.addAll(postorderTraversal(root.right));
        result.add(root.val);
        return result;
    }
}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;
    TreeNode() {}
    TreeNode(int val) { this.val = val; }
    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}