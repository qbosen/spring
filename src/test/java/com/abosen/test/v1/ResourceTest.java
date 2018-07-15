package com.abosen.test.v1;

import com.abosen.core.io.ClassPathResource;
import com.abosen.core.io.FileSystemResource;
import com.abosen.core.io.Resource;
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
        Resource resource = new ClassPathResource("petstore-v1.xml");
        inputStream = resource.getInputStream();
        assertNotNull(inputStream);

    }

    @Test
    public void testFilePathResource() throws IOException {
        Resource resource = new FileSystemResource("src/test/resources/petstore-v1.xml");
        inputStream = resource.getInputStream();
        assertNotNull(inputStream);
    }
}