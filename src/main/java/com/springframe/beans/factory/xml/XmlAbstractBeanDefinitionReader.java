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
    public static final String INIT_METHOD_ATTRIBUTE = "init-method";
    public static final String DESTROY_METHOD_ATTRIBUTE = "destroy-method";

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
//    ??????????????????????????????BeanDefinition??????????????????????????????xml?????????????????????
    protected  void doLoadBeanDefinitions(InputStream inputStream){
        Document document = XmlUtil.readXML(inputStream);
        Element root = document.getDocumentElement();
        NodeList childNodes = root.getChildNodes();
//        ??????xml???????????????
        for (int i = 0; i < childNodes.getLength(); i++){
//            ??????bean??????
            if (childNodes.item(i) instanceof Element){
                if (BEAN_ELEMENT.equals(((Element)childNodes.item(i)).getNodeName())){
                    Element bean = (Element) childNodes.item(i);
                    String id = bean.getAttribute(ID_ATTRIBUTE);
                    String name = bean.getAttribute(NAME_ATTRIBUTE);
                    String className = bean.getAttribute(CLASS_ATTRIBUTE);
                    String initMethodName = bean.getAttribute(INIT_METHOD_ATTRIBUTE);
                    String destroyMethodName = bean.getAttribute(DESTROY_METHOD_ATTRIBUTE);

                    Class<?> clazz = null;
                    try {
                        clazz = Class.forName(className);
                    } catch (ClassNotFoundException e) {
                        throw new BeansException("????????????:[" + className + "]???");
                    }
                    // id?????????name
                    String beanName = StrUtil.isNotEmpty(id) ? id : name;
                    // ??????id???name??????????????????????????????????????????????????????????????????bean?????????
                    if (StrUtil.isEmpty(beanName)){
                        beanName = StrUtil.lowerFirst(clazz.getSimpleName());
                    }
                    BeanDefinition beanDefinition = new BeanDefinition(clazz);
                    beanDefinition.setInitMethodName(initMethodName);
                    beanDefinition.setDestroyMethodName(destroyMethodName);

                    for (int j = 0; j < bean.getChildNodes().getLength(); j++) {
                        if (bean.getChildNodes().item(j) instanceof Element){
                            if (PROPERTY_ELEMENT.equals(((Element)bean.getChildNodes().item(j)).getNodeName())){
                                // ??????property ??????
                                Element property = (Element)bean.getChildNodes().item(j);
                                String nameAttribute = property.getAttribute(NAME_ATTRIBUTE);
                                String valueAttribute = property.getAttribute(VALUE_ATTRIBUTE);
                                String refAttribute = property.getAttribute(REF_ATTRIBUTE);
                                if (StrUtil.isEmpty(nameAttribute)){
                                    throw new BeansException("??????????????????null?????????");
                                }
                                Object value = valueAttribute;
                                // ?????????????????????????????????????????????
                                if (StrUtil.isNotEmpty(refAttribute)){
                                    value = new BeanReference(refAttribute);
                                }
                                PropertyValue propertyValue = new PropertyValue(nameAttribute, value);
                                beanDefinition.getPropertyValues().addPropertyValue(propertyValue);
                            }
                        }
                    }
                    // ??????????????????
                    if (getRegistry().containsBeanDefinition(beanName)) {
                        //beanName????????????
                        throw new BeansException("Duplicate beanName[" + beanName + "] is not allowed");
                    }
                    //??????BeanDefinition
                    getRegistry().registerBeanDefinition(beanName, beanDefinition);
                }
            }
        }
    }
}
