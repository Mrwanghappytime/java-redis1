package com.java.redis.entity;

import com.java.redis.method.CheckCommandParam;
import com.java.redis.method.ExecCommand;
import io.netty.handler.codec.http.HttpResponseEncoder;

public class RedisCommand {

    private String commandName;

    private CheckCommandParam checkCommandParam;

    private Integer commandFlag;

    private ExecCommand execCommand;

    private long totalCalled;

    private long microseconds;

    public RedisCommand(String commandName, CheckCommandParam checkCommandParam, Integer commandFlag, ExecCommand execCommand) {
        this.commandName = commandName;
        this.checkCommandParam = checkCommandParam;
        this.commandFlag = commandFlag;
        this.execCommand = execCommand;
    }

    public String getCommandName() {
        return commandName;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public CheckCommandParam getCheckCommandParam() {
        return checkCommandParam;
    }

    public void setCheckCommandParam(CheckCommandParam checkCommandParam) {
        this.checkCommandParam = checkCommandParam;
    }

    public Integer getCommandFlag() {
        return commandFlag;
    }

    public void setCommandFlag(Integer commandFlag) {
        this.commandFlag = commandFlag;
    }

    public long getTotalCalled() {
        return totalCalled;
    }

    public void setTotalCalled(long totalCalled) {
        this.totalCalled = totalCalled;
    }

    public long getMicroseconds() {
        return microseconds;
    }

    public void setMicroseconds(long microseconds) {
        this.microseconds = microseconds;
    }

    public ExecCommand getExecCommand() {
        return execCommand;
    }

    public void setExecCommand(ExecCommand execCommand) {
        this.execCommand = execCommand;
    }
}
