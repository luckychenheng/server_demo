package com.spring.cloud.moudle.study.demo.design.decorator;

/**
 * 具体的装饰器
 *
 * @author wangmj
 * @since 2018/11/26
 */
public class ConcreteDecorator extends Decorator {
    public ConcreteDecorator(Component component) {
        super(component);
    }

    @Override
    public void operate() {
        System.out.println("操作前");
        super.operate();
        System.out.println("操作后");
    }
}
