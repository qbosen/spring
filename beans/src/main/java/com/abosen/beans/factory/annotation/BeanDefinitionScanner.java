package com.abosen.beans.factory.annotation;

import com.abosen.beans.BeanDefinition;

import java.util.Set;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public interface BeanDefinitionScanner {
    Set<BeanDefinition> doScan(String packageToScan);
}
