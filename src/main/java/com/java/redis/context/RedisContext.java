package com.java.redis.context;

import com.java.redis.entity.*;
import com.java.redis.exception.RedisCheckException;
import com.java.redis.method.CheckCommandParam;
import com.java.redis.util.Dict;

import java.time.LocalDateTime;
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
        RedisServer redisServer = new RedisServer(dbNum);
        setRedisServer(redisServer);
        Dict<String, RedisCommand> commandDict = new Dict<>();
        for (int i = 0; i < CommandContext.redisCommands.size(); i++) {{
            RedisCommand redisCommand = CommandContext.redisCommands.get(i);
            commandDict.set(redisCommand.getCommandName(), redisCommand);
        }}
        redisServer.setCommands(commandDict);
    }

    public static RedisReply execCommand(RedisMessage message, RedisClient redisClient) {
        RedisCommand redisCommand = redisServer.getCommands().get(message.getCommand());
        if (Objects.isNull(redisCommand)) {
            return new RedisReply(-1);
        }
        try {
            CheckCommandParam checkCommandParam = redisCommand.getCheckCommandParam();
            if (checkCommandParam != null) {
                checkCommandParam.checkCommandParam(message);
            }
        } catch (Exception e) {
            if (e instanceof RedisCheckException) {
                return new RedisReply(1, ((RedisCheckException) e).getMsg());
            }
            return new RedisReply(1);
        }
        return redisCommand.getExecCommand().execCommand(message, redisClient);
    }

    public static void addClient(String hostName, int port) {
        RedisClient redisClient = new RedisClient();
        redisClient.setHostname(hostName);
        redisClient.setPort(port);
        redisClient.setConnectTime(LocalDateTime.now());
        redisClient.setDbNum(0);
        redisServer.getClients().set(hostName + ":" + port, redisClient);
    }

    public static RedisClient getClient(String hostName, int port) {
        return redisServer.getClients().get(hostName + ":" + port);
    }

    public static void removeClient(String hostName, int port) {
        redisServer.getClients().remove(hostName + ":" + port);
    }

    public static RedisDb getDb(int dbNum) {
        return redisServer.getDb()[dbNum];
    }
}
