package com.spring.cloud.moudle.study.demo.decodeDemo;

/**
 * @author wangmj
 * @since 2018/11/11
 */
public enum Command {
    LOGIN_COMMAND("login", (byte) 1);

    private String name;
    private Byte value;

    Command(String name, Byte value) {
        this.name = name;
        this.value = value;
    }


    public Byte getValue() {
        return value;
    }

    public String getName() {
        return name;
    }
}
