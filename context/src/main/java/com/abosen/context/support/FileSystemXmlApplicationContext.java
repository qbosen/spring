package com.abosen.context.support;

import com.abosen.core.io.FileSystemResource;
import com.abosen.core.io.Resource;

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
