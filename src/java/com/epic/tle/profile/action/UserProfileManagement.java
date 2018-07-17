 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.profile.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleUserProfile;
import com.epic.tle.profile.bean.UserProfileBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.profile.service.UserProfileFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author chalaka_n
 */
public class UserProfileManagement extends ActionSupport implements AccessControlService, ModelDriven<UserProfileInputBean> {

    UserProfileInputBean inputBean = new UserProfileInputBean();
    HttpServletRequest req = ServletActionContext.getRequest();
    UserProfileFactory service;
    SessionUserBean sub;
    EpicTleUserProfile usrProfile = new EpicTleUserProfile();

    public UserProfileFactory getService() {
        return new UserProfileFactory();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) req.getSession().getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) req.getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    private static List<String> finalselectpagelist = new ArrayList<String>();

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.REGISTER_USER_PROFILES;
        inputBean.setPageCode(page);
        String task = null;
        if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("view".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Load".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("loadPages".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("loadTasks".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("download".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("upload".equals(method)) {
            task = TaskVarList.UPLOAD;
        } else if ("confirm".equals(method)) {
            task = TaskVarList.CONFIRM;
        } else if ("reject".equals(method)) {
            task = TaskVarList.REJECT;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        }

        if ("execute".equals(method)) {
            status = !inputBean.isView();
        } else {
            HttpSession session = req.getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

    public String loadPages() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getProfileService().getmodulePagemap(inputBean);

        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "loadmodulepages";
    }

    public String loadTasks() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getProfileService().getPageTaskmap(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "loadmodulepages";
    }

    @Override
    public UserProfileInputBean getModel() {
        try {
            getService().getProfileService().getmodulemap(inputBean);
            getService().getProfileService().getPagePath(inputBean.getPageCode(), inputBean);
//            getService().getProfileService().getmodulePagemap(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    private boolean applyUserPrivileges() {
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.REGISTER_USER_PROFILES, req);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setView(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setUpdate(false);
                }
            }
        }

        return true;
    }

    public String execute() {
        return SUCCESS;
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<UserProfileBean> dataList = null;

        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
            }

            dataList = getService().getProfileService().loadSearchUserProfile(inputBean, orderBy, from, to);
            if (!dataList.isEmpty()) {
                records = dataList.get(0).getFullCount();
                inputBean.setRecords(records);
                inputBean.setGridModel(dataList);
                int total = (int) Math.ceil((double) records / (double) rows);
                inputBean.setTotal(total);
            } else {
                inputBean.setRecords(0L);
                inputBean.setTotal(0);
            }
