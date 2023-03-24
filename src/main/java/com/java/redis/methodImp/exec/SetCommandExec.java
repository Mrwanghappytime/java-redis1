package com.java.redis.methodImp.exec;

import com.java.redis.constant.RedisValueEncodeEnum;
import com.java.redis.constant.RedisValueTypeEnum;
import com.java.redis.context.RedisContext;
import com.java.redis.entity.RedisClient;
import com.java.redis.entity.RedisMessage;
import com.java.redis.entity.RedisObject;
import com.java.redis.entity.RedisReply;
import com.java.redis.method.ExecCommand;
import com.java.redis.util.Dict;
import com.java.redis.util.StringUtils;

public class SetCommandExec implements ExecCommand {
    @Override
    public RedisReply execCommand(RedisMessage redisMessage, RedisClient redisClient) {
        int dbNum = redisClient.getDbNum();
        Dict<RedisObject, Object> db = RedisContext.getDb(dbNum).getDb();
        RedisObject redisObject = new RedisObject();
        redisObject.setKey(redisMessage.getArgs().get(0).toString());
        Dict.DictEntry<RedisObject, Object> entry = db.getEntry(redisObject);

        if (entry != null && !entry.getKey().getValueType().equals(RedisValueTypeEnum.STRING.getType())) {
           return new RedisReply(1, "WRONG TYPE Operation against a key holding the wrong kind of value");
        }
        redisObject.setValueType(RedisValueTypeEnum.STRING.getType());
        String value = redisMessage.getArgs().get(1).toString();
        redisObject.setValueEncode(RedisValueEncodeEnum.STR.getType());

        if (StringUtils.isNumber(value)) {
            redisObject.setValueEncode(RedisValueEncodeEnum.NUMBER.getType());
        }

        db.set(redisObject, value);
        return new RedisReply(0, "OK");
    }
}
