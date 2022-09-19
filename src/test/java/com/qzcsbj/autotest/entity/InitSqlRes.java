package com.qzcsbj.autotest.entity;

import java.util.Map;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <数据库执行结果实体类>
 */
public class InitSqlRes {
    private String no;  // sql编号
    private Map<String, Object> titleAndCellValue;  // sql执行结果，key是字段名，value是对应字段的查询结果

    public String getNo() {
        return no;
    }

    public void setNo(String no) {
        this.no = no;
    }

    public Map<String, Object> getTitleAndCellValue() {
        return titleAndCellValue;
    }

    public void setTitleAndCellValue(Map<String, Object> titleAndCellValue) {
        this.titleAndCellValue = titleAndCellValue;
    }
}
