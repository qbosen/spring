package com.abosen.beans;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */
public class TypeMismatchException extends BeansException {

    private transient Object value;
    private Class<?> requiredType;

    public TypeMismatchException(Object value, Class<?> requiredType) {
        super("Filed to convert value:" + value + "to type " + requiredType);
        this.value = value;
        this.requiredType = requiredType;
    }

    public Object getValue() {
        return value;
    }

    public Class<?> getRequiredType() {
        return requiredType;
    }
}
