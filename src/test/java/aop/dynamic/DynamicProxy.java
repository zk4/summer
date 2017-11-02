package aop.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by zk on 02/11/2017.
 */
public class DynamicProxy implements InvocationHandler {

    private Object object;

    public DynamicProxy(Object object) {
        this.object = object;
    }

    public void before() {
        System.out.println("dynamic before");
    }

    public void after() {
        System.out.println("dynamic after");
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object ret = method.invoke(object, args);
        after();
        return ret;
    }

    @SuppressWarnings("unchecked")
    public <T> T getProxy() {
        return (T)Proxy.newProxyInstance(
                object.getClass().getClassLoader(),
                object.getClass().getInterfaces(),
                this
        );

    }


}
