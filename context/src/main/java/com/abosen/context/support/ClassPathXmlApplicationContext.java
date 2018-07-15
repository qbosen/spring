package com.abosen.context.support;

import com.abosen.core.io.ClassPathResource;
import com.abosen.core.io.Resource;

/**
 * @author qiubaisen
 * @date 2018/7/12
 */
public class ClassPathXmlApplicationContext extends AbstractApplicationContext {
    public ClassPathXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new ClassPathResource(path, this.getBeanClassLoader());
    }
}
