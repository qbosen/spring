package com.abosen.test.v1;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.*;

/**
 * @author qiubaisen
 * @date 2018/7/11
 */
public class ResourceTest {

    private InputStream inputStream;

    @Before
    public void setUp() {
        inputStream = null;
    }

    @After
    public void setDown() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testClassPathResource() throws IOException {
        Resource resource = new ClassPathResource("file.txt");
        inputStream = resource.getInputStream();
        assertNotNull(inputStream);

    }

    @Test
    public void testFilePathResource() throws IOException {
        Resource resource = new FileSystemResource("/Users/qiubaisen/Documents/spring/core/src/test/resources/file.txt");
        inputStream = resource.getInputStream();
        assertNotNull(inputStream);
    }
}