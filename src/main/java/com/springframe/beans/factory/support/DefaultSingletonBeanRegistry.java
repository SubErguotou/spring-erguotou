package com.springframe.beans.factory.support;

import com.springframe.beans.BeansException;
import com.springframe.beans.factory.DisposableBean;
import com.springframe.beans.factory.config.SingletonBeanRegistry;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * 默认单例bean注册表,实现添加单实例bean功能
 */
public abstract class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {
//    单例对象池
    private Map<String, Object> singletonBean = new HashMap<>();

    //拥有销毁方法的bean池
    private final Map<String, DisposableBean> disposableBeans = new HashMap<>();

//   添加单例bean进单例池。
    public void addSingletonBean(String name, Object singletonObject){
        singletonBean.put(name, singletonObject);
    }

    @Override
    public Object getSingleton(String name) {
        return this.singletonBean.get(name);
    }

    // 保存拥有销毁方法的bean
    public void registerDisposableBean(String beanName, DisposableBean bean) {
        disposableBeans.put(beanName, bean);
    }
    //
    public void destroySingletons(){
        //获得所有的key值
        Set<String> benaNames = disposableBeans.keySet();
        for (String benaName : benaNames) {
            DisposableBean disposableBean = disposableBeans.remove(benaName);
//            销毁bean
            try {
                disposableBean.destory();
            }catch (Exception e){
                throw new BeansException("销毁bean错误", e);
            }
        }
    }
}
