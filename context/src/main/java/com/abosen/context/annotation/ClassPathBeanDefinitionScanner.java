package com.abosen.context.annotation;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.BeanNameGenerator;
import com.abosen.beans.factory.BeanDefinitionStoreException;
import com.abosen.beans.factory.annotation.BeanDefinitionScanner;
import com.abosen.beans.factory.support.BeanDefinitionRegistry;
import com.abosen.core.io.Resource;
import com.abosen.core.io.support.PackageResourceLoader;
import com.abosen.core.type.classreading.MetadataReader;
import com.abosen.core.type.classreading.SimpleMetadataReader;
import com.abosen.stereotype.Component;
import com.abosen.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.Set;

/**
 * @author qiubaisen
 * @date 2018/7/24
 */
public class ClassPathBeanDefinitionScanner implements BeanDefinitionScanner {
    private final BeanDefinitionRegistry registry;
    private PackageResourceLoader resourceLoader = new PackageResourceLoader();
    protected final Log logger = LogFactory.getLog(getClass());
    private BeanNameGenerator beanNameGenerator = new AnnotationBeanNameGenerator();

    public ClassPathBeanDefinitionScanner(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public Set<BeanDefinition> doScan(String packageToScan) {
        String[] basePackages = StringUtils.tokenizeToStringArray(packageToScan, ",");
        Set<BeanDefinition> beanDefinitions = new LinkedHashSet<>();
        for (String basePackage : basePackages) {
            Set<BeanDefinition> candidates = findCandidateComponents(basePackage);
            for (BeanDefinition candidate : candidates) {
                beanDefinitions.add(candidate);
                registry.registryBeanDefinition(candidate.getId(),candidate);
            }
        }
        return beanDefinitions;
    }

    private Set<BeanDefinition> findCandidateComponents(String basePackage) {
        Set<BeanDefinition> candidates = new LinkedHashSet<>();
        try {
            Resource[] resources = this.resourceLoader.getResources(basePackage);

            for (Resource resource : resources) {
                try {
                    MetadataReader metadataReader = new SimpleMetadataReader(resource);
                    if (metadataReader.getAnnotationMetadata().hasAnnotation(Component.class.getName())) {
                        ScannedGenericBeanDefinition beanDefinition = new ScannedGenericBeanDefinition(metadataReader.getAnnotationMetadata());
                        String beanName = this.beanNameGenerator.generateBeanName(beanDefinition, this.registry);
                        beanDefinition.setId(beanName);
                        candidates.add(beanDefinition);
                    }
                } catch (IOException e) {
                    throw new BeanDefinitionStoreException(
                            "Failed to read candidate component class: " + resource, e);
                }
            }
        } catch (IOException e) {
            throw new BeanDefinitionStoreException("I/O failure during classpath scanning", e);
        }
        return candidates;
    }
}
