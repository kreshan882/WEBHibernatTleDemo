/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleAlertType;
import com.epic.tle.mapping.EpicTleAlerts;
import com.epic.tle.mapping.EpicTleResponsecode;
import com.epic.tle.mapping.EpicTleRiskLevel;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.systemtools.bean.SystemAlertsDataBean;
import com.epic.tle.systemtools.bean.SystemAlertsInputBean;
import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import com.epic.tle.systemtools.bean.SystemTnxFailDataBean;
import com.epic.tle.systemtools.bean.SystemTnxFailInputBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dimuthu_h
 */
public class SystemAlertsService implements SystemTnxFailingFactoryInf {

    public List<SystemAlertsDataBean> loadAlertsDetails(SystemAlertsInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<SystemAlertsDataBean> dataList = new ArrayList<SystemAlertsDataBean>();
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
            System.out.println("Select Node is = " + inputBean.getSelectNode());
            if (!inputBean.isSearch()) {
                //Edit by ridmi 2017 11 13
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                inputBean.setTodate(Util.currentDate());
                inputBean.setFromdate(Util.currentDate());

                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);

                //String sqlCount = "select count(sid) from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                String sqlCount = "select count(sid) from EpicTleAlerts sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                queryCount = session.createQuery(sqlCount);
                // queryCount.setInteger("node", Configurations.SERVER_NODE); 
                queryCount.setParameter("beginDate", beginDate);
                queryCount.setParameter("endDate", endDate);
//                Iterator itCount = queryCount.iterate();
//                count = (Long) itCount.next();
            } else if (!inputBean.getSelectNode().equals("-1")) {

                String sqlCount = "select count(sid) from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                queryCount = session.createQuery(sqlCount);
                queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                queryCount.setString("alert", "%" + inputBean.getTid() + "%");
                queryCount.setString("risk", "%" + inputBean.getSid() + "%");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);

            } else {

                String sqlCount = "select count(sid) from EpicTleAlerts sa where sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("alert", "%" + inputBean.getTid() + "%");
                queryCount.setString("risk", "%" + inputBean.getSid() + "%");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);

            }
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

                if (!inputBean.isSearch()) {
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    //String sqlSearch = "from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    String sqlSearch = "from EpicTleAlerts sa where (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    //querySearch.setInteger("node", Configurations.SERVER_NODE);
                    querySearch.setParameter("beginDate", beginDate);
                    querySearch.setParameter("endDate", endDate);

                } else if (!inputBean.getSelectNode().equals("-1")) {

                    String sqlSearch = "from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    querySearch.setString("alert", "%" + inputBean.getTid() + "%");
                    querySearch.setString("risk", "%" + inputBean.getSid() + "%");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                } else {

                    String sqlSearch = "from EpicTleAlerts sa where sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);

                    querySearch.setString("alert", "%" + inputBean.getTid() + "%");
                    querySearch.setString("risk", "%" + inputBean.getSid() + "%");
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

                    SystemAlertsDataBean databean = new SystemAlertsDataBean();
                    EpicTleAlerts objBean = (EpicTleAlerts) it.next();

                    try {
                        databean.setTid(objBean.getTid());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setSid(objBean.getSid().toString());
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }
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
                        databean.setRisklevl(objBean.getEpicTleRiskLevel().getCode() + "");
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
                        databean.setNode(objBean.getEpicTleNodetype().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setNode("--");
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
                        databean.setTleStatus(objBean.getTleStatus().toString() + "");
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
                    }
                    try {
                        databean.setMti(objBean.getMti());
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
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
    public List<SystemTnxFailDataBean> loadTnxFialDetails(SystemTnxFailInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemHistoryInputBean loadMap(SystemHistoryInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemTnxFailInputBean getPagePath(String page, SystemTnxFailInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemAlertsInputBean getPagePath(String page, SystemAlertsInputBean inputBean) throws Exception {
        String module = (page != null) ? page.substring(0, 2) : "";
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

        return inputBean;
    }

    @Override
    public SystemHistoryInputBean getPagePath(String page, SystemHistoryInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemTnxFailInputBean loadDataIntoMap(SystemTnxFailInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemHistoryDataBean> loadHistoryDetails(SystemHistoryInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getStatusValues(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getMasterValues(int from, int to, String table) {
        Session session = null;
        List result = new ArrayList();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Query query = session.createQuery("from " + table + " o order by o.code asc");
            query.setFirstResult(from);
            query.setMaxResults(to);
            result = query.list();

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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
        return result;
    }

    @Override
    public Map<Integer, String> getAlertType() throws Exception {
        Map<Integer, String> alerts = new HashMap<Integer, String>();
        List<EpicTleAlertType> list = getMasterValues(0, 2, "EpicTleAlertType");
        for (EpicTleAlertType list1 : list) {
            alerts.put(list1.getCode(), list1.getDescription());
        }
        return alerts;
    }

    @Override
    public Map<Integer, String> getRisklevel() throws Exception {
        Map<Integer, String> riskLevel = new HashMap<Integer, String>();
        List<EpicTleRiskLevel> list = getMasterValues(0, 3, "EpicTleRiskLevel");
        for (EpicTleRiskLevel list1 : list) {
            riskLevel.put(list1.getCode(), list1.getDescription());
        }
        return riskLevel;
    }

    @Override
    public List<SystemAlertsDataBean> loadAlertsDetailsTrans(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception {
        List<SystemAlertsDataBean> dataList = new ArrayList<SystemAlertsDataBean>();
        Session session = null;
        boolean maxReached=false;
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

                String sqlCount = "select count(sid) from EpicTleAlerts sa where "+/*sa.epicTleNodetype.code =:node and */ "sa.epicTleAlertType.code=:code" + orderBy;
                queryCount = session.createQuery(sqlCount).setInteger("code", 2);
                //queryCount.setInteger("node", Configurations.SERVER_NODE);
//                queryCount.setMaxResults(5);

            } else if (!inputBean.getSelectNode().equals("-1")) {

                String sqlCount = "select count(sid) from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                queryCount = session.createQuery(sqlCount);
                queryCount.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                queryCount.setString("alert", "%" + inputBean.getTid() + "%");
                queryCount.setString("risk", "%" + inputBean.getSid() + "%");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);

            } else {

                String sqlCount = "select count(sid) from EpicTleAlerts sa where sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("alert", "%" + inputBean.getTid() + "%");
                queryCount.setString("risk", "%" + inputBean.getSid() + "%");
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if(count>Configurations.MAX_FETCH_SIZE) {
                count=Configurations.MAX_FETCH_SIZE; 
                maxReached=true;
            }           
            if (count > 0) {

                if (!inputBean.isSearch()) {
                    String sqlSearch = "from EpicTleAlerts sa where sa.epicTleAlertType.code=:code" /* and sa.epicTleNodetype.code =:node" */+ orderBy;
                    querySearch = session.createQuery(sqlSearch).setInteger("code", 2);
                    //querySearch.setInteger("node", Configurations.SERVER_NODE);
//                   querySearch.setMaxResults(5);

//                    Query query = session.createQuery("from EpicTleAlerts");
//                    query.setMaxResults(5);
//                    Query q = session.createQuery("from EpicTleAlerts sa order by sa.datetime desc");
//                    q.setFirstResult(0).setMaxResults(5);

                } else if (!inputBean.getSelectNode().equals("-1")) {

                    String sqlSearch = "from EpicTleAlerts sa where sa.epicTleNodetype.code =:node and sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)";
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setInteger("node", Integer.parseInt(inputBean.getSelectNode()));
                    querySearch.setString("alert", "%" + inputBean.getTid() + "%");
                    querySearch.setString("risk", "%" + inputBean.getSid() + "%");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                } else {

                    String sqlSearch = "from EpicTleAlerts sa where sa.epicTleAlertType.code LIKE:alert and sa.epicTleRiskLevel.code LIKE:risk and (sa.datetime >= :beginDate and sa.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setString("alert", "%" + inputBean.getTid() + "%");
                    querySearch.setString("risk", "%" + inputBean.getSid() + "%");
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);

                }
                if(maxReached){
                    rows=((int)count-from);
                }
                querySearch.setMaxResults(rows);
                querySearch.setFirstResult(from);
                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    SystemAlertsDataBean databean = new SystemAlertsDataBean();
                    EpicTleAlerts objBean = (EpicTleAlerts) it.next();

                    try {
                        databean.setTid(objBean.getTid());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setSid(objBean.getSid().toString());
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }
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
                        databean.setRisklevl(objBean.getEpicTleRiskLevel().getCode() + "");
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
                        databean.setNode(objBean.getEpicTleNodetype().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setNode("--");
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
                        databean.setTleStatus(objBean.getTleStatus().toString() + "");
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
                    }
                    try {
                        databean.setMti(objBean.getMti());
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
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
    public List<SystemAlertsDataBean> loadAlertsDetailsSystem(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception {

        List<SystemAlertsDataBean> dataList = new ArrayList<SystemAlertsDataBean>();
        Session session = null;
        Query querySearch = null;
        Query queryCount = null;
        boolean maxReached=false;
        try {
            long count = 0;

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by sa.datetime desc ";
            }

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            if (!inputBean.isSearch()) {
                String sqlCount = "select count(sid) from EpicTleAlerts sa where "/*sa.epicTleNodetype.code =:node and*/ +" sa.epicTleAlertType.code=:code" + orderBy;
                queryCount = session.createQuery(sqlCount).setInteger("code", 1);
                //queryCount.setInteger("node", Configurations.SERVER_NODE);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();
            }

            if(count>Configurations.MAX_FETCH_SIZE) {
                count=Configurations.MAX_FETCH_SIZE; 
                maxReached=true;
            } 
            if (count > 0) {

                if (!inputBean.isSearch()) {
                    String sqlSearch = "from EpicTleAlerts sa where "/*sa.epicTleNodetype.code =:node and*/+" sa.epicTleAlertType.code=:code" + orderBy;
                    querySearch = session.createQuery(sqlSearch).setInteger("code", 1);
                    //querySearch.setInteger("node", Configurations.SERVER_NODE);

                }
                if(maxReached){
                    rows=((int)count-from);
                }
                querySearch.setMaxResults(rows);
                querySearch.setFirstResult(from);
                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    SystemAlertsDataBean databean = new SystemAlertsDataBean();
                    EpicTleAlerts objBean = (EpicTleAlerts) it.next();

                    try {
                        databean.setTid(objBean.getTid());
                    } catch (NullPointerException npe) {
                        databean.setTid("--");
                    }
                    try {
                        databean.setSid(objBean.getSid().toString());
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }
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

                        databean.setNode(objBean.getEpicTleNodetype().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setNode("--");
                    }
                    try {
                        databean.setRisklevl(objBean.getEpicTleRiskLevel().getCode() + "");
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
                        databean.setTleStatus(objBean.getTleStatus().toString() + "");
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
                    }
                    try {
                        databean.setMti(objBean.getMti());
                    } catch (NullPointerException npe) {
                        databean.setTleStatus("--");
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
}
