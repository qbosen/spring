package com.abosen.context.annotation;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.BeanNameGenerator;
import com.abosen.beans.factory.annotation.AnnotatedBeanDefinition;
import com.abosen.beans.factory.support.BeanDefinitionRegistry;
import com.abosen.core.annotation.AnnotationAttributes;
import com.abosen.core.type.AnnotationMetadata;
import com.abosen.utils.ClassUtils;
import com.abosen.utils.StringUtils;

import java.beans.Introspector;
import java.util.Set;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class AnnotationBeanNameGenerator implements BeanNameGenerator {
    @Override
    public String generateBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        if (definition instanceof AnnotatedBeanDefinition) {
            String beanName = determineBeanNameFromAnnotation((AnnotatedBeanDefinition) definition);
            if (StringUtils.hasText(beanName)) {
                return beanName;
            }
        }
        return buildDefaultBeanName(definition, registry);
    }


    /**
     * Derive a bean name from one of the annotations on the class.
     * @param annotationBeanDefinition the annotation-aware bean definition
     * @return the bean name, or {@code null} if none is found
     */
    protected String determineBeanNameFromAnnotation(AnnotatedBeanDefinition annotationBeanDefinition) {
        AnnotationMetadata metadata = annotationBeanDefinition.getMetadata();
        Set<String> annotationTypes = metadata.getAnnotationTypes();
        String beanName = null;
        for (String type : annotationTypes) {
            AnnotationAttributes attributes = metadata.getAnnotationAttributes(type);
            if (attributes.get("value")!=null) {
                Object value = attributes.get("value");
                if (value instanceof String) {
                    String strVal = (String) value;
                    if (StringUtils.hasLength(strVal)) {
                        beanName = strVal;
                    }
                }
            }
        }
        return beanName;
    }

    /**
     * Derive a default bean name from the given bean definition.
     * <p>The default implementation delegates to {@link #buildDefaultBeanName(BeanDefinition)}.
     * @param definition the bean definition to build a bean name for
     * @param registry the registry that the given bean definition is being registered with
     * @return the default bean name (never {@code null})
     */
    protected String buildDefaultBeanName(BeanDefinition definition, BeanDefinitionRegistry registry) {
        return buildDefaultBeanName(definition);
    }

    /**
     * Derive a default bean name from the given bean definition.
     * <p>The default implementation simply builds a decapitalized version
     * of the short class name: e.g. "mypackage.MyJdbcDao" -> "myJdbcDao".
     * <p>Note that inner classes will thus have names of the form
     * "outerClassName.InnerClassName", which because of the period in the
     * name may be an issue if you are autowiring by name.
     * @param definition the bean definition to build a bean name for
     * @return the default bean name (never {@code null})
     */
    protected String buildDefaultBeanName(BeanDefinition definition) {
        String shortClassName = ClassUtils.getShortName(definition.getBeanClassName());
        return Introspector.decapitalize(shortClassName);
    }
}

