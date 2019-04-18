package com.spring.cloud.moudle.study.demo.jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wangmj
 * @since 2019/1/9
 */
public class OOMTest {
    public static void main(String[] args) {
        List list = new ArrayList();
        while (true) {
            list.add(new HeapVo());
        }
    }
}
