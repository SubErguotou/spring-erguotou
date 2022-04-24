package com.springframe.beans.factory;

import com.springframe.beans.factory.beans.Youkino;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import org.junit.Test;

public class PropertyValueTest {
    @Test
    public void testPropertyValue(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue1 = new PropertyValue("name", "Youkino");
        PropertyValue propertyValue2 = new PropertyValue("age", "18");
        propertyValues.addPropertyValue(propertyValue1);
        propertyValues.addPropertyValue(propertyValue2);

        BeanDefinition beanDefinition = new BeanDefinition(Youkino.class, propertyValues);

        beanFactory.registerBeanDefinition("Youkino", beanDefinition);

        Youkino youkino = (Youkino)beanFactory.getBean("Youkino");
        System.out.println(youkino.getAge());
        System.out.println(youkino.getName());
    }
}
