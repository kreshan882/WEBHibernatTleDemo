/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.host.bean.NIIBean;
import com.epic.tle.mapping.EpicTleNii;
//import com.epic.tle.mapping.EpicTleNiiId;
//import com.epic.tle.mapping.EpicTleNiigroup;
import com.epic.tle.util.HibernateInit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author thilina_t
 */
public class NiiService implements NiiInf {

    @Override
    public List<NIIBean> loadNii(NIIBean inputBean, int max, int first, String orderBy) throws Exception {

        List<NIIBean> dataList = new ArrayList<NIIBean>();
//
//        Session session = null;
//
//        try {
//            if (orderBy.equals("") || orderBy == null) {
//                orderBy = "order by bb.id.nii desc ";
//            }
//
//            long count = 0;
//
//            session = HibernateInit.sessionFactory.openSession();
//            String sqlCount = "";
//            String sqlSearch = "";
//            Query querySearch = null;
//            Query queryCount = null;
//
//            if (inputBean.getNiiId() != 0) {
//
//                sqlCount = "select count(epicTleNiigroup) from EpicTleNii bb where bb.epicTleNiigroup.groupId=:NiiId "+orderBy;
//                queryCount = session.createQuery(sqlCount).setInteger("NiiId", inputBean.getNiiId());
//
//                sqlSearch = "from EpicTleNii bb where bb.epicTleNiigroup.groupId=:NiiId "+orderBy;
//                querySearch = session.createQuery(sqlSearch).setInteger("NiiId", inputBean.getNiiId());;
//
//            }
//            Iterator itCount = queryCount.iterate();
//            count = (Long) itCount.next();
//
//            if (count > 0) {
//
//                querySearch.setMaxResults(max);
//                querySearch.setFirstResult(first);
//
//                Iterator it = querySearch.iterate();
//
//                while (it.hasNext()) {
//                    NIIBean databean = new NIIBean();
//                    EpicTleNii objBean = (EpicTleNii) it.next();
//                    try {
//                        databean.setGroup(objBean.getId().getGroupId());
//                    } catch (NullPointerException npe) {
//                        databean.setGroup(0);
//                    }
//                    try {
//                        databean.setNiiName(objBean.getId().getNii());
//                    } catch (NullPointerException npe) {
//                        databean.setNiiName("");
//                    }
//                    try {
//                        databean.setMapNii(objBean.getId().getMapNii());
//                    } catch (NullPointerException npe) {
//                        databean.setMapNii("");
//                    }
//
//                    databean.setFullCount(count);
//                    dataList.add(databean);
//
//                }
//            }
//        } catch (Exception e) {
//            if (session != null) {
//                session.flush();
//                session.close();
//            }
//            throw e;
//        } finally {
//            if (session != null) {
//                session.flush();
//                session.close();
//            }
//        }

        return dataList;

    }

    @Override
    public boolean insertNii(NIIBean inputBean) throws Exception {

        boolean binAdd = false;
//        EpicTleNii epicTleNii = null;
//        EpicTleNiiId epicTleNiiId = null;
//        Session session = null;
//
//        try {
//
//            session = HibernateInit.sessionFactory.openSession();
//            epicTleNii = new EpicTleNii();
//            epicTleNiiId = new EpicTleNiiId();
//
//            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
//
//            if (inputBean.getNiiId() != 0) {
//
//                epicTleNiiId.setGroupId(inputBean.getNiiId());
//                epicTleNiiId.setNii(inputBean.getNii());
//                epicTleNiiId.setMapNii(inputBean.getMapNii());
//
//                EpicTleNiigroup niiGroup = (EpicTleNiigroup) session.get(EpicTleNiigroup.class, inputBean.getNiiId());
//
//                epicTleNii.setId(epicTleNiiId);
//                epicTleNii.setEpicTleNiigroup(niiGroup);
//                epicTleNii.setStatus(1);
//
//                session.beginTransaction();
//                session.save(epicTleNii);
//                session.getTransaction().commit();
//
//                binAdd = true;
//
//            }
//
//        } catch (Exception e) {
//            if (session != null) {
//                session.close();
//                session = null;
//            }
//            throw e;
//        } finally {
//            if (session != null) {
//                session.close();
//                session = null;
//            }
//        }
        return binAdd;
    }

    @Override
    public boolean deleteNii(NIIBean inputBean) throws Exception {

        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String sql = "DELETE EpicTleNii bb where bb.id.groupId=:NiiId "
                    + "and bb.id.mapNii=:Niimap "
                    + "and bb.id.nii=:NiiName";

            query = session.createQuery(sql);
            query.setInteger("NiiId", inputBean.getGroup());
            query.setString("NiiName", inputBean.getNiiName());
            query.setString("Niimap", inputBean.getMapNii());

            int result = query.executeUpdate();
            if (1 == result) {
                isFeDeleted = true;
            }
            session.getTransaction().commit();

        } catch (Exception e) {
            if (session != null) {
                session.flush();
                session.close();//roll back
            }
            throw e;
        } finally {
            try {
                if (session != null) {
                    session.flush();
                    session.close();//roll back
                }
            } catch (Exception e) {
                throw e;
            }
        }

        return isFeDeleted;

    }

}
