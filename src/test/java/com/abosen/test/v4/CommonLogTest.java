package com.abosen.test.v4;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/22
 */
public class CommonLogTest {
    private final Log logger = LogFactory.getLog(CommonLogTest.class);

    @Test
    public void logTests() {
        logger.debug("[1]-my level is DEBUG");
        logger.info("[2]-my level is INFO");
        logger.warn("[3]-my level is WARN");
        logger.error("[4]-my level is ERROR");
    }
}
