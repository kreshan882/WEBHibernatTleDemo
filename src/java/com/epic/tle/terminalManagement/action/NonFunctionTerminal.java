package com.epic.tle.terminalManagement.action;

import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.terminalManagement.action.*;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalDataBean;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalInputBean;
import com.epic.tle.terminalManagement.service.ExcelReportNonFunctionTerminal;
import com.epic.tle.terminalManagement.service.NonFunctionTerminaFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.PageVarList;
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
 * @author nipun_t
 */
public class NonFunctionTerminal extends ActionSupport implements ModelDriven<NonFunctionTerminalInputBean>, AccessControlService {

    NonFunctionTerminalInputBean inputBean = new NonFunctionTerminalInputBean();
    NonFunctionTerminaFactory service;

    public NonFunctionTerminaFactory getService() {
        return new NonFunctionTerminaFactory();
    }

    @Override
    public String execute(){
        return "success";
    }

    @Override
    public NonFunctionTerminalInputBean getModel() {
        return inputBean;
    }

    public String List() {
        List<NonFunctionTerminalDataBean> dataList = null;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";
            if (null != inputBean.getSidx() && !inputBean.getSidx().isEmpty()) {
                orderBy = "order by wu." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getRegisterTerminalServiceInf().loadTerminalUsers(inputBean, rows, from, orderBy);
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
    
    public String XSLcreat(){
         
        try {
            ByteArrayOutputStream outputStream = null;
            Object object =ExcelReportNonFunctionTerminal.generateExcelReport(inputBean);
            
            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "excelreportnonterminal";
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.NON_FUNCTION_TERMINAL;
        String task = null;
        if("addTerminal".equals(method)) {
            task = TaskVarList.ADD;
        } else if("uploadFile".equals(method)) {
            task = TaskVarList.UPLOAD;
        }else if("List".equals(method)) {
            task = TaskVarList.VIEW;
        }else if("XSLcreat".equals(method)) {
            task = TaskVarList.VIEW;
        }else if("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        }else if("Find".equals(method)) {
            task = TaskVarList.VIEW;
        }else if("Update".equals(method)) {
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
    
    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.NON_FUNCTION_TERMINAL, request);
        inputBean.setAdd(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setUpdate(false);
                } 
            }
        }
        return true;
    }

}
