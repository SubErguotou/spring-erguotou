package com.springframe.beans.common;

import cn.hutool.core.bean.BeanException;
import com.springframe.beans.ConfigurableListableBeanFactory;
import com.springframe.beans.PropertyValue;
import com.springframe.beans.PropertyValues;
import com.springframe.beans.config.BeanDefinition;
import com.springframe.beans.config.BeanFactoryPostProcessor;

public class CustomBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException {
        BeanDefinition youkinoBeanDefinition = beanFactory.getBeanDefinition("youkino");
        PropertyValues propertyValues = youkinoBeanDefinition.getPropertyValues();

        // 更改youkino的bean的定义信息中name属性的值
        PropertyValue propertyValue = new PropertyValue("name", "雪乃");
        propertyValues.addPropertyValue(propertyValue);

    }
}
