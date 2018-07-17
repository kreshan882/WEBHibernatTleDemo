/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleStatusofresponse;
import com.epic.tle.servermanager.bean.ResponseConfigurationBean;
import com.epic.tle.servermanager.service.ResponseConfFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
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
 * @author dimuthu_h
 */
public class ResponseConfiguration extends ActionSupport implements ModelDriven<ResponseConfigurationBean>, AccessControlService {

    ResponseConfigurationBean inputBean = new ResponseConfigurationBean();
    ResponseConfFactory service;
    SessionUserBean sub;
    EpicTleStatusofresponse epicTleStatusofresponse=new EpicTleStatusofresponse();

    public ResponseConfFactory getService() {
        return new ResponseConfFactory();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }
    public String ResetData() {
        inputBean.setToken(getSessionToken());
        try {
            inputBean = getService().getSessionService().viewResponseConfDetails(inputBean);
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }

        return "reset";
    }

    public String Update() {
        inputBean.setToken(getSessionToken());
        try {
                String oldVal=Util.getOldorNewVal(epicTleStatusofresponse,"1=1");
            if (getService().getSessionService().updateResponseConf(inputBean)) {
                String newVal=Util.getOldorNewVal(epicTleStatusofresponse,"1=1");
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.RESPONSE_CONF_UPDATED, null, null, null, getSub().getId(),SystemSection.RESPONSE_FIELD_CONFIGURATIONS,oldVal,newVal);
                LogFileCreator.writeInforToLog(SystemMessage.RESPONSE_CONF_UPDATED);
                addActionMessage(SystemMessage.RESPONSE_CONF_UPDATED);
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.RESPONSE_CONF_UPDATED_ERROR);
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        return "update";
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public ResponseConfigurationBean getModel() {
        inputBean.getF_02_Map().put("1", "Enable");
        inputBean.getF_02_Map().put("2", "Disable");
        inputBean.getF_03_Map().put("1", "Enable");
        inputBean.getF_03_Map().put("2", "Disable");
        inputBean.getF_04_Map().put("1", "Enable");
        inputBean.getF_04_Map().put("2", "Disable");
        inputBean.getF_11_Map().put("1", "Enable");
        inputBean.getF_11_Map().put("2", "Disable");
        inputBean.getF_12_Map().put("1", "Enable");
        inputBean.getF_12_Map().put("2", "Disable");
        inputBean.getF_13_Map().put("1", "Enable");
        inputBean.getF_13_Map().put("2", "Disable");
        inputBean.getF_14_Map().put("1", "Enable");
        inputBean.getF_14_Map().put("2", "Disable");
        inputBean.getF_22_Map().put("1", "Enable");
        inputBean.getF_22_Map().put("2", "Disable");
        inputBean.getF_23_Map().put("1", "Enable");
        inputBean.getF_23_Map().put("2", "Disable");
        inputBean.getF_24_Map().put("1", "Enable");
        inputBean.getF_24_Map().put("2", "Disable");
        inputBean.getF_25_Map().put("1", "Enable");
        inputBean.getF_25_Map().put("2", "Disable");
        inputBean.getF_35_Map().put("1", "Enable");
        inputBean.getF_35_Map().put("2", "Disable");
        inputBean.getF_37_Map().put("1", "Enable");
        inputBean.getF_37_Map().put("2", "Disable");
        inputBean.getF_38_Map().put("1", "Enable");
        inputBean.getF_38_Map().put("2", "Disable");
        inputBean.getF_39_Map().put("1", "Enable");
        inputBean.getF_39_Map().put("2", "Disable");
        inputBean.getF_41_Map().put("1", "Enable");
        inputBean.getF_41_Map().put("2", "Disable");
        inputBean.getF_42_Map().put("1", "Enable");
        inputBean.getF_42_Map().put("2", "Disable");
        inputBean.getF_45_Map().put("1", "Enable");
        inputBean.getF_45_Map().put("2", "Disable");
        inputBean.getF_47_Map().put("1", "Enable");
        inputBean.getF_47_Map().put("2", "Disable");
        inputBean.getF_48_Map().put("1", "Enable");
        inputBean.getF_48_Map().put("2", "Disable");
        inputBean.getF_52_Map().put("1", "Enable");
        inputBean.getF_52_Map().put("2", "Disable");
        inputBean.getF_54_Map().put("1", "Enable");
        inputBean.getF_54_Map().put("2", "Disable");
        inputBean.getF_55_Map().put("1", "Enable");
        inputBean.getF_55_Map().put("2", "Disable");
        inputBean.getF_60_Map().put("1", "Enable");
        inputBean.getF_60_Map().put("2", "Disable");
        inputBean.getF_61_Map().put("1", "Enable");
        inputBean.getF_61_Map().put("2", "Disable");
        inputBean.getF_62_Map().put("1", "Enable");
        inputBean.getF_62_Map().put("2", "Disable");
        inputBean.getF_63_Map().put("1", "Enable");
        inputBean.getF_63_Map().put("2", "Disable");
        inputBean.getF_49_Map().put("1", "Enable");
        inputBean.getF_49_Map().put("2", "Disable");

        try {
            inputBean = getService().getSessionService().viewResponseConfDetails(inputBean);
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }

        return inputBean;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.RESPONSE_CONFIGURATIONS;
        inputBean.setPageCode(page);
        String task = null;

        if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("downloadpdf".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("XSLcreat".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("ResetData".equals(method)) {
            task = TaskVarList.UPDATE;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.RESPONSE_CONFIGURATIONS, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setView(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                }
            }
        }

        return true;
    }

}
