package org.smart4j.framework.util;

/**
 * Created by zk on 02/11/2017.
 */
public final class HelperLoader {
    public static void init() {

        Class<?>[] classList={
                ClassHelper.class,
                BeanHelper.class,
                AopHelper.class,
                IocHelper.class,
                ControllerHelper.class
        };
        for (Class<?> aClass : classList) {
            ClassUtil.loadClass(aClass.getName(),true);
        }
    }
}
