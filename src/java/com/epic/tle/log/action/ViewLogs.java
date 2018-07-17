/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.log.action;

import com.epic.tle.log.bean.ViewLogsDataBean;
import com.epic.tle.log.bean.ViewLogsInputBean;
import com.epic.tle.log.service.ViewLogsService;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.Operation;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nipun_t
 */
public class ViewLogs extends ActionSupport implements ModelDriven<ViewLogsInputBean>, AccessControlService {

    ViewLogsInputBean inputBean = new ViewLogsInputBean();
    ViewLogsService service;
    SessionUserBean sub;

    public ViewLogsService getService() {
        return new ViewLogsService();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String List() {
        List<ViewLogsDataBean> dataList = null;

        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && inputBean.getSidx().isEmpty()) {
                orderBy = " order by " + inputBean.getSidx() + " " + inputBean.getSord();
            }

            dataList = getService().loadLogFileTable(inputBean, orderBy, to, from);

            if (dataList != null && !dataList.isEmpty()) {
                records = dataList.get(0).getFullCount();
                inputBean.setRecords(records);
                inputBean.setGridModel(dataList);
                int total = (int) Math.ceil((double) records / (double) rows);
                inputBean.setTotal(total);

            } else {
                inputBean.setRecords(0L);
                inputBean.setTotal(0);
            }

        } catch (Exception fex) {
            fex.printStackTrace();
            LogFileCreator.writeErrorToLog(fex);
            addActionError("File Path Incorrect");
            return SUCCESS;
        }

        return "list";
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String Download() {

        try {
            File fileToDownload = new File(inputBean.getFilePath());
            inputBean.setContentLength(fileToDownload.length());
            inputBean.setInputStream(new FileInputStream(fileToDownload));
            inputBean.setFileName(fileToDownload.getName());
            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.LOG_FILE_MANAGEMENT, TaskVarList.DOWNLOAD, SystemMessage.LOG_FILE_DOWNLOADED, null, null, null, getSub().getId(), SystemSection.LOG_FILE_MANAGEMENT, null, null);
            LogFileCreator.writeInforToLog(SystemMessage.LOG_FILE_DOWNLOADED);
        } catch (Exception e) {
            addActionError(SystemMessage.LOG_FILE_DOWNLOAD_FAIL);
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }

