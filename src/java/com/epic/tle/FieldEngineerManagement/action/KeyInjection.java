/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.action;

/**
 *
 * @author chalaka_n
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
import com.epic.tle.FieldEngineerManagement.bean.KeyInjectionBean;
import com.epic.tle.FieldEngineerManagement.bean.Terminal;
import com.epic.tle.FieldEngineerManagement.service.KeyInjectionService;
import com.epic.tle.FieldEngineerManagement.service.KeyInjectionServiceFactory;
import com.epic.tle.FieldEngineerManagement.smartcard.CommunicatWithSmartCard;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyInectingConfig;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyMailConfiguration;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyMailing;
import com.epic.tle.FieldEngineerManagement.smartcard.Mode;
import com.epic.tle.FieldEngineerManagement.smartcard.Process_KEYBean;
import com.epic.tle.FieldEngineerManagement.smartcard.WriteGenKeyToPort;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.Operation;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

public class KeyInjection extends ActionSupport implements ModelDriven<KeyInjectionBean>, AccessControlService {

    KeyInjectionServiceFactory service;
    KeyInjectionBean inputbean = new KeyInjectionBean();
    //KeyInjectionService service=new KeyInjectionService();
    SessionUserBean session;

    public KeyInjectionServiceFactory getService() {
        return new KeyInjectionServiceFactory();
    }

    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    @Override
    public String execute() {
        return SUCCESS;
    }

    public String addKeyInject() {
        try {

            if (doValidationKI(inputbean)) {
                System.out.println(inputbean.getTerminalKI());
                Terminal terminal = getService().getKeyInjectInf().getTerminal(inputbean.getTerminalKI());
                if (terminal != null) {
                    if (terminal.getStatus() == Operation.ACTIVE) {

                        getService().getKeyInjectInf().getKeyInjectParems();
                        try {
                            Process_KEYBean obj = new Process_KEYBean();
                            boolean res = CommunicatWithSmartCard.doProcess(Mode.DEV_MANUAL, Mode.REG_OFFLINE, Mode.ALGO_3DES, CommunicatWithSmartCard.generatSerialNumber(), obj);

                            if (res) {
                                obj = CommunicatWithSmartCard.getProcessObject();
                                if (CommunicatWithSmartCard.doManualKeyInjection(obj)) {

                                    terminal.setCOUNTOR("000000");
                                    terminal.setKEYID(obj.getKEYID());
                                    terminal.setETMKSESSIONKEY(obj.getETMK());
                                    terminal.setEMKESESSIONKEY(obj.getEMEK());
                                    terminal.setTMKKVC(obj.getTMK_KVC());
                                    terminal.setMEKKVC(obj.getMEK_KVC());
                                    if (getService().getKeyInjectInf().updateTerminalKeys(terminal)) {
                                        Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.ADD, "Manually key download to Terminal " + terminal.getTid(), null, terminal.getTid(), terminal.getMid(), getSession().getId(),SystemSection.REGISTER_FIELD_ENGINEER,null,null);
                                        LogFileCreator.writeInforToLog(SystemMessage.FIELD_KEY_INJE_SUCCESS);

                                        String key = CommunicatWithSmartCard.getManualRequest(obj);
                                        System.out.println("key----------" + key);
                                        WriteGenKeyToPort.writKeytoport(key, KeyInectingConfig.PORT);

                                        addActionMessage("Key injection Completed.");
                                    }
                                } else {
                                    addActionError("Error: Manual key injection failse.");
                                }
                            } else {
                                addActionError("Error: Manual key injection failse.");
                            }

                        } catch (Exception ex) {
                            ex.printStackTrace();
                            LogFileCreator.writeErrorToLog(ex);
                            addActionError("Error: Error occurred in the system .");
                        }
                    } else {
                        addActionError("Error : Terminal is inactive.");
                    }
                } else {
                    addActionError("Error : Terminal ID does not exists.");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "add";
    }

    public String addKeyMail() {
        try {
            if (doValidationKM(inputbean)) {
                Terminal terminal = getService().getKeyInjectInf().getTerminal(inputbean.getTerminalKM());
                if (terminal != null) {
                    if (terminal.getStatus() == Operation.ACTIVE) {
                        getService().getKeyInjectInf().getKeyMailerParems();
                        try {
                            Process_KEYBean obj = new Process_KEYBean();
                            boolean res = CommunicatWithSmartCard.doProcess(Mode.DEV_MANUAL, Mode.REG_OFFLINE, Mode.ALGO_3DES, CommunicatWithSmartCard.generatSerialNumber(), obj);
                            if (res) {
                                obj = CommunicatWithSmartCard.getProcessObject();
                                if (CommunicatWithSmartCard.doManualKeyInjection(obj)) {

                                    if (KeyMailConfiguration.STATUS == Operation.ACTIVE) {
                                        KeyMailing.PINprint(obj.getTMK(), obj.getMEK(), obj.getTMK_KVC(), obj.getMEK_KVC(), terminal.getTid(), terminal.getBank(), terminal.getName(), terminal.getRegDate(), obj.getKEYID());
//                                        addActionMessage("Key Was Printed");
                                        inputbean.setMessage("Key Was Printed");
                                        inputbean.setSuccessmsg(true);
                                    } else if (KeyMailConfiguration.STATUS == Operation.INACTIVE) {
                                        String counter = (terminal.getCOUNTOR()!=null)?terminal.getCOUNTOR():"000000";
                                        terminal.setCOUNTOR(counter);
                                        terminal.setKEYID(obj.getKEYID());
                                        terminal.setETMKSESSIONKEY(obj.getETMK());
                                        terminal.setEMKESESSIONKEY(obj.getEMEK());
                                        terminal.setTMKKVC(obj.getTMK_KVC());
                                        terminal.setMEKKVC(obj.getMEK_KVC());
                                        terminal.setTMK(obj.getTMK().concat(" ").concat(obj.getTMK_KVC()));
                                        terminal.setMEK(obj.getMEK().concat(" ").concat(obj.getMEK_KVC()));
                                        if (getService().getKeyInjectInf().updateTerminalKeys(terminal)) {                                            
                                           inputbean.setBankaname("HLB");
                                           inputbean.setTerminalid(inputbean.getTerminalKM());
                                           inputbean.setMerchantname(getSession().getUserName());
                                           inputbean.setMek(terminal.getMEK());
                                           inputbean.setIssuedDate(new Date().toString());
                                           inputbean.setKeyid(terminal.getKEYID());
                                           inputbean.setTmk(terminal.getTMK());
                                            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.UPDATE, "Manually key download to Terminal " + terminal.getTid(), null, terminal.getTid(), terminal.getMid(), getSession().getId(),SystemSection.REGISTER_FIELD_ENGINEER,null,null);
                                            LogFileCreator.writeInforToLog("Manually key printed download to Terminal " + terminal.getTid());
                                            inputbean.setMessage("Key Downloading");
                                            inputbean.setSuccessmsg(true);
                                            return "add";
                                        }

                                        //logedSession.setKeyIssueterminal(terminal);
                                        // set pdf parameeters
                                        /////////////////////////////////////////////////
                                        //fileName = "PinMail_" + terminalid + "_" + merchantName + ".pdf";
                                    }
                                } else {
                                    inputbean.setMessage("Error : Key Injection fails.");
                                }
                            } else {
                                inputbean.setMessage("Error : Key Injection fails.");
                            }
                        } catch (Exception ex) {
                            ex.printStackTrace();
                            LogFileCreator.writeErrorToLog(ex);
                            inputbean.setMessage("Error: Error occurred in the system .");
                        }

                    } else {
                        inputbean.setMessage("Error : Terminal is inactive.");
                    }

                } else {
                    inputbean.setMessage("Error : Terminal id does not exists.");
                }
            }
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }
        return "add";
    }

    private boolean doValidationKI(KeyInjectionBean userBean) throws Exception {
        boolean ok = false;

        try {
            if (userBean.getTerminalKI() == null || userBean.getTerminalKI().isEmpty()) {
                addActionError(SystemMessage.FIELD_KEY_INJE_EMPTY);
                userBean.setMessage(SystemMessage.FIELD_KEY_INJE_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(userBean.getTerminalKI()) || (userBean.getTerminalKI().length() != 8)) {
                addActionError(SystemMessage.FIELD_KEY_INJE_INVALID);
                userBean.setMessage(SystemMessage.FIELD_KEY_INJE_INVALID);
                return ok;
            } else {
                ok = true;
            }

        } catch (Exception e) {
            throw e;
        }

        return ok;
    }

    private boolean doValidationKM(KeyInjectionBean userBean) throws Exception {
        boolean ok = false;
        try {
            if (userBean.getTerminalKM() == null || userBean.getTerminalKM().isEmpty()) {
                addActionError(SystemMessage.FIELD_KEY_INJE_EMPTY);
                userBean.setMessage(SystemMessage.FIELD_KEY_INJE_EMPTY);
                return ok;
            } else if (!Util.validateNUMBER(userBean.getTerminalKM()) || (userBean.getTerminalKM().length() != 8)) {
                addActionError(SystemMessage.FIELD_KEY_INJE_INVALID);
                userBean.setMessage(SystemMessage.FIELD_KEY_INJE_INVALID);
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
        boolean status = false;
        applyUserPrivileges();
        String page = PageVarList.TERMINAL_KEY_INJECTION;
        String task = null;

        if ("View".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("addKeyInject".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("addKeyMail".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("downloadpdf".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Download".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("ResetPw".equals(method)) {
            task = TaskVarList.CONFIRM;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.TERMINAL_KEY_INJECTION, request);
        System.out.println(tasklist.size());
      
        inputbean.setVadd(true);
        inputbean.setVupdate(true);
        inputbean.setVdelete(true);
        inputbean.setVdownload(true);
        inputbean.setVresetpass(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputbean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputbean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputbean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputbean.setVdownload(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.CONFIRM)) {
                    inputbean.setVresetpass(false);
                }
            }
        }

        return true;
    }

    @Override
    public KeyInjectionBean getModel() {
        return inputbean;
    }

    public String downloadpdf() {
        try {
            System.out.println("pdfDownloadFE");
            inputbean.setFilename(inputbean.getTerminalid()+".pdf");
            Map reportParameters = new HashMap();
            reportParameters.put("bankname", inputbean.getBankaname());
            reportParameters.put("terminalid", inputbean.getTerminalid());
            reportParameters.put("merchantname", inputbean.getMerchantname());
            reportParameters.put("mek", inputbean.getMek());
            reportParameters.put("issuedDate", inputbean.getIssuedDate());
            reportParameters.put("keyid",inputbean.getKeyid());
            reportParameters.put("tmk", inputbean.getTmk());
            inputbean.setParameterMap(reportParameters);

            List datalist = new ArrayList();
            datalist.add(reportParameters);
            inputbean.getReportdatalist().add(datalist);

//                                            addActionMessage("Save the Key File");
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "PdfDownloadKeyMail";
    }

}
