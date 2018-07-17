/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.service;

import com.epic.tle.login.bean.HomeValues;
import com.epic.tle.login.bean.ModuleBean;
import com.epic.tle.login.bean.PageBean;
import com.epic.tle.login.bean.ProcessingBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.login.bean.UserLoginBean;
import com.epic.tle.mapping.EpicTleProcessingTime;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.mapping.EpicTleProfilePrivilage;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author kreshan
 */
public class LoginService implements LoginServiceInf {

    @Override
    public UserLoginBean getDbUserPassword(UserLoginBean userLoginBean) throws Exception {

        Session session = null;
        session = HibernateInit.sessionFactory.openSession();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
        try {
//            EpicTleUser initBean = new EpicTleUser();
//            initBean = (EpicTleUser) session.get(EpicTleUser.class, userLoginBean.getUserName());

            EpicTleUser initBean = (EpicTleUser) session.createCriteria(EpicTleUser.class)
                    .add(Restrictions.eq("username", userLoginBean.getUserName())) // the leading wild card can become a problem since it cannot be indexed by the DB!
                    .uniqueResult();
            userLoginBean.setDBuserName(initBean.getUsername());
            userLoginBean.setEmail(initBean.getEmail() == "" ? "--" : initBean.getEmail());
            userLoginBean.setDBname(initBean.getName());
            userLoginBean.setDBpassword(initBean.getPassword());
            userLoginBean.setDBuserStatus(initBean.getEpicTleStatus().getCode());
            userLoginBean.setDBuserappCode(initBean.getEpicTleUserProfile().getCode());
            userLoginBean.setUserRole(initBean.getEpicTleUserProfile().getDescription());
            userLoginBean.setId(initBean.getUserid());
            userLoginBean.setUserRoleStatus(initBean.getEpicTleUserProfile().getEpicTleStatus().getCode());//get user role status
            userLoginBean.setCreated_date_time(simpleDateFormat.format(initBean.getCreatedate()));
            userLoginBean.setFailure_login_count(initBean.getFailureLoginAttempts());
            userLoginBean.setLast_raw_update_date_time(initBean.getLastRawUpdateDateTime());
            userLoginBean.setLast_password_updated_date(simpleDateFormat.format(initBean.getLastPasswordChangeDate()));
            userLoginBean.setLast_password_reset_date_time(initBean.getLastPasswordResetDate());

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return userLoginBean;
    }

    public boolean checkUsername(UserLoginBean userLoginBean) throws Exception {

        Session session = null;
        session = HibernateInit.sessionFactory.openSession();
        boolean result = false;
        try {
//            EpicTleUser initBean = new EpicTleUser();
//            initBean = (EpicTleUser) session.get(EpicTleUser.class, userLoginBean.getUserName());

            EpicTleUser initBean = (EpicTleUser) session.createCriteria(EpicTleUser.class)
                    .add(Restrictions.eq("username", userLoginBean.getUserName())) // the leading wild card can become a problem since it cannot be indexed by the DB!
                    .uniqueResult();
            result = (initBean != null);
        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
        return result;
    }

    public boolean varifilogin(UserLoginBean userLoginBean) throws Exception {
        if (Util.generateHash(userLoginBean.getPassword(),Util.getUserRandVal(userLoginBean.getUserName())).equals(userLoginBean.getDBpassword())) {
            return true;
        } else {
            return false;
        }
    }

    public List<String> getUserprofilePageidList(int dbUserprofile) throws Exception {

        List<String> pageList = new ArrayList<String>();
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "select pp.epicTleSection.sectionId from EpicTleProfilePrivilage as pp where pp.epicTleUserProfile.code =:code";
            Query query = session.createQuery(sql).setInteger("code", dbUserprofile);
            pageList = query.list();
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
        return pageList;
    }

    @Override
    public Map<ModuleBean, List<PageBean>> getModulePageTaskByUser(int dBuserappCode) {
        HashMap<ModuleBean, List<PageBean>> moduleBean = new HashMap<ModuleBean, List<PageBean>>();
        List<EpicTleProfilePrivilage> moduleList;
        Session session = null;
        Transaction tx = null;
        Query query;
        try {
            HibernateInit hibernateInit = new HibernateInit();
            hibernateInit.initialize();
            session = HibernateInit.sessionFactory.openSession();
            tx = session.beginTransaction();
            String sql = "from EpicTleProfilePrivilage as pp where pp.epicTleUserProfile.code =:code";
            query = session.createQuery(sql).setInteger("code", dBuserappCode);
            moduleList = query.list();
            Set<ModuleBean> hs = new HashSet<ModuleBean>();
            for (int i = 0; i < moduleList.size(); i++) {
                ModuleBean mbean = new ModuleBean();
                mbean.setMODULE_ID(moduleList.get(i).getEpicTleModule().getCode());
                mbean.setMODULE_NAME(moduleList.get(i).getEpicTleModule().getDescription());
                hs.add(mbean);
            }

            moduleBean = new HashMap<ModuleBean, List<PageBean>>();
            for (ModuleBean bean : hs) {
                List<PageBean> pages = new ArrayList<PageBean>();
                for (int j = 0; j < moduleList.size(); j++) {

                    if (moduleList.get(j).getEpicTleModule().getCode().equals(bean.getMODULE_ID())) {
                        PageBean pageBean = new PageBean();

                        pageBean.setMODULE(moduleList.get(j).getEpicTleModule().getCode());
                        pageBean.setPAGE_ID(moduleList.get(j).getEpicTleSection().getSectionId());
                        pageBean.setPAGE_NAME(moduleList.get(j).getEpicTleSection().getSectionName());
                        pageBean.setPAGE_URL(moduleList.get(j).getEpicTleSection().getSectionUrl());
                        pages.add(pageBean);
                    }
                }
                moduleBean.put(bean, pages);
            }
            tx.commit();
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                session.close();
                session = null;
            }
            throw ex;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }
        }
        return moduleBean;

    }

