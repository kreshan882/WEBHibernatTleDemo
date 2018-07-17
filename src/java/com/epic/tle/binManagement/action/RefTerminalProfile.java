/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.action;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.binManagement.bean.RefTerminalBean;
import com.epic.tle.binManagement.service.BinProfileFactory;
import com.epic.tle.binManagement.service.RefTerminalProfileFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleTerminalRefprofile;
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
public class RefTerminalProfile extends ActionSupport implements AccessControlService, ModelDriven<RefTerminalBean> {

    private RefTerminalBean inputBean = new RefTerminalBean();
    RefTerminalProfileFactory service;
    SessionUserBean session;
    EpicTleTerminalRefprofile epicTleTerminalRefprofile = new EpicTleTerminalRefprofile();

    public RefTerminalProfileFactory getService() {
        return new RefTerminalProfileFactory();
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
    public RefTerminalBean getModel() {
        try {
            inputBean.setFallBackMap(Util.getStatusValues(0, 2));
            inputBean.setSwipeTarMap(Util.getStatusValues(0, 2));
            inputBean.setUpStatusMap(Util.getStatusValues(0, 2));
            inputBean.setNfcBasedMap(Util.getStatusValues(0, 2));
            inputBean.setPinPerformkMap(Util.getStatusValues(0, 2));
            getService().getRefTerminalProfileInf().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {

        boolean status;
        applyUserPrivileges();
        String page = PageVarList.TERMINAL_REF_PROFILE;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.BLOCK_BIN_PROFILE, request);
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

    public String list() {
        inputBean.setToken(getSessionToken());
        List<RefTerminalBean> dataList;
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

            dataList = getService().getRefTerminalProfileInf().loadRefProfile(inputBean, rows, from, orderBy);

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
            //LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    private boolean doValidation(RefTerminalBean bean) throws Exception {
        boolean ok = false;
        try {
            String profile = getService().getRefTerminalProfileInf().GetResult(inputBean);
            if (bean.getName() == null || bean.getName().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_NAME_EMPTY);
                return ok;
            } else if (profile != null) {
                addActionError(SystemMessage.REF_TERMINAL_NAME_ALREADY);
                return ok;
            } else if (bean.getMinAmount() == null || bean.getMinAmount().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_MIN_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getMinAmount())) {
                addActionError(SystemMessage.REF_TERMINAL_MIN_INVALID);
                return ok;
            } else if (bean.getMaxAmount() == null || bean.getMaxAmount().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_MAX_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getMaxAmount())) {
                addActionError(SystemMessage.REF_TERMINAL_MAX_INVALID);
                return ok;
            } else if (Integer.parseInt(bean.getMaxAmount().trim()) < Integer.parseInt(bean.getMinAmount().trim())) {
                addActionError(SystemMessage.REF_TERMINAL_MIN_MAX_LENGTH_INVALID);
                return ok;
            } else if (bean.getFrom().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_FROM);
                return ok;
            } else if (bean.getTo().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_TO);
                return ok;
            } else if ((Integer.parseInt(bean.getFrom()) > Integer.parseInt(bean.getTo())) && (!(bean.getTo().equals("0")))) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_INVALID);
                return ok;
            } else if (Integer.parseInt(bean.getFrom()) == Integer.parseInt(bean.getTo())) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_CANNOT_SAME);
                return ok;
            } else if (bean.getSwipeStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_SWIPE_EMPTY);
                return ok;
            } else if (bean.getFallBackStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_FALLBACK_EMPTY);
                return ok;
            } else if (bean.getNfcBasedStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_NFC_EMPTY);
                return ok;
            } else if (bean.getPinPerformStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_PIN_EMPTY);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    private boolean doValidationUpdate(RefTerminalBean bean) throws Exception {

        boolean ok = false;
        try {
            if (bean.getUpName() == null || bean.getUpName().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_NAME_EMPTY);
                return ok;
            } else if (bean.getUpminAmount() == null || bean.getUpminAmount().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_MIN_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getUpminAmount())) {
                addActionError(SystemMessage.REF_TERMINAL_MIN_INVALID);
                return ok;
            } else if (bean.getUpmaxAmount() == null || bean.getUpmaxAmount().isEmpty()) {
                addActionError(SystemMessage.REF_TERMINAL_MAX_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(bean.getUpmaxAmount())) {
                addActionError(SystemMessage.REF_TERMINAL_MAX_INVALID);
                return ok;
            } else if (bean.getUpfrom().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_FROM);
                return ok;
            } else if (bean.getUpto().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_TO);
                return ok;
            } else if (bean.getUpswipeStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_SWIPE_EMPTY);
                return ok;
            } else if ((Integer.parseInt(bean.getUpfrom()) > Integer.parseInt(bean.getUpto())) && (!(bean.getUpto().equals("0")))) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_INVALID);
                return ok;
            } else if (Integer.parseInt(bean.getUpfrom()) == Integer.parseInt(bean.getUpto())) {
                addActionError(SystemMessage.REF_TERMINAL_OPRH_CANNOT_SAME);
                return ok;
            } else if (bean.getUpfallBackStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_FALLBACK_EMPTY);
                return ok;
            } else if (bean.getUpnfcBasedStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_NFC_EMPTY);
                return ok;
            } else if (bean.getUpPinPerformStatus().equals("-1")) {
                addActionError(SystemMessage.REF_TERMINAL_PIN_EMPTY);
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

                if (getService().getRefTerminalProfileInf().insertRefProfile(inputBean)) {
                    addActionMessage(SystemMessage.REF_TERMINAL_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(), 
                            SystemModule.BIN_MANAGEMENT, 
                            TaskVarList.ADD, 
                            inputBean.getName() +" -> "+SystemMessage.BLOCK_BIN_ADD_SUCCESS,
                            null, null, 
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.TERMINAL_RISK_PROFILE,
                            null, null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.REF_TERMINAL_ADD_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    public String Load() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getRefTerminalProfileInf().getUpdateData(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "load";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal = Util.getOldorNewVal(epicTleTerminalRefprofile, "wu.id ='" + inputBean.getId() + "'");
            if (getService().getRefTerminalProfileInf().deleteRefProfile(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.DELETE, SystemMessage.BLOCK_BIN_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.TERMINAL_RISK_PROFILE, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.REF_TERMINAL_DELETE_SUCCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.REF_TERMINAL_DELETE_SUCCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.REF_TERMINAL_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.REF_TERMINAL_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

    public String update() {
        inputBean.setToken(getSessionToken());
        boolean ok;
        try {
            if (doValidationUpdate(inputBean)) {
                String oldVal = Util.getOldorNewVal(epicTleTerminalRefprofile, "wu.id ='" + inputBean.getUpProfileID() + "'");
                ok = getService().getRefTerminalProfileInf().updateRefProfile(inputBean);
                if (ok == true) {
                    String newVal = Util.getOldorNewVal(epicTleTerminalRefprofile, "wu.id ='" + inputBean.getUpProfileID() + "'");
                    inputBean.setIsUpdated(true);
                    inputBean.setMsg(SystemMessage.REF_TERMINAL_UPDATE_SUCCESS);
                    addActionMessage(SystemMessage.REF_TERMINAL_UPDATE_SUCCESS);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.BLOCK_BIN_UPDATED, null, null, null, getSession().getId(), SystemSection.TERMINAL_RISK_PROFILE, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.REF_TERMINAL_UPDATE_SUCCESS);
                } else {
                    addActionError(SystemMessage.REF_TERMINAL_UPDATE_FAIL);
                }
            }
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

}
