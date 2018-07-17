/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.host.bean.NIIConfigBean;
import com.epic.tle.mapping.EpicTleChannel;
import com.epic.tle.mapping.EpicTleNii;
import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public class NIIConfigService implements RegisterNIIConfigInf {

    @Override
    public List<NIIConfigBean> loadNII(NIIConfigBean inputBean, int max, int first, String orderBy) throws Exception {
        List<NIIConfigBean> dataList = new ArrayList<NIIConfigBean>();
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

            if (inputBean.getChannelID() == 0) {
                sqlCount = "select count(id) from EpicTleNii wu " + orderBy;
                queryCount = session.createQuery(sqlCount);

                sqlSearch = "from EpicTleNii wu " + orderBy;
                querySearch = session.createQuery(sqlSearch);
            } else {

                sqlCount = "select count(id) from EpicTleNii wu where wu.channelid =:des and wu.epicTleNodetype.code=:node " + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", inputBean.getChannelID() + "");
                queryCount.setInteger("node", Configurations.SERVER_NODE);

                sqlSearch = "from EpicTleNii wu where wu.channelid =:des and wu.epicTleNodetype.code=:node " + orderBy;
                querySearch = session.createQuery(sqlSearch);
                querySearch.setString("des", inputBean.getChannelID() + "");
                querySearch.setInteger("node", Configurations.SERVER_NODE);

            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {
                    NIIConfigBean databean = new NIIConfigBean();
                    EpicTleNii objBean = (EpicTleNii) it.next();
                    try {
                        databean.setId(objBean.getId());
                    } catch (NullPointerException npe) {
                        databean.setId(0);
                    }
                    try {
                        databean.setNii(objBean.getNii());
                    } catch (NullPointerException npe) {
                        databean.setNii("--");
                    }
                    try {
                        databean.setMapNii(objBean.getMapNii());
                    } catch (NullPointerException npe) {
                        databean.setMapNii("--");
                    }

                    try {
                        databean.setDatetime(objBean.getDatetime());
                    } catch (NullPointerException npe) {
                        databean.setDatetime(null);
                    }
                    try {
                        databean.setTlestatus(objBean.getTlestatus());
                    } catch (NullPointerException npe) {
                        databean.setDatetime(null);
                    }

                    databean.setFullCount(count);
                    dataList.add(databean);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                session.flush();
                session.clear();
                session.getTransaction().commit();  
                session.close();                
                   
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            }
        }
        

        return dataList;
    }

    @Override
    public boolean insertNIIGroup(NIIConfigBean inputBean) throws Exception {

        boolean binAdd = false;
        EpicTleNii epicTleNii = null;
        Session session = null;
        String sqlStatus = "";
        Query queryStatus;
        String sqlChanel = "";
        Query queryChanel;

        HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
        int channlId = (int) httpSession.getAttribute("channel");

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleNii = new EpicTleNii();

            epicTleNii.setChannelid(channlId);
            epicTleNii.setNii(inputBean.getNii());
            epicTleNii.setMapNii(inputBean.getMapNii());
            epicTleNii.setTlestatus(inputBean.getTlestatus());
            epicTleNii.setDatetime(Util.getLocalDate());
            EpicTleNodetype nodeType = (EpicTleNodetype) session.load(EpicTleNodetype.class, Configurations.SERVER_NODE);
            epicTleNii.setEpicTleNodetype(nodeType);

            session.getTransaction().begin();
            session.save(epicTleNii);
            session.getTransaction().commit();

            binAdd = true;

        } catch (Exception e) {
            e.printStackTrace();  
            session.getTransaction().rollback();
            throw e;
        }
        finally
        {
        if (session != null) {
                session.close();
                session = null;
            }
        }
        return binAdd;
    }

    @Override
    public void getUpdateData(NIIConfigBean inputBean) throws Exception {

    }

    @Override
    public boolean deleteNII(NIIConfigBean inputBean) throws Exception {
        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String sql = "DELETE EpicTleNii bn WHERE bn.id =:niiId";
            query = session.createQuery(sql);
            query.setInteger("niiId", inputBean.getId());
            int result = query.executeUpdate();

            if (1 == result) {
                isFeDeleted = true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                session.flush();
                session.close();//roll back
            } catch (Exception e) {
                throw e;
            }
        }
        return isFeDeleted;

    }

    @Override
    public boolean updateNII(NIIConfigBean inputBean) throws Exception {

        boolean isUpdated = false;
//        Session session = null;
//        Query query = null;
//        EpicTleNiigroup tleNiiGroup = null;
//        try {
//            session = HibernateInit.sessionFactory.openSession();
//            session.beginTransaction();
//            String sql = "from EpicTleNiigroup wu where wu.id =:ID";
//            query = session.createQuery(sql);
//            query.setString("ID", inputBean.getUpid() + "");
//            tleNiiGroup = (EpicTleNiigroup) query.uniqueResult();
//            if (tleNiiGroup != null) {
//
//                session.get(EpicTleNiigroup.class, inputBean.getUpid());
//
//                tleNiiGroup.setGroupName(inputBean.getUpniiConfName());
//                tleNiiGroup.setDescription(inputBean.getUpniiConfDes());
//                EpicTleChannel channel = (EpicTleChannel) session.load(EpicTleChannel.class, tleNiiGroup.getEpicTleChannel().getChannelId());
//                if(channel!=null){
//                channel.setAssignStatus(0);
//                session.save(channel);
//                }
//                tleNiiGroup.setEpicTleChannel((EpicTleChannel) session.get(EpicTleChannel.class, inputBean.getUpChannel()));
//                
//                EpicTleChannel upchannel = (EpicTleChannel) session.load(EpicTleChannel.class, inputBean.getUpChannel());
//                upchannel.setAssignStatus(1);
//                session.save(upchannel);
//                tleNiiGroup.setEpicTleStatus((EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getUpStatus()));
//                tleNiiGroup.setDatetime(tleNiiGroup.getDatetime());
//                
//                session.update(tleNiiGroup);
//                session.getTransaction().commit();
//                isUpdated = true;
//            }
//        } catch (Exception e) {
//            throw e;
//        } finally {
//            try {
//                session.flush();
//                session.close();//roll back
//            } catch (Exception e) {
//                throw e;
//            }
//        }
        return isUpdated;
    }

    @Override
    public NIIConfigBean getPagePath(String page, NIIConfigBean inputBean) throws Exception {
        String module = (page!=null)?page.substring(0, 2):"";
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

        return inputBean;
    }

    @Override
    public Map<Integer, String> getStatusValues(int from, int to) {
        Session session = null;
        List result = new ArrayList();
        Map<Integer, String> status = new HashMap<Integer, String>();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Query query = session.createQuery("from EpicTleStatus o order by o.code asc");
            query.setFirstResult(from);
            query.setMaxResults(to);
            result = query.list();
            for (EpicTleStatus stat : (List<EpicTleStatus>) result) {
                status.put(stat.getCode(), stat.getDescription());
            }
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
        return status;
    }

    @Override
    public List getMasterValues(int from, int to, String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public String GetResult(NIIConfigBean inputBean) throws Exception {
        Session session = null;
        String sqlCount = "";
        String channelName = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleNii wu where (wu.nii =:nii or wu.mapNii =:map) and wu.epicTleNodetype.code =:node";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("nii", inputBean.getNii() + "");
            queryCount.setString("map", inputBean.getMapNii() + "");
            queryCount.setInteger("node", Configurations.SERVER_NODE);

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
//                NIIConfigBean databean = new NIIConfigBean();
                EpicTleNii objBean = (EpicTleNii) it.next();
                EpicTleChannel channel = (EpicTleChannel) session.load(EpicTleChannel.class, objBean.getChannelid());
                channelName = channel.getChannelName();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        finally{
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return channelName;

    }

}
