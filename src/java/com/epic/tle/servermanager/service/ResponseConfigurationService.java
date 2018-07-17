/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleStatusofresponse;
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
import org.jpos.iso.ISOUtil;

/**
 *
 * @author dimuthu_h
 */
public class ResponseConfigurationService implements ServerConfInf {

    public ResponseConfigurationBean viewResponseConfDetails(ResponseConfigurationBean inputBean) throws Exception {

        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String hqlSR = "from EpicTleStatusofresponse";
            query = session.createQuery(hqlSR);
            Iterator it = query.iterate();
            while (it.hasNext()) {
                EpicTleStatusofresponse tlestatusresponseobj = (EpicTleStatusofresponse) it.next();
                inputBean.getTlestatusresponseMap().put(ISOUtil.zeropad(("" + tlestatusresponseobj.getFieldno()), 2), ("" + tlestatusresponseobj.getEpicTleStatus().getCode()));//concat int and string                
            }
            inputBean.setF_02(inputBean.getTlestatusresponseMap().get("02"));
            inputBean.setF_03(inputBean.getTlestatusresponseMap().get("03"));
            inputBean.setF_04(inputBean.getTlestatusresponseMap().get("04"));
            inputBean.setF_11(inputBean.getTlestatusresponseMap().get("11"));
            inputBean.setF_12(inputBean.getTlestatusresponseMap().get("12"));
            inputBean.setF_13(inputBean.getTlestatusresponseMap().get("13"));
            inputBean.setF_14(inputBean.getTlestatusresponseMap().get("14"));
            inputBean.setF_22(inputBean.getTlestatusresponseMap().get("22"));
            inputBean.setF_23(inputBean.getTlestatusresponseMap().get("23"));
            inputBean.setF_24(inputBean.getTlestatusresponseMap().get("24"));
            inputBean.setF_25(inputBean.getTlestatusresponseMap().get("25"));
            inputBean.setF_35(inputBean.getTlestatusresponseMap().get("35"));
            inputBean.setF_37(inputBean.getTlestatusresponseMap().get("37"));
            inputBean.setF_38(inputBean.getTlestatusresponseMap().get("38"));
            inputBean.setF_39(inputBean.getTlestatusresponseMap().get("39"));
            inputBean.setF_41(inputBean.getTlestatusresponseMap().get("41"));
            inputBean.setF_42(inputBean.getTlestatusresponseMap().get("42"));
            inputBean.setF_45(inputBean.getTlestatusresponseMap().get("45"));
            inputBean.setF_47(inputBean.getTlestatusresponseMap().get("47"));
            inputBean.setF_48(inputBean.getTlestatusresponseMap().get("48"));
            inputBean.setF_52(inputBean.getTlestatusresponseMap().get("52"));
            inputBean.setF_54(inputBean.getTlestatusresponseMap().get("54"));
            inputBean.setF_55(inputBean.getTlestatusresponseMap().get("55"));
            inputBean.setF_60(inputBean.getTlestatusresponseMap().get("60"));
            inputBean.setF_61(inputBean.getTlestatusresponseMap().get("61"));
            inputBean.setF_62(inputBean.getTlestatusresponseMap().get("62"));
            inputBean.setF_63(inputBean.getTlestatusresponseMap().get("63"));
            inputBean.setF_49(inputBean.getTlestatusresponseMap().get("49"));

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

    public boolean updateResponseConf(ResponseConfigurationBean inputBean) throws Exception {

        boolean isUpdated = false;
        Session session = null;
        Query query = null;

        List<EpicTleStatusofresponse> tlestatusresponse = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
//            session.beginTransaction();
            String sql = "from EpicTleStatusofresponse";
            query = session.createQuery(sql);
            tlestatusresponse = (List<EpicTleStatusofresponse>) query.list();
            if (tlestatusresponse.size() > 0) {

                for (int i = 0; i < tlestatusresponse.size(); i++) {
                    EpicTleStatus tlestatus = new EpicTleStatus();

                    if (tlestatusresponse.get(i).getFieldno() == 2) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_02()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 3) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_03()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 4) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_04()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 11) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_11()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 12) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_12()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 13) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_13()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 14) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_14()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 22) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_22()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 23) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_23()));
                        tlestatusresponse.get(8).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 24) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_24()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 25) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_25()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 35) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_35()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 37) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_37()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 38) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_38()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 39) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_39()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 41) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_41()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 42) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_42()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 45) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_45()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 47) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_47()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 48) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_48()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 52) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_52()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 54) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_54()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 55) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_55()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 60) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_60()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 61) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_61()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 62) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_62()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 63) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_63()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    } else if (tlestatusresponse.get(i).getFieldno() == 49) {
                        tlestatus.setCode(Integer.parseInt(inputBean.getF_49()));
                        tlestatusresponse.get(i).setEpicTleStatus(tlestatus);
                    }
                    session.save(tlestatusresponse.get(i));
//                    session.getTransaction().commit();
                }
                isUpdated = true;

            }
        } catch (Exception e) {
            if (session != null) {
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateResponseConf(SessionConfigurationBean inputBean) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ResponseConfigurationBean getPagePath(String page, ResponseConfigurationBean inputBean) throws Exception {
        String module = (!page.equals("")) ? page.substring(0, 2) : "";
        Session session = null;
        String pagePath = "";

        try {
            if (!page.equals("")) {
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
