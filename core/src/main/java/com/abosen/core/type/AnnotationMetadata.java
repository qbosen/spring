package com.abosen.core.type;

import com.abosen.core.annotation.AnnotationAttributes;

import java.util.Set;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public interface AnnotationMetadata extends ClassMetadata {
    Set<String> getAnnotationTypes();

    boolean hasAnnotation(String annotationType);

    AnnotationAttributes getAnnotationAttributes(String annotationType);

}
