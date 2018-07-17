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
import com.epic.tle.mapping.EpicTleConnectiontypes;
import com.epic.tle.mapping.EpicTleForwardmethod;
import com.epic.tle.mapping.EpicTleHeaderFormats;
import com.epic.tle.mapping.EpicTleHeaderSize;
import com.epic.tle.mapping.EpicTleIsoFormat;
import com.epic.tle.mapping.EpicTleNii;
import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Configurations;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author kreshan
 */
public class ChannelManageService implements HostManagementInf {

    public List<ChannelManageDataBean> loadChannelData(ChannelManageInputBean inputBean, int max, int first, String orderBy) throws Exception {

        List<ChannelManageDataBean> dataList = new ArrayList<ChannelManageDataBean>();
        Session session = null;
        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.channelName desc ";
            }

            long count = 0;
            System.out.println("Configuration Node: " + Configurations.SERVER_NODE);
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
//            String sqlCount = "select count(username) from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
            String sqlCount = "select count(*) from EpicTleChannel wu where wu.epicTleNodetype.code=:node " + orderBy;
            Query queryCount = session.createQuery(sqlCount);
            queryCount.setInteger("node", Configurations.SERVER_NODE);
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

//                String sqlSearch = "from EpicTleMapnii mn  right join EpicTleFoundniistatus fn on mn.nii =:fn.nii " ;
                String sqlSearch = "from EpicTleChannel wu where wu.epicTleNodetype.code=:node " + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
                querySearch.setInteger("node", Configurations.SERVER_NODE);
                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    ChannelManageDataBean databean = new ChannelManageDataBean();
                    EpicTleChannel objBean = (EpicTleChannel) it.next();

                    try {
                        databean.setChannelId(objBean.getChannelId());
                    } catch (NullPointerException npe) {
                        databean.setGroupName("--");
                    }
                    try {
                        databean.setChannelName(objBean.getChannelName());
//                        databean.setHostName(objBean.getHostname());
                    } catch (NullPointerException npe) {
                        databean.setHostName("--");
                    }
                    try {
                        databean.setIp(objBean.getHostip());
                    } catch (NullPointerException npe) {
                        databean.setIp("--");
                    }
                    try {
                        databean.setPort("" + objBean.getHostport());
                    } catch (NullPointerException npe) {
                        databean.setPort("--");
                    }

