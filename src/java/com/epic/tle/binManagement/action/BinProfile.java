/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.action;

import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.binManagement.service.BinFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleBlockBin;
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
import com.epic.tle.util.constant.TokenConst;
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
public class BinProfile extends ActionSupport implements AccessControlService, ModelDriven<BinBean> {

    private BinBean inputBean = new BinBean();
    BinFactory service;
    SessionUserBean session;
    EpicTleBlockBin epicTleBlockBin = new EpicTleBlockBin();

    public BinFactory getService() {
        return new BinFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    @Override
    public String execute() throws Exception {
        return SUCCESS;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.BLOCK_BIN_PROFILE;
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
    public BinBean getModel() {
        return getInputBean();
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.BLOCK_BIN_PROFILE, request);
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

    /**
     * @return the inputBean
     */
    public BinBean getInputBean() {
        return inputBean;
    }

    /**
     * @param inputBean the inputBean to set
     */
    public void setInputBean(BinBean inputBean) {
        this.inputBean = inputBean;
    }

    public String Add() {
        inputBean.setToken(getSessionToken());
        try {

            if (doValidation(inputBean)) {

                boolean ok = getService().getBinProfileInf().insertBin(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.BIN_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(),
                            SystemModule.BIN_MANAGEMENT,
                            TaskVarList.ADD,
                            "Bin Range { " + inputBean.getLowValue() + " : " + inputBean.getUpperValue() + " } -> " + SystemMessage.BIN_ADD_SUCCESS,
                            null, null,
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.BLOCK_BIN_PROFILE,
                            null, null
                    );
                } else {
                    addActionError(SystemMessage.BIN_REGISTRATION_FAIL);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.BIN_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }

        return "Add";

    }

    private boolean doValidation(BinBean bean) throws Exception {
        boolean ok = true;
        try {
            if (bean.getLowValue() <= 0) {
                inputBean.setSuccess(false);
                addActionError("Low value can't be empty");
                ok = false;
            } else if (bean.getUpperValue() <= 0) {
                inputBean.setSuccess(false);
                addActionError("Upper value cant' be empty");
                ok = false;
            } //            else if (bean.getUpperValue() == bean.getLowValue()) {
            //                inputBean.setSuccess(false);
            //                addActionError("Upper value and lower value can't be equals");
            //                ok = false;
            //            } 
            else if (bean.getUpperValue() < bean.getLowValue()) {
                inputBean.setSuccess(false);
                addActionError("Low value can not be higer than upper value");
                ok = false;
            } else if (Integer.toString(bean.getLowValue()).length() < 6) {
                inputBean.setSuccess(false);
                addActionError("Low value must be minimum 6 charactors");
                ok = false;
            } else if (Integer.toString(bean.getUpperValue()).length() < 6) {
                inputBean.setSuccess(false);
                addActionError("Upper value must be minimum 6 charactors");
                ok = false;
            } else if (getService().getBinProfileInf().checkRange(inputBean)) {
                inputBean.setSuccess(false);
                addActionError("This bin range is already exist .! please use different range");
                ok = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            inputBean.setSuccess(false);
            addActionError("Bin failed");
            throw e;

        }
        return ok;

    }

    private boolean doUpValidation(BinBean bean) throws Exception {
        boolean ok = false;
        try {
            if (bean.getUpBinName() == null || bean.getUpBinName().isEmpty()) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.BIN_INVALID_BLANK_NAME);
                return ok;
            } else if (!Util.validateNUMBER(bean.getUpBinName())) {
                inputBean.setSuccess(false);
                inputBean.setMessage(SystemMessage.BIN_INVALID_NAME);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    public String list() {
        inputBean.setToken(getSessionToken());
        List<BinBean> dataList;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = "order by bb." + inputBean.getSidx() + " " + inputBean.getSord();
            }

            dataList = getService().getBinProfileInf().loadBin(inputBean, rows, from, orderBy);

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
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal = Util.getOldorNewVal(epicTleBlockBin, " wu.epicTleBinProfile.id =" + inputBean.getId() + " and wu.id.low_value=" + inputBean.getLowValue() + " and wu.id.upper_value=" + inputBean.getUpperValue());
            if (getService().getBinProfileInf().deleteBin(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.DELETE, SystemMessage.BIN_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(), SystemSection.BLOCK_BIN_PROFILE, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.BIN_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.BIN_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.BIN_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.BIN_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }

        return "Delete";
    }

}
