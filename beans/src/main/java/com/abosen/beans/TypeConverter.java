package com.abosen.beans;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */
public interface TypeConverter {
    <T> T convertIfNecessary(Object value, Class<T> requiredType) throws TypeMismatchException;
}
