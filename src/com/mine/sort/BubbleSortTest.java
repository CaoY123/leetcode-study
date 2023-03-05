package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 10:08
 * @description 冒泡排序 升序 并输出排序过程
 */
public class BubbleSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        bubbleSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));

    }

    public static int[] bubbleSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 0; i < array.length; i++) {
            boolean flag = false;
            for (int j = 1; j < array.length - i; j++) {
                if (array[j] < array[j - 1]) {
                    flag = true;
                    int tmp = array[j];
                    array[j] = array[j - 1];
                    array[j - 1] = tmp;
                }
            }
            if (!flag) {
                break;
            }
            System.out.println("第" + (i + 1) + "趟冒泡：" + Arrays.toString(array));
        }
        return array;
    }
}