                    try {
                        databean.setConnectiontype(inputBean.getAconTypeMap().get(objBean.getConnectedtype()));
                    } catch (NullPointerException npe) {
                        databean.setConnectiontype("--");
                    }
                    try {
                        databean.setStatus("" + objBean.getEpicTleStatusByStatus().getCode());
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setRoutemethod(objBean.getEpicTleForwardmethod().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setRoutemethod("--");
                    }
                    try {
                        databean.setContime(objBean.getConTimeout().toString());
                    } catch (NullPointerException npe) {
                        databean.setContime("--");
                    }
                    try {
                        databean.setReadtime("" + objBean.getReadTimeout());
                    } catch (NullPointerException npe) {
                        databean.setReadtime("--");
                    }
                    try {
                        databean.setIsoFile(objBean.getIsoFile());
                    } catch (NullPointerException npe) {
                        databean.setIsoFile("--");
                    }
                    try {
                        databean.setTPDUStatus("" + objBean.getEpicTleStatusByTpduStatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setTPDUStatus("--");
                    }
                    try {
                        databean.setKeepAlive("" + objBean.getEpicTleStatusByKeepAliveStatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setKeepAlive("--");
                    }
                    try {
                        databean.setLoadBalance(objBean.getEpicTleStatusByLoadbalancestatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setLoadBalance("--");
                    }
                    try {
                        databean.setOperMethod("" + objBean.getEpicTleChannelOperationMethod().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setOperMethod("--");
                    }
                    try {
                        databean.setPinTranslate("" + objBean.getEpicTleStatusByPintranslateStatus().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setPinTranslate("--");
                    }
                    try {
                        databean.setEncryptZpk("" + objBean.getEncryptZpk());
                    } catch (NullPointerException npe) {
                        databean.setEncryptZpk("--");
                    }

                    try {
                        if (objBean.getSecondaryIp().equals(null)) {
                            databean.setSecIp("");
                        } else {
                            databean.setSecIp("" + objBean.getSecondaryIp());
                        }
                    } catch (NullPointerException npe) {
                        databean.setSecIp("--");
                    }
                    try {
                        if (objBean.getSecondaryPort().equals(null)) {
                            databean.setSecPort("");
                        } else {
                            databean.setSecPort("" + objBean.getSecondaryPort());
                        }
                    } catch (NullPointerException npe) {
                        databean.setSecPort("--");
                    }

                    if (objBean.getEpicTleStatusByStatus().getCode() == 2) {
                        try {
                            databean.setSecStatus("2");
                        } catch (NullPointerException npe) {
                            databean.setSecStatus("--");
                        }
                        try {
                            databean.setBindStatus("2");
                        } catch (NullPointerException npe) {
                            databean.setBindStatus("--");
                        }
                    } else {

                        if (objBean.getConnectedtype() == 1) {

                            try {
                                databean.setBindStatus("" + objBean.getEpicTleStatusByBindStatus().getCode());
                            } catch (NullPointerException npe) {
                                databean.setBindStatus("--");
                            }

                            if (objBean.getEpicTleStatusByLoadbalancestatus().getCode() == 1) {
                                try {
                                    databean.setSecStatus(objBean.getEpicTleStatusByBindSecondaryStatus().getCode() + "");
                                } catch (NullPointerException npe) {
                                    databean.setSecStatus("--");
                                }
                            } else {
                                try {
                                    databean.setSecStatus("3");
                                } catch (NullPointerException npe) {
                                    databean.setSecStatus("--");
                                }
                            }

                        } else {
                            try {
                                databean.setBindStatus("3");
                                databean.setSecStatus("3");
                            } catch (NullPointerException npe) {
                                databean.setSecStatus("--");
                            }
                        }

                    }
                    databean.setFullCount(count);

                    dataList.add(databean);
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
                session.clear();
                session.flush();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
        return dataList;
    }

    public void findNII(ChannelManageInputBean inputBean) throws Exception {

        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleChannel cm where cm.channelId =:channelId and cm.epicTleNodetype.code=:node";
            query = session.createQuery(sql);
            query.setInteger("channelId", inputBean.getUpchannelId());
            query.setInteger("node", Configurations.SERVER_NODE);
            List l = query.list();
            Iterator it = l.iterator();
            if (it.hasNext()) {

                EpicTleChannel channel = (EpicTleChannel) it.next();
                inputBean.setUpchannelName(channel.getChannelName());
                inputBean.setUpchannelId(channel.getChannelId());
                inputBean.setUpport(channel.getHostport().toString());
                inputBean.setUpip(channel.getHostip());
                inputBean.setUpchannelName(channel.getChannelName());
                inputBean.setUpconType(channel.getConnectedtype().toString());
                inputBean.setUpstatus(Integer.toString(channel.getEpicTleStatusByStatus().getCode()));
                inputBean.setUpforwerdMathod(Integer.toString(channel.getEpicTleForwardmethod().getCode()));
                inputBean.setUpheaderFormat(channel.getEpicTleHeaderFormats().getCode());
                inputBean.setUpheaderSize(channel.getEpicTleHeaderSize().getCode());
                inputBean.setUpcontime(channel.getConTimeout().toString());
                inputBean.setUpreadtime(channel.getReadTimeout().toString());
                inputBean.setUpKeepAlive(channel.getEpicTleStatusByKeepAliveStatus().getCode());
                inputBean.setUpPinTrans(Integer.toString(channel.getEpicTleStatusByPintranslateStatus().getCode()));

//                EpicTleIsoFormat isoformat = (EpicTleIsoFormat) session.load(EpicTleIsoFormat.class, Integer.parseInt(channel.getIsoFile()));
//                inputBean.setIsoFile(isoformat.getEpicTleIsoFormat());
                inputBean.setUpisoFile(channel.getIsoFile());

                inputBean.setUpenzpk(channel.getEncryptZpk());
                inputBean.setUploadBalance(channel.getEpicTleStatusByLoadbalancestatus().getCode() + "");

                if (channel.getEpicTleChannelOperationMethod() != null) {
                    inputBean.setUpoperMethod(channel.getEpicTleChannelOperationMethod().getCode() + "");
                }
                if (channel.getEpicTleStatusByBindSecondaryStatus() != null) {
                    inputBean.setUpsecStatus(channel.getEpicTleStatusByBindSecondaryStatus().getCode() + "");
                }
                if (channel.getEpicTleStatusByPintranslateStatus() != null) {
                    inputBean.setUpPinTrans(channel.getEpicTleStatusByPintranslateStatus().getCode() + "");
                }

                inputBean.setUpTPDUStatus(channel.getEpicTleStatusByTpduStatus().getCode() + "");

                inputBean.setUpsecIp(channel.getSecondaryIp());
                inputBean.setUpsecPort(channel.getSecondaryPort());

            }
        } catch (Exception e) {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
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

    public boolean insertnii(ChannelManageInputBean inputBean) throws Exception {
        boolean isInsert = false;
        EpicTleChannel epicTleChannel = null;
        Session session = null;

        //Query query=null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            epicTleChannel = new EpicTleChannel();
//            epicTleChannel.setGroupname(inputBean.getAgroupName()); dan
            epicTleChannel.setChannelName(inputBean.getChannelName());
            epicTleChannel.setHostip(inputBean.getAip());
            epicTleChannel.setHostport(Integer.parseInt(inputBean.getAport()));
            epicTleChannel.setConnectedtype(Integer.parseInt(inputBean.getAconType()));
            epicTleChannel.setConTimeout(Integer.parseInt(inputBean.getContime()));
            epicTleChannel.setReadTimeout(Integer.parseInt(inputBean.getReadtime()));
            epicTleChannel.setAssignStatus(Integer.SIZE);

            EpicTleHeaderSize headerSize = new EpicTleHeaderSize();
            EpicTleHeaderFormats headerFormat = new EpicTleHeaderFormats();
            headerSize.setCode(inputBean.getHeaderSize());
            headerFormat.setCode(inputBean.getHeaderFormat());

            epicTleChannel.setEpicTleHeaderSize(headerSize);
            epicTleChannel.setEpicTleHeaderFormats(headerFormat);
            if (inputBean.getPinTrans().equals("1")) {
                epicTleChannel.setEncryptZpk(inputBean.getEcnryptZPK());
            }

            EpicTleIsoFormat isoformat = (EpicTleIsoFormat) session.load(EpicTleIsoFormat.class, Integer.parseInt(inputBean.getIsoFile()));
            epicTleChannel.setIsoFile(isoformat.getEpicTleIsoFormat());
//            epicTleChannel.setIsoFile(inputBean.getIsoFile());

            EpicTleStatus epicTleStatus = new EpicTleStatus();
            epicTleStatus.setCode(Integer.parseInt(inputBean.getAstatus()));
            epicTleChannel.setEpicTleStatusByStatus(epicTleStatus);

            EpicTleStatus epicTleKeepAliveStatus = new EpicTleStatus();
            epicTleKeepAliveStatus.setCode(Integer.parseInt(inputBean.getKeepAlive()));
            epicTleChannel.setEpicTleStatusByKeepAliveStatus(epicTleKeepAliveStatus);

            EpicTleForwardmethod epicTleForwardmethod = new EpicTleForwardmethod();
            epicTleForwardmethod.setCode(Integer.parseInt(inputBean.getAforwerdMathod()));
            epicTleChannel.setEpicTleForwardmethod(epicTleForwardmethod);

            EpicTleStatus loadBalanceStatus = new EpicTleStatus();

            EpicTleStatus PinTransStatus = new EpicTleStatus();
            PinTransStatus.setCode(Integer.parseInt(inputBean.getPinTrans()));
            epicTleChannel.setEpicTleStatusByPintranslateStatus(PinTransStatus);

//            EpicTleStatus TPDUStatus = new EpicTleStatus();
//            TPDUStatus.setCode(Integer.parseInt(inputBean.getTp));
//            epicTleChannel.setEpicTleStatusByPintranslateStatus(PinTransStatus);
            if (inputBean.getAconType().equals("1")) { //Check Connection Type Permanant 

                if (Integer.parseInt(inputBean.getLoadBalance()) == 1) { //Check Load Balance Active

                    epicTleChannel.setSecondaryIp(inputBean.getSip());
                    epicTleChannel.setSecondaryPort(inputBean.getSport());
                    EpicTleChannelOperationMethod epicTleChannelOperationMethod = new EpicTleChannelOperationMethod();
                    epicTleChannelOperationMethod.setCode(Integer.parseInt(inputBean.getOperMethod()));
                    epicTleChannel.setEpicTleChannelOperationMethod(epicTleChannelOperationMethod);
                    loadBalanceStatus.setCode(Integer.parseInt(inputBean.getLoadBalance()));
                    epicTleChannel.setEpicTleStatusByLoadbalancestatus(loadBalanceStatus);

                } else {
                    epicTleChannel.setSecondaryIp(null);
                    epicTleChannel.setSecondaryPort(null);
                    epicTleChannel.setEpicTleChannelOperationMethod(null);
                    loadBalanceStatus.setCode(Integer.parseInt(inputBean.getLoadBalance()));
                    epicTleChannel.setEpicTleStatusByLoadbalancestatus(loadBalanceStatus);
                }

            } else {
                loadBalanceStatus.setCode(2);
                epicTleChannel.setEpicTleStatusByLoadbalancestatus(loadBalanceStatus);
            }
            EpicTleStatus TPDUStatus = new EpicTleStatus();
            TPDUStatus.setCode(Integer.parseInt(inputBean.getTpdu()));

            epicTleChannel.setEpicTleStatusByTpduStatus(TPDUStatus);

            EpicTleStatus SecondaryStatus = new EpicTleStatus();
            SecondaryStatus.setCode(2);
            epicTleChannel.setEpicTleStatusByBindSecondaryStatus(SecondaryStatus);

            EpicTleStatus epicTleBindStatus = new EpicTleStatus();
            epicTleBindStatus.setCode(2);
            epicTleChannel.setEpicTleStatusByBindStatus(epicTleBindStatus);

            EpicTleNodetype nodeType = (EpicTleNodetype) session.load(EpicTleNodetype.class, Configurations.SERVER_NODE);
            epicTleChannel.setEpicTleNodetype(nodeType);

            session.beginTransaction();
            session.save(epicTleChannel);
            session.getTransaction().commit();
            isInsert = true;

        } catch (Exception e) {
            e.printStackTrace();
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

        return isInsert;
    }

    public boolean updateNII(ChannelManageInputBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            List<EpicTleChannel> epicTleChannel = null;
            String sql = "from EpicTleChannel ch where ch.channelId =:channelId";
            query = session.createQuery(sql);
            query.setString("channelId", Integer.toString(inputBean.getUpchannelId()));
            epicTleChannel = query.list();
            if (epicTleChannel.size() > 0) {

                epicTleChannel.get(0).setChannelName(inputBean.getUpchannelName());
                epicTleChannel.get(0).setHostip(inputBean.getUpip());
                epicTleChannel.get(0).setHostport(Integer.parseInt(inputBean.getUpport()));
                epicTleChannel.get(0).setConnectedtype(Integer.parseInt(inputBean.getUpconType()));
                epicTleChannel.get(0).setConTimeout(Integer.parseInt(inputBean.getUpcontime()));
                epicTleChannel.get(0).setReadTimeout(Integer.parseInt(inputBean.getUpreadtime()));

                EpicTleIsoFormat isoformat = (EpicTleIsoFormat) session.load(EpicTleIsoFormat.class, Integer.parseInt(inputBean.getUpisoFile()));
                epicTleChannel.get(0).setIsoFile(isoformat.getEpicTleIsoFormat());

                EpicTleStatus epicTleTPDUStatus = new EpicTleStatus();
                epicTleTPDUStatus.setCode(Integer.parseInt(inputBean.getUpTPDUStatus()));
                epicTleChannel.get(0).setEpicTleStatusByTpduStatus(epicTleTPDUStatus);

                EpicTleStatus epicTleKeepAliveStatus = new EpicTleStatus();
                epicTleKeepAliveStatus.setCode(inputBean.getUpKeepAlive());
                epicTleChannel.get(0).setEpicTleStatusByKeepAliveStatus(epicTleKeepAliveStatus);

                EpicTleStatus epicTleLoadBalanceStatus = new EpicTleStatus();
                if (Integer.parseInt(inputBean.getUpconType()) == 1) {

                    epicTleLoadBalanceStatus.setCode(Integer.parseInt(inputBean.getUploadBalance()));
                    epicTleChannel.get(0).setEpicTleStatusByLoadbalancestatus(epicTleLoadBalanceStatus);

                    if (Integer.parseInt(inputBean.getUploadBalance()) == 1) {

                        epicTleChannel.get(0).setSecondaryIp(inputBean.getUpsecIp());
                        epicTleChannel.get(0).setSecondaryPort(inputBean.getUpsecPort());

                        EpicTleChannelOperationMethod OperMethodStatus = new EpicTleChannelOperationMethod();
                        OperMethodStatus.setCode(Integer.parseInt(inputBean.getUpoperMethod()));
                        epicTleChannel.get(0).setEpicTleChannelOperationMethod(OperMethodStatus);

                    } else {

                        epicTleChannel.get(0).setSecondaryIp(null);
                        epicTleChannel.get(0).setSecondaryPort(null);
                        epicTleChannel.get(0).setEpicTleChannelOperationMethod(null);

                    }
                } else {

                    epicTleLoadBalanceStatus.setCode(2);
                    epicTleChannel.get(0).setEpicTleStatusByLoadbalancestatus(epicTleLoadBalanceStatus);
                    epicTleChannel.get(0).setSecondaryIp(null);
                    epicTleChannel.get(0).setSecondaryPort(null);
                    epicTleChannel.get(0).setEpicTleChannelOperationMethod(null);
                }

                if (Integer.parseInt(inputBean.getUpPinTrans()) == 1) {
                    epicTleChannel.get(0).setEncryptZpk(inputBean.getUpenzpk());
                }

                EpicTleStatus PinTransStatus = new EpicTleStatus();
                PinTransStatus.setCode(Integer.parseInt(inputBean.getUpPinTrans()));
                epicTleChannel.get(0).setEpicTleStatusByPintranslateStatus(PinTransStatus);

                EpicTleStatus epicTleStatus = new EpicTleStatus();
                epicTleStatus.setCode(Integer.parseInt(inputBean.getUpstatus()));
                epicTleChannel.get(0).setEpicTleStatusByStatus(epicTleStatus);

                EpicTleForwardmethod epicTleForwardmethod = new EpicTleForwardmethod();
                epicTleForwardmethod.setCode(Integer.parseInt(inputBean.getUpforwerdMathod()));
                epicTleChannel.get(0).setEpicTleForwardmethod(epicTleForwardmethod);

                EpicTleNodetype nodeType = (EpicTleNodetype) session.load(EpicTleNodetype.class, Configurations.SERVER_NODE);
                epicTleChannel.get(0).setEpicTleNodetype(nodeType);

                session.save(epicTleChannel.get(0));
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

    public boolean deleteNII(ChannelManageInputBean inputBean) throws Exception {
        boolean isUserDeleted = false;
        Session session = null;
        Query query = null;
        Query NIIquery = null;
        List<EpicTleNii> NIIList = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String niiSql = "from EpicTleNii dd where dd.channelid=:channelId";
            NIIquery = session.createQuery(niiSql);
            NIIquery.setString("channelId", Integer.toString(inputBean.getChannelId()));

            if (!NIIquery.list().isEmpty()) {

                NIIList = NIIquery.list();
                for (EpicTleNii NIIListData : NIIList) {
                    String sqlNII = "DELETE EpicTleNii bn WHERE bn.id =:niiId";
                    Query NiiQuery = session.createQuery(sqlNII);
                    NiiQuery.setInteger("niiId", NIIListData.getId());
                    NiiQuery.executeUpdate();
                }

                String sql = "delete EpicTleChannel ch"
                        + "  where ch.channelId =:channelId";
                query = session.createQuery(sql);
                query.setString("channelId", Integer.toString(inputBean.getChannelId()));
                int result = query.executeUpdate();
                if (1 == result) {
                    isUserDeleted = true;
                }

            } else {
                String sql = "delete EpicTleChannel ch"
                        + "  where ch.channelId =:channelId";
                query = session.createQuery(sql);
                query.setString("channelId", Integer.toString(inputBean.getChannelId()));
                int result = query.executeUpdate();
                if (1 == result) {
                    isUserDeleted = true;
                }
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

    @Override
    public List<ListenerManageDataBean> loadListnerData(ListenerManageInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getMapmapnii(ListenerManageBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkNii(String anii) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkMapNii(String amapnii) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertmapnii(ListenerManageBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateListener(ListenerManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean deleteListener(ListenerManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean insertListener(ListenerManageInputBean bean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findListener(ListenerManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ChannelManageInputBean getPagePath(String page, ChannelManageInputBean inputBean) throws Exception {
        if (page != null && page.trim() != "") {
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
    public ListenerManageInputBean getPagePath(String page, ListenerManageInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    public Map<Integer, String> getIsoFormat() throws Exception {
        Map<Integer, String> HeaderSizeMap = new HashMap<Integer, String>();
        List<EpicTleIsoFormat> list = getMasterValues(0, 10, "EpicTleIsoFormat");
        for (EpicTleIsoFormat list1 : list) {
            HeaderSizeMap.put(list1.getCode(), list1.getEpicTleIsoFormat());
        }
        return HeaderSizeMap;
    }

    @Override
    public String GetResult(ChannelManageInputBean inputBean, String field) throws Exception {
        String channelName = null;
         Session session = null;
        try {

           
            String sqlCount = "";
            Query queryCount = null;
            Query querySearch = null;
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            if (field.toLowerCase().equals("name")) {

                sqlCount = "from EpicTleChannel wu where wu.channelName =:name and wu.epicTleNodetype.code =:node";
                queryCount = session.createQuery(sqlCount);

                if (inputBean.getChannelName() != null) {

                    queryCount.setString("name", inputBean.getChannelName());
                    queryCount.setInteger("node", Configurations.SERVER_NODE);

                } else if (inputBean.getUpchannelName() != null) {

                    System.out.println("inputBean.getUpchannelName() " + inputBean.getUpchannelName());
                    queryCount.setString("name", inputBean.getUpchannelName());
                    queryCount.setInteger("node", Configurations.SERVER_NODE);

                }

            }

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
//                ChannelManageInputBean databean = new ChannelManageInputBean();
                EpicTleChannel objBean = (EpicTleChannel) it.next();
                channelName = objBean.getChannelName();
            }

            session.getTransaction().commit();

        }catch (Exception e) {
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
        return channelName;
    }

    @Override
    public String GetResult(ListenerManageInputBean inputBean, String field) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
