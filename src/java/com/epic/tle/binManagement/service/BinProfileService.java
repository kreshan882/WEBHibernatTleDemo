/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.mapping.EpicTleBinProfile;
import com.epic.tle.mapping.EpicTleBlockBin;
import com.epic.tle.mapping.EpicTleBlockBinId;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author thilina_t
 */
public class BinProfileService implements BinProfileInf {

    @Override
    public List<BinBean> loadBin(BinBean inputBean, int max, int first, String orderBy) throws Exception {

        List<BinBean> dataList = new ArrayList<BinBean>();

        Session session = null;
        try {
            if (orderBy.equals("") || orderBy == null) {
                orderBy = "order by bb.datetime desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sqlCount = "";
            String sqlSearch = "";
            Query querySearch = null;
            Query queryCount = null;

            if (inputBean.getSearchName().equals("")) {

                sqlCount = "select count(bb) from EpicTleBlockBin bb where bb.epicTleBinProfile.id=:profile " + orderBy;
                queryCount = session.createQuery(sqlCount).setInteger("profile", inputBean.getId());

                sqlSearch = "from EpicTleBlockBin bb where bb.epicTleBinProfile.id=:profile " + orderBy;
                querySearch = session.createQuery(sqlSearch).setInteger("profile", inputBean.getId());;

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {
                    BinBean databean = new BinBean();
                    EpicTleBlockBin objBean = (EpicTleBlockBin) it.next();
                    try {
                        databean.setId(objBean.getId().getProfileId());
                    } catch (NullPointerException npe) {
                        databean.setId(0);
                    }
                    try {
                        databean.setLowValue(objBean.getId().getLow_value());
                    } catch (NullPointerException npe) {
                        databean.setLowValue(0);
                    }
                    try {
                        databean.setUpperValue(objBean.getId().getUpper_value());
                    } catch (NullPointerException npe) {
                        databean.setUpperValue(0);
                    }
                    try {
                        databean.setDate(objBean.getDatetime());
                    } catch (NullPointerException npe) {
                        databean.setDate(null);
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
    public boolean insertBin(BinBean inputBean) throws Exception {

        boolean binAdd = false;
        EpicTleBlockBin epicTleBin = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleBin = new EpicTleBlockBin();

            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int binId = (int) httpSession.getAttribute("BlockBin");

            if (binId != 0) {

                Criteria criteria = session.createCriteria(EpicTleBinProfile.class);
                criteria.add(Restrictions.eq("id", binId));
                EpicTleBinProfile binProfileID = (EpicTleBinProfile) criteria.uniqueResult();

                if (binProfileID != null) {

                    epicTleBin.setId(new EpicTleBlockBinId(binProfileID.getId(), inputBean.getLowValue(), inputBean.getUpperValue()));
                    epicTleBin.setEpicTleBinProfile(binProfileID);
                    epicTleBin.setDatetime(Util.getLocalDate());

                    session.beginTransaction();
                    session.save(epicTleBin);
                    session.getTransaction().commit();

                    binAdd = true;
                }

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
        return binAdd;
    }

    @Override
    public boolean deleteBin(BinBean inputBean) throws Exception {

        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String sql = "DELETE EpicTleBlockBin bb where bb.epicTleBinProfile.id=:profile and bb.id.low_value=:low_value and bb.id.upper_value=:upper_value";
            query = session.createQuery(sql);
            query.setInteger("profile", inputBean.getId());
            query.setInteger("low_value", inputBean.getLowValue());
            query.setInteger("upper_value", inputBean.getUpperValue());

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
    public String GetResult(BinBean inputBean) throws Exception {

        Session session = null;
        String sqlCount = "";
        String BlockBin = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleBlockBin bb where bb.id.bin=:name";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("name", inputBean.getBinName());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
//                ChannelManageInputBean databean = new ChannelManageInputBean();
                EpicTleBlockBin objBean = (EpicTleBlockBin) it.next();
                BlockBin = objBean.getEpicTleBinProfile().getDescription();
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
        return BlockBin;

    }

    @Override
    public boolean checkRange(BinBean inputBean) throws Exception {
        boolean value = false;
        Session session = null;
        try {
            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int binId = (int) httpSession.getAttribute("BlockBin");
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            Iterator iterator = session.createQuery("from EpicTleBlockBin bb where bb.id.profileId = :pid and bb.id.low_value = :low_value and bb.id.upper_value = :upper_value")
                    .setInteger("pid", binId)
                    .setInteger("low_value", inputBean.getLowValue())
                    .setInteger("upper_value", inputBean.getUpperValue())
                    .iterate();
            while (iterator.hasNext()) {
                value = true;
                break;
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
                    session.getTransaction().commit();
                    session.clear();
                    session.close();
                    session = null;
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return value;
    }

}
