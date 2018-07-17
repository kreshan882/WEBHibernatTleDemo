/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.mapping.EpicTleUserPasswordHistory;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import com.epic.tle.userManagement.service.ChangePasswordFactory;
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
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author danushka_r
 */
public class ChangePassword extends ActionSupport implements ModelDriven<ChangePasswordBean>, AccessControlService {
    
    ChangePasswordBean changePasswordBean = new ChangePasswordBean();
    ChangePasswordFactory changePasswordService;
    SessionUserBean sub;
    
    public ChangePasswordFactory getChangePasswordService() {
        return new ChangePasswordFactory();
    }
    
    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    @Override
    public ChangePasswordBean getModel() {
        return changePasswordBean;
    }
    
    @SuppressWarnings("empty-statement")
    public String changePassword() {
        boolean ok;
        
        try {
            if (getChangePasswordService().getChangePasswordServiceInf().validateOldPassword(changePasswordBean)) {
                if (doValidation(changePasswordBean)) {
                    List<EpicTleUserPasswordHistory> userPreviousPasswords = Util.getUserPreviousPasswords(getSub().getUserName());
                    boolean val = false;
                    for (EpicTleUserPasswordHistory userPreviousPassword : userPreviousPasswords) {
                        if (userPreviousPassword.getPrivousPassword()
                                .equals(Util.generateHash(changePasswordBean.getNewPassword(),Util.getUserRandVal(getSub().getUserName())))) {
                            val = true;
                        }
                        
                    }
                    if (val != true) {
                        
                        ok = getChangePasswordService().getChangePasswordServiceInf().updatePassword(changePasswordBean);;
                        if (ok) {
                            boolean updatePasswordHistory = Util.updatePasswordHistory(changePasswordBean.getNewPassword(), getSub().getUserName(), getSub().getId());
                            if (updatePasswordHistory) {
                                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.USER_UPDATED+", Changed the Password. UserName : "+getSub().getUserName(), null, null, null, getSub().getId(),SystemSection.CHANGE_PASSWORD,null ,null);
                                LogFileCreator.writeInforToLog(SystemMessage.USER_CHANGE_PASSWORD_SUCCESS);
                                addActionMessage(SystemMessage.USER_CHANGE_PASSWORD_SUCCESS);
                            } else {
                                addActionError(SystemMessage.USER_CHANGE_PASSWORD_ERROR);
                            }
                            
                        } else {
                            addActionError(SystemMessage.USER_CHANGE_PASSWORD_ERROR);
                        }
                    } else {
                        
                        addActionError(SystemMessage.USER_CHANGE_PREVIOUS_PASSWORD);
                    }
                }
            } else {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_OLD_PW_INCORRECT);
            }
        } catch (Exception e) {
            e.printStackTrace();
            addActionError(SystemMessage.USER_CHANGE_PASSWORD_ERROR);
            LogFileCreator.writeErrorToLog(e);
        }
        return "passwordchanged";
    }
    
    private boolean doValidation(ChangePasswordBean changePasswordBean) throws Exception {
        boolean ok = false;
        
        try {
            if (changePasswordBean.getOldPassword().isEmpty() || changePasswordBean.getOldPassword() == null) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_OLD_PW_EMPTY);
                return ok;
            } else if (changePasswordBean.getNewPassword().isEmpty() || changePasswordBean.getNewPassword() == null) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_NEW_PW_EMPTY);
                return ok;
            } else if (changePasswordBean.getRepeatPassword().isEmpty() || changePasswordBean.getRepeatPassword() == null) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_REPEAT_PW_EMPTY);
                return ok;
            } else if (changePasswordBean.getOldPassword().equals(changePasswordBean.getNewPassword())) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_CANNOT_INSERT_OLD_PW);
                return ok;
            } else if (!changePasswordBean.getRepeatPassword().equals(changePasswordBean.getNewPassword())) {
                addActionError(SystemMessage.USER_CONF_PW_MISMATCH);
                return ok;
            }
            
            String passValidMessage = Util.validatePW_POLICY(changePasswordBean.getNewPassword());
            
            if (!passValidMessage.equals("success")) {
                addActionError(passValidMessage);
                return ok;
            } else {
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
        String page = PageVarList.CHANGE_PASSWORD;
        String task = null;
        if ("changePassword".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.CHANGE_PASSWORD, request);
        changePasswordBean.setUpdate(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    changePasswordBean.setUpdate(false);
                }
            }
        }
        return true;
    }
    
}
