package com.abosen.context.annotation;

import com.abosen.beans.factory.annotation.AnnotatedBeanDefinition;
import com.abosen.beans.factory.support.GenericBeanDefinition;
import com.abosen.core.type.AnnotationMetadata;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class ScannedGenericBeanDefinition extends GenericBeanDefinition implements AnnotatedBeanDefinition {

    private final AnnotationMetadata annotationMetadata;

    public ScannedGenericBeanDefinition(AnnotationMetadata metadata) {
        super();
        this.annotationMetadata = metadata;
        super.setBeanClassName(this.annotationMetadata.getClassName());
    }

    @Override
    public final AnnotationMetadata getMetadata() {
        return this.annotationMetadata;
    }
}
