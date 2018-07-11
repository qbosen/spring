package com.abosen.test.v1;

import com.abosen.context.ApplicationContext;
import com.abosen.context.support.ClassPathXmlApplicationContext;
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
        FruitService petStore = (FruitService) context.getBean("fruitService");
        assertNotNull(petStore);
    }
}
