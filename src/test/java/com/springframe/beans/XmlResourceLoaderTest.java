package com.springframe.beans;

import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import com.springframe.beans.factory.xml.XmlAbstractBeanDefinitionReader;
import com.springframe.beans.beans.Youkino;
import com.springframe.core.io.DefaultResourceLoader;
import org.junit.Test;

public class XmlResourceLoaderTest {

    @Test
    public void XmlTest(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        // 使用xml解析资源
        XmlAbstractBeanDefinitionReader xmlAbstractBeanDefinitionReader = new XmlAbstractBeanDefinitionReader(beanFactory, new DefaultResourceLoader());
        // 从classpath:spring.xml里装载bean的定义信息
        xmlAbstractBeanDefinitionReader.LoadBeanDefinitions("classpath:spring.xml");

        Youkino youkino = (Youkino)beanFactory.getBean("youkino");
        System.out.println(youkino.getName());
        System.out.println(youkino.getTomoriNao().getName());
    }
}
