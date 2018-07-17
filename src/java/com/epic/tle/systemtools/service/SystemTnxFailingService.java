/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleAlerts;
import com.epic.tle.mapping.EpicTleAlgorithem;
import com.epic.tle.mapping.EpicTleEncryptionlevles;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleTxntypes;
import com.epic.tle.systemtools.bean.SystemAlertsDataBean;
import com.epic.tle.systemtools.bean.SystemAlertsInputBean;
import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import com.epic.tle.systemtools.bean.SystemTnxFailDataBean;
import com.epic.tle.systemtools.bean.SystemTnxFailInputBean;
import com.epic.tle.util.HibernateInit;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author dimuthu_h
 */
public class SystemTnxFailingService implements SystemTnxFailingFactoryInf {

    public SystemTnxFailInputBean loadDataIntoMap(SystemTnxFailInputBean inputBean) throws Exception {
        Session session = null;
        Query txnTypequery = null;
        Query encLevelquery = null;
        Query encAlgoquery = null;
        try {

            ///***************data load into encAlgo map**************************
            session = HibernateInit.sessionFactory.openSession();
            String txndata = "from EpicTleTxntypes";
            txnTypequery = session.createQuery(txndata);
            Iterator it1 = txnTypequery.iterate();
            while (it1.hasNext()) {
                EpicTleTxntypes txnObj = (EpicTleTxntypes) it1.next();
                inputBean.getTxnTypeMap().put(txnObj.getCode(), txnObj.getDescription());
            }

            ///**********************load data into encMode map*******************
            String encLeveldata = "from EpicTleEncryptionlevles";
            encLevelquery = session.createQuery(encLeveldata);
            Iterator it2 = encLevelquery.iterate();
            inputBean.getEncModeMap().put(0, "UNKWON");
            while (it2.hasNext()) {
                EpicTleEncryptionlevles enclevelObj = (EpicTleEncryptionlevles) it2.next();
                inputBean.getEncModeMap().put(enclevelObj.getCode(), enclevelObj.getDescription());
            }

            ///*****************load data into encAlgo map************************
            String encAlgodata = "from EpicTleAlgorithem";
            encAlgoquery = session.createQuery(encAlgodata);
            Iterator it3 = encAlgoquery.iterate();
            inputBean.getEncAlgoMap().put(0, "UNKWON");
            while (it3.hasNext()) {
                EpicTleAlgorithem encalgoObj = (EpicTleAlgorithem) it3.next();
                inputBean.getEncAlgoMap().put(encalgoObj.getCode(), encalgoObj.getDescription());
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
        return inputBean;
    }

    public List<SystemTnxFailDataBean> loadTnxFialDetails(SystemTnxFailInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<SystemTnxFailDataBean> dataList = new ArrayList<SystemTnxFailDataBean>();

        System.out.println(
                inputBean.getTid() + " | "
                + inputBean.getSerial() + " | "
                + inputBean.getRespCode() + " | "
                + inputBean.getFromdate() + " | "
                + inputBean.getTodate()
        );

        Session session = null;
        try {
            long count = 0;

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by txn.tid desc ";
            }

            session = HibernateInit.sessionFactory.openSession();
            Query querySearch = null;
            Query queryCount = null;
            if ((inputBean.getTid() == null || inputBean.getTid().isEmpty())
                    && (inputBean.getSerial() == null || inputBean.getSerial().isEmpty())
                    && (inputBean.getRespCode() == null || inputBean.getRespCode().isEmpty())
                    && (inputBean.getFromdate() == null || inputBean.getFromdate().isEmpty())
                    && (inputBean.getTodate() == null || (inputBean.getTodate().isEmpty()))) {
                String sqlCount = "select count(sid) from EpicTleAlerts txn" + orderBy;
                queryCount = session.createQuery(sqlCount);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();

                String sqlSearch = "from EpicTleAlerts txn" + orderBy;
                querySearch = session.createQuery(sqlSearch);

            } else {
                String sqlCount = "select count(sid) from EpicTleAlerts txn where txn.tid LIKE:tid and txn.serialno LIKE:serial and (txn.datetime >= :beginDate and txn.datetime <= :endDate)" + orderBy;
//                String sqlCount = "select count(sid) from EpicTleAlerts txn where txn.tid LIKE:tid and txn.serialno LIKE:serial and txn.responsecode LIKE:respCode and (txn.datetime >= :beginDate and txn.datetime <= :endDate)" + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setParameter("tid", "%" + inputBean.getTid().trim() + "%");
                queryCount.setParameter("serial", "%" + inputBean.getSerial().trim() + "%");
//                queryCount.setParameter("respCode", "%" + inputBean.getRespCode().trim() + "%");

                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);

                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);

                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();
                System.out.println("Row Count: " + count);

//                String sqlSearch = "from EpicTleTxnerrors txn where txn.tid LIKE:tid" + orderBy;
                String sqlSearch = "from EpicTleAlerts txn where txn.tid LIKE:tid and txn.serialno LIKE:serial and (txn.datetime >= :beginDate and txn.datetime <= :endDate)" + orderBy;
//                String sqlSearch = "from EpicTleAlerts txn where txn.tid LIKE:tid and txn.serialno LIKE:serial and txn.responsecode LIKE:respCode and (txn.datetime >= :beginDate and txn.datetime <= :endDate)" + orderBy;
                querySearch = session.createQuery(sqlSearch);
                querySearch.setParameter("tid", "%" + inputBean.getTid().trim() + "%");
                querySearch.setParameter("serial", "%" + inputBean.getSerial().trim() + "%");
//                querySearch.setParameter("respCode", "%" + inputBean.getRespCode().trim() + "%");

                querySearch.setParameter("beginDate", beginDate);

                querySearch.setParameter("endDate", endDate);

            }

            querySearch.setMaxResults(max);
            querySearch.setFirstResult(first);
            Iterator it = querySearch.iterate();
            while (it.hasNext()) {
                SystemTnxFailDataBean dataBean = new SystemTnxFailDataBean();
                EpicTleAlerts tnxFailTable = (EpicTleAlerts) it.next();

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
                        dataBean.setTnxtpe(inputBean.getTxnTypeMap().get(tnxFailTable.getEpicTleTxntypes()));
                    } catch (NullPointerException npex) {
                        dataBean.setTnxtpe("--");
                    }
                    try {
//                        dataBean.setResponsecode(tnxFailTable.getEpicTleResponsecode().getDescription());
                    } catch (NullPointerException npex) {
                        dataBean.setResponsecode("--");
                    }
                    try {
//                        dataBean.setEncmode(inputBean.getEncModeMap().get(tnxFailTable.getApplyenmode()));
                    } catch (NullPointerException npex) {
                        dataBean.setEncmode("--");
                    }
                    try {
//                        dataBean.setEncalgo(inputBean.getEncAlgoMap().get(tnxFailTable.getEnalogo()));
                    } catch (NullPointerException npex) {
                        dataBean.setEncalgo("--");
                    }
                    try {
//                        dataBean.setDiviceip(tnxFailTable.getDeviceip());
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
                    dataList.add(dataBean);
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
        return dataList;
    }

    @Override
    public List<SystemAlertsDataBean> loadAlertsDetails(SystemAlertsInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemHistoryInputBean loadMap(SystemHistoryInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemTnxFailInputBean getPagePath(String page, SystemTnxFailInputBean inputBean) throws Exception {

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
    public SystemAlertsInputBean getPagePath(String page, SystemAlertsInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemHistoryInputBean getPagePath(String page, SystemHistoryInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemHistoryDataBean> loadHistoryDetails(SystemHistoryInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getAlertType() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getRisklevel() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getStatusValues(int from, int to) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getMasterValues(int from, int to, String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemAlertsDataBean> loadAlertsDetailsTrans(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception{
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<SystemAlertsDataBean> loadAlertsDetailsSystem(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
