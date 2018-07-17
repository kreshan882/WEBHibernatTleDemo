/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.bean;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kasun_k
 */
public class RegisterFieldEngineerInputBean {

    //AddFieldEngineerBean
    private String deviceType;
    private Map<Integer, String> deviceTypeMap = new HashMap<Integer, String>();
    private Map<Integer, Integer> bdkindexMap = new HashMap<Integer, Integer>();

    private String serialNo;
    private String fldEngName;
    private String bankName;
    private String location;
    private String maxKeyDown;
    private String filename;
    private String algo;
    private Map<Integer, String> algoMap = new HashMap<Integer, String>();
    private String pinVerType;
    private Map<Integer, String> pinVerTypeMap = new HashMap<Integer, String>();

    //jesper report
    Map parameterMap = null;
    List reportdatalist = null;

    public void setGridModel(List<RegisterFieldEngineerBean> gridModel) {
        this.gridModel = gridModel;
    }
    private String pdfbankname;
    private String pdfmerchantname;
    private String pdfcardSerialNo;
    private String pdfuserPin;

    private boolean pdfActive;
    private boolean successmsg;
    private String messagefe;

    //EditViewFieldEngineerInputBean
    private List<RegisterFieldEngineerBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private boolean loadonce = false;
    private String PageCode = "";

    //***************Working Path*************
    private String Module = "";
    private String Section = "";
    //****************Search**********************
    private String searchSerial = "";
    private String selectedDevice = "";
    private String officerName = "";
    private String locations = "";
    private String algorithm = "";
    private String pinVerification = "";
    private String bdkindex = "";

    //****************Edit**********************
    private String upserialNumber;
    private String upfldEngName;
    private String upbankName;
    private String uplocation;
    private String upmaxKeyDown;
    private String upstatus;
    private Map<Integer, String> upstatusMap = new HashMap<Integer, String>();

//    //****************delete**********************
    private String dserialNumber;
    private boolean isDeleted;
    private String dmessage;

    //****************Excel**********************
    private ByteArrayInputStream excelStream;

    //****************Reset Pin**********************
    private String pinserialNumber;

    /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vview;
    private boolean vdownload;
    private boolean vresetpass;
    private boolean vconfirm;
    //    private boolean vactdct;
    /*-------for access control-----------*/

    private boolean downloadActive;

    //for generate terminal excel
    private String terminalId;
    private String statusValue;
    private String encryptionStatusValue;
    private String nonEncryptionStatusValue;
    
    //***************Token************************
    private String Token;


    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getStatusValue() {
        return statusValue;
    }

    public void setStatusValue(String statusValue) {
        this.statusValue = statusValue;
    }

    public String getEncryptionStatusValue() {
        return encryptionStatusValue;
    }

    public void setEncryptionStatusValue(String encryptionStatusValue) {
        this.encryptionStatusValue = encryptionStatusValue;
    }

    public String getNonEncryptionStatusValue() {
        return nonEncryptionStatusValue;
    }

    public void setNonEncryptionStatusValue(String nonEncryptionStatusValue) {
        this.nonEncryptionStatusValue = nonEncryptionStatusValue;
    }
    
    

    public boolean isDownloadActive() {
        return downloadActive;
    }

    public void setDownloadActive(boolean downloadActive) {
        this.downloadActive = downloadActive;
    }

    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdate() {
        return vupdate;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void setVupdate(boolean vupdate) {
        this.vupdate = vupdate;
    }

    public boolean isVdelete() {
        return vdelete;
    }

    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
    }

    public boolean isVdownload() {
        return vdownload;
    }

    public void setVdownload(boolean vdownload) {
        this.vdownload = vdownload;
    }

    public boolean isVresetpass() {
        return vresetpass;
    }

