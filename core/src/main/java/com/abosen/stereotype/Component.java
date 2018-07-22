package com.abosen.stereotype;

import java.lang.annotation.*;

/**
 * @author qiubaisen
 * @date 2018/7/22
 */

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Component {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     *
     * @return the suggested component name, if any
     */
    public abstract String value() default "";
}
