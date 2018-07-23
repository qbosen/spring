package com.abosen.test.v4;

import com.abosen.core.annotation.AnnotationAttributes;
import com.abosen.core.io.ClassPathResource;
import com.abosen.core.type.AnnotationMetadata;
import com.abosen.core.type.ClassMetadata;
import com.abosen.core.type.classreading.MetadataReader;
import com.abosen.core.type.classreading.SimpleMetadataReader;
import com.abosen.stereotype.Component;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class MetadataReaderTest {
    @Test
    public void testGetMetadata() throws IOException {
        ClassPathResource resource = new ClassPathResource("com/abosen/service/v4/PetStoreService.class");

        MetadataReader reader = new SimpleMetadataReader(resource);
//        注意：不需要单独使用ClassMetadata
//        ClassMetadata clzMetadata = reader.getClassMetadata();
        AnnotationMetadata annotationMetadata = reader.getAnnotationMetadata();

        String annotation = Component.class.getName();

        Assert.assertTrue(annotationMetadata.hasAnnotation(annotation));
        AnnotationAttributes attributes = annotationMetadata.getAnnotationAttributes(annotation);
        Assert.assertEquals("petStore", attributes.get("value"));

        //注：下面对class metadata的测试并不充分
        Assert.assertFalse(annotationMetadata.isAbstract());
        Assert.assertFalse(annotationMetadata.isFinal());
        Assert.assertEquals("com.abosen.service.v4.PetStoreService", annotationMetadata.getClassName());
    }
}