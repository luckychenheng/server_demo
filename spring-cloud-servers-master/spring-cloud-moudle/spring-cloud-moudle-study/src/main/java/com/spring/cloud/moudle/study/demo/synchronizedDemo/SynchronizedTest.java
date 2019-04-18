package com.spring.cloud.moudle.study.demo.synchronizedDemo;

/**
 * @author wangmj
 * @since 2018/12/19
 */
public class SynchronizedTest {

    //普通方法锁定的是对象
    public synchronized void test() {

        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "count:" + i);
        }
    }

    //代码块锁定的为对象
    public void test1() {
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + "count:" + i);
            }
        }
    }

    //静态方法锁定的为类
    private synchronized static void test3() {
        for (int i = 0; i < 5; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "count:" + i);
        }
    }
}
