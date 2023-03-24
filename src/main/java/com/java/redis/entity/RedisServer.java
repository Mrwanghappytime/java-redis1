package com.java.redis.entity;

import com.java.redis.util.Dict;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;

public class RedisServer {

    private int dbNum;

    private RedisDb[] db;

    private Dict<String, RedisCommand> commands;

    private Dict<String, RedisClient> clients;

    private boolean inBgSave;

    private boolean inBgAof;

    public RedisServer(Integer dbNum) {
        this.dbNum = dbNum;
        this.setDb(new RedisDb[dbNum]);
        for (int i = 0; i < dbNum; i++) {
            this.getDb()[i] = new RedisDb();
        }
        clients = new Dict<>();
    }

    public boolean getInBgSave() {
        return inBgSave;
    }

    public void setInBgSave(boolean inBgSave) {
        this.inBgSave = inBgSave;
    }

    public boolean getInBgAof() {
        return inBgAof;
    }

    public void setInBgAof(boolean inBgAof) {
        this.inBgAof = inBgAof;
    }

    public RedisDb[] getDb() {
        return db;
    }

    public void setDb(RedisDb[] db) {
        this.db = db;
    }

    public Dict<String, RedisCommand> getCommands() {
        return commands;
    }

    public void setCommands(Dict<String, RedisCommand> commands) {
        this.commands = commands;
    }

    public Dict<String, RedisClient> getClients() {
        return clients;
    }

    public void setClients(Dict<String, RedisClient> clients) {
        this.clients = clients;
    }

    public boolean isInBgSave() {
        return inBgSave;
    }

    public boolean isInBgAof() {
        return inBgAof;
    }

    public int getDbNum() {
        return dbNum;
    }

    public void setDbNum(int dbNum) {
        this.dbNum = dbNum;
    }


}
