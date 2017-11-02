package aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by zk on 02/11/2017.
 */
public class CGlibProxy implements MethodInterceptor {
    private static final CGlibProxy cGlibProxy = new CGlibProxy();

    public static CGlibProxy getInstance() {
        return cGlibProxy;
    }

    private CGlibProxy() {
    }

    @Override
    public Object intercept(Object o, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
        before();
        Object ret = methodProxy.invokeSuper(o, args);
        after();
        return ret;
    }

    public void before() {
        System.out.println("CGlibProxy before");
    }

    public void after() {
        System.out.println("CGlibProxy after");
    }


    public <T> T getProxy(Class<T> cls) {
        return (T) Enhancer.create(cls, this);
    }


}
