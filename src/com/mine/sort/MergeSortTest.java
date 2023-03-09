package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 13:22
 * @description 归并排序 升序 并输出排序过程
 * 稳定排序
 */
public class MergeSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
        System.out.println("排序前的数组：" + Arrays.toString(array));

        mergeSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    private static int count = 0;

    public static int[] mergeSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        mergeFunc(array, 0, array.length - 1);
        count = 0;
        return array;
    }

    public static void mergeFunc(int[] array, int begin, int end) {
        if (begin >= end) {
            return;
        }
        int mid = (end - begin) / 2 + begin;
        mergeFunc(array, begin, mid);
        mergeFunc(array, mid + 1, end);

        // 局部使用直接插入排序
        for (int i = begin + 1; i <= end; i++) {
            int tmp = array[i];
            int index = begin;
            for (int j = i - 1; j >= begin; j--) {
                if (tmp > array[j]) {
                    index = j + 1;
                    break;
                }
            }
            for (int j = i - 1; j >= index; j--) {
                array[j + 1] = array[j];
            }
            array[index] = tmp;
        }

        System.out.println("第" + ++count + "轮排序：" + Arrays.toString(array));
    }
}
