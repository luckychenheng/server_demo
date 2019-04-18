package com.spring.cloud.moudle.study.demo.design.decorator;

/**
 * 装饰器
 *
 * @author wangmj
 * @since 2018/11/26
 */
public class Decorator implements Component {
    private Component component;

    public Decorator(Component component) {
        this.component = component;
    }

    public void operate() {
        component.operate();
    }
}
