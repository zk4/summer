package org.summer.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.framework.annotation.Inject;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * Created by zk on 02/11/2017.
 */
public final class IocHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(IocHelper.class);

    static {
        Map<Class<?>, Object> beanMap =
                BeanHelper.getBeanMap();
        if (beanMap != null && beanMap.size() != 0) {
            for (Map.Entry<Class<?>, Object> beanEntry : beanMap.entrySet()) {
                Class<?> beanClass = beanEntry.getKey();
                Object beanInstance = beanEntry.getValue();

                Field[] beanFields =
                        beanClass.getDeclaredFields();

                if (beanFields != null && beanFields.length != 0) {

                    for (Field beanField : beanFields) {
                        if (beanField.isAnnotationPresent(Inject.class)) {
                            Class<?> type = beanField.getType();
                            Object beanFieldInstance = beanMap.get(type);
                            if (beanFieldInstance != null) {
                                ReflectionUtil.setFiled(beanInstance, beanField, beanFieldInstance);
                            }
                        }
                    }

                }

            }
        }
    }

}
