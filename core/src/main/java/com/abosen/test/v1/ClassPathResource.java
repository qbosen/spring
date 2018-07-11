package com.abosen.test.v1;

import com.abosen.utils.ClassUtils;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class ClassPathResource implements Resource {

    private final String filePath;
    private final ClassLoader classLoader;

    public ClassPathResource(String filePath) {
        this(filePath, null);
    }

    public ClassPathResource(String filePath, ClassLoader classLoader) {
        this.filePath = filePath;
        this.classLoader = classLoader != null ? classLoader : ClassUtils.getDefaultClassLoader();
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        InputStream resourceAsStream = this.classLoader.getResourceAsStream(filePath);
        if (resourceAsStream == null) {
            throw new FileNotFoundException(filePath + " can not be found");
        }
        return resourceAsStream;
    }

    @Override
    public String getDescription() {
        return this.filePath;
    }
}
