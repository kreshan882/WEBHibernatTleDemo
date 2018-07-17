/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerBean;
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import java.util.List;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 * @author kasun_k
 */
public interface ExcelReportFieldEngineerServiceInf {
    public  Object generateExcelReport(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public  Object generateExcelReport1(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public  XSSFWorkbook createExcelTopSection() throws Exception;
    public  XSSFWorkbook createExcelTopSection1() throws Exception;
    public XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<RegisterFieldEngineerBean> dataList);
    public XSSFWorkbook createExcelTableBodySection1(XSSFWorkbook workbook, List<RegisterTerminalBean> dataList);
}
