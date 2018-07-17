/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.systemtools.bean.SystemAlertsDataBean;
import com.epic.tle.systemtools.bean.SystemAlertsInputBean;
import com.epic.tle.systemtools.service.ExcelReportSystemAlerts;
import com.epic.tle.systemtools.service.SystemAlertsServiceFactory;
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
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author dimuthu_h
 */
public class SystemAlerts extends ActionSupport implements ModelDriven<SystemAlertsInputBean>, AccessControlService {

    SystemAlertsInputBean inputBean = new SystemAlertsInputBean();
    SystemAlertsServiceFactory service;
    SessionUserBean sessionBean;

    public SystemAlertsServiceFactory getService() {
        return new SystemAlertsServiceFactory();
    }
    
    public SessionUserBean getSessionBean() {
         return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String List() {
        List<SystemAlertsDataBean> dataList = null;
        try {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = " order by sa." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadAlertsDetails(inputBean, rows, from, orderBy);

            if (null != dataList && !dataList.isEmpty()) {
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
            ex.printStackTrace();
            //LogFileCreator.writeErrorToLog(ex);
        }

        return "list";
    }

    public String ListTrans() {
        List<SystemAlertsDataBean> dataList = null;
        try {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = " order by sa." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadAlertsDetailsTrans(inputBean, rows, from, orderBy);

            if (null != dataList && !dataList.isEmpty()) {
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
            //LogFileCreator.writeErrorToLog(ex);
        }

        return "list";
    }

    public String Loading() {
        List<SystemAlertsDataBean> dataList = null;
        try {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = " order by sa." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadAlertsDetailsSystem(inputBean, rows, from, orderBy);

            if (null != dataList && !dataList.isEmpty()) {
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
            //LogFileCreator.writeErrorToLog(ex);
        }

        return "loading";
    }

    public String XSLcreat() {
        String retMsg = null;
        try {
            ByteArrayOutputStream outputStream = null;
            Object object = ExcelReportSystemAlerts.generateExcelReport(inputBean);

            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
                retMsg = "excelreportsystemalerts";
            } else if (object instanceof ByteArrayOutputStream) {
                outputStream = (ByteArrayOutputStream) object;
                inputBean.setZipStream(new ByteArrayInputStream(outputStream.toByteArray()));
                retMsg = "zip";
            }
             LogFileCreator.writeInforToLog(SystemMessage.SUCCESS_EXPORT);
              Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSessionBean().getUserLevel(), SystemModule.SYSTEM_TOOLS, TaskVarList.DOWNLOAD, SystemMessage.SUCCESS_EXPORT, null, null, null, getSessionBean().getId(),SystemSection.SYSTEM_NOTIFICATION,null,null);

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return retMsg;
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SystemAlertsInputBean getModel() {
        try {
            for (EpicTleNodetype list1 : (List<EpicTleNodetype>) Util.getMasterValues(0, 2, "EpicTleNodetype")) {
                inputBean.getNodes().put(list1.getCode(), list1.getDescription());
            }
            inputBean.setRiskMap(getService().getSessionService().getRisklevel());
            inputBean.setAlertMap(getService().getSessionService().getAlertType());
            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean; //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        if ("ListTrans".equals(method) || "Loading".equals(method)) {
            return true;
        }
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.SYSTEM_ALERTS;
        inputBean.setPageCode(page);
        String task = null;

        if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("downloadpdf".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("ListTrans".equals(method)) {
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
        } else if ("Loading".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.SYSTEM_ALERTS, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setVdownload(true);
        inputBean.setView(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setVdownload(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                }
            }
        }

        return true;
    }

}
