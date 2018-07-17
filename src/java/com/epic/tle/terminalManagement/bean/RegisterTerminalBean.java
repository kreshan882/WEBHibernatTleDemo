/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.bean;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chalaka_n
 */
public class RegisterTerminalBean {
    //form variable
    private String tid;
    private String mid;
    private String serialno;
    private String name;
    private String bank;
    private String location;
    private String terBrand;
    private String encType;
    private String BinPrf;
    private String encStatus;
    private String binProfCheck;
    private String teminalRefProf;
    private String upteminalRefProf;
    
    
    
    //set dropdown list map
    private Map<Integer, String> encTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> encStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> BinPrfMap = new HashMap<Integer, String>();
    private Map<Integer, String> teminalRefProfMap = new HashMap<Integer, String>();

    
    //file upload
    private File upfile;  
    private String upfileContentType;
    private String upfileFileName;
    
    //file download    
    private InputStream inputStream;
    private ByteArrayInputStream textStream;
    
    //edit n view
    private String terminalId;
    private String serialNo;
    private String terminalBrand;
    private String registerDate;
    private String encryptionStatus;
    private String status;
    private long fullCount;
    
    private String blockBinProfNum;
    private String blockBinProfName;
    private String binStatus;
    
    //view table data
    
    private List<RegisterTerminalBean> gridModel;
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
   
    private String fileName;
    private long contentLength;

    private String filePath;
    private String logFileSelection;

    private Map<Integer, String> statusMap = new HashMap<>();
    private Map<Integer, String> binStatusMap = new HashMap<>();
    private Map<Integer, String> encryptionStatusMap = new HashMap<>();
    private Map<Integer, String> nonEncryptionStatusMap = new HashMap<>();

    
    private String statusValue;
    private String encryptionStatusValue;
    private String nonEncryptionStatusValue;
    //private String encryptionStatus;

    //****************delete**********************
    private String dtid;
    private boolean isDeleted;
    private String dmessage;
    private String PageCode="";
        
       //***************Working Path*************
    private String Module="";
    private String Section="";

    //****************Edit**********************
    
    private String nonEncryptionTransaction;
    private String encryptionType;
    
    private int upBinPrf;
    private int upBinStatus;

    //**************Export XSL***************
    private InputStream excelStream;
    
    
    //task privilages
    private boolean add;
    private boolean update;
    private boolean delete;
    private boolean upload;
    private boolean download;
    private boolean find;
    private boolean view;
    
    //***************Token************************
    private String Token;

    public String getBinProfCheck() {
        return binProfCheck;
    }

    public String getTeminalRefProf() {
        return teminalRefProf;
    }

    public void setTeminalRefProf(String teminalRefProf) {
        this.teminalRefProf = teminalRefProf;
    }

    public String getUpteminalRefProf() {
        return upteminalRefProf;
    }

    public void setUpteminalRefProf(String upteminalRefProf) {
        this.upteminalRefProf = upteminalRefProf;
    }

    

    public Map<Integer, String> getTeminalRefProfMap() {
        return teminalRefProfMap;
    }

    public void setTeminalRefProfMap(Map<Integer, String> teminalRefProfMap) {
        this.teminalRefProfMap = teminalRefProfMap;
    }

    
    public void setBinProfCheck(String binProfCheck) {
        this.binProfCheck = binProfCheck;
    }

    
    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getTerBrand() {
        return terBrand;
    }

    public void setTerBrand(String terBrand) {
        this.terBrand = terBrand;
    }

    public String getEncType() {
        return encType;
    }

    public void setEncType(String encType) {
        this.encType = encType;
    }

    public String getEncStatus() {
        return encStatus;
    }

    public void setEncStatus(String encStatus) {
        this.encStatus = encStatus;
    }

    public Map<Integer, String> getEncTypeMap() {
        return encTypeMap;
    }

    public void setEncTypeMap(Map<Integer, String> encTypeMap) {
        this.encTypeMap = encTypeMap;
    }

    public Map<Integer, String> getEncStatusMap() {
        return encStatusMap;
    }

    public void setEncStatusMap(Map<Integer, String> encStatusMap) {
        this.encStatusMap = encStatusMap;
    }

    public File getUpfile() {
        return upfile;
    }

    public void setUpfile(File upfile) {
        this.upfile = upfile;
    }

    public String getUpfileContentType() {
        return upfileContentType;
    }

    public void setUpfileContentType(String upfileContentType) {
        this.upfileContentType = upfileContentType;
    }

    public String getUpfileFileName() {
        return upfileFileName;
    }

