package com.java.redis.context;

import java.util.concurrent.locks.ReentrantLock;

public class LockContext {
    private static final ReentrantLock lock = new ReentrantLock(true);

    public static void lock() {
        lock.lock();
    }

    public static void unlock() {
        lock.unlock();
    }
}
