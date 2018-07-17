/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.mapping.EpicTleInitconfig;
import com.epic.tle.mapping.EpicTleLoglevel;
import com.epic.tle.mapping.EpicTleReplayAttacklevel;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.servermanager.bean.HSMConfigurationBean;
import com.epic.tle.servermanager.bean.PortConfigurationDataBean;
import com.epic.tle.servermanager.bean.PortConfigurationInputBean;
import com.epic.tle.servermanager.bean.ResponseConfigurationBean;
import com.epic.tle.servermanager.bean.ServerConfigurationBean;
import com.epic.tle.servermanager.bean.SessionConfigurationBean;
import com.epic.tle.servermanager.bean.SmsEmailConfigurationBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
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
 * @author dimuthu_h
 */
public class ServerConfigurationService implements ServerConfInf {

    public ServerConfigurationBean viewServerConfDetails(ServerConfigurationBean inputBean) throws Exception {

        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String hqlPL = "from EpicTleInitconfig";
            query = session.createQuery(hqlPL);
            Iterator itTleInitConf = query.iterate();

            EpicTleInitconfig tleserverconfobj = (EpicTleInitconfig) itTleInitConf.next();
//            inputBean.setMessageFormat(tleserverconfobj.getId().getMsgformat());
//            inputBean.setListenerConMode(tleserverconfobj.getId().getRunmode().toString());
//            inputBean.setNoofconnection(tleserverconfobj.getId().getNoofconnection().toString());
//            inputBean.setUport(tleserverconfobj.getId().getUport().toString());
//            inputBean.setPport(tleserverconfobj.getId().getPport().toString());
//            inputBean.setMonitorStatus(tleserverconfobj.getId().getMonitorstatus().toString());
//            inputBean.setStestPacketStatus(tleserverconfobj.getId().getTestpacketstatus().toString());
//            inputBean.setLogFileBackupStatus(tleserverconfobj.getId().getConsolelevel().toString());

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
        return inputBean;
    }

    public boolean updateServerConf(ServerConfigurationBean inputBean) throws Exception {

        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleInitconfig> tleinitconf = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String sql = "from EpicTleInitconfig where node=:node";
            query = session.createQuery(sql);
            System.out.println("Configurations.SERVER_NODE   " + Configurations.SERVER_NODE);
            query.setInteger("node", Configurations.SERVER_NODE);

            tleinitconf = (List<EpicTleInitconfig>) query.list();
            if (tleinitconf.size() > 0) {

                tleinitconf.get(0).setPoolminthread(Integer.parseInt(inputBean.getThredMinPool()));
                tleinitconf.get(0).setPoolmaxthread(Integer.parseInt(inputBean.getThredMaxPool()));
                tleinitconf.get(0).setPoolmaxqueque(Integer.parseInt(inputBean.getMaxQueueSize()));
                tleinitconf.get(0).setPoolbacklog(Integer.parseInt(inputBean.getBackLogSize()));
                EpicTleStatus vipstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getVipStatus());
                tleinitconf.get(0).setEpicTleStatusByVipstatus(vipstatus);
                tleinitconf.get(0).setVip(inputBean.getVip());
                tleinitconf.get(0).setIv(inputBean.getIv());
                EpicTleStatus hoststatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getHostFailStatus());
                tleinitconf.get(0).setEpicTleStatusByHostfailalertstatus(hoststatus);
                EpicTleReplayAttacklevel replaylevel = (EpicTleReplayAttacklevel) session.get(EpicTleReplayAttacklevel.class, inputBean.getReplayAttackLevel());
                tleinitconf.get(0).setEpicTleReplayAttacklevel(replaylevel);
                EpicTleLoglevel loglevel = (EpicTleLoglevel) session.get(EpicTleLoglevel.class, inputBean.getLogLevel());
                tleinitconf.get(0).setEpicTleLoglevel(loglevel);
                tleinitconf.get(0).setLogbackuppath(inputBean.getLogPath());
                tleinitconf.get(0).setNoflogbackupfile(Integer.parseInt(inputBean.getNumLogFiles()));
                EpicTleStatus backupstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getLogBackupStatus());
                tleinitconf.get(0).setEpicTleStatusByLogbackupstatus(backupstatus);
                EpicTleStatus debugstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getCoreDebugStatus());
                tleinitconf.get(0).setBufferSize(Integer.parseInt(inputBean.getBuffer()));

                tleinitconf.get(0).setEpicTleStatusByCoreDebugStatus(debugstatus);
                tleinitconf.get(0).setLogfilename(inputBean.getLogFileName());
                //tleinitconf.get(0).setHsmSlot(Integer.parseInt(inputBean.getHsmSlot()));
                //tleinitconf.get(0).setPsgpassword(Util.dataEncrypter(1,inputBean.getPsgPassword()));
                EpicTleStatus esmstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getEsmStatus());

                tleinitconf.get(0).setEpicTleStatusByEsmstatus(esmstatus);
                tleinitconf.get(0).setMonitorip(inputBean.getMonIp());
                tleinitconf.get(0).setMonitorport(Integer.parseInt(inputBean.getPort()));
                tleinitconf.get(0).setMonitortimeout(Integer.parseInt(inputBean.getTimeOut()));
                EpicTleStatus monstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getMonStatus());

                tleinitconf.get(0).setEpicTleStatusByMonitorstatus(monstatus);
