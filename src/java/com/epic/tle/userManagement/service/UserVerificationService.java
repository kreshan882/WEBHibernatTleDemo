/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.userManagement.bean.UserVerificationBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleUser;

/**
 *
 * @author danushka_r
 */
public class UserVerificationService implements UserVerificationInf {

    public List<UserVerificationBean> loadUsers(UserVerificationBean inputBean, int max, int first, String orderBy) throws Exception {

        List<UserVerificationBean> dataList = new ArrayList<UserVerificationBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.createdate desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "select count(username) from EpicTleUser wu where wu.epicTleStatus.code =:statuscode " + orderBy;
            Query queryCount = session.createQuery(sqlCount);
            queryCount.setInteger("statuscode", Status.ADD);
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from EpicTleUser wu where wu.epicTleStatus.code =:statuscode " + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setInteger("statuscode", Status.ADD);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    UserVerificationBean databean = new UserVerificationBean();
                    EpicTleUser objBean = (EpicTleUser) it.next();

                    try {
                        databean.setName(objBean.getName());
                    } catch (NullPointerException npe) {
                        databean.setName("--");
                    }
                    try {
                        databean.setUserName(objBean.getUsername());
                    } catch (NullPointerException npe) {
                        databean.setUserName("--");
                    }
                    try {
                        databean.setUserType(objBean.getEpicTleUserProfile().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setUserTypeId("" + objBean.getEpicTleUserProfile().getCode());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setDatetime(objBean.getCreatedate());
                    } catch (NullPointerException npe) {
                        databean.setDatetime(null);
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

    public boolean activeUser(String userName) throws Exception {
        boolean isUserConfirmed = false;
        Session session = null;
        Query query = null;

        List<EpicTleUser> tleUser = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:name";
            query = session.createQuery(sql);
            query.setString("name", userName);
            tleUser = (List<EpicTleUser>) query.list();
            if (tleUser.size() > 0) {
                EpicTleStatus status = new EpicTleStatus();
                status.setCode(Status.ACTIVE);
                tleUser.get(0).setEpicTleStatus(status);
                session.save(tleUser.get(0));
                session.getTransaction().commit();
                isUserConfirmed = true;
            }
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
        return isUserConfirmed;
    }

    public boolean deleteUser(String userName) throws Exception {
        boolean isUserDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleUser wu"
                    + "  where wu.username =:username";
            query = session.createQuery(sql);

            query.setString("username", userName);
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

}
