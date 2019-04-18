package com.spring.cloud.moudle.study.demo.sortDemo;

/**
 * 冒泡排序
 *
 * @author wangmj
 * @since 2018/12/26
 */
public class BubbleSortDemo {


    public static void main(String[] args) {
        int arr[] = CommonSort.arr;

        int count = 0;

        for (int i = 0; i < arr.length-1; i++) {
            for (int j = 0; j < arr.length - 1 - i; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                count++;
            }
        }

        CommonSort.printer(arr);
        System.out.println(count);

    }
}
