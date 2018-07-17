/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.action;

import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.host.bean.NIIBean;
import com.epic.tle.host.service.NiiFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleNii;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.Operation;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
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
 * @author thilina_t
 */
public class NIIPopAction extends ActionSupport implements AccessControlService, ModelDriven<NIIBean> {

    private NIIBean inputBean = new NIIBean();
    NiiFactory service;
    SessionUserBean session;

    public NiiFactory getService() {
        return new NiiFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    
    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.NII_CONFIGURATION;
        String task = null;
        if ("list".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Load".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("AssignBin".equals(method)) {
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

    @Override
    public NIIBean getModel() {
        return getInputBean();
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.NII_CONFIGURATION, request);
        getInputBean().setAdd(true);
        getInputBean().setDelete(true);
        getInputBean().setView(true);
        getInputBean().setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    getInputBean().setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    getInputBean().setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    getInputBean().setView(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    getInputBean().setUpdate(false);
                }
            }
        }
        return true;
    }


    public String Add() {
        try {
            if (doValidation(getInputBean())) {
                boolean ok = getService().getNiiInf().insertNii(getInputBean());
                if (ok == true) {
                    addActionMessage(SystemMessage.NII_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.HOST_MANAGEMENT,
                            Operation.ADD + "", 
                            SystemMessage.NII_ADD_SUCCESS+" with name = "+inputBean.getNiiName(),
                            null, null, null,
                            getSession().getId(),
                            SystemSection.CHANNEL__MANAGEMENT,
                            null,null);
                }
            }

        } catch (Exception e) {
            getInputBean().setSuccess(false);
            getInputBean().setMessage(SystemMessage.NII_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }

        return "Add";

    }

    private boolean doValidation(NIIBean bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getNii()== null || bean.getNii().isEmpty()) {
                getInputBean().setSuccess(false);
                addActionError(SystemMessage.HL_EMPTY_NII);
                return ok;
            } else if (!Util.validateNUMBER(bean.getNii())) {
                getInputBean().setSuccess(false);
                addActionError(SystemMessage.NII_INVALID_NAME);
                return ok;
            } else if (bean.getMapNii()== null || bean.getMapNii().isEmpty()) {
                getInputBean().setSuccess(false);
                addActionError(SystemMessage.HL_EMPTY_MAPNII);
                return ok;
            } else if (!Util.validateNUMBER(bean.getMapNii())) {
                getInputBean().setSuccess(false);
                addActionError(SystemMessage.NII_INVALID_MAP_NII);
                return ok;
            }else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }


    public String list() {

        List<NIIBean> dataList;
        try {
            int rows = getInputBean().getRows();
            int page = getInputBean().getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = "order by bb." + getInputBean().getSidx() + " " + getInputBean().getSord();
            }
            
            dataList = getService().getNiiInf().loadNii(getInputBean(), rows, from, orderBy);

            if (!dataList.isEmpty()) {
                records = dataList.get(0).getFullCount();
                getInputBean().setRecords(records);
                getInputBean().setGridModel(dataList);
                int total = (int) Math.ceil((double) records / (double) rows);
                getInputBean().setTotal(total);
            } else {
                getInputBean().setRecords(0L);
                getInputBean().setTotal(0);
            }

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String Delete() {

        try {
            String oldVal=Util.getOldorNewVal(new EpicTleNii(), "wu..id.groupId ='"+inputBean.getGroup()+"' and wu.id.mapNii='"+inputBean.getNiiName()+"' and wu.id.nii='"+inputBean.getMapNii()+"'");
            if (getService().getNiiInf().deleteNii(getInputBean())) {               
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.HOST_MANAGEMENT, TaskVarList.DELETE, SystemMessage.NII_DELETE_SUCESS, null, null, getInputBean().getDbinId(), getSession().getId(),SystemSection.CHANNEL__MANAGEMENT,oldVal,null);
                LogFileCreator.writeInforToLog(SystemMessage.NII_DELETE_SUCESS);
                getInputBean().setIsDeleted(true);
                getInputBean().setDmessage(SystemMessage.NII_DELETE_SUCESS);
            } else {
                getInputBean().setIsDeleted(false);
                getInputBean().setDmessage(SystemMessage.NII_DELETE_FAIL);
            }
        } catch (Exception e) {
            getInputBean().setIsDeleted(false);
            getInputBean().setDmessage(SystemMessage.NII_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

    /**
     * @return the inputBean
     */
    public NIIBean getInputBean() {
        return inputBean;
    }

    /**
     * @param inputBean the inputBean to set
     */
    public void setInputBean(NIIBean inputBean) {
        this.inputBean = inputBean;
    }
}
