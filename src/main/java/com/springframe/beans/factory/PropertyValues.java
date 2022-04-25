package com.springframe.beans.factory;

import java.util.ArrayList;
import java.util.List;

/**
 * 存储bean的所有属性
 */
public class PropertyValues {
//    保存bean的所有属性值(属性池)
    private final List<PropertyValue> propertyValues = new ArrayList<>();

//    添加属性
    public void addPropertyValue(PropertyValue pv){
        propertyValues.add(pv);
    }
//    获得所有属性值
    public PropertyValue[] getPropertyValues() {
//
        return this.propertyValues.toArray(new PropertyValue[0]);
    }
//    获得指定属性
    public PropertyValue getPropertyValue(String propertyName){
        for (int i = 0; i < propertyValues.size(); i++) {
            PropertyValue pv = propertyValues.get(i);
            if (pv.getName().equals(propertyName)){
                return pv;
            }
        }
        return null;
    }
}
