package com.qzcsbj.autotest.utils;


import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @博客 : www.cnblogs.com/uncleyong
 * @微信 : ren168632201
 * @描述 : <解析指定excel表单的数据>
 */
public class ExcelUtil {
    public static Logger logger = Logger.getLogger(ExcelUtil.class);

    public static <T> List<T> loadExcel(String excelPath, String sheetName, Class<T> clazz){
        logger.info("===================开始读取sheet: " + sheetName);
        // 创建一个list
        List<T> list = new ArrayList<T>();
        InputStream in = null;
        // 创建WorkBook对象
        try {
            File file = new File(excelPath);
            in = new FileInputStream(file);
            Workbook workbook = WorkbookFactory.create(in);
            // 获取sheet对象
            Sheet sheet = workbook.getSheet(sheetName);
            // 获取第一行
            Row firstRow = sheet.getRow(0);
            // 获取最后一列的列号
            int lastCellNum = firstRow.getLastCellNum();

            // 表头
            String[] titles = new String[lastCellNum];
            // 将表头放入数组
            for (int i = 0; i < lastCellNum; i++) {
                Cell cell = firstRow.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                // 获取单元格的值
                String title = cell.getStringCellValue();
                // 值保存到数组
                titles[i] = title;
            }

            int lastRowNum = sheet.getLastRowNum();
            for (int i = 1; i <= lastRowNum  ; i++) {
                T obj = clazz.newInstance();
                Row rowData = sheet.getRow(i);
                if (rowData==null || rowDataIsEmpty(rowData)){
                    continue;
                }
                for (int j = 0; j < lastCellNum ; j++) {
                    Cell cell = rowData.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                    cell.setCellType(CellType.STRING);
                    String cellValue = cell.getStringCellValue();
                    String methodName = "set" + titles[j];
                    Method method = clazz.getMethod(methodName, String.class);
                    // 反射调用
                    method.invoke(obj, cellValue);
                }
                list.add(obj);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (in!=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        logger.info("===================读取sheet完成: " + sheetName);
        return list;
    }

    /**
     * 判断数据是否是空
     * @param
     * @return
     */
    public static boolean rowDataIsEmpty(Row rowData) {
        int lastCellNum = rowData.getLastCellNum();
        for (int i = 0; i < lastCellNum; i++) {
            Cell cell = rowData.getCell(i, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
            cell.setCellType(CellType.STRING);
            String cellValue = cell.getStringCellValue();
            if (cellValue!=null && cellValue.trim().length()>0){
                return false;
            }
        }
        return true;
    }
}
