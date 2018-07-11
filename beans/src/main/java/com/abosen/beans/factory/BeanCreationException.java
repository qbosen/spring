package com.abosen.beans.factory;

import com.abosen.beans.BeansException;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class BeanCreationException extends BeansException {
    private String beanName;

    public BeanCreationException(String message) {
        super(message);
    }

    public BeanCreationException(String message, Throwable cause) {
        super(message, cause);
    }

    public BeanCreationException(String beanName, String msg) {
        super("Error creating bean with name '" + beanName + "': " + msg);
        this.beanName = beanName;
    }

    public BeanCreationException(String beanName, String msg, Throwable cause) {
        this(beanName, msg);
        initCause(cause);
    }
    public String getBeanName(){
        return this.beanName;
    }

}
