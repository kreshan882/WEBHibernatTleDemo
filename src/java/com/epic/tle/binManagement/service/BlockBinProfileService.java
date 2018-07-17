/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.mapping.EpicTleBinProfile;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Status;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author thilina_t
 */
class BlockBinProfileService implements BlockBinProfileInf {

    @Override
    public List<BinProfileBean> loadBinProfile(BinProfileBean inputBean, int max, int first, String orderBy) throws Exception {
        List<BinProfileBean> dataList = new ArrayList<BinProfileBean>();

        Session session = null;
        try {
            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by wu.datetime desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
            Query queryCount;
            Query querySearch = null;

            if (inputBean.getSearchName().equals("")) {
                sqlCount = "select count(id) from EpicTleBinProfile wu " + orderBy;
                queryCount = session.createQuery(sqlCount);

                sqlSearch = "from EpicTleBinProfile wu " + orderBy;
                querySearch = session.createQuery(sqlSearch);
            } else {

                sqlCount = "select count(id) from EpicTleBinProfile wu where wu.description LIKE :des " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", "%" + inputBean.getSearchName() + "%");

                sqlSearch = "from EpicTleBinProfile wu where wu.description LIKE :des " + orderBy;
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
                    BinProfileBean databean = new BinProfileBean();
                    EpicTleBinProfile objBean = (EpicTleBinProfile) it.next();
                    if (objBean.getId() != 1) {
                        try {
                            databean.setId(Integer.parseInt(objBean.getId().toString()));
                        } catch (NullPointerException npe) {
                            databean.setId(0);
                        }
                        try {
                            databean.setBinProfileDes(objBean.getDescription());
                        } catch (NullPointerException npe) {
                            databean.setBinProfileDes("--");
                        }
                        try {
                            databean.setStatus(objBean.getStatus());
                        } catch (NullPointerException npe) {
                            databean.setStatus(0);
                        }
                        try {
                            databean.setDatetime(objBean.getDatetime().toString());
                        } catch (NullPointerException npe) {
                            databean.setDatetime(null);
                        }
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

    public BinProfileBean getPagePath(String page, BinProfileBean inputBean) throws Exception {
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
    public boolean insertBlockBinProfile(BinProfileBean inputBean) throws Exception {

        boolean binAdd = false;
        EpicTleBinProfile epicTleBinProfile = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleBinProfile = new EpicTleBinProfile();

            epicTleBinProfile.setDescription(inputBean.getBinProfileDes());
            epicTleBinProfile.setStatus(1);
            epicTleBinProfile.setDatetime(Util.getLocalDate());
            epicTleBinProfile.setEpicTleBlockBins(null);
            epicTleBinProfile.setEpicTleTerminals(null);

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
                session.flush();
                session.close();
                session = null;
            }
        }
        return binAdd;
    }

    @Override
    public void getUpdateData(BinProfileBean inputBean) throws Exception {

        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            EpicTleBinProfile binProfile = (EpicTleBinProfile) session.get(EpicTleBinProfile.class, inputBean.getUpProfileID());

            if (binProfile != null) {
                inputBean.setUpName(binProfile.getDescription());
                inputBean.setUpStatus(binProfile.getStatus().intValue());
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
    public boolean deleteBin(BinProfileBean inputBean) throws Exception {

        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DELETE EpicTleBinProfile bn WHERE bn.id=:Bin";
            query = session.createQuery(sql);
            query.setInteger("Bin", Integer.parseInt(inputBean.getDbinId()));
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
    public boolean updatebin(BinProfileBean inputBean) throws Exception {

        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        EpicTleBinProfile tleWebUser = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleBinProfile wu where wu.id =:ID";
            query = session.createQuery(sql);
            query.setString("ID", inputBean.getUpProfileID() + "");
            tleWebUser = (EpicTleBinProfile) query.uniqueResult();
            if (tleWebUser != null) {
                tleWebUser.setDescription(inputBean.getUpName());
                if (inputBean.getUpStatus() == 1) {
                    tleWebUser.setStatus(1);
                } else if (inputBean.getUpStatus() == 2) {
                    tleWebUser.setStatus(2);
                }
                tleWebUser.setDatetime(tleWebUser.getDatetime());

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
    public String GetResult(BinProfileBean inputBean) throws Exception {

        Session session = null;
        String sqlCount = "";
        String BlockBin = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleBinProfile wu where wu.description=:name";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("name", inputBean.getBinProfileDes());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleBinProfile objBean = (EpicTleBinProfile) it.next();
                BlockBin = objBean.getDescription();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return BlockBin;

    }

}
