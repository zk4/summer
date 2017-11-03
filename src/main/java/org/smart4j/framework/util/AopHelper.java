package org.smart4j.framework.util;

import org.smart4j.framework.annotation.Service;
import org.smart4j.framework.aop.Aspect2;
import org.smart4j.framework.aop.AspectProxy;
import org.smart4j.framework.aop.Proxy;
import org.smart4j.framework.aop.ProxyManager;
import org.smart4j.framework.transaction.TransactionProxy;

import java.lang.annotation.Annotation;
import java.util.*;

/**
 * Created by zk on 03/11/2017.
 */
public final class AopHelper {

    static {
        try {
            Map<Class<?>, Set<Class<?>>> proxyMap = createProxyMap();
            Map<Class<?>, List<Proxy>> targetMap = createTargetMap(proxyMap);
            for (Map.Entry<Class<?>, List<Proxy>> targetentry : targetMap.entrySet()) {
                Class<?> targetClass = targetentry.getKey();
                List<Proxy> proxyList = targetentry.getValue();
                Object proxpy = ProxyManager.createProxpy(targetClass, proxyList);
                BeanHelper.setBean(targetClass, proxpy);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static Set<Class<?>> createTargetClassSet(Aspect2 aspect) throws Exception {
        HashSet<Class<?>> targetClassSet = new HashSet<>();
        Class<? extends Annotation> annotation = aspect.value();
        if (annotation != null && !annotation.equals(Aspect2.class)) {
            targetClassSet.addAll(ClassHelper.getClassSetByAnnotation(annotation));
        }
        return targetClassSet;
    }

    private static Map<Class<?>, Set<Class<?>>> createProxyMap() throws Exception {
        HashMap proxyMap = new HashMap<Class<?>, Set<Class<?>>>();
        addAspectPropxy(proxyMap);
        addTransactionPropxy(proxyMap);
        return proxyMap;
    }

    private static void addAspectPropxy(Map<Class<?>, Set<Class<?>>>  proxyMap) throws Exception {
        Set<Class<?>> proxyClassSet = ClassHelper.getClassSetBySuper(AspectProxy.class);
        for (Class<?> proxyClass : proxyClassSet) {
            if (proxyClass.isAnnotationPresent(Aspect2.class)) {
                Aspect2 aspect = proxyClass.getAnnotation(Aspect2.class);
                Set<Class<?>> targetClassSet = createTargetClassSet(aspect);
                proxyMap.put(proxyClass, targetClassSet);
            }
        }
    }

    private static void addTransactionPropxy(Map<Class<?>, Set<Class<?>>>  proxyMap) throws Exception {
        Set<Class<?>> classSetByAnnotation = ClassHelper.getClassSetByAnnotation(Service.class);
        proxyMap.put(TransactionProxy.class, classSetByAnnotation);


    }

    private static Map<Class<?>, List<Proxy>> createTargetMap(Map<Class<?>, Set<Class<?>>> proxyMap) throws IllegalAccessException, InstantiationException {
        Map<Class<?>, List<Proxy>> targetMap = new HashMap<>();
        for (Map.Entry<Class<?>, Set<Class<?>>> proxyEntiry : proxyMap.entrySet()) {
            Class<?> proxyClass = proxyEntiry.getKey();
            Set<Class<?>> targetClassSet = proxyEntiry.getValue();
            for (Class<?> targetClass : targetClassSet) {
                Proxy proxy = (Proxy) proxyClass.newInstance();
                if (targetMap.containsKey(targetClass)) {
                    targetMap.get(targetClass).add(proxy);
                } else {
                    List<Proxy> proxyList = new ArrayList<>();
                    proxyList.add(proxy);
                    targetMap.put(targetClass, proxyList);
                }
            }
        }
        return targetMap;
    }


}