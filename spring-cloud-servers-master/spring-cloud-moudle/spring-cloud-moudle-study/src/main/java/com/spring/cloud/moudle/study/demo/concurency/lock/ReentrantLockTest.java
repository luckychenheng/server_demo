package com.spring.cloud.moudle.study.demo.concurency.lock;

/**
 * 测试公平锁与非公平锁的区别
 *
 * @author wangmj
 * @since 2019/1/19
 */
public class ReentrantLockTest {
    private static RentrantLockDemo fairLock = new RentrantLockDemo(true);
    private static RentrantLockDemo unfairLock = new RentrantLockDemo(false);


    static class Task implements Runnable {
        private RentrantLockDemo lock;

        public Task(RentrantLockDemo lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            lock.lock();
            try {
                Thread.sleep(100);
                System.out.println("currentThread" + Thread.currentThread().getName());
                System.out.println("fairThreads:" + lock.getQueuedThreads());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {

        Task task = new Task(unfairLock);
        for (int i = 0; i < 5; i++) {
            Thread thread = new Thread(task);
            thread.start();
        }
    }
}
