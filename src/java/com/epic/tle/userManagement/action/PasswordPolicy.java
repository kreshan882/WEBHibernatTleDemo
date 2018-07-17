/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTlePasswordPolicy;
import com.epic.tle.userManagement.bean.PasswordPolicyBean;
import com.epic.tle.userManagement.service.PasswordPolicyFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.PasswordValidator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author danushka_r
 */
public class PasswordPolicy extends ActionSupport implements ModelDriven<PasswordPolicyBean>, AccessControlService {

    PasswordPolicyBean inputBean = new PasswordPolicyBean();
    PasswordPolicyFactory service;
    SessionUserBean sub ;
    EpicTlePasswordPolicy policy = new EpicTlePasswordPolicy();
    
    public PasswordPolicyFactory getService() {
        return new PasswordPolicyFactory();
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    @Override
    public String execute() throws Exception {
        String maxLength = new PasswordValidator().viewPasswordPolicyData().getMax_len();
       // System.out.println("mmmmm >" +maxLength);
        HttpSession session = ServletActionContext.getRequest().getSession(true);
        session.setAttribute("max_length", Integer.parseInt(maxLength));
        return SUCCESS;
    }

    public String resetData() {
        try {
            inputBean = getService().getPasswordPolicyServiceInf().viewPasswordPolicyDetails(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "reset";
    }

    public String update() {
        boolean ok;
        try {
            if (doValidation(inputBean)) {
                EpicTlePasswordPolicy pwdPolicy= new EpicTlePasswordPolicy();               
                pwdPolicy.setSid(inputBean.getSid());
                String oldVal=Util.getOldorNewVal(pwdPolicy, "wu.sid ='"+pwdPolicy.getSid()+"'");
                ok = getService().getPasswordPolicyServiceInf().updatePwdPolicy(inputBean);
                if (ok == false) {                    
                    addActionError(SystemMessage.USER_PW_POLICY_UPDATE_FAIL);
                } else {
                    String newVal=Util.getOldorNewVal(pwdPolicy, "wu.sid ='"+pwdPolicy.getSid()+"'");
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.USER_PW_POLICY_UPDATED, null, null, null, getSub().getId(),SystemSection.REGISTER_USER_PROFILES,oldVal,newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.USER_PW_POLICY_UPDATED);
                    addActionMessage(SystemMessage.USER_PW_POLICY_UPDATED);
                }
            }
        } catch (Exception ex) {
            addActionError(SystemMessage.USER_PW_POLICY_UPDATE_FAIL);
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    @Override
    public PasswordPolicyBean getModel() {
        try {
            inputBean = getService().getPasswordPolicyServiceInf().viewPasswordPolicyDetails(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    private boolean doValidation(PasswordPolicyBean bean) throws Exception {
        boolean ok = false;
        try {
            if (!Util.validateNUMBER(bean.getMin_spcl_chars()) || bean.getMin_spcl_chars().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MIN_SPCL_CHARACTER);
                return ok;
            }else if(Integer.parseInt(bean.getMin_spcl_chars())<1){
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_SPCL_CHARACTER_LENGTH);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMax_spcl_chars()) || bean.getMax_spcl_chars().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MAX_SPCL_CHARACTER);
                return ok;
            }else if (Integer.parseInt(bean.getMax_spcl_chars()) < Integer.parseInt(bean.getMin_spcl_chars())) {
                addActionError(SystemMessage.USER_PW_POLICY_INVALID_SPCL_CHARACTER_LENGTH);
                return ok;
            }else if (!Util.validateSPECIALCHAR(bean.getAllowspecialcharacters()) || bean.getAllowspecialcharacters() == null) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_SPECIAL_CHARACTER_SET);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMin_upercase()) || bean.getMin_upercase().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MIN_UPPERCASE);
                return ok;
            }else if(Integer.parseInt(bean.getMin_upercase())<1){
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_UPPERCASE_LENGTH);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMin_lowcase()) || bean.getMin_lowcase().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MIN_LOWERCASE);
                return ok;
            }else if(Integer.parseInt(bean.getMin_lowcase())<1){
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_LOWERCASE_LENGTH);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMin_numerics()) || bean.getMin_numerics().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MIN_NUMERIC_CHARACTER);
                return ok;
            }else if(Integer.parseInt(bean.getMin_numerics())<1){
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_NUMERIC_CHARACTER_LENGTH);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMin_len()) || bean.getMin_len().equals("0") || bean.getMin_len().equals("")||Integer.parseInt(bean.getMin_len())<7) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MIN_LENGTH);
                return ok;
            }else if (Integer.parseInt(bean.getMin_len()) < (Integer.parseInt(bean.getMin_spcl_chars())+Integer.parseInt(bean.getMin_upercase())+Integer.parseInt(bean.getMin_lowcase())+Integer.parseInt(bean.getMin_numerics()))) {
                 addActionError(SystemMessage.USER_PW_POLICY_MIN_LENGTH);
                return ok;
            }else if (!Util.validateNUMBER(bean.getMax_len()) || bean.getMax_len().equals("0") || bean.getMax_len().equals("")) {
                addActionError(SystemMessage.USER_PW_POlICY_INVALID_MAX_LENGTH);
                return ok;
            }else if (Integer.parseInt(bean.getMax_len()) < Integer.parseInt(bean.getMin_len())) {
                addActionError(SystemMessage.USER_PW_POLICY_INVALID_MIN_MAX_LENGTH);
                return ok;
            }else if (Integer.parseInt(bean.getMax_len()) < (Integer.parseInt(bean.getMax_spcl_chars())+Integer.parseInt(bean.getMin_upercase())+Integer.parseInt(bean.getMin_lowcase())+Integer.parseInt(bean.getMin_numerics()))) {
                addActionError(SystemMessage.USER_PW_POLICY_INVALID_MAX_LENGTH_WITH_SPC_CHAR);
                return ok;
            }else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }

        return ok;
    }

    @Override
    public boolean checkAccess(String method, int userRole) {
        boolean status;
        applyUserPrivileges();
        String page = PageVarList.PASSWORD_POLICY;
        String task = null;
        if ("resetData".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("update".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.PASSWORD_POLICY, request);
        inputBean.setReset(true);
        inputBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setReset(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setUpdate(false);
                }
            }
        }
        return true;
    }
}
