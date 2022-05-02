package com.springframe.beans.factory.support;

import com.springframe.beans.factory.config.BeanDefinition;

public interface InstantiationStrategy {
    Object instantiate(BeanDefinition beanDefinition);
}
