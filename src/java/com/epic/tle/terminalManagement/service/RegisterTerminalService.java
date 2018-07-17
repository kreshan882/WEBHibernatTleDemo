/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.service;

/**
 *
 * @author danushka_r
 */
import com.epic.tle.mapping.EpicTleBinProfile;
import com.epic.tle.mapping.EpicTleEncryptionlevles;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.mapping.EpicTleTerminalRefprofile;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalDataBean;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalInputBean;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.Query;
import org.hibernate.Session;

public class RegisterTerminalService implements RegisterTerminalServiceInf {

    public void setETypeDropDown(RegisterTerminalBean inputBean) throws Exception {
        List<EpicTleEncryptionlevles> encryptionLeavaleSList;
        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            String hql = "from EpicTleEncryptionlevles";
            Query query = session.createQuery(hql);
            encryptionLeavaleSList = (List<EpicTleEncryptionlevles>) query.list();
            int size = encryptionLeavaleSList.size();
            for (int i = 0; i < size; i++) {
                inputBean.getEncTypeMap().put(encryptionLeavaleSList.get(i).getCode(), encryptionLeavaleSList.get(i).getDescription());
            }

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

    }

    public void BinPrfDropDown(RegisterTerminalBean inputBean) throws Exception {
        List<EpicTleBinProfile> binPrfList;
        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            String hql = "from EpicTleBinProfile pf where pf.id !=1 ";
            Query query = session.createQuery(hql);
            binPrfList = (List<EpicTleBinProfile>) query.list();
            int size = binPrfList.size();
            for (int i = 0; i < size; i++) {
                if (binPrfList.get(i).getStatus().intValue() == 1) {
                    inputBean.getBinPrfMap().put(binPrfList.get(i).getId(), binPrfList.get(i).getDescription());
                }
            }

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    public boolean addTerminal(RegisterTerminalBean inputBean) throws Exception {
        boolean isAddTerminal = false;
        EpicTleTerminal tleTerminal = null;
        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            tleTerminal = new EpicTleTerminal();

            EpicTleStatus tleStatusByStatuse = new EpicTleStatus();
            tleStatusByStatuse.setCode(Status.ACTIVE);
            tleTerminal.setEpicTleStatusByStatus(tleStatusByStatuse);

            EpicTleEncryptionlevles tleEncryptionLeval = new EpicTleEncryptionlevles();
            tleEncryptionLeval.setCode(Integer.parseInt(inputBean.getEncType()));
            tleTerminal.setEpicTleEncryptionlevles(tleEncryptionLeval);

            EpicTleStatus epicTleStatusByNonenctxnstatus = new EpicTleStatus();
            epicTleStatusByNonenctxnstatus.setCode(Integer.parseInt(inputBean.getEncStatus()));
            tleTerminal.setEpicTleStatusByNonenctxnstatus(epicTleStatusByNonenctxnstatus);

            EpicTleBinProfile tleBinProfile = (EpicTleBinProfile) session.get(EpicTleBinProfile.class, Integer.parseInt(inputBean.getBinPrf()));
            tleTerminal.setEpicTleBinProfile(tleBinProfile);

            EpicTleTerminalRefprofile tleRefProfile = (EpicTleTerminalRefprofile) session.get(EpicTleTerminalRefprofile.class, Integer.parseInt(inputBean.getTeminalRefProf()));
            tleTerminal.setEpicTleTerminalRefprofile(tleRefProfile);

            tleTerminal.setMid(inputBean.getMid());
            tleTerminal.setSerialNo(inputBean.getSerialno());
            tleTerminal.setBank(inputBean.getBank());
            tleTerminal.setName(inputBean.getName());
            tleTerminal.setLocation(inputBean.getLocation());
            tleTerminal.setRegdate(Util.getLocalDate().toString());
            tleTerminal.setLastupdatedate(Util.getLocalDate());
            tleTerminal.setLasttxndate(Util.getLocalDate());
            tleTerminal.setTerminalbrand(inputBean.getTerBrand());

            tleTerminal.setTid(inputBean.getTid());
            session.save(tleTerminal);
            session.getTransaction().commit();
            isAddTerminal = true;
        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isAddTerminal;
    }

    public List getEncryptionStatus() throws Exception {
        Map<Integer, String> dataMap = new HashMap<Integer, String>();
        Session session = null;
        Query query = null;
        List<EpicTleEncryptionlevles> encryptionLevelStatsList;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleEncryptionlevles";
            query = session.createQuery(sql);

            encryptionLevelStatsList = query.<EpicTleEncryptionlevles>list();

            session.getTransaction().commit();

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return encryptionLevelStatsList;

    }

    public boolean deleteTerminalUser(String dtid) throws Exception {
        boolean isUserDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleTerminal wu"
                    + "  where wu.tid =:tid";
            query = session.createQuery(sql);

            query.setString("tid", dtid);
            int result = query.executeUpdate();
            if (1 == result) {
                isUserDeleted = true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return isUserDeleted;
    }

    public void findUser(RegisterTerminalBean inputBean) throws Exception {
        List<EpicTleTerminal> finduserlist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleTerminal wu where wu.tid =:tid";
            query = session.createQuery(sql);
            query.setString("tid", inputBean.getTid());
            finduserlist = query.list();

            if (0 < finduserlist.size()) {
                inputBean.setTid(finduserlist.get(0).getTid());
                inputBean.setMid(finduserlist.get(0).getMid());
                inputBean.setSerialNo(finduserlist.get(0).getSerialNo());
                inputBean.setTerminalBrand(finduserlist.get(0).getTerminalbrand());
                inputBean.setBank(finduserlist.get(0).getBank());
                inputBean.setName(finduserlist.get(0).getName());
                inputBean.setLocation(finduserlist.get(0).getLocation());
                inputBean.setNonEncryptionTransaction(finduserlist.get(0).getEpicTleStatusByNonenctxnstatus().getDescription());
                inputBean.setEncryptionType(finduserlist.get(0).getEpicTleEncryptionlevles().getDescription());
                inputBean.setStatus(Integer.toString(finduserlist.get(0).getEpicTleStatusByStatus().getCode()));
                inputBean.setEncryptionType(Integer.toString(finduserlist.get(0).getEpicTleEncryptionlevles().getCode()));
                inputBean.setNonEncryptionTransaction(Integer.toString(finduserlist.get(0).getEpicTleStatusByNonenctxnstatus().getCode()));

                inputBean.setUpBinPrf(finduserlist.get(0).getEpicTleBinProfile().getId().intValue());
                inputBean.setUpteminalRefProf(finduserlist.get(0).getEpicTleTerminalRefprofile().getId().toString());
//                inputBean.setUpBinStatus(finduserlist.get(0).getEpicTleStatusByBinstatus().getCode());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

    }

    public void initiateValuesToMap(RegisterTerminalBean inputBean) throws Exception {
        HashMap<Integer, String> dataMap = new HashMap<Integer, String>();
        Session session = null;
        Query query = null;
        List<EpicTleEncryptionlevles> encryptionLevelStatsList;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleEncryptionlevles";
            query = session.createQuery(sql);

            encryptionLevelStatsList = query.<EpicTleEncryptionlevles>list();

            for (int i = 0; i < encryptionLevelStatsList.size(); i++) {
                inputBean.getEncryptionStatusMap().put(encryptionLevelStatsList.get(i).getCode(), encryptionLevelStatsList.get(i).getDescription());
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

    }

    public boolean updateTerminal(RegisterTerminalBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleTerminal> epicTleTerminal = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleTerminal wu where wu.tid =:tid";
            query = session.createQuery(sql);
            query.setString("tid", inputBean.getTid());
            epicTleTerminal = query.list();
            if (epicTleTerminal.size() > 0) {
                epicTleTerminal.get(0).setMid(inputBean.getMid());
                epicTleTerminal.get(0).setSerialNo(inputBean.getSerialNo());
                epicTleTerminal.get(0).setName(inputBean.getName());
                epicTleTerminal.get(0).setBank(inputBean.getBank());
                epicTleTerminal.get(0).setLocation(inputBean.getLocation());
                epicTleTerminal.get(0).setTerminalbrand(inputBean.getTerminalBrand());

                EpicTleStatus epicTleStatus = new EpicTleStatus();
                epicTleStatus.setCode(Integer.parseInt(inputBean.getStatus()));
                epicTleTerminal.get(0).setEpicTleStatusByStatus(epicTleStatus);

                EpicTleEncryptionlevles epicTleEncryptionlevles = new EpicTleEncryptionlevles();
                epicTleEncryptionlevles.setCode(Integer.parseInt(inputBean.getEncryptionType()));
                epicTleTerminal.get(0).setEpicTleEncryptionlevles(epicTleEncryptionlevles);

                EpicTleStatus epicTleStatusNonEncrypt = new EpicTleStatus();
                epicTleStatusNonEncrypt.setCode(Integer.parseInt(inputBean.getNonEncryptionTransaction()));
                epicTleStatusNonEncrypt.setEpicTleTerminalsForNonenctxnstatus(epicTleStatusNonEncrypt.getEpicTleTerminalsForNonenctxnstatus());
                epicTleTerminal.get(0).setEpicTleStatusByNonenctxnstatus(epicTleStatusNonEncrypt);

                EpicTleBinProfile binProfile = (EpicTleBinProfile) session.get(EpicTleBinProfile.class, inputBean.getUpBinPrf());
                epicTleTerminal.get(0).setEpicTleBinProfile(binProfile);

                EpicTleTerminalRefprofile tleRefProfile = (EpicTleTerminalRefprofile) session.get(EpicTleTerminalRefprofile.class, Integer.parseInt(inputBean.getUpteminalRefProf()));
                epicTleTerminal.get(0).setEpicTleTerminalRefprofile(tleRefProfile);

                session.save(epicTleTerminal.get(0));
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                session.flush();
                session.close();//roll back
            } catch (Exception e) {
                throw e;
            }
        }
        return isUpdated;
    }

    public List<RegisterTerminalBean> loadTerminalUsers(RegisterTerminalBean inputBean, int max, int first, String orderBy) throws Exception {

        EpicTleTerminal epicTleTerminal = new EpicTleTerminal();
        List<RegisterTerminalBean> dataList = new ArrayList<RegisterTerminalBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.tid desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query queryCount;
            Query querySearch;
            if (inputBean.getStatusValue() == null) {
                String sqlCount = "select count(tid) from EpicTleTerminal wu " + orderBy;
                queryCount = session.createQuery(sqlCount);
            } else {
                String sqlCount = "select count(tid) from EpicTleTerminal wu where wu.tid LIKE:tid and wu.epicTleStatusByStatus.code LIKE :statuscode and wu.epicTleEncryptionlevles.code LIKE :statusLevel and wu.epicTleStatusByNonenctxnstatus.code LIKE :statusNonEnValue " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("tid", "%" + inputBean.getTerminalId() + "%");
                queryCount.setString("statuscode", "%" + inputBean.getStatusValue() + "%");
                queryCount.setString("statusLevel", "%" + inputBean.getEncryptionStatusValue() + "%");
                queryCount.setString("statusNonEnValue", "%" + inputBean.getNonEncryptionStatusValue() + "%");
            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

                if (inputBean.getStatusValue() == null) {
                    String sqlSearch = "from EpicTleTerminal wu " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                } else {
                    String sqlSearch = "from EpicTleTerminal wu where wu.tid LIKE :tid and  wu.epicTleStatusByStatus.code LIKE :statuscode and wu.epicTleEncryptionlevles.code LIKE :statusLevel and wu.epicTleStatusByNonenctxnstatus.code LIKE :statusNonEnValue " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setString("tid", "%" + inputBean.getTerminalId() + "%");
                    querySearch.setString("statuscode", "%" + inputBean.getStatusValue() + "%");
                    querySearch.setString("statusLevel", "%" + inputBean.getEncryptionStatusValue() + "%");
                    querySearch.setString("statusNonEnValue", "%" + inputBean.getNonEncryptionStatusValue() + "%");
                }

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

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
                        databean.setStatus("" + objBean.getEpicTleStatusByStatus().getCode());
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
                        databean.setTeminalRefProf("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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

        return dataList;
    }

    @Override
    public Object generateExcelReport() throws Exception {

        Object returnObject = null;
        Session session = null;
        try {
            XSSFWorkbook workbook = createExcelTopSection();

            List<RegisterTerminalBean> dataList = new ArrayList<>();

            session = HibernateInit.sessionFactory.openSession();

            String sqlSearch = "from EpicTleTerminal ";
            Query querySearch = session.createQuery(sqlSearch);
//            querySearch.setInteger("statuscode", Status.ACTIVE);
            Iterator it = querySearch.iterate();

            while (it.hasNext()) {
                RegisterTerminalBean databean = new RegisterTerminalBean();
                EpicTleTerminal objBean = (EpicTleTerminal) it.next();
                try {
                    databean.setTid(objBean.getTid());
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
                    databean.setStatus(objBean.getEpicTleStatusByStatus().getDescription());
                } catch (NullPointerException npe) {
                    databean.setStatus("--");
                }

                dataList.add(databean);

            }

            workbook = createExcelTableBodySection(workbook, dataList);

            returnObject = workbook;

        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return returnObject;
    }

    private XSSFWorkbook createExcelTopSection() throws Exception {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("Terminal Management");
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
        cell.setCellValue("Encryption Status");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(9);
        cell.setCellValue("Status");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        return workbook;
    }

    private XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<RegisterTerminalBean> dataList) {
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
        }

        return workbook;
    }

    @Override
    public List<NonFunctionTerminalDataBean> loadTerminalUsers(NonFunctionTerminalInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public RegisterTerminalBean getPagePath(String page, RegisterTerminalBean inputBean) throws Exception {
        String module = (page != null && page !="") ? page.substring(0, 2) : "";
        Session session = null;
        String pagePath = "";

        try {
            if (module != null && module !="") {
                session = HibernateInit.sessionFactory.openSession();
                session.beginTransaction();
                EpicTleSection epicTleSection = (EpicTleSection) session.get(EpicTleSection.class, page);
                String mod = epicTleSection.getEpicTleModule().getDescription();
                String sect = epicTleSection.getSectionName();

                inputBean.setModule(mod);
                inputBean.setSection(sect);

            }

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }

        return inputBean;
    }

    @Override
    public void RepPrfDropDown(RegisterTerminalBean inputBean) throws Exception {
        List<EpicTleTerminalRefprofile> binPrfList;
        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            String hql = "from EpicTleTerminalRefprofile pf where pf.id !=1 ";
            Query query = session.createQuery(hql);
            binPrfList = (List<EpicTleTerminalRefprofile>) query.list();
            int size = binPrfList.size();
            for (int i = 0; i < size; i++) {
                if (binPrfList.get(i).getEpicTleStatusByStatus().getCode() == 1) {
                    inputBean.getTeminalRefProfMap().put(binPrfList.get(i).getId(), binPrfList.get(i).getName());
                }
            }

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    @Override
    public String GetResult(RegisterTerminalBean inputBean) throws Exception {

        Session session = null;
        String sqlCount = "";
        String terminal = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleTerminal wu where wu.tid =:name";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("name", inputBean.getTid());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
//                ChannelManageInputBean databean = new ChannelManageInputBean();
                EpicTleTerminal objBean = (EpicTleTerminal) it.next();
                terminal = objBean.getEpicTleBinProfile().getDescription();
            }

        } catch (Exception e) {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            try {
                if (session != null) {
                    session.flush();
                    session.clear();
                    session.getTransaction().commit();
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return terminal;

    }

}
