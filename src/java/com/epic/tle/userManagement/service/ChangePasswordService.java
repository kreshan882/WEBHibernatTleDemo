/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.Date;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author danushka_r
 */
public class ChangePasswordService implements ChangePasswordServiceInf{

    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");

    @Override
    public boolean validateOldPassword(ChangePasswordBean changePasswordBean) throws Exception {

        boolean isValidate = false;

        Session session = null;
        Query query;
        List<EpicTleUser> tleWebUser;
        try {
            String hashPasswordValue = Util.generateHash(changePasswordBean.getOldPassword(),Util.getUserRandVal(sub.getUserName()));
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", sub.getUserName());
            tleWebUser = query.list();
            if (tleWebUser.size() > 0) {
                isValidate = tleWebUser.get(0).getPassword().equals(hashPasswordValue);
                session.getTransaction().commit();
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
            session.close();
            session = null;
            }         
        }
        return isValidate;
    }

    @Override
    public boolean updatePassword(ChangePasswordBean changePasswordBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query;
        List<EpicTleUser> tleWebUser;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", sub.getUserName());
            tleWebUser = query.list();          
            if (tleWebUser.size() > 0) {
                tleWebUser.get(0).setPassword(Util.generateHash(changePasswordBean.getNewPassword(),Util.dataEncrypter(0, tleWebUser.get(0).getRandVal())));
                tleWebUser.get(0).setLastPasswordChangeDate(new Date());
                session.save(tleWebUser.get(0));
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
    
}
