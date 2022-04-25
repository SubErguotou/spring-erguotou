package com.springframe.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import com.springframe.BeansException;
import com.springframe.beans.factory.PropertyValue;
import com.springframe.beans.factory.config.BeanDefinition;

public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory{

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object creatBean(String BeanName, BeanDefinition beanDefinition) {
        return doCreateBean(BeanName, beanDefinition);
    }

//    使用beanDefinition来创建bean
    protected Object doCreateBean(String BeanName, BeanDefinition beanDefinition){

        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
//            创建bean实例
            bean = creatBeanInstantiation(beanDefinition);
//            为创建好的bean添加属性
            addPropertyValues(BeanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("newInstance创建bean失败", e);
        }
//        将创建好的bean加入单例池
        addSingletonBean(BeanName, bean);
        return bean;
    }
    protected Object creatBeanInstantiation(BeanDefinition beanDefinition){
        return instantiationStrategy.instantiate(beanDefinition);
    }

    protected void addPropertyValues(String BeanName, Object bean, BeanDefinition beanDefinition){
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
//                System.out.println(name+": " +value);
                //通过反射设置属性, 为bean实例里name的属性设置vlaue，相同的name会被后来的name值覆盖
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e){
            new BeansException(BeanName + "设置属性错误 ", e);
        }
    }


    protected InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
    protected void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