    public void setUpfileFileName(String upfileFileName) {
        this.upfileFileName = upfileFileName;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    public ByteArrayInputStream getTextStream() {
        return textStream;
    }

    public void setTextStream(ByteArrayInputStream textStream) {
        this.textStream = textStream;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTerminalBrand() {
        return terminalBrand;
    }

    public void setTerminalBrand(String terminalBrand) {
        this.terminalBrand = terminalBrand;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEncryptionStatus() {
        return encryptionStatus;
    }

    public void setEncryptionStatus(String encryptionStatus) {
        this.encryptionStatus = encryptionStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public List<RegisterTerminalBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<RegisterTerminalBean> gridModel) {
        this.gridModel = gridModel;
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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getContentLength() {
        return contentLength;
    }

    public void setContentLength(long contentLength) {
        this.contentLength = contentLength;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getLogFileSelection() {
        return logFileSelection;
    }

    public void setLogFileSelection(String logFileSelection) {
        this.logFileSelection = logFileSelection;
    }

    public Map<Integer, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<Integer, String> statusMap) {
        this.statusMap = statusMap;
    }

    public Map<Integer, String> getEncryptionStatusMap() {
        return encryptionStatusMap;
    }

    public void setEncryptionStatusMap(Map<Integer, String> encryptionStatusMap) {
        this.encryptionStatusMap = encryptionStatusMap;
    }

    public Map<Integer, String> getNonEncryptionStatusMap() {
        return nonEncryptionStatusMap;
    }

    public void setNonEncryptionStatusMap(Map<Integer, String> nonEncryptionStatusMap) {
        this.nonEncryptionStatusMap = nonEncryptionStatusMap;
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

    public String getDtid() {
        return dtid;
    }

    public void setDtid(String dtid) {
        this.dtid = dtid;
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

    public String getNonEncryptionTransaction() {
        return nonEncryptionTransaction;
    }

    public void setNonEncryptionTransaction(String nonEncryptionTransaction) {
        this.nonEncryptionTransaction = nonEncryptionTransaction;
    }

    public String getEncryptionType() {
        return encryptionType;
    }

    public void setEncryptionType(String encryptionType) {
        this.encryptionType = encryptionType;
    }

    public InputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(InputStream excelStream) {
        this.excelStream = excelStream;
    }

    

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public String getBlockBinProfNum() {
        return blockBinProfNum;
    }

    public void setBlockBinProfNum(String blockBinProfNum) {
        this.blockBinProfNum = blockBinProfNum;
    }

    public String getBlockBinProfName() {
        return blockBinProfName;
    }

    public void setBlockBinProfName(String blockBinProfName) {
        this.blockBinProfName = blockBinProfName;
    }

    /**
     * @return the BinPrf
     */
    public String getBinPrf() {
        return BinPrf;
    }

    /**
     * @param BinPrf the BinPrf to set
     */
    public void setBinPrf(String BinPrf) {
        this.BinPrf = BinPrf;
    }

    /**
     * @return the BinPrfMap
     */
    public Map<Integer, String> getBinPrfMap() {
        return BinPrfMap;
    }

    /**
     * @param BinPrfMap the BinPrfMap to set
     */
    public void setBinPrfMap(Map<Integer, String> BinPrfMap) {
        this.BinPrfMap = BinPrfMap;
    }

    /**
     * @return the upBinPrf
     */
    public int getUpBinPrf() {
        return upBinPrf;
    }

    /**
     * @param upBinPrf the upBinPrf to set
     */
    public void setUpBinPrf(int upBinPrf) {
        this.upBinPrf = upBinPrf;
    }

    /**
     * @return the binStatusMap
     */
    public Map<Integer, String> getBinStatusMap() {
        return binStatusMap;
    }

    /**
     * @param binStatusMap the binStatusMap to set
     */
    public void setBinStatusMap(Map<Integer, String> binStatusMap) {
        this.binStatusMap = binStatusMap;
    }

    /**
     * @return the upBinStatus
     */
    public int getUpBinStatus() {
        return upBinStatus;
    }

    /**
     * @param upBinStatus the upBinStatus to set
     */
    public void setUpBinStatus(int upBinStatus) {
        this.upBinStatus = upBinStatus;
    }

    /**
     * @return the binStatus
     */
    public String getBinStatus() {
        return binStatus;
    }

    /**
     * @param binStatus the binStatus to set
     */
    public void setBinStatus(String binStatus) {
        this.binStatus = binStatus;
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
     * @return the delete
     */
    public boolean isDelete() {
        return delete;
    }

    /**
     * @param delete the delete to set
     */
    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    /**
     * @return the upload
     */
    public boolean isUpload() {
        return upload;
    }

    /**
     * @param upload the upload to set
     */
    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    /**
     * @return the download
     */
    public boolean isDownload() {
        return download;
    }

    /**
     * @param download the download to set
     */
    public void setDownload(boolean download) {
        this.download = download;
    }

    /**
     * @return the find
     */
    public boolean isFind() {
        return find;
    }

    /**
     * @param find the find to set
     */
    public void setFind(boolean find) {
        this.find = find;
    }

    /**
     * @return the view
     */
    public boolean isView() {
        return view;
    }

    /**
     * @param view the view to set
     */
    public void setView(boolean view) {
        this.view = view;
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

}
