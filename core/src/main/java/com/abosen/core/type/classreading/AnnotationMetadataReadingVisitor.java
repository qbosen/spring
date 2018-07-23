package com.abosen.core.type.classreading;

import com.abosen.core.annotation.AnnotationAttributes;
import com.abosen.core.type.AnnotationMetadata;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.Type;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author qiubaisen
 * @date 2018/7/23
 */
public class AnnotationMetadataReadingVisitor extends ClassMetadataReadingVisitor implements AnnotationMetadata {

    // 所有的注解全量名
    private final Set<String> annotationSet = new LinkedHashSet<>(4);
    // 每一个注解的属性字典
    private final Map<String, AnnotationAttributes> attributesMap = new LinkedHashMap<>(4);


    @Override
    public AnnotationVisitor visitAnnotation(final String description, boolean visible) {
        String className = Type.getType(description).getClassName();
        this.annotationSet.add(className);
        return new AnnotationAttributesReadingVisitor(className, this.attributesMap);
    }

    public Set<String> getAnnotationTypes() {
        return this.annotationSet;
    }

    public boolean hasAnnotation(String annotationType) {
        return this.annotationSet.contains(annotationType);
    }

    public AnnotationAttributes getAnnotationAttributes(String annotationType) {
        return this.attributesMap.get(annotationType);
    }
}
