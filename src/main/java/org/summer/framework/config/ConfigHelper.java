package org.summer.framework.config;

import org.summer.framework.util.PropsUtil;

import java.util.Properties;

/**
 * Created by zk on 02/11/2017.
 */
public final class ConfigHelper {
    private static final Properties CONFIG_PROPS;
    static {
        CONFIG_PROPS = PropsUtil.loadProps(ConfigConstant.CONFIG_FILE);

    }
    public static String getJdbcDriver() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_DRIVER);
    }

    public static String getJdbcUrl() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_URL);
    }

    public static String getJdbcUser() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_USERNAME);
    }

    public static String getJdbcPassword() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.JDBC_PASSWORD);
    }

    public static String getAppBasePacakge() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_BASE_PACKAGE);
    }

    public static String getAppJspPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_JSP_PATH);
    }

    public static String getAppAssertPath() {
        return PropsUtil.getString(CONFIG_PROPS, ConfigConstant.APP_ASSERT_PATH);
    }

}