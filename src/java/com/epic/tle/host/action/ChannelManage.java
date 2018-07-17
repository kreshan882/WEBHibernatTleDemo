/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.action;

import com.epic.tle.host.bean.ChannelManageDataBean;
import com.epic.tle.host.bean.ChannelManageInputBean;
import com.epic.tle.host.service.ChannelManagementFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleChannel;
import com.epic.tle.mapping.EpicTleChannelOperationMethod;
import com.epic.tle.mapping.EpicTleConnectiontypes;
import com.epic.tle.mapping.EpicTleForwardmethod;
import com.epic.tle.mapping.EpicTleHeaderFormats;
import com.epic.tle.mapping.EpicTleHeaderSize;
import com.epic.tle.mapping.EpicTleIsoFormat;
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
 * @author kreshan
 */
public class ChannelManage extends ActionSupport implements ModelDriven<ChannelManageInputBean>, AccessControlService {

    ChannelManageInputBean inputBean = new ChannelManageInputBean();
    ChannelManagementFactory service;
    SessionUserBean session;
    EpicTleChannel channel = new EpicTleChannel();

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    public ChannelManagementFactory getService() {
        return new ChannelManagementFactory();
    }

    public String execute() {
        return SUCCESS;
    }

    public String Find() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getSessionService().findNII(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }

