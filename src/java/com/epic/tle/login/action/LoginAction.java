/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.action;

import com.epic.tle.login.bean.HomeValues;
import com.epic.tle.login.bean.ModuleBean;
import com.epic.tle.login.bean.PageBean;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.login.bean.UserLoginBean;
import com.epic.tle.login.service.LoginServiceFactory;
import com.epic.tle.operationManagement.service.OperationManageFactory;
import com.epic.tle.token.bean.TokenBean;
import com.epic.tle.token.service.TokenFactory;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.SessionVariable;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.text.SimpleDateFormat;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author kreshan
 */
public class LoginAction extends ActionSupport implements ModelDriven<UserLoginBean>, SessionAware {

    LoginServiceFactory service;
    private final Timer time = new Timer(true);
    private static int counter;
//    private ScheduledTaskManager sheduleTask;
    UserLoginBean userLoginBean = new UserLoginBean();
    SessionUserBean sessionUserBean = new SessionUserBean();
    private SessionMap<String, Object> sessionMap;
    HomeValues homeValues = new HomeValues();
    Map<ModuleBean, List<PageBean>> modulePageList = null;
    List<String> profilePageidList = new ArrayList<String>();
    Map<String, List<TaskBean>> taskList;

    private static boolean isStartMonitorService = false;

    public LoginServiceFactory getService() {
        return new LoginServiceFactory();
    }

    public String execute() {
        userLoginBean.setServiceStatus(Boolean.toString(Configurations.SERVER_STATUS));
        userLoginBean.setServerNode(Integer.toString(Configurations.SERVER_NODE));
        userLoginBean.setDatabase(Configurations.DB_CONF.toUpperCase());
        return SUCCESS;
    }

    public boolean validateLogin(UserLoginBean bean) throws Exception {
        if (bean.getUserName() != null && bean.getUserName().equals("")) {
            addActionError(SystemMessage.USER_EMPTY_USERNAME);
            return false;
        }
        if (!getService().getLoginService().checkUsername(bean)) {
            addActionError(SystemMessage.USER_INVALID_USERNAME);
            return false;
        } else if (bean.getPassword() != null && bean.getPassword().equals("")) {
            addActionError(SystemMessage.USER_LOGIN_EMPTY_PWD);
            return false;
        }
        return true;

    }

