/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.host.bean.ChannelManageDataBean;
import com.epic.tle.host.bean.ChannelManageInputBean;
import com.epic.tle.host.bean.ListenerManageBean;
import com.epic.tle.host.bean.ListenerManageDataBean;
import com.epic.tle.host.bean.ListenerManageInputBean;
import com.epic.tle.mapping.EpicTleChannel;
import com.epic.tle.mapping.EpicTleChannelOperationMethod;
import com.epic.tle.mapping.EpicTleSection;
//import com.epic.tle.mapping.EpicTleMapnii; dan
import com.epic.tle.mapping.EpicTleConnectiontypes;
import com.epic.tle.mapping.EpicTleForwardmethod;
import com.epic.tle.mapping.EpicTleHeaderFormats;
import com.epic.tle.mapping.EpicTleHeaderSize;
import com.epic.tle.mapping.EpicTleIsoFormat;
import com.epic.tle.mapping.EpicTleListeners;
import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.StringTokenizer;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author KRESHAN
 */
public class ListenerManageService implements HostManagementInf {

    public List<ListenerManageDataBean> loadListnerData(ListenerManageInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<ListenerManageDataBean> dataList = new ArrayList<ListenerManageDataBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.listenerName desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
//            String sqlCount = "select count(username) from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
            String sqlCount = "select count(*) from EpicTleListeners wu where wu.epicTleNodetype.code=:node " + orderBy;
            Query queryCount = session.createQuery(sqlCount);
            queryCount.setInteger("node", Configurations.SERVER_NODE);
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

//                String sqlSearch = "from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
                String sqlSearch = "from EpicTleListeners wu where wu.epicTleNodetype.code=:node " + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setInteger("node", Configurations.SERVER_NODE);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    ListenerManageDataBean databean = new ListenerManageDataBean();
                    EpicTleListeners objBean = (EpicTleListeners) it.next();

                    databean.setListName(objBean.getListenerName());
                    databean.setListStatus(Integer.toString(objBean.getEpicTleStatusByStatus().getCode()));

                    databean.setListKeepLive(objBean.getEpicTleStatusByListenerKeepAliveStatus().getDescription());
                    databean.setListConnectType(objBean.getEpicTleConnectiontypes().getDescription());

                    if (objBean.getEpicTleConnectiontypes().getCode() == 1) {
                        databean.setListBindStatus((objBean.getEpicTleStatusByBindStatus() != null) ? Integer.toString(objBean.getEpicTleStatusByBindStatus().getCode()) : "");
                    } else {
                        databean.setListBindStatus("3");
                    }

                    databean.setListPort(Integer.toString(objBean.getListenerPort().intValue()));
                    databean.setListHeadSize(objBean.getEpicTleHeaderSize().getDescription());
                    databean.setListHeaderFormat(objBean.getEpicTleHeaderFormats().getDescription());
                    databean.setListId(objBean.getListenerId().intValue());
                    databean.setListTimeOut(objBean.getListenerTimeout().toString());
                    databean.setTpduStatus(objBean.getEpicTleStatusByTpduStatus().getDescription());
                    databean.setIsoFile(objBean.getIsoFile());

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

    public void getMapmapnii(ListenerManageBean inputBean) throws Exception {

    }

    public void findListener(ListenerManageInputBean inputBean) throws Exception {

        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = " from EpicTleListeners li where li.listenerId =:listenerId and li.epicTleNodetype.code=:node";
            query = session.createQuery(sql);
            query.setString("listenerId", Integer.toString(inputBean.getUplistId()));
            query.setInteger("node", Configurations.SERVER_NODE);
            List l = query.list();
            Iterator it = l.iterator();
            if (it.hasNext()) {
                EpicTleListeners rows = (EpicTleListeners) it.next();
                inputBean.setUplistName(rows.getListenerName());
                inputBean.setUplistTimeOut(rows.getListenerTimeout().toString());
                inputBean.setUplistProt(rows.getListenerPort().toString());
                inputBean.setUplistId(rows.getListenerId().intValue());
                inputBean.setUplistConnectType("" + rows.getEpicTleConnectiontypes().getCode());
                inputBean.setUplistKeepLive("" + rows.getEpicTleStatusByListenerKeepAliveStatus().getCode());
                inputBean.setUplistBindStatus("" + rows.getEpicTleStatusByBindStatus().getCode());
                inputBean.setUplistStatus("" + rows.getEpicTleStatusByStatus().getCode());
                inputBean.setUplistHeadSize("" + rows.getEpicTleHeaderSize().getCode());
                inputBean.setUplistHeaderFormat("" + rows.getEpicTleHeaderFormats().getCode());
                inputBean.setUptpduStatus("" + rows.getEpicTleStatusByTpduStatus().getCode());
                inputBean.setUpisofile("" + rows.getIsoFile());
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
    }

    public boolean checkNii(String anii) throws Exception {
        boolean isniiExists = false;
        List niilist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleFoundniistatus fs"
                    + "  where fs.nii =:nii";
            query = session.createQuery(sql);
            query.setString("nii", anii);
            niilist = query.list();
            if (0 < niilist.size()) {
                isniiExists = true;
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
        return isniiExists;

    }

    public boolean checkMapNii(String amapnii) throws Exception {
        boolean ismapniiExists = false;
//        List<EpicTleMapnii> mapniilist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleMapnii fs"
                    + "  where fs.nii =:amapnii";
            query = session.createQuery(sql);
            query.setString("amapnii", amapnii);
//            mapniilist = query.list();
//            if (0 < mapniilist.size()) {
//               ismapniiExists = true;
//            }
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception e) {
                throw e;
            }
        }
        return ismapniiExists;

    }

    public boolean insertListener(ListenerManageInputBean bean) throws Exception {
        boolean isAddnii = false;
        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();

            EpicTleListeners listener = new EpicTleListeners();
            listener.setListenerName(bean.getListName());
            listener.setListenerPort(Integer.parseInt(bean.getListPort()));
            listener.setListenerTimeout(Integer.parseInt(bean.getListTimeOut()));
            listener.setIsoFile(bean.getIsofile());
            listener.setDatetime(Util.getLocalDate());
            EpicTleConnectiontypes types = new EpicTleConnectiontypes();
            types.setCode(Integer.parseInt(bean.getListConnectType()));
            listener.setEpicTleConnectiontypes(types);

            EpicTleHeaderFormats format = new EpicTleHeaderFormats();
            format.setCode(Integer.parseInt(bean.getListHeaderFormat()));
            listener.setEpicTleHeaderFormats(format);

            EpicTleHeaderSize size = new EpicTleHeaderSize();
            size.setCode(Integer.parseInt(bean.getListHeadSize()));
            listener.setEpicTleHeaderSize(size);

            EpicTleStatus status = new EpicTleStatus();
            status.setCode(Integer.parseInt(bean.getListStatus()));
            listener.setEpicTleStatusByStatus(status);

            EpicTleStatus keep = new EpicTleStatus();
            keep.setCode(Integer.parseInt(bean.getListKeepLive()));
            listener.setEpicTleStatusByListenerKeepAliveStatus(keep);
//
//            EpicTleStatus bind = new EpicTleStatus();
//            bind.setCode(Integer.parseInt("2"));
//            listener.setEpicTleStatusByBindStatus(bind);

            EpicTleStatus tpdu = new EpicTleStatus();
            tpdu.setCode(Integer.parseInt(bean.getTpduStatus()));
            listener.setEpicTleStatusByTpduStatus(tpdu);

            EpicTleIsoFormat isoformat = (EpicTleIsoFormat) session.load(EpicTleIsoFormat.class, Integer.parseInt(bean.getIsofile()));
            listener.setIsoFile(isoformat.getEpicTleIsoFormat());

            EpicTleNodetype nodeType = (EpicTleNodetype) session.load(EpicTleNodetype.class, Configurations.SERVER_NODE);
            listener.setEpicTleNodetype(nodeType);

            session.beginTransaction();
            session.save(listener);
            session.getTransaction().commit();
            isAddnii = true;
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
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

        return isAddnii;

    }

    public boolean insertmapnii(ListenerManageBean inputBean) throws Exception {
        boolean isAddmapnii = false;

        Session session = null;

        try {
            session = HibernateInit.sessionFactory.openSession();

            // EpicTleMapnii mapnii = new EpicTleMapnii();
            //
            session.getTransaction().commit();
            isAddmapnii = true;
        } catch (Exception e) {
            throw e;
        } finally {
            try {
                session.flush();
                session.close();
            } catch (Exception ex) {
                throw ex;
            }
        }
        return isAddmapnii;
    }

    public boolean updateListener(ListenerManageInputBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            List<EpicTleListeners> epicTleListener = null;
            String sql = "from EpicTleListeners ch where ch.listenerId =:listenerId";
            query = session.createQuery(sql);
            query.setString("listenerId", Integer.toString(inputBean.getUplistId()));
            epicTleListener = query.list();
            if (epicTleListener.size() > 0) {

                epicTleListener.get(0).setListenerName(inputBean.getUplistName());
                epicTleListener.get(0).setListenerPort(Integer.parseInt(inputBean.getUplistProt()));
                epicTleListener.get(0).setListenerTimeout(Integer.parseInt(inputBean.getUplistTimeOut()));

                EpicTleStatus status = new EpicTleStatus();
                status.setCode(Integer.parseInt(inputBean.getUplistStatus()));
                epicTleListener.get(0).setEpicTleStatusByStatus(status);

                EpicTleStatus keeplive = new EpicTleStatus();
                keeplive.setCode(Integer.parseInt(inputBean.getUplistKeepLive()));
                epicTleListener.get(0).setEpicTleStatusByListenerKeepAliveStatus(keeplive);

//                EpicTleStatus binding = new EpicTleStatus();
//                binding.setCode(Integer.parseInt(inputBean.getUplistBindStatus()));
//                epicTleListener.get(0).setEpicTleStatusByBindStatus(binding);
                EpicTleStatus tpduStatus = new EpicTleStatus();
                tpduStatus.setCode(Integer.parseInt(inputBean.getUptpduStatus()));
                epicTleListener.get(0).setEpicTleStatusByTpduStatus(tpduStatus);

                EpicTleConnectiontypes types = new EpicTleConnectiontypes();
                types.setCode(Integer.parseInt(inputBean.getUplistConnectType()));
                epicTleListener.get(0).setEpicTleConnectiontypes(types);

                EpicTleHeaderFormats format = new EpicTleHeaderFormats();
                format.setCode(Integer.parseInt(inputBean.getUplistHeaderFormat()));
                epicTleListener.get(0).setEpicTleHeaderFormats(format);

                EpicTleHeaderSize size = new EpicTleHeaderSize();
                size.setCode(Integer.parseInt(inputBean.getUplistHeadSize()));
                epicTleListener.get(0).setEpicTleHeaderSize(size);

                EpicTleIsoFormat isoformat = (EpicTleIsoFormat) session.load(EpicTleIsoFormat.class, Integer.parseInt(inputBean.getUpisofile()));
                epicTleListener.get(0).setIsoFile(isoformat.getEpicTleIsoFormat());

                EpicTleNodetype nodeType = (EpicTleNodetype) session.load(EpicTleNodetype.class, Configurations.SERVER_NODE);
                epicTleListener.get(0).setEpicTleNodetype(nodeType);

                session.save(epicTleListener.get(0));
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
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
        return isUpdated;
    }

    public boolean deleteListener(ListenerManageInputBean inputBean) throws Exception {
        boolean isDeletd = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();

            session.beginTransaction();
            String sql2 = "delete EpicTleListeners fs where fs.listenerId =:listenerId";
            query = session.createQuery(sql2);
            query.setString("listenerId", Integer.toString(inputBean.getListId()));
            int resultmap = query.executeUpdate();
            if (1 == resultmap) {
                isDeletd = true;
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
        return isDeletd;
    }

    @Override
    public List<ChannelManageDataBean> loadChannelData(ChannelManageInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findNII(ChannelManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    //@Override
    public boolean insertnii(ChannelManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateNII(ChannelManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteNII(ChannelManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChannelManageInputBean getPagePath(String page, ChannelManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ListenerManageInputBean getPagePath(String page, ListenerManageInputBean inputBean) throws Exception {
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
        }
        return inputBean;
    }

    @Override
    public Map<Integer, String> getConnecType() throws Exception {
        Map<Integer, String> ConnecTypeMap = new HashMap<Integer, String>();
        List<EpicTleConnectiontypes> list = getMasterValues(0, 2, "EpicTleConnectiontypes");
        for (EpicTleConnectiontypes list1 : list) {
            ConnecTypeMap.put(list1.getCode(), list1.getDescription());
        }
        return ConnecTypeMap;
    }

    @Override
    public Map<Integer, String> getForwardMethod() throws Exception {
        Map<Integer, String> ForwardMethodMap = new HashMap<Integer, String>();
        List<EpicTleForwardmethod> list = getMasterValues(0, 2, "EpicTleForwardmethod");
        for (EpicTleForwardmethod list1 : list) {
            ForwardMethodMap.put(list1.getCode(), list1.getDescription());
        }
        return ForwardMethodMap;
    }

    @Override
    public Map<Integer, String> getHeaderFormat() throws Exception {
        Map<Integer, String> HeaderFormatMap = new HashMap<Integer, String>();
        List<EpicTleHeaderFormats> list = getMasterValues(0, 2, "EpicTleHeaderFormats");
        for (EpicTleHeaderFormats list1 : list) {
            HeaderFormatMap.put(list1.getCode(), list1.getDescription());
        }
        return HeaderFormatMap;
    }

    @Override
    public Map<Integer, String> getHeaderSize() throws Exception {
        Map<Integer, String> HeaderSizeMap = new HashMap<Integer, String>();
        List<EpicTleHeaderSize> list = getMasterValues(0, 2, "EpicTleHeaderSize");
        for (EpicTleHeaderSize list1 : list) {
            HeaderSizeMap.put(list1.getCode(), list1.getDescription());
        }
        return HeaderSizeMap;
    }

    @Override
    public Map<Integer, String> getOperMethod() throws Exception {
        Map<Integer, String> OperMethodMap = new HashMap<Integer, String>();
        List<EpicTleChannelOperationMethod> list = getMasterValues(0, 2, "EpicTleChannelOperationMethod");
        for (EpicTleChannelOperationMethod list1 : list) {
            OperMethodMap.put(list1.getCode(), list1.getDescription());
        }
        return OperMethodMap;
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
        Session session = null;
        List result = new ArrayList();
        try {
            session = HibernateInit.sessionFactory.openSession();
            Query query = session.createQuery("from " + table + " o order by o.code asc");
            query.setFirstResult(from);
            query.setMaxResults(to);
            result = query.list();

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
        return result;
    }

    @Override
    public Map<Integer, String> getIsoFormat() throws Exception {
        Map<Integer, String> HeaderSizeMap = new HashMap<Integer, String>();
        List<EpicTleIsoFormat> list = getMasterValues(0, 10, "EpicTleIsoFormat");
        for (EpicTleIsoFormat list1 : list) {
            HeaderSizeMap.put(list1.getCode(), list1.getEpicTleIsoFormat());
        }
        return HeaderSizeMap;
    }

    public String GetResult(ListenerManageInputBean inputBean, String field) throws Exception {
        Session session = null;
        String sqlCount = "";
        String listenerPort = null;
        Query queryCount = null;
        Query querySearch = null;
        try {
            long count = 0;
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            if (field.equals("name")) {

                sqlCount = "from EpicTleListeners wu where wu.listenerName =:name and wu.epicTleNodetype.code =:node";
                queryCount = session.createQuery(sqlCount);
                if (inputBean.getListName() != null) {
                    queryCount.setString("name", inputBean.getListName());
                    queryCount.setInteger("node", Configurations.SERVER_NODE);
                } else if (inputBean.getUplistName() != null) {
                    queryCount.setString("name", inputBean.getUplistName());
                    queryCount.setInteger("node", Configurations.SERVER_NODE);
                }

            } else if (field.equals("port")) {

                sqlCount = "from EpicTleListeners wu where wu.listenerPort =:port and wu.epicTleNodetype.code =:node";
                queryCount = session.createQuery(sqlCount);
                if (inputBean.getListName() != null && !inputBean.getListPort().isEmpty()) {
                    queryCount.setInteger("port", Integer.parseInt(inputBean.getListPort()));
                    queryCount.setInteger("node", Configurations.SERVER_NODE);
                } else if (inputBean.getUplistName() != null && inputBean.getUplistProt() != "") {
                    queryCount.setInteger("port", Integer.parseInt(inputBean.getUplistProt()));
                    queryCount.setInteger("node", Configurations.SERVER_NODE);
                }

            }

//            sqlCount = "from EpicTleListeners wu where wu.listenerName =:name or wu.listenerPort =:port and wu.epicTleNodetype.code =:node";
//            queryCount = session.createQuery(sqlCount);
//            if (inputBean.getListName() != null) {
//                queryCount.setString("name", inputBean.getListName());
//                queryCount.setInteger("port", Integer.parseInt(inputBean.getListPort()));
//                queryCount.setInteger("node", Configurations.SERVER_NODE);
//            } else if (inputBean.getUplistName() != null) {
//                queryCount.setString("name", inputBean.getUplistName());
//                queryCount.setInteger("port", Integer.parseInt(inputBean.getUplistProt()));
//                queryCount.setInteger("node", Configurations.SERVER_NODE);
//            }
            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
//                ChannelManageInputBean databean = new ChannelManageInputBean();
                EpicTleListeners objBean = (EpicTleListeners) it.next();
                listenerPort = objBean.getListenerPort().toString();
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
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return listenerPort;
    }

    @Override
    public String GetResult(ChannelManageInputBean inputBean, String field) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
