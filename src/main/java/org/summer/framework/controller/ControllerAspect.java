package org.summer.framework.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.summer.framework.annotation.Controller;
import org.summer.framework.aop.Aspect;
import org.summer.framework.aop.AspectProxy;

import java.lang.reflect.Method;

/**
 * Created by zk on 03/11/2017.
 */
@Aspect(Controller.class)
public class ControllerAspect extends AspectProxy {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerAspect.class);

    private long begin;

    @Override
    public void after(Class<?> cls, Method method, Object[] params, Object result) {
        LOGGER.debug("---ControllerAspect------begin----------");
        LOGGER.debug(String.format("class: %s",cls.getName()));
        LOGGER.debug(String.format("method: %s",method.getName()));
        begin=System.currentTimeMillis();

    }

    @Override
    public void before(Class<?> cls, Method method, Object[] params) {
        LOGGER.debug(String.format("time: %dms",System.currentTimeMillis()-begin));
        LOGGER.debug("---ControllerAspect------end----------");
    }
}
