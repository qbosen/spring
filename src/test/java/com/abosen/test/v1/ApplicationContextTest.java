package com.abosen.test.v1;

import com.abosen.context.ApplicationContext;
import com.abosen.context.support.ClassPathXmlApplicationContext;
import com.abosen.context.support.FileSystemXmlApplicationContext;
import com.abosen.service.v1.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/12
 */
public class ApplicationContextTest {
    @Test
    public void testGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }

    @Test
    public void testGetBeanFromFileSystemContext() {
        ApplicationContext context = new FileSystemXmlApplicationContext("src/test/resources/petstore-v1.xml");
        PetStoreService petStoreService = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStoreService);
    }
}
