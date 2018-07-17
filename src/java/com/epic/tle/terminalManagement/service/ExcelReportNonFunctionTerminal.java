/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.service;

import com.epic.tle.terminalManagement.service.*;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalDataBean;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author nipun_t
 */
public class ExcelReportNonFunctionTerminal {

    public static Object generateExcelReport(NonFunctionTerminalInputBean inputBean) throws Exception {
       
        Object returnObject = null;
        Session session = null;
        try {
            XSSFWorkbook workbook = ExcelReportNonFunctionTerminal.createExcelTopSection();

            List<NonFunctionTerminalDataBean> dataList = new ArrayList<NonFunctionTerminalDataBean>();

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            System.err.println(inputBean.getFromdate());
            System.err.println(inputBean.getTodate());
            Query querySearch;

//                String sqlSearch = "from EpicTleTerminal wu" ;
//                querySearch = session.createQuery(sqlSearch);
                String sqlSearch = "from EpicTleTerminal wu where wu.lasttxndate >= :beginDate and wu.lasttxndate <= :endDate order by wu.tid desc ";
                    querySearch = session.createQuery(sqlSearch);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    querySearch.setParameter("endDate", endDate);
            Iterator it = querySearch.iterate();

            while (it.hasNext()) {

                NonFunctionTerminalDataBean databean = new NonFunctionTerminalDataBean();
                EpicTleTerminal objBean = (EpicTleTerminal) it.next();
                try {
                    databean.setTid(objBean.getTid());
                    System.err.println(objBean.getTid());
                } catch (NullPointerException npe) {
                    databean.setTid("--");
                }
                try {
                    databean.setMid(objBean.getMid());
                } catch (NullPointerException npe) {
                    databean.setMid("--");
                }
                try {
                    databean.setSerialNo(objBean.getSerialNo());
                } catch (NullPointerException npe) {
                    databean.setSerialNo("--");
                }
                try {
                    databean.setTerminalBrand(objBean.getTerminalbrand());
                } catch (NullPointerException npe) {
                    databean.setTerminalBrand("--");
                }
                try {
                    databean.setName(objBean.getName());
                } catch (NullPointerException npe) {
                    databean.setName("--");
                }
                try {
                    databean.setBank(objBean.getBank());
                } catch (NullPointerException npe) {
                    databean.setBank("--");
                }
                try {
                    databean.setLocation(objBean.getLocation());
                } catch (NullPointerException npe) {
                    databean.setLocation("--");
                }
                try {
                    if (objBean.getRegdate() == null) {
                        databean.setRegisterDate("0000-00-00");
                    } else {
                        databean.setRegisterDate(objBean.getRegdate());
                    }
                } catch (NullPointerException npe) {
                    databean.setRegisterDate("--");
                }
                try {
                    if (objBean.getLasttxndate()== null) {
                        databean.setLastTransDate("0000-00-00");
                    } else {
                        databean.setLastTransDate(objBean.getLasttxndate().toString());
                    }
                } catch (NullPointerException npe) {
                    databean.setLastTransDate("--");
                }

                dataList.add(databean);

            }

            workbook = ExcelReportNonFunctionTerminal.createExcelTableBodySection(workbook, dataList);

            returnObject = workbook;

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.clear();
                session.getTransaction().commit();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return returnObject;
    }

    private static XSSFWorkbook createExcelTopSection() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Non Function Terminal");
        XSSFCellStyle fontBoldedUnderlinedCell = ExcelCommon.getColumnHeadeCell(workbook);

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("TID");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("MID");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(2);
        cell.setCellValue("Serial No");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("Terminal Brand");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(4);
        cell.setCellValue("Name");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("Bank");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("Location");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(7);
        cell.setCellValue("Register Date");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(8);
        cell.setCellValue("Last Transaction Date");
        cell.setCellStyle(fontBoldedUnderlinedCell);


        return workbook;
    }

    private static XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<NonFunctionTerminalDataBean> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getTid());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getMid());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getSerialNo());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getTerminalBrand());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(4);
            cell.setCellValue(dataList.get(i).getName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            cell.setCellValue(dataList.get(i).getBank());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getLocation());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(7);
            cell.setCellValue(dataList.get(i).getRegisterDate());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(8);
            cell.setCellValue(dataList.get(i).getLastTransDate());
            cell.setCellStyle(rowColumnCell);

        }

        return workbook;
    }
}
