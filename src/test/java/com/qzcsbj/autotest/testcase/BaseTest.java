package com.qzcsbj.autotest.testcase;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
// public class BaseTest extends BaseSuite {
public class BaseTest {
    // public static CloseableHttpClient httpClient;
    // public static BasicCookieStore basicCookieStore = new BasicCookieStore();
    // Header header = null;

    @BeforeTest
    public void beforeTest(){
        // httpClient = HttpClients.custom().setDefaultCookieStore(basicCookieStore).build();
        // header = new BasicHeader("User-Agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/92.0.4515.107 Safari/537.36");

        System.out.println(this.getClass().getSimpleName() + " -> " + new Throwable().getStackTrace()[0].getMethodName());
    }

    @AfterTest
    public void afterTest(){
        System.out.println(this.getClass().getSimpleName() + " -> " + new Throwable().getStackTrace()[0].getMethodName());
    }
}
