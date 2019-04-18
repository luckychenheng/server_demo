package com.spring.cloud.moudle.study.demo.concurency.lock;

/**
 * 共享锁测试类 结果应为两个线程同时获取到锁，然后等待1S，即线程成对出现
 *
 * @author wangmj
 * @since 2019/1/18
 */
public class CountDownLockTest {

    static class task implements Runnable {
        CountDownLock lock = new CountDownLock();

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(1000);
                System.out.println("currentThread" + Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unLock();
            }
        }
    }


    public static void main(String[] args) throws InterruptedException {
        task task = new task();
        for (int i = 0; i < 10; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
