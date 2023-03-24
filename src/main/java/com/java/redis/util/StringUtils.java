package com.java.redis.util;

import java.util.Arrays;
import java.util.List;

public class StringUtils {

    public static boolean isNumber(String str) {
        return str.matches("[+|-]?[1-9][\\d]*");
    }
}
