/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.userManagement.bean.UserVerificationBean;
import com.epic.tle.userManagement.service.UserVerificationFactory;
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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author danushka_r
 */
public class UserVerification extends ActionSupport implements ModelDriven<UserVerificationBean>, AccessControlService {

    UserVerificationBean inputBean = new UserVerificationBean();
    UserVerificationFactory service;
    EpicTleUser epicTleUser = new EpicTleUser();
//    SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");

    public UserVerificationFactory getService() {
        return new UserVerificationFactory();
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String list() {
        List<UserVerificationBean> dataList;
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
            dataList = getService().getUserVerificationInf().loadUsers(inputBean, rows, from, orderBy);

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
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String active() {
        boolean test = false;
        try {
                epicTleUser.setUsername(inputBean.getUserName());
                String oldVal=Util.getOldorNewVal(epicTleUser, "wu.username ='"+inputBean.getUserName()+"'");
            if (test = getService().getUserVerificationInf().activeUser(inputBean.getUserName())) {
                String newVal=Util.getOldorNewVal(epicTleUser, "wu.username ='"+inputBean.getUserName()+"'");
                inputBean.setCmessage(SystemMessage.USER_CONFIRM_CONFIRMATION_SUCCESS);
                inputBean.setIsConfirmed(true);
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.CONFIRM, SystemMessage.USER_CONFIRM_CONFIRMATION_SUCCESS, null, null, null, getSub().getId(),SystemSection.REGISTER_USER,oldVal,newVal);
                LogFileCreator.writeInforToLog(SystemMessage.USER_CONFIRM_CONFIRMATION_SUCCESS);
            }

        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
            inputBean.setCmessage(SystemMessage.USER_CONFIRM_CONFIRMATION_ERROR);
            inputBean.setIsConfirmed(false);

        }
        return "active";
    }

    public String delete() {
        try {
             epicTleUser.setUsername(inputBean.getUserName());
             String oldVal=Util.getOldorNewVal(epicTleUser, "wu.username ='"+inputBean.getUserName()+"'");
            if (getService().getUserVerificationInf().deleteUser(inputBean.getUserName())) {
                inputBean.setDmessage(SystemMessage.USER_DELETED);
                inputBean.setIsDeleted(true);

                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.DELETE, SystemMessage.USER_DELETED, null, null, null, getSub().getId(),SystemSection.REGISTER_USER,oldVal,null);
                LogFileCreator.writeInforToLog(SystemMessage.USER_DELETED);
            }

        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
            inputBean.setDmessage(SystemMessage.USER_DELETED_ERROR);
            inputBean.setIsDeleted(false);
        }
        return "delete";
    }

    @Override
    public UserVerificationBean getModel() {
        return inputBean;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.USER_VERIFICATION;
        String task = null;
        if ("list".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("active".equals(method)) {
            task = TaskVarList.CONFIRM;
        } else if ("delete".equals(method)) {
            task = TaskVarList.DELETE;
        }

        if ("execute".equals(method)) {
            status = true;
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.USER_VERIFICATION, request);
        inputBean.setList(true);
        inputBean.setActive(true);
        inputBean.setDelete(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setList(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setActive(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setDelete(false);
                }
            }
        }
        return true;
    }

}
