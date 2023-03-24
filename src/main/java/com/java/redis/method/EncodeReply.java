package com.java.redis.method;

import com.java.redis.entity.RedisReply;

@FunctionalInterface
public interface EncodeReply {
    String encode(RedisReply redisReply, RedisReply... redisReplies);
}