package com.java.redis.method;

import com.java.redis.entity.RedisClient;
import com.java.redis.entity.RedisMessage;
import com.java.redis.entity.RedisReply;

public interface ExecCommand {
    RedisReply execCommand(RedisMessage redisMessage, RedisClient redisClient);
}
