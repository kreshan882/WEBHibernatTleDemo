/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.servermanager.bean.SessionConfigurationBean;
import com.epic.tle.servermanager.service.SessionConfFactory;
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
public class SessionConfiguration extends ActionSupport implements ModelDriven<SessionConfigurationBean>, AccessControlService {

    SessionConfigurationBean inputBean = new SessionConfigurationBean();
    SessionConfFactory  service;
    SessionUserBean sub;

    public SessionConfFactory getService() {
        return new SessionConfFactory ();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean)ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    public String Update() {      
        try {

            if (doValidation(inputBean)) {               
                if (getService().getSessionService().updateSessionConfDetails(inputBean)) {
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.SESSION_CONF_UPDATE_SUCCESS,null,null,null,getSub().getId(),SystemSection.CONFIGURATIONS,null,null);
                    LogFileCreator.writeInforToLog(SystemMessage.SESSION_CONF_UPDATE_SUCCESS);
                    addActionMessage(SystemMessage.SESSION_CONF_UPDATE_SUCCESS);
                }
            }
        } catch (Exception ex) {
            addActionError(SystemMessage.SESSION_CONF_UPDATE_FAIL);
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        return "update";
    }

    public String ResetData() {

        try {
            inputBean = getService().getSessionService().viewSessionConfDetails(inputBean);
      
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        return "reset";
    }

    private boolean doValidation(SessionConfigurationBean bean) throws Exception {  
        boolean ok = false;
        try {

            if (inputBean.getMax_pin_counter() == null || inputBean.getMax_pin_counter().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_MAX_PIN_COUNTER_VALUE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getMax_pin_counter()) || (Integer.parseInt(inputBean.getMax_pin_counter()) < 1 || Integer.parseInt(inputBean.getMax_pin_counter()) > 100)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MAX_PIN_COUNTER_VALUE);
                return ok;
            } else if (inputBean.getHost_pro_timeout() == null || inputBean.getHost_pro_timeout().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_HOST_TIMEOUT_VALUE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getHost_pro_timeout()) || (Integer.parseInt(inputBean.getHost_pro_timeout()) < 100 || Integer.parseInt(inputBean.getHost_pro_timeout()) > 100000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_HOST_TIMEOUT_VALUE);
                return ok;
            } else if (inputBean.getLog_fname() == null || inputBean.getLog_fname().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_LOG_FILE_NAME);
                return ok;
            } else if (inputBean.getPkt_size_listeners() == null || inputBean.getPkt_size_listeners().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_LISTENER_PKT_SIZE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getPkt_size_listeners()) || (Integer.parseInt(inputBean.getPkt_size_listeners()) < 100 || Integer.parseInt(inputBean.getPkt_size_listeners()) > 5000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_LISTENER_PKT_SIZE);
                return ok;
            } else if (inputBean.getPkt_size_channels() == null || inputBean.getPkt_size_channels().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_CHANNALE_PKT_SIZE);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getPkt_size_channels()) || (Integer.parseInt(inputBean.getPkt_size_channels()) < 100 || Integer.parseInt(inputBean.getPkt_size_channels()) > 5000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_CHANNALE_PKT_SIZE);
                return ok;
            } else if (inputBean.getMonitor_ip() == null || inputBean.getMonitor_ip().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_MONITOR_IP);
                return ok;
            } else if (!inputBean.getMonitor_ip().matches("([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])(\\.([0-9]|[1-9][0-9]|1[0-9][0-9]|2[0-4][0-9]|25[0-5])){3}")) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MONITOR_IP);
                return ok;
            } else if (inputBean.getMonitor_port() == null || inputBean.getMonitor_port().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_MONITOR_PORT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getMonitor_port()) || (Integer.parseInt(inputBean.getMonitor_port()) < 3000 || Integer.parseInt(inputBean.getMonitor_port()) > 50000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MONITOR_PORT);
                return ok;
            } else if (inputBean.getMonitor_timeout() == null || inputBean.getMonitor_timeout().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_MONITOR_TIME_OUT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getMonitor_timeout()) || (Integer.parseInt(inputBean.getMonitor_timeout()) < 1000 || Integer.parseInt(inputBean.getMonitor_timeout()) > 10000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MONITOR_TIME_OUT);
                return ok;
            }else if (inputBean.getChannel_timeout() == null || inputBean.getChannel_timeout().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_CHANNEL_FIND_TIME_OUT);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getChannel_timeout())) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_CHANNEL_FIND_TIME_OUT);
                return ok;
            }  
            else if (inputBean.getHtest_time_period() == null || inputBean.getHtest_time_period().equals("")) {
                addActionError(SystemMessage.SESSION_CONF_EMPTY_HOST_TEST_TIME);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getHtest_time_period()) || (Integer.parseInt(inputBean.getHtest_time_period()) < 100 || Integer.parseInt(inputBean.getHtest_time_period()) > 50000)) {
                addActionError(SystemMessage.SESSION_CONF_INVALID_MONITOR_IP);
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

    @Override
    public SessionConfigurationBean getModel() {
        inputBean.getReplyAtchLvlMap().put("1", "Level 1");
        inputBean.getReplyAtchLvlMap().put("2", "Level 2");
        inputBean.getReplyAtchLvlMap().put("3", "Level 3");
        inputBean.getAltNotificationStatusMap().put("1", "Enable");
        inputBean.getAltNotificationStatusMap().put("2", "Disable");
        inputBean.getTxnFailNotificationStatusMap().put("1", "Enable");
        inputBean.getTxnFailNotificationStatusMap().put("2", "Disable");
        inputBean.getLogLevelMap().put("0", "Level 0");
        inputBean.getLogLevelMap().put("1", "Level 1");
        inputBean.getLogLevelMap().put("2", "Level 2");
        inputBean.getLogLevelMap().put("3", "Level 3");
        inputBean.getLogLevelMap().put("4", "Level 4");
        inputBean.getLogLevelMap().put("5", "Level 5");
        inputBean.getHsizeChannelsMap().put("1", "2");
        inputBean.getHsizeChannelsMap().put("2", "3");
        inputBean.getHsizeChannelsMap().put("3", "3");
        inputBean.getHsizeListenersMap().put("1", "2");
        inputBean.getHsizeListenersMap().put("2", "3");
        inputBean.getHsizeListenersMap().put("3", "4");
        inputBean.getRequestTPDUMap().put("1", "Enable");
        inputBean.getRequestTPDUMap().put("2", "Disable");
        inputBean.getResponseTPDUMap().put("1", "Enable");
        inputBean.getResponseTPDUMap().put("2", "Disable");
        inputBean.getTpduModifyOptionMap().put("1", "Active");
        inputBean.getTpduModifyOptionMap().put("2", "Inactive");
        inputBean.getTpduModifyPositionMap().put("1", "Source");
        inputBean.getTpduModifyPositionMap().put("2", "Destination");
        try {
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
            inputBean = getService().getSessionService().viewSessionConfDetails(inputBean);
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
        String page = PageVarList.SESSION_CONFIGURATIONS;
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
            status = true;
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }
     
      private boolean applyUserPrivileges(){
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.SESSION_CONFIGURATIONS, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } 
            }
        }

        return true;
    }

}
