/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.service;

import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.mapping.EpicTleOperationAlerts;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.operationManagement.bean.OperationManageBean;
import com.epic.tle.mapping.EpicTleSysOperation;
import com.epic.tle.operationManagement.bean.OperationBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author thilina_t
 */
public class OperationService implements OperationManageInf {

    @Override
    public void getOperations(OperationManageBean inputBean) throws Exception {
        List<EpicTleSysOperation> tleDeviceTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleSysOperation ";
            Query query = session.createQuery(sql);
            tleDeviceTypes = query.list();

            for (int i = 0; i < tleDeviceTypes.size(); i++) {
                if (tleDeviceTypes.get(i).getCode() != 6) {
                    inputBean.getOperationList().put(tleDeviceTypes.get(i).getCode(), tleDeviceTypes.get(i).getDescription());
                }
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
                    session.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public List<OperationManageBean> loadListnerData(OperationManageBean inputBean, int rows, int from, String orderBy, boolean isFromDashBoard) throws Exception {
        List<OperationManageBean> dataList = new ArrayList<OperationManageBean>();
        Session session = null;
        boolean maxReached=false;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.datetime desc ";
            }
            if (inputBean.getFromdate() == null && inputBean.getTodate() == null) {
                inputBean.setFromdate(Util.currentDate());
                inputBean.setTodate(Util.tomorrowDate());
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
//            String sqlCount = "select count(username) from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
            if (!inputBean.isSearch()) {
                if(isFromDashBoard){
                    sqlCount = "select count(*) from EpicTleOperationAlerts wu "/*where wu.epicTleNodetype.code =:node "*/ + orderBy;
                }
                else{
                    sqlCount = "select count(*) from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node " + orderBy;
                }
                Query queryCount = session.createQuery(sqlCount);
                if(!isFromDashBoard)queryCount.setInteger("node", Configurations.SERVER_NODE);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();
            } else {
//                sqlCount = "select count(*) from EpicTleOperationAlerts wu " + orderBy;
                sqlCount = "select count(*) from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node and (wu.datetime >= :beginDate and wu.datetime <= :endDate) " + orderBy;
                Query queryCount = session.createQuery(sqlCount);
                queryCount.setInteger("node", Configurations.SERVER_NODE);
                SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                queryCount.setParameter("beginDate", beginDate);
                Date endDate = dateFormatter.parse(inputBean.getTodate());
                endDate.setDate(endDate.getDate() + 1);
                queryCount.setParameter("endDate", endDate);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();
            }
            if(count>Configurations.MAX_FETCH_SIZE) {
                count=Configurations.MAX_FETCH_SIZE; 
                maxReached=true;
            } 
            if (count > 0) {
                Query querySearch = null;
//                String sqlSearch = "from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
                if (!inputBean.isSearch()) {
                    if(isFromDashBoard){
                        sqlSearch = "from EpicTleOperationAlerts wu "/*where wu.epicTleNodetype.code =:node " */+ orderBy;
                    }
                    else{
                        sqlSearch = "from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node " + orderBy;
                    }
                    querySearch = session.createQuery(sqlSearch);
                    if(!isFromDashBoard)querySearch.setInteger("node", Configurations.SERVER_NODE);
                } else {
                    sqlSearch = "from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node and (wu.datetime >= :beginDate and wu.datetime <= :endDate) " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setInteger("node", Configurations.SERVER_NODE);
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

                    OperationManageBean databean = new OperationManageBean();
                    EpicTleOperationAlerts objBean = (EpicTleOperationAlerts) it.next();

//                    EpicTleSysOperation opr = (EpicTleSysOperation)session.get(EpicTleSysOperation.class,objBean.getOperationId());
                    databean.setOperaiton(objBean.getEpicTleSysOperation().getDescription());
                    databean.setColorCode(Integer.toString(objBean.getEpicTleSysOperation().getEpicTleColorFormat().getCode()));
                    databean.setUsername(objBean.getUsername());
                    databean.setIp(objBean.getIp());
                    databean.setStauts(objBean.getEpicTleStatus().getCode());
                    databean.setStatusStr(objBean.getEpicTleStatus().getDescription());
                    databean.setDateTime(objBean.getDatetime().toString());
                    databean.setMessage(objBean.getMessage());
                    databean.setNode(objBean.getEpicTleNodetype().getDescription());

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
    public void getMessage(OperationManageBean inputBean) throws Exception {

        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            EpicTleSysOperation operation = (EpicTleSysOperation) session.get(EpicTleSysOperation.class, inputBean.getListOpr());
            inputBean.setOprMessage(operation.getMessage());
            inputBean.setColorCode(Integer.toString(operation.getEpicTleColorFormat().getCode()));
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                if (session != null) {
                    session.flush();
                    session.close();
                }
            } catch (Exception e) {
                throw e;
            }
        }
    }

    @Override
    public void getReportDetails(OperationManageBean inputBean) throws Exception {
        Session session = null;
        try {
            String orderBy = " order by wu.datetime desc ";

            if (inputBean.getFromdate() == null && inputBean.getTodate() == null) {
                inputBean.setFromdate(Util.currentDate());
                inputBean.setTodate(Util.tomorrowDate());
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
//            String sqlCount = "select count(username) from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
            if (inputBean.getFromdate() == null && inputBean.getTodate() == null) {
                sqlCount = "select count(*) from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node " + orderBy;
                Query queryCount = session.createQuery(sqlCount);
                queryCount.setInteger("node", Configurations.SERVER_NODE);
                Iterator itCount = queryCount.iterate();
                count = (Long) itCount.next();
            } else {
//                sqlCount = "select count(*) from EpicTleOperationAlerts wu " + orderBy;
                sqlCount = "select count(*) from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node and (wu.datetime >= :beginDate and wu.datetime <= :endDate)" + orderBy;
                Query queryCount = session.createQuery(sqlCount);
                queryCount.setInteger("node", Configurations.SERVER_NODE);
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
                Query querySearch = null;
//                String sqlSearch = "from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
                if (inputBean.getFromdate() == null && inputBean.getTodate() == null) {
                    sqlSearch = "from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setInteger("node", Configurations.SERVER_NODE);
                    
                } else {
                    sqlSearch = "from EpicTleOperationAlerts wu where wu.epicTleNodetype.code =:node and (wu.datetime >= :beginDate and wu.datetime <= :endDate)" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setInteger("node", Configurations.SERVER_NODE);
                    SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date beginDate = dateFormatter.parse(inputBean.getFromdate());
                    querySearch.setParameter("beginDate", beginDate);
                    Date endDate = dateFormatter.parse(inputBean.getTodate());
                    endDate.setDate(endDate.getDate() + 1);
                    querySearch.setParameter("endDate", endDate);
                }

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    OperationManageBean databean = new OperationManageBean();
                    EpicTleOperationAlerts objBean = (EpicTleOperationAlerts) it.next();

//                    EpicTleSysOperation opr = (EpicTleSysOperation)session.get(EpicTleSysOperation.class,objBean.getOperationId());
                    databean.setOPERATION(objBean.getEpicTleSysOperation().getDescription());
                    databean.setUSERNAME(objBean.getUsername());
                    databean.setIP(objBean.getIp());
                    databean.setStauts(objBean.getEpicTleStatus().getCode());
                    databean.setSTATUS(objBean.getEpicTleStatus().getDescription());
                    databean.setDATETIME(objBean.getDatetime().toString());
                    databean.setMESSAGE(objBean.getMessage());

                    databean.setFullCount(count);

                    inputBean.getReportdatalist().add(databean);
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
                session.getTransaction().commit();
                session.clear();
                session.close();
                session = null;
            }
        }
    }

    @Override
    public void inactiveBindStatus() throws Exception {
        Session session = HibernateInit.sessionFactory.openSession();
        try {

            session.beginTransaction();
            String s = "update EpicTleChannel ch set ch.epicTleStatusByBindStatus.code=:bindcode,ch.epicTleStatusByBindSecondaryStatus.code=:seccode";
            Query q = session.createQuery(s);
            q.setInteger("bindcode", 2);
            q.setInteger("seccode", 2);
            int rows = q.executeUpdate();

            s = "update EpicTleListeners ch set ch.epicTleStatusByBindStatus.code=:code";
            Query q1 = session.createQuery(s);
            q1.setInteger("code", 2);
            int rows1 = q1.executeUpdate();

            session.getTransaction().commit();

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
    public OperationManageBean getPagePath(String page, OperationManageBean inputBean) throws Exception {

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
    public void insertOperationAlerts(OperationBean alerts) throws Exception {
        EpicTleOperationAlerts operationAlert = new EpicTleOperationAlerts();
        Session session = null;
        try {
            

            session = HibernateInit.sessionFactory.openSession();
            EpicTleSysOperation operation = (EpicTleSysOperation) session.get(EpicTleSysOperation.class, Integer.parseInt(alerts.getOperation()));
            operationAlert.setEpicTleSysOperation(operation);
            
            operationAlert.setUsername(alerts.getUsername());
            operationAlert.setIp(alerts.getIp());
            operationAlert.setMessage(alerts.getMessage());
            operationAlert.setEpicTleStatus((EpicTleStatus)session.get(EpicTleStatus.class, 6));
            EpicTleNodetype node = (EpicTleNodetype)session.get(EpicTleNodetype.class, Configurations.SERVER_NODE);
            operationAlert.setEpicTleNodetype(node);

            session.beginTransaction();
            session.save(operationAlert);
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }

        }
    }

}
