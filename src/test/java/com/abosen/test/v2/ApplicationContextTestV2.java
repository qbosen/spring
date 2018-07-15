package com.abosen.test.v2;

import com.abosen.context.ApplicationContext;
import com.abosen.context.support.ClassPathXmlApplicationContext;
import com.abosen.dao.v2.AccountDao;
import com.abosen.dao.v2.ItemDao;
import com.abosen.service.v2.PetStoreService;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */
public class ApplicationContextTestV2 {

    @Test
    public void testGetBeanProperty() {
        ApplicationContext context = new ClassPathXmlApplicationContext("petstore-v2.xml");
        PetStoreService petStore = (PetStoreService) context.getBean("petStore");
        assertNotNull(petStore);
        assertNotNull(petStore.getAccountDao());
        assertNotNull(petStore.getItemDao());

        assertTrue(petStore.getAccountDao() instanceof AccountDao);
        assertTrue(petStore.getItemDao() instanceof ItemDao);
        assertEquals("Tom", petStore.getOwner());
        assertEquals(2, petStore.getVersion());
    }
}
