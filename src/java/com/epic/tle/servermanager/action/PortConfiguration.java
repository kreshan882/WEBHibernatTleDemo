/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTlePortconfig;
import com.epic.tle.servermanager.bean.PortConfigurationDataBean;
import com.epic.tle.servermanager.bean.PortConfigurationInputBean;
import com.epic.tle.servermanager.service.PortConfFactory;
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
public class PortConfiguration extends ActionSupport implements ModelDriven<PortConfigurationInputBean>, AccessControlService {

    PortConfigurationInputBean inputBean = new PortConfigurationInputBean();
    PortConfFactory service;
    SessionUserBean sub;
    EpicTlePortconfig portConfig=new EpicTlePortconfig();

    public PortConfFactory getService() {
        return new PortConfFactory();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<PortConfigurationDataBean> dataList = null;
        try {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = " order by pc." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadPortConfDetails(inputBean, rows, from, orderBy);

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
            getService().getSessionService().findPortDetails(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }

    public String Update() {
        inputBean.setToken(getSessionToken());
        try {
            if (doValidation(inputBean)) {
                String oldVal=Util.getOldorNewVal(portConfig, "wu.sid ='"+inputBean.getSid()+"'");
                getService().getSessionService().updatePortConfiguration(inputBean);
                String newVal=Util.getOldorNewVal(portConfig, "wu.sid ='"+inputBean.getSid()+"'");
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.SERVER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.PORT_CONF_UPDATED, null, null, null, getSub().getId(),SystemSection.PORT_CONFIGURATION,oldVal,newVal);
                LogFileCreator.writeInforToLog(SystemMessage.PORT_CONF_UPDATED);
                addActionMessage(SystemMessage.PORT_CONF_UPDATED);

            }
        } catch (Exception ex) {
            addActionError(SystemMessage.PORT_CONF_UPDATE_ERROR);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }

        return "update";
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public PortConfigurationInputBean getModel() {
        inputBean.getDatarateMap().put("000110", "110");
        inputBean.getDatarateMap().put("000300", "300");
        inputBean.getDatarateMap().put("001200", "1200");
        inputBean.getDatarateMap().put("002400", "2400");
        inputBean.getDatarateMap().put("004800", "4800");
        inputBean.getDatarateMap().put("009600", "9600");
        inputBean.getDatarateMap().put("019200", "19200");
        inputBean.getDatarateMap().put("038400", "38400");
        inputBean.getDatarateMap().put("057200", "57200");
        inputBean.getDatarateMap().put("115200", "115200");
        inputBean.getDatarateMap().put("230400", "230400");
        inputBean.getDatarateMap().put("460800", "460800");
        inputBean.getDatarateMap().put("921600", "921600");
        inputBean.getDatabitMap().put("5", "5");
        inputBean.getDatabitMap().put("6", "6");
        inputBean.getDatabitMap().put("7", "7");
        inputBean.getDatabitMap().put("8", "8");
        inputBean.getStopbitMap().put("1", "1");
        inputBean.getStopbitMap().put("2", "2");
        inputBean.getParityMap().put("0", "Non");
        inputBean.getParityMap().put("1", "Odd");
        inputBean.getParityMap().put("2", "Even");
        inputBean.getStatusMap().put("1", "Active");
        inputBean.getStatusMap().put("2", "Inactive");
        inputBean.getPortMap().put("PL", "Parallel Port");
        inputBean.getPortMap().put("COM", "Serial Port");
        try {
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean;
    }

    private boolean doValidation(PortConfigurationInputBean inputBean) throws Exception {
        boolean ok = false;
        try {

            if (inputBean.getDatarate().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_DATARATE);
                return ok;
            } else if (inputBean.getDatabit().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_DATABIT);
                return ok;
            } else if (inputBean.getParity().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_PARITY);
                return ok;
            } else if (inputBean.getStopbit().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_STOPBITS);
                return ok;
            } else if (inputBean.getPort().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_PORT);
                return ok;
            } else if (inputBean.getStatus().equals("-1")) {
                addActionError(SystemMessage.PORT_CONF_INVALID_STATUS);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }

        return ok;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.PORT_CONFIGURATIONS;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.PORT_CONFIGURATIONS, request);
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
