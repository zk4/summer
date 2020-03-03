package org.summer.framework.controller;

import java.util.Map;

/**
 * Created by zk on 02/11/2017.
 */
public class Param {
    Map<String, String[]> paramMap;

    public Param(Map<String, String[]> paramMap) {
        this.paramMap = paramMap;
    }

    public Map<String, Object> getMap() {
        return null;
    }


    public Long getLong(String id) {
            return Long.parseLong((String)paramMap.get(id)[0]);
    }

    public Map<String, String[]> getParamMap() {
        return paramMap;
    }
}
