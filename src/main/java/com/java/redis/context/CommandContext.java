package com.java.redis.context;

import com.java.redis.constant.RedisCommandFlagEnum;
import com.java.redis.entity.RedisCommand;
import com.java.redis.methodImp.check.GetCheckParam;
import com.java.redis.methodImp.check.SetCheckParam;
import com.java.redis.methodImp.exec.GetCommandExec;
import com.java.redis.methodImp.exec.SetCommandExec;

import java.util.Arrays;
import java.util.List;

public class CommandContext {
    public final static List<RedisCommand> redisCommands = Arrays.asList(
            new RedisCommand("get", new GetCheckParam(), RedisCommandFlagEnum.READ.getFlag(), new GetCommandExec()),
            new RedisCommand("set", new SetCheckParam(), RedisCommandFlagEnum.WRITE.getFlag(), new SetCommandExec())
    );
}
