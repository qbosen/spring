package com.abosen.beans.factory.support;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.PropertyValue;
import com.abosen.beans.SimpleTypeConverter;
import com.abosen.beans.factory.BeanCreationException;
import com.abosen.beans.factory.config.ConfigurableBeanFactory;
import com.abosen.utils.Assert;
import com.abosen.utils.ClassUtils;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.time.Period;
import java.util.List;
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
        Object bean = instantiateBean(beanDefinition);
        populateBean(beanDefinition, bean);
        return bean;
    }

    private void populateBean(BeanDefinition beanDefinition, Object bean) {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        if (propertyValues == null || propertyValues.size() == 0) {
            return;
        }
        BeanDefinitionValueResolver resolver = new BeanDefinitionValueResolver(this);
        SimpleTypeConverter converter = new SimpleTypeConverter();
        for (PropertyValue propertyValue : propertyValues) {
            String propertyName = propertyValue.getName();
            Object originValue = propertyValue.getValue();
            Object resolvedValue = resolver.resolveValueIfNecessary(originValue);

            try {
                // 遍历查找bean的属性，并设置值
                BeanInfo beanInfo = Introspector.getBeanInfo(bean.getClass());
                PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
                for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
                    if (propertyDescriptor.getName().equals(propertyName)) {
                        Object convertedValue = converter.convertIfNecessary(resolvedValue, propertyDescriptor.getPropertyType());
                        propertyDescriptor.getWriteMethod().invoke(bean, convertedValue);
                        break;
                    }
                }

            } catch (Exception e) {
                throw new BeanCreationException("Failed to obtain BeanInfo for class [" + beanDefinition.getBeanClassName() + "]", e);
            }
        }
    }

    private Object instantiateBean(BeanDefinition beanDefinition) {
        if (beanDefinition.hasConstructorArgument()) {
            ConstructorResolver resolver = new ConstructorResolver(this);
            return resolver.autowireConstructor(beanDefinition);
        } else {
            ClassLoader classLoader = this.getBeanClassLoader();
            String beanClassName = beanDefinition.getBeanClassName();

            try {
                Class<?> aClass = classLoader.loadClass(beanClassName);
                return aClass.newInstance();
            } catch (Exception e) {
                throw new BeanCreationException(beanClassName, "create bean failed", e);
            }
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
