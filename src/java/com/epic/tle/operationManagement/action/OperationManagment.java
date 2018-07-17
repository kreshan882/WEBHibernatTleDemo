/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.util.OperationListenerMonitor;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.operationManagement.bean.OperationBean;
import com.epic.tle.operationManagement.bean.OperationManageBean;
import com.epic.tle.operationManagement.service.OperationManageFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.net.InetAddress;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author thilina_t
 */
public class OperationManagment extends ActionSupport implements AccessControlService, ModelDriven<OperationManageBean> {

    private OperationManageBean inputBean = new OperationManageBean();
    private boolean isFromDashBoard;

    public OperationManageFactory getService() {
        return new OperationManageFactory();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
      
    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    OperationManageFactory service;
//    SessionUserBean sub;

    @Override
    public boolean checkAccess(String method, int userRole) {
//        if ("List".equals(method)) {
//            return true;
//        }
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.OPERATION_MANAGEMENT;
        inputBean.setPageCode(page);
        String task = null;

        if ("List".equals(method)) {
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
            task = TaskVarList.SEND;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("getMessage".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Download".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Echo".equals(method)) {
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

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.OPERATION_MANAGEMENT, request);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        inputBean.setSend(true);
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

                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.SEND)) {
                    inputBean.setSend(false);
                }
            }
        }
        return true;
    }

    @Override
    public String execute() throws Exception {
        return "success";
    }

    @Override
    public OperationManageBean getModel() {
        try {
            getService().getOperationManageInf().getOperations(inputBean);
            getService().getOperationManageInf().getPagePath(inputBean.getPageCode(), inputBean);

        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String getMessage() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getOperationManageInf().getMessage(inputBean);

        } catch (Exception e) {
//            inputBean.setResponseCode("error");
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "message";
    }

//    public String Echo(){
//        try {
////                inputBean.setOprName(service.getOperationList().get(inputBean.getOperation()));
//                String in = 6+ "|" + getSub().getUserName() + "|" + InetAddress.getLocalHost().getHostAddress();
//                String response = OperationListenerMonitor.send(in.trim());
//                System.out.println("Res  echo :  " + response);
//                if(response!=null && response.equals("00")){
//                    inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
//                    
//                }else{
//                    inputBean.setOprMessage(SystemMessage.OPERATION_SEND_FAIL);
//                    getService().getOperationManageInf().inactiveBindStatus();
//                }
////                String success[] = response.split("\\|");
////                inputBean.setResponseCode(success[0]);
//            
//
//        } catch (Exception e) {
////            inputBean.setResponseCode("error");
//            try{
//                getService().getOperationManageInf().inactiveBindStatus();
//            }catch(Exception sql){
//                sql.printStackTrace();
//                LogFileCreator.writeErrorToLog(e);
//            }
//            addActionError(SystemMessage.OPERATION_SEND_FAIL);
//            e.printStackTrace();
//            LogFileCreator.writeErrorToLog(e);
//        }
//        return "add";
//    
//    }
    public String Add() {
        inputBean.setToken(getSessionToken());
        try {
            if (inputBean.getListOpr() != 0) {
//                inputBean.setOprName(service.getOperationList().get(inputBean.getOperation()));
//                if(Configurations.SERVER_STATUS){
                if (inputBean.getListOpr() == 2) {
                    //operation start
                    getService().getOperationManageInf().insertOperationAlerts(new OperationBean(Integer.toString(inputBean.getListOpr()), getSub().getUserName(), ServletActionContext.getRequest().getRemoteAddr(), "Perform service starting .."));

                    if (Util.runConsoleCommand("service epictle start")) {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                        addActionMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                    } else {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SELECT_FAIL);
                        addActionError(SystemMessage.OPERATION_SEND_FAIL);
                    }

                } else if (inputBean.getListOpr() == 3) {
                    //operation stop
                    getService().getOperationManageInf().insertOperationAlerts(new OperationBean(Integer.toString(inputBean.getListOpr()), getSub().getUserName(), ServletActionContext.getRequest().getRemoteAddr(), "Perform service stoping .."));

                    if (Util.runConsoleCommand("service epictle stop")) {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                        addActionMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                    } else {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SELECT_FAIL);
                        addActionError(SystemMessage.OPERATION_SEND_FAIL);
                    }

                } else if (inputBean.getListOpr() == 7) {
                    //operation restart
                    getService().getOperationManageInf().insertOperationAlerts(new OperationBean(Integer.toString(inputBean.getListOpr()), getSub().getUserName(), ServletActionContext.getRequest().getRemoteAddr(), "Perform service restarting .."));

                    if (Util.runConsoleCommand("service epictle restart")) {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                        addActionMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                    } else {
                        addActionError(SystemMessage.OPERATION_SEND_FAIL);
                    }

                } else {
                    String in = inputBean.getListOpr() + "|" + getSub().getUserName() + "|" + ServletActionContext.getRequest().getRemoteAddr();
                    String response = OperationListenerMonitor.send(in.trim());
                    if (response != null && response.equals("00")) {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                        addActionMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                    } else {
                        inputBean.setOprMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                        addActionMessage(SystemMessage.OPERATION_SEND_SUCCESS);
                    }
                }
//                String success[] = response.split("\\|");
//                inputBean.setResponseCode(success[0]);
//            }else{
////                inputBean.setErrmsg(" Select operation");
//                addActionError(SystemMessage.SERVERSTATUS_FAIL);
//            }
            } else {
                inputBean.setOprMessage(SystemMessage.OPERATION_SELECT_FAIL);
                addActionError(SystemMessage.OPERATION_SELECT_FAIL);
            }

        } catch (Exception e) {
//            inputBean.setResponseCode("error");
            inputBean.setOprMessage(SystemMessage.OPERATION_SEND_FAIL);
            addActionError(SystemMessage.OPERATION_SEND_FAIL);
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "add";
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<OperationManageBean> dataList = null;
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
            dataList = getService().getOperationManageInf().loadListnerData(inputBean, rows, from, orderBy,isFromDashBoard);

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
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";

    }

    public String Download() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getOperationManageInf().getReportDetails(inputBean);
            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.OPERATION_MANAGEMENT, TaskVarList.DOWNLOAD, SystemMessage.SUCCESS_DOWNLOAD_PDF, null, null, null, getSub().getId(),SystemSection.OPERATION_MANAGEMENT,null,null);
        } catch (Exception e) {
//            inputBean.setResponseCode("error");
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "PdfDownload";
    }

    public boolean getIsFromDashBoard() {
        return isFromDashBoard;
    }

    public void setIsFromDashBoard(boolean isFromDashBoard) {
        this.isFromDashBoard = isFromDashBoard;
    }
    
    
}
