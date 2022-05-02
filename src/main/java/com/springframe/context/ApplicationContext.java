package com.springframe.context;

import com.springframe.beans.factory.HierarchicalBeanFactory;
import com.springframe.beans.factory.ListableBeanFactory;
import com.springframe.core.io.ResourceLoader;

public interface ApplicationContext extends ListableBeanFactory, ResourceLoader, HierarchicalBeanFactory {
}
