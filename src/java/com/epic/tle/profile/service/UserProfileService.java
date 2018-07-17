/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.profile.service;

import com.epic.tle.mapping.EpicTleModule;
import com.epic.tle.mapping.EpicTleProfilePrivilage;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleTask;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.mapping.EpicTleUserProfile;
import com.epic.tle.profile.bean.UserProfileBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chalaka_n
 */
public class UserProfileService implements UserProfileInf {

    @Override
    public int insertUserProfile(UserProfileInputBean inputBean) throws Exception {
        int newProfileId = 0;
        EpicTleUserProfile epicTleUserProfile = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            int count = 0;
            String sqlCount = "select max(code) from EpicTleUserProfile";
            Query queryCount = session.createQuery(sqlCount);
            Iterator itCount = queryCount.iterate();
            int decimal = (int) itCount.next();
            count = decimal;
            newProfileId = count + 1;

            epicTleUserProfile = new EpicTleUserProfile();
            epicTleUserProfile.setCode(newProfileId);
            epicTleUserProfile.setDescription(inputBean.getProfilename());

            EpicTleStatus epicTleStatus = new EpicTleStatus();
            epicTleStatus.setCode(Status.ACTIVE);
            epicTleUserProfile.setEpicTleStatus(epicTleStatus);
            epicTleUserProfile.setCreatedate(Util.getLocalDate());

            session.beginTransaction();
            session.save(epicTleUserProfile);
            session.getTransaction().commit();

        } catch (Exception ex) {
            if (session != null) {
                session.close();
                session = null;
            }
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.flush();
                session.close();
                session = null;
            }

        }
        return (int) newProfileId;
    }

    @Override
    public List<UserProfileBean> loadSearchUserProfile(UserProfileInputBean inputBean, String orderBy, int first, int max) throws Exception {
        List<UserProfileBean> dataList = new ArrayList<UserProfileBean>();
        Session session = null;

        try {

            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.description desc ";
            }

            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            Query queryCount;
            Query querySearch;
            if (inputBean.getSearchName().equals("")) {
                String sqlCount = "select count(code) from EpicTleUserProfile wu" + orderBy;
                queryCount = session.createQuery(sqlCount);
            } else {
                String sqlCount = "select count(code) from EpicTleUserProfile wu where wu.description LIKE:des" + orderBy;
                queryCount = session.createQuery(sqlCount);
                queryCount.setString("des", "%" + inputBean.getSearchName() + "%");
            }

            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();
            if (count > 0) {

                if (inputBean.getSearchName().equals("")) {
                    String sqlSearch = "from EpicTleUserProfile wu" + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                } else {
                    String sqlSearch = "from EpicTleUserProfile wu where wu.description LIKE :des " + orderBy;
                    querySearch = session.createQuery(sqlSearch);
                    querySearch.setString("des", "%" + inputBean.getSearchName() + "%");
                }

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);
                Iterator it = querySearch.iterate();
                while (it.hasNext()) {

                    UserProfileBean databean = new UserProfileBean();
                    EpicTleUserProfile objBean = (EpicTleUserProfile) it.next();
                    try {
                        databean.setProfileID(objBean.getCode());
                    } catch (NullPointerException npe) {
                        databean.setProfileID(0);
                    }
                    try {
                        databean.setName(objBean.getDescription());
                    } catch (Exception npe) {
                        databean.setName("--");
                    }
                    try {
                        databean.setStatus(objBean.getEpicTleStatus().getCode() + "");
                    } catch (NullPointerException npe) {
                        databean.setStatus("--");
                    }
                    try {
                        databean.setRegDate(objBean.getCreatedate().toString());
                    } catch (NullPointerException npe) {
                        databean.setRegDate("--");
                    }

                    databean.setFullCount(count);

                    dataList.add(databean);
                }
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                session.clear();
                session.close();
                session = null;
            }
        }
        return dataList;
    }

    @Override
    public boolean userRoleDelete(UserProfileInputBean Bean) throws Exception {
        boolean isUserDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleProfilePrivilage wu"
                    + "  where wu.epicTleUserProfile.code =:code";
            query = session.createQuery(sql);

            query.setInteger("code", Bean.getProfileID());
            int result = query.executeUpdate();
            if (1 == result) {
                isUserDeleted = true;
            }
            session.getTransaction().commit();
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

        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleUserProfile wu"
                    + "  where wu.code =:code";
            query = session.createQuery(sql);

            query.setInteger("code", Bean.getProfileID());
            int result = query.executeUpdate();
            if (1 == result) {
                isUserDeleted = true;
            }
            session.getTransaction().commit();
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
        return isUserDeleted;
    }

    @Override
    public boolean isAnyUserUsingThisProfile(UserProfileInputBean Bean) throws Exception {
        boolean ok = false;
        Session session = null;
        try {
            List<EpicTleUser> epicTleUser = null;

            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUser wu where wu.epicTleUserProfile.code =:code";
            Query query = session.createQuery(sql);
            query.setInteger("code", Bean.getProfileID());
            epicTleUser = query.list();

            if (epicTleUser.size() > 0) {
                ok = true;
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

        return ok;
    }

    @Override
    public void getmodulemap(UserProfileInputBean inputBean) throws Exception {
        List<EpicTleModule> tleModule = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleModule ";
            Query query = session.createQuery(sql);
            tleModule = query.list();

            for (int i = 0; i < tleModule.size(); i++) {
                inputBean.getModulesMap().put(tleModule.get(i).getCode(), tleModule.get(i).getDescription());
            }

        } catch (Exception ex) {
            if (session != null) {
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
    }

    @Override
    public void getmodulePagemap(UserProfileInputBean inputBean) throws Exception {
        List<EpicTleSection> tleSection = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
//            String sql = "from EpicTleModule ";
//            Query query = session.createQuery(sql);
            String sql = "from EpicTleSection se where se.epicTleModule.code=:code ";
            Query query = session.createQuery(sql);
            query.setString("code", inputBean.getModules());
            tleSection = query.list();

            for (EpicTleSection section : tleSection) {
                inputBean.getAllPageMap().put(section.getSectionId(), section.getSectionName());
            }

        } catch (Exception ex) {
            if (session != null) {
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
    }

    @Override
    public void getPageTaskmap(UserProfileInputBean inputBean) throws Exception {
        List<EpicTleSection> tleSection = null;
        List<EpicTleProfilePrivilage> tlePrivilages = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
//            String sql = "from EpicTleModule ";
//            Query query = session.createQuery(sql);
            String sql = "from EpicTleSection se where se.sectionId=:sectionId ";
            Query query = session.createQuery(sql);
            query.setString("sectionId", inputBean.getPages());
            tleSection = query.list();

//            for (int i = 0; i < tleSection.size(); i++){
//                for(){
//                
//                }
//            }
            for (EpicTleSection section : tleSection) {
                for (Object taskobj : section.getEpicTleTasks()) {
                    EpicTleTask task = (EpicTleTask) taskobj;
                    inputBean.getUnSelectedTaskMap().put(task.getTaskId(), task.getDescription());
                }

            }
            if (inputBean.getFlag().equalsIgnoreCase("update")) {

                sql = "from EpicTleProfilePrivilage se where se.epicTleSection.sectionId=:sectionId and se.epicTleUserProfile.code=:code";
                query = session.createQuery(sql);
                query.setString("sectionId", inputBean.getPages());
                query.setInteger("code", inputBean.getUpProfileID());
                tlePrivilages = query.list();

                for (EpicTleProfilePrivilage privilage : tlePrivilages) {
                    inputBean.getSelectedTaskMap().put(privilage.getEpicTleTask().getTaskId(), privilage.getEpicTleTask().getDescription());
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
                session.flush();
                session.close();
                session = null;
            }
        }
    }

    @Override
    public boolean alredyInsertProfile(String profileName) throws Exception {
        boolean already = false;
        try {
            List<EpicTleUserProfile> tlePage = null;
            Session session = null;
            try {

                session = HibernateInit.sessionFactory.openSession();
                String sql = "from EpicTleUserProfile au where au.description=:des ";
                Query query = session.createQuery(sql);
                query.setString("des", profileName);
                tlePage = query.list();

                if (!tlePage.isEmpty()) {
                    already = true;
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
        } catch (Exception e) {
            throw e;
        }
        return already;
    }

    @Override
    public void insertUserProfileModule(int profileID, UserProfileInputBean input) throws Exception {
        String[] taskArray = input.getTaskString().split("\\|");
        EpicTleProfilePrivilage privilages = new EpicTleProfilePrivilage();
        Session session = null;
        try {            
            for (int i = 0; i < taskArray.length; i++) {
                try{
                if (!taskArray[i].isEmpty()) {  
                    session = HibernateInit.sessionFactory.openSession();
                    EpicTleUserProfile profile = new EpicTleUserProfile();
                    profile.setCode(profileID);

                    EpicTleModule module = new EpicTleModule();
                    module.setCode(input.getModules());

                    EpicTleSection section = new EpicTleSection();
                    section.setSectionId(input.getPages());

                    EpicTleTask task = new EpicTleTask();
                    task.setTaskId(taskArray[i]);

                    privilages.setEpicTleUserProfile(profile);
                    privilages.setEpicTleModule(module);
                    privilages.setEpicTleSection(section);
                    privilages.setEpicTleTask(task);

                    session.beginTransaction();
                    session.save(privilages);
                    session.getTransaction().commit();
                }
                }catch (Exception ex) {
                    if (session != null) {
                        session.getTransaction().rollback();
                        if(session.isOpen())session.close();
                        session = null;
                    }
                    ex.printStackTrace();
                    throw ex;
                } finally {
                    if (session != null) {
                        session.flush();
                        if(session.isOpen())session.close();
                        session = null;
                    }
                }
            }
        } catch (Exception ex) {
            if (session != null) {
                session.getTransaction().rollback();
                if(session.isOpen())session.close();
                session = null;
            }
            ex.printStackTrace();
            throw ex;
        } finally {
            if (session != null) {
                session.flush();
                if(session.isOpen())session.close();
                session = null;
            }
        }
    }

    @Override
    public void getUpdateData(UserProfileInputBean inputBean) throws Exception {
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            EpicTleUserProfile userProfile = (EpicTleUserProfile) session.get(EpicTleUserProfile.class, inputBean.getUpProfileID());

            if (userProfile != null) {
                inputBean.setUpName(userProfile.getDescription());
                inputBean.setUpStatus(userProfile.getEpicTleStatus().getCode());
            }
        } catch (Exception e) {
            if (session != null) {
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
    }

    @Override
    public boolean updateUserProfile(UserProfileInputBean inputBean) throws Exception {
        boolean ok = false;
        Session session=null,sessiontask = null;
        Query query = null;

        try {

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleProfilePrivilage wu"
                    + "  where wu.epicTleUserProfile.code =:code and wu.epicTleSection.sectionId=:sectionId";
            query = session.createQuery(sql);

            query.setInteger("code", inputBean.getUpProfileID());
            query.setString("sectionId", inputBean.getPages());
            int result = query.executeUpdate();
            session.getTransaction().commit();
            result = 1;
            if (1 == result) {
                List<EpicTleUserProfile> epicTleUserProfiles = new ArrayList<EpicTleUserProfile>();
                EpicTleUserProfile epicTleUserProfile = (EpicTleUserProfile) session.get(EpicTleUserProfile.class, inputBean.getUpProfileID());
                epicTleUserProfile.setDescription(inputBean.getUpName());
                EpicTleStatus epicTleStatus = new EpicTleStatus();
                epicTleStatus.setCode(inputBean.getUpStatus());
                epicTleUserProfile.setEpicTleStatus(epicTleStatus);

                session.beginTransaction();
                session.save(epicTleUserProfile);
                session.getTransaction().commit();
                ok = true;
            } else {
                ok = false;
            }

            if (ok) {
                String[] taskArray = inputBean.getTaskString().split("\\|");
                EpicTleProfilePrivilage privilages = new EpicTleProfilePrivilage();
                for (int i = 0; i < taskArray.length; i++) {
                    try{
                        sessiontask=HibernateInit.sessionFactory.openSession();
                         if (!taskArray[i].isEmpty()) {
                            EpicTleUserProfile profile = new EpicTleUserProfile();
                            profile.setCode(inputBean.getUpProfileID());

                            EpicTleModule module = new EpicTleModule();
                            module.setCode(inputBean.getModules());

                            EpicTleSection section = new EpicTleSection();
                            section.setSectionId(inputBean.getPages());

                            EpicTleTask task = new EpicTleTask();
                            task.setTaskId(taskArray[i]);

                            privilages.setEpicTleUserProfile(profile);
                            privilages.setEpicTleModule(module);
                            privilages.setEpicTleSection(section);
                            privilages.setEpicTleTask(task);

                            sessiontask.beginTransaction();
                            sessiontask.save(privilages);
                            sessiontask.getTransaction().commit();
                        }
                    }
                    catch (Exception e) {
                        if (sessiontask != null) {
                            sessiontask.flush();
                            if(sessiontask.isOpen())sessiontask.close();
                            sessiontask = null;
                        }  
                         ok = false;
                         e.printStackTrace();
                         throw e;
                    }
                    finally{
                        if (sessiontask != null) {
                            sessiontask.flush();
                            if(sessiontask.isOpen())sessiontask.close();
                            sessiontask = null;
                        }                    
                    }
                }
            }
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
                if(session.isOpen())session.close();
                session = null;
            }
            ok = false;
            e.printStackTrace();
            throw e;
        } finally {
            if (session != null) {
                session.flush();
                if(session.isOpen())session.close();
                session = null;
            }
        }
        return ok;
    }

    public UserProfileInputBean getPagePath(String page, UserProfileInputBean inputBean) {

        if (!page.isEmpty() && page != null) {

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
        return inputBean;

    }

    @Override
    public int insertDefaultUserProfilePrivilages(int profileId) throws Exception {
        Session session = null,sessionpwd=null;
        int res = 0;
        try {
            boolean value = true;
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
         
            List list = session.createCriteria(EpicTleProfilePrivilage.class, "up")
                    .createAlias("up.epicTleUserProfile", "epicTleUserProfile")
                    .add(Restrictions.eq("epicTleUserProfile.code", profileId)).list();
            ArrayList<EpicTleProfilePrivilage> al = (ArrayList<EpicTleProfilePrivilage>) list;
            if (al != null) {
                for (EpicTleProfilePrivilage al1 : al) {
                    if (al1.getEpicTleModule().getCode().equals("10")) {
                        value = false;
                    }
                }
            }
            if (value == true) {
                EpicTleProfilePrivilage etpp = new EpicTleProfilePrivilage();
                etpp.setEpicTleUserProfile(new EpicTleUserProfile(profileId));
                etpp.setEpicTleTask(new EpicTleTask("04"));
                etpp.setEpicTleModule(new EpicTleModule("10"));
                etpp.setEpicTleSection(new EpicTleSection("1001"));
                Serializable save = session.save(etpp);                
                
                res = save.hashCode();
            } else {
                res = -1;
            }
            /*
                Add change password as default function
            */
            session.getTransaction().commit();
            sessionpwd = HibernateInit.sessionFactory.openSession();
            sessionpwd.beginTransaction();
            EpicTleProfilePrivilage etppPwdChange = new EpicTleProfilePrivilage();
            etppPwdChange.setEpicTleUserProfile(new EpicTleUserProfile(profileId));
            etppPwdChange.setEpicTleTask(new EpicTleTask(TaskVarList.UPDATE));
            etppPwdChange.setEpicTleModule(new EpicTleModule(SystemModule.USER_MANAGEMENT));
            etppPwdChange.setEpicTleSection(new EpicTleSection(SystemSection.CHANGE_PASSWORD));
            sessionpwd.save(etppPwdChange);
            /*
                End of Add change password as default function
            */
            sessionpwd.getTransaction().commit();           
        } catch (Exception e) {
            if (session != null) {
                session.close();
                session = null;
                if(sessionpwd.isOpen())sessionpwd.close();
                sessionpwd = null;
            }
            throw e;
        } finally {
            if (session != null) {
                session.close();
                session = null;
                if(sessionpwd.isOpen())sessionpwd.close();
                sessionpwd = null;
            }
        }
        return res;
    }

}
