package com.java.redis.entity;

import java.util.List;

public class RedisReply {
    private Integer code;
    private String message;
    private int length;
    private List<RedisReply> redisReplies;

    public void setRedisReplies(List<RedisReply> redisReplies) {
        this.redisReplies = redisReplies;
    }

    public List<RedisReply> getRedisReplies() {
        return redisReplies;
    }

    public static RedisReply builder(Integer code, String message) {
        return new RedisReply(code, message);
    }

    public static RedisReply builder(Integer code, String message, int length) {
        return new RedisReply(code, message, length);
    }

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

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public RedisReply(Integer code) {
        this.code = code;
    }

    public RedisReply(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public RedisReply(Integer code, String message, int length) {
        this.code = code;
        this.message = message;
        this.length = length;
    }

    @Override
    public String toString() {
        return "RedisReply{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
