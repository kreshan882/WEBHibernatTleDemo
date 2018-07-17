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
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerBean;
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.FieldEngineerManagement.service.RegisterFieldEngineerServiceFactory;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.epic.tle.FieldEngineerManagement.bean.FieldEngineer;
import com.epic.tle.FieldEngineerManagement.service.ExcelReportFieldEngineerServiceFactory;
import com.epic.tle.FieldEngineerManagement.smartcard.Process_KEYBean;
import com.epic.tle.FieldEngineerManagement.smartcard.CommunicatWithSmartCard;
import com.epic.tle.FieldEngineerManagement.smartcard.HSMConnector;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyInectingConfig;
import com.epic.tle.FieldEngineerManagement.smartcard.KeyMailConfiguration;
import com.epic.tle.FieldEngineerManagement.smartcard.Mode;
import com.epic.tle.FieldEngineerManagement.smartcard.PINMailing;
import com.epic.tle.FieldEngineerManagement.smartcard.PrinterConfig;
import com.epic.tle.FieldEngineerManagement.smartcard.WriteGenKeyToPort;
import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleCardholders;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.constant.Algorithem;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PinVerficationMethod;
import com.epic.tle.util.constant.SelectDevice;
import com.epic.tle.util.constant.Status;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

public class RegisterFieldEngineer extends ActionSupport implements ModelDriven<RegisterFieldEngineerInputBean>, AccessControlService {
    
    RegisterFieldEngineerServiceFactory service;
    ExcelReportFieldEngineerServiceFactory excelService;
    
    RegisterFieldEngineerInputBean inputBean = new RegisterFieldEngineerInputBean();
//    RegisterFieldEngineerServiceOrcel service = new RegisterFieldEngineerServiceOrcel();
    SessionUserBean session;
    EpicTleCardholders crdHolder = new EpicTleCardholders();
    private FieldEngineer fieldEng = new FieldEngineer();
    