//                tleinitconf.get(0).setSmsGwUrl(inputBean.getSmsUrl());
                tleinitconf.get(0).setEmailGwUrl(inputBean.getEmailgwurl());
                tleinitconf.get(0).setEmailGwPort(inputBean.getEmailgwport());
                tleinitconf.get(0).setEmailUsername(inputBean.getEmailgwuser());
                if (inputBean.isIsChecked()) {
                    tleinitconf.get(0).setEmailPassword(Util.dataEncrypter(1, inputBean.getEmailgwpassword()));
                }
//                tleinitconf.get(0).setEmailPassword(Util.dataEncrypter(1, inputBean.getEmailgwpassword()));

                tleinitconf.get(0).setServicePort(Integer.parseInt(inputBean.getServicePort()));
                tleinitconf.get(0).setServiceClientTimeout(Integer.parseInt(inputBean.getClientTimeout()));
//                tleinitconf.get(0).setEmailUsername(inputBean.getEmailgwuser());
//                tleinitconf.get(0).setEmailPassword(inputBean.getEmailgwpassword());
                tleinitconf.get(0).setSmsUsername(inputBean.getSmsUsername());
                tleinitconf.get(0).setSmsPort(inputBean.getSmsPort());
                if (inputBean.isIsCheckedsms()) {
                     tleinitconf.get(0).setSmsPassword(Util.dataEncrypter(1, inputBean.getSmsPassword()));
                }
               
                tleinitconf.get(0).setMaxpincountor(Integer.parseInt(inputBean.getPinCounter()));
                EpicTleStatus adstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getAdVerifyStatus());
                tleinitconf.get(0).setEpicTleStatusByAdVerifyStatus(adstatus);
                EpicTleStatus autoRegstatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getAutoRegStatus());
                tleinitconf.get(0).setEpicTleStatusByAutoRegistaryStatus(autoRegstatus);
