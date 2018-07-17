/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.action;

import com.epic.tle.host.bean.NIIConfigBean;
import com.epic.tle.host.service.RegisterNIIConfigFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleNii;
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
 * @author thilina_t
 */
public class NIIAction extends ActionSupport implements AccessControlService, ModelDriven<NIIConfigBean> {

    RegisterNIIConfigFactory service;
    NIIConfigBean inputBean = new NIIConfigBean();
    SessionUserBean session;

    public RegisterNIIConfigFactory getService() {
        return new RegisterNIIConfigFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.CHANNEL_MANAGEMENT;
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
        } else if ("Assign".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("LoadDropdown".equals(method)) {
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

    @Override
    public NIIConfigBean getModel() {

        try {
            getService().getRegisterNIIConfigInf().getPagePath(inputBean.getPageCode(), inputBean);
            inputBean.setTlestatusMap(getService().getRegisterNIIConfigInf().getStatusValues(0, 2));
        } catch (Exception e) {
        }

        return inputBean;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.NII_CONFIGURATION, request);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setView(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setUpdate(false);
                }
            }
        }
        return true;
    }

    public String list() {
        inputBean.setToken(getSessionToken());
        List<NIIConfigBean> dataList;
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

            dataList = getService().getRegisterNIIConfigInf().loadNII(inputBean, rows, from, orderBy);

            if (!dataList.isEmpty()) {
                System.out.println("not empty ----------------");
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

    private boolean doValidation(NIIConfigBean bean) throws Exception {
        boolean ok = false;

        try {
            String Channel = getService().getRegisterNIIConfigInf().GetResult(inputBean);

            if (bean.getNii() == null || bean.getNii().isEmpty()) {
                addActionError(SystemMessage.NII_GROUP_INVALID_BLANK_NAME);
                return ok;
            } else if (bean.getMapNii() == null || bean.getMapNii().isEmpty()) {
                addActionError(SystemMessage.NII_GROUP_INVALID_BLANK_DESCRIPTION);
                return ok;
            } else if (!Util.validateNUMBER(bean.getNii())) {
                addActionError(SystemMessage.NII_GROUP_NUMBER);
                return ok;
            } else if (!Util.validateNUMBER(bean.getMapNii())) {
                addActionError(SystemMessage.NII_GROUP_MAP_NII_NUMBER);
                return ok;
            } else if (Channel != null) {
                addActionError(SystemMessage.NII_ALREADY_DEFINE + " : " + Channel);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    private boolean doValidationUpdate(NIIConfigBean bean) throws Exception {
        boolean ok = false;
        try {
            String Channel = getService().getRegisterNIIConfigInf().GetResult(inputBean);
            if (bean.getUpniiConfName() == null || bean.getUpniiConfName().isEmpty()) {
                addActionError(SystemMessage.NII_GROUP_INVALID_BLANK_NAME);
                return ok;
            } else if (bean.getUpniiConfDes() == null || bean.getUpniiConfDes().isEmpty()) {
                addActionError(SystemMessage.NII_GROUP_INVALID_BLANK_DESCRIPTION);
                return ok;
            } else if (inputBean.getUpChannel() == -1) {
                addActionError(SystemMessage.NII_GROUP_INVALID_CHANNEL);
                return ok;
            } else if (inputBean.getUpStatus() == -1) {
                addActionError(SystemMessage.NII_GROUP_INVALID_CHANNEL);
                return ok;
            } else if (!Util.validateString(bean.getUpniiConfName())) {
                addActionError(SystemMessage.NII_GROUP_INVALID_NAME);
                return ok;
            } else if (!Util.validateString(bean.getUpniiConfDes())) {
                addActionError(SystemMessage.NII_GROUP_INVALID_NAME);
                return ok;
            } else if (Channel != null) {
                addActionError(SystemMessage.NII_ALREADY_DEFINE + " " + Channel);
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
                boolean ok = getService().getRegisterNIIConfigInf().insertNIIGroup(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.NII_GROUP_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.HOST_MANAGEMENT,
                            TaskVarList.ADD + "",
                            SystemMessage.NII_GROUP_ADD_SUCCESS + " - { NII :" + inputBean.getNii() + ", Map NII :" + inputBean.getMapNii() + " }",
                            null, null, null,
                            getSession().getId(),
                            SystemSection.CHANNEL__MANAGEMENT,
                            null, null);
                } else {
                    addActionError(SystemMessage.NII_GROUP_REGISTRATION_FAIL);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.HOST_MANAGEMENT,
                            TaskVarList.ADD + "",
                            SystemMessage.NII_GROUP_REGISTRATION_FAIL,
                            null, null, null,
                            getSession().getId(),
                            SystemSection.CHANNEL__MANAGEMENT,
                            null, null);
                }
            }
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    public String Load() {
        inputBean.setToken(getSessionToken());
        try {

            getService().getRegisterNIIConfigInf().getUpdateData(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "load";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal = Util.getOldorNewVal(new EpicTleNii(), "wu.id ='" + inputBean.getId() + "'");
            if (getService().getRegisterNIIConfigInf().deleteNII(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.DELETE, SystemMessage.NII_GROUP_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.CHANNEL__MANAGEMENT, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.NII_GROUP_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.NII_GROUP_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.NII_GROUP_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.NII_GROUP_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

    public String Assign() {
        inputBean.setToken(getSessionToken());
        return "Assign";
    }

    public String LoadDropdown() {
        inputBean.setToken(getSessionToken());
        try {
        } catch (Exception ex) {
            ex.printStackTrace();
            addActionError(SystemMessage.BLOCK_BIN_UPDATED_ERROR);
            LogFileCreator.writeErrorToLog(ex);
        }
        return "drop";

    }

}
