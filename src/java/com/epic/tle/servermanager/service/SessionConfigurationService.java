/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.mapping.EpicTleInitconfig;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.servermanager.bean.HSMConfigurationBean;
import com.epic.tle.servermanager.bean.PortConfigurationDataBean;
import com.epic.tle.servermanager.bean.PortConfigurationInputBean;
import com.epic.tle.servermanager.bean.ResponseConfigurationBean;
import com.epic.tle.servermanager.bean.ServerConfigurationBean;
import com.epic.tle.servermanager.bean.SessionConfigurationBean;
import com.epic.tle.servermanager.bean.SmsEmailConfigurationBean;
import com.epic.tle.util.HibernateInit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public class SessionConfigurationService implements ServerConfInf{
    
    public SessionConfigurationBean viewSessionConfDetails(SessionConfigurationBean inputBean) throws Exception{
        Session session = null;
        Query query = null;
        try{
            session = HibernateInit.sessionFactory.openSession();
            String hqlSelect ="from EpicTleInitconfig";
            query =session.createQuery(hqlSelect);
            Iterator it =query.iterate();
            
            EpicTleInitconfig tleInitConf = (EpicTleInitconfig)it.next();
            
//            inputBean.setMax_pin_counter(tleInitConf.getId().getMaxpincountor().toString());
//            inputBean.setHost_pro_timeout(tleInitConf.getId().getUprocesstimeout().toString());
//            inputBean.setLog_fname(tleInitConf.getId().getLogfilename());
//            inputBean.setPkt_size_listeners(tleInitConf.getId().getPacketsizelistner().toString());
//            inputBean.setPkt_size_channels(tleInitConf.getId().getPacketsizechennel().toString());
//            inputBean.setMonitor_ip(tleInitConf.getId().getMonitorip());
//            inputBean.setMonitor_port(tleInitConf.getId().getMonitorport().toString());
//            inputBean.setMonitor_timeout(tleInitConf.getId().getMonitortimeout().toString());
//            inputBean.setChannel_timeout(tleInitConf.getId().getChannelfindtimeout().toString());            
//            inputBean.setReplyAtchLvl(tleInitConf.getId().getReplaylevel().toString());
//            inputBean.setAltNotificationStatus(tleInitConf.getId().getAlertstatus().toString());//not sure
//            inputBean.setTxnFailNotificationStatus(tleInitConf.getId().getFailstatus().toString());
//            inputBean.setLogLevel(tleInitConf.getId().getLoglevel().toString());
//            inputBean.setHsizeListeners(tleInitConf.getId().getHeadersizelistner().toString());
//            inputBean.setHsizeChannels(tleInitConf.getId().getHeadersizechennel().toString());
//            inputBean.setRequestTPDU(tleInitConf.getId().getRequesttpdu().toString());
//            inputBean.setResponseTPDU(tleInitConf.getId().getResponsetpdu().toString());
//            inputBean.setHtest_time_period(tleInitConf.getId().getTestpacketsendtime().toString());//not sure
//            inputBean.setTpduModifyOption(tleInitConf.getId().getTpduchangestatus().toString());
//            inputBean.setTpduModifyPosition(tleInitConf.getId().getTpduchangeposition().toString());
          
            
        }catch (Exception ex) {
            if(session!=null){
            session.close();
            session = null;
            }
            throw ex;
        } finally {
           if(session!=null){
            session.close();
            session = null;
            }
        }
        
        return inputBean;
    }
    
    public boolean updateSessionConfDetails(SessionConfigurationBean inputBean) throws Exception {

        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleInitconfig> tleInitConf = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleInitconfig";
            query = session.createQuery(sql);       
            tleInitConf = (List<EpicTleInitconfig>) query.list();            
            if (tleInitConf.size() > 0) {           
//                tleInitConf.get(0).getId().setMaxpincountor(Integer.parseInt(inputBean.getMax_pin_counter().trim()));
//                tleInitConf.get(0).getId().setReplaylevel(Integer.parseInt(inputBean.getReplyAtchLvl().trim()));
//                tleInitConf.get(0).getId().setAlertstatus(Integer.parseInt(inputBean.getAltNotificationStatus().trim()));
//                tleInitConf.get(0).getId().setFailstatus(Integer.parseInt(inputBean.getTxnFailNotificationStatus().trim()));
//                tleInitConf.get(0).getId().setUprocesstimeout(Integer.parseInt(inputBean.getHost_pro_timeout().trim()));
//                tleInitConf.get(0).getId().setLoglevel(Integer.parseInt(inputBean.getLogLevel().trim()));
//                tleInitConf.get(0).getId().setLogfilename(inputBean.getLog_fname().trim());
//                tleInitConf.get(0).getId().setHeadersizelistner(Integer.parseInt(inputBean.getHsizeListeners().trim()));
//                tleInitConf.get(0).getId().setHeadersizechennel(Integer.parseInt(inputBean.getHsizeChannels().trim()));
//                tleInitConf.get(0).getId().setPacketsizelistner(Integer.parseInt(inputBean.getPkt_size_listeners().trim()));
//                tleInitConf.get(0).getId().setPacketsizechennel(Integer.parseInt(inputBean.getPkt_size_channels().trim()));
//                tleInitConf.get(0).getId().setRequesttpdu(Integer.parseInt(inputBean.getRequestTPDU().trim()));
//                tleInitConf.get(0).getId().setResponsetpdu(Integer.parseInt(inputBean.getResponseTPDU().trim()));
//                tleInitConf.get(0).getId().setMonitorip(inputBean.getMonitor_ip().trim());
//                tleInitConf.get(0).getId().setMonitorport(Integer.parseInt(inputBean.getMonitor_port().trim()));
//                tleInitConf.get(0).getId().setMonitortimeout(Integer.parseInt(inputBean.getMonitor_timeout().trim()));
//                tleInitConf.get(0).getId().setChannelfindtimeout(Integer.parseInt(inputBean.getChannel_timeout().trim()));
//                tleInitConf.get(0).getId().setTestpacketsendtime(Integer.parseInt(inputBean.getHtest_time_period().trim()));
//                tleInitConf.get(0).getId().setTpduchangestatus(Integer.parseInt(inputBean.getTpduModifyOption().trim()));
//                tleInitConf.get(0).getId().setTpduchangeposition(Integer.parseInt(inputBean.getTpduModifyPosition().trim()));
                
                session.save(tleInitConf.get(0));
                session.getTransaction().commit();
                isUpdated = true;
            }
        } catch (Exception e) {
            if(session!=null){
            session.getTransaction().rollback();
            session.close();
            session = null;
            }
            throw e;
        } finally {
           if(session!=null){
            session.flush();
            session.close();
            session = null;
            }
        }
        return isUpdated;
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
    public ServerConfigurationBean viewServerConfDetails(ServerConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateServerConf(ServerConfigurationBean inputBean) throws Exception {
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
    public boolean updateResponseConf(SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseConfigurationBean getPagePath(String page, ResponseConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ServerConfigurationBean getPagePath(String page, ServerConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getMasterValues(int from, int to, String table) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getlogLevelMap() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Map<Integer, String> getAttackLevel() throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void loadIniconfig(ServerConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    
}
