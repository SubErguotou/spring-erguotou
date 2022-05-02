package com.springframe.beans.factory;

import com.springframe.beans.BeansException;

/**
 * Bean工厂，定义了获取bean信息
 */

public interface BeanFactory {
    public Object getBean(String name);
    /**
     * 根据名称和类型查找bean
     *
     * @param name
     * @param requiredType
     * @param <T>
     * @return
     * @throws BeansException
     */
    <T> T getBean(String name, Class<T> requiredType) throws BeansException;
}
