package com.spring.cloud.moudle.study.demo.jvm;

/**
 * @author wangmj
 * @create 2018/12/4
 */
public class HeapTest {
    public static void main(String[] args) {
        testA();
        testB();
    }

    private static void testA() {
        HeapVo a = new HeapVo();
        System.out.println(a.hashCode());
        System.out.println("A内存地址" + System.identityHashCode(a));
    }

    private static void testB() {
        HeapVo b = new HeapVo();
        System.out.println(b.hashCode());
        System.out.println("B内存地址" + System.identityHashCode(b));
    }
}
