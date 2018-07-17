/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.UserLoginBean;
import com.epic.tle.login.service.LoginServiceFactory;
import com.epic.tle.mapping.EpicTleUserPasswordHistory;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import com.epic.tle.userManagement.service.ChangePasswordFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.SessionAware;

/**
 *
 * @author gayan_s
 */
public class ResetPasswordAction extends ActionSupport implements ModelDriven<ChangePasswordBean>, SessionAware {

    private SessionMap<String, Object> sessionMap;
    ChangePasswordBean resetPasswordBean = new ChangePasswordBean();
    ChangePasswordFactory changePasswordService;
    SessionUserBean sub;

    public ChangePasswordFactory getChangePasswordService() {
        return new ChangePasswordFactory();
    }

    @Override
    public ChangePasswordBean getModel() {
        return resetPasswordBean;
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public LoginServiceFactory getService() {
        return new LoginServiceFactory();
    }

    public String resetPassword() {
        HttpSession session = ServletActionContext.getRequest().getSession(true);
        UserLoginBean userLoginBean = (UserLoginBean) session.getAttribute("ubean");
        boolean ok;

        try {
//            if (getService().getLoginService().validateOldPassword(resetPasswordBean, userLoginBean.getUserName())) {
            if (doValidation(resetPasswordBean)) {
                List<EpicTleUserPasswordHistory> userPreviousPasswords = Util.getUserPreviousPasswords(userLoginBean.getUserName());
                boolean val = false;
                for (EpicTleUserPasswordHistory userPreviousPassword : userPreviousPasswords) {
                    if (userPreviousPassword.getPrivousPassword()
                            .equals(Util.generateHash(resetPasswordBean.getNewPassword(), Util.getUserRandVal(userLoginBean.getUserName())))) {
                        val = true;
                    }
                }
                if (val != true) {

                    ok = getService().getLoginService().updatePassword(resetPasswordBean, userLoginBean.getUserName());;
                    if (ok) {
                        boolean updatePasswordHistory = Util.updatePasswordHistory(resetPasswordBean.getNewPassword(), userLoginBean.getUserName(), userLoginBean.getId());
                        if (updatePasswordHistory) {
                            Util.insertHistoryRecord(
                                    LogTypes.TLEWEBAPP,
                                    userLoginBean.getDBuserappCode(),
                                    SystemModule.USER_MANAGEMENT,
                                    TaskVarList.UPDATE,
                                    SystemMessage.USER_CHANGE_PASSWORD_SUCCESS + " Password Reset. UserName: " + userLoginBean.getUserName(),
                                    null,
                                    null,
                                    null,
                                    userLoginBean.getId(),
                                    SystemSection.REGISTER_USER,
                                    null,
                                    null
                            );
                            boolean updateUserStatus = getService().getLoginService().updateUserStatus(Status.ACTIVE, userLoginBean.getId());
                            boolean updateLastPasswordChangeDate = getService().getLoginService().updateLastPasswordChangeDate(userLoginBean.getId());
                            if (updateUserStatus && updateLastPasswordChangeDate) {
//                                    LogFileCreator.writeInforToLog(SystemMessage.USER_CHANGE_PASSWORD_SUCCESS);
                                addActionMessage(SystemMessage.USER_CHANGE_PASSWORD_SUCCESS);
                                return LOGIN;
                            } else {
                                addActionError(SystemMessage.USER_LOGIN_ERROR);
                            }

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
//            } else {
//                addActionError(SystemMessage.USER_CHANGE_PASSWORD_OLD_PW_INCORRECT);
//            }
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
            e.printStackTrace();
            addActionError(SystemMessage.USER_CHANGE_PASSWORD_ERROR);
            LogFileCreator.writeErrorToLog(e);
        }
        return "passwordresetted";
    }

    @Override
    public void setSession(Map<String, Object> map) {
        sessionMap = (SessionMap) map;
    }

    private boolean doValidation(ChangePasswordBean changePasswordBean) throws Exception {
        boolean ok = false;

        try {
            if (changePasswordBean.getNewPassword().isEmpty() || changePasswordBean.getNewPassword() == null) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_NEW_PW_EMPTY);
                return ok;
            } else if (changePasswordBean.getRepeatPassword().isEmpty() || changePasswordBean.getRepeatPassword() == null) {
                addActionError(SystemMessage.USER_CHANGE_PASSWORD_REPEAT_PW_EMPTY);
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
}
