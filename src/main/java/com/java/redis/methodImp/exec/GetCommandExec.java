package com.java.redis.methodImp.exec;

import com.java.redis.context.RedisContext;
import com.java.redis.entity.*;
import com.java.redis.method.ExecCommand;
import com.java.redis.util.Dict;

public class GetCommandExec implements ExecCommand {
    @Override
    public RedisReply execCommand(RedisMessage redisMessage, RedisClient redisClient) {
        int dbNum = redisClient.getDbNum();
        RedisDb db = RedisContext.getDb(dbNum);
        RedisObject redisObject = new RedisObject();
        redisObject.setKey(redisMessage.getArgs().get(0).toString());
        Object o = db.getDb().get(redisObject);
        if (o != null) {
            return new RedisReply(0, o.toString());
        }
        return new RedisReply(0, null);
    }
}
