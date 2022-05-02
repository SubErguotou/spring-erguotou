package com.springframe.context.support;

import cn.hutool.core.bean.BeanException;
import com.springframe.beans.factory.BeanFactory;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;

public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext{

    private DefaultListableBeanFactory beanFactory;

    @Override
    protected void refreshBeanFactory() throws BeanException {
        //床架bean工厂
        DefaultListableBeanFactory beanFactory = createBeanFactory();

        // 加载bean的定义信息
        loadBeanDefinitions(beanFactory);

        this.beanFactory = beanFactory;
    }

    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException;

    private DefaultListableBeanFactory createBeanFactory() {
        return new DefaultListableBeanFactory();
    }

    @Override
    public DefaultListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
