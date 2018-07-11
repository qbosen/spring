package com.abosen.beans;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class BeansException extends RuntimeException {
    public BeansException(String message) {
        super(message);
    }

    public BeansException(String message, Throwable cause) {
        super(message, cause);
    }
}
