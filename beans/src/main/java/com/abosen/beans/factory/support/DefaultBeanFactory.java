package com.abosen.beans.factory.support;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.factory.BeanCreationException;
import com.abosen.beans.factory.config.ConfigurableBeanFactory;
import com.abosen.utils.Assert;
import com.abosen.utils.ClassUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class DefaultBeanFactory extends DefaultSingletonBeanRegistry
        implements ConfigurableBeanFactory, BeanDefinitionRegistry {

    private final Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>(64);

    private ClassLoader classLoader;


    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        Assert.notNull(beanName, "beanName must not be null");
        Assert.notNull(beanDefinition, "beanDefinition must not be null");
        this.beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public Object getBean(String beanName) {
        BeanDefinition beanDefinition = this.beanDefinitionMap.get(beanName);
        if (beanDefinition == null) return null;
        if (beanDefinition.isSingleton()) {
            Object bean = this.getSingleton(beanName);
            if (bean == null) {
                bean = createBean(beanDefinition);
                this.registrySingleton(beanName, bean);
            }
            return bean;
        }
        return createBean(beanDefinition);
    }

    private Object createBean(BeanDefinition beanDefinition) {
        ClassLoader classLoader = this.getBeanClassLoader();
        String beanClassName = beanDefinition.getBeanClassName();

        try {
            Class<?> aClass = classLoader.loadClass(beanClassName);
            return aClass.newInstance();
        } catch (Exception e) {
            throw new BeanCreationException(beanClassName, "create bean failed", e);
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.classLoader != null ? this.classLoader : ClassUtils.getDefaultClassLoader();
    }
}
