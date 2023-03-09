package com.mine.datastructor.binarytree;

import sun.reflect.generics.tree.Tree;

import java.util.*;

/**
 * @author CaoY
 * @date 2023-03-04 16:27
 * @description 987. 二叉树的垂序遍历
 * 链接：https://leetcode.cn/problems/vertical-order-traversal-of-a-binary-tree/description/
 * 思想：先处理数据，根据横纵坐标找出横坐标相同的结点，将它们整理在一起，之所以要放在一个TreeMap中，是因为TreeMap有按key从小到大排序的特性，
 * 然后在verticalTraversal()中进行排序整理，即可按要求得到相应的结果序列
 * 总体思路：归纳（遍历） + 整理（排序）
 * 注：此处加 static 只是为了方便在main中测试，提交时务必将其去掉，不然运行多个测试用例时会出错，因为 map 是 static 的，会导致其存储
 * 上一个用例中运行的值。
 */
public class VerticalTraverseTest {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        TreeNode node1 = new TreeNode(2);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(6);
        TreeNode node6 = new TreeNode(7);

        root.left = node1;
        root.right = node2;
        node1.left = node3;
        node1.right = node4;
        node2.left = node5;
        node2.right = node6;

        List<List<Integer>> res = verticalTraversal(root);
        for (List<Integer> list : res) {
            System.out.println(list);
        }

    }
    // key是横坐标，value是这一列的点的坐标（横、纵坐标）
    private static Map<Integer, List<int[]> > map = new TreeMap<>();

    public static List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer> > res = new ArrayList<>();
        dfs(root, 0, 0);
        for (Integer key : map.keySet()) {
            List<int[]> list = map.get(key);
            list.sort((a, b) -> a[0] == b[0] ? a[1] - b[1] : a[0] - b[0]);
            List<Integer> li = new ArrayList<>();
            for (int[] val : list) {
                li.add(val[1]);
            }
            res.add(li);
        }
        return res;
    }

    public static void dfs(TreeNode root, int x, int y) {
        if (root == null) {
            return;
        }
        // 取出List并插入新的key
        List<int[]> list = map.getOrDefault(x, new ArrayList<>());
        list.add(new int[]{y, root.val});
        map.put(x, list);
        dfs(root.left, x - 1, y + 1);
        dfs(root.right, x + 1, y + 1);
    }
}
