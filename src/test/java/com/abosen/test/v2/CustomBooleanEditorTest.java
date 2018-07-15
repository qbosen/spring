package com.abosen.test.v2;

import com.abosen.beans.propertyeditors.CustomBooleanEditor;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author qiubaisen
 * @date 2018/7/16
 */
public class CustomBooleanEditorTest {
    @Test
    public void testConvertStringToBoolean() {

        CustomBooleanEditor editor = new CustomBooleanEditor(true);
        editor.setAsText("true");
        Assert.assertEquals(true, editor.getValue());
        editor.setAsText("false");
        Assert.assertEquals(false, editor.getValue());
        editor.setAsText("on");
        Assert.assertEquals(true, editor.getValue());
        editor.setAsText("off");
        Assert.assertEquals(false, editor.getValue());
        editor.setAsText("yes");
        Assert.assertEquals(true, editor.getValue());
        editor.setAsText("no");
        Assert.assertEquals(false, editor.getValue());

        try {
            editor.setAsText("aabbcc");
        } catch (IllegalArgumentException e) {
            return;
        }
        Assert.fail();

    }
}
