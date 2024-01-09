package com.qzcsbj.autotest.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class PropertiesUtil {
    public static Properties properties = new Properties();
    static {
        try {
            InputStream in = PropertiesUtil.class.getClassLoader().getResourceAsStream("config.properties");
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // public static String getExcelPath(){
    //     return properties.getProperty("excelPath").trim();
    // }

    // public static String[] getCaseSheetName(){
    //     String sheetNames = properties.getProperty("caseSheetName");
    //     String[] sheetArr = sheetNames.split(",");
    //     return sheetArr;
    // }

    // public static String getCaseSheetName(){
    //     return properties.getProperty("caseSheetName");
    // }


    public static String getIp(){
        return properties.getProperty("project.ip");
    }

    public static String getPort(){
        return properties.getProperty("project.port").trim();
    }

}
