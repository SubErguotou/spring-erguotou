package com.springframe.beans.factory.support;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.ClassUtil;
import cn.hutool.core.util.StrUtil;
import com.springframe.beans.BeansException;
import com.springframe.beans.factory.DisposableBean;
import com.springframe.beans.factory.InitializingBean;
import com.springframe.beans.factory.config.AutowriteCapableBeanFactory;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.config.BeanPostProcessor;
import com.springframe.beans.factory.config.BeanReference;
import com.springframe.beans.PropertyValue;

import java.lang.reflect.Method;

/**
 * 自动装配能力的beanFactory
 * 目前能够自动实例化bean、为bean添加属性值，为bean添加前后置处理器
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowriteCapableBeanFactory {

    private InstantiationStrategy instantiationStrategy = new SimpleInstantiationStrategy();

    @Override
    protected Object creatBean(String BeanName, BeanDefinition beanDefinition) {
        return doCreateBean(BeanName, beanDefinition);
    }

//    使用beanDefinition来创建bean
    protected Object doCreateBean(String BeanName, BeanDefinition beanDefinition){

        Class beanClass = beanDefinition.getBeanClass();
        Object bean = null;
        try {
//            创建bean实例
            bean = creatBeanInstantiation(beanDefinition);
//            为创建好的bean添加属性
            addPropertyValues(BeanName, bean, beanDefinition);
            //执行bean的初始化方法和BeanPostProcessor的前置和后置处理方法
            initializeBean(BeanName, bean, beanDefinition);
        } catch (Exception e) {
            throw new BeansException("newInstance创建bean失败", e);
        } catch (Throwable e) {
            e.printStackTrace();
        }

        //注册有销毁方法的bean
        registerDisposableBeanIfNecessary(BeanName, bean, beanDefinition);

//        将创建好的bean加入单例池
        addSingletonBean(BeanName, bean);
        return bean;
    }

    protected void registerDisposableBeanIfNecessary(String beanName, Object bean, BeanDefinition beanDefinition){
        if(bean instanceof DisposableBean || StrUtil.isNotEmpty(beanDefinition.getDestroyMethodName())){
            registerDisposableBean(beanName, new DisposableBeanAdapter(bean, beanName, beanDefinition));
        }
    }

    protected Object creatBeanInstantiation(BeanDefinition beanDefinition){
        return getInstantiationStrategy().instantiate(beanDefinition);
    }

    //添加属性值
    protected void addPropertyValues(String BeanName, Object bean, BeanDefinition beanDefinition){
        try {
            for (PropertyValue propertyValue : beanDefinition.getPropertyValues().getPropertyValues()){
                String name = propertyValue.getName();
                Object value = propertyValue.getValue();
//                如果bean的value值时BeanReference，则先实例化bean的依赖对象
                if (value instanceof BeanReference){
                    BeanReference beanReference = (BeanReference) value;
                    value = getBean(beanReference.getBeanName());
                }

                //通过反射设置属性, 为bean实例里name的属性设置vlaue，相同的name会被后来的name值覆盖
                BeanUtil.setFieldValue(bean, name, value);
            }
        } catch (Exception e){
            new BeansException(BeanName + "设置属性错误 ", e);
        }
    }

    protected Object initializeBean(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
        // 执行前置处理器
        Object wrappedBean = applyBeanPostProcessorsBeforeInitialization(bean, beanName);

        //调用初始化方法
        invokeInitMethods(beanName, wrappedBean, beanDefinition);
        //后置处理器
        wrappedBean = applyBeanPostProcessorsAfterInitialization(bean, beanName);
        return wrappedBean;
    }
    @Override
    public Object applyBeanPostProcessorsBeforeInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessBeforeInitialization(result, beanName);
            if (current == null) {
                return result;
            }
            result = current;
        }
        return result;
    }

    /**
     * 执行bean的初始化方法
     *
     * @param beanName
     * @param bean
     * @param beanDefinition
     * @throws Throwable
     */
    protected void invokeInitMethods(String beanName, Object bean, BeanDefinition beanDefinition) throws Throwable {
        if (bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }
        String initMethodName = beanDefinition.getInitMethodName();
        if (StrUtil.isNotEmpty(initMethodName)){
            /**
             * getPublicMethod：查找指定Public方法 如果找不到对应的方法或方法不为public的则返回null
             * 去bean实例中找有没有指定的方法
             */
            Method initMethod = ClassUtil.getPublicMethod(beanDefinition.getBeanClass(), initMethodName);
            if (initMethod == null){
                throw new BeansException("initMethod为空");
            }
            // 调用bean的initMethod方法
            initMethod.invoke(bean);
        }

    }

    @Override
    public Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName) throws BeansException {
        Object result = existingBean;
        for (BeanPostProcessor processor : getBeanPostProcessors()) {
            Object current = processor.postProcessAfterInitialization(result, beanName);
            if (current == null){
                return result;
            }
            result = current;
        }
        return result;
    }



    protected InstantiationStrategy getInstantiationStrategy() {
        return instantiationStrategy;
    }
    protected void setInstantiationStrategy(InstantiationStrategy instantiationStrategy) {
        this.instantiationStrategy = instantiationStrategy;
    }
}
