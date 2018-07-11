package com.abosen.test.v1;


import com.abosen.beans.BeanDefinition;
import com.abosen.beans.factory.BeanCreationException;
import com.abosen.beans.factory.BeanDefinitionStoreException;
import com.abosen.beans.factory.support.DefaultBeanFactory;
import com.abosen.beans.factory.xml.XmlBeanDefinitionReader;
import com.abosen.service.v1.PetStoreService;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author qiubaisen
 * @date 2018/7/11
 */
public class BeanFactoryTest {
    private DefaultBeanFactory factory = null;
    private XmlBeanDefinitionReader reader = null;

    @Before
    public void setUp() {
        factory = new DefaultBeanFactory();
        reader = new XmlBeanDefinitionReader(factory);
    }

    @Test
    public void testGetBean() {
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        BeanDefinition beanDefinition = factory.getBeanDefinition("petstore");

        assertTrue(beanDefinition.isSingleton());
        assertFalse(beanDefinition.isPrototype());
        assertEquals(BeanDefinition.SCOPE_DEFAULT, beanDefinition.getScope());
        assertEquals("com.abosen.service.v1.PetStoreService", beanDefinition.getBeanClassName());

        PetStoreService petstore = (PetStoreService) factory.getBean("petstore");
        assertNotNull(petstore);
        PetStoreService petstore_copy = (PetStoreService) factory.getBean("petstore");
        assertTrue(petstore.equals(petstore_copy));
    }

    @Test(expected = BeanCreationException.class)
    public void testInvalidBean() {
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v1.xml"));
        Object invalidBean = factory.getBean("invalidBean");
    }

    @Test(expected = BeanDefinitionStoreException.class)
    public void testInvalidXml() {
        reader.loadBeanDefinitions(new ClassPathResource("xxx.xml"));
    }
}
