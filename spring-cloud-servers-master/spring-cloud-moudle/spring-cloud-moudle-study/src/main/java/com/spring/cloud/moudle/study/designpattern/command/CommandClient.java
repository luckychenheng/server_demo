package com.spring.cloud.moudle.study.designpattern.command;

/**
 * @author wangmj
 * @since 2018/11/1
 */
public class CommandClient {
    public static void main(String[] args) {
        ICommand command = new ConcreteCommand("考羊肉串");
        ICommand command1 = new ConcreteCommand("烤大腰子");
        Invoker invoker = new Invoker();
        invoker.addCommond(command);
        invoker.addCommond(command1);
        invoker.excuteCommand();
    }
}
