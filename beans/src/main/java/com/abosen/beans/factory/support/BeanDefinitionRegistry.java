package com.abosen.beans.factory.support;

import com.abosen.beans.BeanDefinition;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface BeanDefinitionRegistry {
    BeanDefinition getBeanDefinition(String beanName);

    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
