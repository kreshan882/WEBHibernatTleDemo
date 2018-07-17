/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.mapping.EpicTleBlockBin;
import com.epic.tle.mapping.EpicTleSmsProfile;
import com.epic.tle.mapping.EpicTleSmsProfileInfo;
import com.epic.tle.servermanager.bean.SMSInfoBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.bouncycastle.asn1.isismtt.x509.Restriction;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ridmi_g
 */
public class SMSInfoService implements SMSInfoInf {

    @Override
    public boolean insertInfo(SMSInfoBean inputBean) throws Exception {
        boolean infoAdd = false;
        EpicTleSmsProfileInfo epicTleSmsProfileInfo = null;
        Session session = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleSmsProfileInfo = new EpicTleSmsProfileInfo();

            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("smsprofileid");
          

//            if (binId != 0) {
//                Criteria criteria = session.createCriteria(EpicTleBinProfile.class);
//                criteria.add(Restrictions.eq("id", binId));
//                EpicTleBinProfile binProfileID = (EpicTleBinProfile) criteria.uniqueResult();
//                if (binProfileID != null) {
            EpicTleSmsProfile epicTleSmsProfile = new EpicTleSmsProfile();
            epicTleSmsProfile.setSmsProfileId(profileid);
            epicTleSmsProfileInfo.setProfileId(epicTleSmsProfile);
            epicTleSmsProfileInfo.setMobileNo(inputBean.getMobileNo());
            epicTleSmsProfileInfo.setEmail(inputBean.getEmail());
            epicTleSmsProfileInfo.setCeratedDate(Util.getLocalDate());

            session.beginTransaction();
            session.save(epicTleSmsProfileInfo);
            session.getTransaction().commit();

            infoAdd = true;
//                }

            //}
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
        return infoAdd;
    }

  
    @Override
    public List<SMSInfoBean> loadInfo(SMSInfoBean inputBean, int max, int first, String orderBy) throws Exception {
        List<SMSInfoBean> dataList = new ArrayList<SMSInfoBean>();
        Session session = null;
        try {
            long count = 0;
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Criteria seacrhCriteria = null;
            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("smsprofileid");
            Order order = null;
            if (inputBean.getSearchName().equals("")) {
                if (!orderBy.equals("")) {
                    if (orderBy.equalsIgnoreCase("asc")) {
                        order = Order.asc("info.ceratedDate");
                    } else if (orderBy.equalsIgnoreCase("desc")) {
                        order = Order.desc("info.ceratedDate");
                    }
                } else {
                    order = Order.desc("info.ceratedDate");
                }
                Object uniqueResult = session.createCriteria(EpicTleSmsProfileInfo.class, "info")
                        .createAlias("info.profileId", "smsprofile")
                        .addOrder(order)
                        .add(Restrictions.eq("smsprofile.smsProfileId", profileid))
                        .setProjection(Projections.rowCount()).uniqueResult();
                count = Long.parseLong(uniqueResult.toString());
                seacrhCriteria = session.createCriteria(EpicTleSmsProfileInfo.class, "info")
                        .createAlias("info.profileId", "smsprofile")
                        .addOrder(order)
                        .add(Restrictions.eq("smsprofile.smsProfileId", profileid));
                        
            }
            if (count > 0) {
                seacrhCriteria.setFirstResult(first);
                seacrhCriteria.setMaxResults(max);
                Iterator it = seacrhCriteria.list().iterator();
                while (it.hasNext()) {
                    SMSInfoBean databean = new SMSInfoBean();
                    EpicTleSmsProfileInfo objBean = (EpicTleSmsProfileInfo) it.next();
                  
                    try {
                        databean.setId(objBean.getId());
                    } catch (NullPointerException npe) {
                        databean.setId(0);
                    }
                    try {
                        databean.setProfileid(objBean.getProfileId().getSmsProfileId());
                    } catch (NullPointerException npe) {
                        databean.setProfileid(0);
                    }
                    try {
                        databean.setMobileNo(objBean.getMobileNo());
                    } catch (NullPointerException npe) {
                        databean.setMobileNo("");
                    }
                    try {
                        databean.setEmail(objBean.getEmail());
                    } catch (Exception e) {
                        databean.setEmail("");
                    }
                    try {
                        databean.setDate(objBean.getCeratedDate());
                    } catch (NullPointerException npe) {
                        databean.setDate(null);
                    }

                    databean.setFullCount(count);
                    dataList.add(databean);

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
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
    public boolean deleteInfo(SMSInfoBean inputBean) throws Exception {
        boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
  
         
            String sql = "DELETE EpicTleSmsProfileInfo bb where bb.id=:id ";
            query = session.createQuery(sql);
            query.setInteger("id", inputBean.getId());
           

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
    public String GetResult(SMSInfoBean inputBean) throws Exception {
        Session session = null;
        String sqlCount = "";
        String mobileno = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            
            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("smsprofileid");

            sqlCount = "from EpicTleSmsProfileInfo wu where wu.mobileNo=:mobileno and wu.profileId.smsProfileId=:id";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("mobileno", inputBean.getMobileNo());
            queryCount.setInteger("id", profileid);

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleSmsProfileInfo objBean = (EpicTleSmsProfileInfo) it.next();
                mobileno = objBean.getMobileNo();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
             if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            throw e;
        }finally {
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
        return mobileno;
    }

    @Override
    public String GetEmailResult(SMSInfoBean inputBean) throws Exception {
       Session session = null;
        String sqlCount = "";
        String email = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            
            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("smsprofileid");

            sqlCount = "from EpicTleSmsProfileInfo wu where wu.email=:email and wu.profileId.smsProfileId=:id";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("email", inputBean.getEmail());
            queryCount.setInteger("id", profileid);

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleSmsProfileInfo objBean = (EpicTleSmsProfileInfo) it.next();
                email = objBean.getEmail();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
             if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
            throw e;
        }finally {
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
        return email;
    }

}
