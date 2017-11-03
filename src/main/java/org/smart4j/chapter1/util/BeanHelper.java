package org.smart4j.chapter1.util;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zk on 02/11/2017.
 */
public class BeanHelper {
    private static final Map<Class<?>,Object> BEAN_MAP=new HashMap<Class<?>,Object>();

    static {
        Set<Class<?>> beanClassSet = ClassHelper.getBeanClassSet();
        for (Class<?> aClass : beanClassSet) {
            Object o = ReflectionUtil.newInstance(aClass);
            BEAN_MAP.put(aClass,o);
        }
    }

    public static Map<Class<?>, Object> getBeanMap() {
        return BEAN_MAP;
    }

    public static <T> T getBean(Class<T> cls){
        if (!BEAN_MAP.containsKey(cls)) {
            throw new RuntimeException("can`t find bean cls:"+cls);
        }
        return (T) BEAN_MAP.get(cls);
    }

    public static void setBean(Class<?> cls,Object obj){
        BEAN_MAP.put(cls,obj);
    }
}
