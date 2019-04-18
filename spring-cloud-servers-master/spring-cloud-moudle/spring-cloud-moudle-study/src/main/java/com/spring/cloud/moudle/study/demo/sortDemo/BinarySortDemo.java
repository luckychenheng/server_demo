package com.spring.cloud.moudle.study.demo.sortDemo;

/**
 * 二分查找
 *
 * @author wangmj
 * @since 2019/3/9
 */
public class BinarySortDemo {

    private static final int target = 99;

    public static void main(String[] args) {

        int result = binarySearch();
        System.out.println(result);

    }

    private static int binarySearch() {
        int[] arr = CommonSort.sortArr;
        int end = arr.length;
        int start = 0;
        while (start < end) {
            int mid = (start + end) / 2;
            if (end - start == 1) {
                return 0;
            }
            if (arr[mid] < target) {
                start = mid;
            } else if (arr[mid] > target) {
                end = mid;
            } else {
                return mid;
            }
        }
        return 0;
    }

}
