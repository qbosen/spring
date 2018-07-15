package com.abosen.beans.factory.xml;

import com.abosen.beans.BeanDefinition;
import com.abosen.beans.PropertyValue;
import com.abosen.beans.factory.BeanDefinitionStoreException;
import com.abosen.beans.factory.config.RuntimeBeanReference;
import com.abosen.beans.factory.config.TypedStringValue;
import com.abosen.beans.factory.support.BeanDefinitionRegistry;
import com.abosen.beans.factory.support.GenericBeanDefinition;
import com.abosen.core.io.Resource;
import com.abosen.utils.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.spi.LoggerFactory;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;

/**
 * @author qiubaisen
 * @date 2018/7/10
 */
public class XmlBeanDefinitionReader {

    private static final String ID_ATTRIBUTE = "id";
    private static final String CLASS_ATTRIBUTE = "class";
    private static final String SCOPE_ATTRIBUTE = "scope";
    private static final String PROPERTY_ELEMENT = "property";
    private static final String REF_ATTRIBUTE = "ref";
    private static final String VALUE_ATTRIBUTE = "value";
    public static final String NAME_ATTRIBUTE = "name";


    private BeanDefinitionRegistry registry;

    private final Log logger = LogFactory.getLog(getClass());

    public XmlBeanDefinitionReader(BeanDefinitionRegistry registry) {
        this.registry = registry;
    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = null;

        try {
            inputStream = resource.getInputStream();
            SAXReader saxReader = new SAXReader();
            Document document = saxReader.read(inputStream);
            Element rootElement = document.getRootElement();
            Iterator<Element> iterator = rootElement.elementIterator();
            while (iterator.hasNext()) {
                Element element = iterator.next();
                String beanId = element.attributeValue(ID_ATTRIBUTE);
                String beanClassName = element.attributeValue(CLASS_ATTRIBUTE);
                String scope = element.attributeValue(SCOPE_ATTRIBUTE);
                BeanDefinition beanDefinition = new GenericBeanDefinition(beanId, beanClassName);
                beanDefinition.setScope(scope == null ? BeanDefinition.SCOPE_DEFAULT : scope);
                parsePropertyElement(element, beanDefinition);
                this.registry.registryBeanDefinition(beanId, beanDefinition);
            }
            inputStream.close();
        } catch (Exception e) {
            throw new BeanDefinitionStoreException("IOException parsing XML document from " + resource.getDescription(), e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void parsePropertyElement(Element element, BeanDefinition beanDefinition) {
        Iterator iterator = element.elementIterator(PROPERTY_ELEMENT);
        while (iterator.hasNext()) {
            Element propElem = (Element) iterator.next();
            String propertyName = propElem.attributeValue(NAME_ATTRIBUTE);
            if (!StringUtils.hasLength(propertyName)) {
                logger.fatal("Tag 'property' must have a 'name' attribute");
                return;
            }
            Object value = parsePropertyValue(propElem, propertyName);
            PropertyValue propertyValue = new PropertyValue(propertyName, value);
            beanDefinition.getPropertyValues().add(propertyValue);
        }
    }


    public Object parsePropertyValue(Element element, String propertyName) {
        String elementName = (propertyName != null) ?
                "<property> element for property '" + propertyName + "'" :
                "<constructor-arg> element";
        boolean hasRefAttribute = (element.attribute(REF_ATTRIBUTE) != null);
        boolean hasValueAttribute = (element.attribute(VALUE_ATTRIBUTE) != null);
        if (hasRefAttribute) {
            String refName = element.attributeValue(REF_ATTRIBUTE);
            if (!StringUtils.hasText(refName)) {
                logger.error(elementName + " contains empty 'ref' attribute");
            }
            RuntimeBeanReference ref = new RuntimeBeanReference(refName);
            return ref;
        } else if (hasValueAttribute) {
            TypedStringValue valueHolder = new TypedStringValue(element.attributeValue(VALUE_ATTRIBUTE));

            return valueHolder;
        } else {

            throw new RuntimeException(elementName + " must specify a ref or value");
        }
    }
}
