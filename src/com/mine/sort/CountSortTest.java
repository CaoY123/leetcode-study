package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 22:06
 * @description 计数排序 升序 并输出排序过程
 * 参考链接：https://blog.csdn.net/thinkwon/article/details/101544159
 */
public class CountSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
//        int[] array = {2, 3, 8, 7, 1, 2, 2, 2, 7, 3, 9, 8, 2, 1, 4, 2, 4, 6, 9, 2};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        countSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] countSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // 第一轮遍历，找出 array 中的最大值和最小值
        int minNum = Integer.MAX_VALUE;
        int maxNum = Integer.MIN_VALUE;
        for (int i = 0; i < array.length; i++) {
            if (maxNum < array[i]) {
                maxNum = array[i];
            }
            if (minNum > array[i]) {
                minNum = array[i];
            }
        }
        int [] newArray = new int[maxNum - minNum + 1];
        // 归纳每个元素的个数，这里本质上是把（每个元素的值 - delta）作为数组下标
        for (int i = 0; i < array.length; i++) {
            newArray[array[i] - minNum]++;
        }
        System.out.println("*********************************************");
        System.out.println("归纳后每种元素的分布情况：");
        for (int i = 0; i < newArray.length; i++) {
            if (newArray[i] > 0) {
                System.out.println("【" + (i+ minNum) + "】" + "数目：" + newArray[i]);
            }
        }
        System.out.println("*********************************************");
        // 将 newArray 写入 array 中，这里可以选择写入一个新数组中，但我们就选择写入原数组中
        int index = 0;
        for (int i = 0; i < newArray.length; i++) {
            for (int j = 0; j < newArray[i]; j++) {
                array[index++] = i + minNum;
            }
        }
        return array;
    }
}
