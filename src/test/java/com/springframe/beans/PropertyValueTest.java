package com.springframe.beans;

import com.springframe.beans.config.BeanDefinition;
import com.springframe.beans.support.DefaultListableBeanFactory;
import com.springframe.beans.beans.TomoriNao;
import com.springframe.beans.beans.Youkino;
import org.junit.Test;

public class PropertyValueTest {
    @Test
    public void testPropertyValue(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();

        PropertyValues propertyValues = new PropertyValues();
        PropertyValue propertyValue1 = new PropertyValue("name", "雪乃");
        PropertyValue propertyValue2 = new PropertyValue("age", "18");
        propertyValues.addPropertyValue(propertyValue1);
        propertyValues.addPropertyValue(propertyValue2);
        BeanDefinition beanDefinitionY = new BeanDefinition(Youkino.class, propertyValues);
        beanFactory.registerBeanDefinition("Youkino", beanDefinitionY);
        Youkino youkino = (Youkino)beanFactory.getBean("Youkino");
        System.out.println(youkino.getAge());
        System.out.println(youkino.getName());


        propertyValues.addPropertyValue(new PropertyValue("name", "有利奈绪"));
        propertyValues.addPropertyValue(new PropertyValue("age", "17"));
        BeanDefinition beanDefinitionT = new BeanDefinition(TomoriNao.class, propertyValues);
        beanFactory.registerBeanDefinition("TomoriNao", beanDefinitionT);
        TomoriNao tomoriNao = (TomoriNao)beanFactory.getBean("TomoriNao");
        System.out.println(tomoriNao.getName());
        System.out.println(tomoriNao.getAge());

//        从Youkino的定义信息中获取属性池
//        PropertyValues propertyValuesY = beanDefinitionY.getPropertyValues();
//        从TomoriNao的定义信息中获取属性池
//        PropertyValues propertyValuesT = beanDefinitionT.getPropertyValues();
        /**
         *  比较两个两个属性池的内存地址，结果为true，两个不同类型的对象共用一个属性池
         */
//        System.out.println(propertyValuesY == propertyValuesT);
//
//        for (PropertyValue pv1 : propertyValuesY.getPropertyValues()){
//            System.out.println(pv1.value);
//        }
//        System.out.println("-----------------------");
//        for (PropertyValue pv2 : propertyValuesT.getPropertyValues()){
//            System.out.println(pv2.value);
//        }
//        boolean equals = propertyValuesY.equals(propertyValuesT);
//        System.out.println(equals);
    }

    @Test
    public void TestPropertyValues(){
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        PropertyValues propertyValues = new PropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("name", "友利奈绪"));
        propertyValues.addPropertyValue(new PropertyValue("age", "18"));
        /**
         *    这两行在通过反射对属性赋值时，会把前面的覆盖掉，导致创建几个对象属性的name都是雪乃，age属性都是17
         */
        propertyValues.addPropertyValue(new PropertyValue("name", "雪乃"));
        propertyValues.addPropertyValue(new PropertyValue("age", "17"));

        BeanDefinition beanDefinitionT = new BeanDefinition(TomoriNao.class, propertyValues);

        beanFactory.registerBeanDefinition("TomoriNao", beanDefinitionT);

        TomoriNao tomoriNao = (TomoriNao)beanFactory.getBean("TomoriNao");
        System.out.println(tomoriNao.getName());
        System.out.println(tomoriNao.getAge());

        BeanDefinition beanDefinitionY = new BeanDefinition(Youkino.class, propertyValues);
        beanFactory.registerBeanDefinition("Youkino", beanDefinitionY);
        Youkino youkino = (Youkino) beanFactory.getBean("Youkino");
        System.out.println(youkino.getName());
        System.out.println(youkino.getAge());

    }
}