    @Override
    public HashMap<String, List<TaskBean>> getAllPageTask(int profileID) throws Exception {
        HashMap<String, List<TaskBean>> pageTaskMap = new HashMap<String, List<TaskBean>>();
        List<EpicTleProfilePrivilage> pageList = new ArrayList<EpicTleProfilePrivilage>();
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            // String sql = "select pmp.epicTlePage.pageId from EpicTleProfilePrivilage as pmp where pmp.epicTleUserProfile.code =:code";
            String sql = "from EpicTleProfilePrivilage as pp where pp.epicTleUserProfile.code =:code";
            Query query = session.createQuery(sql).setInteger("code", profileID);
            pageList = query.list();

            Set<String> section = new HashSet<String>();
            String secName = "";
            for (int i = 0; i < pageList.size(); i++) {
                if (!secName.equals(pageList.get(i).getEpicTleSection().getSectionId())) {
                    section.add(pageList.get(i).getEpicTleSection().getSectionId());
                    secName = pageList.get(i).getEpicTleSection().getSectionName();
                }

            }

            for (String strSec : section) {
                List<TaskBean> tasklist = new ArrayList<TaskBean>();
                for (int i = 0; i < pageList.size(); i++) {
                    if (pageList.get(i).getEpicTleSection().getSectionId().equals(strSec)) {
                        TaskBean bean = new TaskBean();
                        bean.setTASK_ID(pageList.get(i).getEpicTleTask().getTaskId());
                        bean.setTASK_NAME(pageList.get(i).getEpicTleTask().getDescription());
                        tasklist.add(bean);
                    }
                }
                pageTaskMap.put(strSec, tasklist);
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
                session.flush();
                session.close();
                session = null;
            }
        }

