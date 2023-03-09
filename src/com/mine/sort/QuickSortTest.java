package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 14:34
 * @description 快速排序 升序 并输出排序过程
 * 左右震荡排序，不稳定，每次确定 选定为基准元素的位置
 */
public class QuickSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
//        int[] array = {30, 40, 60, 10, 20, 50};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        quickSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] quickSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        quickFunc(array, 0, array.length - 1, 1);
        return array;
    }

    public static void quickFunc(int[] array, int left, int right, int count) {
        if (left >= right) {
            return;
        }
        int baseIndex = left;
        int base = array[baseIndex];
        int endIndex = right;
        // flag 为 true，右指针移动，寻找比base小的；反之，左指针移动，寻找比 base 大的
        boolean flag = true;
        while (left < right) {
            if (flag) {
                if (array[right] < base) {
                    array[left] = array[right];
                    flag = false;
                    left++;
                } else {
                    right--;
                }
            } else {
                if (array[left] > base) {
                    array[right] = array[left];
                    flag = true;
                    right--;
                } else {
                    left++;
                }
            }
        }
        array[left] = base;
        System.out.println("第" + count + "轮快速排序（参考基准："+ base + "）：" + Arrays.toString(array));
        quickFunc(array, baseIndex, left - 1, ++count);
        quickFunc(array, left + 1, endIndex, ++count);
    }

}
