package com.abosen.test.v4;

import com.abosen.beans.event.EventManager;
import com.abosen.context.annotation.BeanDefinitionScanReaderListener;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class EventManagerTest {

    @Test
    public void testOperateListener() {
        BeanDefinitionScanReaderListener scannerListener = new BeanDefinitionScanReaderListener();
        EventManager.addListener(scannerListener);
        assertEquals(1, EventManager.getListenerCount());

        EventManager.addListener(scannerListener);
        assertEquals(2, EventManager.getListenerCount());

        EventManager.removeListener(scannerListener);
        assertEquals(1, EventManager.getListenerCount());

        EventManager.addListener(eventObject -> {});
        assertEquals(1, EventManager.getListenerCount());

        EventManager.clear();
        assertEquals(0, EventManager.getListenerCount());
    }
}
