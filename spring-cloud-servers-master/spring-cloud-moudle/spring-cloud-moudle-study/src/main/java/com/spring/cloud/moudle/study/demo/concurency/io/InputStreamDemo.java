package com.spring.cloud.moudle.study.demo.concurency.io;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author wangmj
 * @since 2019/1/5
 */
public class InputStreamDemo {
    public static void main(String[] args) {
        String content = "test";
        InputStream inputStream = new ByteArrayInputStream(content.getBytes());
        try {
            int ch ;
            while ((ch= inputStream.read()) != -1) {
                System.out.println((char) ch);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
