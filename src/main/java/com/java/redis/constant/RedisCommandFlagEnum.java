package com.java.redis.constant;

public enum RedisCommandFlagEnum {

    READ(1),
    WRITE(1 << 1),
    NO_AUTH(1 << 2);
    ;

    private int flag;

    RedisCommandFlagEnum(int flag) {
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

}
