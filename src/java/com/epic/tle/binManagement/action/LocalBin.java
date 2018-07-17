/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.action;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.binManagement.bean.LocalBinBean;
import com.epic.tle.binManagement.service.LocalBinFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleLocalbin;
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
public class LocalBin extends ActionSupport implements AccessControlService, ModelDriven<LocalBinBean> {

    LocalBinBean inputBean = new LocalBinBean();
    LocalBinFactory service;
    SessionUserBean session;
    EpicTleLocalbin epicTleLocalbin = new EpicTleLocalbin();

    public LocalBinFactory getService() {
        return new LocalBinFactory();
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
        String page = PageVarList.LOCAL_BIN_PROFILE;
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
        } else if ("AssignBin".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("SystemBlockBinlist".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.LOCAL_BIN_PROFILE, request);
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
    public LocalBinBean getModel() {

        try {
            getService().getLocalBinInf().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean;

    }

    public String list() {
        inputBean.setToken(getSessionToken());
        inputBean.setCategory_code(1);
        List<LocalBinBean> dataList;
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

            dataList = getService().getLocalBinInf().loadLocalBin(inputBean, rows, from, orderBy);

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

    public String SystemBlockBinlist() {
        inputBean.setToken(getSessionToken());
        inputBean.setCategory_code(2);
        List<LocalBinBean> dataList;
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

            dataList = getService().getLocalBinInf().loadLocalBin(inputBean, rows, from, orderBy);

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

    private boolean doValidation(LocalBinBean bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getBin() == null || bean.getBin().isEmpty()) {
                if (inputBean.getCategory_code() == 1) {
                    addActionError(SystemMessage.LOCAL_BIN_INVALID_PROFILE_NAME);
                    return ok;
                } else if (inputBean.getCategory_code() == 2) {
                    addActionError(SystemMessage.SYSTEM_BIN_INVALID_PROFILE_NAME);
                    return ok;
                }

            } else if (!Util.validateNUMBER(bean.getBin()) || bean.getBin().length() != 6) {
                addActionError(SystemMessage.LOCAL_BIN_INVALID_NAME);
                return ok;
            } else if (getService().getLocalBinInf().checkBin(inputBean)) {
                if (inputBean.getCategory_code() == 1) {
                    addActionError(SystemMessage.LOCAL_BIN_EXIST);
                    return ok;
                } else if (inputBean.getCategory_code() == 2) {
                    addActionError("System block BIN is already exist");
                    return ok;
                }

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
            inputBean.setCategory_code(1);
            if (doValidation(inputBean)) {

                boolean ok = getService().getLocalBinInf().insertLocalBin(inputBean);
                if (ok == true) {
                    String mzg = "";
                    if (inputBean.getCategory_code() == 1) {
                        mzg = SystemMessage.LOCAL_BIN_ADD_SUCCESS;
                    } else {
                        mzg = SystemMessage.SYSTEM_BLOCK_BIN_ADD_SUCCESS;
                    }
                    addActionMessage(mzg);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.BIN_MANAGEMENT,
                            TaskVarList.ADD,
                            inputBean.getBin() + " -> " + mzg,
                            null, null,
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.LOCAL_BIN,
                            null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.LOCAL_BIN_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    public String AddSysBlockBin() {
        inputBean.setToken(getSessionToken());
        try {
            inputBean.setCategory_code(2);
            if (doValidation(inputBean)) {

                boolean ok = getService().getLocalBinInf().insertLocalBin(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.SYSTEM_BLOCK_BIN_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.BIN_MANAGEMENT,
                            TaskVarList.ADD,
                            inputBean.getBin() + " -> " + SystemMessage.SYSTEM_BLOCK_BIN_ADD_SUCCESS,
                            null, null,
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.LOCAL_BIN,
                            null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.LOCAL_BIN_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal = Util.getOldorNewVal(epicTleLocalbin, "wu.epicTleLocalbinPK.bin ='" + inputBean.getBin() + "'");
            if (getService().getLocalBinInf().deleteLocalBin(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.DELETE, SystemMessage.LOCAL_BIN_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.LOCAL_BIN, oldVal, null);
                String mzg = "";
                if (inputBean.getCategory_code() == 1) {
                    mzg = SystemMessage.LOCAL_BIN_DELETE_SUCESS;
                } else if (inputBean.getCategory_code() == 2) {
                    mzg = SystemMessage.SYSTEM_BLOCK_BIN_DELETE_SUCESS;
                }
                LogFileCreator.writeInforToLog(mzg);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(mzg);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.LOCAL_BIN_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.LOCAL_BIN_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

}
