package com.springframe.beans;

import com.springframe.beans.beans.Youkino;
import com.springframe.beans.common.CustomBeanFactoryPostProcessor;
import com.springframe.beans.support.DefaultListableBeanFactory;
import com.springframe.beans.xml.XmlAbstractBeanDefinitionReader;
import org.junit.Test;

public class BeanFactoryPostProcessorTest {

    @Test
    public void TestBeanFactoryPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlAbstractBeanDefinitionReader xmlAbstractBeanDefinitionReader = new XmlAbstractBeanDefinitionReader(beanFactory);
        xmlAbstractBeanDefinitionReader.LoadBeanDefinitions("classpath:spring.xml");

        CustomBeanFactoryPostProcessor customBeanFactoryPostProcessor = new CustomBeanFactoryPostProcessor();
        customBeanFactoryPostProcessor.postProcessorBeanFactory(beanFactory);

        Youkino youkino = (Youkino)beanFactory.getBean("youkino");
        System.out.println(youkino.getName());
    }

}
