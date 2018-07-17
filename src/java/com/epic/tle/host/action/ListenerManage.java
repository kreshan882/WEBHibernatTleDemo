/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.action;

import com.epic.tle.host.bean.ListenerManageDataBean;
import com.epic.tle.host.bean.ListenerManageInputBean;
import com.epic.tle.host.service.ListenerManagementFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleConnectiontypes;
import com.epic.tle.mapping.EpicTleHeaderFormats;
import com.epic.tle.mapping.EpicTleHeaderSize;
import com.epic.tle.mapping.EpicTleIsoFormat;
import com.epic.tle.mapping.EpicTleListeners;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author KRESHAN
 */
public class ListenerManage extends ActionSupport implements ModelDriven<ListenerManageInputBean>, AccessControlService {

    ListenerManageInputBean inputBean = new ListenerManageInputBean();
    ListenerManagementFactory service;
    SessionUserBean session;
    EpicTleListeners listener = new EpicTleListeners();

    public ListenerManagementFactory getService() {
        return new ListenerManagementFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    public String execute() {
        return SUCCESS;
    }

    public String Add() {
        inputBean.setToken(getSessionToken());
        try {

            if (doValidationAdd(inputBean)) {
                if (getService().getSessionService().insertListener(inputBean)) {

                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.HOST_MANAGEMENT,
                            TaskVarList.ADD,
                            SystemMessage.HL_LISTENER_ADD_SUCCESS + " - { Listner Name : " + inputBean.getListName() + ", Listner Port :" + inputBean.getListPort() + " }",
                            null, null, null,
                            getSession().getId(),
                            SystemSection.LISTENER_MANAGEMENT,
                            null, null);
                    LogFileCreator.writeInforToLog(SystemMessage.HL_LISTENER_ADD_SUCCESS);
                    addActionMessage(SystemMessage.HL_LISTENER_ADD_SUCCESS);
                } else {
                    addActionError(SystemMessage.HL_LISTENER_ADD_FAIL);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.HL_LISTENER_ADD_FAIL);
        }

        return "add";
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<ListenerManageDataBean> dataList = null;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = "order by wu." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadListnerData(inputBean, rows, from, orderBy);

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
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";

    }

    public String Find() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getSessionService().findListener(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }

    public String Update() {
        inputBean.setToken(getSessionToken());
        try {
            if (doValidationUpdate(inputBean)) {
                String oldVal = Util.getOldorNewVal(listener, "wu.listenerId ='" + Integer.toString(inputBean.getUplistId()) + "'");
                getService().getSessionService().updateListener(inputBean);
                String newVal = Util.getOldorNewVal(listener, "wu.listenerId ='" + Integer.toString(inputBean.getUplistId()) + "'");
                addActionMessage(SystemMessage.HL_LISTENER_UPDATE_SUCCESS);
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.HL_LISTENER_UPDATE_SUCCESS, null, null, null, getSession().getId(), SystemSection.LISTENER_MANAGEMENT, oldVal, newVal);
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.HL_LISTENER_UPDATE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";

    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal = Util.getOldorNewVal(listener, "wu.listenerId ='" + Integer.toString(inputBean.getListId()) + "'");
            if (getService().getSessionService().deleteListener(inputBean)) {
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.HL_LISTENER_DELETE_SUCCESS);
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.DELETE, SystemMessage.HL_LISTENER_DELETE_SUCCESS, null, null, null, getSession().getId(), SystemSection.LISTENER_MANAGEMENT, oldVal, null);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.HL_LISTENER_DELETE_FAIL);

            }

        } catch (Exception ex) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.HL_LISTENER_DELETE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "delete";
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.LISTENER_MANAGEMENT;
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
            status = !inputBean.isVview();
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.LISTENER_MANAGEMENT, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setVview(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setVview(false);
                }
            }
        }

        return true;
    }

    private boolean doValidationAdd(ListenerManageInputBean inputBean) throws Exception {

        try {

            if (inputBean.getListName() == null || inputBean.getListName().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_LISTENER_NAME);
                return false;
            } else if (!Util.validateNAME(inputBean.getListName())) {
                addActionError(SystemMessage.HL_INVALID_LISTENER_NAME);
                return false;
            } else {
                String name = getService().getSessionService().GetResult(inputBean, "name");
                if (name != null) {
                    addActionError(SystemMessage.HL_LISTENER_ALREADY);
                    return false;
                }
            }
            if (inputBean.getListPort() == null || inputBean.getListPort().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_PORT);
                return false;
            } else if (!Util.validateNUMBER(inputBean.getListPort())) {
                addActionError(SystemMessage.HL_INVALID_LISTENER_PORT);
                return false;
            } else {
                String port = getService().getSessionService().GetResult(inputBean, "port");
                if (port != null) {
                    addActionError(SystemMessage.HL_LISTENER_PORT_ALREADY);
                    return false;
                }
            }
            if (inputBean.getListConnectType() == null || inputBean.getListConnectType().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_CONNECTION_TYPE);
                return false;

            }
            if (inputBean.getListTimeOut() == null || inputBean.getListTimeOut().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_LISTINER_TIMEOUT);
                return false;
            } else if (!Util.validateNUMBER(inputBean.getListTimeOut())) {
                addActionError(SystemMessage.HL_INVALID_LISTINER_TIMEOUT);
                return false;
            }
            if (inputBean.getListKeepLive() == null || inputBean.getListKeepLive().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_KEEP_ALIVE);
                return false;
            } else if (inputBean.getListHeaderFormat() == null || inputBean.getListHeaderFormat().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_HEADER_FORMAT);
                return false;
            } else if (inputBean.getListHeadSize() == null || inputBean.getListHeadSize().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_HEADER_SIZE);
                return false;
            } else if (inputBean.getListStatus() == null || inputBean.getListStatus().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_STATUS);
                return false;
            }

            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    private boolean doValidationUpdate(ListenerManageInputBean inputBean) throws Exception {
        try {

            if (inputBean.getUplistName() == null || inputBean.getUplistName().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_LISTENER_NAME);
                return false;
            }
            if (!Util.validateNAME(inputBean.getUplistName())) {
                addActionError(SystemMessage.HL_INVALID_LISTENER_NAME);
                return false;
            }
            if (inputBean.getUplistProt() == null || inputBean.getUplistProt().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_PORT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getUplistProt())) {
                addActionError(SystemMessage.HL_INVALID_LISTENER_PORT);
                return false;
            }

            if (inputBean.getUplistConnectType() == null || inputBean.getUplistConnectType().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_CONNECTION_TYPE);
                return false;

            }
            if (inputBean.getUplistTimeOut() == null || inputBean.getUplistTimeOut().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_LISTINER_TIMEOUT);
                return false;
            } else if (!Util.validateNUMBER(inputBean.getUplistTimeOut())) {
                addActionError(SystemMessage.HL_INVALID_LISTINER_TIMEOUT);
                return false;
            }
            if (inputBean.getUplistKeepLive() == null || inputBean.getUplistKeepLive().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_KEEP_ALIVE);
                return false;
            } else if (inputBean.getUplistHeaderFormat() == null || inputBean.getUplistHeaderFormat().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_HEADER_FORMAT);
                return false;
            } else if (inputBean.getUplistHeadSize() == null || inputBean.getUplistHeadSize().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_HEADER_SIZE);
                return false;
            } else if (inputBean.getUplistStatus() == null || inputBean.getUplistStatus().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_STATUS);
                return false;
            } else if (!inputBean.getHidUplistName().equals(inputBean.getUplistName()) && !inputBean.getHidUplistPort().equals(inputBean.getUplistProt())) {
                addActionError(SystemMessage.HL_LISTENER_ALREADY);
                return false;
            } else if (!inputBean.getHidUplistName().equals(inputBean.getUplistName())) {
                String name = getService().getSessionService().GetResult(inputBean, "name");
                if (name != null) {
                    addActionError(SystemMessage.HL_LISTENER_ALREADY);
                    return false;
                }

            } else if (!inputBean.getHidUplistPort().equals(inputBean.getUplistProt())) {
                String port = getService().getSessionService().GetResult(inputBean, "port");
                if (port != null) {
                    addActionError(SystemMessage.HL_LISTENER_PORT_ALREADY);
                    return false;
                }

            }

            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public ListenerManageInputBean getModel() {
        try {
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);

            inputBean.setListstatusMap(Util.getStatusValues(0, 2));
            inputBean.setListStatusMap(Util.getStatusValues(0, 2));
            inputBean.setListKeepLiveMap(Util.getStatusValues(0, 2));
            inputBean.setListBindStatusMap(Util.getStatusValues(0, 2));

            for (EpicTleConnectiontypes list1 : (List<EpicTleConnectiontypes>) Util.getMasterValues(0, 2, "EpicTleConnectiontypes")) {
                inputBean.getListconTypeMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleHeaderFormats list1 : (List<EpicTleHeaderFormats>) Util.getMasterValues(0, 2, "EpicTleHeaderFormats")) {
                inputBean.getListHeaderFormatMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleHeaderSize list1 : (List<EpicTleHeaderSize>) Util.getMasterValues(0, 2, "EpicTleHeaderSize")) {
                inputBean.getListHeaderSizeMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleIsoFormat list1 : (List<EpicTleIsoFormat>) Util.getMasterValues(0, 10, "EpicTleIsoFormat")) {
                inputBean.getISOFormat().put(list1.getCode(), list1.getEpicTleIsoFormat());
            }

//            inputBean.setListconTypeMap(getService().getSessionService().getConnecType());
//            inputBean.setListHeaderSizeMap(getService().getSessionService().getHeaderSize());
//            inputBean.setListHeaderFormatMap(getService().getSessionService().getHeaderFormat());
//            inputBean.setISOFormat(getService().getSessionService().getIsoFormat());
            return inputBean;
        } catch (Exception ex) {
            Logger.getLogger(ListenerManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
