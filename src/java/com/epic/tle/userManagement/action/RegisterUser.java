/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.login.service.LoginServiceFactory;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import com.epic.tle.util.MailBucket;
import com.epic.tle.mapping.EpicTleUser;
import com.epic.tle.userManagement.bean.RegisterUserBean;
import com.epic.tle.userManagement.service.RegisterUserFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.ExcelCommon;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.User;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.charset.Charset;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author danushka_r
 */
public class RegisterUser extends ActionSupport implements AccessControlService, ModelDriven<RegisterUserBean> {

    RegisterUserBean inputBean = new RegisterUserBean();
    EpicTleUser epicTleUser = new EpicTleUser();
    HttpServletRequest req = ServletActionContext.getRequest();
    SessionUserBean sessionBean;
    RegisterUserFactory service;

    public SessionUserBean getSessionBean() {
        return (SessionUserBean) req.getSession().getAttribute("SessionObject");
    }

    public String getSessionToken() {
        return (String) req.getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    public RegisterUserFactory getService() {
        return new RegisterUserFactory();
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String list() {
        List<RegisterUserBean> dataList;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records;
            String orderBy = "";

            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by wu." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getRegisterUserServiceInf().loadUsers(inputBean, rows, from, orderBy);

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
//            if(inputBean.getSearchName() != null){
//                 LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);
//            }

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String delete() {
        inputBean.setToken(getSessionToken());
        try {
            if (inputBean.getDuserName().equals(getSessionBean().getUserName())) {
                inputBean.setDmessage(SystemMessage.USER_DELETE_CANNOT_DELETE_CURRENT_USER);
                inputBean.setIsDeleted(false);
            } else if (inputBean.getDuserName().equalsIgnoreCase("admin")) {
                inputBean.setDmessage(SystemMessage.USER_DELETE_CANNOT_DELETE_ADMIN);
                inputBean.setIsDeleted(false);
            } else {
                epicTleUser.setUsername(inputBean.getDuserName());
                String oldVal = Util.getOldorNewVal(epicTleUser, "wu.username ='" + epicTleUser.getUsername() + "'");
                if (getService().getRegisterUserServiceInf().deleteUser(inputBean.getDuserName())) {
                    inputBean.setDmessage(SystemMessage.USER_DELETED);
                    inputBean.setIsDeleted(true);
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSessionBean().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.DELETE, SystemMessage.USER_DELETED, null, null, null, getSessionBean().getId(), SystemSection.REGISTER_USER, oldVal, null);
                    LogFileCreator.writeInforToLog(SystemMessage.USER_DELETED);
                }
            }
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
            inputBean.setDmessage(SystemMessage.USER_DELETED_ERROR);
            inputBean.setIsDeleted(false);
        }
        return "delete";
    }

    public String find() {
        inputBean.setToken(getSessionToken());
        try {
            getService().getRegisterUserServiceInf().findUser(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }

    public String update() {
        boolean ok;
        try {
            if (doValidationUpdate(inputBean)) {
                epicTleUser.setUsername(inputBean.getUpuserName());
                String oldVal = Util.getOldorNewVal(epicTleUser, "wu.username ='" + epicTleUser.getUsername() + "'");
                ok = getService().getRegisterUserServiceInf().updateUser(inputBean);
                if (ok) {
                    String newVal = Util.getOldorNewVal(epicTleUser, "wu.username ='" + epicTleUser.getUsername() + "'");
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSessionBean().getUserLevel(), SystemModule.USER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.USER_UPDATED + ",User name : " + inputBean.getUpuserName(), null, null, null, getSessionBean().getId(), SystemSection.REGISTER_USER, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.USER_UPDATED);
                    addActionMessage(SystemMessage.USER_UPDATED);
                } else {
                    addActionError(SystemMessage.USER_UPDATED_ERROR);
                }
            }
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            addActionError(SystemMessage.USER_UPDATED_ERROR);
            LogFileCreator.writeErrorToLog(ex);
        }
        return "update";
    }

    public String add() {
        boolean ok;
        try {

            if (doValidationAdd(inputBean)) {
                ok = getService().getRegisterUserServiceInf().addUser(inputBean);
                epicTleUser.setUsername(inputBean.getUserName());
                if (ok == false) {
                    addActionError(SystemMessage.USER_ADD_FAIL);
                } else {
                    addActionMessage(SystemMessage.USER_ADD_SUCESS);
                    Util.insertHistoryRecord(
                            LogTypes.TLEWEBAPP,
                            getSessionBean().getUserLevel(),
                            SystemModule.USER_MANAGEMENT,
                            TaskVarList.ADD,
                            SystemMessage.USER_ADD_SUCESS + " with name : " + inputBean.getName(),
                            null, null, null,
                            getSessionBean().getId(),
                            SystemSection.REGISTER_USER,
                            null, null);
                    LogFileCreator.writeInforToLog(SystemMessage.USER_ADD_SUCESS);
                }
            }

        } catch (Exception ex) {
            addActionError(SystemMessage.USER_ADD_FAIL);
            LogFileCreator.writeErrorToLog(ex);
            ex.printStackTrace();
        }
        return "add";
    }

    public String export() {
        ByteArrayOutputStream outputStream;
        try {

            XSSFWorkbook workbook1 = new XSSFWorkbook();
            XSSFSheet sheet = workbook1.createSheet("ceft_transaction_report");

            XSSFCellStyle fontBoldedUnderlinedCell = ExcelCommon.getFontBoldedUnderlinedCell(workbook1);

            Row row = sheet.createRow(0);
            Cell cell = row.createCell(0);
            cell.setCellValue("Name");
            cell.setCellStyle(fontBoldedUnderlinedCell);

            row = sheet.createRow(0);
            cell = row.createCell(1);
            cell.setCellValue("Age");
            cell.setCellStyle(fontBoldedUnderlinedCell);
            // Object object =ExcelReportTerminal.generateExcelReport();
            if (workbook1 instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) workbook1;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
            }

        } catch (Exception e) {
        }
        return "excelreportterminal";
    }

    private boolean doValidationUpdate(RegisterUserBean userBean) throws Exception {
        boolean ok = false;
        try {
            if (userBean.getUpName() == null || userBean.getUpName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_NAME);
                return ok;
            }
            if (!Util.validateNAME(userBean.getUpName())) {
                addActionError(SystemMessage.USER_INVALID_NAME);
                return ok;
            }
            if (userBean.getUpuserName() == null || userBean.getUpuserName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_USERNAME);
                return ok;
            }
            if (!Util.validateUSERNAME(userBean.getUpuserName())) {
                addActionError(SystemMessage.USER_INVALID_USERNAME);
                return ok;
            }
            if (!Util.validateEMAIL(userBean.getUpEmail())) {
                addActionError(SystemMessage.USER_INVALID_EMAIL);
                return ok;
            } else if (getService().getRegisterUserServiceInf().checkEmail(userBean.getUpEmail(), userBean.getUpuserName())) {
                addActionError(SystemMessage.USER_UPDATED_ERROR_EMAIL_DUPLICATE);
                return ok;
            }
            if (userBean.getUpuserTypeId() == -1) {
                addActionError(SystemMessage.USER_EMPTY_USERTYPE);
                return ok;
            }
            if (inputBean.isIsChecked() == true) {

                String passValidMessage = Util.validatePW_POLICY(userBean.getUpNewPw());

                if (!passValidMessage.equals("success")) {
                    addActionError(passValidMessage);
                    return ok;
                }
                if ((!userBean.getUpRepetedNewPw().equals(userBean.getUpNewPw()))) {
                    addActionError(SystemMessage.USER_CONF_PW_MISMATCH);//missmatch
                    return ok;
                }

            }
            if ((inputBean.isIsChecked() == true) && (userBean.getUpNewPw().isEmpty() || userBean.getUpNewPw() == null)) {
                addActionError(SystemMessage.USER_PW_EMPTY);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return ok;
    }

    private boolean doValidationAdd(RegisterUserBean userBean) throws Exception {
        boolean ok = false;
        try {
            if (userBean.getName() == null || userBean.getName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_NAME);
                return ok;
            }
            if (!Util.validateNAME(userBean.getName())) {
                addActionError(SystemMessage.USER_INVALID_NAME);
                return ok;
            } else if (userBean.getUserName() == null || userBean.getUserName().isEmpty()) {
                addActionError(SystemMessage.USER_EMPTY_USERNAME);
                return ok;
            } else if (!Util.validateUSERNAME(userBean.getUserName())) {
                addActionError(SystemMessage.USER_INVALIDUSERNAME);
                return ok;
            } else if (getService().getRegisterUserServiceInf().checkUserName(userBean.getUserName())) {
                addActionError(SystemMessage.USER_USERNAME_ALREADY);
                return ok;
            } else if (userBean.getPassword().isEmpty() || userBean.getPassword() == null) {
                addActionError(SystemMessage.USER_PW_EMPTY);
                return ok;
            } else if (userBean.getConfirmPassword().isEmpty() || userBean.getConfirmPassword() == null) {
                addActionError(SystemMessage.USER_CONF_PW_EMPTY);
                return ok;
            } else if (!userBean.getConfirmPassword().equals(userBean.getPassword())) {
                addActionError(SystemMessage.USER_CONF_PW_MISMATCH);
                return ok;
            } else if (!Util.validateEMAIL(userBean.getEmail())) {
                addActionError(SystemMessage.USER_INVALID_EMAIL);
                return ok;

            } else if (getService().getRegisterUserServiceInf().checkEmail(userBean.getEmail(), userBean.getUserName())) {
                addActionError(SystemMessage.USER_UPDATED_ERROR_EMAIL_DUPLICATE);
                return ok;
            }
//            String passValidMessage = Util.validatePW_POLICY(userBean.getPassword());
//
//            if (!passValidMessage.equals("success")) {
//                addActionError(passValidMessage);
//                return ok;
//            } else
            if (userBean.getUserType().equals("-1")) {
                addActionError(SystemMessage.USER_EMPTY_USER_PROFILE);
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
        String page = PageVarList.REGISTER_USER;
        inputBean.setPageCode(page);
        String task = null;

        if ("list".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("export".equals(method)) {
            task = TaskVarList.DOWNLOAD;

        } else if ("reset".equals(method)) {
            task = TaskVarList.RESET;
        }
        if ("execute".equals(method)) {
//            System.out.println(inputBean.is);
            status = !inputBean.isView();
        } else {
            HttpSession session = ServletActionContext.getRequest().getSession(false);
            status = new Common().checkMethodAccess(task, page, session);
        }
        return status;
    }

    private boolean applyUserPrivileges() {
        HttpServletRequest request = ServletActionContext.getRequest();
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.REGISTER_USER, request);
        inputBean.setAdd(true);
        inputBean.setDelete(true);
        inputBean.setView(true);
        inputBean.setUpdate(true);
        inputBean.setReset(true);
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
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.RESET)) {
                    inputBean.setReset(false);
                }
            }
        }
        return true;
    }

    @Override
    public RegisterUserBean getModel() {
        try {
            inputBean.setUserTypeMap(getService().getRegisterUserServiceInf().getUserTypes());
            getService().getRegisterUserServiceInf().getPagePath(inputBean.getPageCode(), inputBean);
            inputBean.setStatusTypeMap(getService().getRegisterUserServiceInf().getStatusTypes());
            inputBean.setUserTypeMap(Util.getUserTypes());

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

    public String resetUserPassowrd() {
        inputBean.setToken(getSessionToken());
        if (!getSessionBean().getEmail().equals("--") && !getSessionBean().getEmail().isEmpty()) {

            if (inputBean.getEmail() != null && !inputBean.getEmail().isEmpty()) {
                if (!inputBean.getEmail().equals("--")) {
                    if (Util.validateEMAIL(inputBean.getEmail())) {
                        MailBucket bucket = new MailBucket();
                        bucket.setIsHtml(true);
                        bucket.setTo_mail_address(inputBean.getEmail());

                        bucket.setDebug(false);
                        String genarateTempPassword = Util.genarateTempPassword();
                        String passwordRestMailTemplate = Util.getPasswordRestMailTemplate(Configurations.SERVER_NODE);
                        String senderEmail = Util.getSenderEmail(Configurations.SERVER_NODE);
                        bucket.setFrom_mail_address(senderEmail);
                        String[] split = passwordRestMailTemplate.split("@");
                        String subject = split[0] + " " + new Date().toString();
                        bucket.setSubject(subject);
                        String replace = split[1]
                                .replace("[EXPTIME]", Util.getTempPasswordExpirationTimePeriod(Configurations.SERVER_NODE) + "")
                                .replace("[username]", inputBean.getUserName())
                                .replace("[tempassword]", genarateTempPassword);
                        bucket.setMessage(replace);
                        try {
                            Util.sendMail(bucket);
                            boolean updateLastPaswwordResetDate = getService().getRegisterUserServiceInf().updateLastPaswwordResetDate(inputBean.getUserName());
                            boolean updateUserStatus = getService().getRegisterUserServiceInf().updateUserStatus(Status.FORGET, inputBean.getUserName());
                            ChangePasswordBean bean = new ChangePasswordBean();
                            bean.setNewPassword(genarateTempPassword);
                            getLoginService().getLoginService().updatePassword(bean, inputBean.getUserName());

                            boolean s = getLoginService()
                                    .getLoginService()
                                    .updateLastPasswordChangeDateByUsername(inputBean.getUserName());
                            boolean ss = getLoginService().getLoginService().updateRemainigPasswordAttemptByUserName(0, inputBean.getUserName());
                            Util.insertHistoryRecord(
                                    LogTypes.TLEWEBAPP,
                                    getSessionBean().getUserLevel(),
                                    SystemModule.USER_MANAGEMENT,
                                    TaskVarList.RESET,
                                    "User Password reset - Send temp passowrd[" + genarateTempPassword + "] to user " + inputBean.getUserName(),
                                    null, null, null,
                                    getSessionBean().getId(),
                                    SystemSection.REGISTER_USER,
                                    null, null
                            );
                            if (updateLastPaswwordResetDate && updateUserStatus && s && ss) {
                                inputBean.setIsTempPassSent(true);
                            } else {
                                inputBean.setIsTempPassSent(false);
                            }

                        } catch (MessagingException ex) {
                            System.out.println(ex.getLocalizedMessage());
                            inputBean.setIsTempPassSent(false);
                            inputBean.setMailMessage("Error sending mail");
                        } catch (Exception ex) {
                            System.out.println(ex.getLocalizedMessage());
                            inputBean.setIsTempPassSent(false);
                            inputBean.setMailMessage("Error sending mail");
                        }
                    } else {
                        inputBean.setIsTempPassSent(false);
                        inputBean.setMailMessage("Invalid mail address");
                    }
                } else {
                    inputBean.setIsTempPassSent(false);
                    inputBean.setMailMessage("Invalid mail address");
                }
            } else {
                inputBean.setIsTempPassSent(false);
                inputBean.setMailMessage("Invalid mail address");
            }
        } else {
            inputBean.setIsTempPassSent(false);
            inputBean.setMailMessage("You dont have a mail address , please add a mail address to your profile..!");
        }

        return "reset";
    }

    public LoginServiceFactory getLoginService() {
        return new LoginServiceFactory();
    }
}
