package com.abosen.beans.factory.config;

import com.abosen.beans.factory.BeanFactory;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface ConfigurableBeanFactory extends BeanFactory {
    void setBeanClassLoader(ClassLoader classLoader);

    ClassLoader getBeanClassLoader();
}
