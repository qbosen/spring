package com.abosen.beans.factory.support;

import com.abosen.beans.factory.config.RuntimeBeanReference;
import com.abosen.beans.factory.config.TypedStringValue;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */
public class BeanDefinitionValueResolver {
    private final DefaultBeanFactory factory;

    public BeanDefinitionValueResolver(DefaultBeanFactory factory) {
        this.factory = factory;
    }

    public Object resolveValueIfNecessary(Object value) {
        if (value instanceof RuntimeBeanReference) {
            RuntimeBeanReference reference = (RuntimeBeanReference) value;
            String beanName = reference.getBeanName();
            Object bean = factory.getBean(beanName);
            return bean;
        } else if (value instanceof TypedStringValue) {
            TypedStringValue typedStringValue = (TypedStringValue) value;
            return typedStringValue.getValue();
        } else {
            // TODO: 2018/7/15 扩展
            throw new RuntimeException("the value " + value + " has not implemented");
        }
    }
}
