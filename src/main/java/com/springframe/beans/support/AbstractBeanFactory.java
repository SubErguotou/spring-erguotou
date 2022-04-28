package com.springframe.beans.support;

import com.springframe.beans.config.BeanDefinition;
import com.springframe.beans.BeanFactory;

/**
 * 实现了BeanFactory，继承了DefaultSingletonBeanRegistry
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {

    @Override
    public Object getBean(String beanName) {
//        检查单例对象池中是存在BeanDefinition
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
