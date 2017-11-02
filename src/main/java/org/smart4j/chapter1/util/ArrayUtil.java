package org.smart4j.chapter1.util;


import org.apache.commons.lang3.ArrayUtils;

/**
 * Created by zk on 02/11/2017.
 */
public class ArrayUtil {

    public static boolean isNotEmpty(Object[] array){
        return ArrayUtils.isNotEmpty(array);
    }
    public static boolean isEmpty(Object[] array){
        return ArrayUtils.isEmpty(array);
    }

}
