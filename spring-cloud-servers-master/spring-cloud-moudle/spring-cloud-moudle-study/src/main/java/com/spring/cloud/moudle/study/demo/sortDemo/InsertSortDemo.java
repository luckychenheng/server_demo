package com.spring.cloud.moudle.study.demo.sortDemo;

/**
 * @author wangmj
 * @since 2019/3/17
 */
public class InsertSortDemo {

    /**
     * 插入排序
     */
    public static void insertSort(int arr[]) {
        for (int i = 1; i < arr.length; i++) {
            for (int j = i; j > 0; j--) {
                if (arr[j] < arr[j - 1]) {
                    SortHelper.swapArr(arr, j, j - 1);
                }
            }
        }
    }

    /**
     * 升级版插入排序
     */
    public static void insertSortPro(int arr[]) {
        for (int i = 0; i < arr.length; i++) {
            int value = arr[i];
            int j;
            for (j = i; j > 0 && arr[j - 1] > value; j--) {
                arr[j] = arr[j - 1];
            }
            //说明没有过交换
            if (j != i) {
                arr[j] = value;
            }
        }
    }
}