    public String Add() throws Exception {
        inputBean.setToken(getSessionToken());
        SessionUserBean sub = (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
        try {
            if (doValidationAdd(inputBean)) {
                if (getService().getSessionService().insertnii(inputBean)) {

                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            sub.getUserLevel(),
                            SystemModule.HOST_MANAGEMENT,
                            TaskVarList.ADD,
                            SystemMessage.HL_CHANNEL_ADD_SUCCESS + "{ Channel Name : " + inputBean.getChannelName() + ", IP : " + inputBean.getAip() + ", Port : " + inputBean.getAport() + " }",
                            null, null, null,
                            sub.getId(),
                            SystemSection.CHANNEL__MANAGEMENT,
                            null, null);
                    LogFileCreator.writeInforToLog(SystemMessage.HL_CHANNEL_ADD_SUCCESS);
                    addActionMessage(SystemMessage.HL_CHANNEL_ADD_SUCCESS);
                } else {
                    addActionError(SystemMessage.HL_CHANNEL_ADD_FAIL);
                }

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.HL_CHANNEL_ADD_FAIL);
        }

        return "add";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            channel.setChannelId(inputBean.getChannelId());
            String oldVal = Util.getOldorNewVal(channel, "wu.channelId ='" + channel.getChannelId() + "'");
            if (getService().getSessionService().deleteNII(inputBean)) {
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.HL_CHANNEL_DELETE);
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.DELETE, SystemMessage.HL_CHANNEL_DELETE, null, null, null, getSession().getId(), SystemSection.CHANNEL__MANAGEMENT, oldVal, null);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.HL_CHANNEL_DELETE_FAIL);

            }

        } catch (Exception ex) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.HL_CHANNEL_DELETE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "delete";
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<ChannelManageDataBean> dataList = null;
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
            dataList = getService().getSessionService().loadChannelData(inputBean, rows, from, orderBy);

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

    public String Update() {
        inputBean.setToken(getSessionToken());
        try {
            if (doValidationUpdate(inputBean)) {
                channel.setChannelId(inputBean.getUpchannelId());
                String oldVal = Util.getOldorNewVal(channel, "wu.channelId ='" + channel.getChannelId() + "'");
                if (getService().getSessionService().updateNII(inputBean)) {
                    String newVal = Util.getOldorNewVal(channel, "wu.channelId ='" + channel.getChannelId() + "'");
                    addActionMessage(SystemMessage.HL_CHANNEL_UPDATE_SUCCESS);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.HL_CHANNEL_UPDATE_SUCCESS, null, null, null, getSession().getId(), SystemSection.CHANNEL__MANAGEMENT, oldVal, newVal);
                } else {
                    addActionError(SystemMessage.HL_CHANNEL_UPDATE_FAIL);
                }
            }
        } catch (Exception ex) {
            addActionError(SystemMessage.HL_CHANNEL_UPDATE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    @Override
    public ChannelManageInputBean getModel() {
        try {
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);

            inputBean.setAstatusMap(Util.getStatusValues(0, 2));
            inputBean.setKeepAliveStatus(Util.getStatusValues(0, 2));
//            inputBean.setAconTypeMap(getService().getSessionService().getConnecType());

            for (EpicTleConnectiontypes list1 : (List<EpicTleConnectiontypes>) Util.getMasterValues(0, 2, "EpicTleConnectiontypes")) {
                inputBean.getAconTypeMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleForwardmethod list1 : (List<EpicTleForwardmethod>) Util.getMasterValues(0, 2, "EpicTleForwardmethod")) {
                inputBean.getAforwerdMathodMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleHeaderFormats list1 : (List<EpicTleHeaderFormats>) Util.getMasterValues(0, 2, "EpicTleHeaderFormats")) {
                inputBean.getHeaderFormatMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleHeaderSize list1 : (List<EpicTleHeaderSize>) Util.getMasterValues(0, 2, "EpicTleHeaderSize")) {
                inputBean.getHeaderSizeMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleChannelOperationMethod list1 : (List<EpicTleChannelOperationMethod>) Util.getMasterValues(0, 2, "EpicTleChannelOperationMethod")) {
                inputBean.getOperMethodMap().put(list1.getCode(), list1.getDescription());
            }

            for (EpicTleIsoFormat list1 : (List<EpicTleIsoFormat>) Util.getMasterValues(0, 10, "EpicTleIsoFormat")) {
                inputBean.getIsoTypeMap().put(list1.getCode(), list1.getEpicTleIsoFormat());
            }

//            inputBean.setAforwerdMathodMap(getService().getSessionService().getForwardMethod());
//            inputBean.setHeaderFormatMap(getService().getSessionService().getHeaderFormat());
//            inputBean.setHeaderSizeMap(getService().getSessionService().getHeaderSize());
//            inputBean.setOperMethodMap(getService().getSessionService().getOperMethod());
//            inputBean.setIsoTypeMap(getService().getSessionService().getIsoFormat());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputBean;

    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.CHANNEL_MANAGEMENT;
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
        } else if ("AssignNII".equals(method)) {
            task = TaskVarList.VIEW;
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

    public String AssignNII() {
        inputBean.setToken(getSessionToken());
        int channelID = inputBean.getChannelId();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        session.setAttribute("channel", channelID);
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        return "assign";
    }

    public String AddNII() {
        inputBean.setToken(getSessionToken());
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        return "assign";
    }

    public String listnii() {
        inputBean.setToken(getSessionToken());
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        return "assign";
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.CHANNEL_MANAGEMENT, request);
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

    private boolean doValidationUpdate(ChannelManageInputBean inputBean) throws Exception {
        try {
            String channel = null;
            String port = null;

            if (inputBean.getUpchannelName() == null || inputBean.getUpchannelName().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_CHANNEL_NAME);
                return false;
            } else if (!inputBean.getHidUpName().equals(inputBean.getUpchannelName())) {
                channel = getService().getSessionService().GetResult(inputBean, "name");
            }
            if (inputBean.getUpisoFile() == null || inputBean.getUpisoFile().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_ISO_NAME);
                return false;
            }
            if (!inputBean.getUpchannelName().matches("[a-zA-Z0-9_. ]*") && inputBean.getUpchannelName().length() <= 50 ) {
                addActionError(SystemMessage.HL_INVALID_CHANNEL_NAME);
                return false;
            }
            if (inputBean.getUpip() == null || inputBean.getUpip().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_IP);
                return false;
            }
            if (!Util.validateIP(inputBean.getUpip())) {
                addActionError(SystemMessage.HL_INVALID_IP);
                return false;
            }
            if (inputBean.getUpport() == null || inputBean.getUpport().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_PORT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getUpport())) {
                addActionError(SystemMessage.HL_INVALID_PORT);
                return false;
            }
            if (inputBean.getUpstatus().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_STATUS);
                return false;
            }
            if (inputBean.getUpconType().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_CON_TYPE);
                return false;
            }
            if (inputBean.getUpforwerdMathod().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_FORWARD_METHOD);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getUpcontime())) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getUpreadtime())) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (Integer.parseInt(inputBean.getUpcontime()) <= 0) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (Integer.parseInt(inputBean.getUpreadtime()) <= 0) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (inputBean.getUpconType().equals("1")) {
                if (inputBean.getUploadBalance().equals("-1")) {
                    addActionError(SystemMessage.HL_INVALID_LOAD_TYPE);
                    return false;
                }
                if (inputBean.getUploadBalance().equals("1")) {
                    if (!Util.validateIP(inputBean.getUpsecIp())) {
                        addActionError(SystemMessage.HL_INVALID_SEC_IP);
                        return false;
                    }
                    if (inputBean.getUpsecPort() == null || inputBean.getUpsecPort().isEmpty()) {
                        addActionError(SystemMessage.HL_INVALID_SEC_PORT);
                        return false;
                    }
                    if (inputBean.getUpoperMethod().equals("-1")) {
                        addActionError(SystemMessage.HL_INVALID_OPERATION);
                        return false;
                    }
                }
            }
            if (inputBean.getUpTPDUStatus().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_TPDU_STATUS);
                return false;
            }
            if (inputBean.getUpcontime() == null || inputBean.getUpcontime().isEmpty()) {
                addActionError(SystemMessage.HL_CON_EMPTY_TIMEOUT);
                return false;
            }
            if (inputBean.getUpreadtime() == null || inputBean.getUpreadtime().isEmpty()) {
                addActionError(SystemMessage.HL_READ_EMPTY_TIMEOUT);
                return false;
            }

            if (Integer.parseInt(inputBean.getUpPinTrans()) == 1) {
                if (inputBean.getUpenzpk().isEmpty() || inputBean.getUpenzpk().equals("")) {
                    addActionError(SystemMessage.HL_EMPTY_ENCRYPT_ZPK_SIZE);
                    return false;
                } else if (!Util.validateHEXA(inputBean.getUpenzpk())) {
                    addActionError(SystemMessage.HL_EMPTY_ENCRYPT_ZPK_VALUE);
                    return false;
                } else if (inputBean.getUpenzpk().length() != 32) {
                    addActionError(SystemMessage.ZPK_LENGTH_TOO_HIGH);
                    return false;
                }
            }

            if (channel != null) {
                addActionError(SystemMessage.HL_CHANNEL_NAME_DEFINE);
                return false;
            } else if (port != null) {
                addActionError(SystemMessage.HL_CHANNEL_PORT_DEFINE);
                return false;
            }

            return true;

        } catch (Exception e) {
            throw e;
        }
    }

    private boolean doValidationAdd(ChannelManageInputBean inputBean) throws Exception {
        try {

            String channel = getService().getSessionService().GetResult(inputBean, "name");

            if (inputBean.getChannelName() == null || inputBean.getChannelName().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_CHANNEL_NAME);
                return false;
            }
            if (inputBean.getIsoFile() == null || inputBean.getIsoFile().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_ISO_NAME);
                return false;
            }

            if (!inputBean.getChannelName().matches("[a-zA-Z0-9_. ]*") && inputBean.getChannelName().length() <= 50 || (inputBean.getChannelName().length() < 3)) {
                addActionError(SystemMessage.HL_INVALID_CHANNEL_NAME);
                return false;
            }
            if (inputBean.getAip() == null || inputBean.getAip().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_IP);
                return false;
            }
            if (!Util.validateIP(inputBean.getAip())) {
                addActionError(SystemMessage.HL_INVALID_IP);
                return false;
            }
            if (inputBean.getAport() == null || inputBean.getAport().isEmpty()) {
                addActionError(SystemMessage.HL_EMPTY_PORT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getAport())) {
                addActionError(SystemMessage.HL_INVALID_PORT);
                return false;
            }
            if (inputBean.getTpdu().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_TPDU_STATUS);
                return false;
            }
            if (inputBean.getAstatus().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_STATUS);
                return false;
            }

