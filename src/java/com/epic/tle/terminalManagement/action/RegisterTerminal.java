/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.action;

import com.epic.tle.login.bean.SessionUserBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.mapping.EpicTleTerminal;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import com.epic.tle.terminalManagement.service.ExcelReportTerminal;
import com.epic.tle.terminalManagement.service.RegisterTerminaFactory;
import com.epic.tle.util.AccessControlService;
import com.epic.tle.util.Common;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.LogTypes;
import com.epic.tle.util.constant.PageVarList;
import com.epic.tle.util.constant.SystemMessage;
import com.epic.tle.util.constant.SystemModule;
import com.epic.tle.util.constant.SystemSection;
import com.epic.tle.util.constant.TaskVarList;
import com.epic.tle.util.constant.TokenConst;
import static com.opensymphony.xwork2.Action.SUCCESS;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.Reader;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author danushka_r
 */
public class RegisterTerminal extends ActionSupport implements AccessControlService, ModelDriven<RegisterTerminalBean> {

    RegisterTerminalBean inputBean = new RegisterTerminalBean();
//    TerminalInputBean excelinputBean = new TerminalInputBean();
    RegisterTerminaFactory terminalFactory;
    SessionUserBean sub;
    EpicTleTerminal terminal = new EpicTleTerminal();

    public RegisterTerminaFactory getTerminalFactory() {
        return new RegisterTerminaFactory();
    }

    public String getSessionToken() {
        return (String) ServletActionContext.getRequest().getSession().getAttribute(TokenConst.SESSION_TOKEN);
    }

    public SessionUserBean getSub() {
        return (SessionUserBean) ServletActionContext.getRequest().getSession(false).getAttribute("SessionObject");
    }

    @Override
    public String execute() {
        if (inputBean.getDmessage() != null && !inputBean.getDmessage().isEmpty()) {
            String message = inputBean.getDmessage();
            if (message.equals("inavalidFileType")) {
                addActionError(SystemMessage.TERMINAL_SELECT_FORMAT);
            }
        }
        return SUCCESS;
    }

    public String addTerminal() {
        inputBean.setToken(getSessionToken());
        try {
            if (doAddValidation(inputBean)) {
                getTerminalFactory().getRegisterTerminalServiceInf().addTerminal(inputBean);
                addActionMessage(SystemMessage.TERMINAL_ADD_SUCCESS);
                terminal.setTid(inputBean.getTid());
                Util.insertHistoryRecord(
                        LogTypes.TLEWEBAPP,
                        getSub().getUserLevel(),
                        SystemModule.TERMMINAL_MANAGEMENT,
                        TaskVarList.ADD,
                        SystemMessage.TERMINAL_ADD_SUCCESS + " with name : " + inputBean.getName() + " and id : " + inputBean.getTid(),
                        inputBean.getTid(),
                        inputBean.getMid(),
                        inputBean.getSerialno(),
                        getSub().getId(),
                        SystemSection.REGISTER_TERMINAL,
                        null, null);
                LogFileCreator.writeInforToLog(SystemMessage.TERMINAL_ADD_SUCCESS);
            }

        } catch (Exception ex) {
            ex.printStackTrace();
            addActionError(SystemMessage.TERMINAL_REGISTRATION_FAIL);
            LogFileCreator.writeErrorToLog(ex);
        }

        return "add";
    }

