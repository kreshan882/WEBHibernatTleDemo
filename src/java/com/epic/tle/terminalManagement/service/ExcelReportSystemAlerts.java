package com.epic.tle.terminalManagement.service;

import com.epic.tle.mapping.EpicTleAlerts;
import com.epic.tle.mapping.EpicTleResponsecode;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.systemtools.bean.SystemAlertsDataBean;
import com.epic.tle.systemtools.bean.SystemAlertsInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
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
 * @author nipun_t
 */
public class ExcelReportSystemAlerts {

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

    public static Object generateExcelReport() throws Exception {
        Session session = null;
        Object returnObject = null;
        try {
            String directory = Util.getOSLogPath("/tmp/systemAlertsTemporary");

            File file = new File(directory);
            deleteDir(file);
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            int count = 0;
            String sqlCount = "select count(tid) from EpicTleAlerts";
            Query queryCount = session.createQuery(sqlCount);
            

            if (queryCount.uniqueResult() != null) {
                count = ((Number) queryCount.uniqueResult()).intValue();
                if (count == 0) {
                    XSSFWorkbook workbook = new XSSFWorkbook();
                    XSSFSheet sheet = workbook.createSheet("System Alert Report");
                    sheet.autoSizeColumn(count);
                    ExcelReportSystemAlerts.createExcelTableHeaderSection(workbook, 0);
                    returnObject = workbook;
                }
            }
            if (count > 0) {
                long maxRow = Long.parseLong("10000");
                int currRow = headerRowCount;
                int fileCount = 0;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("System Alert Report");
                currRow = ExcelReportSystemAlerts.createExcelTableHeaderSection(workbook, currRow);
                int selectRow = 10000;
                int numberOfTimes = count / selectRow;
                if ((count % selectRow) > 0) {
                    numberOfTimes += 1;
                }
                int from = 0;
                int listrownumber = 1;

                String sqlSearch = "from EpicTleAlerts";
                Query query = session.createQuery(sqlSearch);
                
                Iterator itSearch = query.iterate();
                query.setFirstResult(from);
                query.setMaxResults(selectRow);
                for (int i = 0; i < numberOfTimes; i++) {
                    while (itSearch.hasNext()) {
                        SystemAlertsDataBean databean = new SystemAlertsDataBean();
                        EpicTleAlerts objBean = (EpicTleAlerts) itSearch.next();

                        try {
                            databean.setSerialno(objBean.getSerialno());
                        } catch (NullPointerException npe) {
                            databean.setSerialno("--");
                        }
                        try {
                            databean.setAlerts(objBean.getAlertinformation());
                        } catch (NullPointerException npe) {
                            databean.setAlerts("--");
                        }
                        try {
                            databean.setDatetime(objBean.getDatetime().toString());
                        } catch (NullPointerException npe) {
                            databean.setDatetime("--");
                        }
                        try {
                            databean.setAlertType(objBean.getEpicTleAlertType().getDescription());
                        } catch (NullPointerException npe) {
                            databean.setAlertType("--");
                        }
                        try {
                            databean.setRisklevl(objBean.getEpicTleRiskLevel().getDescription() + "");
                        } catch (NullPointerException npe) {
                            databean.setRisklevl("--");
                        }
                        try {
                            databean.setConnectionip(objBean.getClientIp());
                        } catch (NullPointerException npe) {
                            databean.setConnectionip("--");
                        }
                        try {
                            databean.setCardBin(objBean.getCardBin());
                        } catch (NullPointerException npe) {
                            databean.setCardBin("--");
                        }
                        try {
                            if (objBean.getResponseCode() != null && objBean.getResponseCode() != "") {
                                EpicTleResponsecode resCode = (EpicTleResponsecode) session.load(EpicTleResponsecode.class, objBean.getResponseCode());
                                databean.setRespCode(resCode.getDescription());
                            } else {
                                databean.setRespCode("--");
                            }
//                        databean.setRespCode(objBean.getResponseCode());
                        } catch (Exception npe) {
                            databean.setRespCode("--");
                        }
                        try {
                            EpicTleStatus status = (EpicTleStatus) session.load(EpicTleStatus.class, objBean.getTleStatus());
                            databean.setTleStatus(status.getDescription());
                        } catch (Exception npe) {
                            databean.setTleStatus("--");
                        }
                        try {
                            databean.setMti(objBean.getMti());
                        } catch (NullPointerException npe) {
                            databean.setTleStatus("--");
                        }

                        databean.setFullCount(count);
                        if (currRow + 1 > maxRow) {
                            fileCount++;
                            ExcelReportSystemAlerts.writeTemporaryFile(workbook, fileCount, directory);
                            workbook = ExcelReportSystemAlerts.createExcelTopSection();
                            sheet = workbook.getSheetAt(0);
                            currRow = headerRowCount;
                            ExcelReportSystemAlerts.createExcelTableHeaderSection(workbook, currRow);
                        }
//                        currRow = ExcelReportSystemAlerts.createExcelTableBodySection(workbook, databean, currRow, listrownumber);
                        listrownumber++;
                    }

                    from = from + selectRow;
                }

                if (fileCount > 0) {
                    fileCount++;
                    ExcelReportSystemAlerts.writeTemporaryFile(workbook, fileCount, directory);
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

    private static XSSFWorkbook createExcelTopSection() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("System Alert Report");
        return workbook;
    }

    private static int createExcelTableHeaderSection(XSSFWorkbook workbook, int currrow) throws Exception {
        XSSFCellStyle columnHeaderCell = ExcelCommon.getColumnHeadeCell(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue("Risk Level");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(1);
        cell.setCellValue("Alert Type");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(2);
        cell.setCellValue("Alerts");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(3);
        cell.setCellValue("MTI");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(4);
        cell.setCellValue("Card Bin");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(5);
        cell.setCellValue("Client IP");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(6);
        cell.setCellValue("Response");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(7);
        cell.setCellValue("TLE Status");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(8);
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
                file = new File(directory + File.separator + "Syatem Alerts Report_" + fileCount + ".xlsx");
            } else {
                file = new File(directory + File.separator + "Syatem Alerts Report.xlsx");
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

    private static int createExcelTableBodySection(XSSFWorkbook workbook, SystemAlertsDataBean dataBean, int currrow, int rownumber) throws Exception {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue(dataBean.getRisklevl());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(1);
        cell.setCellValue(dataBean.getAlertType());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(2);
        cell.setCellValue(dataBean.getAlerts());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(3);
        cell.setCellValue(dataBean.getMti());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(4);
        cell.setCellValue(dataBean.getCardBin());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(5);
        cell.setCellValue(dataBean.getConnectionip());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(6);
        cell.setCellValue(dataBean.getRespCode());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(7);
        cell.setCellValue(dataBean.getTleStatus());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(8);
        cell.setCellValue(dataBean.getDatetime().toString());
        cell.setCellStyle(rowColumnCell);

        return currrow;
    }
}
