package org.smart4j.framework.aop;

import java.lang.annotation.*;

/**
 * Created by zk on 02/11/2017.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface Aspect2 {
    Class<? extends Annotation> value();

}
