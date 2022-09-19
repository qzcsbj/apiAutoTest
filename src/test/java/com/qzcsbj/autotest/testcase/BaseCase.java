package com.qzcsbj.autotest.testcase;

import com.alibaba.fastjson.JSONObject;
import com.qzcsbj.autotest.entity.CaseData;
import com.qzcsbj.autotest.entity.Variable;
import com.qzcsbj.autotest.utils.*;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.log4j.Logger;
import org.testng.annotations.*;
import org.testng.annotations.Optional;

import java.util.*;
import java.util.regex.Pattern;

/**
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class BaseCase{
    public static Logger logger = Logger.getLogger(BaseCase.class);

    // ip
    public static String ip;

    // 端口
    public static String port;

    // HttpClient对象
    // public static CloseableHttpClient httpClient;

    // public static BasicCookieStore basicCookieStore = new BasicCookieStore();
    // Header header = null;

    // 存放全局变量
    public static Map<String, String> globalVariableMap = new HashMap<String, String>();

    // 替换符
    public static Pattern replaceParamPattern = Pattern.compile("\\$\\{(.*?)\\}");

    // 保存所有用例对象
    public static List<CaseData> cases = new ArrayList<CaseData>();

    // 存放变量对象
    public static List<Variable> variables = new ArrayList<>();



    @Test(dataProvider = "datasFromExcel", timeOut = 5000)
    public void test(CaseData caseData){
        // 获取对象中的数据
        String url = caseData.getUrl();
        String requestType = caseData.getRequestType();
        String headers = caseData.getHeaders();
        // String cookies = caseData.getCookies();  // 暂未涉及
        String parameters = caseData.getParameters();
        // String uploadFile = caseData.getUploadFile();  // 暂未涉及
        String initSql = caseData.getInitSql();
        String globalVariables = caseData.getGlobalVariables();
        String assertFields = caseData.getAssertFields();


        url = "http://" + PropertiesUtil.getIp() + ":"+ PropertiesUtil.getPort() + url;

        // 执行初始化sql
        if (initSql!=null && initSql.trim().length()>0){
            // 替换sql中的参数
            initSql = VariableUtil.variableSubstitution(initSql);

            // 调用方法
            String initSqlRes = InitSqlUtil.doInitSql(initSql);
        }


        // 替换入参中的非关联参数
        parameters = VariableUtil.variableSubstitution(parameters);
        // 替换入参中的关联参数
        parameters = GlobalVariableUtil.substitutionGlobalVariable(parameters);

        String actual = null;
        JSONObject headsJsonObject = JSONObject.parseObject(headers);
        if (headsJsonObject!=null && "application/json".equals(headsJsonObject.getString("Content-Type"))){
            JSONObject paramJsonObject = JSONObject.parseObject(parameters);

            // 请求
            actual = HttpRequestJsonUtil.sendRequest(url, requestType, paramJsonObject,headsJsonObject);
            logger.info("json请求返回结果： " + actual);

        } else {
            HashMap<String, String> params = new HashMap<String, String>();
            JSONObject jsonObject = JSONObject.parseObject(parameters);
            Set<String> keys = jsonObject.keySet();
            for (String key : keys) {
                params.put(key, jsonObject.getString(key));
            }


            // 请求，获取结果
            actual = HttpRequestUtil.sendRequest(url, requestType, params);
            logger.info("k-v请求返回结果： " + actual);
        }

        // 是否需要保存全局变量
        if (StringUtil.isNotNullAndEmpty(globalVariables)){
            GlobalVariableUtil.saveGlobalVariable(actual, globalVariables);
        }

        // 是否需要断言关键字段
        if (StringUtil.isNotNullAndEmpty(assertFields)){
            AssertUtil.getFieldsAssertRes(actual, assertFields);
        }
    }
}