//            if (inputBean.getLoadBalance().equals("-1")) {
//                addActionError(SystemMessage.HL_INVALID_LOAD_TYPE);
//                return false;
//            }
            if (inputBean.getAconType().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_CON_TYPE);
                return false;
            }
            if (inputBean.getAconType().equals("1")) {
                if (inputBean.getLoadBalance().equals("-1")) {
                    addActionError(SystemMessage.HL_INVALID_LOAD_TYPE);
                    return false;
                }
                if (inputBean.getLoadBalance().equals("1")) {
                    if (!Util.validateIP(inputBean.getSip())) {
                        addActionError(SystemMessage.HL_INVALID_SEC_IP);
                        return false;
                    }
                    if (inputBean.getSport() == null || inputBean.getSport().isEmpty()) {
                        addActionError(SystemMessage.HL_INVALID_SEC_PORT);
                        return false;
                    }
                    if (inputBean.getOperMethod().equals("-1")) {
                        addActionError(SystemMessage.HL_INVALID_OPERATION);
                        return false;
                    }
                }
            }
            if (inputBean.getAforwerdMathod().equals("-1")) {
                addActionError(SystemMessage.HL_INVALID_FORWARD_METHOD);
                return false;
            }

            if (inputBean.getContime() == null || inputBean.getContime().isEmpty()) {
                addActionError(SystemMessage.HL_CON_EMPTY_TIMEOUT);
                return false;
            }
            if (inputBean.getReadtime() == null || inputBean.getReadtime().isEmpty()) {
                addActionError(SystemMessage.HL_READ_EMPTY_TIMEOUT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getContime())) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (!Util.validateNUMBER(inputBean.getReadtime())) {
                addActionError(SystemMessage.HL_READ_INVALID_TIMEOUT);
                return false;
            }
            if (Integer.parseInt(inputBean.getContime()) <= 0) {
                addActionError(SystemMessage.HL_CON_INVALID_TIMEOUT);
                return false;
            }
            if (Integer.parseInt(inputBean.getReadtime()) <= 0) {
                addActionError(SystemMessage.HL_READ_INVALID_TIMEOUT);
                return false;
            }

            if (Integer.parseInt(inputBean.getPinTrans()) == 1) {

                if (inputBean.getEcnryptZPK().isEmpty() || inputBean.getEcnryptZPK().equals("")) {
                    addActionError(SystemMessage.HL_EMPTY_ENCRYPT_ZPK_SIZE);
                    return false;
                } else if (!Util.validateHEXA(inputBean.getEcnryptZPK())) {
                    addActionError(SystemMessage.HL_EMPTY_ENCRYPT_ZPK_VALUE);
                    return false;
                } else if (inputBean.getEcnryptZPK().length() != 32) {
                    addActionError(SystemMessage.ZPK_LENGTH_TOO_HIGH);
                    return false;
                }

            }

            if (channel != null) {
                addActionError(SystemMessage.HL_CHANNEL_NAME_DEFINE);
                return false;
            }
            return true;

        } catch (Exception e) {
            throw e;
        }
    }

}
