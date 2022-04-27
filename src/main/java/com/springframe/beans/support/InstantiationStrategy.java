package com.springframe.beans.support;

import com.springframe.beans.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition);
}
