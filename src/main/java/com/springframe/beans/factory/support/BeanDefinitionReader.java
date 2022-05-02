package com.springframe.beans.factory.support;

import cn.hutool.core.bean.BeanException;
import com.springframe.core.io.Resource;
import com.springframe.core.io.ResourceLoader;

/**
 * Bean的定义信息读取
 */

public interface BeanDefinitionReader {

//    获取注册中心
    BeanDefinitionRegistry getRegistry();

//    获取资源加载器
    ResourceLoader getResourceLoader();

//    装载bean的定义信息
    void LoadBeanDefinitions(Resource resource) throws BeanException;
    void LoadBeanDefinitions(String location) throws BeanException;
    void LoadBeanDefinitions(String[] locations) throws  BeanException;

}
