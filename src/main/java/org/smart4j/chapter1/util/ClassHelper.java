package org.smart4j.chapter1.util;

import org.smart4j.chapter1.annotation.Controller;
import org.smart4j.chapter1.annotation.Service;
import org.smart4j.chapter1.config.ConfigHelper;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by zk on 02/11/2017.
 */
public class ClassHelper {
    private  static final Set<Class<?>>  CLASS_SET;
    static {
        String basePackage= ConfigHelper.getAppBasePacakge();
        CLASS_SET=ClassUtil.getClassSet(basePackage);

    }
    public static Set<Class<?>>  getClassSet(){
        return CLASS_SET;
    }

    public static Set<Class<?>>  getServiceSet(){
        HashSet<Class<?>> classes = new HashSet<>();
        for (Class<?> aClass : CLASS_SET) {
            if(aClass.isAnnotationPresent(Service.class)){
                classes.add(aClass);
            }
        }
        return classes;
    }

    public static Set<Class<?>>  getControllerSet(){
        HashSet<Class<?>> classes = new HashSet<>();
        for (Class<?> aClass : CLASS_SET) {
            if(aClass.isAnnotationPresent(Controller.class)){
                classes.add(aClass);
            }
        }
        return classes;
    }
    public static Set<Class<?>>  getBeanClassSet(){
        HashSet<Class<?>> classes = new HashSet<>();
        classes.addAll(getServiceSet());
        classes.addAll(getControllerSet());
        return classes;
    }
}
