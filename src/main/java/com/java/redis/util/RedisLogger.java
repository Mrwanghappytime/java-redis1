package com.java.redis.util;

import java.io.PrintStream;

public class RedisLogger {
    public static void info(Object o) {
        System.out.println("INFO:" + o);
    }

    public static void error(Object o) {
        System.out.println("ERROR:" + o);
    }

    public static void warn(Object o) {
        System.out.println("WARN:" + o);
    }
}
