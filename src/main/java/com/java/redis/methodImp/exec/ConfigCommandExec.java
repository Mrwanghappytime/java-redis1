package com.java.redis.methodImp.exec;

import com.java.redis.entity.RedisClient;
import com.java.redis.entity.RedisMessage;
import com.java.redis.entity.RedisReply;
import com.java.redis.method.ExecCommand;

import java.util.List;

public class ConfigCommandExec implements ExecCommand {
    @Override
    public RedisReply execCommand(RedisMessage redisMessage, RedisClient redisClient) {
        List<Object> args = redisMessage.getArgs();
        return new RedisReply(0, "16");
    }
}
