package com.java.redis.entity;

import com.java.redis.util.Dict;

public class RedisDb {
    private Dict<RedisObject, Object> db;
    private Dict<RedisObject, Long> expire;

    public RedisDb() {
        db = Dict.dictCreate();
        expire = Dict.dictCreate();
    }

    public Dict<RedisObject, Object> getDb() {
        return db;
    }

    public void setDb(Dict<RedisObject, Object> db) {
        this.db = db;
    }

    public Dict<RedisObject, Long> getExpire() {
        return expire;
    }

    public void setExpire(Dict<RedisObject, Long> expire) {
        this.expire = expire;
    }
}
