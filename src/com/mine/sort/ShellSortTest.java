package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 12:22
 * @description 希尔排序 升序 —— 对于直接插入排序算法的改进
 * 不稳定排序
 * 策略：按照一定间隔来对一组数据进行分组，并在分组内使用直接排序，
 * 再逐次将间隔降为1对整个数组进行排序，每轮排序步长缩短为原来的一半
 * 技巧：因为下标难以控制，细节多，所以我们可以对着直接插入排序的代码写
 */
public class ShellSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
//        int[] array = {7, 6, 9, 3, 1, 5, 2, 4};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        shellSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] shellSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // grep —— 同组相邻两元素的间隔，也是该轮排序的组数
        int grep = array.length / 2;
        int i = 1;
        while (grep >= 1) {
            directInsertSort(array, grep);
            System.out.println("第" + i + "轮轮希尔排序（步长为 " + grep + "）：" + Arrays.toString(array));
            grep /= 2;
            i++;
        }
        return array;
    }

    // 按间隔数 grep 来对 array 排序
    public static int[] directInsertSort(int[] array, int grep) {
        for (int i = 0; i < grep; i++) {
            for (int j = i + grep; j < array.length; j+=grep) {
                int tmp = array[j];
                int index = i;
                for (int k = j - grep; k >= i; k-=grep) {
                    if (tmp > array[k]) {
                        index = k + grep;
                        break;
                    }
                }
                for (int k = j - grep; k >= index; k-=grep) {
                    array[k + grep] = array[k];
                }
                array[index] = tmp;
            }
        }
        return array;
    }
}
