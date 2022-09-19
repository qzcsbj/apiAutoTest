package com.qzcsbj.autotest.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.qzcsbj.autotest.entity.InitSqlRes;
import com.qzcsbj.autotest.entity.Sql;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class InitSqlUtil {
    public static Logger logger = Logger.getLogger(InitSqlUtil.class);

    public static String doInitSql(String initSql) {
        // 定义存放sql执行结果的list【执行结果暂未使用】
        List<InitSqlRes> initSqlResList = new ArrayList<>();
        // 初始化sql解析为对象
        List<Sql> sqls = JSONObject.parseArray(initSql, Sql.class);
        // 遍历获取sql，并执行
        for (Sql sql_ : sqls) {
            String sqlNo = sql_.getSqlNo();
            String sql = sql_.getSql().trim();
            String prefix = sql.split(" ")[0].trim().toLowerCase();
            if (prefix.startsWith("select")){
                logger.info("=====执行select语句：" + sql_);
                Map<String, Object> titleAndCellValue = JDBCUtil.query(sql);

                // 结果封装到初始化结果对象中
                InitSqlRes initSqlRes = new InitSqlRes();
                initSqlRes.setNo(sqlNo);
                initSqlRes.setTitleAndCellValue(titleAndCellValue);

                // 添加到集合
                initSqlResList.add(initSqlRes);

            } else if (prefix.startsWith("delete")){
                logger.info("=====执行delete语句：" + sql_);
                JDBCUtil.nonQuery(sql);
            } else if (prefix.startsWith("update")){
                logger.info("=====执行update语句：" + sql_);
                JDBCUtil.nonQuery(sql);
            } else if (prefix.startsWith("insert")){
                logger.info("=====执行insert语句：" + sql_);
                JDBCUtil.nonQuery(sql);
            } else {
                logger.info("=====sql语句【"+ sql_ +"】不符合规范");
            }
        }

        return JSON.toJSONString(initSqlResList);
    }
}
