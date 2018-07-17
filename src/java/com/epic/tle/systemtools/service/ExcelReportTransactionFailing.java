package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleAlerts;
import com.epic.tle.systemtools.bean.SystemTnxFailDataBean;
import com.epic.tle.systemtools.bean.SystemTnxFailInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
public class ExcelReportTransactionFailing {

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

    public static Object generateExcelReport(SystemTnxFailInputBean inputBean) throws Exception {
        Session session = null;
        Object returnObject = null;
        try {
            String directory = Util.getOSLogPath("/tmp/transactionFailureTemporary");

            File file = new File(directory);
            deleteDir(file);
            session = HibernateInit.sessionFactory.openSession();

            int count = 0;
            String sqlCount = "select count(sid) from EpicTleAlerts txn where txn.tid  LIKE:tid";
            Query queryCount = session.createQuery(sqlCount);
            queryCount.setParameter("tid", "%" + inputBean.getTid().trim() + "%");

            if (queryCount.uniqueResult() != null) {
                count = ((Number) queryCount.uniqueResult()).intValue();
            }
            if (count > 0) {
                long maxRow = Long.parseLong("10000");
                int currRow = headerRowCount;
                int fileCount = 0;
                XSSFWorkbook workbook = new XSSFWorkbook();
                XSSFSheet sheet = workbook.createSheet("Transaction Failing Report");
                currRow = ExcelReportTransactionFailing.createExcelTableHeaderSection(workbook, currRow);
                int selectRow = 10000;
                int numberOfTimes = count / selectRow;
                if ((count % selectRow) > 0) {
                    numberOfTimes += 1;
                }
                int from = 0;
                int listrownumber = 1;

                String sqlSearch = "from EpicTleAlerts txn where txn.tid  LIKE:tid";
                Query query = session.createQuery(sqlSearch);
                query.setParameter("tid", "%" + inputBean.getTid().trim() + "%");

                Iterator itSearch = query.iterate();
                query.setFirstResult(from);
                query.setMaxResults(selectRow);
                for (int i = 0; i < numberOfTimes; i++) {
                    while (itSearch.hasNext()) {
                        SystemTnxFailDataBean dataBean = new SystemTnxFailDataBean();
                        EpicTleAlerts tnxFailTable = (EpicTleAlerts) itSearch.next();
                        if (tnxFailTable.getEpicTleAlertType().getCode() == 2) {
                            try {
                                dataBean.setSid(tnxFailTable.getSid().toString());
                            } catch (NullPointerException npex) {
                                dataBean.setSid("--");
                            }
                            try {
                                dataBean.setTid(tnxFailTable.getTid());
                            } catch (NullPointerException npex) {
                                dataBean.setTid("--");
                            }
                            try {
                                dataBean.setSerialno(tnxFailTable.getSerialno());
                            } catch (NullPointerException npex) {
                                dataBean.setSerialno("--");
                            }
                            try {
                                dataBean.setDescription(tnxFailTable.getAlertinformation());
                            } catch (NullPointerException npex) {
                                dataBean.setDescription("--");
                            }
                            try {
                                dataBean.setTnxtpe(inputBean.getTxnTypeMap().get("" + tnxFailTable.getEpicTleTxntypes()));
                            } catch (NullPointerException npex) {
                                dataBean.setTnxtpe("--");
                            }
                            try {
//                                dataBean.setResponsecode(tnxFailTable.getEpicTleResponsecode().getDescription());
                            } catch (NullPointerException npex) {
                                dataBean.setResponsecode("--");
                            }
                            try {
//                            dataBean.setEncmode(inputBean.getEncModeMap().get(tnxFailTable.getApplyenmode().toString()));
                            } catch (NullPointerException npex) {
                                dataBean.setEncmode("--");
                            }
                            try {
//                            dataBean.setEncalgo(inputBean.getEncAlgoMap().get(tnxFailTable.getEnalogo().toString()));
                            } catch (NullPointerException npex) {
                                dataBean.setEncalgo("--");
                            }
                            try {
//                            dataBean.setDiviceip(tnxFailTable.getDeviceip());
                            } catch (NullPointerException npex) {
                                dataBean.setDiviceip("--");
                            }
                            try {
                                dataBean.setConnectionip(tnxFailTable.getClientIp());
                            } catch (NullPointerException npex) {
                                dataBean.setConnectionip("--");
                            }
                            try {
                                dataBean.setDatetime(tnxFailTable.getDatetime());
                            } catch (NullPointerException npex) {
                                dataBean.setDatetime(null);
                            }

                            dataBean.setFullCount(count);
                            if (currRow + 1 > maxRow) {
                                fileCount++;
                                ExcelReportTransactionFailing.writeTemporaryFile(workbook, fileCount, directory);
                                workbook = ExcelReportTransactionFailing.createExcelTopSection(inputBean);
                                sheet = workbook.getSheetAt(0);
                                currRow = headerRowCount;
                                ExcelReportTransactionFailing.createExcelTableHeaderSection(workbook, currRow);
                            }
                            currRow = ExcelReportTransactionFailing.createExcelTableBodySection(workbook, dataBean, currRow, listrownumber);
                            listrownumber++;
                        }
                    }

                    from = from + selectRow;
                }

                if (fileCount > 0) {
                    fileCount++;
                    ExcelReportTransactionFailing.writeTemporaryFile(workbook, fileCount, directory);
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
                session.close();
            }
        }
        return returnObject;
    }

    private static XSSFWorkbook createExcelTopSection(SystemTnxFailInputBean inputBean) throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Transaction Failing Report");
        return workbook;
    }

