/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleHistory;
import com.epic.tle.mapping.EpicTleModule;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleTask;
import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
 * @author dimuthu_h
 */
public class SystemHistoryService implements ReportSystemHistoryInf {

    public SystemHistoryInputBean loadMap(SystemHistoryInputBean inputBean) throws Exception {
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String mapquery = "from EpicTleModule ";
            Query query = session.createQuery(mapquery);
            Iterator it = query.iterate();
            while (it.hasNext()) {
                EpicTleModule tleModule = (EpicTleModule) it.next();
                inputBean.getModuleMap().put(tleModule.getCode(), tleModule.getDescription());
            }
            
            String taskmapsql = "from EpicTleTask ";
            Query taskMapquery = session.createQuery(taskmapsql);
            Iterator taskit = taskMapquery.iterate();
            while (taskit.hasNext()) {
                EpicTleTask tleTask = (EpicTleTask) taskit.next();
                inputBean.getTaskMap().put(tleTask.getTaskId(), tleTask.getDescription());
            }
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

        return inputBean;
    }
    
    public SystemHistoryInputBean loadSectionMap(SystemHistoryInputBean inputBean) throws Exception {
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String mapquery = "from EpicTleSection ";
            Query query = session.createQuery(mapquery);
            Iterator it = query.iterate();
            while (it.hasNext()) {
                EpicTleSection tleSection = (EpicTleSection) it.next();
                inputBean.getSectionMap().put(tleSection.getSectionId(), tleSection.getSectionName());
            }
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

        return inputBean;
    }

