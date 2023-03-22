package com.java.redis.entity;

import java.util.HashMap;
import java.util.List;

public class RedisServer {
    private List<HashMap<String, Object>> db;

    private HashMap<String, RedisCommand> commands;

    private boolean inBgSave;

    private boolean inBgAof;

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

    public List<HashMap<String, Object>> getDb() {
        return db;
    }

    public void setDb(List<HashMap<String, Object>> db) {
        this.db = db;
    }

    public HashMap<String, RedisCommand> getCommands() {
        return commands;
    }

    public void setCommands(HashMap<String, RedisCommand> commands) {
        this.commands = commands;
    }
}
