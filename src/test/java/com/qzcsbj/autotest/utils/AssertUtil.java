package com.qzcsbj.autotest.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import org.testng.Assert;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class AssertUtil {
    // 断言字段
    public static void getFieldsAssertRes(String actual, String assertFields){
        if (StringUtil.isNotNullAndEmpty(actual)){
            String key,value;
            Object document = Configuration.defaultConfiguration().jsonProvider().parse(actual);
            String[] assertFieldarr = assertFields.split(";");
            for (String assertField : assertFieldarr) {
                if (StringUtil.isNotNullAndEmpty(assertField)){
                    key = assertField.split("=")[0].trim();
                    value = assertField.split("=")[1].trim();
                    String key_real = JsonPath.read(document, key).toString();
                    Assert.assertEquals(key_real, value);
                } else {
                    Assert.fail("assertField is null or empty");
                }
            }
        } else {
            Assert.fail("actual is null or empty");
        }
    }
}
