package com.springframe.beans.factory;


import com.springframe.beans.factory.beans.Youkino;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class BeanFactoryTest {

    @Test
    public void TestBeanFactory(){
//        创建IOC容器
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
//        创建bean的定义
        BeanDefinition beanDefinition = new BeanDefinition(Youkino.class);
//        把bean的定义信息注册进Bean工厂
        beanFactory.registerBeanDefinition("Youkino", beanDefinition);
//        获得bean
        Youkino youkino = (Youkino)beanFactory.getBean("Youkino");

        youkino.getInfo();
    }

}
