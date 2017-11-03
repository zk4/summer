package org.smart4j.framework.controller;

import java.util.Map;

/**
 * Created by zk on 02/11/2017.
 */
public class Param {
    Map<String, Object> paramMap;

    public Param(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getMap() {
        return null;
    }


    public Long getLong(String id) {
            return Long.parseLong((String)paramMap.get(id));
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }
}
