package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 10:30
 * @description 选择排序 升序 并输出排序过程
 * 策略：遍历过程中与当前最小的比较，每轮选择都能选出一个最小的放到剩余未排序的最开始的位置
 * 不稳定排序
 */
public class SelectSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
        System.out.println("排序前的数组：" + Arrays.toString(array));

        selectSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] selectSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length - 1; i++) {
            int minNum = array[i];
            int minIndex = i;
            for (int j = i + 1; j < array.length; j++) {
                if (minNum > array[j]) {
                    minNum = array[j];
                    minIndex = j;
                }
            }
            // 交换值
            array[minIndex] = array[i];
            array[i] = minNum;
            System.out.println("第" + (i + 1) + "轮选择排序：" + Arrays.toString(array));
        }
        return array;
    }
}
