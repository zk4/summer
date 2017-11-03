package org.smart4j.framework.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by zk on 02/11/2017.
 */
public class ReflectionUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(ReflectionUtil.class);


    public static Object newInstance(Class<?> cls) {
        Object instance=null;

        try {
            instance=cls.newInstance();
        } catch (InstantiationException e) {
            LOGGER.error("instance error",e);
            LOGGER.error("cls:",cls);
        } catch (IllegalAccessException e) {
            LOGGER.error("illeagal access error",e);
        }
        return instance;
    }

    public static Object invokeMethod(Object obj, Method method, Object ... args){
        Object  result=null;


        try {
            method.setAccessible(true);
            result=method.invoke(obj,args);
        } catch (IllegalAccessException e) {
            LOGGER.error("IllegalAccessException error",e);
        } catch (InvocationTargetException e) {
            LOGGER.error("InvocationTargetException error",e);
        }
        return result;
    }
    public static void setFiled(Object obj, Field field, Object value ){
        field.setAccessible(true);
        try {
            field.set(obj,value);
        } catch (IllegalAccessException e) {
                e.printStackTrace();
        }

    }
}
