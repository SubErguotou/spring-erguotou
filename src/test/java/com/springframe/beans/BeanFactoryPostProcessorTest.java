package com.springframe.beans;

import com.springframe.beans.beans.TomoriNao;
import com.springframe.beans.beans.Youkino;
import com.springframe.beans.common.CustomBeanFactoryPostProcessor;
import com.springframe.beans.common.CustomBeanPostProcessor;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import com.springframe.beans.factory.xml.XmlAbstractBeanDefinitionReader;
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

    @Test
    public void TestBeanPostProcessor(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlAbstractBeanDefinitionReader xmlAbstractBeanDefinitionReader = new XmlAbstractBeanDefinitionReader(beanFactory);
        xmlAbstractBeanDefinitionReader.LoadBeanDefinitions("classpath:spring.xml");

        CustomBeanPostProcessor customBeanPostProcessor = new CustomBeanPostProcessor();
        beanFactory.addBeanPostProcessor(customBeanPostProcessor);

        TomoriNao tomoriNao = (TomoriNao)beanFactory.getBean("tomoriNao");
        System.out.println(tomoriNao.getName());
    }

}
