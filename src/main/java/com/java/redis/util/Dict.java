package com.java.redis.util;

import com.java.redis.context.RedisContext;

public class Dict {
    public static final int INIT_SIZE = 4;

    private DictType dictType;
    private DictHt[] ht;
    private int rehashIndex;

    public Dict(DictType dictType) {
        this.dictType = dictType;
        ht = new DictHt[2];
        ht[0] = new DictHt();
        rehashIndex = -1;
    }

    public Dict() {
        this((s) -> s.hashCode());
    }


    public static Dict dictCreate() {
        return new Dict();
    }

    public <T> void add(String key, T value) {
        DictEntry tDictEntry = new DictEntry<>(null, key, value);
        boolean inRehashAndRehash = checkInRehashAndRehash();
        int dictHtIndex = inRehashAndRehash ? 1 : 0;
        int index = dictType.hashFunction(key) & ht[dictHtIndex].sizemask;
        tDictEntry.next = ht[dictHtIndex].entries[index];
        ht[dictHtIndex].entries[index] = tDictEntry;
        ht[dictHtIndex].used++;
        if (!inRehashAndRehash) {
            checkNeedRehashAndInitRehash(true);
        }
    }

    private void checkNeedRehashAndInitRehash(boolean add) {
        boolean inBgSaveAndRewriteAof = isInBgSaveAndRewriteAof();

    }

    private boolean isInBgSaveAndRewriteAof() {
        return RedisContext.getRedisServer().getInBgAof() || RedisContext.getRedisServer().getInBgSave();
    }

    private boolean checkInRehashAndRehash() {
        if (rehashIndex == -1) {
            return false;
        }
        rehash();
        return rehashIndex != -1;
    }

    private void rehash() {
        DictEntry entry = ht[0].entries[rehashIndex];
        while (entry != null) {
            int index = this.dictType.hashFunction(entry.key) & ht[1].sizemask;
            entry.next = ht[1].entries[index];
            ht[1].entries[index] = entry;
            ht[1].used++;
        }
        rehashIndex++;
        if (rehashIndex == ht[0].size) {
            rehashIndex = -1;
            ht[0] = ht[1];
            ht[1] = null;
        }
    }

    private static class DictEntry<T> {
        private DictEntry next;
        private String key;
        private T value;

        public DictEntry(DictEntry next, String key, T value) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private static class DictHt {
        private DictEntry[] entries;
        // 每次扩容一倍，初始值为4
        private int size;
        // size - 1
        private int sizemask;
        private int used;

        public DictHt() {
            this(INIT_SIZE);
        }

        public DictHt(int initSize) {
            entries = new DictEntry[initSize];
            size = initSize;
            sizemask = size - 1;
            used = 0;
        }
    }

    @FunctionalInterface
    public interface DictType {
        int hashFunction(String key);
    }
}
