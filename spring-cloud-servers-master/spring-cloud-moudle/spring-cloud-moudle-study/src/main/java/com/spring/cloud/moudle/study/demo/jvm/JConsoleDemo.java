package com.spring.cloud.moudle.study.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmj
 * @since 2019/1/30
 */
public class JConsoleDemo {
    static class OOMObject{
        public byte[] bytes = new byte[64 * 1024];
    }

    public static void fileHeap(int num) throws InterruptedException {
        List list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            Thread.sleep(50);
            list.add(new byte[64 * 1024]);
        }
        System.gc();
    }

    public static void main(String[] args) throws InterruptedException {
        fileHeap(1000);
    }
}
