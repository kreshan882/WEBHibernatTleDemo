/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleSmsProfile;
import com.epic.tle.servermanager.bean.SMSProfileBean;
import com.epic.tle.servermanager.service.SMSProfileFactory;
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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author ridmi_g
 */
public class SMSProfile extends ActionSupport implements AccessControlService, ModelDriven<SMSProfileBean> {

    private SMSProfileBean inputBean = new SMSProfileBean();
    SessionUserBean session;
    SMSProfileFactory service;
    EpicTleSmsProfile epicTleSmsProfile = new EpicTleSmsProfile();

    public SMSProfileFactory getService() {
        return new SMSProfileFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {

        boolean status;
        applyUserPrivileges();
        String page = PageVarList.SMS_PROFILE;
        inputBean.setPageCode(page);
        String task = null;
        if ("list".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Load".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("AssignInfo".equals(method)) {
            task = TaskVarList.VIEW;
        }else if ("FilterSMS".equals(method)){
             task = TaskVarList.VIEW;
        }

        if ("execute".equals(method)) {
            status = !inputBean.isView();
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;

    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.SMS_PROFILE, request);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setUpdate(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                }
            }
        }
        return true;
    }

    @Override
    public SMSProfileBean getModel() {
        try {
            inputBean.setUpBinStatusMap(Util.getStatusValues(0, 2));
            getService().getSMSProfileInf().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean;
    }

    public String Add() {
//        System.out.println("add");
        inputBean.setToken(getSessionToken());
        try {

            if (doValidation(inputBean)) {

                boolean ok = getService().getSMSProfileInf().insertSMSProfile(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.SMS_PROFILE_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.SERVER_MANAGEMENT,
                            TaskVarList.ADD,
                            inputBean.getGn() + " -> " + SystemMessage.SMS_PROFILE_ADD_SUCCESS,
                            null, null,
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.SMS_PROFILE,
                            null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.SMS_PROFILE_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    private boolean doValidation(SMSProfileBean bean) throws Exception {
        boolean ok = false;
        try {
            String SMSprofile = getService().getSMSProfileInf().GetResult(inputBean);
            if (SMSprofile != null) {
                addActionError(SystemMessage.SMS_PROFILE_ALREADY_NAME);
                return ok;
            } else if (bean.getGn() == null || bean.getGn().isEmpty()) {
                addActionError(SystemMessage.SMS_PROFILE_INVALID_PROFILE_NAME);
                return ok;
            } else if (!Util.validateString(bean.getGn())) {
                addActionError(SystemMessage.SMS_PROFILE_INVALID_NAME);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    public String list() {

        inputBean.setToken(getSessionToken());
        List<SMSProfileBean> dataList;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = "order by wu." + inputBean.getSidx() + " " + inputBean.getSord();
               
            }

            dataList = getService().getSMSProfileInf().loadSMSProfile(inputBean, rows, from, orderBy);

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
            // LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String Load() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getSMSProfileInf().getUpdateData(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "load";
    }

    public String update() {
        inputBean.setToken(getSessionToken());
        boolean ok;
        try {
            if (doValidationUpdate(inputBean)) {
                
                String oldVal = Util.getOldorNewVal(epicTleSmsProfile, "wu.smsProfileId ='" + inputBean.getUpProfileID()+ "'");
               
                ok = getService().getSMSProfileInf().updateprofile(inputBean);
                if (ok == true) {
                    String newVal = Util.getOldorNewVal(epicTleSmsProfile, "wu.smsProfileId ='" + inputBean.getUpProfileID() + "'");
                    
                    addActionMessage(SystemMessage.SMS_PROFILE_UPDATE_SUCCESS);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.SMS_PROFILE_UPDATE_SUCCESS, null, null, null, getSession().getId(), SystemSection.SMS_PROFILE, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.SMS_PROFILE_UPDATE_SUCCESS);
                } else {
                    addActionError(SystemMessage.SMS_PROFILE_UPDATED_ERROR);
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    private boolean doValidationUpdate(SMSProfileBean smsProfileBean) throws Exception {

        boolean ok = false;
        try {
            if (smsProfileBean.getUpName() == null || smsProfileBean.getUpName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_NAME);
                return ok;
            }
            if (!Util.validateString(smsProfileBean.getUpName())) {
                addActionError(SystemMessage.USER_INVALID_NAME);
                return ok;
            }
            if (smsProfileBean.getUpStatus() == -1) {
                addActionError(SystemMessage.USER_EMPTY_USERTYPE);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
          
            String oldVal = Util.getOldorNewVal(epicTleSmsProfile, "wu.smsProfileId ='" + inputBean.getDbinId() + "'");
            if (getService().getSMSProfileInf().deleteProfile(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.DELETE, SystemMessage.SMS_PROFILE_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.SMS_PROFILE, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.SMS_PROFILE_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.SMS_PROFILE_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.SMS_PROFILE_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.SMS_PROFILE_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

    public String AssignInfo() {
        Integer id = inputBean.getId();
        inputBean.setToken(getSessionToken());
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        session.setAttribute("smsprofileid", id);
        return "Assign";

    }
    public String FilterSMS(){
         Integer id = inputBean.getId();
        inputBean.setToken(getSessionToken());
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        session.setAttribute("filtersms", id);

        return "Filter";
    }

}
