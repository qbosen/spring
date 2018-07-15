package com.abosen.beans;

import java.util.List;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface BeanDefinition {

    String SCOPE_SINGLETON = "singleton";
    String SCOPE_PROTOTYPE = "prototype";
    String SCOPE_DEFAULT = "";

    String getBeanClassName();

    void setBeanClassName(String beanClassName);

    boolean isSingleton();

    boolean isPrototype();

    String getScope();

    void setScope(String scope);

    List<PropertyValue> getPropertyValues();

}
