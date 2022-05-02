package com.springframe.context;

import cn.hutool.core.bean.BeanException;

public interface ConfigurableApplicationContext extends ApplicationContext{
    /**
     * 刷新容器
     */
    void refresh() throws BeanException;
}
