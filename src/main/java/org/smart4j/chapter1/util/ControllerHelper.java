package org.smart4j.chapter1.util;


import org.smart4j.chapter1.annotation.Action;
import org.smart4j.chapter1.core.Handler;
import org.smart4j.chapter1.core.Request;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by zk on 02/11/2017.
 */
public class ControllerHelper {

    private static final Map<Request, Handler> ACTION_MAP = new HashMap<Request, Handler>();

    static {
        Set<Class<?>> controllerSet =
                ClassHelper.getControllerSet();

        if (controllerSet != null && !controllerSet.isEmpty()) {
            for (Class<?> controllerClass : controllerSet) {
                Method[] declaredMethods =
                        controllerClass.getDeclaredMethods();
                if (ArrayUtil.isNotEmpty(declaredMethods)) {
                    for (Method method : declaredMethods) {
                        if (method.isAnnotationPresent(Action.class)) {
                            Action annotation = method.getAnnotation(Action.class);
                            String mapping = annotation.value();
                            if (mapping.matches("\\w+:/\\w*")) {
                                String[] array = mapping.split(":");
                                if (ArrayUtil.isNotEmpty(array) && array.length == 2) {
                                    String requestMethod = array[0];
                                    String requestpath = array[1];
                                    Request request = new Request(requestMethod, requestpath);
                                    Handler handler = new Handler(controllerClass, method);
                                    ACTION_MAP.put(request, handler);
                                }
                            }
                        }
                    }

                }
            }
        }

    }
    public static Handler getHandler(String requestMethod,String requestpath){
        Request request = new Request(requestMethod, requestpath);
        return ACTION_MAP.get(request);


    }
     public static Handler getHandler(Handler handler){
        return ACTION_MAP.get(handler);
     }
}
