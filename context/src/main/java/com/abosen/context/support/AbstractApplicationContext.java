package com.abosen.context.support;

import com.abosen.beans.factory.support.DefaultBeanFactory;
import com.abosen.beans.factory.xml.XmlBeanDefinitionReader;
import com.abosen.context.ApplicationContext;
import com.abosen.core.io.Resource;
import com.abosen.utils.ClassUtils;

/**
 * @author qiubaisen
 * @date 2018/7/12
 */
public abstract class AbstractApplicationContext implements ApplicationContext {

    private DefaultBeanFactory factory = null;
    private ClassLoader beanClassLoader;

    public AbstractApplicationContext(String configFile) {
        factory = new DefaultBeanFactory();
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(factory);
        Resource resource = getResourceByPath(configFile);
        reader.loadBeanDefinitions(resource);
        factory.setBeanClassLoader(this.getBeanClassLoader());
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.beanClassLoader = classLoader;
    }

    @Override
    public ClassLoader getBeanClassLoader() {
        return this.beanClassLoader != null ? this.beanClassLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public Object getBean(String beanName) {
        return this.factory.getBean(beanName);
    }

    protected abstract Resource getResourceByPath(String path);
}
