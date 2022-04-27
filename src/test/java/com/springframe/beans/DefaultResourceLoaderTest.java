package com.springframe.beans;

import cn.hutool.core.io.IoUtil;
import com.springframe.core.io.DefaultResourceLoader;
import com.springframe.core.io.Resource;
import org.junit.Test;
import sun.misc.IOUtils;

import java.io.*;

public class DefaultResourceLoaderTest {
    @Test
    public void TestResourceLoader() throws IOException {
        DefaultResourceLoader defaultResourceLoader = new DefaultResourceLoader();

//        获取ClassPathResource下的资源
        Resource ClassResource = defaultResourceLoader.getResource("classpath:hello.txt");
        InputStream ClassinputStream = ClassResource.getInputStream();
        String Classcontext = IoUtil.readUtf8(ClassinputStream);
        System.out.println(Classcontext);

//        获取FileSystemResource下的资源
        Resource FileResource = defaultResourceLoader.getResource("D:\\code\\Java\\spring源码\\javaTest\\src\\main\\java\\image\\1647590345522.jpg");
        InputStream FileinputStream = FileResource.getInputStream();
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\code\\Java\\mini-spring源码\\erguotou-spring\\src\\test\\java\\com\\springframe\\beans\\image\\雪乃.jpg");
        int b;
        while ((b = FileinputStream.read()) != -1){
            fileOutputStream.write(b);
        }
        fileOutputStream.close();

//        获取URL下的资源
        Resource UrlResource = defaultResourceLoader.getResource("https://www.baidu.com/");
        InputStream UrlinputStream = UrlResource.getInputStream();
        FileOutputStream fileOutputStream1 = new FileOutputStream("baidu.html");
        IoUtil.copy(UrlinputStream, fileOutputStream1);
    }
}
