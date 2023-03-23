package com.java.redis.entity;

public class RedisReply {
    private Integer code;
    private String message;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public RedisReply(Integer code) {
        this.code = code;
    }

    public RedisReply(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    @Override
    public String toString() {
        return "RedisReply{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
