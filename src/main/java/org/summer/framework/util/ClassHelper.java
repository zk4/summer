package org.summer.framework.util;

import org.summer.framework.annotation.Controller;
import org.summer.framework.annotation.Service;
import org.summer.framework.config.ConfigHelper;

import java.lang.annotation.Annotation;
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

    public static Set<Class<?>>  getClassSetBySuper(Class<?> superClass){
        HashSet<Class<?>> classes = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (superClass.isAssignableFrom(cls) && !superClass.equals(cls)) {
                classes.add(cls);
            }
        }
        return classes;
    }

    public static Set<Class<?>> getClassSetByAnnotation(Class<? extends Annotation> annotationClass){
        HashSet<Class<?>> classSet = new HashSet<>();
        for (Class<?> cls : CLASS_SET) {
            if (cls.isAnnotationPresent(annotationClass)) {
                classSet.add(cls);
            }
        }
        return classSet;
    }
}
