package com.spring.cloud.moudle.study.demo.concurency.wait;

/**
 * @author wangmj
 * @since 2019/1/7
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        QueueDemo demo = new QueueDemo();
        Producer producer = new Producer(3,demo);
        new Thread(producer).start();
        Thread.sleep(1000);

        Producer producer1 = new Producer(9,demo);
        new Thread(producer1).start();
        Thread.sleep(1000);


        Consumer consumer = new Consumer(3,demo);
        new Thread(consumer).start();

    }
}
