package com.abosen.beans.factory;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface BeanFactory {
    Object getBean(String beanName);
}