    public String uploadFile() {
        inputBean.setToken(getSessionToken());
        Reader in = null;
        try {
            long s = inputBean.getUpfile().length();
            System.out.println("FILE SIZE " + s);
//            if (s > 20) {
//                System.out.println("return sucees");
//                addActionError(SystemMessage.TERMINAL_FILE_SIZE);
//                return "success";
//
//            }
            if (null == inputBean.getUpfile()) {
                addActionError(SystemMessage.TERMINAL_SELECT_FILE);
                return "success";
            } else {
                System.out.println("file name    " + inputBean.getUpfileFileName());

                String[] fileExtension = inputBean.getUpfileFileName().split("\\.");
                System.out.println("Extension last value  " + fileExtension[fileExtension.length - 1]);

                if (!fileExtension[fileExtension.length - 1].equalsIgnoreCase("txt")) {
                    addActionError(SystemMessage.TERMINAL_SELECT_FORMAT);
                    //return "invalid_file_format";
                    return "success";
                } else if (inputBean.getUpfile().length() > 500485760) {
                    addActionError(SystemMessage.TERMINAL_FILE_SIZE);
                    return "success";
                } else {
                    in = new FileReader(inputBean.getUpfile());
                    BufferedReader reader = new BufferedReader(in);
                    StringBuffer result = new StringBuffer();
                    result.append("-------------------------------------------------\r\n" + "@@@@  @@@@  @@@@@  @@@@@     @@@@@@  @      @@@@@ \r\n" + "@     @   @   @    @            @    @      @     \r\n" + "@@@@  @@@@    @    @@@@@        @    @      @@@@@ \r\n" + "@     @       @    @            @    @      @     \r\n" + "@@@@  @     @@@@@  @@@@@        @    @@@@@  @@@@@ \r\n" + "-------------------------------------------------\r\n" + "\r\n");
                    int j = 1;
                    while (true) {
                        String lineContent = reader.readLine();

                        if (lineContent == null || lineContent.equals("")) {
                            break;
                        }

                        String[] array = lineContent.split("\\|");

                        if (array.length != 9) {
                            result.append("Incorrect Total Record .\r\n");
                            Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.ADD, SystemMessage.TERMINAL_UPLOAD_FAIL, null, null, null, getSub().getId(), SystemSection.REGISTER_TERMINAL, null, null);
                        } else {
                            boolean insert = true;
                            result.append("-------------------------------------------------\r\n" + "      Registering Terminal Record [ " + (j++) + ":" + array[0] + " ]\r\n" + "-------------------------------------------------\r\n");
                            if (!array[0].matches("\\d{8}")) {  // Terminal ID Should be 8 numbers

                                insert = false;
                                result.append("Incorrect TID.\r\n");
                            }

                            if (!array[1].matches("\\d{8,15}")) { // MID should be 8 to 15 numbers

                                insert = false;
                                result.append("Incorrect MID.\r\n");
                            }
                            if (!array[2].matches("\\d{0,16}")) { // Serial No should br 0 to 16 numbers

                                insert = false;
                                result.append("Incorrect Serial No.\r\n");
                            }
                            if (array[3].length() > 80) {
                                if (array[3].length() == 0) {
                                    array[3] = null;
                                }
                                insert = false;
                                result.append("Incorrect Name field Length.\r\n");
                            }
                            if (array[4].length() > 50) {
                                if (array[4].length() == 0) {
                                    array[4] = null;
                                }
                                insert = false;
                                result.append("Incorrect Bank field Length.\r\n");
                            }
                            if (array[5].length() > 80) {
                                if (array[5].length() == 0) {
                                    array[5] = null;
                                }
                                insert = false;
                                result.append("Incorrect Location field Length.\r\n");
                            }
                            if (array[6].length() > 50) {
                                if (array[6].length() == 0) {
                                    array[6] = null;
                                }
                                insert = false;
                                result.append("Incorrect brand field Length.\r\n");
                            }
                            if (!array[7].matches("\\d{1}")) {
                                insert = false;
                                result.append("Incorrect encryption format.\r\n");
                            } else if (Integer.parseInt(array[7]) < 1 && Integer.parseInt(array[7]) > 4) {
                                result.append("Incorrect encryption type.\r\n");
                            }

                            if (!array[8].matches("\\d{1}")) {
                                insert = false;
                                result.append("Incorrect status format.\r\n");
                            } else if (Integer.parseInt(array[8]) < 1 && Integer.parseInt(array[8]) > 2) {
                                result.append("Incorrect status type.\r\n");
                            }
                            if (insert) {

                                inputBean.setTid(array[0]);
                                inputBean.setMid(array[1]);
                                inputBean.setSerialno(array[2]);
                                inputBean.setName(array[3]);
                                inputBean.setBank(array[4]);
                                inputBean.setLocation(array[5]);
                                inputBean.setTerBrand(array[6]);
                                inputBean.setEncType(array[7]);
                                inputBean.setEncStatus(array[8]);
                                inputBean.setBinPrf("1");
                                inputBean.setTeminalRefProf("1");

                                try {
                                    getTerminalFactory().getRegisterTerminalServiceInf().addTerminal(inputBean);
                                    terminal.setTid(inputBean.getTid());
                                    String newVal = Util.getOldorNewVal(terminal, "wu.tid ='" + terminal.getTid() + "'");
                                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.UPLOAD, SystemMessage.TERMINAL_ADD_SUCCESS, inputBean.getTid(), inputBean.getMid(), inputBean.getSerialno(), getSub().getId(), SystemSection.REGISTER_TERMINAL, null, newVal);
                                    result.append("Terminal registerd.\r\n");
                                } catch (org.hibernate.exception.ConstraintViolationException e) {
                                    LogFileCreator.writeErrorToLog(e);
                                    result.append("TID already exist.\r\n");
                                }

                            } else {
                                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.ADD, SystemMessage.TERMINAL_UPLOAD_FAIL, null, null, null, getSub().getId(), SystemSection.REGISTER_TERMINAL, null, null);
                                result.append("Terminal not registerd.\r\n");
                            }

                        }
                        result.append("-------------------------------------------------\r\n" + "\r\n" + "\r\n");

                    }

                    addActionMessage(SystemMessage.TERMINAL_LIST_ADD_SUCCESS);
                    BufferedWriter bwr = null;
                    bwr = new BufferedWriter(new FileWriter(new File(Util.getOSLogPath(Configurations.FE_TEMP_FILE_PATH_LIN) + "terAddList.txt")));
                    bwr.write(result.toString());
                    bwr.flush();
                    bwr.close();

                    File fileToDownload = new File(Util.getOSLogPath(Configurations.FE_TEMP_FILE_PATH_LIN) + "terAddList.txt");
                    inputBean.setContentLength(fileToDownload.length());
                    inputBean.setInputStream(new FileInputStream(fileToDownload));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
            addActionError(SystemMessage.TERMINAL_LISTADD_FAIL);
            return "success";
        }

