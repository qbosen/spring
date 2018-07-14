package com.abosen.context.support;

import com.abosen.test.v1.ClassPathResource;
import com.abosen.test.v1.FileSystemResource;
import com.abosen.test.v1.Resource;

/**
 * @author qiubaisen
 * @date 2018/7/12
 */
public class FileSystemXmlApplicationContext extends AbstractApplicationContext {
    public FileSystemXmlApplicationContext(String configFile) {
        super(configFile);
    }

    @Override
    protected Resource getResourceByPath(String path) {
        return new FileSystemResource(path);
    }
}
