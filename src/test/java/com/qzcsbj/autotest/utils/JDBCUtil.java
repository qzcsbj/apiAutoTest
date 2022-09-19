package com.qzcsbj.autotest.utils;

import org.apache.log4j.Logger;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.Set;


/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class JDBCUtil {
    public static Logger logger = Logger.getLogger(JDBCUtil.class);

    public static Properties properties = new Properties();
    static {
        // 解析jdbc.properties
        try {
            InputStream in = JDBCUtil.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(in);
        } catch (IOException e) {
            logger.info("解析发生异常。");
            e.printStackTrace();
        }
    }


    /**
     * 查询
     * @param
     * @return
     */
    public static Map<String,Object> query(String sql){
        HashMap<String, Object> stringObjectHashMap = null;
        try {
            Connection connection = getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            // 获取查询相关的信息
            ResultSetMetaData metaData = resultSet.getMetaData();
            // 获取sql中有多少个查询字段
            int columnCount = metaData.getColumnCount();
            // System.out.println("查询字段数为：" + columnCount);

            stringObjectHashMap = new HashMap<String, Object>();
            // 从结果集获取查询数据
            while (resultSet.next()){
                for (int i = 1; i <= columnCount; i++) {
                    String columnLabel = metaData.getColumnLabel(i);
                    String columnValue = resultSet.getObject(columnLabel).toString();
                      stringObjectHashMap.put(columnLabel, columnValue);
                }
            }

            logger.info("查询结果：" + stringObjectHashMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringObjectHashMap;
    }


    /**
     * 非查询
     * @param
     * @return
     */
    public static void nonQuery(String sql){
        try {
            // 1、获取连接
            Connection connection = getConnection();
            // 2、获取statement
            Statement statement = connection.createStatement();
            // 3、调用非查询方法
            int n = statement.executeUpdate(sql);
            if (n>0){
                logger.info("操作成功数据条数：【" + n + "】");
            } else {
                logger.info("操作成功数据条数：【0】");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接
     * @param
     * @return
     */
    public static Connection getConnection() throws SQLException {
        String url = properties.getProperty("jdbc.url");
        String username = properties.getProperty("jdbc.username");
        String password = properties.getProperty("jdbc.password");
        Connection connection = DriverManager.getConnection(url, username, password);
        return connection;
    }
}