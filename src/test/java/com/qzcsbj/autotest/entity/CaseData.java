package com.qzcsbj.autotest.entity;

/**
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <case类>
 */
public class CaseData {
    private String caseId;
    private String apiName;
    private String describe;
    private String url;
    private String requestType;
    private String headers;
    private String cookies;
    private String parameters;
    private String uploadFile;
    private String initSql;
    private String globalVariables;
    private String assertFields;

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }

    public String getApiName() {
        return apiName;
    }

    public void setApiName(String apiName) {
        this.apiName = apiName;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getRequestType() {
        return requestType;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getHeaders() {
        return headers;
    }

    public void setHeaders(String headers) {
        this.headers = headers;
    }

    public String getCookies() {
        return cookies;
    }

    public void setCookies(String cookies) {
        this.cookies = cookies;
    }

    public String getParameters() {
        return parameters;
    }

    public void setParameters(String parameters) {
        this.parameters = parameters;
    }

    public String getUploadFile() {
        return uploadFile;
    }

    public void setUploadFile(String uploadFile) {
        this.uploadFile = uploadFile;
    }

    public String getInitSql() {
        return initSql;
    }

    public void setInitSql(String initSql) {
        this.initSql = initSql;
    }

    public String getGlobalVariables() {
        return globalVariables;
    }

    public void setGlobalVariables(String globalVariables) {
        this.globalVariables = globalVariables;
    }

    public String getAssertFields() {
        return assertFields;
    }

    public void setAssertFields(String assertFields) {
        this.assertFields = assertFields;
    }

    @Override
    public String toString() {
        return "CaseData{" +
                "caseId='" + caseId + '\'' +
                ", apiName='" + apiName + '\'' +
                ", describe='" + describe + '\'' +
                ", url='" + url + '\'' +
                ", requestType='" + requestType + '\'' +
                ", headers='" + headers + '\'' +
                ", cookies='" + cookies + '\'' +
                ", parameters='" + parameters + '\'' +
                ", uploadFile='" + uploadFile + '\'' +
                ", initSql='" + initSql + '\'' +
                ", globalVariables='" + globalVariables + '\'' +
                ", assertFields='" + assertFields + '\'' +
                '}';
    }
}

