/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

/**
 *
 * @author danushka_r
 */
import com.epic.tle.mapping.EpicTlePasswordPolicy;
import com.epic.tle.userManagement.bean.PasswordPolicyBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.util.Iterator;
import org.hibernate.Query;
import org.hibernate.Session;
import java.util.List;

/**
 *
 * @author danushka_r
 */
public class PasswordPolicyService implements PasswordPolicyServiceInf {

    @Override
    public PasswordPolicyBean viewPasswordPolicyDetails(PasswordPolicyBean inputbean) throws Exception {

        Session session = null;
        Query query;
        try {
            session = HibernateInit.sessionFactory.openSession();
            
            session.beginTransaction();
            String hqlPL = "from EpicTlePasswordPolicy";
            query = session.createQuery(hqlPL);
            Iterator itTlePwdPolicy = query.iterate();

            EpicTlePasswordPolicy tlepwdpolicyobj = (EpicTlePasswordPolicy) itTlePwdPolicy.next();
            inputbean.setMin_len(tlepwdpolicyobj.getMinLegnth().toString());
            inputbean.setMax_len(tlepwdpolicyobj.getMaxLegnth().toString());
            inputbean.setAllowspecialcharacters(tlepwdpolicyobj.getAllowspecialcharacters());
            inputbean.setMin_spcl_chars(tlepwdpolicyobj.getMinSpclChars().toString());
            inputbean.setMax_spcl_chars(tlepwdpolicyobj.getMaxSpclChars().toString());
            inputbean.setMin_upercase(tlepwdpolicyobj.getMinUppercase().toString());
            inputbean.setMin_lowcase(tlepwdpolicyobj.getMinLowcase().toString());
            inputbean.setMin_numerics(tlepwdpolicyobj.getMinNumerics().toString());
            inputbean.setSid(tlepwdpolicyobj.getSid());

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
        return inputbean;
    }

    @Override
    public boolean updatePwdPolicy(PasswordPolicyBean pwdbean) throws Exception {

        boolean isUpdated = false;
        Session session = null;
        Query query;
        List<EpicTlePasswordPolicy> tlePwdPolicy;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTlePasswordPolicy";
            query = session.createQuery(sql);
            tlePwdPolicy = (List<EpicTlePasswordPolicy>) query.list();
            if (tlePwdPolicy.size() > 0) {
                tlePwdPolicy.get(0).setMaxLegnth(Integer.parseInt(pwdbean.getMax_len()));
                tlePwdPolicy.get(0).setMinLegnth(Integer.parseInt(pwdbean.getMin_len()));
                tlePwdPolicy.get(0).setAllowspecialcharacters(pwdbean.getAllowspecialcharacters());
                tlePwdPolicy.get(0).setMinSpclChars(Integer.parseInt(pwdbean.getMin_spcl_chars()));
                tlePwdPolicy.get(0).setMaxSpclChars(Integer.parseInt(pwdbean.getMax_spcl_chars()));
                tlePwdPolicy.get(0).setMinUppercase(Integer.parseInt(pwdbean.getMin_upercase()));
                tlePwdPolicy.get(0).setMinLowcase(Integer.parseInt(pwdbean.getMin_lowcase()));
                tlePwdPolicy.get(0).setMinNumerics(Integer.parseInt(pwdbean.getMin_numerics()));
                tlePwdPolicy.get(0).setLstupdatedate(Util.getLocalDate());
                session.save(tlePwdPolicy.get(0));
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
}
