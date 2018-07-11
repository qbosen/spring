package com.abosen.beans.factory;

import com.abosen.beans.BeansException;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class BeanDefinitionStoreException extends BeansException {
    public BeanDefinitionStoreException(String message) {
        super(message);
    }

    public BeanDefinitionStoreException(String message, Throwable cause) {
        super(message, cause);
    }
}
