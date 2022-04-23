package com.springframe.beans.factory.support;

import com.springframe.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry{

//    BeanDefinition(Bean定义信息池)池
    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    protected BeanDefinition getBeanDefinition(String BeanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(BeanName);
        return beanDefinition;
    }
}
