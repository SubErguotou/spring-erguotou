package com.springframe.core.io;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class FileSystemResource implements Resource{
    private final String filePath;

    public FileSystemResource(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        Path path = new File(this.filePath).toPath();
//        返回一个字节流
        return Files.newInputStream(path);
    }
}
