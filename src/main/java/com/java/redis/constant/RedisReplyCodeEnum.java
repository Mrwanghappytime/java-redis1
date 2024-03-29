package com.java.redis.constant;

import com.java.redis.entity.RedisReply;
import com.java.redis.method.EncodeReply;
import com.java.redis.methodImp.encode.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public enum RedisReplyCodeEnum {
    SIMPLE_STRING(0, "简单字符串", new SimpleStringReplyEncode()),
    ERR_STRING(1, "错误字符串", new ErrStringReplyEncode()),
    NUMBER(2, "整数", new NumberReplyEncode()),
    BULK_STRING(3, "批量字符串", new BulkStringReplyEncode()),
    ARRAY(5, "数组", new ArrayReplyEncode()),
    ;

    private Integer code;
    private String title;
    private EncodeReply encodeReply;
    private static Map<Integer, RedisReplyCodeEnum> map;

    RedisReplyCodeEnum(Integer code, String title, EncodeReply encodeReply) {
        this.code = code;
        this.title = title;
        this.encodeReply = encodeReply;

    }

    public Integer getCode() {
        return code;
    }

    public String getTitle() {
        return title;
    }

    public static String encodeReply(RedisReply redisReply) {
        initMap();

        if (!map.containsKey(redisReply.getCode())) {
            String encode = RedisReplyCodeEnum.ERR_STRING.encodeReply.encode(redisReply, redisReply.getRedisReplies() == null ? null : redisReply.getRedisReplies().toArray(new RedisReply[0]));
            return encode;
        }

        return map.get(redisReply.getCode()).encodeReply.encode(redisReply);
    }

    private static void initMap() {
        if (map == null) {
            map = Arrays.stream(values()).collect(Collectors.toMap(RedisReplyCodeEnum::getCode, a -> a));
        }
    }


}
