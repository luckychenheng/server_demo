package com.spring.cloud.moudle.study.demo.concurency.util;

import java.util.concurrent.*;

/**
 * @author wangmj
 * @since 2019/1/24
 */
public class SemaphoreDemo {

    //同时只有三个线程执行
    private static Semaphore semaphore = new Semaphore(3);

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    public static void main(String[] args) {

        long start = System.nanoTime();

        for (int i = 0; i < 10; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        semaphore.acquire();
                        Thread.sleep(1000);
                        System.out.println("thread name:" + Thread.currentThread().getName()+" and nanoTime:"
                                + TimeUnit.NANOSECONDS.toSeconds(System.nanoTime()-start));
                        semaphore.release();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
        executor.shutdown();
    }

}
