package com.abosen.core.type.classreading;

import com.abosen.core.io.ClassPathResource;
import com.abosen.core.io.Resource;
import com.abosen.core.type.AnnotationMetadata;
import com.abosen.core.type.ClassMetadata;
import org.springframework.asm.ClassReader;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class SimpleMetadataReader implements MetadataReader {

    private final Resource resource;
    private final ClassMetadata classMetadata;
    private final AnnotationMetadata annotationMetadata;

    public SimpleMetadataReader(Resource resource) throws IOException {
        ClassReader classReader;
        try (InputStream inputStream = new BufferedInputStream(resource.getInputStream())) {
            classReader = new ClassReader(inputStream);
        }
        AnnotationMetadataReadingVisitor visitor = new AnnotationMetadataReadingVisitor();
        classReader.accept(visitor, ClassReader.SKIP_DEBUG);
        this.resource = resource;
        this.classMetadata = visitor;
        this.annotationMetadata = visitor;
    }

    @Override
    public Resource getResource() {
        return this.resource;
    }

    @Override
    public ClassMetadata getClassMetadata() {
        return this.classMetadata;
    }

    @Override
    public AnnotationMetadata getAnnotationMetadata() {
        return this.annotationMetadata;
    }
}
