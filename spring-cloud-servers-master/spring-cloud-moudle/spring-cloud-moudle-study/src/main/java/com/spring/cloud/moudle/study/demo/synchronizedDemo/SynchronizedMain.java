package com.spring.cloud.moudle.study.demo.synchronizedDemo;

/**
 * @author wangmj
 * @since 2018/12/19
 */
public class SynchronizedMain {
    public static void main(String[] args) {
        SynchronizedTest test = new SynchronizedTest();
        SynchronizedTest test1 = new SynchronizedTest();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                test.test1();
//                test1.test1();
            }
        });

        thread1.start();
        thread2.start();

    }
}
