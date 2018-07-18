package com.abosen.beans.factory.support;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.ConstructorArgument;
import com.abosen.beans.PropertyValue;

import java.util.ArrayList;
import java.util.List;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class GenericBeanDefinition implements BeanDefinition {


    private final String beanName;
    private String beanClassName;
    private boolean singleton = true;
    private boolean prototype = false;
    private String scope = SCOPE_DEFAULT;
    private List<PropertyValue> propertyValues = new ArrayList<>();
    private ConstructorArgument constructorArgument = new ConstructorArgument();

    public GenericBeanDefinition(String beanName, String beanClassName) {
        this.beanName = beanName;
        this.beanClassName = beanClassName;
    }

    @Override
    public String getId() {
        return beanName;
    }

    @Override
    public String getBeanClassName() {
        return this.beanClassName;
    }

    @Override
    public void setBeanClassName(String beanClassName) {
        this.beanClassName = beanClassName;
    }

    public String getBeanName() {
        return beanName;
    }

    @Override
    public boolean isSingleton() {
        return this.singleton;
    }

    @Override
    public boolean isPrototype() {
        return this.prototype;
    }

    @Override
    public String getScope() {
        return this.scope;
    }

    @Override
    public void setScope(String scope) {
        this.scope = scope;
        this.singleton = SCOPE_SINGLETON.equals(scope) || SCOPE_DEFAULT.equals(scope);
        this.prototype = SCOPE_PROTOTYPE.equals(scope);
    }

    @Override
    public List<PropertyValue> getPropertyValues() {
        return this.propertyValues;
    }

    @Override
    public ConstructorArgument getConstructorArgument() {
        return this.constructorArgument;
    }

    @Override
    public boolean hasConstructorArgument() {
        return !this.constructorArgument.isEmpty();
    }
}
