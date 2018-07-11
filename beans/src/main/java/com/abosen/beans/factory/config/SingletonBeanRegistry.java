package com.abosen.beans.factory.config;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface SingletonBeanRegistry {
    void registrySingleton(String beanName, Object singletonObject);

    Object getSingleton(String beanName);
}
