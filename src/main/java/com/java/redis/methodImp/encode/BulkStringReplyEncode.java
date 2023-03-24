package com.java.redis.methodImp.encode;

import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;

public class BulkStringReplyEncode implements EncodeReply {

    @Override
    public String encode(RedisReply redisReply, RedisReply... redisReplies) {
        return "$" + redisReply.getLength() + "\r\n" + redisReply.getMessage() + "\r\n";
    }
}
