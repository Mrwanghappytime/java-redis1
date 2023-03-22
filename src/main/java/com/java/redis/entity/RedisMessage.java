package com.java.redis.entity;

import java.util.List;

public class RedisMessage {

    private String command;

    private List<Object> args;

    public List<Object> getArgs() {
        return args;
    }

    public void setArgs(List<Object> args) {
        this.args = args;
    }

    public String getCommand() {
        return command;
    }

    public void setCommand(String command) {
        this.command = command;
    }

    @Override
    public String toString() {
        return "RedisMessage{" +
                "command='" + command + '\'' +
                ", args=" + args +
                '}';
    }
}
