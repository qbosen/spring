package com.abosen.test.v2;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV2.class,
        BeanDefinitionTestV2.class,
        BeanDefinitionValueResolverTest.class,
        CustomBooleanEditorTest.class,
        CustomNumberEditorTest.class,
        TypeConverterTest.class,
})
public class V2AllTests {
}
