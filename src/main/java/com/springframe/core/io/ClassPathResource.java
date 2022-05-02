package com.springframe.core.io;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

public class ClassPathResource implements Resource{

    private final String path;

    public ClassPathResource(String path) {
        this.path = path;
    }

    @Override
    public InputStream getInputStream() throws IOException {
//        通过获得当前对象的类装载器，通过类装载器读取配置文件，获取资源文件的字节流
        InputStream is = this.getClass().getClassLoader().getResourceAsStream(this.path);
        if (is == null){
            throw new FileNotFoundException(this.path + "文件打开失败");
        }
        return is;
    }
}
