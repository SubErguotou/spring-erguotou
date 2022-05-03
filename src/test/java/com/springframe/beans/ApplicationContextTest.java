package com.springframe.beans;

import com.springframe.beans.beans.TomoriNao;
import com.springframe.beans.beans.Youkino;
import com.springframe.context.support.ClassPathXmlApplicationContext;
import org.junit.Test;

public class ApplicationContextTest {
    @Test
    public void TestApplicationContext(){
        ClassPathXmlApplicationContext classPathXmlApplicationContext = new ClassPathXmlApplicationContext("classpath:spring.xml");
//        TomoriNao tomoriNao = classPathXmlApplicationContext.getBean("tomoriNao", TomoriNao.class);
//        System.out.println(tomoriNao.getName());
    }
}
