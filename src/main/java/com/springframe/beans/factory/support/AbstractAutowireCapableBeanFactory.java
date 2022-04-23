package com.springframe.beans.factory.support;

import com.springframe.BeansException;
import com.springframe.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{
    @Override
    protected Object creatBean(String BeanName, BeanDefinition beanDefinition) {
        return doCreateBean(BeanName, beanDefinition);
    }

//    使用beanDefinition来创建bean
    protected Object doCreateBean(String BeanName, BeanDefinition beanDefinition){

        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
//            利用反射创建bena
            bean = beanClass.newInstance();
        } catch (Exception e) {
            throw new BeansException("newInstance创建bean失败", e);
        }
//        将创建好的bean加入单例池
        addSingletonBean(BeanName, bean);
        return bean;
    }

}
