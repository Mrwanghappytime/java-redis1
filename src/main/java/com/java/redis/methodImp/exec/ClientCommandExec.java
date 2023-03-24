package com.java.redis.methodImp.exec;

import com.java.redis.entity.RedisClient;
import com.java.redis.entity.RedisMessage;
import com.java.redis.entity.RedisReply;
import com.java.redis.method.ExecCommand;

public class ClientCommandExec implements ExecCommand {
    @Override
    public RedisReply execCommand(RedisMessage redisMessage, RedisClient redisClient) {
        return new RedisReply(0, "OK");
    }
}
