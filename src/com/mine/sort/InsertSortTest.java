package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 10:44
 * @description 直接插入排序 升序 并输出排序过程
 * 稳定排序
 */
public class InsertSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
        System.out.println("排序前的数组：" + Arrays.toString(array));

        insertSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }
    
    public static int[] insertSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        for (int i = 1; i < array.length; i++) {
            int tmp = array[i];
            int index = 0;
            for (int j = i - 1; j >= 0; j--) {
                if (tmp > array[j]) {
                    index = j + 1;
                    break;
                }
            }
            for (int j = i - 1; j >= index; j--) {
                array[j + 1] = array[j];
            }
            array[index] = tmp;
            System.out.println("第" + i + "轮插入排序：" + Arrays.toString(array));
        }
        return array;
    }
}
