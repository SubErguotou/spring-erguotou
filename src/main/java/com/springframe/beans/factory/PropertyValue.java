package com.springframe.beans.factory;

/**
 * bean的bean的属性结构，name与value
 */
public class PropertyValue {
    public final String name;
    public final Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
