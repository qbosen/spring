package com.abosen.beans;

import com.abosen.beans.factory.support.BeanDefinitionRegistry;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public interface BeanNameGenerator {
    /**
     * Generate a bean name for the given bean definition.
     * @param definition the bean definition to generate a name for
     * @param registry the bean definition registry that the given definition
     * is supposed to be registered with
     * @return the generated bean name
     */
    String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry);
}
