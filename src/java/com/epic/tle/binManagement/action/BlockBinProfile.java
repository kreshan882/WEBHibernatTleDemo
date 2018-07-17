/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.action;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.binManagement.service.BinProfileFactory;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleBinProfile;
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
public class BlockBinProfile extends ActionSupport implements AccessControlService, ModelDriven<BinProfileBean> {

    private BinProfileBean inputBean = new BinProfileBean();
    BinProfileFactory service;
    SessionUserBean session;
    EpicTleBinProfile epicTleBinProfile=new EpicTleBinProfile();

    public BinProfileFactory getService() {
        return new BinProfileFactory();
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
    public BinProfileBean getModel() {
        try {
            inputBean.setUpBinStatusMap(Util.getStatusValues(0, 2));
            getService().getBlockBinProfileInf().getPagePath(inputBean.getPageCode(), inputBean);
        } catch (Exception e) {
        }
        return inputBean;
    }

    /**
     * @return the inputBean
     */
    public BinProfileBean getBinProfileBean() {
        return inputBean;
    }

    /**
     * @param inputBean the inputBean to set
     */
    public void setBinProfileBean(BinProfileBean inputBean) {
        this.inputBean = inputBean;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {

        boolean status;
        applyUserPrivileges();
        String page = PageVarList.BLOCK_BIN_PROFILE;
        inputBean.setPageCode(page);
        String task = null;
        if ("list".equals(method)) {
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
            task = TaskVarList.ADD;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("AssignBin".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.BLOCK_BIN_PROFILE, request);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setUpdate(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                }
            }
        }
        return true;
    }

    public String list() {
        inputBean.setToken(getSessionToken());
        List<BinProfileBean> dataList;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = "order by wu." + inputBean.getSidx() + " " + inputBean.getSord();
            }

            dataList = getService().getBlockBinProfileInf().loadBinProfile(inputBean, rows, from, orderBy);

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
           // LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    private boolean doValidation(BinProfileBean bean) throws Exception {
        boolean ok = false;
        try {
            String BlockBin = getService().getBlockBinProfileInf().GetResult(inputBean);
            if (BlockBin != null) {
                addActionError(SystemMessage.BLOCK_BIN_ALREADY_NAME);
                return ok;
            } else if (bean.getBinProfileDes() == null || bean.getBinProfileDes().isEmpty()) {
                addActionError(SystemMessage.BLOCK_BIN_INVALID_PROFILE_NAME);
                return ok;
            } else if (!Util.validateString(bean.getBinProfileDes())) {
                addActionError(SystemMessage.BLOCK_BIN_INVALID_NAME);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    private boolean doValidationUpdate(BinProfileBean binProfileBean) throws Exception {

        boolean ok = false;
        try {
            if (binProfileBean.getUpName() == null || binProfileBean.getUpName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_NAME);
                return ok;
            }
            if (!Util.validateString(binProfileBean.getUpName())) {
                addActionError(SystemMessage.USER_INVALID_NAME);
                return ok;
            }
            if (binProfileBean.getUpStatus() == -1) {
                addActionError(SystemMessage.USER_EMPTY_USERTYPE);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    public String Add() {
        inputBean.setToken(getSessionToken());
        try {

            if (doValidation(inputBean)) {

                boolean ok = getService().getBlockBinProfileInf().insertBlockBinProfile(inputBean);
                if (ok == true) {
                    addActionMessage(SystemMessage.BLOCK_BIN_ADD_SUCCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSession().getUserLevel(), 
                            SystemModule.BIN_MANAGEMENT,
                            TaskVarList.ADD, 
                            inputBean.getBinProfileDes()+" -> "+SystemMessage.BLOCK_BIN_ADD_SUCCESS,
                            null, null, 
                            inputBean.getDbinId(),
                            getSession().getId(),
                            SystemSection.BLOCK_BIN_PROFILE,
                            null,null);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.BLOCK_BIN_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "Add";
    }

    public String AssignBin() {
        inputBean.setToken(getSessionToken());
        int binID = inputBean.getBinId();
        String comName = inputBean.getcBinProfile();
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        ServletActionContext.getRequest().setAttribute(TokenConst.REQUEST_TOKEN, getSessionToken());
        session.setAttribute("BlockBin", binID);
        return "Assign";

    }

    public String Load() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getBlockBinProfileInf().getUpdateData(inputBean);
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
        }
        return "load";
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            String oldVal=Util.getOldorNewVal(epicTleBinProfile, "wu.id ='"+inputBean.getDbinId()+"'");
            if (getService().getBlockBinProfileInf().deleteBin(inputBean)) {             
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.DELETE, SystemMessage.BLOCK_BIN_DELETE_SUCESS, null, null, inputBean.getDbinId(), getSession().getId(),SystemSection.BLOCK_BIN_PROFILE,oldVal,null);
                LogFileCreator.writeInforToLog(SystemMessage.BLOCK_BIN_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.BLOCK_BIN_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.BLOCK_BIN_DELETE_FAIL);
            }
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.BLOCK_BIN_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
//            e.printStackTrace();
        }

        return "Delete";
    }

    public String update() {
        inputBean.setToken(getSessionToken());
        boolean ok;
        try {
            if (doValidationUpdate(inputBean)) {
                String oldVal=Util.getOldorNewVal(epicTleBinProfile, "wu.id ='"+inputBean.getUpProfileID()+"'");
                ok = getService().getBlockBinProfileInf().updatebin(inputBean);
                if (ok == true) {
                    String newVal=Util.getOldorNewVal(epicTleBinProfile, "wu.id ='"+inputBean.getUpProfileID()+"'");
//                    inputBean.setIsUpdated(true);
//                    inputBean.setMsg(SystemMessage.BLOCK_BIN_UPDATE_SUCCESS);
                    addActionMessage(SystemMessage.BLOCK_BIN_UPDATE_SUCCESS);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.BIN_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.BLOCK_BIN_UPDATED, null, null, null, getSession().getId(),SystemSection.BLOCK_BIN_PROFILE,oldVal,newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.BLOCK_BIN_UPDATE_SUCCESS);
                } else {
                    addActionError(SystemMessage.BLOCK_BIN_UPDATED_ERROR);
                }
            }
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

}
