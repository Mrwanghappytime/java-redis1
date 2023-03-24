package com.java.redis.methodImp.encode;

import com.java.redis.constant.RedisReplyCodeEnum;
import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;

public class ArrayReplyEncode implements EncodeReply {

    @Override
    public String encode(RedisReply redisReply, RedisReply... redisReplies) {
        int length = redisReplies.length;
        StringBuilder sb = new StringBuilder();
        sb.append("*" + length + "\r\n");
        for (int i = 0; i < length; i++) {
            sb.append(RedisReplyCodeEnum.encodeReply(redisReplies[i]));
        }
        return  sb.toString();
    }
}