    public RegisterFieldEngineerServiceFactory getService() {
        return new RegisterFieldEngineerServiceFactory();
    }
    
    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }
    
    public ExcelReportFieldEngineerServiceFactory getExcelService() {
        return new ExcelReportFieldEngineerServiceFactory();
    }
    
    public SessionUserBean getSession() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }
    
    @Override
    public String execute() {
        return SUCCESS;
    }
    
    public String downloadpdf() {
        inputBean.setToken(getSessionToken());
        try {
            if (inputBean.isSuccessmsg()) {
                System.out.println("pdfDownloadFE");
                addActionMessage(inputBean.getMessagefe());
                if (inputBean.isPdfActive()) {
                    inputBean.setFilename(inputBean.getPdfcardSerialNo() + ".pdf");
                    Map reportParameters = new HashMap();
                    reportParameters.put("bankname", inputBean.getPdfbankname());
                    reportParameters.put("merchantname", inputBean.getPdfmerchantname());
                    reportParameters.put("cardSerialNo", inputBean.getPdfcardSerialNo());
                    reportParameters.put("issuedDate", Util.getLocalDate().toString());
                    reportParameters.put("userPin", inputBean.getPdfuserPin());
                    
                    inputBean.setParameterMap(reportParameters);
                    
                    List datalist = new ArrayList();
                    datalist.add(reportParameters);
                    inputBean.setReportdatalist(datalist);
                    return "pdfDownloadFE";
                }
            } else {
                addActionError(inputBean.getMessagefe());
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            inputBean.setSuccessmsg(false);
            inputBean.setMessagefe(SystemMessage.FIELD_ADD_PDF_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return SUCCESS;
    }
    
    public String Add() {
        inputBean.setToken(getSessionToken());
        try {
            if (doValidation(inputBean)) {
                fieldEng.setSELECTEDDEVICE(Integer.parseInt(inputBean.getDeviceType()));
                fieldEng.setALGORITHEM(Integer.parseInt(inputBean.getAlgo()));
                fieldEng.setPINVERFICATION(Integer.parseInt(inputBean.getPinVerType()));
                
                fieldEng.setSERIALNO(inputBean.getSerialNo());
                fieldEng.setOFFICERNAME(inputBean.getFldEngName());
                fieldEng.setBANKNAME(inputBean.getBankName());
                fieldEng.setLOCATION(inputBean.getLocation());
                fieldEng.setMAXTMKDOWNLOD(Integer.parseInt(inputBean.getMaxKeyDown()));
                fieldEng.setBDKINDEX(Integer.parseInt(inputBean.getBdkindex()));
                
                Process_KEYBean process = new Process_KEYBean();
                boolean res = false;
                
                if (fieldEng.getSELECTEDDEVICE() == SelectDevice.SMARTCAER) {
                    String serialNo = "";
                    serialNo = CommunicatWithSmartCard.generatSerialNumber();
                    System.out.println("----fieldEng.getPINVERFICATION()------ " + fieldEng.getPINVERFICATION());
                    
                    if (fieldEng.getALGORITHEM() == Algorithem.TRIPLEDES) {
                        if (fieldEng.getPINVERFICATION() == PinVerficationMethod.ONLINE) {
                            
                            res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_ONLINE, Mode.ALGO_3DES, serialNo, process);
//                            res =true;
                            System.out.println("---------- response ------------ " + res);
                            if (res) {
                                process = CommunicatWithSmartCard.getProcessObject();
                                System.out.println("---------- object ------------ " + process.getEFWK());
                                System.out.println("---------- object ------------ " + process.getFWK_KVC());
                                fieldEng.setSERIALNO(serialNo);
                                fieldEng.setEFWK(process.getEFWK());
                                fieldEng.setKVC(process.getFWK_KVC());
                                fieldEng.setPINBLOCK(process.getPINBLOCK());
                                
                                getService().getRegEngInf().getPinMailerParems();
                                inputBean.setDownloadActive(true);
                                if (PrinterConfig.STATUS == Status.ACTIVE) {
                                    inputBean.setDownloadActive(false);
                                    //pin mailer
                                    PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                                    inputBean.setMessagefe("Key Was Printed");
                                    inputBean.setSuccessmsg(true);
                                }
                            } else {
                                inputBean.setSuccessmsg(false);
                                inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                            }
                        } else if (fieldEng.getPINVERFICATION() == PinVerficationMethod.OFFLINE) {
                            res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_OFFLINE, Mode.ALGO_3DES, serialNo, process);
                            
                            if (res) {
                                process = CommunicatWithSmartCard.getProcessObject();
                                
                                fieldEng.setSERIALNO(serialNo);
                                fieldEng.setEFWK(process.getEFWK());
                                fieldEng.setKVC(process.getFWK_KVC());
                                fieldEng.setPINBLOCK(process.getPINBLOCK());
                                
                                getService().getRegEngInf().getPinMailerParems();
                                inputBean.setDownloadActive(true);
                                if (PrinterConfig.STATUS == Status.ACTIVE) {
                                    inputBean.setDownloadActive(false);
                                    PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                                    inputBean.setMessagefe("Key Was Printed");
                                    inputBean.setSuccessmsg(true);
                                }
                            } else {
                                inputBean.setSuccessmsg(false);
                                inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                            }
                        }
                    } else if (fieldEng.getALGORITHEM() == Algorithem.RSA) {
                        if (fieldEng.getPINVERFICATION() == PinVerficationMethod.ONLINE) {
                            res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_ONLINE, Mode.ALG_RSA, serialNo, process);
                            if (res) {
                                process = CommunicatWithSmartCard.getProcessObject();
                                
                                fieldEng.setSERIALNO(serialNo);
                                fieldEng.setEFWK(process.getEFWK());
                                fieldEng.setKVC(process.getFWK_KVC());
                                fieldEng.setPINBLOCK(process.getPINBLOCK());
                                fieldEng.setMODULES(process.getRSA_MODULE());
                                fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                                
                                getService().getRegEngInf().getPinMailerParems();
                                inputBean.setDownloadActive(true);
                                if (PrinterConfig.STATUS == Status.ACTIVE) {
                                    PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                                    inputBean.setMessagefe("Key Was Printed");
                                    inputBean.setSuccessmsg(true);
                                    
                                }
                            } else {
                                inputBean.setSuccessmsg(false);
                                inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                            }
                        } else if (fieldEng.getPINVERFICATION() == PinVerficationMethod.OFFLINE) {
                            res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_OFFLINE, Mode.ALG_RSA, serialNo, process);
                            if (res) {
                                process = CommunicatWithSmartCard.getProcessObject();
                                
                                fieldEng.setSERIALNO(serialNo);
                                fieldEng.setEFWK(process.getEFWK());
                                fieldEng.setKVC(process.getFWK_KVC());
                                fieldEng.setPINBLOCK(process.getPINBLOCK());
                                fieldEng.setMODULES(process.getRSA_MODULE());
                                fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                                
                                getService().getRegEngInf().getPinMailerParems();
                                if (PrinterConfig.STATUS == Status.ACTIVE) {
                                    PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                                    inputBean.setMessagefe("Key Was Printed");
                                    inputBean.setSuccessmsg(true);
                                    
                                }
                            } else {
                                inputBean.setSuccessmsg(false);
                                inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                            }
                        }
                    }
                } else if (fieldEng.getSELECTEDDEVICE() == SelectDevice.KEYINJECTION) {
                    System.out.println("process.getPINBLOCK()--------" + process.getPINBLOCK());
                    
                    res = CommunicatWithSmartCard.doProcess(Mode.DEV_KEYINJECTIONTERMINAL, Mode.REG_OFFLINE, Mode.ALGO_3DES, fieldEng.getSERIALNO(), process);
                    if (res) {
                        process = CommunicatWithSmartCard.getProcessObject();
                        
                        fieldEng.setEFWK(process.getEFWK());
                        fieldEng.setKVC(process.getFWK_KVC());
                        fieldEng.setPINBLOCK(process.getPINBLOCK());
                        fieldEng.setMODULES(process.getRSA_MODULE());
                        fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                        
                        String key = process.getFWK() + process.getFWK_KVC();
                        getService().getRegEngInf().getKeyInjectParems();
                        inputBean.setDownloadActive(true);
                        if (KeyInectingConfig.STATUS == Status.ACTIVE) {
                            WriteGenKeyToPort.writKeytoport(key, KeyMailConfiguration.PORT);
                            inputBean.setMessagefe("Key Was Printed");
                            inputBean.setSuccessmsg(true);
                        } else {
                            inputBean.setSuccessmsg(false);
                            inputBean.setMessagefe("Please enable the port to communicate with the terminal.");
                            res = false;
                        }
                    } else {
                        inputBean.setSuccessmsg(false);
                        inputBean.setMessagefe("Key injection fail.");
                    }
                }
                
                if (res) {
                    if (!getService().getRegEngInf().isSerialNumExist(fieldEng.getSERIALNO())) { //From CardHolder
                        getService().getRegEngInf().insertFieldEngineer(fieldEng);  //To CardHolder
                        crdHolder.setSerialno(fieldEng.getSERIALNO());
                        Util.insertHistoryRecord(
                                LogTypes.TLEWEBAPP,
                                getSession().getUserLevel(),
                                SystemModule.FIELD_ENGINEER_MANAGEMENT,
                                TaskVarList.ADD,
                                SystemMessage.FIELD_ADD_SUCESS,
                                null, null,
                                fieldEng.getSERIALNO(),
                                getSession().getId(),
                                SystemSection.REGISTER_FIELD_ENGINEER,
                                null, null);
                        LogFileCreator.writeInforToLog(SystemMessage.FIELD_ADD_SUCESS);
                        inputBean.setSuccessmsg(true);
                        inputBean.setPdfActive(false);
                        inputBean.setMessagefe(SystemMessage.FIELD_ADD_SUCESS);
                        
                        if (fieldEng.getSELECTEDDEVICE() == SelectDevice.SMARTCAER) {
                            System.out.println("smart card pdf");
                            getService().getRegEngInf().getPinMailerParems();
                            if (PrinterConfig.STATUS == Status.INACTIVE) {
                                inputBean.setPdfbankname(fieldEng.getBANKNAME());
                                inputBean.setPdfmerchantname(fieldEng.getOFFICERNAME());
                                inputBean.setPdfcardSerialNo(fieldEng.getSERIALNO());
                                inputBean.setPdfuserPin(process.getPIN());
                                
                                inputBean.setSuccessmsg(true);
                                inputBean.setPdfActive(true);
                                inputBean.setMessagefe(SystemMessage.FIELD_ADD_SUCESS);
                            }
                        }
                    } else {
                        inputBean.setSuccessmsg(false);
                        inputBean.setMessagefe(SystemMessage.FIELD_SERIALNO_ALREADY);
                    }
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            inputBean.setSuccessmsg(false);
            inputBean.setMessagefe(SystemMessage.FIELD_ADD_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        
        return "addfe";
    }
    
    @Override
    public RegisterFieldEngineerInputBean getModel() {
        try {
            
            int[] bdkIndex = HSMConnector.getBDKIndex();
            for (int i : bdkIndex) {
                if (i != -1) {
                    inputBean.getBdkindexMap().put(i, i);
                }
            }
            getService().getRegEngInf().getdevivetype(inputBean);
            getService().getRegEngInf().getAlgoMap(inputBean);
            getService().getRegEngInf().getPinVerTypeMap(inputBean);
            getService().getRegEngInf().getPagePath(inputBean.getPageCode(), inputBean);
            inputBean.setUpstatusMap(Util.getStatusValues(0, 2));
            
        } catch (Exception e) {
            inputBean.setSuccessmsg(false);
            inputBean.setMessagefe(SystemMessage.FIELD_ADD_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return inputBean;
    }
    
    private boolean doValidationUpdate(RegisterFieldEngineerInputBean inputBean) throws Exception {
        boolean ok = false;
        try {
            if (inputBean.getUpserialNumber() == null || inputBean.getUpserialNumber().isEmpty()) {
                addActionError(SystemMessage.FIELD_EMPTY_SERIAL);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getUpserialNumber())) {
                addActionError(SystemMessage.FIELD_INVAID_SERIAL);
                return ok;
            } else if (inputBean.getUpfldEngName() == null || inputBean.getUpfldEngName().isEmpty()) {
                addActionError(SystemMessage.FIELD_EMPTY_NAME);
                return ok;
            } else if (!Util.validateNAME(inputBean.getUpfldEngName())) {
                addActionError(SystemMessage.FIELD_INVAID_NAME);
                return ok;
            } else if (inputBean.getUpbankName() == null || inputBean.getUpbankName().isEmpty()) {
                addActionError(SystemMessage.FIELD_EMPTY_BANKNAME);
                return ok;
            } else if (!Util.validateNAME(inputBean.getUpbankName())) {
                addActionError(SystemMessage.FIELD_INVAID_BANKNAME);
                return ok;
            } else if (inputBean.getUplocation() == null || inputBean.getUplocation().isEmpty()) {
                addActionError(SystemMessage.FIELD_EMPTY_LOCATION);
                return ok;
            } else if (!Util.validateNAME(inputBean.getUplocation())) {
                addActionError(SystemMessage.FIELD_INVAID_LOCATION);
                return ok;
            } else if (inputBean.getUpmaxKeyDown() == null || inputBean.getUpmaxKeyDown().isEmpty()) {
                addActionError(SystemMessage.FIELD_EMPTY_MAXKEYDOWN);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getUpmaxKeyDown())) {
                addActionError(SystemMessage.FIELD_INVAID_MAXKEYDOWN);
                return ok;
            } else if (inputBean.getUpstatus().equals("-1")) {
                addActionError(SystemMessage.FIELD_SELECT_STATUS);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception ex) {
            throw ex;
        }
        return ok;
    }
    
    private boolean doValidation(RegisterFieldEngineerInputBean inputBean) throws Exception {
        boolean ok = false;
        try {
            if (inputBean.getDeviceType().equals("-1")) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_SELECT_DEVICE);
                return ok;
            } else if (!inputBean.getDeviceType().equals("1")) {
                if (inputBean.getSerialNo() == null || inputBean.getSerialNo().isEmpty()) {
                    inputBean.setSuccessmsg(false);
                    inputBean.setMessagefe(SystemMessage.FIELD_EMPTY_SERIAL);
                    return ok;
                } else if (!Util.validateNUMBER(inputBean.getSerialNo())) {
                    inputBean.setSuccessmsg(false);
                    inputBean.setMessagefe(SystemMessage.FIELD_INVAID_SERIAL);
                    return ok;
                }
            }
            if (inputBean.getFldEngName() == null || inputBean.getFldEngName().isEmpty()) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_EMPTY_NAME);
                return ok;
            } else if (!Util.validateFieldEngNAME(inputBean.getFldEngName())) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_INVAID_NAME);
                return ok;
            } else if (inputBean.getBankName() == null || inputBean.getBankName().isEmpty()) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_EMPTY_BANKNAME);
                return ok;
            } else if (!Util.validateNAME(inputBean.getBankName())) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_INVAID_BANKNAME);
                return ok;
            } else if (inputBean.getLocation() == null || inputBean.getLocation().isEmpty()) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_EMPTY_LOCATION);
                return ok;
