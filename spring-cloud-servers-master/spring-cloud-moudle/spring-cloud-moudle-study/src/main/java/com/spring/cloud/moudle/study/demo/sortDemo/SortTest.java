package com.spring.cloud.moudle.study.demo.sortDemo;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * 选择排序
 *
 * @author wangmj
 * @since 2019/3/13
 */
public class SortTest {
    public static void main(String[] args) {
        //随机生成区间内的正整数
        int[] arr = SortHelper.generateArr(20, 2, 30000);
//        MergeSortDemo.mergeSort(arr, 0, arr.length - 1);
        //copy数据
        int[] arr1 = Arrays.copyOf(arr, 10000);
        int[] arr2 = Arrays.copyOf(arr, 10000);

        //选择排序
        long start = System.nanoTime();
        SelectionSortDemo.selectionSort(arr);
        long endTime = System.nanoTime();
        System.out.println("selection time = " + TimeUnit.NANOSECONDS.toMillis(endTime - start));
        SortHelper.printer(arr);

        //插入排序
        long start1 = System.nanoTime();
        InsertSortDemo.insertSort(arr1);
        long endTime1 = System.nanoTime();
        System.out.println("insert time = " + TimeUnit.NANOSECONDS.toMillis(endTime1 - start1));
        SortHelper.printer(arr1);

        //插入排序改进
        long start2 = System.nanoTime();
        InsertSortDemo.insertSortPro(arr2);
        long endTime2 = System.nanoTime();
        System.out.println("insertPro time = " + TimeUnit.NANOSECONDS.toMillis(endTime2 - start2));
        SortHelper.printer(arr2);


    }



}
