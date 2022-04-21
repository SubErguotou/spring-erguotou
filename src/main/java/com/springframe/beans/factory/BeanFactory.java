package com.springframe.beans.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * 手动是实现BeanFactory，只能注册bean和获取bean
 */

public class BeanFactory {
    private Map<String, Object> beanMap = new HashMap<>();

//    注册bean
    public void registerBean(String name, Object bean){
        beanMap.put(name, bean);
    }
//    获得bean
    public Object getBean(String name){
        return beanMap.get(name);
    }
}
