package com.spring.cloud.moudle.study.demo.design.decorator;

/**
 * 具体的被装饰者
 *
 * @author wangmj
 * @since 2018/11/26
 */
public class ConcreteComponent implements Component {
    @Override
    public void operate() {
        System.out.println("具体的操作");
    }
}
