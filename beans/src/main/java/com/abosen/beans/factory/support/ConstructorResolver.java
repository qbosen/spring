package com.abosen.beans.factory.support;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.ConstructorArgument;
import com.abosen.beans.SimpleTypeConverter;
import com.abosen.beans.factory.BeanCreationException;
import com.abosen.beans.factory.config.ConfigurableBeanFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Constructor;
import java.util.List;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */
public class ConstructorResolver {
    protected final Log logger = LogFactory.getLog(getClass());

    private final ConfigurableBeanFactory beanFactory;

    public ConstructorResolver(ConfigurableBeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    public Object autowireConstructor(final BeanDefinition beanDefinition) {
        //todo type index name
        //todo int,string 构造同时存在
        Constructor<?> constructorToUse = null;
        Object[] argsToUse = null;

        Class<?> beanClass;
        try {
            beanClass = this.beanFactory.getBeanClassLoader().loadClass(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            throw new BeanCreationException(beanDefinition.getId(), "Instantiation of bean failed, can't resolve class", e);
        }

        Constructor<?>[] candidates = beanClass.getConstructors();
        BeanDefinitionValueResolver valueResolver = new BeanDefinitionValueResolver(this.beanFactory);
        ConstructorArgument argument = beanDefinition.getConstructorArgument();
        SimpleTypeConverter typeConverter = new SimpleTypeConverter();

        for (int i = 0; i < candidates.length; i++) {
            Class<?>[] parameterTypes = candidates[i].getParameterTypes();
            if (parameterTypes.length != argument.getArgumentCount()) {
                continue;
            }
            argsToUse = new Object[parameterTypes.length];
            boolean isArgsMatch = this.valuesMatchTypes(parameterTypes, argument.getArgumentValues(), argsToUse, valueResolver, typeConverter);
            if (isArgsMatch) {
                constructorToUse = candidates[i];
                break;
            }
        }

        if (constructorToUse == null) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a appropriate constructor");
        }

        try {
            return constructorToUse.newInstance(argsToUse);
        } catch (Exception e) {
            throw new BeanCreationException(beanDefinition.getId(), "can't find a create instance using " + constructorToUse);
        }
    }


    private boolean valuesMatchTypes(Class<?>[] parameterTypes,
                                     List<ConstructorArgument.ValueHolder> valueHolders,
                                     Object[] argsToUse,
                                     BeanDefinitionValueResolver valueResolver,
                                     SimpleTypeConverter typeConverter) {
        for (int i = 0; i < parameterTypes.length; i++) {
            ConstructorArgument.ValueHolder valueHolder = valueHolders.get(i);

            try {
                Object value = valueHolder.getValue();
                Object resolvedValue = valueResolver.resolveValueIfNecessary(value);
                // 转型失败。说明该构造器不匹配
                Object convertedValue = typeConverter.convertIfNecessary(resolvedValue, parameterTypes[i]);
                argsToUse[i] = convertedValue;
            } catch (Exception e) {
                logger.error(e);
                return false;
            }
        }
        return true;
    }
}
