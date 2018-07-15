package com.abosen.beans.factory.config;


import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */

@AllArgsConstructor
public class RuntimeBeanReference {
    private final @Getter
    String beanName;

}
