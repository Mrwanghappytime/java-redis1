package com.java.redis.methodImp.check;

import com.java.redis.entity.RedisMessage;
import com.java.redis.method.CheckCommandParam;

import java.util.List;

public class GetCheckParam implements CheckCommandParam {
    @Override
    public void checkCommandParam(RedisMessage redisMessage) {
        List<Object> args = redisMessage.getArgs();
        if (args == null && args.size() == 0) {
            throw new RuntimeException("参数不能为空");
        }

        if (args.size() > 1) {
            throw new RuntimeException("不能接受这么多参数");
        }
    }
}
