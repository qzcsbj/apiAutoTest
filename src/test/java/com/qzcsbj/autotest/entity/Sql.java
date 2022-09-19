package com.qzcsbj.autotest.entity;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class Sql {
    private String sqlNo;
    private String sql;

    public String getSqlNo() {
        return sqlNo;
    }

    public void setSqlNo(String sqlNo) {
        this.sqlNo = sqlNo;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return "Sql{" +
                "sqlNo='" + sqlNo + '\'' +
                ", sql='" + sql + '\'' +
                '}';
    }
}
