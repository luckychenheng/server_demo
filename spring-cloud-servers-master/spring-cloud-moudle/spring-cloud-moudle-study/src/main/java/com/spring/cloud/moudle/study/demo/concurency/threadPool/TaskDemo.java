package com.spring.cloud.moudle.study.demo.concurency.threadPool;

/**
 * @author wangmj
 * @since 2019/1/5
 */
public class TaskDemo implements Runnable {
    @Override
    public void run() {
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        System.out.println("thread start"+Thread.currentThread().getName());
    }
}
