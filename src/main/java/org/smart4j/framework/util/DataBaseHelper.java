package org.smart4j.framework.util;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by zk on 02/11/2017.
 */
public class DataBaseHelper {
    private static final Logger LOGGER = LoggerFactory.getLogger(DataBaseHelper.class);

    private static final String DRIVER;
    private static final String URL;
    private static final String USERNAME;
    private static final String PASSWORD;

    private static final ThreadLocal<Connection> CONNECTION_HOLDER = new ThreadLocal<>();
    private static final BasicDataSource DATA_SOURCE;

    private static final QueryRunner QUERY_RUNNER = new QueryRunner();

    static {
        Properties properties = PropsUtil.loadProps("config.properties");
        DRIVER = properties.getProperty("jdbc.driver");
        URL = properties.getProperty("jdbc.url");
        USERNAME = properties.getProperty("jdbc.username");
        PASSWORD = properties.getProperty("jdbc.password");

        DATA_SOURCE = new BasicDataSource();
        DATA_SOURCE.setDriverClassName(DRIVER);
        DATA_SOURCE.setUrl(URL);
        DATA_SOURCE.setUsername(USERNAME);
        DATA_SOURCE.setPassword(PASSWORD);

        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {

            LOGGER.error("can not load jdbc driver", e);

        }
    }

    public static <T> List<T> queryEntityList(Class<T> entityClass, String sql, Object... params) {
        List<T> entityList;
        try {
            Connection connection = getConnection();
            entityList = QUERY_RUNNER.query(connection, sql, new BeanListHandler<T>(entityClass), params);
        } catch (SQLException e) {
            LOGGER.error("execute sql " + sql);
            LOGGER.error("execute sql failure " + e);

            throw new RuntimeException(e);

        }
        return entityList;
    }

    public static int executeUpdate(String sql, Object... params) {
        int rows = 0;
        try {
            LOGGER.info("sql: {}", sql);
            Connection conn = getConnection();

            rows = QUERY_RUNNER.update(conn, sql, params);
        } catch (SQLException e) {
            LOGGER.error("execute sql " + sql);
            LOGGER.error("execute sql failure " + e);

            throw new RuntimeException(e);

        }
        return rows;
    }

    public static void rollback() {
        Connection connection = CONNECTION_HOLDER.get();

        try {
            if (connection != null)
                connection.rollback();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static <T> T getOneEntity(Class<T> entityClass, long id) {
        String sql = "SELECT * FROM " + getTableName(entityClass) + " WHERE id=?";
        Connection connection = getConnection();
        T t = null;
        try {
            t = QUERY_RUNNER.query(connection, sql, new BeanHandler<T>(entityClass), id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    public static <T> boolean deleteEntity(Class<T> entittyClass, long id) {
        String sql = "DELETE FROM " + getTableName(entittyClass) + " WHERE id=?";
        return executeUpdate(sql, id) == 1;
    }

    public static <T> boolean updateEntity(Class<?> entityClass, long id, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.size() == 0) {
            throw new RuntimeException("no field to insert");
        }

        String sql = "UPDATE " + getTableName(entityClass) + " SET ";
        StringBuilder columns = new StringBuilder();
        for (String fieldName : fieldMap.keySet()) {
            columns.append(fieldName).append("=?, ");
        }
        sql += columns.substring(0, columns.lastIndexOf(", ")) + " WHERE id=?";
        List<Object> paramList = new ArrayList<>();
        paramList.addAll(fieldMap.values());
        paramList.add(id);
        Object[] params = paramList.toArray();

        return executeUpdate(sql, params) == 1;
    }

    public static <T> boolean insertEntity(Class<T> entityClass, Map<String, Object> fieldMap) {
        if (fieldMap == null || fieldMap.size() == 0) {
            throw new RuntimeException("no field to insert");
        }
        String sql = "INSERT INTO " + getTableName(entityClass);
        StringBuilder columns = new StringBuilder("(");
        StringBuilder values = new StringBuilder("(");
        for (String field : fieldMap.keySet()) {
            columns.append(field).append(", ");
            values.append("?, ");
        }
        columns.replace(columns.lastIndexOf(", "), columns.length(), ")");
        values.replace(values.lastIndexOf(", "), values.length(), ")");
        sql += columns + " VALUES " + values;

        Object[] params = fieldMap.values().toArray();

        return executeUpdate(sql, params) == 1;
    }

    private static <T> String getTableName(Class<T> entityClass) {
        return entityClass.getSimpleName().toLowerCase();
    }

    public static Connection getConnection() {
        Connection conn = CONNECTION_HOLDER.get();
        if (conn == null) {
            try {
                conn = DATA_SOURCE.getConnection();
            } catch (SQLException e) {
                LOGGER.error("get connection failure", e);
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
        return conn;
    }

    public static void executeSqlFile(String filePath) {
        InputStream resourceAsStream =
                Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(resourceAsStream));
        String sql = null;
        try {
            while ((sql = bufferedReader.readLine()) != null) {
                executeUpdate(sql);
            }
        } catch (IOException e) {
            LOGGER.error("sql: {}", sql);
            LOGGER.error("executeSqlFile failure: ", e);

        }
    }

    //    public static void closeConnection() {
//        Connection connection = CONNECTION_HOLDER.get();
//        if (connection != null) {
//            try {
//                connection.close();
//            } catch (SQLException e) {
//                LOGGER.error("error connection failure", e);
//            } finally {
//                CONNECTION_HOLDER.remove();
//            }
//
//        }
//    }
    public static void beginTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.setAutoCommit(false);

            } catch (SQLException e) {
                LOGGER.error("beginTransaction failure: ", e);
                throw  e;
            } finally {
                CONNECTION_HOLDER.set(conn);
            }
        }
    }

    public static void commitdTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.commit();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("commitdTransaction failure: ", e);
                throw e;
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }

    public static void rollbackTransaction() throws SQLException {
        Connection conn = getConnection();
        if (conn != null) {
            try {
                conn.rollback();
                conn.close();
            } catch (SQLException e) {
                LOGGER.error("rollbackTransaction failure: ", e);
                throw e;
            } finally {
                CONNECTION_HOLDER.remove();
            }
        }
    }
}
