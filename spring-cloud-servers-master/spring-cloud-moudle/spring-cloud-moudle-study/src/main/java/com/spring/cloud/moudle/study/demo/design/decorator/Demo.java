package com.spring.cloud.moudle.study.demo.design.decorator;

/**
 * @author wangmj
 * @since 2018/11/26
 */
public class Demo {
    public static void main(String[] args) {
        Component component = new ConcreteDecorator(new ConcreteComponent());
        component.operate();
    }
}
