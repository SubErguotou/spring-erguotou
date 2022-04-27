package com.springframe.beans.config;

/**
 * 单实例bean注册
 */
public interface SingletonBeanRegistry {

    Object getSingleton(String name);
}
