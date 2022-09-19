package com.qzcsbj.autotest.utils;

import com.jayway.jsonpath.Configuration;
import com.jayway.jsonpath.JsonPath;
import com.qzcsbj.autotest.testcase.BaseCase;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.regex.Matcher;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class GlobalVariableUtil {
    public static Logger logger = Logger.getLogger(GlobalVariableUtil.class);

    /**
     * 解析全局变量，并更新全局变量的值
     * @param
     * @return
     */
    public static void saveGlobalVariable(String res, String globalVariables){
        if (null == res || "".equals(res) || null == globalVariables || "".equals(globalVariables)) {
            return;
        }
        String key,value;
        Object document = Configuration.defaultConfiguration().jsonProvider().parse(res);
        String[] globalVariablearr = globalVariables.split(";");
        for (String globalVariable : globalVariablearr) {
            if (StringUtil.isNotNullAndEmpty(globalVariable)){
                key = globalVariable.split("=")[0].trim();
                value = globalVariable.split("=")[1].trim();
                String value_real = JsonPath.read(document, value);
                BaseCase.globalVariableMap.put(key, value_real);
            }
        }
    }

    /**
     * 从全局map中获取全局变量并替换
     * @param
     * @return
     */
    public static String substitutionGlobalVariable(String param){
        if (!StringUtil.isNotNullAndEmpty(param)){
            return "";
        }
        Matcher matcher = BaseCase.replaceParamPattern.matcher(param);
        while (matcher.find()) {
            String replaceKey = matcher.group(1).trim();
            String value;
            if ("".equals(replaceKey) || !BaseCase.globalVariableMap.containsKey(replaceKey)){
                value = null;
                Assert.fail("替换失败");
            } else {
                value = BaseCase.globalVariableMap.get(replaceKey);
            }
            param = param.replace(matcher.group(), value);
        }
        return param;
    }
}
