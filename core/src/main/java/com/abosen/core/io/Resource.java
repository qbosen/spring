package com.abosen.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public interface Resource {
    InputStream getInputStream() throws IOException;

    String getDescription();
}
