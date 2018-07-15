package com.abosen.test.v2;

import com.abosen.beans.SimpleTypeConverter;
import com.abosen.beans.TypeConverter;
import com.abosen.beans.TypeMismatchException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */
public class TypeConverterTest {

    @Test
    public void testConvertStringToInt() {
        TypeConverter converter = new SimpleTypeConverter();
        Integer i = converter.convertIfNecessary("3", Integer.class);
        Assert.assertEquals(3, i.intValue());

        try {
            converter.convertIfNecessary("3.1", Integer.class);
        } catch (TypeMismatchException e) {
            return;
        }
        fail();
    }

    @Test
    public void testConvertStringToBoolean() {
        TypeConverter converter = new SimpleTypeConverter();
        Boolean b = converter.convertIfNecessary("true", Boolean.class);
        Assert.assertTrue(b);

        try {
            converter.convertIfNecessary("xxxyyyzzz", Boolean.class);
        } catch (TypeMismatchException e) {
            return;
        }
        fail();
    }

}
