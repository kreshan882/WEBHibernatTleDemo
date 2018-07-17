/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleProcessingTime;
import com.epic.tle.mapping.EpicTleResponsecode;
import com.epic.tle.systemtools.bean.SystemProcessDataBean;
import com.epic.tle.systemtools.bean.SystemProcessInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author thilina_t
 */
public class ExcelReportSystemProcess {

    private static final int columnCount = 1;
    private static final int headerRowCount = 0;

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }

    public static Object generateExcelReport(SystemProcessInputBean inputBean) throws Exception {
        Session session = null;
        Object returnObject = null;
        Query queryCount = null;
        Query query = null;
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
        try {

            String directory = Util.getOSLogPath("/tmp/systemProcessTemporary");

            File file = new File(directory);
            deleteDir(file);
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            int count = 0;

            if (!inputBean.getSelectNode().equals("-1")) {

                if (!inputBean.getTransTID().isEmpty()) {
                    String sqlCount="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                         sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                        queryCount = session.createQuery(sqlCount);
                    }
                    else{
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                        queryCount = session.createQuery(sqlCount);
                        queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    queryCount.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                    queryCount = session.createQuery(sqlCount);
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                } else {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                    queryCount = session.createQuery(sqlCount);
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    //queryCount.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                    //queryCount.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                    queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                    queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                }

            } else {

                if (!inputBean.getTransTID().isEmpty()) {
                     String sqlCount="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                        queryCount = session.createQuery(sqlCount);
                    }
                    else{
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                        queryCount = session.createQuery(sqlCount);
                        queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                    queryCount.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                    queryCount = session.createQuery(sqlCount);
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                } else {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                    queryCount = session.createQuery(sqlCount);
                    //queryCount.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                    //queryCount.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                    queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                    queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);

                }

            }

            if (queryCount.uniqueResult() != null) {
                count = ((Number) queryCount.uniqueResult()).intValue();
                if (count == 0) {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("System Transaction Time Report");
                    sheet.autoSizeColumn(count);
                    ExcelReportSystemProcess.createExcelTableHeaderSection(workbook, 0);
                    returnObject = workbook;
                }
            }
            if (count > 0) {
                long maxRow = Long.parseLong("10000");
                int currRow = headerRowCount;
                int fileCount = 0;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("System Transaction Time Report");
                currRow = ExcelReportSystemProcess.createExcelTableHeaderSection(workbook, currRow);
                int selectRow = 10000;
                int numberOfTimes = count / selectRow;
                if ((count % selectRow) > 0) {
                    numberOfTimes += 1;
                }
                int from = 0;
                int listrownumber = 1;

                if (!inputBean.getSelectNode().equals("-1")) {

                    if (!inputBean.getTransTID().isEmpty()) {
                        String sqlSearch="";
                        if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                            sqlSearch = "from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                            query = session.createQuery(sqlSearch);
                        }
                        else{
                            sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                            query = session.createQuery(sqlSearch);
                            query.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                            query.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                        }
                        query.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        query.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                        String sqlSearch = "from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                        query = session.createQuery(sqlSearch);
                        query.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    } else {

                        String sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node";
                        query = session.createQuery(sqlSearch);
                        query.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        //query.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                        //query.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                        query.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        query.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    }

                } else {

                    if (!inputBean.getTransTID().isEmpty()) {
                        String sqlSearch="";
                        if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                            sqlSearch = "from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                            query = session.createQuery(sqlSearch);
                        }
                        else{
                            sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                            query = session.createQuery(sqlSearch);
                            query.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                            query.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                        }
                        query.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                        String sqlSearch = "from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                        query = session.createQuery(sqlSearch);
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    } else {

                        String sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                        query = session.createQuery(sqlSearch);
                       // query.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                        //query.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                        query.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        query.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        query.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        query.setParameter("endDate", endDate);

                    }

                }

                Iterator itSearch = query.iterate();
                query.setFirstResult(from);
                query.setMaxResults(selectRow);
                for (int i = 0; i < numberOfTimes; i++) {
                    while (itSearch.hasNext()) {
                        SystemProcessDataBean databean = new SystemProcessDataBean();
                        EpicTleProcessingTime objBean = (EpicTleProcessingTime) itSearch.next();

                        try {
                            if (!objBean.getResponseCode().isEmpty() && objBean.getResponseCode() != "") {
                                databean.setResponseCode(objBean.getResponseCode());
                            } else {
                                databean.setResponseCode("--");
                            }
                        } catch (NullPointerException npe) {
                            databean.setResponseCode("--");
                        }
                        try {
                            databean.setTid(objBean.getTid());
                        } catch (NullPointerException npe) {
                            databean.setTid("--");
                        }
                        try {
                            databean.setHostTime(objBean.getHostTime() + "");
                        } catch (NullPointerException npe) {
                            databean.setHostTime("--");
                        }
                        try {
                            databean.setTleTime(objBean.getTleTime() + "");
                        } catch (NullPointerException npe) {
                            databean.setTleTime("--");
                        }
                        try {
                            databean.setTotalTime(objBean.getTotalTime() + "");
                        } catch (NullPointerException npe) {
                            databean.setTleTime("--");
                        }
                        try {
                            databean.setEpicTleTxntypes(objBean.getEpicTleTxntypes().getDescription());
                        } catch (NullPointerException npe) {
                            databean.setEpicTleTxntypes("--");
                        }
                        try {
                            databean.setTraceNo(objBean.getTraceNo());
                        } catch (NullPointerException npe) {
                            databean.setTraceNo("--");
                        }
                        try {
                            databean.setBin(objBean.getBin());
                        } catch (NullPointerException npe) {
                            databean.setBin("--");
                        }
                        try {
                            databean.setEpicTleStatus(objBean.getEpicTleStatus().getDescription());
                        } catch (NullPointerException npe) {
                            databean.setEpicTleStatus("--");
                        }
                        try {
                            databean.setDatetime(objBean.getDatetime().toString());
                        } catch (NullPointerException npe) {
                            databean.setDatetime("--");
                        }
                        try {
                            databean.setNode(objBean.getEpicTleNodetype().getDescription());
                        } catch (NullPointerException npe) {
                            databean.setNode("--");
                        }
                        databean.setFullCount(count);
                        if (currRow + 1 > maxRow) {
                            fileCount++;
                            ExcelReportSystemProcess.writeTemporaryFile(workbook, fileCount, directory);
                            workbook = ExcelReportSystemProcess.createExcelTopSection(inputBean);
                            sheet = workbook.getSheetAt(0);
                            currRow = headerRowCount;
                            ExcelReportSystemProcess.createExcelTableHeaderSection(workbook, currRow);
                        }
                        currRow = ExcelReportSystemProcess.createExcelTableBodySection(workbook, databean, currRow, listrownumber);
                        listrownumber++;
                    }

                    from = from + selectRow;
                }

                if (fileCount > 0) {
                    fileCount++;
                    ExcelReportSystemProcess.writeTemporaryFile(workbook, fileCount, directory);
                    ByteArrayOutputStream outputStream = ExcelCommon.zipFiles(file.listFiles());
                    returnObject = outputStream;
                } else {
                    for (int i = 0; i < columnCount; i++) {
                        //to auto size all column in the sheet
                        sheet.autoSizeColumn(i);
                    }
                    returnObject = workbook;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.close();
            }
        }
        return returnObject;
    }

    private static XSSFWorkbook createExcelTopSection(SystemProcessInputBean inputBean) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("System Transaction Time Report");
        return workbook;
    }

    private static int createExcelTableHeaderSection(XSSFWorkbook workbook, int currrow) throws Exception {
        XSSFCellStyle columnHeaderCell = ExcelCommon.getColumnHeadeCell(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue("Response");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(1);
        cell.setCellValue("Node");
        cell.setCellStyle(columnHeaderCell);
        
        cell = row.createCell(2);
        cell.setCellValue("TID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(3);
        cell.setCellValue("TLE Time");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(4);
        cell.setCellValue("Host Time");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(5);
        cell.setCellValue("Full Time");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(6);
        cell.setCellValue("TXN Type");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(7);
        cell.setCellValue("Trace No.");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(8);
        cell.setCellValue("BIN");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(9);
        cell.setCellValue("TLE Status");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(10);
        cell.setCellValue("Date/ Time");
        cell.setCellStyle(columnHeaderCell);

        return currrow;
    }

    private static void writeTemporaryFile(XSSFWorkbook workbook, int fileCount, String directory) throws Exception {
        File file;
        FileOutputStream outputStream = null;
        try {
            XSSFSheet sheet = workbook.getSheetAt(0);
            for (int i = 0; i < columnCount; i++) {
                //to auto size all column in the sheet
                sheet.autoSizeColumn(i);
            }

            file = new File(directory);
            if (!file.exists()) {
                System.out.println("Directory created or not : " + file.mkdirs());
            }
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getCanonicalPath());
            if (fileCount > 0) {
                file = new File(directory + File.separator + "Syatem Transaction Time Report_" + fileCount + ".xlsx");
            } else {
                file = new File(directory + File.separator + "Syatem Transaction Time Report.xlsx");
            }
            outputStream = new FileOutputStream(file);
            workbook.write(outputStream);
        } catch (IOException e) {
            throw e;
        } finally {
            if (outputStream != null) {
                outputStream.flush();
                outputStream.close();
            }
        }
    }

    private static int createExcelTableBodySection(XSSFWorkbook workbook, SystemProcessDataBean dataBean, int currrow, int rownumber) throws Exception {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue(dataBean.getResponseCode());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(1);
        cell.setCellValue(dataBean.getNode());
        cell.setCellStyle(rowColumnCell);
        
        cell = row.createCell(2);
        cell.setCellValue(dataBean.getTid());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(3);
        cell.setCellValue(dataBean.getTleTime());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(4);
        cell.setCellValue(dataBean.getHostTime());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(5);
        cell.setCellValue(dataBean.getTotalTime());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(6);
        cell.setCellValue(dataBean.getEpicTleTxntypes());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(7);
        cell.setCellValue(dataBean.getTraceNo());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(8);
        cell.setCellValue(dataBean.getBin());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(9);
        cell.setCellValue(dataBean.getEpicTleStatus());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(10);
        cell.setCellValue(dataBean.getDatetime());
        cell.setCellStyle(rowColumnCell);

        return currrow;
    }

}
