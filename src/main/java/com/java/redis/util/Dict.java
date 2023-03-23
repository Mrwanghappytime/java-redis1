package com.java.redis.util;

import com.java.redis.context.RedisContext;

public class Dict<T, K> {
    public static final int INIT_SIZE = 4;

    private DictType<T> dictType;
    private Double LOAD_FACTOR = 0.75D;
    private DictHt<T, K>[] ht;
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

    public void set(T key, K value) {
        DictEntry tDictEntry = new DictEntry<>(null, key, value);
        boolean inRehashAndRehash = checkInRehashAndRehash();
        int dictHtIndex = inRehashAndRehash ? 1 : 0;
        int index = dictType.hashFunction(key) & ht[dictHtIndex].sizemask;
        tDictEntry.next = ht[dictHtIndex].entries[index];
        ht[dictHtIndex].entries[index] = tDictEntry;
        ht[dictHtIndex].used++;
        if (!inRehashAndRehash) {
            checkNeedRehashAndInitRehash();
        }
    }

    public K get(T key) {
        boolean inRehashAndRehash = checkInRehashAndRehash();
        int hash = dictType.hashFunction(key);

        K search = search(ht[0], key, hash);

        if (search != null) {
            return search;
        }

        if (inRehashAndRehash) {
            return search(ht[1], key, hash);
        }

        return null;
    }

    private K search(DictHt dictHt, T key, int hash) {
        int index = dictHt.sizemask & hash;

        DictEntry<T, K> entry = dictHt.entries[index];

        while (entry != null) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
            entry = entry.next;
        }

        return null;
    }

    private void checkNeedRehashAndInitRehash() {
        boolean inBgSaveAndRewriteAof = isInBgSaveAndRewriteAof();
        if (inBgSaveAndRewriteAof) {
            return;
        }

        if (ht[0].used * LOAD_FACTOR > ht[0].size) {
            ht[1] = new DictHt<>(ht[0].size);
            rehashIndex = 0;
        }
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
        DictEntry<T, K> entry = ht[0].entries[rehashIndex];
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

    public boolean remove(T key) {
        boolean inRehashAndRehash = checkInRehashAndRehash();
        int hash = dictType.hashFunction(key);
        boolean remove = remove(ht[0], key, hash);
        if (!remove && inRehashAndRehash) {
            return remove(ht[1], key, hash);
        }
        return remove;
    }

    private boolean remove(DictHt<T, K> tkDictHt, T key, int hash) {
        int index = tkDictHt.sizemask & hash;

        DictEntry<T, K> entry = tkDictHt.entries[index];

        if (entry == null) {
            return false;
        }

        if (entry.key.equals(key)) {
            tkDictHt.entries[index] = entry.next;
            return true;
        }

        while(entry.next != null) {
            if (entry.next.key.equals(key)) {
                entry.next = entry.next.next;
                return true;
            }
            entry = entry.next;
        }

        return false;

    }

    private static class DictEntry<T, K> {
        private DictEntry next;
        private T key;
        private K value;

        public DictEntry(DictEntry next, T key, K value) {
            this.next = next;
            this.key = key;
            this.value = value;
        }
    }

    private static class DictHt<T, K> {
        private DictEntry<T, K>[] entries;
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
    public interface DictType<T> {
        int hashFunction(T key);
    }
}
