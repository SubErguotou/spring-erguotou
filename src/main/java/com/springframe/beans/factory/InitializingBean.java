package com.springframe.beans.factory;

/**
 *  初始化接口
 */
public interface InitializingBean {
    void afterPropertiesSet() throws Exception;
}
