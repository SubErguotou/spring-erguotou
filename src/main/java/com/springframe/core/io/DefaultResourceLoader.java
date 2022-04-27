package com.springframe.core.io;

import java.net.MalformedURLException;
import java.net.URL;

public class DefaultResourceLoader implements ResourceLoader{
    public static final String CLASSPATH_URL_PREFIX = "classpath:";

    @Override
    public Resource getResource(String location) {
//        startsWith: 检查字符前缀
//        当作classpath下的资源处理
        if(location.startsWith(CLASSPATH_URL_PREFIX)){
            return new ClassPathResource(location.substring(CLASSPATH_URL_PREFIX.length()));
        }else {
            try {
//                当作URL处理
                return new UrlResource(new URL(location));

            } catch (MalformedURLException ex) {
                String path = location;
//                去除前缀/
                if (path.startsWith("/")){
                    path = location.substring(1);
                }
                return new FileSystemResource(path);
            }
        }
    }
}
