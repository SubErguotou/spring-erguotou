package com.springframe.beans.factory.support;

import com.springframe.beans.BeansException;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.config.BeanPostProcessor;
import com.springframe.beans.factory.config.ConfigurableBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * 实现了BeanFactory，继承了DefaultSingletonBeanRegistry
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements ConfigurableBeanFactory {
    // 前后置处理器池
    private final List<BeanPostProcessor> beanPostProcessors = new ArrayList<>();
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

    @Override
    public <T> T getBean(String name, Class<T> requiredType) throws BeansException {
        return (T) getBean(name);
    }

    //    创建单例bean
    protected abstract Object creatBean(String BeanName, BeanDefinition beanDefinition);

//    获得BeanDefinition
    protected abstract BeanDefinition getBeanDefinition(String BeanName);

    //添加前后置处理器具体是实现
    @Override
    public void addBeanPostProcessor(BeanPostProcessor beanPostProcessor) {
        this.beanPostProcessors.remove(beanPostProcessor);
        this.beanPostProcessors.add(beanPostProcessor);
    }
    // 获得所有前后置处理器
    public List<BeanPostProcessor> getBeanPostProcessors() {
        return this.beanPostProcessors;
    }
}
