package com.java.redis.methodImp.check;

import com.java.redis.entity.RedisMessage;
import com.java.redis.exception.RedisCheckException;
import com.java.redis.method.CheckCommandParam;

import java.util.List;

public class SetCheckParam implements CheckCommandParam {
    @Override
    public void checkCommandParam(RedisMessage redisMessage) {
        List<Object> args = redisMessage.getArgs();
        if (args == null && args.size() == 0) {
            throw new RedisCheckException("syntax error");
        }

        if (args.size() == 2 || args.size() == 4) {
            return;
        }
        throw new RedisCheckException("syntax error");
    }
}
