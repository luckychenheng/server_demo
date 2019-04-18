package com.spring.cloud.moudle.study.demo.concurency.wait;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class Producer implements Runnable {
    private int num;
    private QueueDemo queueDemo;

    public Producer(int num,QueueDemo queueDemo) {
        this.num = num;
        this.queueDemo = queueDemo;
    }
    @Override
    public void run() {
        queueDemo.product(num);
    }
}
