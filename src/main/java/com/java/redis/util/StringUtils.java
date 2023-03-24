package com.java.redis.util;

public class StringUtils {

    public static boolean isNumber(String str) {
        return str.matches("[+|-]?[1-9][\\d]*");
    }
}
