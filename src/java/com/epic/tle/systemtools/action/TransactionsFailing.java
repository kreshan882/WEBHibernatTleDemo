/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.action;

import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.systemtools.bean.SystemTnxFailDataBean;
import com.epic.tle.systemtools.bean.SystemTnxFailInputBean;
import com.epic.tle.systemtools.service.SystemTnxFailingFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.PageVarList;
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
public class TransactionsFailing extends ActionSupport implements ModelDriven<SystemTnxFailInputBean>, AccessControlService {

    SystemTnxFailInputBean inputBean = new SystemTnxFailInputBean();
    SystemTnxFailingFactory service = new SystemTnxFailingFactory();

    public String List() {
        List<SystemTnxFailDataBean> dataList = null;
        boolean ok = false;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = " order by txn." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = service.getSessionService().loadTnxFialDetails(inputBean, rows, from, orderBy);

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
            LogFileCreator.writeErrorToLog(ex);
        }

        return "list";
    }

//    public String XSLcreat() {
//        String retMsg = null;
//        try {
//            ByteArrayOutputStream outputStream = null;
//            Object object = ExcelReportTransactionFailing.generateExcelReport(inputBean);
//
//            if (object instanceof XSSFWorkbook) {
//                XSSFWorkbook workbook = (XSSFWorkbook) object;
//                outputStream = new ByteArrayOutputStream();
//                workbook.write(outputStream);
//                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
//                retMsg = "excelreporttransactionfailing";
//            } else if (object instanceof ByteArrayOutputStream) {
//                outputStream = (ByteArrayOutputStream) object;
//                inputBean.setZipStream(new ByteArrayInputStream(outputStream.toByteArray()));
//                retMsg = "zip";
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            LogFileCreator.writeErrorToLog(e);
//        }
//        return retMsg;
//    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.TRANSACTIONS_FAILINGS;
        inputBean.setCode(page);
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

    @Override
    public SystemTnxFailInputBean getModel() {
        try {
            service.getSessionService().getPagePath(inputBean.getCode(), inputBean);
            service.getSessionService().loadDataIntoMap(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        return inputBean;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.REGISTER_FIELD_ENGINEER, request);
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
