package com.mine.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-03-05 22:46
 * @description 桶排序 升序 并输出排序过程 稳定排序
 * 参考链接：https://fhfirehuo.github.io/Attacking-Java-Rookie/Chapter03/BucketSort.html
 * 排序过程不方便描述，故不作输出，总体上是用递归桶归纳排序，并不涉及比较的过程，但是相当耗内存，可以debug理解这个过程
 */
public class BucketSortTest {

    public static void main(String[] args) {
//        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
        int[] array = {2, 3, 8, 7, 1, 2, 2, 2, 7, 3, 9, 8, 2, 1, 4, 2, 4, 6, 9, 2};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        array = bucketSort(array, 3);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] bucketSort(int[] array, int bucketSize) {
        if (array == null || array.length <= 1) {
            return array;
        }

        List<Integer> list = new ArrayList<>(array.length);
        // 将 array 转换为 ArrayList
        for (int i = 0; i < array.length; i++) {
            list.add(array[i]);
        }
        return bucketFunc(list, bucketSize);
    }

    public static int[] bucketFunc(List<Integer> array, int bucketSize) {
        int[] result = new int[array.size()];
        if (array.size() < 2 || bucketSize < 1) {
            for (int i = 0; i < array.size(); i++) {
                result[i] = array.get(i);
            }
            return result;
        }
        // 找出 array 中的最大值和最小值
        int minNum = array.get(0);
        int maxNum = array.get(0);
        for (int i = 1; i < array.size(); i++) {
            if (minNum > array.get(i)) {
                minNum = array.get(i);
            }
            if (maxNum < array.get(i)) {
                maxNum = array.get(i);
            }
        }

        int delta = maxNum - minNum;
        List<List<Integer> > lists = new ArrayList<>(delta / bucketSize + 1);
        // 给 lists 分配实际的空间
        for (int i = 0; i < delta / bucketSize + 1; i++) {
            lists.add(new ArrayList<>());
        }

        // 遍历 array，将相应的数据放入桶中
        for (int i = 0; i < array.size(); i++) {
            lists.get((array.get(i) - minNum) / bucketSize).add(array.get(i));
        }

        // 缩小 bucketSize，进行递归桶排序
        int index = 0;
        for (List<Integer> aList : lists) {
            int[] array1 = bucketFunc(aList, bucketSize / 2);
            // 处理排序后的局部数组
            for (int data : array1) {
                result[index++] = data;
            }
        }
        return result;
    }
}
