package com.qzcsbj.autotest.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <k-v方式：get、post请求>
 */
public class HttpRequestUtil {
    public static String getRequest(String url, Map<String,String> params){
        String res = "";
        boolean flag = true;
        Set<String> keys = params.keySet();
        for (String key : keys) {
            if (flag){
                url += "?" + key + "=" + params.get(key);
                flag = false;
            }else {
                url += "&" + key + "=" + params.get(key);
            }
        }
        HttpGet httpGet = new HttpGet(url);
        HttpClient httpClient = HttpClients.createDefault();

        try {
            HttpResponse response = httpClient.execute(httpGet);
            res = EntityUtils.toString(response.getEntity());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                ((CloseableHttpClient) httpClient).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String postRequest(String url, Map<String,String> params){
        String res = "";
        HttpPost httpPost = new HttpPost(url);
        ArrayList<BasicNameValuePair> basicNameValuePairs = new ArrayList<BasicNameValuePair>();

        // 遍历map，放到basicNameValuePairs中
        Set<String> keys = params.keySet();
        for (String key : keys) {
            basicNameValuePairs.add(new BasicNameValuePair(key,params.get(key)));
        }
        HttpClient httpClient = HttpClients.createDefault();
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(basicNameValuePairs,"utf-8"));
            HttpResponse httpResponse = httpClient.execute(httpPost);
            res = EntityUtils.toString(httpResponse.getEntity());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                ((CloseableHttpClient) httpClient).close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public static String sendRequest(String url, String requestType, Map<String, String> parameters){
        String response = "";
        if ("get".equalsIgnoreCase(requestType)){
            response = getRequest(url, parameters);
        }else if ("post".equalsIgnoreCase(requestType)){
            response = postRequest(url, parameters);
        }else {
            response = "error request type!!!";
        }
        return response;
    }
}
