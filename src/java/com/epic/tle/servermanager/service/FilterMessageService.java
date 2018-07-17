/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.mapping.EpicTleSmsFilter;
import com.epic.tle.mapping.EpicTleSmsProfile;
import com.epic.tle.mapping.EpicTleSmsProfileInfo;
import com.epic.tle.servermanager.bean.FilterMessageBean;
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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author ridmi_g
 */
public class FilterMessageService implements FilterMessageInf{

    @Override
    public List<FilterMessageBean> loadFilterSMS(FilterMessageBean inputBean, int max, int first, String orderBy) throws Exception {
       List<FilterMessageBean> dataList = new ArrayList<FilterMessageBean>();
        Session session = null;
        
        try {
            long count = 0;
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Criteria seacrhCriteria = null;
            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("filtersms");
//            System.out.println("profile id > "+profileid);
            Order order = null;
            if (inputBean.getSearchName().equals("")) {
                if (!orderBy.equals("")) {
                    if (orderBy.equalsIgnoreCase("asc")) {
                        order = Order.asc("filter.ceratedDate");
                    } else if (orderBy.equalsIgnoreCase("desc")) {
                        order = Order.desc("filter.ceratedDate");
                    }
                } else {
                    order = Order.desc("filter.ceratedDate");
                }
                Object uniqueResult = session.createCriteria(EpicTleSmsFilter.class, "filter")
                        .createAlias("filter.profileId", "smsfilter")
                        .addOrder(order)
                        .add(Restrictions.eq("smsfilter.smsProfileId", profileid))
                        .setProjection(Projections.rowCount()).uniqueResult();
                count = Long.parseLong(uniqueResult.toString());
                
//                System.out.println("count "+count);
                
                seacrhCriteria = session.createCriteria(EpicTleSmsFilter.class, "filter")
                        .createAlias("filter.profileId", "smsfilter")
                        .addOrder(order)
                        .add(Restrictions.eq("smsfilter.smsProfileId", profileid));
                        
            }
         
            if(count > 0 ){
                seacrhCriteria.setFirstResult(first);
                seacrhCriteria.setMaxResults(max);
                Iterator it = seacrhCriteria.list().iterator();
                
                while (it.hasNext()) {
                  FilterMessageBean databean = new  FilterMessageBean();
                   EpicTleSmsFilter objBean = (EpicTleSmsFilter) it.next();
                   
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
                        databean.setMessage(objBean.getMessage());
                    } catch (NullPointerException npe) {
                        databean.setMessage("");
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
        }finally {
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
    public boolean insertSMS(FilterMessageBean inputBean) throws Exception {
         boolean infoSMS = false;
        EpicTleSmsFilter epicTleSmsFilter = null;
        Session session = null;
        
         try {

            session = HibernateInit.sessionFactory.openSession();
            epicTleSmsFilter = new EpicTleSmsFilter();

            HttpSession httpSession = ServletActionContext.getRequest().getSession(false);
            int profileid = (int) httpSession.getAttribute("filtersms");


            EpicTleSmsProfile epicTleSmsProfile = new EpicTleSmsProfile();
            epicTleSmsProfile.setSmsProfileId(profileid);
            epicTleSmsFilter.setProfileId(epicTleSmsProfile);
            epicTleSmsFilter.setMessage(inputBean.getMessage());
            epicTleSmsFilter.setCeratedDate(Util.getLocalDate());

            session.beginTransaction();
            session.save(epicTleSmsFilter);
            session.getTransaction().commit();

            infoSMS = true;
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
        return infoSMS;
    }

    @Override
    public boolean deleteMessage(FilterMessageBean inputBean) throws Exception {
         boolean isFeDeleted = false;
        Session session = null;
        Query query = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            String sql = "DELETE EpicTleSmsFilter bb where bb.id=:id";
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
    public String GetResult(FilterMessageBean inputBean) throws Exception {
        Session session = null;
        String sqlCount = "";
        String msg = null;
        Query queryCount;
        Query querySearch = null;
        try {
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();

            sqlCount = "from EpicTleSmsFilter wu where wu.message=:message";
            queryCount = session.createQuery(sqlCount);
            queryCount.setString("message", inputBean.getMessage());

            Iterator it = queryCount.iterate();

            while (it.hasNext()) {
                EpicTleSmsFilter objBean = (EpicTleSmsFilter) it.next();
                msg = objBean.getMessage();
            }

            session.getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
             if (session != null) {
                session.getTransaction().rollback();
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
        return msg;
    }

    
    
}
