package com.spring.cloud.moudle.study.demo.sortDemo;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Optional;

/**
 * 归并排序
 *
 * @author wangmj
 * @since 2019/3/19
 */
public class MergeSortDemo {

    public static void main(String[] args) {
        int[] arr = SortHelper.generateArr(10, 2, 20);
        System.out.println("before=" + Arrays.toString(arr));
//        mergeSort(ints, 0, 9);
        mergeSortBu(arr);
        System.out.println("result=" + Arrays.toString(arr));
    }

    /**
     * 递归排序
     */
    static void mergeSort(int arr[], int left, int right) {
        if (left < right) {
            int mid = (left + right) / 2;
            //排序[0~mid]
            mergeSort(arr, left, mid);
            //排序[mid~right]
            mergeSort(arr, mid + 1, right);
            //归并左右两边数组
            merge(arr, left, mid, right);
        }
    }

    /**
     * 自底向上的归并排序
     */
    private static void mergeSortBu(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n + 1; i += i) {
            for (int j = 0; j + i < n; j += i + i) {
                int right = n - 1 < j + 2 * i - 1 ? n - 1 : j + 2 * i - 1;
                System.out.println("left=" + j + ",mid=" + (j + i - 1) + ",right=" + right);
                merge(arr, j, j + i - 1, right);
            }
        }
    }

    /**
     * 归并left-right排序
     */
    private static void merge(int arr[], int left, int mid, int right) {
        int temp[] = new int[arr.length];
        for (int i = left; i < right + 1; i++) {
            temp[i - left] = arr[i];
        }
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i > mid) {
                arr[k] = temp[j - left];
                j++;
            } else if (j > right) {
                arr[k] = temp[i - left];
                i++;
            } else if (temp[i - left] < temp[j - left]) {
                arr[k] = temp[i - left];
                i++;
            } else {
                arr[k] = temp[j - left];
                j++;
            }
        }

    }
}
