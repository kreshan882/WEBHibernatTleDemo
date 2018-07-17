/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.action;

import com.epic.tle.host.bean.NIIConfigBean;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleNodetype;
import com.epic.tle.systemtools.bean.SystemProcessDataBean;
import com.epic.tle.systemtools.bean.SystemProcessInputBean;
import com.epic.tle.systemtools.service.ExcelReportSystemProcess;
import com.epic.tle.systemtools.service.SystemProcessTimeFactory;
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
 * @author thilina_t
 */
public class SystemProcess extends ActionSupport implements ModelDriven<SystemProcessInputBean>, AccessControlService {

    SystemProcessInputBean inputBean = new SystemProcessInputBean();
    SessionUserBean sessionBean;

    public SystemProcessTimeFactory getService() {
        return new SystemProcessTimeFactory();
    }
    
    public SessionUserBean getSessionBean() {
         return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String List() {
        List<SystemProcessDataBean> dataList = null;
        try {

            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by sa." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getSessionService().loadProcessDetails(inputBean, rows, from, orderBy);

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
            //LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);

        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "list";
    }

    public String XSLcreat() {
        String retMsg = null;
        try {
            if(inputBean.getFromdate().equals("")||inputBean.getTodate().equals("")){
            inputBean.setFromdate(Util.currentDate());
            inputBean.setTodate(Util.currentDate());            
            }
            ByteArrayOutputStream outputStream = null;
            Object object = ExcelReportSystemProcess.generateExcelReport(inputBean);

            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
                retMsg = "excelreportsystemtransaction";
            } else if (object instanceof ByteArrayOutputStream) {
                outputStream = (ByteArrayOutputStream) object;
                inputBean.setZipStream(new ByteArrayInputStream(outputStream.toByteArray()));
                retMsg = "zip";
            }
             LogFileCreator.writeInforToLog(SystemMessage.SUCCESS_EXPORT);
              Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSessionBean().getUserLevel(), SystemModule.SYSTEM_TOOLS, TaskVarList.DOWNLOAD, SystemMessage.SUCCESS_EXPORT, null, null, null, getSessionBean().getId(),SystemSection.TRANSACTION_PROCESS,null,null);

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return retMsg;
    }

    public String Search() {

        List<SystemProcessDataBean> dataList = null;
        try {

            if (doValidation(inputBean)) {

                int rows = inputBean.getRows();
                int page = inputBean.getPage();
                int to = (rows * page);
                int from = to - rows;
                long records = 0;
                String orderBy = "";

                if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                    orderBy = "order by sh." + inputBean.getSidx() + " " + inputBean.getSord();
                }
                dataList = getService().getSessionService().loadProcessDetails(inputBean, rows, from, orderBy);

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
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }

        return "list";
    }

    @Override
    public String execute() {

        return SUCCESS;
    }

    @Override
    public SystemProcessInputBean getModel() {

        try {

            getService().getSessionService().getPagePath(inputBean.getPageCode(), inputBean);
            for (EpicTleNodetype list1 : (List<EpicTleNodetype>) Util.getMasterValues(0, 2, "EpicTleNodetype")) {
            inputBean.getNodes().put(list1.getCode(), list1.getDescription());
        }
//            getService().getSessionService().loadMap(inputBean);

        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    private boolean doValidation(SystemProcessInputBean bean) throws Exception {
        boolean ok = false;
        try {
            if (!Util.validateNUMBER(bean.getFrom())) {
                addActionError(SystemMessage.PROCESS_TIME);
                return ok;
            } else if (!Util.validateNUMBER(bean.getTo())) {
                addActionError(SystemMessage.PROCESS_TIME);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return ok;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.TRANSACTION_TIME;
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
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Search".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.TRANSACTION_TIME, request);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setView(true);
        inputBean.setVdownload(true);

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
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setVdownload(false);
                }
            }
        }

        return true;
    }

}
