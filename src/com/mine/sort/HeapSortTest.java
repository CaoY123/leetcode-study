package com.mine.sort;

import java.util.Arrays;

/**
 * @author CaoY
 * @date 2023-03-05 15:23
 * @description 堆排序 升序 并输出排序过程
 * 不稳定排序
 */
public class HeapSortTest {

    public static void main(String[] args) {
        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
//        int[] array = {4, 6, 8, 5, 9};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        heapSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] heapSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        heapFunc(array, array.length, 1);
        return array;
    }

    public static void heapFunc(int[] array, int length, int count) {
        if (length <= 1) {
            return;
        }
        heapCons(array, 0, length);
        int tmp = array[length - 1];
        array[length - 1] = array[0];
        array[0] = tmp;
        System.out.println("第" + count++ + "轮堆排序结果：" + Arrays.toString(array));
        heapFunc(array, --length, count);
    }

    // 大顶堆构建函数，当调整位置后要以换位置的元素作为根结点向下调整
    public static void heapCons(int[] array, int begin, int length) {
        int lastLeave = length / 2 - 1;
        if (begin > lastLeave) {
            return;
        }
        for (int i = lastLeave; i >= begin; i--) {
            int index = i * 2 + 1;
            int val = array[index];
            if (i * 2 + 2 < length) {
                if (array[i * 2 + 2] > val) {
                    index = i * 2 + 2;
                    val = array[index];
                }
            }
            if (val > array[i]) {
                int tmp = array[i];
                array[i] = val;
                array[index] = tmp;
                // 调整之后有可能会造成下面的结构不符合大顶堆定义
                heapCons(array, index, length);
            }
        }
    }
}
