package org.smart4j.chapter1.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by zk on 02/11/2017.
 */
public class JsonUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    private  static final ObjectMapper OBJECT_MAPPER=new ObjectMapper();

    public static <T> String toJson(T obj){
        String json=null;
        try {
            json=OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return json;
    }

    public static <T> T fromJson(String json, Class<T> type ){
        T pojo=null;
        try {
            pojo = OBJECT_MAPPER.readValue(json, type);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return pojo;
    }

}
