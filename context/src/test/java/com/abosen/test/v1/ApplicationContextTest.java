package com.abosen.test.v1;

import com.abosen.context.ApplicationContext;
import com.abosen.context.support.ClassPathXmlApplicationContext;
import com.abosen.context.support.FileSystemXmlApplicationContext;
import com.abosen.service.v1.FruitService;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @author qiubaisen
 * @date 2018/7/12
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("fruit-v1.xml");
        FruitService fruitService = (FruitService) context.getBean("fruitService");
        assertNotNull(fruitService);
    }

    @Test
     public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/fruit-v1.xml");
        FruitService fruitService = (FruitService) context.getBean("fruitService");
        assertNotNull(fruitService);
    }
}