//            } else if (!Util.validateNAME(inputBean.getLocation())) {
//                inputBean.setSuccessmsg(false);
//                inputBean.setMessagefe(SystemMessage.FIELD_INVAID_LOCATION);
//                return ok;
            } else if (inputBean.getMaxKeyDown() == null || inputBean.getMaxKeyDown().isEmpty()) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_EMPTY_MAXKEYDOWN);
                return ok;
            } else if (!Util.validateNUMBER(inputBean.getMaxKeyDown())) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_INVAID_MAXKEYDOWN);
                return ok;
            } else if (inputBean.getAlgo().equals("-1")) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_SELECT_ALGO);
                return ok;
            } else if (inputBean.getPinVerType().equals("-1")) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_SELECT_PINVERTYPE);
                return ok;
            } else if (inputBean.getBdkindex().equals("-1")) {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_SELECT_BDK);
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
        String page = PageVarList.REGISTER_FIELD_ENGINEER;
        inputBean.setPageCode(page);
        String task = null;
        
        if ("Add".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("downloadpdf".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("XSLcreat1".equals(method)) {
            task = TaskVarList.DOWNLOAD;
            
        } else if ("XSLcreat".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
        } else if ("PinReset".equals(method)) {
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.REGISTER_FIELD_ENGINEER, request);
        inputBean.setVconfirm(true);
        inputBean.setVadd(true);
        inputBean.setVupdate(true);
        inputBean.setVdelete(true);
        inputBean.setVdownload(true);
        inputBean.setVresetpass(true);
        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setVadd(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setVupdate(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setVdelete(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    
                    inputBean.setVdownload(false);
                } else if (task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.VIEW)) {
                    
                    inputBean.setVview(false);
                }
//                }else if(task.getTASK_ID().toString().equalsIgnoreCase(TaskVarList.CONFIRM)){
//                    inputBean.setVconfirm(false);
//                }
            }
        }
        
        return true;
    }
    
    public String List() {
        inputBean.setToken(getSessionToken());
        List<RegisterFieldEngineerBean> dataList = null;
        try {
            int rows = inputBean.getRows();
            int page = inputBean.getPage();
            int to = (rows * page);
            int from = to - rows;
            long records = 0;
            String orderBy = "";
            
            if (!inputBean.getSidx().isEmpty()) {
                orderBy = " order by ch." + inputBean.getSidx() + " " + inputBean.getSord();
            }
            dataList = getService().getRegEngInf().loadFldEngCardHolderDetail(inputBean, rows, from, orderBy);
           
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
    
    public String XSLcreat() {
        inputBean.setToken(getSessionToken());
        try {
            ByteArrayOutputStream outputStream = null;
            Object object = getExcelService().getExcelRepFieEngSerInf().generateExcelReport(inputBean);
            
            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
            }
            LogFileCreator.writeInforToLog(SystemMessage.SUCCESS_EXPORT);
            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.DOWNLOAD, SystemMessage.SUCCESS_EXPORT, null, null, fieldEng.getSERIALNO(), getSession().getId(), SystemSection.REGISTER_FIELD_ENGINEER, null, null);
            
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "excelreportfieldeng";
    }
    
    public String XSLcreat1() {
        inputBean.setToken(getSessionToken());
        System.out.println("XSLcreat");
        try {
            ByteArrayOutputStream outputStream = null;
            Object object = getExcelService().getExcelRepFieEngSerInf().generateExcelReport1(inputBean);
            
            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
            }
            LogFileCreator.writeInforToLog(SystemMessage.SUCCESS_EXPORT);
            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.DOWNLOAD, SystemMessage.SUCCESS_EXPORT, null, null, null, getSession().getId(), SystemSection.REGISTER_FIELD_ENGINEER, null, null);
            
        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return "excelreportfieldeng1";
    }
    
    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            crdHolder.setSerialno(inputBean.getDserialNumber());
            String oldVal = Util.getOldorNewVal(crdHolder, "wu.serialno ='" + crdHolder.getSerialno() + "'");
            if (getService().getRegEngInf().deleteFE(inputBean)) {
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.DELETE, SystemMessage.FIELD_DELETE_SUCESS, null, null, inputBean.getDserialNumber(), getSession().getId(), SystemSection.REGISTER_FIELD_ENGINEER, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.FIELD_DELETE_SUCESS);
                inputBean.setIsDeleted(true);
                inputBean.setDmessage(SystemMessage.FIELD_DELETE_SUCESS);
            } else {
                inputBean.setIsDeleted(false);
                inputBean.setDmessage(SystemMessage.FIELD_DELETE_FAIL);
            }
            
        } catch (Exception e) {
            inputBean.setIsDeleted(false);
            inputBean.setDmessage(SystemMessage.FIELD_DELETE_FAIL);
            LogFileCreator.writeErrorToLog(e);
            e.printStackTrace();
        }
        return "delete";
    }
    
    public String Find() {
        inputBean.setToken(getSessionToken());
        try {
            
            getService().getRegEngInf().findFieldEngineer(inputBean);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }
    
    public String Update() {
        inputBean.setToken(getSessionToken());
        boolean ok = false;
        try {
            if (doValidationUpdate(inputBean)) {
                crdHolder.setSerialno(inputBean.getUpserialNumber());
                String oldVal = Util.getOldorNewVal(crdHolder, "wu.serialno ='" + crdHolder.getSerialno() + "'");
                ok = getService().getRegEngInf().updateFieldEng(inputBean);
                if (ok) {
                    String newVal = Util.getOldorNewVal(crdHolder, "wu.serialno ='" + crdHolder.getSerialno() + "'");
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.FIELD_UPDATE_SUCESS, null, null, inputBean.getUpserialNumber(), getSession().getId(), SystemSection.REGISTER_FIELD_ENGINEER, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.FIELD_UPDATE_SUCESS);
                    addActionMessage(SystemMessage.FIELD_UPDATE_SUCESS);
                    
                    inputBean.setSuccessmsg(true);
                    inputBean.setPdfActive(false);
                    inputBean.setMessagefe(SystemMessage.FIELD_UPDATE_SUCESS);
                    
                } else {
                    inputBean.setMessagefe(SystemMessage.FIELD_UPDATE_FAIL);
                }
            }
        } catch (Exception ex) {
            addActionError(SystemMessage.FIELD_UPDATE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            
        }
        
        return "update";
    }
    
    public String PinReset() {
        inputBean.setToken(getSessionToken());
        FieldEngineer fieldEng = new FieldEngineer();
        try {
            getService().getRegEngInf().getfieldEngData(inputBean.getPinserialNumber(), fieldEng);
            
            Process_KEYBean process = new Process_KEYBean();
            boolean res = false;
            if (fieldEng.getSELECTEDDEVICE() == SelectDevice.SMARTCAER) {
                String serialNo = "";
                serialNo = fieldEng.getSERIALNO();
                
                if (fieldEng.getALGORITHEM() == Algorithem.TRIPLEDES) {
                    if (fieldEng.getPINVERFICATION() == PinVerficationMethod.ONLINE) {
                        
                        res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_ONLINE, Mode.ALGO_3DES, serialNo, process);
                        
                        if (res) {
                            process = CommunicatWithSmartCard.getProcessObject();
                            fieldEng.setSERIALNO(serialNo);
                            fieldEng.setEFWK(process.getEFWK());
                            fieldEng.setKVC(process.getFWK_KVC());
                            fieldEng.setPINBLOCK(process.getPINBLOCK());
                            
                            getService().getRegEngInf().getPinMailerParems();
                            if (PrinterConfig.STATUS == Status.ACTIVE) {
                                PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                            }
                        } else {
                            inputBean.setSuccessmsg(false);
                            inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                        }
                    } else if (fieldEng.getPINVERFICATION() == PinVerficationMethod.OFFLINE) {
                        res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_OFFLINE, Mode.ALGO_3DES, serialNo, process);
                        
                        if (res) {
                            process = CommunicatWithSmartCard.getProcessObject();
                            
                            fieldEng.setEFWK(process.getEFWK());
                            fieldEng.setKVC(process.getFWK_KVC());
                            fieldEng.setPINBLOCK(process.getPINBLOCK());
                            
                            getService().getRegEngInf().getPinMailerParems();
                            
                            if (PrinterConfig.STATUS == Status.ACTIVE) {
                                PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                            }
                        } else {
                            inputBean.setSuccessmsg(false);
                            inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                        }
                    }
                } else if (fieldEng.getALGORITHEM() == Algorithem.RSA) {
                    if (fieldEng.getPINVERFICATION() == PinVerficationMethod.ONLINE) {
                        res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_ONLINE, Mode.ALG_RSA, serialNo, process);
                        if (res) {
                            process = CommunicatWithSmartCard.getProcessObject();
                            
                            fieldEng.setEFWK(process.getEFWK());
                            fieldEng.setKVC(process.getFWK_KVC());
                            fieldEng.setPINBLOCK(process.getPINBLOCK());
                            fieldEng.setMODULES(process.getRSA_MODULE());
                            fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                            
                            getService().getRegEngInf().getPinMailerParems();
                            
                            if (PrinterConfig.STATUS == Status.ACTIVE) {
                                PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                            }
                        } else {
                            inputBean.setSuccessmsg(false);
                            inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                        }
                    } else if (fieldEng.getPINVERFICATION() == PinVerficationMethod.OFFLINE) {
                        res = CommunicatWithSmartCard.doProcess(Mode.DEV_SMART, Mode.REG_OFFLINE, Mode.ALG_RSA, serialNo, process);
                        if (res) {
                            process = CommunicatWithSmartCard.getProcessObject();
                            
                            fieldEng.setEFWK(process.getEFWK());
                            fieldEng.setKVC(process.getFWK_KVC());
                            fieldEng.setPINBLOCK(process.getPINBLOCK());
                            fieldEng.setMODULES(process.getRSA_MODULE());
                            fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                            
                            getService().getRegEngInf().getPinMailerParems();
                            
                            if (PrinterConfig.STATUS == Status.ACTIVE) {
                                PINMailing.PINprint(process.getPIN(), serialNo, fieldEng.getOFFICERNAME(), Util.getDateFE(), fieldEng.getBANKNAME());
                            }
                        } else {
                            inputBean.setSuccessmsg(false);
                            inputBean.setMessagefe("Communication with smart card failed. Please make sure the smart card is inserted into the reader.");
                        }
                    }
                }
            } else if (fieldEng.getSELECTEDDEVICE() == SelectDevice.KEYINJECTION) {
                
                res = CommunicatWithSmartCard.doProcess(Mode.DEV_KEYINJECTIONTERMINAL, Mode.REG_OFFLINE, Mode.ALGO_3DES, fieldEng.getSERIALNO(), process);
                if (res) {
                    process = CommunicatWithSmartCard.getProcessObject();
                    
                    fieldEng.setEFWK(process.getEFWK());
                    fieldEng.setKVC(process.getFWK_KVC());
                    fieldEng.setPINBLOCK(process.getPINBLOCK());
                    fieldEng.setMODULES(process.getRSA_MODULE());
                    fieldEng.setEXPONENT(process.getRSA_EXPORNET());
                    
                    String key = process.getFWK() + process.getFWK_KVC();
                    getService().getRegEngInf().getKeyInjectParems();
                    if (KeyInectingConfig.STATUS == Status.ACTIVE) {
                        WriteGenKeyToPort.writKeytoport(key, KeyMailConfiguration.PORT);
                    } else {
                        inputBean.setSuccessmsg(false);
                        inputBean.setMessagefe("Please enable the port to communicate with the terminal.");
                        res = false;
                    }
                } else {
                    inputBean.setSuccessmsg(false);
                    inputBean.setMessagefe("Key injection fail.");
                }
            }
            
            if (res) {
                
                getService().getRegEngInf().updateFieldEngineer(fieldEng);  //To CardHolder
                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSession().getUserLevel(), SystemModule.FIELD_ENGINEER_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.FIELD_PIN_RESET, null, null, fieldEng.getSERIALNO(), getSession().getId(), SystemSection.REGISTER_FIELD_ENGINEER, null, null);
                LogFileCreator.writeInforToLog(SystemMessage.FIELD_PIN_RESET);
                inputBean.setSuccessmsg(true);
                inputBean.setPdfActive(false);
                inputBean.setMessagefe(SystemMessage.FIELD_PIN_RESET);
                
                if (fieldEng.getSELECTEDDEVICE() == SelectDevice.SMARTCAER) {
                    
                    getService().getRegEngInf().getPinMailerParems();
                    if (PrinterConfig.STATUS == Status.ACTIVE) {
                        inputBean.setPdfbankname(fieldEng.getBANKNAME());
                        inputBean.setPdfmerchantname(fieldEng.getOFFICERNAME());
                        inputBean.setPdfcardSerialNo(fieldEng.getSERIALNO());
                        inputBean.setPdfuserPin(process.getPIN());
                        
                        inputBean.setSuccessmsg(true);
                        inputBean.setPdfActive(true);
                        inputBean.setMessagefe(SystemMessage.FIELD_PIN_RESET + " click ok to download pdf.");
                    }
                }
                
            } else {
                inputBean.setSuccessmsg(false);
                inputBean.setMessagefe(SystemMessage.FIELD_PIN_RESET_FAIL);
            }
            
        } catch (Exception e) {
            e.printStackTrace();
            inputBean.setSuccessmsg(false);
            inputBean.setMessagefe(SystemMessage.FIELD_PIN_RESET_FAIL);
            LogFileCreator.writeErrorToLog(e);
        }
        return "pinreset";
    }
    
}
