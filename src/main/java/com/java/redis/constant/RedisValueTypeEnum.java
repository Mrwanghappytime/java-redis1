package com.java.redis.constant;

public enum RedisValueTypeEnum {
    STRING(0, "STR"),
    LIST(1, "LIST"),
    SET(2, "SET"),
    HASH(3, "HASH"),
    SORT_SET(4, "SORT_SET"),
    ;


    private Integer type;
    private String title;

    RedisValueTypeEnum(Integer type, String title) {
        this.type = type;
        this.title = title;
    }

    public Integer getType() {
        return type;
    }

    public String getTitle() {
        return title;
    }
}
