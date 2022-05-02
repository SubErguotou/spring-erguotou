package com.springframe.beans.factory.xml;

import cn.hutool.core.bean.BeanException;
import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.XmlUtil;
import com.springframe.beans.BeansException;
import com.springframe.beans.factory.config.BeanReference;
import com.springframe.beans.PropertyValue;
import com.springframe.beans.factory.config.BeanDefinition;
import com.springframe.beans.factory.support.AbstractBeanDefinitionReader;
import com.springframe.beans.factory.support.BeanDefinitionRegistry;
import com.springframe.core.io.Resource;
import com.springframe.core.io.ResourceLoader;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.IOException;
import java.io.InputStream;

public class XmlAbstractBeanDefinitionReader extends AbstractBeanDefinitionReader {

    public static final String BEAN_ELEMENT = "bean";
    public static final String PROPERTY_ELEMENT = "property";
    public static final String ID_ATTRIBUTE = "id";
    public static final String NAME_ATTRIBUTE = "name";
    public static final String CLASS_ATTRIBUTE = "class";
    public static final String VALUE_ATTRIBUTE = "value";
    public static final String REF_ATTRIBUTE = "ref";

    public XmlAbstractBeanDefinitionReader(BeanDefinitionRegistry registry) {
        super(registry);
    }

    public XmlAbstractBeanDefinitionReader(BeanDefinitionRegistry registry, ResourceLoader resourceLoader) {
        super(registry, resourceLoader);
    }


    @Override
    public void LoadBeanDefinitions(String location) throws BeanException {
        Resource resource = getResourceLoader().getResource(location);
        LoadBeanDefinitions(resource);
    }
    @Override
    public void LoadBeanDefinitions(Resource resource) throws BeanException {
        try {
            InputStream inputStream = resource.getInputStream();
            try {
                doLoadBeanDefinitions(inputStream);
            } finally {
                inputStream.close();
            }
        } catch (IOException ex) {
            throw new BeansException("IOException parsing XML document from " + resource, ex);
        }
    }
//    把读取到的资源流变成BeanDefinition，并注册到注册中心，xml主要的解析逻辑
    protected  void doLoadBeanDefinitions(InputStream inputStream){
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
//        遍历xml的每个节点
        for (int i = 0; i < childNodes.getLength(); i++){
//            提取bean节点
            if (childNodes.item(i) instanceof Element){
                if (BEAN_ELEMENT.equals(((Element)childNodes.item(i)).getNodeName())){
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);

                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("没有找到:[" + className + "]类");
                    }
                    // id优先于name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    // 如果id和name都为空，将类名的第一个字母转为小写字母后作为bean的名称
                    if (StrUtil.isEmpty(beanName)){
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);

                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element){
                            if (PROPERTY_ELEMENT.equals(((Element)bean.getChildNodes().item(j)).getNodeName())){
                                // 解析property 标签
                                Element property = (Element)bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);
                                if (StrUtil.isEmpty(nameAttribute)){
                                    throw new BeansException("这个属性名为null或为空");
                                }
                                Object value = valueAttribute;
                                // 判断这个属性是否依赖于其他对象
                                if (StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    // 检测是否同名
                    if (getRegistry().containsBeanDefinition(beanName)) {
                        //beanName不能重名
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }
                    //注册BeanDefinition
                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}
