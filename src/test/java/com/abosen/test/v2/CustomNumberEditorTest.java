package com.abosen.test.v2;

import com.abosen.beans.propertyeditors.CustomNumberEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/15
 */
public class CustomNumberEditorTest {
    @Test
    public void testConvertString() {
        CustomNumberEditor editor = new CustomNumberEditor(Integer.class, true);
        editor.setAsText("3");
        Object value = editor.getValue();

        Assert.assertTrue(value instanceof Integer);
        Assert.assertEquals(3, ((Integer) value).intValue());

        editor.setAsText("");
        Assert.assertNull(editor.getValue());

        try {
            editor.setAsText("3.1");
        } catch (IllegalArgumentException e) {
            return;
        }

        Assert.fail();
    }
}