//            if(inputBean.getSearchName() != null){
//                  LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);
//            }

        } catch (Exception e) {
            addActionError(SystemMessage.USRP_ROLE_INVALID_SEARCH);
            LogFileCreator.writeErrorToLog(e);
        }
        return "list";
    }

    private boolean doValidation(UserProfileInputBean bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getProfilename() == null || bean.getProfilename().isEmpty()) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_NAME_EMPTY);
                return ok;
            } else if (!Util.validateString(bean.getProfilename())) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_NAME_INVALID);
                return ok;
            } else if (getService().getProfileService().alredyInsertProfile(inputBean.getProfilename())) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_ALREADY);
                return ok;
            } else if (bean.getTaskString().isEmpty() || bean.getTaskString().split("\\|").length < 0) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_NOT_SELECT_PAGES);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    public String Add() {
        inputBean.setToken(getSessionToken());
        try {
            if (doValidation(inputBean)) {
                int profileID = getService().getProfileService().insertUserProfile(inputBean);
                int insertDefaultUserProfilePrivilages = getService().getProfileService().insertDefaultUserProfilePrivilages(profileID);
                getService().getProfileService().insertUserProfileModule(profileID, inputBean);

                usrProfile.setCode(profileID);
//                addActionMessage(SystemMessage.USRP_PROFILE_ADD_SUCCESS);
                Util.insertHistoryRecord(
                        LogTypes.TLEWEBAPP,
                        getSub().getUserLevel(),
                        SystemModule.USER_PROFILE_MANAGEMENT,
                        TaskVarList.ADD,
                        SystemMessage.USRP_PROFILE_ADD_SUCCESS + " with name : " + inputBean.getName() + " and id : " + profileID,
                        null, null, null,
                        getSub().getId(),
                        SystemSection.REGISTER_USER_PROFILES,
                        null, null);
                LogFileCreator.writeInforToLog(SystemMessage.USRP_PROFILE_ADD_SUCCESS);
                inputBean.setSuccess(true);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_ADD_SUCCESS);
            }
//            finalselectpagelist.removeAll(finalselectpagelist);
        } catch (Exception e) {
            inputBean.setSuccess(false);
            inputBean.setMessage(SystemMessage.USRP_PROFILE_ADD_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }

        return "add";

    }

    public String Load() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getProfileService().getUpdateData(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "load";
    }

    public String Update() {
        inputBean.setToken(getSessionToken());
        try {
            boolean ok = false;
            if (doUpdateValidation(inputBean)) {
                usrProfile.setCode(inputBean.getUpProfileID());
                String oldVal = Util.getOldorNewVal(usrProfile, "wu.code ='" + usrProfile.getCode() + "'");
                ok = getService().getProfileService().updateUserProfile(inputBean);

                if (ok == true) {
                    String newVal = Util.getOldorNewVal(usrProfile, "wu.code ='" + usrProfile.getCode() + "'");
                    inputBean.setSuccess(true);
                    inputBean.setMessage(SystemMessage.USRP_ROLE_UPDATED);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_PROFILE_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.USRP_ROLE_UPDATED, null, null, null, getSub().getId(), SystemSection.REGISTER_USER_PROFILES, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.USRP_ROLE_UPDATED);
                }
            }
        } catch (Exception ex) {
            inputBean.setSuccess(true);
            inputBean.setMessage(SystemMessage.USRP_ROLE_UPDATED_FAIL);
            LogFileCreator.writeErrorToLog(ex);
        }

        return "update";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            boolean ok = false;
            usrProfile.setCode(inputBean.getProfileID());
            String oldVal = Util.getOldorNewVal(usrProfile, "wu.code ='" + usrProfile.getCode() + "'");
            if (getService().getProfileService().isAnyUserUsingThisProfile(inputBean)) {
                inputBean.setMessage(SystemMessage.USRP_PROF_ALREADY_USED);
                return "delete";
            } else {
                ok = getService().getProfileService().userRoleDelete(inputBean);
            }

            if (ok == true) {
                inputBean.setMessage(SystemMessage.USRP_PROF_DELETED);
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_PROFILE_MANAGEMENT, TaskVarList.DELETE, SystemMessage.USRP_PROF_DELETED, null, null, null, getSub().getId(), SystemSection.REGISTER_USER_PROFILES, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.USRP_PROF_DELETED);

            } else {
                inputBean.setMessage(SystemMessage.USRP_PROF_DELETED_FAIL);
            }
            inputBean.setDelsuccess("1");

        } catch (Exception e) {
            e.printStackTrace();
            inputBean.setMessage(SystemMessage.USRP_PROF_DELETED_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }

        return "delete";
    }

    private boolean doUpdateValidation(UserProfileInputBean bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getUpName() == null || bean.getUpName().isEmpty()) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_NAME_EMPTY);
                return ok;
            } else if (!Util.validateString(bean.getUpName())) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_NAME_INVALID);
                return ok;
            } else if (inputBean.getUpStatus() == -1) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.USRP_PROFILE_STATUS);
                return ok;
            } //                else if (getService().getProfileService().alredyInsertProfile(inputBean.getUpName())) {
            //                inputBean.setSuccess(false);
            //                inputBean.setMessage(SystemMessage.USRP_PROFILE_ALREADY);
            //                return ok;
            //            } 
            //            else if (bean.getTaskString().isEmpty() || bean.getTaskString().split("\\|").length < 0) {
            //                inputBean.setSuccess(false);
            //                inputBean.setMessage(SystemMessage.USRP_PROFILE_NOT_SELECT_PAGES);
            //                return ok;
            //            } 
            else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

}
