package com.mine.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author CaoY
 * @date 2023-03-06 23:04
 * @description 基数排序 升序 并输出排序过程 稳定排序
 * 算法描述：
 * 基数排序是一种非比较排序算法，它根据数字的位数，将待排序的数字按照从低位到高位或从高位到低位的顺序依次进行排序。
 * 基数排序的实现过程通常包括以下步骤：
 *
 * 1. 取得数组中的最大数，并取得位数；
 * 2. 以个位数为关键字，将所有数字分配到对应的桶中；
 * 3. 将所有桶中的数字按照顺序依次取出，形成新的数组；
 * 4. 以十位数为关键字，将所有数字分配到对应的桶中；
 * 进行如上过程直到到达最大位数
 */
public class RadixSortTest {

    public static void main(String[] args) {
//        int[] array = {3, 44, 38, 5, 47, 15, 36, 26, 27, 2, 46, 4, 19, 50, 48};
        int[] array = {3, 44, 38, 123, 5, 47, 15, 36, 200, 99, 69, 26, 27, 2121, 2, 46, 4, 19, 50, 48};
//        int[] array = {2, 1};
//        int[] array = {2};
//        int[] array = {};
//        int[] array = null;
//        int[] array = {2, 3, 8, 7, 1, 2, 2, 2, 7, 3, 9, 8, 2, 1, 4, 2, 4, 6, 9, 2};
        System.out.println("排序前的数组：" + Arrays.toString(array));

        array = radixSort(array);

        System.out.println("排序后的结果：");
        System.out.println(Arrays.toString(array));
    }

    public static int[] radixSort(int[] array) {
        if (array == null || array.length <= 1) {
            return array;
        }
        // 找到数组中的最大值
        int maxNum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maxNum < array[i]) {
                maxNum = array[i];
            }
        }
        // 分析 maxNum的位数
        int tmpNum = maxNum;
        // maxNum的位数，如 100 为3位数
        int digits = 0;
        do {
            digits++;
            tmpNum /= 10;
        } while (tmpNum > 0);

        // 按照 个（第一位）、十（第二位）、百（第三位）...归纳
        for (int i = 0; i < digits; i++) {
            List<List<Integer> > list = new ArrayList<>(10);
            for (int j = 0; j < 10; j++) {
                list.add(new ArrayList<>());
            }

            for (int j = 0; j < array.length; j++) {
                // 求出 第 i + 1 位上的数字值
                int idx = array[j] % (int)Math.pow(10, i + 1) / (int) Math.pow(10, i);
                list.get(idx).add(array[j]);
            }

            // 重新取出放入 array 数组
            int index = 0;
            for (List<Integer> aList : list) {
                for (Integer data : aList) {
                    array[index++] = data;
                }
            }
            System.out.println("按第" + (i + 1) + "位数字归纳排序：" + Arrays.toString(array));
        }

        return array;
    }
}
