package com.java.redis.methodImp.encode;

import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;

public class SimpleStringReplyEncode implements EncodeReply {

    @Override
    public String encode(RedisReply redisReply) {
        String message = redisReply.getMessage();
        return "+" + (message == null ? "null" : message) + "\r\n";
    }
}
