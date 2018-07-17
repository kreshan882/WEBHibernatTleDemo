/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleInitconfig;
import com.epic.tle.servermanager.bean.ServerConfigurationBean;
import com.epic.tle.servermanager.service.ServerConfFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
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
public class ServerConfiguration extends ActionSupport implements ModelDriven<ServerConfigurationBean>, AccessControlService {

    ServerConfigurationBean inputBean = new ServerConfigurationBean();
    ServerConfFactory service;
    SessionUserBean sub;
    EpicTleInitconfig epicTleInitconfig = new EpicTleInitconfig();

    public ServerConfFactory getService() {
        return new ServerConfFactory();
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
            getService().getSessionService().loadIniconfig(inputBean);

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }

        return "reset";
    }

    public String Update() {
     
        inputBean.setToken(getSessionToken());
        boolean ok = false;
        try {
            if (doValidation(inputBean)) {
                String oldVal = Util.getOldorNewVal(epicTleInitconfig, "wu.node ='" + Configurations.SERVER_NODE + "'");
                if (getService().getSessionService().updateServerConf(inputBean)) {
                    String newVal = Util.getOldorNewVal(epicTleInitconfig, "wu.node ='" + Configurations.SERVER_NODE + "'");
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.SERVER_CONF_UPDATED, null, null, null, getSub().getId(), SystemSection.CONFIGURATIONS, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.SERVER_CONF_UPDATED);
                    addActionMessage(SystemMessage.SERVER_CONF_UPDATED);

                } else {
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.SERVER_CONF_UPDATED_ERROR, null, null, null, getSub().getId(), SystemSection.CONFIGURATIONS, null, null);
                    LogFileCreator.writeInforToLog(SystemMessage.SERVER_CONF_UPDATED_ERROR);
                    addActionError(SystemMessage.SERVER_CONF_UPDATED_ERROR);
                }
            } 
        } catch (Exception ex) {
            addActionError(SystemMessage.SERVER_CONF_UPDATED_ERROR);
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }

        return "update";
    }

    private boolean doValidation(ServerConfigurationBean inputBean) throws Exception {
        boolean ok = false;
        try {
            if (inputBean.getThredMaxPool() == null || inputBean.getThredMaxPool().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_MAX_POOL);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getThredMaxPool())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MAX_POOL);
                return ok;
            } else if (inputBean.getThredMinPool() == null || inputBean.getThredMinPool().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_MIN_POOL);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getThredMinPool())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MAX_POOL);
                return ok;
            } else if (inputBean.getMaxQueueSize() == null || inputBean.getMaxQueueSize().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_MAX_QUEUE_SIZE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getMaxQueueSize())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MAX_QUEUE_SIZE);
                return ok;
            } else if (inputBean.getBackLogSize() == null || inputBean.getBackLogSize().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_BACKLOG_SIZE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getBackLogSize())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_BACKLOG_SIZE);
                return ok;
            } else if (inputBean.getVipStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_VIPSTATUS);
                return ok;
            } else if (inputBean.getAdVerifyStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_ADVERIFYSTATUS);
                return ok;
            } else if (inputBean.getVip() == null || inputBean.getVip().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_VIP);
                return ok;
            } else if (!Util.validateIP(inputBean.getVip())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_VIP);
                return ok;
            } else if (inputBean.getIv() == null || inputBean.getIv().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_IV);
                return ok;
            } else if (!Util.validateHEXA(inputBean.getIv())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_IV);
                return ok;
            } else if (inputBean.getIv().length() > 16) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_IV);
                return ok;
            } else if (inputBean.getHostFailStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_HOSTFAIL_ALERTS);
                return ok;
            } else if (inputBean.getReplayAttackLevel() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_ATTACKLEVEL);
                return ok;
            } else if (inputBean.getAutoRegStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_AUTOREG);
                return ok;
            } else if (inputBean.getBuffer() == null || inputBean.getBuffer().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_BUFFER);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getBuffer()) || Integer.parseInt(inputBean.getBuffer()) < 250) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_BUFFER);
                return ok;
            } else if (inputBean.getLogLevel() == 0) {
                addActionError(SystemMessage.SERVER_CONF_LOGLEVEL);
                return ok;
            } else if (inputBean.getLogBackupStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_LOGBACKUP);
                return ok;
            } else if (inputBean.getLogPath() == null || inputBean.getLogPath().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_LOGBACKUP);
                return ok;
            } else if (Integer.toString(inputBean.getNumOfHistory()) == null || Integer.toString(inputBean.getNumOfHistory()).isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_NUMOFHISTORY);
                return ok;
            } else if (!Util.validateNUMBER(Integer.toString(inputBean.getNumOfHistory()))) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_NUMOFHISTORY);
                return ok;
            } else if (inputBean.getCoreDebugStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_COREDBUGSTATUS);
                return ok;
            } else if (inputBean.getLogFileName() == null || inputBean.getLogFileName().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_LOGFILENAME);
                return ok;
            } else if (inputBean.getNumLogFiles() == null || inputBean.getNumLogFiles().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_NUMLOGBACKFILES);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getNumLogFiles())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_NUMLOGBACKFILES);
                return ok;
            } else if (inputBean.getEsmStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_ESMSTATUS);
                return ok;
            } else if (inputBean.getMonIp() == null || inputBean.getMonIp().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_MONIP);
                return ok;
            } else if (!Util.validateIP(inputBean.getMonIp())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MONITOR_IP);
                return ok;
            } else if (inputBean.getPort() == null || inputBean.getPort().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_PORT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getPort())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MONITOR_PORT);
                return ok;
            } else if (inputBean.getTimeOut() == null || inputBean.getTimeOut().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_TIMEOUT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getTimeOut())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_MONITOR_TIMEOUT);
                return ok;
            } else if (inputBean.getSmsUrl() == null || inputBean.getSmsUrl().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_SMSURL);
                return ok;
            } else if (inputBean.getSmsTimeout() == null || inputBean.getSmsTimeout().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_SMSTIMEOUT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getSmsTimeout())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_SMSTIMEOUT);
                return ok;
            } else if (inputBean.getMonStatus() == 0) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_MONITOR);
                return ok;
            } else if (inputBean.getSmsUrl() == null || inputBean.getSmsUrl().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_SMSURL);
                return ok;
            } else if (inputBean.getSmsUsername() == null || inputBean.getSmsUsername().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_SMSUSERNAME);
                return ok;
            } else if (inputBean.isIsCheckedsms() == true) {
                if (inputBean.getSmsPassword().isEmpty() || inputBean.getSmsPassword() == null) {
                    addActionError(SystemMessage.SERVER_CONF_EMPTY_SMSPASSWORD);
                    return ok;
                } else {
                    ok = true;
                }
            } else if (inputBean.isIsChecked() == true) {
                if (inputBean.getEmailgwpassword().isEmpty() || inputBean.getEmailgwpassword() == null) {
                    addActionError(SystemMessage.SERVER_CONF_EMPTY_EMAIL_PASS);
                    return ok;
                } else {
                    ok = true;
                }

            } else if (inputBean.getPinCounter() == null || inputBean.getPinCounter().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_PINCOUNTER);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getPinCounter())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_PINCOUNTER);
                return ok;
            } else if (inputBean.getServicePort() == null || inputBean.getServicePort().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_EMPTY_SERVICEPORT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getServicePort())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_PINCOUNTER);
                return ok;
            } else if (inputBean.getTimeOut() == null || inputBean.getTimeOut().isEmpty()) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_EMPTY_TIMEOUT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getTimeOut())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_CLIENT_TIMEOUT);
                return ok;
            }
            else if(!Util.validateNUMBER(Integer.toString(inputBean.getSmsPort()))){
                addActionError(SystemMessage.SERVER_CONFIG_INVALID_SMS_PORT);
                System.out.println("sms port");
                return ok;
            } 
            else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public ServerConfigurationBean getModel() {

        try {
            inputBean.getVipStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));
            inputBean.getHostFailStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));
            inputBean.getLogBackupStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));
            inputBean.getCoreDebugStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));
            inputBean.getLogLevelMap().putAll(getService().getSessionService().getlogLevelMap());
            inputBean.getReplayAttackLevelMap().putAll(getService().getSessionService().getAttackLevel());
            inputBean.getMonStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));

            inputBean.getUkptStatus().putAll(getService().getSessionService().getStatusValues(0, 2));
            inputBean.getSmsNotify().putAll(getService().getSessionService().getStatusValues(0, 2));

            for (int i = 0; i <= 10; i++) {
                inputBean.getHsmSlotMap().put(i, Integer.toString(i));
            }
            inputBean.getServiceStatusMap().putAll(getService().getSessionService().getStatusValues(0, 2));

            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
            inputBean = getService().getSessionService().viewServerConfDetails(inputBean);

            getService().getSessionService().loadIniconfig(inputBean);

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
        String page = PageVarList.SERVER_CONFIGURATIONS;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.SERVER_CONFIGURATIONS, request);
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

    private boolean doValidationSession(ServerConfigurationBean inputBean) throws Exception {
        boolean ok = false;
        try {
            if (inputBean.getPinCounter() == null || inputBean.getPinCounter().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_MAX_PIN_COUNTER_VALUE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getPinCounter())) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MAX_PIN_COUNTER_VALUE);
                return ok;
            } else if (inputBean.getLogLevel() == 0) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_LOGLEVEL);
                return ok;
            } else if (inputBean.getLogFileName() == null || inputBean.getLogFileName().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_LOGFILENAME);
                return ok;
            } else if (inputBean.getLogPath() == null || inputBean.getLogPath().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_LOGPATH);
                return ok;
            } else if (inputBean.getIv() == null || inputBean.getIv().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_IV);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getIv())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_IV);
                return ok;
            } else if (inputBean.getHostFailStatus() == 0) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_HOSTFAIL_STATUS);
                return ok;
            } else if (inputBean.getReplayAttackLevel() == 0) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_REPLAYATTACK_LEVEL);
                return ok;
            } else if (inputBean.getNumLogFiles() == null || inputBean.getNumLogFiles().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_NUMOF_LOGS);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getNumLogFiles())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_NUMOF_LOGFILES);
                return ok;
            } //            else if (!Util.validateNUMBER(inputBean.getPort())) {
            //                addActionError(SystemMessage.SERVER_CONF_INVALID_MONITOR_PORT);
            //                return ok;
            //            } else if (!Util.validateNUMBER(inputBean.getTimeOut())) {
            //                addActionError(SystemMessage.SERVER_CONF_INVALID_MONITOR_TIMEOUT);
            //                return ok;
            //            } //            else if (!Util.validateURL(inputBean.getSmsUrl())) {
            //                addActionError(SystemMessage.SERVER_CONF_INVALID_SMSURL);
            //                return ok;
            //            }
            //            else if (!Util.validateURL(inputBean.getEmailUrl())) {
            //                addActionError(SystemMessage.SERVER_CONF_INVALID_EMAILURL);
            //                return ok;
            //            }
            else if (!Util.validateNUMBER(inputBean.getTimeOut())) {
                addActionError(SystemMessage.SERVER_CONF_INVALID_CLIENT_TIMEOUT);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
//
        return ok;
    }

}
