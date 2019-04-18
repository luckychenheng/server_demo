package com.spring.cloud.moudle.study.demo.concurency.wait;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class Consumer implements Runnable {
    private int num;
    private QueueDemo demo;

    public Consumer(int num,QueueDemo demo ) {
        this.num = num;
        this.demo = demo;
    }

    @Override
    public void run() {
        try {
            demo.consume(num);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
