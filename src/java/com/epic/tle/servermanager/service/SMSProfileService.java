/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleSmsProfile;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.servermanager.bean.SMSProfileBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author ridmi_g
 */
public class SMSProfileService implements SMSProfileInf {

    public SMSProfileBean getPagePath(String page, SMSProfileBean inputBean) throws Exception {
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
    public boolean insertSMSProfile(SMSProfileBean inputBean) throws Exception {

        boolean SMSProfileAdd = false;
        EpicTleSmsProfile epicTleSmsProfile = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            epicTleSmsProfile = new EpicTleSmsProfile();

            epicTleSmsProfile.setProfileName(inputBean.getGn());
            epicTleSmsProfile.setCeratedDate(Util.getLocalDate());
            EpicTleStatus epicTleStatus = new EpicTleStatus();
            epicTleStatus.setCode(1);
            epicTleSmsProfile.setEpicTleStatus(epicTleStatus);

            session.save(epicTleSmsProfile);
            session.getTransaction().commit();
            SMSProfileAdd = true;

        } catch (Exception e) {
            if (session != null) {
                session.flush();
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

        return SMSProfileAdd;
    }

    @Override
    public String GetResult(SMSProfileBean inputBean) throws Exception {

        Session session = null;
        String sqlCount = "";
        String profile = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleSmsProfile wu where wu.profileName=:name";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("name", inputBean.getGn());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleSmsProfile objBean = (EpicTleSmsProfile) it.next();
                profile = objBean.getProfileName();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            throw e;
        }finally {
            try {
                if (session != null) {
                    session.flush();
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return profile;

    }

    @Override
    public List<SMSProfileBean> loadSMSProfile(SMSProfileBean inputBean, int max, int first, String orderBy) throws Exception {
        List<SMSProfileBean> dataList = new ArrayList<SMSProfileBean>();

        Session session = null;
        try {
            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by wu.ceratedDate desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
            Query queryCount;
            Query querySearch = null;

            if (inputBean.getSearchName().equals("")) {
                sqlCount = "select count(smsProfileId) from EpicTleSmsProfile wu " + orderBy;
                queryCount = session.createQuery(sqlCount);

                sqlSearch = "from EpicTleSmsProfile wu " + orderBy;
                querySearch = session.createQuery(sqlSearch);
            } else {

                sqlCount = "select count(smsProfileId) from EpicTleSmsProfile wu where wu.profileName LIKE :des " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", "%" + inputBean.getSearchName() + "%");

                sqlSearch = "from EpicTleSmsProfile wu where wu.profileName LIKE :des " + orderBy;
                querySearch = session.createQuery(sqlSearch);
                querySearch.setString("des", "%" + inputBean.getSearchName() + "%");

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {
                    SMSProfileBean databean = new SMSProfileBean();
                    EpicTleSmsProfile objBean = (EpicTleSmsProfile) it.next();
//                    if (objBean.getId != 1) {
                    try {
                        databean.setId(Integer.parseInt(objBean.getSmsProfileId().toString()));
                    } catch (NullPointerException npe) {
                        databean.setId(0);
                    }
                    try {
                        databean.setGn(objBean.getProfileName());
                    } catch (NullPointerException npe) {
                        databean.setGn("--");
                    }
                    try {
                        databean.setStatus(objBean.getEpicTleStatus().getCode());
                    } catch (NullPointerException npe) {
                        databean.setStatus(0);
                    }
                    try {
                        databean.setDatetime(objBean.getCeratedDate().toString());
                    } catch (NullPointerException npe) {
                        databean.setDatetime(null);
                    }
                    databean.setFullCount(count);
                    dataList.add(databean);
                }
//                }
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

        return dataList;
    }

    @Override
    public void getUpdateData(SMSProfileBean inputBean) throws Exception {
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            EpicTleSmsProfile smsProfile = (EpicTleSmsProfile) session.get(EpicTleSmsProfile.class, inputBean.getUpProfileID());

            if (smsProfile != null) {
                inputBean.setUpName(smsProfile.getProfileName());
                inputBean.setUpStatus(smsProfile.getEpicTleStatus().getCode());
            }
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
    }

    @Override
    public boolean updateprofile(SMSProfileBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        EpicTleSmsProfile tleWebUser = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleSmsProfile wu where wu.smsProfileId =:ID";
            query = session.createQuery(sql);
            query.setString("ID", inputBean.getUpProfileID() + "");
            tleWebUser = (EpicTleSmsProfile) query.uniqueResult();
            if (tleWebUser != null) {
                tleWebUser.setProfileName(inputBean.getUpName());
                if (inputBean.getUpStatus() == 1) {

                    EpicTleStatus epicTleStatus = new EpicTleStatus();
                    epicTleStatus.setCode(1);
                    tleWebUser.setEpicTleStatus(epicTleStatus);
                } else if (inputBean.getUpStatus() == 2) {
                    EpicTleStatus epicTleStatus = new EpicTleStatus();
                    epicTleStatus.setCode(2);
                    tleWebUser.setEpicTleStatus(epicTleStatus);
                }
                tleWebUser.setCeratedDate(tleWebUser.getCeratedDate());

                session.update(tleWebUser);
                session.getTransaction().commit();
                isUpdated = true;
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
                    session = null;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return isUpdated;
    }

    @Override
    public boolean deleteProfile(SMSProfileBean inputBean) throws Exception {
        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DELETE EpicTleSmsProfile bn WHERE bn.smsProfileId=:profile";
            query = session.createQuery(sql);
            query.setInteger("profile", Integer.parseInt(inputBean.getDbinId()));
            int result = query.executeUpdate();
            if (1 == result) {
                isFeDeleted = true;
            }
            session.getTransaction().commit();
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
                    session = null;
                }
            } catch (Exception e) {
                throw e;
            }
        }
        return isFeDeleted;

    }

}
