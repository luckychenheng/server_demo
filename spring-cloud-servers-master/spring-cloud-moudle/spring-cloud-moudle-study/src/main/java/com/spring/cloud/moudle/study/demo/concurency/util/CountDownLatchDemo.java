package com.spring.cloud.moudle.study.demo.concurency.util;

import java.util.concurrent.CountDownLatch;

/**
 * countDownLatch测试类
 *
 * @author wangmj
 * @since 2019/1/24
 */
public class CountDownLatchDemo {
    private static CountDownLatch countDownLatch = new CountDownLatch(2);

    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程1开始");
                countDownLatch.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("线程2开始");
                countDownLatch.countDown();
            }
        }).start();
        //此时会等待线程1和线程2执行完才会执行下面代码
        countDownLatch.await();
        System.out.println("3");
    }
}
