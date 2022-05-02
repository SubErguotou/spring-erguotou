package com.springframe.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.springframe.core.io.DefaultResourceLoader;
import com.springframe.core.io.ResourceLoader;

/**
 * 抽象的beanDefinition读取
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader{
    private final BeanDefinitionRegistry Registry;
    private ResourceLoader resourceLoader;

    protected AbstractBeanDefinitionReader(BeanDefinitionRegistry registry){
        this(registry, new DefaultResourceLoader());
    }
//
    public AbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        Registry = registry;
        this.resourceLoader = resourceLoader;
    }

    @Override
    public BeanDefinitionRegistry getRegistry() {
        return Registry;
    }

    @Override
    public void LoadBeanDefinitions(String[] locations) throws BeanException {
        for (String location : locations) {
            LoadBeanDefinitions(location);
        }
    }

    public void setResourceLoader(ResourceLoader resourceLoader) {
        this.resourceLoader = resourceLoader;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }
}
