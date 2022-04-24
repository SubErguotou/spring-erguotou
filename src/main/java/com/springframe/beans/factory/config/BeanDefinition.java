package com.springframe.beans.factory.config;

import com.springframe.beans.factory.PropertyValues;

/**
 * 用于定义bean的定义信息的类，包含bean的class类型、构造参数、属性值等信息，每个bean对应一个BeanDefinition的实例
 * 此处保存Class类型和bean属性
 */

public class BeanDefinition {
    private Class BeanClass;
    private PropertyValues propertyValues;


    public BeanDefinition(Class beanClass){
//        调用两个参数的构造器
        this(beanClass, null);
    }
    public BeanDefinition(Class beanClass, PropertyValues propertyValues){
        this.BeanClass = beanClass;
        this.propertyValues = propertyValues != null ? propertyValues : new PropertyValues();
    }


    public Class getBeanClass() {
        return this.BeanClass;
    }

    public void setBeanClass(Class beanClass) {
        this.BeanClass = beanClass;
    }

    public PropertyValues getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(PropertyValues propertyValues) {
        this.propertyValues = propertyValues;
    }
}
