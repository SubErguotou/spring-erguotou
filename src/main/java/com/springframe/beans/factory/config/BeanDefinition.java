package com.springframe.beans.factory.config;

/**
 * 用于定义bean的定义信息的类，包含bean的class类型、构造参数、属性值等信息，每个bean对应一个BeanDefinition的实例
 */

public class BeanDefinition {
    private Class BeanClass;

    public BeanDefinition(Class beanClass){
        this.BeanClass = beanClass;
    }
    public Class getBeanClass() {
        return this.BeanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.BeanClass = beanClass;
    }
}
