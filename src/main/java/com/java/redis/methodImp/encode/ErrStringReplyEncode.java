package com.java.redis.methodImp.encode;

import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;

public class ErrStringReplyEncode implements EncodeReply {

    @Override
    public String encode(RedisReply redisReply, RedisReply... redisReplies) {
        return  "-ERR " + (redisReply.getMessage() == null ? "" : redisReply.getMessage()) + "\r\n";
    }
}
