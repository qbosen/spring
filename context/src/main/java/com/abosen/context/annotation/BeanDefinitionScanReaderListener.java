package com.abosen.context.annotation;

import com.abosen.beans.event.BeanDefinitionReaderEvent;
import com.abosen.beans.event.BeanDefinitionReaderListener;
import com.abosen.beans.event.EventManager;
import com.abosen.beans.factory.support.BeanDefinitionRegistry;

import java.util.EventObject;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class BeanDefinitionScanReaderListener implements BeanDefinitionReaderListener {
    public BeanDefinitionScanReaderListener() {
        EventManager.addSupportType(BeanDefinitionReaderEvent.class, BeanDefinitionReaderListener.class);
    }

    @Override
    public void action(EventObject eventObject) {
        if (eventObject instanceof BeanDefinitionReaderEvent) {
            BeanDefinitionReaderEvent event = (BeanDefinitionReaderEvent) eventObject;
            BeanDefinitionRegistry registry = event.getSource();
            ClassPathBeanDefinitionScanner classPathBeanDefinitionScanner = new ClassPathBeanDefinitionScanner(registry);
            classPathBeanDefinitionScanner.doScan(event.getBasePackages());
        }
    }
}
