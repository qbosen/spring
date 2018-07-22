package com.abosen.beans.factory.annotation;

import java.lang.annotation.*;

/**
 * @author qiubaisen
 * @date 2018/7/22
 */

@Target({ElementType.CONSTRUCTOR, ElementType.FIELD, ElementType.METHOD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Autowired {
    /**
     * Declares whether the annotated dependency is required.
     * <p>Defaults to {@code true}.
     */
    public abstract boolean required() default true;
}
