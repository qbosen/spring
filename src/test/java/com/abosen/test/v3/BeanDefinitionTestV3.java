package com.abosen.test.v3;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.ConstructorArgument;
import com.abosen.beans.factory.config.RuntimeBeanReference;
import com.abosen.beans.factory.config.TypedStringValue;
import com.abosen.beans.factory.support.DefaultBeanFactory;
import com.abosen.beans.factory.xml.XmlBeanDefinitionReader;
import com.abosen.core.io.ClassPathResource;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */
public class BeanDefinitionTestV3 {
    private DefaultBeanFactory factory;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));
    }

    @Test
    public void testConstructorArgument() {
        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        Assert.assertEquals("com.abosen.service.v3.PetStoreService", beanDefinition.getBeanClassName());

        ConstructorArgument argument = beanDefinition.getConstructorArgument();
        List<ConstructorArgument.ValueHolder> valueHolders = argument.getArgumentValues();
        Assert.assertEquals(3, valueHolders.size());

        RuntimeBeanReference reference1 = (RuntimeBeanReference) valueHolders.get(0).getValue();
        Assert.assertEquals("accountDao", reference1.getBeanName());
        RuntimeBeanReference reference2 = (RuntimeBeanReference) valueHolders.get(1).getValue();
        Assert.assertEquals("itemDao", reference2.getBeanName());
        TypedStringValue stringValue = (TypedStringValue) valueHolders.get(2).getValue();
        Assert.assertEquals("1", stringValue.getValue());
    }

}
