package com.abosen.utils;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public abstract class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
