package com.qzcsbj.autotest.utils;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class StringUtil {
    public static boolean isNotNullAndEmpty(String s){
        return s!=null && s.trim().length()!=0;
    }
}
