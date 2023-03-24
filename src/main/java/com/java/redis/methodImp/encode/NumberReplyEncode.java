package com.java.redis.methodImp.encode;

import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;

import java.util.Arrays;

public class NumberReplyEncode implements EncodeReply {

    @Override
    public String encode(RedisReply redisReply, RedisReply... redisReplies) {
        return  ":" + redisReply.getMessage() + "\r\n";
    }
}
