package com.java.redis.methodImp.check;

import com.java.redis.entity.RedisMessage;
import com.java.redis.method.CheckCommandParam;

import java.util.List;

public class SetCheckParam implements CheckCommandParam {
    @Override
    public void checkCommandParam(RedisMessage redisMessage) {
        List<Object> args = redisMessage.getArgs();
        if (args == null && args.size() == 0) {
            throw new RuntimeException("参数不能为空");
        }

        if (args.size() == 2 || args.size() == 4) {
            return;
        }
        throw new RuntimeException("不支持的参数数量" + args.size());
    }
}
