package org.smart4j.framework.aop;

/**
 * Created by zk on 02/11/2017.
 */
public interface  Proxy {
    Object doProxy(ProxyChain proxyChain) throws  Throwable;

}