    public void setVresetpass(boolean vresetpass) {
        this.vresetpass = vresetpass;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Map<Integer, String> getDeviceTypeMap() {
        return deviceTypeMap;
    }

    public void setDeviceTypeMap(Map<Integer, String> deviceTypeMap) {
        this.deviceTypeMap = deviceTypeMap;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getFldEngName() {
        return fldEngName;
    }

    public void setFldEngName(String fldEngName) {
        this.fldEngName = fldEngName;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMaxKeyDown() {
        return maxKeyDown;
    }

    public void setMaxKeyDown(String maxKeyDown) {
        this.maxKeyDown = maxKeyDown;
    }

    public String getAlgo() {
        return algo;
    }

    public void setAlgo(String algo) {
        this.algo = algo;
    }

    public Map<Integer, String> getAlgoMap() {
        return algoMap;
    }

    public void setAlgoMap(Map<Integer, String> algoMap) {
        this.algoMap = algoMap;
    }

    public String getPinVerType() {
        return pinVerType;
    }

    public void setPinVerType(String pinVerType) {
        this.pinVerType = pinVerType;
    }

    public Map<Integer, String> getPinVerTypeMap() {
        return pinVerTypeMap;
    }

    public void setPinVerTypeMap(Map<Integer, String> pinVerTypeMap) {
        this.pinVerTypeMap = pinVerTypeMap;
    }

    public Map getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List getReportdatalist() {
        return reportdatalist;
    }

    public void setReportdatalist(List reportdatalist) {
        this.reportdatalist = reportdatalist;
    }

    public String getPdfbankname() {
        return pdfbankname;
    }

    public void setPdfbankname(String pdfbankname) {
        this.pdfbankname = pdfbankname;
    }

    public String getPdfmerchantname() {
        return pdfmerchantname;
    }

    public void setPdfmerchantname(String pdfmerchantname) {
        this.pdfmerchantname = pdfmerchantname;
    }

    public String getPdfcardSerialNo() {
        return pdfcardSerialNo;
    }

    public void setPdfcardSerialNo(String pdfcardSerialNo) {
        this.pdfcardSerialNo = pdfcardSerialNo;
    }

    public String getPdfuserPin() {
        return pdfuserPin;
    }

    public void setPdfuserPin(String pdfuserPin) {
        this.pdfuserPin = pdfuserPin;
    }

    public boolean isPdfActive() {
        return pdfActive;
    }

    public void setPdfActive(boolean pdfActive) {
        this.pdfActive = pdfActive;
    }

    public boolean isSuccessmsg() {
        return successmsg;
    }

    public void setSuccessmsg(boolean successmsg) {
        this.successmsg = successmsg;
    }

    public String getMessagefe() {
        return messagefe;
    }

    public void setMessagefe(String messagefe) {
        this.messagefe = messagefe;
    }

    public List<RegisterFieldEngineerBean> getGridModel() {
        return gridModel;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public String getSearchSerial() {
        return searchSerial;
    }

    public void setSearchSerial(String searchSerial) {
        this.searchSerial = searchSerial;
    }

    public String getUpserialNumber() {
        return upserialNumber;
    }

    public void setUpserialNumber(String upserialNumber) {
        this.upserialNumber = upserialNumber;
    }

    public String getUpfldEngName() {
        return upfldEngName;
    }

    public void setUpfldEngName(String upfldEngName) {
        this.upfldEngName = upfldEngName;
    }

    public String getUpbankName() {
        return upbankName;
    }

    public void setUpbankName(String upbankName) {
        this.upbankName = upbankName;
    }

    public String getUplocation() {
        return uplocation;
    }

    public void setUplocation(String uplocation) {
        this.uplocation = uplocation;
    }

    public String getUpmaxKeyDown() {
        return upmaxKeyDown;
    }

    public void setUpmaxKeyDown(String upmaxKeyDown) {
        this.upmaxKeyDown = upmaxKeyDown;
    }

    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    public Map<Integer, String> getUpstatusMap() {
        return upstatusMap;
    }

    public void setUpstatusMap(Map<Integer, String> upstatusMap) {
        this.upstatusMap = upstatusMap;
    }

    public String getDserialNumber() {
        return dserialNumber;
    }

    public void setDserialNumber(String dserialNumber) {
        this.dserialNumber = dserialNumber;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDmessage() {
        return dmessage;
    }

    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
    }

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getPinserialNumber() {
        return pinserialNumber;
    }

    public void setPinserialNumber(String pinserialNumber) {
        this.pinserialNumber = pinserialNumber;
    }

    /**
     * @return the PageCode
     */
    public String getPageCode() {
        return PageCode;
    }

    /**
     * @param PageCode the PageCode to set
     */
    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    /**
     * @return the Module
     */
    public String getModule() {
        return Module;
    }

    /**
     * @param Module the Module to set
     */
    public void setModule(String Module) {
        this.Module = Module;
    }

    /**
     * @return the Section
     */
    public String getSection() {
        return Section;
    }

    /**
     * @param Section the Section to set
     */
    public void setSection(String Section) {
        this.Section = Section;
    }

    /**
     * @return the vview
     */
    public boolean isVview() {
        return vview;
    }

    /**
     * @param vview the vview to set
     */
    public void setVview(boolean vview) {
        this.vview = vview;
    }

    public String getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(String selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public String getOfficerName() {
        return officerName;
    }

    public void setOfficerName(String officerName) {
        this.officerName = officerName;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public void setAlgorithm(String algorithm) {
        this.algorithm = algorithm;
    }

    public String getPinVerification() {
        return pinVerification;
    }

    public void setPinVerification(String pinVerification) {
        this.pinVerification = pinVerification;
    }

    public boolean isVconfirm() {
        return vconfirm;
    }

    public void setVconfirm(boolean vconfirm) {
        this.vconfirm = vconfirm;
    }
    
    public String getToken() {
         return Token;
    }
    /**
     * 
     * @param Token the token for CSRF
     */
    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getBdkindex() {
        return bdkindex;
    }

    public void setBdkindex(String bdkindex) {
        this.bdkindex = bdkindex;
    }

    public Map<Integer, Integer> getBdkindexMap() {
        return bdkindexMap;
    }

    public void setBdkindexMap(Map<Integer, Integer> bdkindexMap) {
        this.bdkindexMap = bdkindexMap;
    }

}
