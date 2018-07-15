package com.abosen.beans;

import lombok.Getter;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */

public class PropertyValue {

    private final @Getter
    String name;
    private final @Getter
    Object value;
    private boolean converted = false;
    private Object convertedValue;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public synchronized boolean isConverted() {
        return converted;
    }

    public synchronized Object getConvertedValue() {
        return convertedValue;
    }

    public synchronized void setConvertedValue(Object convertedValue) {
        this.converted = true;
        this.convertedValue = convertedValue;
    }
}
