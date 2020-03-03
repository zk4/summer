package org.summer.framework.controller;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zk on 02/11/2017.
 */
public class View {

    private String path;
    private Map<String,Object> model=new HashMap<>();

    public View(String path) {
        this.path = path;
    }

    public View addModel(String key, Object value) {
        model.put(key,value);
        return this;
    }

    public String getPath() {
        return path;
    }

    public Map<String, Object> getModel() {
        return model;
    }
}
