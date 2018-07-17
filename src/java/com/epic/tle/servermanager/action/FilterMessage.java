/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleSmsFilter;
import com.epic.tle.mapping.EpicTleSmsProfile;
import com.epic.tle.mapping.EpicTleSmsProfileInfo;
import com.epic.tle.servermanager.bean.FilterMessageBean;
import com.epic.tle.servermanager.bean.SMSInfoBean;
import com.epic.tle.servermanager.service.FilterMessageFactory;
import com.epic.tle.servermanager.service.SMSInfoFactory;
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
import static com.opensymphony.xwork2.Action.SUCCESS;
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
public class FilterMessage extends ActionSupport implements AccessControlService, ModelDriven<FilterMessageBean> {

    private FilterMessageBean inputBean = new FilterMessageBean();
    SessionUserBean session;
    EpicTleSmsFilter epicTleSmsFilter = new EpicTleSmsFilter();
//    EpicTleSmsProfile epicTleSmsProfile = new EpicTleSmsProfile();

    public FilterMessageFactory getService() {
        return new FilterMessageFactory();
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
        String task = null;
        if ("list".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Load".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("AssignBin".equals(method)) {
            task = TaskVarList.VIEW;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.SMS_PROFILE, request);
        getInputBean().setAdd(true);
        getInputBean().setDelete(true);
        getInputBean().setView(true);
        getInputBean().setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    getInputBean().setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    getInputBean().setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    getInputBean().setView(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    getInputBean().setUpdate(false);
                }
            }
        }
        return true;
    }

    public FilterMessageBean getInputBean() {
        return inputBean;
    }

    public void setInputBean(FilterMessageBean inputBean) {
        this.inputBean = inputBean;
    }

    @Override
    public FilterMessageBean getModel() {
        return getInputBean();
    }

    public String list() {

        inputBean.setToken(getSessionToken());
        List<FilterMessageBean> dataList;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = inputBean.getSord();
            }

            dataList = getService().getFilterMessageInf().loadFilterSMS(inputBean, rows, from, orderBy);

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

    public String Add() {
        inputBean.setToken(getSessionToken());
        try {

            if (doValidation(inputBean)) {

                boolean ok = getService().getFilterMessageInf().insertSMS(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.SMS_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.SERVER_MANAGEMENT,
                            TaskVarList.ADD,
                            inputBean.getMessage()+ " -> " + SystemMessage.SMS_ADD_SUCCESS,
                            null, null,
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.SMS_PROFILE,
                            null, null
                    );
                } else {
                    addActionError(SystemMessage.SMS_REGISTRATION_FAIL);
                }
            }

        } catch (Exception e) {
            addActionError(SystemMessage.SMS_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }

        return "Add";

    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
         
       
            String oldVal = Util.getOldorNewVal(epicTleSmsFilter, "wu.id ='" + inputBean.getId()+ "' ");

            if (getService().getFilterMessageInf().deleteMessage(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.DELETE, SystemMessage.SMS_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.SMS_PROFILE, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.SMS_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.SMS_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.SMS_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.SMS_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

    private boolean doValidation(FilterMessageBean bean) throws Exception {
//        System.out.println("messge "+bean.getMessage());
        boolean ok = false;
        try {
             String msg = getService().getFilterMessageInf().GetResult(inputBean);

             if(msg != null){
                 addActionError(SystemMessage.SMS_ALREADY_DEFINED);
                return ok;
             }else if (bean.getMessage() == null || bean.getMessage().trim().isEmpty()) {
                addActionError(SystemMessage.SMS_EMPTY);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        return ok;
    }

}
