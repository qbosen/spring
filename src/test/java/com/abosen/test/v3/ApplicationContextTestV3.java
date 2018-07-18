package com.abosen.test.v3;

import com.abosen.context.ApplicationContext;
import com.abosen.context.support.ClassPathXmlApplicationContext;
import com.abosen.service.v3.PetStoreService;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */
public class ApplicationContextTestV3 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(1, petStore.getVersion());
    }

    @Test
    public void testGetAnotherBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v3.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore2");
        Assert.assertNotNull(petStore.getAccountDao());
        Assert.assertNotNull(petStore.getItemDao());
        Assert.assertEquals(-1, petStore.getVersion());
    }

}
