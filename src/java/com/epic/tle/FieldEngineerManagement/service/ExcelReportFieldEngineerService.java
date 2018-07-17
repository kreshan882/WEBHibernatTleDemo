/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerBean;
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.mapping.EpicTleCardholders;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;

public class ExcelReportFieldEngineerService implements ExcelReportFieldEngineerServiceInf {

    public Object generateExcelReport(RegisterFieldEngineerInputBean inputBean) throws Exception {

        Object returnObject = null;
        Session session = null;
        try {
            XSSFWorkbook workbook = new ExcelReportFieldEngineerService().createExcelTopSection();

            List<RegisterFieldEngineerBean> dataList = new ArrayList<RegisterFieldEngineerBean>();

            session = HibernateInit.sessionFactory.openSession();

            session.beginTransaction();

//            String sqlSearch = "from EpicTleCardholders ch where ch.epicTleStatus.code =:statuscode order by ch.sid asc ";
//            Query querySearch = session.createQuery(sqlSearch);
//            querySearch.setInteger("statuscode", Status.ACTIVE);
            if (inputBean.getAlgorithm().equals("-1")) {
                inputBean.setAlgorithm("");
            }
            if (inputBean.getPinVerification().equals("-1")) {
                inputBean.setPinVerification("");
            }
            long count = 0;
            Query queryCount;
            Query querySearch;
            String sqlCount = "select count(sid) from EpicTleCardholders ch where ch.epicTleStatus.code =:statuscode and ch.serialno LIKE :serialno and ch.epicTleSelecteddevice.description LIKE :selectedDevice and ch.officername LIKE :officerName and ch.location LIKE :locations and ch.epicTleAlgorithem.code LIKE :algorithm and ch.epicTlePinverficationmethod.code LIKE :pinVerification";
            queryCount = session.createQuery(sqlCount);

            queryCount.setInteger("statuscode", Status.ACTIVE);
            queryCount.setParameter("serialno", "%" + inputBean.getSearchSerial() + "%");
            queryCount.setParameter("selectedDevice", "%" + inputBean.getSelectedDevice() + "%");
            queryCount.setParameter("officerName", "%" + inputBean.getOfficerName() + "%");
            queryCount.setParameter("locations", "%" + inputBean.getLocations() + "%");
            queryCount.setString("algorithm", "%" + inputBean.getAlgorithm() + "%");
            queryCount.setString("pinVerification", "%" + inputBean.getPinVerification() + "%");

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {
                String sqlSearch = " from EpicTleCardholders ch where ch.epicTleStatus.code =:statuscode and ch.serialno LIKE :serialno and ch.epicTleSelecteddevice.description LIKE :selectedDevice and ch.officername LIKE :officerName and ch.location LIKE :locations and ch.epicTleAlgorithem.code LIKE :algorithm and ch.epicTlePinverficationmethod.code LIKE :pinVerification";
                querySearch = session.createQuery(sqlSearch);
                querySearch.setInteger("statuscode", Status.ACTIVE);
                querySearch.setParameter("serialno", "%" + inputBean.getSearchSerial() + "%");
                querySearch.setParameter("selectedDevice", "%" + inputBean.getSelectedDevice() + "%");
                querySearch.setParameter("officerName", "%" + inputBean.getOfficerName() + "%");
                querySearch.setParameter("locations", "%" + inputBean.getLocations() + "%");
                querySearch.setString("algorithm", "%" + inputBean.getAlgorithm() + "%");
                querySearch.setString("pinVerification", "%" + inputBean.getPinVerification() + "%");

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    RegisterFieldEngineerBean databean = new RegisterFieldEngineerBean();
                    EpicTleCardholders objBean = (EpicTleCardholders) it.next();
                    try {
                        databean.setSid("" + objBean.getSid());
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }
                    try {
                        databean.setSerialno(objBean.getSerialno());
                    } catch (NullPointerException npe) {
                        databean.setSerialno("--");
                    }
                    try {
                        databean.setSelecteddevice(objBean.getEpicTleSelecteddevice().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setSelecteddevice("--");
                    }
                    try {
                        databean.setOfficername(objBean.getOfficername());
                    } catch (NullPointerException npe) {
                        databean.setOfficername("--");
                    }
                    try {
                        databean.setBankname(objBean.getBankname());
                    } catch (NullPointerException npe) {
                        databean.setBankname("--");
                    }
                    try {
                        databean.setLocation(objBean.getLocation());
                    } catch (NullPointerException npe) {
                        databean.setLocation("--");
                    }
                    try {
                        if (objBean.getRegdate() == null) {
                            databean.setRegdate("0000-00-00");
                        } else {
                            databean.setRegdate(objBean.getRegdate().toString());
                        }
                    } catch (NullPointerException npe) {
                        databean.setRegdate("--");
                    }
                    try {
                        databean.setMaxtmkdownlod("" + objBean.getMaxtmkdownlod());
                    } catch (NullPointerException npe) {
                        databean.setMaxtmkdownlod("--");
                    }
                    try {
                        databean.setAvaliabletmkdownlod("" + (objBean.getMaxtmkdownlod() - objBean.getMaxcountor()));
                    } catch (NullPointerException npe) {
                        databean.setAvaliabletmkdownlod("--");
                    }
                    try {
                        databean.setStatus(objBean.getEpicTleStatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setAlgorithem(objBean.getEpicTleAlgorithem().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setAlgorithem("--");
                    }
                    try {
                        databean.setPinVerfi(objBean.getEpicTlePinverficationmethod().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setPinVerfi("--");
                    }

                    dataList.add(databean);

                }
            }

            workbook = new ExcelReportFieldEngineerService().createExcelTableBodySection(workbook, dataList);

            returnObject = workbook;

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
        return returnObject;
    }

    public Object generateExcelReport1(RegisterFieldEngineerInputBean bean) throws Exception {

        Object returnObject = null;
        Session session = null;
        try {
            XSSFWorkbook workbook = new ExcelReportFieldEngineerService().createExcelTopSection1();

            List<RegisterTerminalBean> dataList = new ArrayList<RegisterTerminalBean>();

            // session = HibernateInit.sessionFactory.openSession();
//            String where = this.makeWhereClause(bean);
//
//            session.beginTransaction();
//            String sqlSearch = "from EpicTleTerminal tt where "+where;
//            Query querySearch = session.createQuery(sqlSearch);
//            Iterator it = querySearch.iterate();
//
//            while (it.hasNext()) {
//
//                RegisterTerminalBean databean = new RegisterTerminalBean();
//                EpicTleTerminal objBean = (EpicTleTerminal) it.next();
//                try {
//                    databean.setTid(objBean.getTid());
//                } catch (NullPointerException npe) {
//                    databean.setTid("--");
//                }
//                try {
//                    databean.setMid(objBean.getMid());
//                } catch (NullPointerException npe) {
//                    databean.setMid("--");
//                }
//                try {
//                    databean.setSerialNo(objBean.getSerialNo());
//                } catch (NullPointerException npe) {
//                    databean.setSerialNo("--");
//                }
//                try {
//                    databean.setTerminalBrand(objBean.getTerminalbrand());
//                } catch (NullPointerException npe) {
//                    databean.setTerminalBrand("--");
//                }
//                try {
//                    databean.setBank(objBean.getBank());
//                } catch (NullPointerException npe) {
//                    databean.setBank("--");
//                }
//                try {
//                    databean.setName(objBean.getName());
//                } catch (NullPointerException npe) {
//                    databean.setName("--");
//                }
//                try {
//                    databean.setLocation(objBean.getLocation());
//                } catch (NullPointerException npe) {
//                    databean.setLocation("--");
//                }
//                try {
//                    databean.setRegisterDate(objBean.getRegdate());
//                } catch (NullPointerException npe) {
//                    databean.setRegisterDate("--");
//                }
//                try {
//                    databean.setEncryptionStatus(objBean.getEpicTleEncryptionlevles().getDescription());
//                } catch (NullPointerException npe) {
//                    databean.setEncryptionStatus("--");
//                }
//                try {
//                    databean.setStatus(objBean.getEpicTleStatusByStatus().getDescription());
//                } catch (NullPointerException npe) {
//                    databean.setStatus("--");
//                }
//
//                dataList.add(databean);
//
//            }
            //Edit by ridmi 2017 11 13
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query queryCount;
            Query querySearch;
            if (bean.getStatusValue() == null) {
                String sqlCount = "select count(tid) from EpicTleTerminal wu";
                queryCount = session.createQuery(sqlCount);
            } else {
                String sqlCount = "select count(tid) from EpicTleTerminal wu where wu.tid LIKE:tid and wu.epicTleStatusByStatus.code LIKE :statuscode and wu.epicTleEncryptionlevles.code LIKE :statusLevel and wu.epicTleStatusByNonenctxnstatus.code LIKE :statusNonEnValue";
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("tid", "%" + bean.getTerminalId() + "%");
                queryCount.setString("statuscode", "%" + bean.getStatusValue() + "%");
                queryCount.setString("statusLevel", "%" + bean.getEncryptionStatusValue() + "%");
                queryCount.setString("statusNonEnValue", "%" + bean.getNonEncryptionStatusValue() + "%");
            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

                if (bean.getStatusValue() == null) {
                    String sqlSearch = "from EpicTleTerminal wu";
                    querySearch = session.createQuery(sqlSearch);
                } else {
                    String sqlSearch = "from EpicTleTerminal wu where wu.tid LIKE :tid and  wu.epicTleStatusByStatus.code LIKE :statuscode and wu.epicTleEncryptionlevles.code LIKE :statusLevel and wu.epicTleStatusByNonenctxnstatus.code LIKE :statusNonEnValue";
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setString("tid", "%" + bean.getTerminalId() + "%");
                    querySearch.setString("statuscode", "%" + bean.getStatusValue() + "%");
                    querySearch.setString("statusLevel", "%" + bean.getEncryptionStatusValue() + "%");
                    querySearch.setString("statusNonEnValue", "%" + bean.getNonEncryptionStatusValue() + "%");
                }

//                querySearch.setMaxResults(max);
//                querySearch.setFirstResult(first);
                Iterator it = querySearch.iterate();
                while (it.hasNext()) {
                    RegisterTerminalBean databean = new RegisterTerminalBean();
                    EpicTleTerminal objBean = (EpicTleTerminal) it.next();

                    try {
                        databean.setTid(objBean.getTid().toString());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setMid(objBean.getMid());
                    } catch (Exception npe) {
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
                        databean.setBank(objBean.getBank());
                    } catch (NullPointerException npe) {
                        databean.setBank("--");
                    }
                    try {
                        databean.setName(objBean.getName());
                    } catch (NullPointerException npe) {
                        databean.setName("--");
                    }
                    try {
                        databean.setLocation(objBean.getLocation());
                    } catch (NullPointerException npe) {
                        databean.setLocation("--");
                    }
                    try {
                        databean.setRegisterDate(objBean.getRegdate());
                    } catch (NullPointerException npe) {
                        databean.setRegisterDate("--");
                    }
                    try {
                        databean.setEncryptionStatus(objBean.getEpicTleEncryptionlevles().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setEncryptionStatus("--");
                    }
                    try {
                        databean.setStatus("" + objBean.getEpicTleStatusByStatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setBlockBinProfName(objBean.getEpicTleBinProfile().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setBlockBinProfName("--");
                    }
                    try {
                        databean.setTeminalRefProf(objBean.getEpicTleTerminalRefprofile().getName());
                    } catch (NullPointerException npe) {
                        databean.setTeminalRefProf("--");
                    }
                    try {
                        databean.setEncStatus(objBean.getEpicTleStatusByNonenctxnstatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setEncStatus("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }
            }

            workbook = new ExcelReportFieldEngineerService().createExcelTableBodySection1(workbook, dataList);

            returnObject = workbook;

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
        return returnObject;
    }

    public XSSFWorkbook createExcelTopSection() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Field Engineer");
        XSSFCellStyle fontBoldedUnderlinedCell = ExcelCommon.getColumnHeadeCell(workbook);

        Row row = sheet.createRow(0);

        Cell cell = row.createCell(0);
        cell.setCellValue("ID");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(1);
        cell.setCellValue("Serial Number");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(2);
        cell.setCellValue("Selected Device");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("Officer Name");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(4);
        cell.setCellValue("Bank Name");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("Location");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("Registered Date");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(7);
        cell.setCellValue("Maximum Key Download");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(8);
        cell.setCellValue("Available Key Download");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(9);
        cell.setCellValue("Algorithem");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(10);
        cell.setCellValue("Pin Verification");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(11);
        cell.setCellValue("Status");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        return workbook;
    }

    public XSSFWorkbook createExcelTopSection1() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Registered Terminals");
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
        cell.setCellValue("Bank");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("Name");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("Location");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(7);
        cell.setCellValue("Register Date");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(8);
        cell.setCellValue("Encryption Type");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(9);
        cell.setCellValue("Status");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(10);
        cell.setCellValue("Block Bin Profile");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(11);
        cell.setCellValue("Non Encryption Transactions");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(12);
        cell.setCellValue("Terminal Risk Profile");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        return workbook;
    }

    public XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<RegisterFieldEngineerBean> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getSid());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getSerialno());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getSelecteddevice());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getOfficername());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(4);
            cell.setCellValue(dataList.get(i).getBankname());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            cell.setCellValue(dataList.get(i).getLocation());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getRegdate());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(7);
            cell.setCellValue(dataList.get(i).getMaxtmkdownlod());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(8);
            cell.setCellValue(dataList.get(i).getAvaliabletmkdownlod());
            cell.setCellStyle(rowColumnCell);
            
            cell = row.createCell(9);
            cell.setCellValue(dataList.get(i).getAlgorithem());
            cell.setCellStyle(rowColumnCell);
            
            cell = row.createCell(10);
            cell.setCellValue(dataList.get(i).getPinVerfi());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(11);
            cell.setCellValue(dataList.get(i).getStatus());
            cell.setCellStyle(rowColumnCell);
        }

        return workbook;
    }

    public XSSFWorkbook createExcelTableBodySection1(XSSFWorkbook workbook, List<RegisterTerminalBean> dataList) {
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
            cell.setCellValue(dataList.get(i).getBank());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            cell.setCellValue(dataList.get(i).getName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getLocation());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(7);
            cell.setCellValue(dataList.get(i).getRegisterDate());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(8);
            cell.setCellValue(dataList.get(i).getEncryptionStatus());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(9);
            cell.setCellValue(dataList.get(i).getStatus());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(10);
            cell.setCellValue(dataList.get(i).getBlockBinProfName());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(11);
            cell.setCellValue(dataList.get(i).getEncStatus());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(12);
            cell.setCellValue(dataList.get(i).getTeminalRefProf());
            cell.setCellStyle(rowColumnCell);
        }

        return workbook;
    }
}
