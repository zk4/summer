package org.smart4j.chapter1.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.smart4j.chapter1.annotation.Controller;
import org.smart4j.chapter1.aop.Aspect2;
import org.smart4j.chapter1.aop.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by zk on 03/11/2017.
 */
@Aspect2(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug("---------begin----------");
        LOGGER.debug(String.format("class: %s",cls.getName()));
        LOGGER.debug(String.format("method: %s",method.getName()));
        begin=System.currentTimeMillis();

    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug(String.format("time: %dms",System.currentTimeMillis()-begin));
        LOGGER.debug("---------end----------");
    }
}
