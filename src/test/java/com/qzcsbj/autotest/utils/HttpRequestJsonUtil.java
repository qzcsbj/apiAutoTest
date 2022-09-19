package com.qzcsbj.autotest.utils;

import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Set;

/**
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <json方式：post请求>
 */
public class HttpRequestJsonUtil {

    // 声明为静态方法
    public static String postRequest(String url, JSONObject jsonObject, JSONObject headers){
        String res = "";
        HttpPost httpPost = new HttpPost(url);
        // 通过形参设置请求头
        Set<String> headerkeys = headers.keySet();
        for (String headerkey : headerkeys) {
            httpPost.addHeader(headerkey.trim(),headers.getString(headerkey).trim());
        }
        // 发送 json 类型数据
        httpPost.setEntity(new StringEntity(jsonObject.toString(),"UTF-8"));

        CloseableHttpClient httpClient = HttpClients.createDefault();
        // 发送请求
        HttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpPost);
            res = EntityUtils.toString(httpResponse.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return res;
    }

    public static String sendRequest(String url, String requestType, JSONObject jsonObject, JSONObject headers){
        String response = "";
        if ("post".equalsIgnoreCase(requestType)){
            response = postRequest(url, jsonObject, headers);
        }else {
            response = "error request type!!!";
        }
        return response;
    }
}