    public List<SystemHistoryDataBean> loadHistoryDetails(SystemHistoryInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<SystemHistoryDataBean> dataList = new ArrayList<SystemHistoryDataBean>();
        Session session = null;

        try {
            long count = 0;

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by sh.datetime desc ";
            }

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query querySearch = null;
            Query queryCount = null;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            String taskClause="";
            boolean isTaskSet=false;
            if(null!=inputBean.getSearchTask() && !inputBean.getSearchTask().equals("-1")){
                taskClause=" sh.epicTleTask.taskId = :taskId and "; 
                isTaskSet=true;
            }
            if (!inputBean.isSearch()) {
                //Edit by ridmi 2017 11 13
                inputBean.setTodate(Util.currentDate());
                inputBean.setFromdate(Util.currentDate());

                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
               // String sqlCount = "select count(sid) from EpicTleHistory sh where sh.epicTleNodetype.code =:node and (sh.datetime >= :beginDate and sh.datetime <= :endDate)";
               String sqlCount = "select count(sid) from EpicTleHistory sh where (sh.datetime >= :beginDate and sh.datetime <= :endDate)";
                queryCount = session.createQuery(sqlCount);
                //queryCount.setInteger("node", Configurations.SERVER_NODE);
                queryCount.setParameter("beginDate", beginDate);
                queryCount.setParameter("endDate", endDate);
//                Iterator itCount = queryCount.iterate();
//                count = (Long) itCount.next();

            } else if (!inputBean.getSelectNode().equals("-1")) {
                if(null==inputBean.getSelectMod()||inputBean.getSelectMod().equals("-1")){
                    inputBean.setSelectMod("");
                }
                String sqlCount = "select count(sid) from EpicTleHistory sh where "+taskClause+" sh.epicTleUser.username LIKE :userName and (sh.datetime >= :beginDate and sh.datetime <= :endDate) and sh.epicTleNodetype.code =:node  and sh.module LIKE :module " + orderBy;
                queryCount = session.createQuery(sqlCount);
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                queryCount.setParameter("module", "%"+inputBean.getSelectMod()+"%");
                queryCount.setParameter("userName", "%"+inputBean.getSearchUserName()+"%"); 
                if(isTaskSet)queryCount.setParameter("taskId", inputBean.getSearchTask());

            } else {
                if(null==inputBean.getSelectMod()||inputBean.getSelectMod().equals("-1")){
                    inputBean.setSelectMod("");
                }
                String sqlCount = "select count(sid) from EpicTleHistory sh where "+taskClause+" sh.epicTleUser.username LIKE :userName and (sh.datetime >= :beginDate and sh.datetime <= :endDate)  and sh.module like :module " + orderBy;
                queryCount = session.createQuery(sqlCount);
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                queryCount.setParameter("module", "%"+inputBean.getSelectMod()+"%");
                queryCount.setParameter("userName", "%"+inputBean.getSearchUserName()+"%");
                if(isTaskSet) queryCount.setParameter("taskId", inputBean.getSearchTask());

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {
                if (!inputBean.isSearch()) {
                     Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    
                    //String sqlSearch = "from EpicTleHistory sh where sh.epicTleNodetype.code =:node and (sh.datetime >= :beginDate and sh.datetime <= :endDate)" + orderBy;
                    String sqlSearch = "from EpicTleHistory sh where (sh.datetime >= :beginDate and sh.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    //querySearch.setInteger("node", Configurations.SERVER_NODE);
                    querySearch.setParameter("beginDate", beginDate);
                    querySearch.setParameter("endDate", endDate);
                } else if (!inputBean.getSelectNode().equals("-1")) {
                    if(null==inputBean.getSelectMod()||inputBean.getSelectMod().equals("-1")){
                        inputBean.setSelectMod("");
                    }
                    String sqlSearch = "from EpicTleHistory sh where "+taskClause+" sh.epicTleUser.username LIKE :userName and (sh.datetime >= :beginDate and sh.datetime <= :endDate) and sh.epicTleNodetype.code =:node  and sh.module like :module  " + orderBy;
                    querySearch = session.createQuery(sqlSearch);

                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);
                    querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    querySearch.setParameter("module", "%"+inputBean.getSelectMod()+"%");
                    querySearch.setParameter("userName", "%"+inputBean.getSearchUserName()+"%");
                    if(isTaskSet)querySearch.setParameter("taskId", inputBean.getSearchTask());
                } else {
                    if(null==inputBean.getSelectMod()||inputBean.getSelectMod().equals("-1")){
                        inputBean.setSelectMod("");
                    }
                    String sqlSearch = "from EpicTleHistory sh where "+taskClause+" sh.epicTleUser.username LIKE :userName and (sh.datetime >= :beginDate and sh.datetime <= :endDate)  and sh.module like :module  " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);
                    querySearch.setParameter("module", "%"+inputBean.getSelectMod()+"%");
                    querySearch.setParameter("userName", "%"+inputBean.getSearchUserName()+"%");
                    if(isTaskSet)querySearch.setParameter("taskId", inputBean.getSearchTask());

                }
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);
                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    SystemHistoryDataBean databean = new SystemHistoryDataBean();
                    EpicTleHistory objBean = (EpicTleHistory) it.next();
                    try {
                        databean.setSid(Integer.toString(objBean.getSid()));
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }
                    try {
                        databean.setUserType(objBean.getEpicTleUserProfile().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setModule(inputBean.getModuleMap().get(objBean.getModule()));
                    } catch (NullPointerException npe) {
                        databean.setModule("--");
                    }
                    try {
                        databean.setOperation(objBean.getEpicTleTask().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setOperation("--");
                    }
                    try {
                        databean.setDateTime(objBean.getDatetime().toString());
                    } catch (NullPointerException npe) {
                        databean.setDateTime(null);
                    }
                    try {
                        databean.setLocation(objBean.getLocation());
                    } catch (NullPointerException npe) {
                        databean.setLocation("--");
                    }
                    try {
                        databean.setComment(objBean.getRemark());
                    } catch (NullPointerException npe) {
                        databean.setComment("--");
                    }
                    try {
                        databean.setSerialNo(objBean.getSerialno());
                    } catch (NullPointerException npe) {
                        databean.setSerialNo("--");
                    }
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
                        databean.setWebUser(objBean.getEpicTleUser().getUsername());
                    } catch (NullPointerException npe) {
                        databean.setWebUser("--");
                    }
                    try {
                        databean.setNode(objBean.getEpicTleNodetype().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setNode("--");
                    }
                    try {
                        databean.setSection(inputBean.getSectionMap().get(objBean.getSection()));
                    } catch (NullPointerException npe) {
                        databean.setSection(" ");
                    }
                    databean.setFullCount(count);

                    dataList.add(databean);
                }

            }

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
        return dataList;
    }

    @Override
    public SystemHistoryInputBean getPagePath(String page, SystemHistoryInputBean inputBean) throws Exception {
        if (page != null && page != "") {
            String module = page.substring(0, 2);
            Session session = null;
            String pagePath = "";

            try {

                session = HibernateInit.sessionFactory.openSession();
                session.beginTransaction();
                EpicTleSection epicTleSection = (EpicTleSection) session.get(EpicTleSection.class, page);
                String mod = epicTleSection.getEpicTleModule().getDescription();
                String sect = epicTleSection.getSectionName();

                inputBean.setModule(mod);
                inputBean.setSection(sect);

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
        return inputBean;
    }

    @Override
    public Object generateExcelReport(SystemHistoryInputBean inputBean) throws Exception {

        Object returnObject = null;
        Session session = null;
        try {
            XSSFWorkbook workbook = SystemHistoryService.createExcelTopSection();

            List<SystemHistoryDataBean> dataList = new ArrayList<SystemHistoryDataBean>();

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
//            System.err.println(inputBean.getFromdate());
//            System.err.println(inputBean.getTodate());
//            System.err.println(inputBean.getOperationType());
            Query querySearch;
            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            if (!inputBean.getSelectNode().equals("-1")) {

                String sqlSearch = "from EpicTleHistory sh where (sh.datetime >= :beginDate and sh.datetime <= :endDate) and sh.epicTleNodetype.code =:node";
                querySearch = session.createQuery(sqlSearch);

                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                querySearch.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                querySearch.setParameter("endDate", endDate);
                querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));

            } else {

                String sqlSearch = "from EpicTleHistory sh where (sh.datetime >= :beginDate and sh.datetime <= :endDate)";
                querySearch = session.createQuery(sqlSearch);
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                querySearch.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                querySearch.setParameter("endDate", endDate);

            }
            Iterator it = querySearch.iterate();
            System.err.println(it.hasNext());
            int countPrintSize = 0;
            while (it.hasNext() && countPrintSize <= 1000) {
                countPrintSize++;
                SystemHistoryDataBean databean = new SystemHistoryDataBean();
                EpicTleHistory objBean = (EpicTleHistory) it.next();

                try {
                    databean.setUserType(objBean.getEpicTleUserProfile().getDescription());
                } catch (NullPointerException npe) {
                    databean.setUserType("--");
                }
                try {
                    databean.setModule(inputBean.getModuleMap().get(objBean.getModule()));
                } catch (NullPointerException npe) {
                    databean.setModule("--");
                }
                try {
                    databean.setOperation(objBean.getEpicTleTask().getDescription());
                } catch (NullPointerException npe) {
                    databean.setOperation("--");
                }
                try {
                    databean.setDateTime(objBean.getDatetime().toString());
                } catch (NullPointerException npe) {
                    databean.setDateTime(null);
                }
                try {
                    databean.setLocation(objBean.getLocation());
                } catch (NullPointerException npe) {
                    databean.setLocation("--");
                }
                try {
                    databean.setComment(objBean.getRemark());
                } catch (NullPointerException npe) {
                    databean.setComment("--");
                }
                try {
                    databean.setSerialNo(objBean.getSerialno());
                } catch (NullPointerException npe) {
                    databean.setSerialNo("--");
                }
                try {
                    databean.setWebUser(objBean.getEpicTleUser().getUsername());
                } catch (NullPointerException npe) {
                    databean.setWebUser("--");
                }
                try {
                    databean.setNode(objBean.getEpicTleNodetype().getDescription());
                } catch (NullPointerException npe) {
                    databean.setWebUser("--");
                }
                try {
                    databean.setSection(inputBean.getSectionMap().get(objBean.getSection()));
                } catch (NullPointerException npe) {
                    databean.setSection("--");
                }
                try {
                    databean.setOldValue(objBean.getOldValue());
                } catch (NullPointerException npe) {
                    databean.setOldValue("--");
                }
                try {
                    databean.setNewValue(objBean.getNewValue());
                } catch (NullPointerException npe) {
                    databean.setNewValue("--");
                }
                dataList.add(databean);
//                System.err.println("data list==="+dataList);
            }

            workbook = SystemHistoryService.createExcelTableBodySection(workbook, dataList);

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
        cell.setCellValue("Node");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(1);
        cell.setCellValue("User");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        //sheet.autoSizeColumn(0);

        cell = row.createCell(2);
        cell.setCellValue("Location");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(3);
        cell.setCellValue("Module");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(4);
        cell.setCellValue("Section");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(5);
        cell.setCellValue("Task");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(6);
        cell.setCellValue("Comment");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        cell = row.createCell(7);
        cell.setCellValue("Date/Time");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(8);
        cell.setCellValue("New Value");
        cell.setCellStyle(fontBoldedUnderlinedCell);
        
        cell = row.createCell(9);
        cell.setCellValue("Old Value");
        cell.setCellStyle(fontBoldedUnderlinedCell);

        return workbook;
    }

    private static XSSFWorkbook createExcelTableBodySection(XSSFWorkbook workbook, List<SystemHistoryDataBean> dataList) {
        XSSFSheet sheet = workbook.getSheetAt(0);
        XSSFCellStyle rowColumnCell = ExcelCommon.getRowColumnCell(workbook);
        int excelrow = 1;
        int i = -1;
        while (++i < dataList.size()) {
            Row row = sheet.createRow(excelrow++);

            Cell cell = row.createCell(0);
            cell.setCellValue(dataList.get(i).getNode());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(1);
            cell.setCellValue(dataList.get(i).getUserType());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(2);
            cell.setCellValue(dataList.get(i).getLocation());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(3);
            cell.setCellValue(dataList.get(i).getModule());
            cell.setCellStyle(rowColumnCell);
            
            cell = row.createCell(4);
            cell.setCellValue(dataList.get(i).getSection());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(5);
            cell.setCellValue(dataList.get(i).getOperation());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(6);
            cell.setCellValue(dataList.get(i).getComment());
            cell.setCellStyle(rowColumnCell);

            cell = row.createCell(7);
            cell.setCellValue(dataList.get(i).getDateTime().toString());
            cell.setCellStyle(rowColumnCell);
            
            cell = row.createCell(8);
            cell.setCellValue(dataList.get(i).getOldValue());
            cell.setCellStyle(rowColumnCell);
            
            cell = row.createCell(9);
            cell.setCellValue(dataList.get(i).getNewValue());
            cell.setCellStyle(rowColumnCell);

        }

        return workbook;
    }

    @Override
    public SystemHistoryInputBean loadMoreHistoryDetails(SystemHistoryInputBean inputBean) throws Exception {
        Object returnObject = null;
        Session session = null;
        Query querySearch = null;
        try{
        session = HibernateInit.sessionFactory.openSession();
        session.beginTransaction();
        String sqlSearch = "from EpicTleHistory sh where sid = :sid";
        querySearch = session.createQuery(sqlSearch);
        querySearch.setParameter("sid", Integer.valueOf(inputBean.getSidx()));
        Iterator it = querySearch.iterate();
        while (it.hasNext()) 
        {
            EpicTleHistory objBean = (EpicTleHistory) it.next();
            if(null!=objBean.getOldValue() && !objBean.getOldValue().isEmpty()){
                mapValues(1,inputBean,objBean.getOldValue());
            }               
            if(null!=objBean.getNewValue() && !objBean.getNewValue().isEmpty()){
                mapValues(0,inputBean,objBean.getNewValue());
            }
        }

        } 
        catch (Exception e) {
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
        return inputBean;
        }
    
        private void mapValues(int mode,SystemHistoryInputBean inputBean,String s){
            HashMap hm=new HashMap();
            boolean changed=false;
            try{
                try{
                s=s.substring(s.indexOf('{')+1 ,s.lastIndexOf('}'));
                }
                catch(Exception ex){               
                }
            String[] dataValePair=s.split(",");
            for(String sDataValuePair: dataValePair){
                String [] sArr= sDataValuePair.split(":",2);  
                if(null!=inputBean.getOldValueMap() && !inputBean.getOldValueMap().isEmpty()){                    
                    String [] fromOldVal=inputBean.getOldValueMap().get(sArr[0]);
                    changed=false;
                    if(!fromOldVal[0].equals(sArr[1])){
                        changed=true;
                        inputBean.getOldValueMap().put(sArr[0],new String [] {fromOldVal[0],"changed"});
                    }
                    
                }
                hm.put(sArr[0], new String[] { sArr[1],(changed?"changed":"")});
            }
            if(mode==1){
                inputBean.setOldValueMap(hm);
            }
            else{
                inputBean.setNewValueMap(hm);
            }
            }
            catch(Exception e){
                hm.put("", new String[]{"Sorry, Error While Extracting Data","changed"});
                if(mode==1){
                    inputBean.setOldValueMap(hm);
                }
                else{
                    inputBean.setNewValueMap(hm);
                }
            }
                    
        }

}