        return "downloadTerList";
    }

    public String List() {
        inputBean.setToken(getSessionToken());
        List<RegisterTerminalBean> dataList = null;
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
            dataList = getTerminalFactory().getRegisterTerminalServiceInf().loadTerminalUsers(inputBean, rows, from, orderBy);

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
            //LogFileCreator.writeInforToLog(SystemMessage.SEARCH_SUCCESS);
        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return "list";
    }

    public String XSLcreat() {
        inputBean.setToken(getSessionToken());
        String result = null;

        try {
            ByteArrayOutputStream outputStream = null;
//            Object object = getExcelService().getExcelRepTeminalInf().generateExcelReport();
            Object object = ExcelReportTerminal.generateExcelReport(inputBean);

            if (object instanceof XSSFWorkbook) {
                XSSFWorkbook workbook = (XSSFWorkbook) object;
                outputStream = new ByteArrayOutputStream();
                workbook.write(outputStream);
                inputBean.setExcelStream(new ByteArrayInputStream(outputStream.toByteArray()));
                result = "excelreportterminal";
            }

        } catch (Exception e) {
            e.printStackTrace();
            LogFileCreator.writeErrorToLog(e);
        }
        return result;
    }

    public String Delete() {
        inputBean.setToken(getSessionToken());
        try {
            terminal.setTid(inputBean.getTid());
            String oldVal = Util.getOldorNewVal(terminal, "wu.tid ='" + terminal.getTid() + "'");

            if (getTerminalFactory().getRegisterTerminalServiceInf().deleteTerminalUser(inputBean.getTid())) {
                inputBean.setDmessage(SystemMessage.TERMINAL_DELETE_SUCCESS);
                inputBean.setIsDeleted(true);

                Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.DELETE, SystemMessage.TERMINAL_DELETE_SUCCESS, null, null, null, getSub().getId(), SystemSection.REGISTER_TERMINAL, oldVal, null);
                LogFileCreator.writeInforToLog(SystemMessage.TERMINAL_DELETE_SUCCESS);
            }
        } catch (Exception e) {
            LogFileCreator.writeErrorToLog(e);
            inputBean.setDmessage(SystemMessage.TERMINAL_DELETE_FAIL);
            inputBean.setIsDeleted(false);
        }

        return "delete";
    }

    public String Find() {
        inputBean.setToken(getSessionToken());
        try {
            getTerminalFactory().getRegisterTerminalServiceInf().findUser(inputBean);
        } catch (Exception ex) {
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);
        }
        return "find";
    }

    public String Update() {
        inputBean.setToken(getSessionToken());
        boolean ok = false;
        try {
            if (doEditValidation(inputBean)) {
                terminal.setTid(inputBean.getTid());
                String oldVal = Util.getOldorNewVal(terminal, "wu.tid ='" + terminal.getTid() + "'");
                ok = getTerminalFactory().getRegisterTerminalServiceInf().updateTerminal(inputBean);
                if (ok) {
                    String newVal = Util.getOldorNewVal(terminal, "wu.tid ='" + terminal.getTid() + "'");
                    Util.insertHistoryRecord(LogTypes.TLEWEBAPP, getSub().getUserLevel(), SystemModule.TERMMINAL_MANAGEMENT, TaskVarList.UPDATE, SystemMessage.TERMINAL_UPDATE_SUCCESS, null, null, null, getSub().getId(), SystemSection.REGISTER_TERMINAL, oldVal, newVal);
                    LogFileCreator.writeInforToLog(SystemMessage.TERMINAL_UPDATE_SUCCESS);
                    addActionMessage(SystemMessage.TERMINAL_UPDATE_SUCCESS);

                } else {
                    addActionError(SystemMessage.TERMINAL_UPDATE_FAIL);
                }
            }
        } catch (Exception ex) {
            addActionError(SystemMessage.TERMINAL_UPDATE_FAIL);
            ex.printStackTrace();
            LogFileCreator.writeErrorToLog(ex);

        }

        return "update";
    }

    private boolean doAddValidation(RegisterTerminalBean terminalBean) throws Exception {
        boolean ok = false;
        try {
            String terminal = getTerminalFactory().getRegisterTerminalServiceInf().GetResult(inputBean);
            if (terminal != null) {
                addActionError("This Terminal ID Already Define.");
                return ok;
            }
            if ((terminalBean.getTid().isEmpty()) || !Util.validateNUMBER(terminalBean.getTid())) {
                addActionError(SystemMessage.TERMINAL_ID_INVALID);
                return ok;
            } else if (terminalBean.getTid().length() != 8) {
                addActionError(SystemMessage.TERMINAL_ID_TOO_LONG);
                return ok;
            } else if (terminalBean.getMid() == null || terminalBean.getMid().isEmpty() || !Util.validateNUMBER(terminalBean.getMid())) {
                addActionError(SystemMessage.TERMINAL_INVALID_MERCHANT_ID);
                return ok;
            } else if (!terminalBean.getMid().matches("\\d{8,15}")) {
                addActionError(SystemMessage.TERMINAL_LENGTH);
                return ok;
            } else if ((terminalBean.getSerialno() != null || !terminalBean.getSerialno().isEmpty()) && !Util.validateNUMBER(terminalBean.getSerialno())) {
                addActionError(SystemMessage.TERMINAL_INVALID_SERIAL_NO);
                return ok;
            }
            if ((terminalBean.getName() != null && !terminalBean.getName().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getName())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_NAME);
                    return ok;
                }
            }
            if ((terminalBean.getBank() != null && !terminalBean.getBank().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getBank())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_BANK_NAME);
                    return ok;
                }
            }
