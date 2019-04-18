package com.spring.cloud.moudle.study.demo.sortDemo;

/**
 * 选择排序
 *
 * @author wangmj
 * @since 2019/3/17
 */
public class SelectionSortDemo {


    /**
     * 选择排序
     *
     * @param arr 数组
     */
    public static void selectionSort(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[minIndex] > arr[j]) {
                    minIndex = j;
                }
            }
            SortHelper.swapArr(arr, i, minIndex);
        }
    }
}
