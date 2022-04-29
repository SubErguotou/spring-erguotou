package com.springframe.beans;

import com.springframe.beans.beans.Youkino;
import com.springframe.beans.support.BeanDefinitionReader;
import com.springframe.beans.support.DefaultListableBeanFactory;
import com.springframe.beans.xml.XmlAbstractBeanDefinitionReader;
import com.springframe.core.io.ClassPathResource;
import com.springframe.core.io.DefaultResourceLoader;
import org.junit.Test;

public class XmlResourceLoaderTest {

    @Test
    public void XmlTest(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlAbstractBeanDefinitionReader xmlAbstractBeanDefinitionReader = new XmlAbstractBeanDefinitionReader(beanFactory);
        xmlAbstractBeanDefinitionReader.LoadBeanDefinitions("classpath:spring.xml");

        Youkino youkino = (Youkino)beanFactory.getBean("youkino");
        System.out.println(youkino.getName());
    }
}