//                tleinitconf.get(0).setAdUrl(inputBean.getAdUrl());
//                tleinitconf.get(0).setAdUsername(inputBean.getAdUsername());
//                tleinitconf.get(0).setAdPassword(inputBean.getAdPassword());
                tleinitconf.get(0).setNumHistoryRecords(inputBean.getNumOfHistory());

                EpicTleStatus smsNotify = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getSmsNotifyStatus());
                tleinitconf.get(0).setEpicTleStatusBySmsNotifyStatus(smsNotify);

                EpicTleStatus ukpt = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getUkptStat());
                tleinitconf.get(0).setEpicTleStatusByUkptStatus(ukpt);

                EpicTleStatus autostatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getAutoRegStatus());
                tleinitconf.get(0).setEpicTleStatusByAutoRegistaryStatus(autostatus);

                tleinitconf.get(0).setSmsServiceUrl(inputBean.getSmsUrl());
                tleinitconf.get(0).setSmsServiceTimeout(Integer.parseInt(inputBean.getSmsTimeout()));
                

            }
            session.save(tleinitconf.get(0));
            session.getTransaction().commit();
            isUpdated = true;

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
        return isUpdated;
    }

    @Override
    public SessionConfigurationBean viewSessionConfDetails(SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateSessionConfDetails(SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseConfigurationBean viewResponseConfDetails(ResponseConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateResponseConf(ResponseConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<PortConfigurationDataBean> loadPortConfDetails(PortConfigurationInputBean inputBean, int max, int first, String orderBy) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void findPortDetails(PortConfigurationInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updatePortConfiguration(PortConfigurationInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SmsEmailConfigurationBean viewSmsEmailConfDetails(SmsEmailConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateSmsEmailConf(SmsEmailConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HSMConfigurationBean viewTextContent(HSMConfigurationBean inpuBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateHSMConf(HSMConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SessionConfigurationBean getPagePath(String page, SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateResponseConf(SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseConfigurationBean getPagePath(String page, ResponseConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerConfigurationBean getPagePath(String page, ServerConfigurationBean inputBean) throws Exception {
        String module = (page != null) ? page.substring(0, 2) : "";
        Session session = null;
        String pagePath = "";

        try {
            if (page != null) {
                session = HibernateInit.sessionFactory.openSession();
                session.beginTransaction();
                EpicTleSection epicTleSection = (EpicTleSection) session.get(EpicTleSection.class, page);
                String mod = epicTleSection.getEpicTleModule().getDescription();
                String sect = epicTleSection.getSectionName();

                inputBean.setModule(mod);
                inputBean.setSection(sect);
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

        return inputBean;
    }

    @Override
    public PortConfigurationInputBean getPagePath(String page, PortConfigurationInputBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SmsEmailConfigurationBean getPagePath(String page, SmsEmailConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public HSMConfigurationBean getPagePath(String page, HSMConfigurationBean inputBean) throws Exception {
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
    public Map<Integer, String> getlogLevelMap() throws Exception {
        Map<Integer, String> map = new HashMap<Integer, String>();
        try {
            for (EpicTleLoglevel loglevel : (List<EpicTleLoglevel>) getMasterValues(0, 5, "EpicTleLoglevel")) {
                map.put(loglevel.getCode(), loglevel.getDescription());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return map;
    }

    @Override
    public Map<Integer, String> getAttackLevel() throws Exception {
        Map<Integer, String> map = new HashMap<Integer, String>();
        try {
            for (EpicTleReplayAttacklevel loglevel : (List<EpicTleReplayAttacklevel>) getMasterValues(0, 3, "EpicTleReplayAttacklevel")) {
                map.put(loglevel.getCode(), loglevel.getDescription());
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            throw ex;
        }

        return map;
    }

    @Override
    public void loadIniconfig(ServerConfigurationBean inputBean) throws Exception {
        Session session = null;
        List result = new ArrayList();
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query query = session.createQuery("from EpicTleInitconfig where node=:node");
            query.setInteger("node", Configurations.SERVER_NODE);
            result = query.list();
            for (EpicTleInitconfig config : (List<EpicTleInitconfig>) result) {
                inputBean.setThredMaxPool(Integer.toString(config.getPoolmaxthread()));
                inputBean.setThredMinPool(Integer.toString(config.getPoolminthread()));
                inputBean.setMaxQueueSize(Integer.toString(config.getPoolmaxqueque()));
                inputBean.setBackLogSize(Integer.toString(config.getPoolbacklog()));
                inputBean.setVipStatus(config.getEpicTleStatusByVipstatus().getCode());
                inputBean.setVip(config.getVip());
                inputBean.setIv(config.getIv());
                inputBean.setHostFailStatus(config.getEpicTleStatusByHostfailalertstatus().getCode());
                inputBean.setReplayAttackLevel(config.getEpicTleReplayAttacklevel().getCode());
                inputBean.setBuffer(Integer.toString(config.getBufferSize()));

                inputBean.setLogLevel(config.getEpicTleLoglevel().getCode());
                inputBean.setLogPath(config.getLogbackuppath());
                inputBean.setLogFileName(config.getLogfilename());
                inputBean.setLogBackupStatus(config.getEpicTleStatusByLogbackupstatus().getCode());
                inputBean.setCoreDebugStatus(config.getEpicTleStatusByCoreDebugStatus().getCode());
                inputBean.setNumLogFiles(Integer.toString(config.getNoflogbackupfile()));

                //inputBean.setPsgPassword(Util.dataEncrypter(0,config.getPsgpassword()));
                //inputBean.setHsmSlot(Integer.toString(config.getHsmSlot()));
                inputBean.setEsmStatus(config.getEpicTleStatusByEsmstatus().getCode());

                inputBean.setMonIp(config.getMonitorip());
                inputBean.setPort(Integer.toString(config.getMonitorport()));
                inputBean.setTimeOut(Integer.toString(config.getMonitortimeout()));
                inputBean.setMonStatus(config.getEpicTleStatusByMonitorstatus().getCode());

//                inputBean.setEmailUrl(config.getEmailGwUrl());
                inputBean.setServicePort(Integer.toString(config.getServicePort()));
                inputBean.setClientTimeout(Integer.toString(config.getServiceClientTimeout()));
                inputBean.setSmsUsername(config.getSmsUsername());
                inputBean.setSmsPort(config.getSmsPort());

                inputBean.setEmailgwurl(config.getEmailGwUrl());
                inputBean.setEmailgwuser(config.getEmailUsername());
                inputBean.setEmailgwport(config.getEmailGwPort());
//                inputBean.setEmailgwpassword(Util.dataEncrypter(0, config.getEmailPassword()));

                inputBean.setSmsUrl(config.getSmsServiceUrl());
                inputBean.setSmsTimeout(Integer.toString(config.getSmsServiceTimeout()));

                inputBean.setPinCounter(Integer.toString(config.getMaxpincountor()));
                inputBean.setAdUrl(config.getAdUrl());
                inputBean.setAdPassword(config.getAdPassword());
                inputBean.setAdUsername(config.getAdUsername());
                inputBean.setAdVerifyStatus(config.getEpicTleStatusByAdVerifyStatus().getCode());
                inputBean.setAutoRegStatus(config.getEpicTleStatusByAutoRegistaryStatus().getCode());
                inputBean.setNumOfHistory(config.getNumHistoryRecords());
                inputBean.setSmsNotifyStatus(config.getEpicTleStatusBySmsNotifyStatus().getCode());
                inputBean.setUkptStat(config.getEpicTleStatusByUkptStatus().getCode());
//                System.out.println("SMS Notify Status: "+config.getEpicTleStatusBySmsNotifyStatus().getDescription());
//                System.out.println("UKPT Status : "+config.getEpicTleStatusByUkptStatus().getDescription());
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
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
    }

}
