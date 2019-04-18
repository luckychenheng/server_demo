package com.spring.cloud.moudle.study.demo.sortDemo;

import java.util.Arrays;
import java.util.Random;

/**
 * 算法辅助类
 *
 * @author wangmj
 * @since 2019/3/13
 */
public class SortHelper {

    /**
     * 生成个数为n,范围在[min~max]之间的数组
     *
     * @param n   数组个数
     * @param min 最小数
     * @param max 最大数
     * @return 随机数组
     */
    public static int[] generateArr(int n, int min, int max) {
        assert min <= max;
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            Random random = new Random();
            int value = random.nextInt(max - min) + min + 1;
            arr[i] = value;
        }
        return arr;
    }

    /**
     * 打印数组
     *
     * @param arr 数组
     */
    public static void printer(int arr[]) {
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 交换数组元素
     *
     * @param arr    数组
     * @param before 交换位置1
     * @param after  交换位置2
     */
    public static void swapArr(int[] arr, int before, int after) {
        int beforeVal = arr[before];
        arr[before] = arr[after];
        arr[after] = beforeVal;
    }
}
