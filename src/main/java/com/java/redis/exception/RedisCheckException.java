package com.java.redis.exception;

public class RedisCheckException extends RuntimeException {

    private String msg;

    public RedisCheckException(String msg) {
        super(msg);
        this.msg = msg;
    }

    public String getMsg() {
        return msg;
    }
}
