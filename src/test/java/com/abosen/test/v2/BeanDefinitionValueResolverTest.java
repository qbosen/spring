package com.abosen.test.v2;

import com.abosen.beans.factory.config.RuntimeBeanReference;
import com.abosen.beans.factory.config.TypedStringValue;
import com.abosen.beans.factory.support.BeanDefinitionValueResolver;
import com.abosen.beans.factory.support.DefaultBeanFactory;
import com.abosen.beans.factory.xml.XmlBeanDefinitionReader;
import com.abosen.core.io.ClassPathResource;
import com.abosen.dao.v2.AccountDao;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */
public class BeanDefinitionValueResolverTest {

    private BeanDefinitionValueResolver resolver;

    @Before
    public void setUp() {
        DefaultBeanFactory factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        reader.loadBeanDefinitions(new ClassPathResource("petstore-v2.xml"));
        resolver = new BeanDefinitionValueResolver(factory);
    }

    @Test
    public void testResolveRuntimeBeanReference() {
        RuntimeBeanReference reference = new RuntimeBeanReference("accountDao");
        Object value = resolver.resolveValueIfNecessary(reference);

        Assert.assertNotNull(value);
        Assert.assertTrue(value instanceof AccountDao);
    }

    public void testResolveTypedStringValue() {
        TypedStringValue stringValue = new TypedStringValue("test");

        Object value = resolver.resolveValueIfNecessary(stringValue);
        Assert.assertNotNull(value);
        Assert.assertEquals("test", value);
    }
}
