package com.spring.cloud.moudle.study.demo.concurency.lock;

/**
 * 独占锁测试类
 *
 * @author wangmj
 * @since 2019/1/15
 */
public class MuxaLockTest {
    //安全数
    private int safeCount = 0;
    //不安全数
    private int unsafeCount = 0;
    //自定义独占锁
    private MutexLock lock = new MutexLock();

    public static void main(String[] args) throws InterruptedException {
        MuxaLockTest lockTest = new MuxaLockTest();
        for (int j = 0; j < 100; j++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int i = 0; i < 1000; i++) {
                        lockTest.safeCount();
                        lockTest.unsafeCount();
                    }
                }
            }).start();
        }

        //等待使所有线程都执行完毕
        Thread.sleep(2000);

        System.out.println("safeCount = " + lockTest.safeCount);
        System.out.println("unsafeCount = " + lockTest.unsafeCount);

    }

    /**
     * 加锁，线程安全
     */
    private void safeCount() {
        lock.lock();
        try {
            safeCount++;
        } finally {
            lock.unlock();
        }
    }

    /**
     * 不加锁，会造成线程安全问题
     */
    private void unsafeCount() {
        unsafeCount++;
    }
}
