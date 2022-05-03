package com.springframe.beans;

import com.springframe.beans.beans.TomoriNao;
import com.springframe.beans.beans.Youkino;
import com.springframe.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class beanInitAndDisTest {

    @Test
    public void beanInitAndDis(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:init-and-destroy.xml");
        classPathXmlApplicationContext.registerShutdownHook();
        TomoriNao tomoriNao = classPathXmlApplicationContext.getBean("tomoriNao", TomoriNao.class);
        System.out.println(tomoriNao.getName());


    }
}