//            if ((terminalBean.getLocation() != null && !terminalBean.getLocation().isEmpty())) {
//                if (!Util.validateNAME(terminalBean.getLocation())) {
//                    addActionError(SystemMessage.TERMINAL_INVALID_LOCATION_NAME);
//                    return ok;
//                }
//            }
//            if ((terminalBean.getLocation() != null && !terminalBean.getLocation().isEmpty())) {
//                if (!Util.validateNameWithSpcCharactors(terminalBean.getLocation())) {
//                    addActionError(SystemMessage.TERMINAL_INVALID_LOCATION_NAME);
//                    return ok;
//                }
//            }
            if ((terminalBean.getTerBrand() != null && !terminalBean.getTerBrand().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getTerBrand())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_TERBRAND_NAME);
                    return ok;
                }
            }
            if (terminalBean.getEncType().equals("-1")) {
                addActionError(SystemMessage.TERMINAL_ENCTYPE_NOT_SELECTED);
                return ok;
            } else if (terminalBean.getEncStatus().equals("-1")) {
                addActionError(SystemMessage.TERMINAL_NONENCTNX_NOT_SELECTED);
                return ok;
            } else {
                ok = true;
            }
        } catch (Exception e) {
            throw e;
        }
        return ok;
    }

    private boolean doEditValidation(RegisterTerminalBean terminalBean) throws Exception {

        boolean ok = false;
        try {
            if (terminalBean.getMid() == null || terminalBean.getMid().isEmpty() || !Util.validateNUMBER(terminalBean.getMid())) {
                addActionError(SystemMessage.TERMINAL_INVALID_MERCHANT_ID);
                return ok;
            } else if (!terminalBean.getMid().matches("\\d{8,15}")) {
                addActionError(SystemMessage.TERMINAL_LENGTH);
                return ok;
            }
            if ((terminalBean.getSerialNo() != null || !terminalBean.getSerialNo().isEmpty())) {
                if (!Util.validateNUMBER(terminalBean.getSerialNo())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_SERIAL_NO);
                    return ok;
                }
            }
            if ((terminalBean.getName() != null && !terminalBean.getName().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getName())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_NAME);
                    return ok;
                }
            }
            if ((terminalBean.getBank() != null && !terminalBean.getBank().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getBank())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_BANK_NAME);
                    return ok;
                }
            }
