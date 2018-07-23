package com.abosen.core.type.classreading;

import com.abosen.core.annotation.AnnotationAttributes;
import com.abosen.core.type.AnnotationMetadata;
import org.springframework.asm.AnnotationVisitor;
import org.springframework.asm.SpringAsmInfo;

import java.util.Map;

/**
 * @author qiubaisen
 * @date 2018/7/23
 */
public class AnnotationAttributesReadingVisitor extends AnnotationVisitor {
    private final String annotationType;
    private final Map<String, AnnotationAttributes> attributesMap;

    private final AnnotationAttributes annotationAttributes = new AnnotationAttributes();

    public AnnotationAttributesReadingVisitor(String annotationType, Map<String, AnnotationAttributes> attributesMap) {
        super(SpringAsmInfo.ASM_VERSION);
        this.annotationType = annotationType;
        this.attributesMap = attributesMap;
    }

    @Override
    public void visitEnd() {
        this.attributesMap.put(annotationType, annotationAttributes);
    }

    @Override
    public void visit(String attributeName, Object attributeValue) {
        this.annotationAttributes.put(attributeName, attributeValue);
    }
}
