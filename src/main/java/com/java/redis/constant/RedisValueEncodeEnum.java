package com.java.redis.constant;

public enum RedisValueEncodeEnum {
    // 字段传类型的编码方式
    NUMBER(0, "int"),
    STR(1, "str"),
    // list编码类型
    LIST(2, "LIST"),
    QUICK_LIST(3, "QUICK_LIST"),
    // 字典编码
    DICT(4, "DICT"),
    LISTPACK(5, "LISTPACK"),
    // 集合类型
    SET(6, "SET"),
    LISTPACK_SET(7, "LISTPACK_SET"),
    INT_SET(8, "INT_SET"),
    // 排序集合类型
    SKIP_TABLE(9, "SKIP_TABLE"),
    ;


    private Integer type;
    private String title;

    RedisValueEncodeEnum(Integer type, String title) {
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
