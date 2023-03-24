package com.java.redis.entity;

import java.util.Objects;

public class RedisObject {

    private String key;

    private Integer valueType;

    private Integer valueEncode;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RedisObject that = (RedisObject) o;
        return Objects.equals(key, that.key);
    }

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }

    public RedisObject(String key) {
        this.key = key;
    }

    public RedisObject() {
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public Integer getValueEncode() {
        return valueEncode;
    }

    public void setValueEncode(Integer valueEncode) {
        this.valueEncode = valueEncode;
    }
}
