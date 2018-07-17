/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.RefTerminalBean;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleTerminalRefprofile;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public class RefTerminalProfileService implements RefTerminalProfileInf {

    @Override
    public List<RefTerminalBean> loadRefProfile(RefTerminalBean inputBean, int max, int first, String orderBy) throws Exception {
        List<RefTerminalBean> dataList = new ArrayList<RefTerminalBean>();

        Session session = null;
        try {
            
            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by wu.timestamp desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
            Query queryCount;
            Query querySearch = null;

            if (inputBean.getSearchName().equals("")) {
                sqlCount = "select count(id) from EpicTleTerminalRefprofile wu " + orderBy;
                queryCount = session.createQuery(sqlCount);

                sqlSearch = "from EpicTleTerminalRefprofile wu " + orderBy;
                querySearch = session.createQuery(sqlSearch);
            } else {

                sqlCount = "select count(id) from EpicTleTerminalRefprofile wu where wu.name LIKE :des " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", "%" + inputBean.getSearchName() + "%");

                sqlSearch = "from EpicTleTerminalRefprofile wu where wu.name LIKE :des " + orderBy;
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

                    RefTerminalBean databean = new RefTerminalBean();
                    EpicTleTerminalRefprofile objBean = (EpicTleTerminalRefprofile) it.next();

                    if (objBean.getId() != 1) {
                        databean.setId(Integer.parseInt(objBean.getId().toString()));
                        databean.setName(objBean.getName());
                        databean.setMinAmount(Integer.toString(objBean.getMinAmount()));
                        databean.setMaxAmount(Integer.toString(objBean.getMaxAmount()));
                        databean.setFrom(Integer.toString(objBean.getOprHoursFrom()));
                        databean.setTo(Integer.toString(objBean.getOprHoursTo()));
                        databean.setSwipeStatus(objBean.getEpicTleStatusBySwipeTransStatus().getDescription());
                        databean.setFallBackStatus(objBean.getEpicTleStatusByFallBackStatus().getDescription());
                        databean.setNfcBasedStatus(objBean.getEpicTleStatusByNfcTxnStatus().getDescription());
                        databean.setPinPerformStatus(objBean.getEpicTleStatusByPinTxnStatus().getDescription());
                        databean.setStatus(Integer.toString(objBean.getEpicTleStatusByStatus().getCode()));

                        databean.setFullCount(count);
                        dataList.add(databean);
                    }

                }
            }
        } catch (Exception e) {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            e.printStackTrace();
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
    public boolean insertRefProfile(RefTerminalBean inputBean) throws Exception {
        boolean binAdd = false;
        EpicTleTerminalRefprofile epicTleBinProfile = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleBinProfile = new EpicTleTerminalRefprofile();

            epicTleBinProfile.setName(inputBean.getName());
            epicTleBinProfile.setMinAmount(Integer.parseInt(inputBean.getMinAmount()));
            epicTleBinProfile.setMaxAmount(Integer.parseInt(inputBean.getMaxAmount()));
            epicTleBinProfile.setOprHoursFrom(Integer.parseInt(inputBean.getFrom()));
            epicTleBinProfile.setOprHoursTo(Integer.parseInt(inputBean.getTo()));

            EpicTleStatus swipeStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getSwipeStatus()));

            epicTleBinProfile.setEpicTleStatusBySwipeTransStatus(swipeStatus);

            EpicTleStatus fallbackStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getFallBackStatus()));

            epicTleBinProfile.setEpicTleStatusByFallBackStatus(fallbackStatus);

            EpicTleStatus nfcBaseStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getNfcBasedStatus()));

            epicTleBinProfile.setEpicTleStatusByNfcTxnStatus(nfcBaseStatus);

            EpicTleStatus pinPerformStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getPinPerformStatus()));

            epicTleBinProfile.setEpicTleStatusByPinTxnStatus(pinPerformStatus);

            EpicTleStatus status = (EpicTleStatus) session.get(EpicTleStatus.class, Status.ACTIVE);
            epicTleBinProfile.setEpicTleStatusByStatus(status);

            session.beginTransaction();
            session.save(epicTleBinProfile);
            session.getTransaction().commit();

            binAdd = true;

        } catch (Exception e) {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.beginTransaction();
                session.save(epicTleBinProfile);
                session.getTransaction().commit();
                session.flush();
                session.close();
                session = null;
            }
        }
        return binAdd;
    }

    @Override
    public void getUpdateData(RefTerminalBean inputBean) throws Exception {
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            EpicTleTerminalRefprofile refProfile = (EpicTleTerminalRefprofile) session.get(EpicTleTerminalRefprofile.class, inputBean.getUpProfileID());

            if (refProfile != null) {
                inputBean.setUpName(refProfile.getName());
                inputBean.setUpminAmount(Integer.toString(refProfile.getMinAmount()));
                inputBean.setUpmaxAmount(Integer.toString(refProfile.getMaxAmount()));
                inputBean.setUpfrom(Integer.toString(refProfile.getOprHoursFrom()));
                inputBean.setUpto(Integer.toString(refProfile.getOprHoursTo()));
                inputBean.setUpswipeStatus(Integer.toString(refProfile.getEpicTleStatusBySwipeTransStatus().getCode()));
                inputBean.setUpfallBackStatus(Integer.toString(refProfile.getEpicTleStatusByFallBackStatus().getCode()));
                inputBean.setUpnfcBasedStatus(Integer.toString(refProfile.getEpicTleStatusByNfcTxnStatus().getCode()));
                inputBean.setUpPinPerformStatus(Integer.toString(refProfile.getEpicTleStatusByPinTxnStatus().getCode()));
                inputBean.setUpStatus(refProfile.getEpicTleStatusByStatus().getCode());
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
    public boolean deleteRefProfile(RefTerminalBean inputBean) throws Exception {
        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DELETE EpicTleTerminalRefprofile bn WHERE bn.id=:id";
            query = session.createQuery(sql);
            query.setInteger("id", inputBean.getId());
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

    @Override
    public boolean updateRefProfile(RefTerminalBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        EpicTleTerminalRefprofile refProfile = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleTerminalRefprofile wu where wu.id =:ID";
            query = session.createQuery(sql);
            query.setString("ID", inputBean.getUpProfileID() + "");
            refProfile = (EpicTleTerminalRefprofile) query.uniqueResult();
            if (refProfile != null) {
                refProfile.setName(inputBean.getUpName());
                refProfile.setMinAmount(Integer.parseInt(inputBean.getUpminAmount()));
                refProfile.setMaxAmount(Integer.parseInt(inputBean.getUpmaxAmount()));
                refProfile.setOprHoursFrom(Integer.parseInt(inputBean.getUpfrom()));
                refProfile.setOprHoursTo(Integer.parseInt(inputBean.getUpto()));

                EpicTleStatus swipeStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getUpswipeStatus()));

                refProfile.setEpicTleStatusBySwipeTransStatus(swipeStatus);

                EpicTleStatus fallbackStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getUpfallBackStatus()));

                refProfile.setEpicTleStatusByFallBackStatus(fallbackStatus);

                EpicTleStatus status = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getUpStatus());
                refProfile.setEpicTleStatusByStatus(status);

                EpicTleStatus nfcBaseStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getUpnfcBasedStatus()));

                refProfile.setEpicTleStatusByNfcTxnStatus(nfcBaseStatus);

                EpicTleStatus pinPerformStatus = (EpicTleStatus) session.get(EpicTleStatus.class, Integer.parseInt(inputBean.getUpPinPerformStatus()));

                refProfile.setEpicTleStatusByPinTxnStatus(pinPerformStatus);

                session.update(refProfile);
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            e.printStackTrace();
//            throw e;
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
    public RefTerminalBean getPagePath(String page, RefTerminalBean inputBean) throws Exception {
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
    public String GetResult(RefTerminalBean inputBean) throws Exception {
        Session session = null;
        String sqlCount = "";
        String profile = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleTerminalRefprofile wu where wu.name=:name";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("name", inputBean.getName());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleTerminalRefprofile objBean = (EpicTleTerminalRefprofile) it.next();
                profile = objBean.getName();
            }
        } catch (Exception e) {
            e.printStackTrace();
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
        return profile;
    }

}
