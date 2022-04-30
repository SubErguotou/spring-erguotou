package com.springframe.beans.support;

import com.springframe.beans.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;

/**
 * 默认单例bean注册表,实现添加单实例bean功能
 */
public abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
//    单例对象池
    private Map<String, Object> singletonBean = new HashMap<>();
//   添加单例bean进单例池。
    public void addSingletonBean(String name, Object singletonObject){
        singletonBean.put(name, singletonObject);
    }

    @Override
    public Object getSingleton(String name) {
        return this.singletonBean.get(name);
    }
    
}
