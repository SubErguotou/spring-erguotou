package com.springframe.beans;

import com.springframe.beans.factory.config.BeanReference;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import com.springframe.beans.beans.Youkino;
import org.junit.Test;

public class PopulateBeanWithPropertyValuesTest {
    @Test
    public void TestReference(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

//        注册TomoriNao
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "有利"));
        BeanDefinition beanDefinitionT = new BeanDefinition(Youkino.class, propertyValues);
        beanFactory.registerBeanDefinition("TomoriNao", beanDefinitionT);
//        Youkino tomoriNao = (Youkino)beanFactory.getBean("TomoriNao");
//        System.out.println(tomoriNao.getName());

//        注册Youkino
        PropertyValues propertyValuesY = new PropertyValues();
        propertyValuesY.addPropertyValue(new PropertyValue("name", "雪乃"));
        propertyValuesY.addPropertyValue(new PropertyValue("age", "18"));

//       依赖TomoriNao
        propertyValuesY.addPropertyValue(new PropertyValue("tomoriNao", new BeanReference("TomoriNao")));
        BeanDefinition beanDefinitionY = new BeanDefinition(Youkino.class, propertyValuesY);

        beanFactory.registerBeanDefinition("Youkino", beanDefinitionY);
        Youkino youkino = (Youkino)beanFactory.getBean("Youkino");
        System.out.println(youkino.getTomoriNao().getName());

    }
}
