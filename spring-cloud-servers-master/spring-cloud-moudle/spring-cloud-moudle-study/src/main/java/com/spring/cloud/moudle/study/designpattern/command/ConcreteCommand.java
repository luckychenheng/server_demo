package com.spring.cloud.moudle.study.designpattern.command;

/**
 * 命令具体的实现类
 *
 * @author wangmj
 * @since 2018/11/1
 */
public class ConcreteCommand extends ICommand {

    private String commond;

    public ConcreteCommand(String commond) {
        this.commond = commond;
    }

    @Override
    void execute() {
        System.out.println("执行" + commond + "命令");
    }
}
