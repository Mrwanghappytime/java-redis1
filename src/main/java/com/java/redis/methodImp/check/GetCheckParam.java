package com.java.redis.methodImp.check;

import com.java.redis.entity.RedisMessage;
import com.java.redis.exception.RedisCheckException;
import com.java.redis.method.CheckCommandParam;

import java.util.List;

public class GetCheckParam implements CheckCommandParam {
    @Override
    public void checkCommandParam(RedisMessage redisMessage) {
        List<Object> args = redisMessage.getArgs();
        if (args == null && args.size() == 0) {
            throw new RedisCheckException("wrong number of arguments for 'get' command");
        }

        if (args.size() > 1) {
            throw new RedisCheckException("wrong number of arguments for 'get' command");
        }
    }
}
