/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleHistory;
import com.epic.tle.mapping.EpicTleProcessingTime;
import com.epic.tle.mapping.EpicTleResponsecode;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import com.epic.tle.systemtools.bean.SystemProcessDataBean;
import com.epic.tle.systemtools.bean.SystemProcessInputBean;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
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
 * @author thilina_t
 */
public class SystemProcessService implements SystemProcessTimeFactoryInf {

    @Override
    public List<SystemProcessDataBean> loadProcessDetails(SystemProcessInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<SystemProcessDataBean> dataList = new ArrayList<SystemProcessDataBean>();
        Session session = null;
        try {
            long count = 0;

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by sa.datetime desc ";
            }

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query querySearch = null;
            Query queryCount = null;

            if (!inputBean.isSearch()) {
                //Edit by ridmi 2017 11 13
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                inputBean.setTodate(Util.currentDate());
                inputBean.setFromdate(Util.currentDate());

                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);

                //String sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.epicTleNodetype.code =:node and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate" + orderBy;
                String sqlCount = "select count(id) from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                queryCount = session.createQuery(sqlCount);
                //queryCount.setInteger("node", Configurations.SERVER_NODE);
                queryCount.setParameter("beginDate", beginDate);
                queryCount.setParameter("endDate", endDate);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

            } else if (!inputBean.getSelectNode().equals("-1")) {
                if (!inputBean.getTransTID().isEmpty() && inputBean.getTransTID() != null ) {
                    String sqlCount="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.epicTleNodetype.code =:node and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                        queryCount = session.createQuery(sqlCount);
                    }
                    else{
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.epicTleNodetype.code =:node and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                        queryCount = session.createQuery(sqlCount);
                        queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    queryCount.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);
                    Iterator itCount = queryCount.iterate();
                    count = (Long) itCount.next();

                } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node" + orderBy;
                    queryCount = session.createQuery(sqlCount);
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);
                    Iterator itCount = queryCount.iterate();
                    count = (Long) itCount.next();

                } else {

                    String sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node" + orderBy;
                    queryCount = session.createQuery(sqlCount);
                    queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    //queryCount.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                    //queryCount.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                    queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                    queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    queryCount.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    queryCount.setParameter("endDate", endDate);
                    Iterator itCount = queryCount.iterate();
                    count = (Long) itCount.next();

                }

            } else if (!inputBean.getTransTID().isEmpty() && inputBean.getTransTID() != null) {
                    String sqlCount="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                        queryCount = session.createQuery(sqlCount);
                    }
                    else{
                        sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;                               
                        queryCount = session.createQuery(sqlCount);
                        queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                queryCount.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

            } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                String sqlCount = "select count(id) from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                queryCount = session.createQuery(sqlCount);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

            } else {

                String sqlCount = "select count(id) from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                queryCount = session.createQuery(sqlCount);
                //queryCount.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                //queryCount.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                queryCount.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                queryCount.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

            }

            if (count > 0) {

                if (!inputBean.isSearch()) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);

                   // String sqlSearch = "from EpicTleProcessingTime sa where sa.epicTleNodetype.code =:node and (sh.datetime >= :beginDate and sh.datetime <= :endDate)" + orderBy;
                    String sqlSearch = "from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    //querySearch.setInteger("node", Configurations.SERVER_NODE);
                    querySearch.setParameter("beginDate", beginDate);
                    querySearch.setParameter("endDate", endDate);

                } else if (!inputBean.getSelectNode().equals("-1")) {

                    if (!inputBean.getTransTID().isEmpty() && inputBean.getTransTID() != null) {
                    String sqlSearch="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                        sqlSearch = "from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node " + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                    }
                    else{
                        sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)  and sa.epicTleNodetype.code =:node  " + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                        querySearch.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        querySearch.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                        querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        querySearch.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        querySearch.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        querySearch.setParameter("endDate", endDate);

                    } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                        String sqlSearch = "from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node" + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                        querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        querySearch.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        querySearch.setParameter("endDate", endDate);

                    } else {
                        String sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate) and sa.epicTleNodetype.code =:node" + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                        querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                        //querySearch.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                        //querySearch.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                        querySearch.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        querySearch.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                        Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                        querySearch.setParameter("beginDate", beginDate);
                        Date endDate = dateFormatter.parse(inputBean.getTodate());
                        endDate.setDate(endDate.getDate() + 1);
                        querySearch.setParameter("endDate", endDate);

                    }

                } else if (!inputBean.getTransTID().isEmpty() && inputBean.getTransTID() != null) {
                    String sqlSearch="";
                    if(inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()){
                        sqlSearch = "from EpicTleProcessingTime sa where sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                    }
                    else{
                        sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and sa.tid LIKE :searchTID and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                        querySearch = session.createQuery(sqlSearch);
                        querySearch.setInteger("from", (Integer.parseInt(inputBean.getFrom())));
                        querySearch.setInteger("to", (Integer.parseInt(inputBean.getTo())));
                    }
                    querySearch.setString("searchTID", "%" + inputBean.getTransTID() + "%");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                } else if (inputBean.getTransTID().isEmpty() && inputBean.getFrom().isEmpty() && inputBean.getTo().isEmpty()) {

                    String sqlSearch = "from EpicTleProcessingTime sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                } else {
                    String sqlSearch = "from EpicTleProcessingTime sa where sa.totalTime between :from and :to and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    //querySearch.setString("from", Long.toString(Integer.parseInt(inputBean.getFrom()) * 1000));
                    //querySearch.setString("to", Long.toString(Integer.parseInt(inputBean.getTo()) * 1000));
                    querySearch.setInteger("from",(Integer.parseInt(inputBean.getFrom())));
                    querySearch.setInteger("to",(Integer.parseInt(inputBean.getTo())));
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                }

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);
                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    SystemProcessDataBean databean = new SystemProcessDataBean();
                    EpicTleProcessingTime objBean = (EpicTleProcessingTime) it.next();

                    try {
                        databean.setId(objBean.getId() + "");
                    } catch (NullPointerException npe) {
                        databean.setId("--");
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
                        databean.setTid(objBean.getTid());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setDatetime(objBean.getDatetime().toString());
                    } catch (NullPointerException npe) {
                        databean.setDatetime("--");
                    }
                    try {
                        if (!objBean.getResponseCode().isEmpty() && objBean.getResponseCode() != null) {
                            databean.setResponseCode(objBean.getResponseCode());
                        } else {
                            databean.setResponseCode("--");
                        }
                    } catch (NullPointerException npe) {
                        databean.setResponseCode("--");
                    }
                    try {
                        databean.setBin(objBean.getBin());
                    } catch (NullPointerException npe) {
                        databean.setBin("--");
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
                        databean.setEpicTleStatus(objBean.getEpicTleStatus().getCode() + "");
                    } catch (NullPointerException npe) {
                        databean.setEpicTleStatus("--");
                    }
                    try {
                        databean.setNode(objBean.getEpicTleNodetype().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setNode("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }

            }

        } catch (Exception ex) {
            ex.printStackTrace();
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
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
    public SystemProcessInputBean getPagePath(String page, SystemProcessInputBean inputBean) throws Exception {
        if (!page.isEmpty() && !page.equals("")) {

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
                    session.clear();
                    session.getTransaction().commit();
                    session.close();
                    session = null;
                }
            }
        }
        return inputBean;
    }

    @Override
    public SystemProcessInputBean loadMap(SystemProcessInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