//            if ((terminalBean.getLocation() != null && !terminalBean.getLocation().isEmpty())) {
//                if (!Util.validateNAME(terminalBean.getLocation())) {
//                    addActionError(SystemMessage.TERMINAL_INVALID_LOCATION_NAME);
//                    return ok;
//                }
//            }
            if ((terminalBean.getTerminalBrand() != null && !terminalBean.getTerminalBrand().isEmpty())) {
                if (!Util.validateNAME(terminalBean.getTerminalBrand())) {
                    addActionError(SystemMessage.TERMINAL_INVALID_TERBRAND_NAME);
                    return ok;
                }
            }
            if (terminalBean.getEncryptionType().equals("-1")) {
                addActionError(SystemMessage.TERMINAL_ENCTYPE_NOT_SELECTED);
                return ok;
            } else if (terminalBean.getStatus().equals("-1")) {
                addActionError(SystemMessage.TERMINAL_STATUS_SELECTED);
                return ok;
            } else if (terminalBean.getNonEncryptionTransaction().equals("-1")) {
                addActionError(SystemMessage.TERMINAL_NONENCTNX_NOT_SELECTED);
                return ok;
            } else if (terminalBean.getUpBinStatus() == -1) {
                addActionError(SystemMessage.TERMINAL_BIN_PROFILE_STATUS_NOT_SELECTED);
                return ok;
            } else if ((terminalBean.getBinPrf() == null || terminalBean.getBinPrf().equals("-1")) && terminalBean.getUpBinStatus() == 1) {
                addActionError(SystemMessage.TERMINAL_BIN_PROFILE_NOT_SELECTED);
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
        String page = PageVarList.REGISTER_TERMINAL;
        inputBean.setPageCode(page);
        String task = null;
        if ("addTerminal".equals(method)) {
            task = TaskVarList.ADD;
        } else if ("uploadFile".equals(method)) {
            task = TaskVarList.UPLOAD;
        } else if ("List".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("XSLcreat".equals(method)) {
            task = TaskVarList.DOWNLOAD;
        } else if ("Delete".equals(method)) {
            task = TaskVarList.DELETE;
        } else if ("Find".equals(method)) {
            task = TaskVarList.VIEW;
        } else if ("Update".equals(method)) {
            task = TaskVarList.UPDATE;
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
        List<TaskBean> tasklist = new Common().getUserTaskListByPage(PageVarList.REGISTER_TERMINAL, request);
        inputBean.setAdd(true);
        inputBean.setUpdate(true);
        inputBean.setDelete(true);
        inputBean.setUpload(true);
        inputBean.setDownload(true);
        inputBean.setFind(true);
        inputBean.setView(true);

        if (tasklist != null && tasklist.size() > 0) {
            for (TaskBean task : tasklist) {
                if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.ADD)) {
                    inputBean.setAdd(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPDATE)) {
                    inputBean.setUpdate(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DELETE)) {
                    inputBean.setDelete(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.UPLOAD)) {
                    inputBean.setUpload(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.DOWNLOAD)) {
                    inputBean.setDownload(false);
                } else if (task.getTASK_ID().equalsIgnoreCase(TaskVarList.VIEW)) {
                    inputBean.setView(false);
                }
            }
        }
        return true;
    }

    @Override
    public RegisterTerminalBean getModel() {
        try {
            getTerminalFactory().getRegisterTerminalServiceInf().setETypeDropDown(inputBean);
            getTerminalFactory().getRegisterTerminalServiceInf().BinPrfDropDown(inputBean);
            getTerminalFactory().getRegisterTerminalServiceInf().RepPrfDropDown(inputBean);
            inputBean.getEncStatusMap().put(1, "Enable");
            inputBean.getEncStatusMap().put(2, "Disable");

            getTerminalFactory().getRegisterTerminalServiceInf().initiateValuesToMap(inputBean);
            inputBean.getStatusMap().put(1, "ACTIVE");
            inputBean.getStatusMap().put(2, "INACTIVE");

            inputBean.getBinStatusMap().put(1, "ACTIVE");
            inputBean.getBinStatusMap().put(2, "INACTIVE");

            inputBean.getNonEncryptionStatusMap().put(1, "ACTIVE");
            inputBean.getNonEncryptionStatusMap().put(2, "INACTIVE");
            getTerminalFactory().getRegisterTerminalServiceInf().getPagePath(inputBean.getPageCode(), inputBean);

        } catch (Exception ex) {
            LogFileCreator.writeErrorToLog(ex);
        }
        return inputBean;
    }

}
