/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.mapping.EpicTleSection;
import com.epic.tle.mapping.EpicTleStatus;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.mapping.EpicTleUserProfile;
import com.epic.tle.userManagement.bean.RegisterUserBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author chalaka_n
 */
public class RegisterUserService implements RegisterUserServiceInf {

    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");

    public Map<Integer, String> getUserTypes() throws Exception {
        Map<Integer, String> usertypesmap = new HashMap<Integer, String>();

        List<EpicTleUserProfile> tleUserTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUserProfile wp where wp.epicTleStatus.code=:code";
            Query query = session.createQuery(sql);
            query.setInteger("code", 1);
            tleUserTypes = query.list();

            for (int i = 0; i < tleUserTypes.size(); i++) {
                usertypesmap.put(tleUserTypes.get(i).getCode(), tleUserTypes.get(i).getDescription());
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
        return usertypesmap;
    }

    public Map<Integer, String> getStatusTypes() throws Exception {

        Map<Integer, String> statusTypesMap = new HashMap<Integer, String>();

        List<EpicTleStatus> tleStatusTypes = null;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleStatus wu where wu.code between 1 and 2";
            Query query = session.createQuery(sql);
//            query.setInteger("code", 1);
            tleStatusTypes = query.list();

            for (int i = 0; i < tleStatusTypes.size(); i++) {
                statusTypesMap.put(tleStatusTypes.get(i).getCode(), tleStatusTypes.get(i).getDescription());
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
        return statusTypesMap;

    }

    @Override
    public RegisterUserBean getPagePath(String page, RegisterUserBean inputBean) {
        String module = (page != null && page != "") ? page.substring(0, 2) : "";
        Session session = null;
        String pagePath = "";

        try {

            if (module != null && module != "") {
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

    public boolean checkUserName(String userName) throws Exception {
        boolean isUsername = false;
        List<EpicTleUser> tleWebUser = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", userName);
            tleWebUser = query.list();
            if (0 < tleWebUser.size()) {
                int statusCode = 0;
                for (int i = 0; i < tleWebUser.size(); i++) {
                    statusCode = tleWebUser.get(i).getEpicTleStatus().getCode();
                    if (statusCode == 1) {
                        isUsername = true;
                    }
                }

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
        return isUsername;
    }
    
    
    public boolean checkEmail(String email,String userName) throws Exception {
        boolean isEmail = false;
        List<EpicTleUser> tleWebUser = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUser wu where wu.username!=:username and wu.email =:email";
            query = session.createQuery(sql);
            query.setString("email", email);
            query.setString("username", userName);
            tleWebUser = query.list();
            if (0 < tleWebUser.size()) {
               isEmail = true;
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
        return isEmail;
    }

    public boolean addUser(RegisterUserBean inputBean) throws Exception {
        boolean isAddUser = false;
        EpicTleUser tleWebUser = null;
        Session session = null;
       // String password="";
        try {
            String randomValue=Util.getRandomVal(10);
            session = HibernateInit.sessionFactory.openSession();
            tleWebUser = new EpicTleUser();
            tleWebUser.setName(inputBean.getName());
            tleWebUser.setUsername(inputBean.getUserName());
            tleWebUser.setRandVal(Util.dataEncrypter(1, randomValue));
            tleWebUser.setPassword(Util.generateHash(inputBean.getPassword(),randomValue));
            tleWebUser.setEmail(inputBean.getEmail());
            EpicTleUserProfile tleuser = new EpicTleUserProfile();
            tleuser.setCode(Integer.parseInt(inputBean.getUserType()));
            tleWebUser.setEpicTleUserProfile(tleuser);

            EpicTleStatus tlestatus = (EpicTleStatus) session.get(EpicTleStatus.class, 9);
            tleWebUser.setEpicTleStatus(tlestatus);
            tleWebUser.setCreatedate(Util.getLocalDate());
            tleWebUser.setCreateusername(sub.getUserName());
            tleWebUser.setLastPasswordChangeDate(new Date());
            tleWebUser.setLastRawUpdateDateTime(new Date());
            tleWebUser.setLastPasswordResetDate(new Date());

            session.beginTransaction();
            Serializable a=session.save(tleWebUser);
            session.getTransaction().commit();
            int id=a.hashCode();
            Util.updatePasswordHistory(inputBean.getPassword(), sub.getUserName(), id,randomValue);
            isAddUser = true;
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
        return isAddUser;
    }

    //should edit
    public List<RegisterUserBean> loadUsers(RegisterUserBean inputBean, int max, int first, String orderBy) throws Exception {

        List<RegisterUserBean> dataList = new ArrayList<RegisterUserBean>();
        Session session = null;
        try {
            if (orderBy.equals("") || orderBy == null) {
                orderBy = " order by wu.createdate desc ";
            }
            long count = 0;

            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
//            String sqlCount = "select count(username) from EpicTleUser wu where wu.epicTleStatus.code =:statuscode and wu.name LIKE :name " + orderBy;
            String sqlCount = "select count(username) from EpicTleUser wu where wu.name LIKE :name and wu.epicTleUserProfile.description LIKE :userRole " + orderBy;
            Query queryCount = session.createQuery(sqlCount);
//            queryCount.setInteger("statuscode", Status.ACTIVE);
            queryCount.setParameter("name", "%" + inputBean.getSearchName() + "%");
            queryCount.setParameter("userRole", "%" + inputBean.getUserRole() + "%");
//            queryCount.setParameter("status", "%" +inputBean.getStatus()+ "%");
            Iterator itCount = queryCount.iterate();
            count = (Long) itCount.next();

            if (count > 0) {

//               String sqlSearch = "from EpicTleUser wu where wu.epicTleStatus.code =:statuscode and wu.name LIKE :name " + orderBy;
                String sqlSearch = "from EpicTleUser wu where wu.name LIKE :name and wu.epicTleUserProfile.description LIKE :userRole" + orderBy;
                Query querySearch = session.createQuery(sqlSearch);
//                querySearch.setInteger("statuscode", Status.ACTIVE);
                querySearch.setParameter("name", "%" + inputBean.getSearchName() + "%");
                querySearch.setParameter("userRole", "%" + inputBean.getUserRole() + "%");
//                querySearch.setParameter("status", "%" +inputBean.getStatus()+ "%");

                querySearch.setMaxResults(max);
                querySearch.setFirstResult(first);

                Iterator it = querySearch.iterate();

                while (it.hasNext()) {

                    RegisterUserBean databean = new RegisterUserBean();
                    EpicTleUser objBean = (EpicTleUser) it.next();
                    try {
                        databean.setName(objBean.getName());
                    } catch (NullPointerException npe) {
                        databean.setName("--");
                    }
                    try {
                        databean.setEmail(objBean.getEmail().isEmpty() ? "--" : objBean.getEmail());
                    } catch (NullPointerException npe) {
                        databean.setEmail("--");
                    }
                    try {
                        databean.setUserName(objBean.getUsername());
                    } catch (NullPointerException npe) {
                        databean.setUserName("--");
                    }
                    try {
                        databean.setUserType(objBean.getEpicTleUserProfile().getDescription());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setUserTypeId("" + objBean.getEpicTleUserProfile().getCode());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setStatusType("" + objBean.getEpicTleStatus().getCode());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    try {
                        databean.setDate(objBean.getCreatedate().toString());
                    } catch (NullPointerException npe) {
                        databean.setUserType("--");
                    }
                    databean.setFullCount(count);

                    dataList.add(databean);
                }

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
                session.clear();
                session.getTransaction().commit();
                session.close();
                session = null;
            }
        }
        return dataList;
    }

    public boolean deleteUser(String duserName) throws Exception {
        boolean isUserDeleted = false;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "delete EpicTleUser wu"
                    + "  where wu.username =:username";
            query = session.createQuery(sql);

            query.setString("username", duserName);
            int result = query.executeUpdate();
            if (1 == result) {
                isUserDeleted = true;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) {
                session.getTransaction().rollback();
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
        return isUserDeleted;
    }

    public void findUser(RegisterUserBean inputBean) throws Exception {
        List<EpicTleUser> finfuserlist = null;
        Session session = null;
        Query query = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", inputBean.getUpuserName());
            finfuserlist = query.list();

            if (0 < finfuserlist.size()) {
                inputBean.setUpName(finfuserlist.get(0).getName());
                inputBean.setUpEmail(finfuserlist.get(0).getEmail());
                inputBean.setUpuserName(finfuserlist.get(0).getUsername());
                inputBean.setUpuserTypeId(finfuserlist.get(0).getEpicTleUserProfile().getCode());
                int code = finfuserlist.get(0).getEpicTleStatus().getCode();
                inputBean.setUpstatus(code);
                boolean ism = true;
                if (code == 1) {
                    ism = false;
                } else if (code == 2) {
                    ism = false;
                }
                inputBean.setIsNewMember(ism);
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
                session.close();
                session = null;
            }
        }

    }

    public boolean updateUser(RegisterUserBean inputBean) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleUser> tleWebUser = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", inputBean.getUpuserName());
            tleWebUser = query.list();
            if (tleWebUser.size() > 0) {
                tleWebUser.get(0).setName(inputBean.getUpName());
                tleWebUser.get(0).setEmail(inputBean.getUpEmail());
                EpicTleUserProfile tu = new EpicTleUserProfile();
                tu.setCode(inputBean.getUpuserTypeId());
                tleWebUser.get(0).setEpicTleUserProfile(tu);
//                if (inputBean.isIsChecked()) {
//                    tleWebUser.get(0).setPassword(Util.generateHash(inputBean.getUpNewPw()));
//                }
                if (inputBean.getUpstatus() > 0) {
                    EpicTleStatus tlestatus = (EpicTleStatus) session.get(EpicTleStatus.class, inputBean.getUpstatus());
                    tleWebUser.get(0).setEpicTleStatus(tlestatus);
                }
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
    public boolean updateLastPaswwordResetDate(String userName) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        Query query = null;
        List<EpicTleUser> tleWebUser = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            String sql = "from EpicTleUser wu where wu.username =:username";
            query = session.createQuery(sql);
            query.setString("username", userName);
            tleWebUser = query.list();
            if (tleWebUser.size() > 0) {
                tleWebUser.get(0).setLastPasswordResetDate(new Date());
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
    public boolean updateUserStatus(int status, String username) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        try {
            session = HibernateInit.sessionFactory.openSession();
            Transaction beginTransaction = session.beginTransaction();
            EpicTleUser user = (EpicTleUser) session.createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.username", username)).uniqueResult();
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
    public boolean updatePassword(String password, String username) throws Exception {
        boolean isUpdated = false;
        Session session = null;
        EpicTleUser user;
        try {
            session = HibernateInit.sessionFactory.openSession();
            session.beginTransaction();
            user = (EpicTleUser) session
                    .createCriteria(EpicTleUser.class, "user")
                    .add(Restrictions.eq("user.username", username))
                    .uniqueResult();
            user.setPassword(Util.generateHash(password,Util.getUserRandVal(username)));
            session.update(user);
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
