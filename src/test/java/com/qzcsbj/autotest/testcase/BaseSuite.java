package com.qzcsbj.autotest.testcase;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class BaseSuite {
    @BeforeSuite
    public void beforeSuite(){
        System.out.println(this.getClass().getSimpleName() + " -> " + new Throwable().getStackTrace()[0].getMethodName());
    }

    @AfterSuite
    public void afterSuite(){
        System.out.println(this.getClass().getSimpleName() + " -> " + new Throwable().getStackTrace()[0].getMethodName());

    }
}
