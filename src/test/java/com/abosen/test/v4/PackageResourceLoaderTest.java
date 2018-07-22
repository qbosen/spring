package com.abosen.test.v4;

import com.abosen.core.io.Resource;
import com.abosen.core.io.support.PackageResourceLoader;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/22
 */
public class PackageResourceLoaderTest {
    @Test
    public void testGetPackageResources() {
        PackageResourceLoader loader = new PackageResourceLoader();
        Resource[] resources = loader.getResources("com.abosen.dao.v4");
        Assert.assertEquals(2, resources.length);
    }


}
