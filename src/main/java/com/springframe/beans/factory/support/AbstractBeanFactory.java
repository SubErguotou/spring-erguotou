package com.springframe.beans.factory.support;

import com.springframe.beans.factory.BeanFactory;
import com.springframe.beans.factory.config.BeanDefinition;

/**
 * 实现了BeanFactory，继承了DefaultSingletonBeanRegistry
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
//        检查池中是存在BeanDefinition
        Object bean = getSingleton(beanName);
        if (bean != null){
            return bean;
        }
//        没有则创建beanDefinition
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return creatBean(beanName, beanDefinition);
    }
//    创建单例bean
    protected abstract Object creatBean(String BeanName, BeanDefinition beanDefinition);

//    获得BeanDefinition
    protected abstract BeanDefinition getBeanDefinition(String BeanName);
}