    public String loginCheck() {
        try {
            if (validateLogin(userLoginBean)) {
                userLoginBean = getService().getLoginService().getDbUserPassword(userLoginBean);
                String passwordExpirationTimePeriod = Util.getPasswordExpirationTimePeriod(Configurations.SERVER_NODE);
                Calendar c = Calendar.getInstance();
                c.setTime(new SimpleDateFormat("dd/MM/yyyy")
                        .parse(userLoginBean.getLast_password_updated_date()));
                c.add(Calendar.DATE, Integer.parseInt(passwordExpirationTimePeriod));
                Date currentDatePlusOne = c.getTime();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/MM/yyyy");
                Date date1 = simpleDateFormat.parse(simpleDateFormat.format(currentDatePlusOne));
                Date date2 = simpleDateFormat.parse(simpleDateFormat.format(new Date()));
                if ((date1.compareTo(date2) > 0)) {
                    if (getService().getLoginService().varifilogin(userLoginBean)) {

                        if (Status.ACTIVE == userLoginBean.getDBuserStatus()) {
                            if (Status.ACTIVE == userLoginBean.getUserRoleStatus()) {// check user role active or not

                                sessionUserBean.setName(userLoginBean.getDBname());
                                sessionUserBean.setEmail(userLoginBean.getEmail());
                                sessionUserBean.setUserName(userLoginBean.getDBuserName());
                                sessionUserBean.setUserLevel(userLoginBean.getDBuserappCode());
                                sessionUserBean.setId(userLoginBean.getId());
                                sessionUserBean.setUserRole(userLoginBean.getUserRole());

                                HttpSession sessionPrevious = ServletActionContext.getRequest().getSession(false);
                                if (sessionPrevious != null) {
                                    sessionPrevious.removeAttribute("SessionObject");
                                    sessionPrevious.removeAttribute("profilePageidList");
                                    sessionPrevious.removeAttribute("modulePageList");
                                    sessionPrevious.removeAttribute("pageTaskList");
                                    sessionPrevious.removeAttribute("SessionHomeValues");

                                    SessionMap session = (SessionMap) ActionContext.getContext().getSession();
                                    session.invalidate();
                                    session.put("renewServletSession", null);
                                    session.remove("renewServletSession");

                                    session.entrySet();

                                }
                                TokenBean tokenBean = new TokenBean();
                                TokenFactory tokenFactory = new TokenFactory();
                                HttpSession session = ServletActionContext.getRequest().getSession(true);
                                tokenFactory.getTokenInf().generateToken(tokenBean);
                                session.setAttribute(TokenConst.SESSION_TOKEN, tokenBean.getSessionToken());
                                sessionUserBean.setCurrentSessionId(session.getId());
                                session.setAttribute("SessionObject", sessionUserBean);
                                //set user and sessionid to hashmap              
                                ServletContext sc = ServletActionContext.getServletContext();
                                HashMap<String, String> userMap = (HashMap<String, String>) sc.getAttribute(SessionVariable.USERMAP);
                                if (userMap == null) {
                                    userMap = new HashMap<String, String>();
                                }
                                userMap.put(sessionUserBean.getUserName(), session.getId());
                                sc.setAttribute(SessionVariable.USERMAP, userMap);

//                        getService().getLoginService().getHomeValues(homeValues);
//                        session.setAttribute("SessionHomeValues", homeValues);
                                profilePageidList = getService().getLoginService().getUserprofilePageidList(userLoginBean.getDBuserappCode());
                                session.setAttribute("profilePageidList", profilePageidList);

                                taskList = getService().getLoginService().getAllPageTask(userLoginBean.getDBuserappCode());
                                session.setAttribute("pageTaskList", taskList);

                                modulePageList = getService().getLoginService().getModulePageTaskByUser(userLoginBean.getDBuserappCode());
                                session.setAttribute("modulePageList", modulePageList);

                                SessionUserBean sessionUser1 = (SessionUserBean) session.getAttribute("SessionObject");

                                HttpServletRequest request1 = ServletActionContext.getRequest();
                                HttpSession session1 = request1.getSession(false);
                                SessionUserBean sessionUser = (SessionUserBean) session1.getAttribute("SessionObject");
                  
                                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, userLoginBean.getDBuserappCode(), SystemModule.USER_MANAGEMENT, TaskVarList.LOGIN, SystemMessage.USER_LOGIN_SUCCESS, null, null, null, userLoginBean.getId(), null, null, null);
                                LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_SUCCESS);

//                                if (!isStartMonitorService) {
//                                    isStartMonitorService = true;
//
//                                    new Thread(new MonitorServerStatus()).start();
//                                }
                                boolean updateRemainigPasswordAttempt = getService().getLoginService().updateRemainigPasswordAttempt(0, userLoginBean.getId());
                                if (updateRemainigPasswordAttempt == true) {
                                    return SUCCESS;
                                } else {
                                    LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_ERROR + "Failed to update Remaining password attempts");
                                    addActionError(SystemMessage.USER_LOGIN_ERROR);
                                    return LOGIN;
                                }

                            } else {
                                addActionError(SystemMessage.USER_ROLE_STATUS_NOT_ACTIVATE);
                                return LOGIN;
                            }

                        } else {
                            String msg = "";
                            if (userLoginBean.getDBuserStatus() == Status.LOCKED) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                Calendar c1 = Calendar.getInstance();
                                c1.setTime(userLoginBean.getLast_raw_update_date_time());
                                c1.add(Calendar.MINUTE, Util.getUserAccountLockOutTime(Configurations.SERVER_NODE));
                                Date time1 = c1.getTime();
                                Date parse = dateFormat.parse(dateFormat.format(time1));
                                Date parse1 = dateFormat.parse(dateFormat.format(new Date()));
                                long def = parse.getTime() - parse1.getTime();
                                long def_msg = def / (60 * 1000) % 60;
                                msg = SystemMessage.USER_LOGIN_STATUSE_LOCKED + " Please try after " + def_msg + " minites ";
                                if (def_msg <= 0) {
                                    boolean updateUserStatus = getService()
                                            .getLoginService()
                                            .updateUserStatus(Status.ACTIVE, userLoginBean.getId());
                                    if (updateUserStatus == true) {
                                        Util.insertHistoryRecord(
                                                LogTypes.TLEWEBAPP,
                                                userLoginBean.getDBuserappCode(),
                                                SystemModule.USER_MANAGEMENT,
                                                TaskVarList.LOGIN,
                                                SystemMessage.USER_LOGIN_STATUSE_UNLOCKED,
                                                null,
                                                null,
                                                null,
                                                userLoginBean.getId(),
                                                null,
                                                null,
                                                null
                                        );
//                                        loginCheck();
                                        msg = SystemMessage.USER_LOGIN_STATUSE_UNLOCKED;
                                    } else {
                                        LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_ERROR + "Failed to update User Account statuts to UNLOCKED");
                                        msg = SystemMessage.USER_LOGIN_ERROR;
                                    }
                                }

                            } else if (userLoginBean.getDBuserStatus() == Status.WAITIING_FOR_PASSWORD) {
                                ServletActionContext.getRequest().getSession(true).setAttribute("ubean", userLoginBean);
                                addActionError(SystemMessage.FIRST_LOGIN);
                                return "password-change";
                            } else if (userLoginBean.getDBuserStatus() == Status.FORGET) {

                                ServletActionContext.getRequest().getSession(true).setAttribute("ubean", userLoginBean);
                                Calendar c1 = Calendar.getInstance();
                                System.out.println(userLoginBean.getLast_password_reset_date_time());
                                c1.setTime(userLoginBean.getLast_password_reset_date_time());
                                System.out.println(Util.getTempPasswordExpirationTimePeriod(Configurations.SERVER_NODE));
                                c1.add(Calendar.HOUR_OF_DAY, Util.getTempPasswordExpirationTimePeriod(Configurations.SERVER_NODE));
                                Date time1 = c1.getTime();
                                if (new Date().compareTo(time1) > 0) {
                                    msg = "Your temperory password has expired..! Please contact admin support.!";
                                } else {
                                    return "forget-change";
                                }

                            } else {
                                msg = SystemMessage.USER_LOGIN_STATUSE_NOT_ACTIVATE;
                            }

                            addActionError(msg);
                            return LOGIN;
                        }

                    } else {
                        if (userLoginBean.getDBuserStatus() != Status.LOCKED) {

                            int remainigPasswordAttempt = getService()
                                    .getLoginService()
                                    .getRemainigPasswordAttempt(userLoginBean.getId());
                            boolean updateRemainigPasswordAttempt = getService()
                                    .getLoginService()
                                    .updateRemainigPasswordAttempt(remainigPasswordAttempt + 1, userLoginBean.getId());

                            if (updateRemainigPasswordAttempt == true) {
                                String msg = "";
                                Integer numberOfPasswordAttempts = Util.getNumberOfPasswordAttempts(Configurations.SERVER_NODE);
                                if (numberOfPasswordAttempts <= getService()
                                        .getLoginService()
                                        .getRemainigPasswordAttempt(userLoginBean.getId())) {
                                    boolean updateUserStatus = getService().getLoginService().updateUserStatus(Status.LOCKED, userLoginBean.getId());
                                    if (updateUserStatus == true) {

                                        if (getService().getLoginService().updateLastRawUpdateDateTime(userLoginBean.getId()) == true) {
                                            msg = SystemMessage.USER_LOGIN_STATUSE_LOCKED + " Please try again after " + Util.getUserAccountLockOutTime(Configurations.SERVER_NODE) + " minites ";
                                            Util.insertHistoryRecord(
                                                    LogTypes.TLEWEBAPP,
                                                    userLoginBean.getDBuserappCode(),
                                                    SystemModule.USER_MANAGEMENT,
                                                    TaskVarList.LOGIN,
                                                    SystemMessage.USER_LOGIN_STATUSE_LOCKED,
                                                    null,
                                                    null,
                                                    null,
                                                    userLoginBean.getId(),
                                                    null,
                                                    null,
                                                    null
                                            );
                                        } else {
                                            LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_ERROR + "Failed to update User Account LastRawUpdateDateTime");
                                            msg = SystemMessage.USER_LOGIN_ERROR;
                                        }
                                    } else {
                                        LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_ERROR + "Failed to update User Account statuts to LOCKED");
                                        msg = SystemMessage.USER_LOGIN_ERROR;
                                    }

                                } else {

                                    msg = SystemMessage.USER_LOGIN_FAIL + " You are left with "
                                            + (numberOfPasswordAttempts - getService()
                                            .getLoginService()
                                            .getRemainigPasswordAttempt(userLoginBean.getId())) + " attempts.!";
                                    System.out.println("you have Remainig " + (numberOfPasswordAttempts - getService()
                                            .getLoginService()
                                            .getRemainigPasswordAttempt(userLoginBean.getId())) + " Password attempts");
                                    Util.insertHistoryRecord(
                                            LogTypes.TLEWEBAPP,
                                            userLoginBean.getDBuserappCode(),
                                            SystemModule.USER_MANAGEMENT,
                                            TaskVarList.LOGIN,
                                            "Invallid login attempt .! " + msg,
                                            null,
                                            null,
                                            null,
                                            userLoginBean.getId(),
                                            null,
                                            null,
                                            null
                                    );

                                }
                                addActionError(msg);
                                return LOGIN;
                            } else {
                                addActionError(SystemMessage.USER_LOGIN_ERROR);
                                return LOGIN;
                            }
                        } else {
                            String msg = "";
                            if (userLoginBean.getDBuserStatus() == Status.LOCKED) {
                                SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm");
                                Calendar c1 = Calendar.getInstance();
                                c1.setTime(userLoginBean.getLast_raw_update_date_time());
                                c1.add(Calendar.MINUTE, Util.getUserAccountLockOutTime(Configurations.SERVER_NODE));
                                Date time1 = c1.getTime();
                                Date parse = dateFormat.parse(dateFormat.format(time1));
                                Date parse1 = dateFormat.parse(dateFormat.format(new Date()));
                                long def = parse.getTime() - parse1.getTime();
                                long def_msg = def / (60 * 1000) % 60;
                                msg = SystemMessage.USER_LOGIN_STATUSE_LOCKED + " Please try after " + def_msg + " minites ";
                                if (def_msg <= 0) {
                                    boolean updateUserStatus = getService()
                                            .getLoginService()
                                            .updateUserStatus(Status.ACTIVE, userLoginBean.getId());
                                    if (updateUserStatus == true) {
                                        boolean updateRemainigPasswordAttempt = getService().getLoginService().updateRemainigPasswordAttempt(0, userLoginBean.getId());
                                        if (updateRemainigPasswordAttempt) {
                                            Util.insertHistoryRecord(
                                                    LogTypes.TLEWEBAPP,
                                                    userLoginBean.getDBuserappCode(),
                                                    SystemModule.USER_MANAGEMENT,
                                                    TaskVarList.LOGIN,
                                                    SystemMessage.USER_LOGIN_STATUSE_UNLOCKED,
                                                    null,
                                                    null,
                                                    null,
                                                    userLoginBean.getId(),
                                                    null,
                                                    null,
                                                    null
                                            );
                                            msg = SystemMessage.USER_LOGIN_STATUSE_UNLOCKED;
                                        } else {
                                            msg = SystemMessage.USER_LOGIN_ERROR;
                                        }

//                                        loginCheck();
                                    } else {
                                        LogFileCreator.writeInforToLog(SystemMessage.USER_LOGIN_ERROR + "Failed to update user account statuts to UNLOCKED");
                                        msg = SystemMessage.USER_LOGIN_ERROR;
                                    }
                                }

                            } else {
                                msg = SystemMessage.USER_LOGIN_STATUSE_NOT_ACTIVATE;
                            }

                            addActionError(msg);
                            return LOGIN;

                        }
                    }
                } else {
                    ServletActionContext.getRequest().getSession(true).setAttribute("ubean", userLoginBean);
                    addActionError(SystemMessage.USER_PASSWORD_EXPIRED);
                    return "password-change";
                }
            }

        } catch (org.hibernate.exception.JDBCConnectionException hib) {
            System.out.println("-------------- connection ------------------");
            new HibernateInit().initialize();
        } catch (Exception ex) {
            ex.printStackTrace();
            addActionError(SystemMessage.USER_LOGIN_ERROR);
            LogFileCreator.writeErrorToLog(ex);
            return LOGIN;
        }
        return LOGIN;
    }

    public OperationManageFactory getServiceOpr() {
        return new OperationManageFactory();
    }

    @Override
    public UserLoginBean getModel() {
        userLoginBean.setServerNode(Integer.toString(Configurations.SERVER_NODE));
        return userLoginBean;
    }

    public String homeFunction() {
        return SUCCESS;
    }

    public String chartData() {
        try {
            getService().getLoginService().getProcessingData(userLoginBean);
            userLoginBean.setServiceStatus(Boolean.toString(Configurations.SERVER_STATUS));
            userLoginBean.setServerNode(Integer.toString(Configurations.SERVER_NODE));

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return SUCCESS;
    }

    public String FiltertData() {
        try {
            getService().getLoginService().getFilterData(userLoginBean);
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return SUCCESS;
    }

    public String logoutFunction() {
        System.out.println("logout-------");
        try {
            HttpSession session = ServletActionContext.getRequest().getSession(false);

//            executor.shutdown();
            if (session != null) {
                if (userLoginBean.getMessage() != null && !userLoginBean.getMessage().isEmpty()) {
                    String message = userLoginBean.getMessage();
                    if (message.equals("error1")) {
                        addActionError("Access denied. Please login again.");
                    } else if (message.equals("error2")) {
                        addActionError("You have been logged another mechine.");
                    } else if (message.equals("success1")) {
                        addActionMessage("Your password changed successfully. Please login with the new password.");
                    } else if (message.equals("csrfError")) {
                        addActionError("Action terminated. Failed to validate access token. Please Login again.");
                    }
                }
                SessionUserBean su = (SessionUserBean) session.getAttribute("SessionObject");
                if (su != null) {
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, su.getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.LOGOUT, SystemMessage.USER_LOGOUT_SUCCESS, null, null, null, su.getId(), null, null, null);
                    LogFileCreator.writeInforToLog(SystemMessage.USER_LOGOUT_SUCCESS);
                    session.removeAttribute("SessionObject");
                    session.removeAttribute("profilePageidList");
                    session.removeAttribute("modulePageList");
                    session.removeAttribute("pageTaskList");
                    session.removeAttribute("SessionHomeValues");
                    SessionMap s = (SessionMap) ActionContext.getContext().getSession();
                    s.invalidate();
                    s.put("renewServletSession", null);
                    s.remove("renewServletSession");
                    s.entrySet();
//                    session.invalidate();
//                    session = null;
                } else {
                    addActionError("Session timeout");
                }

            } else {
                addActionError("Session timeout");
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        userLoginBean.setServerNode(Integer.toString(Configurations.SERVER_NODE));
        userLoginBean.setDatabase(Configurations.DB_CONF.toUpperCase());
        return LOGIN;
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

    public String removeSessionAttribute() {
        System.out.println("incative - session time out");
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        session.removeAttribute("SessionObject");
        return "remove";
    }
}
