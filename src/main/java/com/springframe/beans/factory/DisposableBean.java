package com.springframe.beans.factory;

import cn.hutool.core.bean.BeanException;

import java.lang.reflect.InvocationTargetException;

/**
 * 销毁接口
 */
public interface DisposableBean {

    void destory() throws BeanException, InvocationTargetException, IllegalAccessException, Exception;
}
