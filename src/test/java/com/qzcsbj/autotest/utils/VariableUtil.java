package com.qzcsbj.autotest.utils;

import com.qzcsbj.autotest.entity.Variable;
import org.apache.log4j.Logger;
import org.testng.Assert;

import java.util.*;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class VariableUtil {
    public static Logger logger = Logger.getLogger(VariableUtil.class);

    // 存放变量和值
    public static Map<String, String> variableMap = new HashMap<>();

    // 从对象列表variableList中获取变量和值，放到map中
    public static void loadVariablesToMap(List<Variable> variableList) {
        for (Variable variable : variableList){
            String name = variable.getName();
            String value = variable.getValue();
            variableMap.put(name,value);
        }
    }

    /**
     * 替换变量
     * @param
     * @return
     */
    public static String variableSubstitution(String parameters) {
        Set<String> names = variableMap.keySet();
        for (String name : names) {
            if (parameters.contains(name)){
                parameters = parameters.replace(name,variableMap.get(name));
            }
        }
        return parameters;
    }
}
