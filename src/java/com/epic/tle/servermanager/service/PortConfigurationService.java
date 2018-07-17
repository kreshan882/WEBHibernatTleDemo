/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.mapping.EpicTlePortconfig;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.servermanager.bean.HSMConfigurationBean;
import com.epic.tle.servermanager.bean.PortConfigurationDataBean;
import com.epic.tle.servermanager.bean.PortConfigurationInputBean;
import com.epic.tle.servermanager.bean.ResponseConfigurationBean;
import com.epic.tle.servermanager.bean.ServerConfigurationBean;
import com.epic.tle.servermanager.bean.SessionConfigurationBean;
import com.epic.tle.servermanager.bean.SmsEmailConfigurationBean;
import com.epic.tle.util.HibernateInit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jpos.iso.ISOUtil;

/**
 *
 * @author dimuthu_h
 */
public class PortConfigurationService implements ServerConfInf {

    public List<PortConfigurationDataBean> loadPortConfDetails(PortConfigurationInputBean inputBean, int max, int first, String orderBy) throws Exception {
        List<PortConfigurationDataBean> dataList = new ArrayList<PortConfigurationDataBean>();
        Session session = null;
        try {
            long count = 0;

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by pc.sid desc ";
            }

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query querySearch = null;
            Query queryCount = null;

            String sqlCount = "select count(sid) from EpicTlePortconfig pc" + orderBy;
            queryCount = session.createQuery(sqlCount);
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

                String sqlSearch = "from EpicTlePortconfig pc" + orderBy;
                querySearch = session.createQuery(sqlSearch);

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);
                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    PortConfigurationDataBean databean = new PortConfigurationDataBean();
                    EpicTlePortconfig objBean = (EpicTlePortconfig) it.next();

                    try {
                        databean.setDescription(objBean.getDescription());
                    } catch (NullPointerException npe) {
                        databean.setDescription("--");
                    }
                    try {

                        databean.setDatarate(inputBean.getDatarateMap().get(ISOUtil.zeropad(objBean.getDatarate().toString(), 6)));
                    } catch (NullPointerException npe) {
                        databean.setDatarate("--");
                    }
                    try {
                        databean.setDatabit(inputBean.getDatabitMap().get(objBean.getDatabits().toString()));
                    } catch (NullPointerException npe) {
                        databean.setDatabit("--");
                    }
                    try {
                        databean.setParity(inputBean.getParityMap().get(objBean.getParity()));
                    } catch (NullPointerException npe) {
                        databean.setParity("--");
                    }
                    try {
                        databean.setStopbit(inputBean.getStopbitMap().get(objBean.getStopbits().toString()));
                    } catch (NullPointerException npe) {
                        databean.setStopbit("--");
                    }
                    try {
                        databean.setPort(inputBean.getPortMap().get(objBean.getPort()));
                    } catch (NullPointerException npe) {
                        databean.setPort("--");
                    }
                    try {
                        databean.setStatus(inputBean.getStatusMap().get(objBean.getStatus().toString()));
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setSid(objBean.getSid().toString());
                    } catch (NullPointerException npe) {
                        databean.setSid("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }

            }

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
        return dataList;
    }

    public void findPortDetails(PortConfigurationInputBean inputBean) throws Exception {
        List<EpicTlePortconfig> findportconflist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setString("sid", inputBean.getSid());
            findportconflist = query.list();
            if (0 < findportconflist.size()) {
                inputBean.setSid(findportconflist.get(0).getSid().toString());
                inputBean.setDatarate(ISOUtil.zeropad(findportconflist.get(0).getDatarate().toString(), 6));//zeropad
                inputBean.setDatabit(findportconflist.get(0).getDatabits().toString());
                inputBean.setParity(findportconflist.get(0).getParity());
                inputBean.setStopbit(findportconflist.get(0).getStopbits().toString());
                inputBean.setPort(findportconflist.get(0).getPort());
                inputBean.setStatus(findportconflist.get(0).getStatus().toString());

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

    }

    public boolean updatePortConfiguration(PortConfigurationInputBean inputBean) throws Exception {
         boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTlePortconfig> tlePortConf = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTlePortconfig pc where pc.sid =:sid";
            query = session.createQuery(sql);
            query.setString("sid", inputBean.getSid());
            tlePortConf = query.list();
            if (tlePortConf.size() > 0) {
                tlePortConf.get(0).setDatarate(Integer.parseInt(inputBean.getDatarate()));
                tlePortConf.get(0).setDatabits(Integer.parseInt(inputBean.getDatabit()));
                tlePortConf.get(0).setParity(inputBean.getParity());
                tlePortConf.get(0).setPort(inputBean.getPort());
                tlePortConf.get(0).setStopbits(Integer.parseInt(inputBean.getStopbit()));
                tlePortConf.get(0).setStatus(Integer.parseInt(inputBean.getStatus()));
                session.save(tlePortConf.get(0));
                session.getTransaction().commit();
                isUpdated = true;
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
    public ServerConfigurationBean viewServerConfDetails(ServerConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateServerConf(ServerConfigurationBean inputBean) throws Exception {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public PortConfigurationInputBean getPagePath(String page, PortConfigurationInputBean inputBean) throws Exception {
        String module =(page!=null)?page.substring(0, 2):"";
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
