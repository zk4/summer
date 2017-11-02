package org.smart4j.chapter1.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by zk on 02/11/2017.
 */
public class PropsUtil {
    private static final Logger LOGGER = LoggerFactory.getLogger(PropsUtil.class);

    public static Properties loadProps(String fileName) {
        Properties pros = null;
        InputStream is = null;
        try {
            if (is == null)
                is = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName);

             pros = new Properties();

            pros.load(is);
        } catch (IOException e) {

            LOGGER.error("load properties file failure", e);
        } finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    LOGGER.error("close input stream failure", e);
                }
            }
        }
        return pros;

    }

    public static String getString(Properties properties, String key) {
        return getString(properties, key, "");
    }

    private static String getString(Properties properties, String key, String defaultValue) {
        String value = defaultValue;
        if (properties.containsKey(key))
            value = properties.getProperty(key);
        return value;
    }

    public static int getInt(Properties properties, String key) {

        if (properties.containsKey(key))
            return Integer.parseInt(properties.getProperty(key));
        throw new RuntimeException("not found properties key:" + key);
    }

    public static long getLong(Properties properties, String key) {

        if (properties.containsKey(key))
            return Long.parseLong(properties.getProperty(key));
        throw new RuntimeException("not found properties key:" + key);
    }

    public static boolean getBoolean(Properties properties, String key) {

        if (properties.containsKey(key))
            return Boolean.parseBoolean(properties.getProperty(key));
        throw new RuntimeException("not found properties key:" + key);
    }

}
