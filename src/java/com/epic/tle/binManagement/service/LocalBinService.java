/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.LocalBinBean;
import com.epic.tle.mapping.EpicTleLocalbin;
import com.epic.tle.mapping.EpicTleLocalbinPK;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilina_t
 */
public class LocalBinService implements LocalBinInf {

    @Override
    public List<LocalBinBean> loadLocalBin(LocalBinBean inputBean, int max, int first, String orderBy) throws Exception {

        List<LocalBinBean> dataList = new ArrayList<LocalBinBean>();

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

            if (inputBean.getSearchLocalBIN().equals("")) {
                sqlCount = "select count(wu.epicTleLocalbinPK.bin) from EpicTleLocalbin wu where wu.epicTleLocalbinPK.categoryCode = :categoryCode " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setParameter("categoryCode", inputBean.getCategory_code());

                sqlSearch = "from EpicTleLocalbin wu where wu.epicTleLocalbinPK.categoryCode = :categoryCode " + orderBy;
                querySearch = session.createQuery(sqlSearch);
                querySearch.setParameter("categoryCode", inputBean.getCategory_code());
            } else {

                sqlCount = "select count(wu.epicTleLocalbinPK.bin) from EpicTleLocalbin wu where wu.epicTleLocalbinPK.categoryCode = :categoryCode and wu.epicTleLocalbinPK.bin LIKE :des " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", "%" + inputBean.getSearchLocalBIN() + "%");
                queryCount.setParameter("categoryCode", inputBean.getCategory_code());

                sqlSearch = "from EpicTleLocalbin wu where wu.epicTleLocalbinPK.categoryCode = :categoryCode and wu.epicTleLocalbinPK.bin LIKE :des " + orderBy;
                querySearch = session.createQuery(sqlSearch);
                querySearch.setString("des", "%" + inputBean.getSearchLocalBIN() + "%");
                querySearch.setParameter("categoryCode", inputBean.getCategory_code());

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {
                    LocalBinBean databean = new LocalBinBean();
                    EpicTleLocalbin objBean = (EpicTleLocalbin) it.next();

                    try {

                        databean.setCategory_code(objBean.getEpicTleLocalbinPK().getCategoryCode());
                    } catch (NullPointerException npe) {
                        databean.setCategory_code(0);
                    }
                    try {

                        databean.setBin(objBean.getEpicTleLocalbinPK().getBin());
                    } catch (NullPointerException npe) {
                        databean.setBin("--");
                    }
                    try {

                        databean.setCategory(objBean.getEpicTleLocalbinPK().getCategoryCode()== 1 ? "Bank Bin" : "System Block Bin");
                    } catch (NullPointerException npe) {
                        databean.setBin("--");
                    }
                    try {
                        databean.setDatetime(objBean.getTimestamp().toString());
                    } catch (NullPointerException npe) {
                        databean.setDatetime(null);
                    }
                    databean.setFullCount(count);
                    dataList.add(databean);
                }
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
    public boolean insertLocalBin(LocalBinBean inputBean) throws Exception {
        boolean binAdd = false;
        EpicTleLocalbin epicTleLocalbin = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleLocalbin = new EpicTleLocalbin();
            EpicTleLocalbinPK pK = new EpicTleLocalbinPK();
            pK.setBin(inputBean.getBin());
            pK.setCategoryCode(inputBean.getCategory_code());
            epicTleLocalbin.setEpicTleLocalbinPK(pK);
//            epicTleLocalbin.setCategoryCode(inputBean.getCategory_code());
            epicTleLocalbin.setTimestamp(Util.getLocalDate());

            session.beginTransaction();
            session.save(epicTleLocalbin);
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
    public boolean deleteLocalBin(LocalBinBean inputBean) throws Exception {

        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "DELETE EpicTleLocalbin bn WHERE bn.epicTleLocalbinPK.bin=:Bin and bn.epicTleLocalbinPK.categoryCode= :categoryCode";
            query = session.createQuery(sql);
            query.setInteger("Bin", Integer.parseInt(inputBean.getBin()));
            query.setParameter("categoryCode", inputBean.getCategory_code());
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
    public LocalBinBean getPagePath(String page, LocalBinBean inputBean) throws Exception {
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
    public boolean checkBin(LocalBinBean inputBean) throws Exception {
        Session session = null;
        boolean value = false;
        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            EpicTleLocalbin localbin = (EpicTleLocalbin) session.createCriteria(EpicTleLocalbin.class, "bin")
//                    .createAlias("bin.epicTleLocalbinPK", "pk")
                    .add(Restrictions.eq("bin.epicTleLocalbinPK.bin", inputBean.getBin()))
                    .add(Restrictions.eq("bin.epicTleLocalbinPK.categoryCode", inputBean.getCategory_code()))
                    .uniqueResult();
            if (localbin != null) {
                value = true;
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

        return value;
    }

}
