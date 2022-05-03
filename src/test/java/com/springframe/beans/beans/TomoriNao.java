package com.springframe.beans.beans;

import cn.hutool.core.bean.BeanException;
import com.springframe.beans.factory.DisposableBean;
import com.springframe.beans.factory.InitializingBean;

import java.lang.reflect.InvocationTargetException;

public class TomoriNao implements InitializingBean, DisposableBean {
    private String name;
    private String age;

    public TomoriNao() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public void customInitMethod(){
        System.out.println("友利奈绪--xml自定义初始化方法");
    }
    public void customDestroyMethod(){
        System.out.println("友利奈绪--xml自定义销毁方法");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("友利奈绪--接口初始化方法");
    }

    @Override
    public void destory() throws Exception {
        System.out.println("有利奈绪--接口定义销毁方法");
    }
}