        return pageTaskMap;
    }

    @Override
    public void getProcessingData(UserLoginBean userLoginBean) throws Exception {
        SimpleDateFormat sf= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        List<EpicTleProcessingTime> monitor = new ArrayList<EpicTleProcessingTime>();
        ProcessingBean bean = new ProcessingBean();
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
//            String sql = "from EpicTleProcessingTime ORDER BY datetime DESC";
            String sql = "select avg(tleTime) from EpicTleProcessingTime sh where sh.datetime >= :today GROUP BY hour(sh.datetime)";
            Query query = session.createQuery(sql);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            query.setParameter("today", dateFormatter.parse(dateFormatter.format(new Date())));
            ArrayList<Double> avgTime = (ArrayList<Double>) query.list();
//            monitor = (List<EpicTleProcessingTime>) query.list();

//            for (EpicTleProcessingTime process : monitor) {
//                
//                ProcessingBean bean = new ProcessingBean();
//                bean.setDatetime(process.getDatetime().toString().replace("T", " ").replace(".0", ""));
//                bean.setHostTime(process.getHostTime() / 1000);
//                bean.setTleTime(process.getTleTime() / 1000);
//                bean.setTotalTime(process.getTotalTime() / 1000);
//                userLoginBean.getChartMap().add(bean);
//                
//            }
 //           String DateTimeSql = "from EpicTleProcessingTime sh where sh.datetime >= :today GROUP BY hour(sh.datetime),sh.id";
            String DateTimeSql = "select max(datetime) from EpicTleProcessingTime sh where sh.datetime >= :today GROUP BY hour(sh.datetime)";
            Query DateTimeQuery = session.createQuery(DateTimeSql);
            DateTimeQuery.setParameter("today", dateFormatter.parse(dateFormatter.format(new Date())));
            monitor = (List) DateTimeQuery.list();

            for (int i = 0; i < avgTime.size(); i++) {
                ProcessingBean processingBean = new ProcessingBean();
                processingBean.setTotalTime(avgTime.get(i));
                processingBean.setDatetime(sf.format(monitor.get(i)));
                userLoginBean.getChartMap().add(processingBean);
            }

        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
//            throw e;
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
                session = null;
            }
        }
    }

    @Override
    public void getFilterData(UserLoginBean userLoginBean) throws Exception {
        List<EpicTleProcessingTime> monitor = new ArrayList<EpicTleProcessingTime>();
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleProcessingTime sh where (sh.datetime >= :beginDate and sh.datetime <= :endDate) ";
            Query query = session.createQuery(sql);

            SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");
            Date beginDate = new Date();
            query.setParameter("beginDate", dateFormatter.parse(dateFormatter.format(beginDate)));
//            dateFormatter.parse();
//            Date endDate = dateFormatter.parse(userLoginBean.getToDate());
//            endDate.setDate(endDate.getDate() + 1);
            query.setParameter("endDate", dateFormatter.parse(dateFormatter.format(beginDate)));

            monitor = (List<EpicTleProcessingTime>) query.list();

            for (EpicTleProcessingTime process : monitor) {
                ProcessingBean bean = new ProcessingBean();
                bean.setDatetime(process.getDatetime().toString().replace("T", " ").replace(".0", ""));
                bean.setHostTime(process.getHostTime());
                bean.setTleTime(process.getTleTime());
                bean.setTotalTime(process.getTotalTime());
                System.out.println(process.getTleTime());
                userLoginBean.getChartMap().add(bean);
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
                session.close();
                session = null;
            }
        }
    }

    @Override
    public Map<ModuleBean, List<PageBean>> getModulePageByUser(int dBuserappCode) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void getHomeValues(HomeValues homeValues) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserStatus(int status, int userId) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.userid", userId)).uniqueResult();
            user.setEpicTleStatus(new EpicTleStatus(status));
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public int getRemainigPasswordAttempt(int userId) throws Exception {
        int remaining_attempts = -1;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.userid", userId)).uniqueResult();
            remaining_attempts = user.getFailureLoginAttempts();
            beginTransaction.commit();
        } catch (Exception e) {
            e.printStackTrace();
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
        return remaining_attempts;
    }

    @Override
    public boolean updateRemainigPasswordAttempt(int value, int userId) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.userid", userId)).uniqueResult();
            user.setFailureLoginAttempts(value);
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public boolean updateLastRawUpdateDateTime(int userId) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.userid", userId)).uniqueResult();
            user.setLastRawUpdateDateTime(new Date());
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public boolean validateOldPassword(ChangePasswordBean changePasswordBean, String username) throws Exception {

        boolean isValidate = false;

        Session session = null;
        Query query;
        List<EpicTleUser> tleWebUser;
        try {
            String hashPasswordValue = Util.generateHash(changePasswordBean.getOldPassword(),Util.getUserRandVal(username));
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", username);
            tleWebUser = query.list();
            if (tleWebUser.size() > 0) {
                isValidate = tleWebUser.get(0).getPassword().equals(hashPasswordValue);
                session.getTransaction().commit();
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
                session.close();
                session = null;
            }
        }
        return isValidate;
    }

    @Override
    public boolean updatePassword(ChangePasswordBean changePasswordBean, String username) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query;
        List<EpicTleUser> tleWebUser;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", username);
            tleWebUser = query.list();
            if (tleWebUser.size() > 0) {
                tleWebUser.get(0).setPassword(Util.generateHash(changePasswordBean.getNewPassword(),Util.getUserRandVal(username)));
                session.save(tleWebUser.get(0));
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
    public boolean updateLastPasswordChangeDate(int userId) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.userid", userId)).uniqueResult();
            user.setLastPasswordChangeDate(new Date());
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public boolean updateLastPaswwordResetDate(String userName) throws Exception {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean updateUserStatusByUserName(int status, String userName) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.username", userName)).uniqueResult();
            user.setEpicTleStatus(new EpicTleStatus(status));
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public boolean updateRemainigPasswordAttemptByUserName(int value, String userName) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.username", userName)).uniqueResult();
            user.setFailureLoginAttempts(value);
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

    @Override
    public boolean updateLastPasswordChangeDateByUsername(String userName) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.username", userName)).uniqueResult();
            user.setLastPasswordChangeDate(new Date());
            session.update(user);
            beginTransaction.commit();
            isUpdated = true;
        } catch (Exception e) {
            e.printStackTrace();
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
        return isUpdated;
    }

}