        return "download";
    }

    public String DownloadAllSelectedLog() {
//        String logArchivePath = "/opt/epicline/logs";
        String logArchivePath = "/epicline/logs/est";
        String logDirPath = "/epicline/logs/testtemp";
        inputBean.setLogTypes(new Date().toString());
//        String logDirPath = "/opt/tmpFE";

        try {
            if (!inputBean.getLogTypes().equals("-1") || inputBean.getLogTypes() != null) {
//                String dirPath = Util.getOSLogPath(logArchivePath) + inputBean.getLogTypesMap().get(inputBean.getLogTypes());
                String dirPath = Util.getOSLogPath(logArchivePath) + inputBean.getLogTypes();
//                String zipFilePath = Util.getOSLogPath(logDirPath) + inputBean.getLogTypesMap().get(inputBean.getLogTypes()) + ".zip";
                String zipFilePath = Util.getOSLogPath(logDirPath) + inputBean.getLogTypes() + ".zip";
                File dir = new File(dirPath);

                getService().zipDirectory(dir, zipFilePath);
                File fileToDownload = new File(zipFilePath);
                inputBean.setContentLength(fileToDownload.length());
                inputBean.setInputStream(new FileInputStream(fileToDownload));
                inputBean.setFileName(fileToDownload.getName());
                fileToDownload.delete();

                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.LOG_FILE_MANAGEMENT, TaskVarList.DOWNLOAD, SystemMessage.LOG_FILE_DOWNLOADED, null, null, null, getSub().getId(), SystemSection.LOG_FILE_MANAGEMENT, null, null);
                LogFileCreator.writeInforToLog(SystemMessage.LOG_FILE_DOWNLOADED);
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }

        return "downloadselectedlog";
    }

    public String Delete() {
        File logfile = null;
        try {
            String logArchivePath = Configurations.LOG_PATH + inputBean.getLogTypes();
            logfile = new File(Util.getOSLogPath(logArchivePath) + inputBean.getFileName());
            logfile.delete();
            inputBean.setDmessage(SystemMessage.LOG_FILE_DELETED);
            inputBean.setIsDeleted(true);

            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.LOG_FILE_MANAGEMENT, TaskVarList.DELETE, SystemMessage.LOG_FILE_DELETED, null, null, null, getSub().getId(), SystemSection.LOG_FILE_MANAGEMENT, "{ Log_File_Name : " + inputBean.getFileName() + " }", null);
            LogFileCreator.writeInforToLog(SystemMessage.LOG_FILE_DELETED);
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
            inputBean.setDmessage(SystemMessage.LOG_FILE_DELETE_ERROR);
            inputBean.setIsDeleted(false);
        }

        return "delete";
    }

    public String ClearLog() throws Exception {
        try {

            List<String> filesListInDir = new ArrayList<String>();

            String logArchivePath = Configurations.LOG_PATH + inputBean.getLogTypesMap().get(inputBean.getLogTypes());

            File logDir = new File(Util.getOSLogPath(logArchivePath));
            filesListInDir = Util.getPopulateFilesList(logDir);

            for (String path : filesListInDir) {
                File deleteFile = new File(path);
                deleteFile.delete();
            }
            inputBean.setDmessage(SystemMessage.LOG_FILE_ALL_LOG_CLEAR_SUCCESSFULY);
            inputBean.setIsDeleted(true);

            Util.insertHistoryRecord(
                    LogTypes.TLEWEBAPP,
                    getSub().getUserLevel(),
                    SystemModule.LOG_FILE_MANAGEMENT,
                    TaskVarList.DELETE,
                    SystemMessage.LOG_FILE_ALL_LOG_CLEAR_SUCCESSFULY + " { File_Path :" + logArchivePath + " }",
                    null, null, null,
                    getSub().getId(),
                    SystemSection.LOG_FILE_MANAGEMENT,
                    null, null);
            LogFileCreator.writeInforToLog(SystemMessage.LOG_FILE_ALL_LOG_CLEAR_SUCCESSFULY);
        } catch (Exception ex) {
            ex.printStackTrace();
            inputBean.setDmessage(SystemMessage.LOG_FILE_ALL_LOG_CLEAR_ERROR);
            inputBean.setIsDeleted(false);
            LogFileCreator.writeErrorToLog(ex);
        }

        return "clearlog";
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.LOG_FILE_MANAGEMENT;
        String task = null;
        if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("ClearLog".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("ClearLog".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Download".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("DownloadAllSelectedLog".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.LOG_FILE_MANAGEMENT, request);
        inputBean.setDownload(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setUpdate(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setDownload(false);
                }
            }
        }
        return true;
    }

    @Override
    public ViewLogsInputBean getModel() {
//        DownloadAllSelectedLog();
//        inputBean.getLogTypesMap().put("1", "alert");
//        inputBean.getLogTypesMap().put("2", "debug");
//        inputBean.getLogTypesMap().put("3", "dump");
//        inputBean.getLogTypesMap().put("4", "errors");
//        inputBean.getLogTypesMap().put("5", "fail");
//        inputBean.getLogTypesMap().put("6", "hexdump");
//        inputBean.getLogTypesMap().put("7", "infors");
//        inputBean.getLogTypesMap().put("8", "init");
//        inputBean.getLogTypesMap().put("9", "rawdata");
//        inputBean.getLogTypesMap().put("10", "timeout");
//        inputBean.getLogTypesMap().put("11", "trace");

        return inputBean;
    }

}
