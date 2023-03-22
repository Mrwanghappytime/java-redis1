package com.java.redis.context;

import com.java.redis.entity.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class RedisContext {

    private static RedisServer redisServer;

    public static RedisServer getRedisServer() {
        return redisServer;
    }

    public static void setRedisServer(RedisServer redisServer) {
        RedisContext.redisServer = redisServer;
    }

    public static void initRedisContext(String config, String[] args) {
        Integer dbNum = 16;
        RedisServer redisServer = new RedisServer();
        redisServer.setDb(new ArrayList<>());
        for (int i = 0; i < dbNum; i++) {
            redisServer.getDb().add(new HashMap<>());
        }
    }

    public static RedisReply execCommand(RedisMessage message, RedisClient redisClient) {
        RedisCommand redisCommand = redisServer.getCommands().get(message.getCommand());
        if (Objects.isNull(redisCommand)) {
            return new RedisReply(-1);
        }
        redisCommand.getCheckCommandParam().checkCommandParam(message);
        return redisCommand.getExecCommand().execCommand(message, redisClient);
    }
}