    private static int createExcelTableHeaderSection(XSSFWorkbook workbook, int currrow) throws Exception {
        XSSFCellStyle columnHeaderCell = ExcelCommon.getColumnHeadeCell(workbook);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue("SID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(1);
        cell.setCellValue("TID");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(2);
        cell.setCellValue("Serial No");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(3);
        cell.setCellValue("Date/ Time");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(4);
        cell.setCellValue("Description");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(5);
        cell.setCellValue("Transaction Type");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(6);
        cell.setCellValue("Response Code");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(7);
        cell.setCellValue("Encryption Mode");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(8);
        cell.setCellValue("Encryption Algo");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(9);
        cell.setCellValue("Device IP");
        cell.setCellStyle(columnHeaderCell);

        cell = row.createCell(10);
        cell.setCellValue("Connection IP");
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
                file = new File(directory + File.separator + "Transaction Failing Report_" + fileCount + ".xlsx");
            } else {
                file = new File(directory + File.separator + "Transaction Failing Report.xlsx");
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

    private static int createExcelTableBodySection(XSSFWorkbook workbook, SystemTnxFailDataBean dataBean, int currrow, int rownumber) throws Exception {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        Row row = sheet.createRow(currrow++);

        Cell cell = row.createCell(0);
        cell.setCellValue(dataBean.getSid());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(1);
        cell.setCellValue(dataBean.getTid());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(2);
        cell.setCellValue(dataBean.getSerialno());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(3);
        cell.setCellValue(dataBean.getDatetime().toString());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(4);
        cell.setCellValue(dataBean.getDescription());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(5);
        cell.setCellValue(dataBean.getTnxtpe());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(6);
        cell.setCellValue(dataBean.getResponsecode());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(7);
        cell.setCellValue(dataBean.getEncmode());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(8);
        cell.setCellValue(dataBean.getEncalgo());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(9);
        cell.setCellValue(dataBean.getDiviceip());
        cell.setCellStyle(rowColumnCell);

        cell = row.createCell(10);
        cell.setCellValue(dataBean.getConnectionip());
        cell.setCellStyle(rowColumnCell);

        return currrow;
    }
}
