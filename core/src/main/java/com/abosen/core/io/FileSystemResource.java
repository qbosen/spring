package com.abosen.core.io;

import com.abosen.utils.Assert;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * @author qiubaisen
 * @date 2018/7/1
 */
public class FileSystemResource implements Resource {

    private final String filePath;
    private final File file;

    public FileSystemResource(String filePath) {
        Assert.notNull(filePath, "path must not be null");
        this.filePath = filePath;
        this.file = new File(filePath);
    }

    @Override
    public InputStream getInputStream() throws FileNotFoundException {
        return new FileInputStream(file);
    }

    @Override
    public String getDescription() {
        return "file [" + this.file.getAbsolutePath() + "]";
    }
}
