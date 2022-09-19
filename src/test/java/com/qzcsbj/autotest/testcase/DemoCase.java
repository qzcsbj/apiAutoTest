package com.qzcsbj.autotest.testcase;

import com.qzcsbj.autotest.entity.CaseData;
import com.qzcsbj.autotest.entity.Variable;
import com.qzcsbj.autotest.utils.ExcelUtil;
import com.qzcsbj.autotest.utils.VariableUtil;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @公众号 : 全栈测试笔记
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <>
 */
public class DemoCase extends BaseCase{
    @Parameters({ "excelPath", "dataSheetName", "variableSheetName"})
    @BeforeTest
    public void readDataFromExcel(@Optional("caseData/caseData.xlsx") String excelPath, @Optional("v4_scenesCase") String dataSheetName, @Optional("variables") String variableSheetName){
        cases = ExcelUtil.loadExcel(excelPath, dataSheetName, CaseData.class);
        variables = ExcelUtil.loadExcel(excelPath, variableSheetName, Variable.class);
        VariableUtil.loadVariablesToMap(variables);
    }

    @DataProvider(name = "datasFromExcel")
    public Iterator<Object[]> getCaseDatas(){
        // 说明：如果测试用例没有封装到对象中，那么可以循环excel单元格，把值可以放到map中（参见博客样例）
        List<Object[]> apiDataList = new ArrayList<Object[]>();
        for (CaseData caseData : cases){
            apiDataList.add(new Object[] { caseData });
        }
        return apiDataList.iterator();
    }
}
