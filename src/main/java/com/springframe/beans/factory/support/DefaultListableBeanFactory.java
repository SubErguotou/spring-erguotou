package com.springframe.beans.factory.support;

import com.springframe.beans.BeansException;
import com.springframe.beans.factory.ConfigurableListableBeanFactory;
import com.springframe.beans.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ConfigurableListableBeanFactory {

//    BeanDefinition(Bean定义信息池)池
    private final Map<String, BeanDefinition> beanDefinitionMap = new HashMap<>();


    @Override
    public void registerBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }

    @Override
    public BeanDefinition getBeanDefinition(String BeanName) {
        BeanDefinition beanDefinition = beanDefinitionMap.get(BeanName);
        return beanDefinition;
    }
    //提前实例化所有单实例bean
    @Override
    public void preInstantiateSingletons() throws BeansException {
        // keySet拿到所有bean定义信息的key，再对每个定义信息key做getBean操作
        beanDefinitionMap.keySet().forEach(this::getBean);
    }

//    检测是否包含包含在bean的定义信息池中getBeanDefinitionNames
    @Override
    public boolean containsBeanDefinition(String beanName) {
        return beanDefinitionMap.containsKey(beanName);
    }

    // 获取某个类型实例化的所有对象
    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) throws BeansException {
        Map<String, T> result = new HashMap<>();
        // 迭代出bean定义信息里的bean名和定义信息
        beanDefinitionMap.forEach((beanName, beanDefinition) -> {
            Class beanClass = beanDefinition.getBeanClass();
            // 判断是否是和传入的类型一致
            if (type.isAssignableFrom(beanClass)) {
                // 实例化
                T bean = (T) getBean(beanName);
                result.put(beanName, bean);
            }
        });
        return result;
    }

    @Override
    public String[] getBeanDefinitionNames() {
        Set<String> beanNames = beanDefinitionMap.keySet();
        return beanNames.toArray(new String[beanNames.size()]);
    }
}
