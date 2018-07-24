package com.abosen.beans.factory.annotation;

import com.abosen.beans.BeanDefinition;
import com.abosen.core.type.AnnotationMetadata;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public interface AnnotatedBeanDefinition extends BeanDefinition {
    AnnotationMetadata getMetadata();
}
