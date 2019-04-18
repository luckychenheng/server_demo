package com.spring.cloud.moudle.study.demo.jvm;

import lombok.Synchronized;

/**
 * @author wangmj
 * @since 2019/1/30
 */
public class DeadLockDemo {

    static class syncronizeDemo implements Runnable {
        private int a;
        private int b;

        public syncronizeDemo(int a, int b) {
            this.a = a;
            this.b = b;
        }

        @Override
        public void run() {
            synchronized (Integer.valueOf(a)) {
                synchronized (Integer.valueOf(b)) {
                    System.out.println(a + b);
                }
            }
        }
    }


    public static void main(String[] args) {
        for (int i = 0; i < 100; i++) {
            new Thread(new syncronizeDemo(1, 2)).start();
            new Thread(new syncronizeDemo(2,1)).start();
        }
    }
}
