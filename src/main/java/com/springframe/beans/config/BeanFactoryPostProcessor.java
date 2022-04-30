package com.springframe.beans.config;

import cn.hutool.core.bean.BeanException;
import com.springframe.beans.ConfigurableListableBeanFactory;

/**
 * 允许自定义修改bean的属性
 */
public interface BeanFactoryPostProcessor {
    /**
     * 在所有BeanFactory加载完之后，但在bean实例化前，修改BeanFactory的属性值
     * @param beanFactory
     * @throws BeanException
     */
    void postProcessorBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeanException;
}
