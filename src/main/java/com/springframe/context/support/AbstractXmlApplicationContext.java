package com.springframe.context.support;

import cn.hutool.core.bean.BeanException;
import com.springframe.beans.factory.support.DefaultListableBeanFactory;
import com.springframe.beans.factory.xml.XmlAbstractBeanDefinitionReader;

public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext{
    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) throws BeanException {
        XmlAbstractBeanDefinitionReader xmlAbstractBeanDefinitionReader = new XmlAbstractBeanDefinitionReader(beanFactory, this);
        //获得所有的本地配置文件
        String[] configLocations = getConfigLocations();
        for (String configLocation : configLocations) {
            xmlAbstractBeanDefinitionReader.LoadBeanDefinitions(configLocation);
        }

    }

    protected abstract String[] getConfigLocations();
}
