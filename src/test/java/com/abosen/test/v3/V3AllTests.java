package com.abosen.test.v3;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author qiubaisen
 * @date 2018/7/19
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ApplicationContextTestV3.class,
        BeanDefinitionTestV3.class,
        ConstructorResolverTest.class,
})
public class V3AllTests {
}
