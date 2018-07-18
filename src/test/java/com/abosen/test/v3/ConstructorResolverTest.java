package com.abosen.test.v3;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.factory.support.ConstructorResolver;
import com.abosen.beans.factory.support.DefaultBeanFactory;
import com.abosen.beans.factory.xml.XmlBeanDefinitionReader;
import com.abosen.core.io.ClassPathResource;
import com.abosen.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */
public class ConstructorResolverTest {
    @Test
    public void testAutowireConstructor() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v3.xml"));

        BeanDefinition beanDefinition = factory.getBeanDefinition("petStore");
        ConstructorResolver resolver = new ConstructorResolver(factory);
        PetStoreService petStoreService = ((PetStoreService) resolver.autowireConstructor(beanDefinition));
        Assert.assertEquals(1, petStoreService.getVersion());
        Assert.assertNotNull(petStoreService.getAccountDao());
        Assert.assertNotNull(petStoreService.getItemDao());
    }
}
