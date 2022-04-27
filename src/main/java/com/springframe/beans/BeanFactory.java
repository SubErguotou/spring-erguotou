package com.springframe.beans;

/**
 * Bean工厂，定义了获取bean信息
 */

public interface BeanFactory {
    public Object getBean(String name);
}
