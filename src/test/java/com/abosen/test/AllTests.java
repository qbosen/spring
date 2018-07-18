package com.abosen.test;

import com.abosen.test.v1.V1AllTests;
import com.abosen.test.v2.V2AllTests;
import com.abosen.test.v3.V3AllTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        V1AllTests.class,
        V2AllTests.class,
        V3AllTests.class,

})
public class AllTests {
}
