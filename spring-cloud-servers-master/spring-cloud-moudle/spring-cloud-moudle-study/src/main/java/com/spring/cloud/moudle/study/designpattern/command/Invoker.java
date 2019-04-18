package com.spring.cloud.moudle.study.designpattern.command;

import java.util.ArrayList;
import java.util.List;

/**
 * 命令调用者，类似服务员角色
 *
 * @author wangmj
 * @since 2018/11/1
 */
public class Invoker {
    private List<ICommand> commands = new ArrayList<>();

    public void addCommond(ICommand command) {
        commands.add(command);
    }

    public void excuteCommand() {
        for (ICommand command : commands) {
            command.execute();
        }
    }
}
