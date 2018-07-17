package com.epic.tle.binManagement.bean;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author chalaka_n
 */
public class RefTerminalBean {

    
    private int id;
    private String name;
    private String minAmount;
    private String maxAmount;
    private String from;
    private String to;
    private String swipeStatus;
    private String fallBackStatus;
    private String PinPerformStatus;
    private String nfcBasedStatus;
    private String timestamp;
    private String status;
    
    private String upname;
    private String upminAmount;
    private String upmaxAmount;
    private String upfrom;
    private String upto;
    private String upswipeStatus;
    private String upfallBackStatus;
    private String upnfcBasedStatus;
    private String upPinPerformStatus;
    private String pstatus;
    
    

    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;

    private long fullCount;

    //table
    private List<RefTerminalBean> gridModel;
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

    //****************Search**********************
    private String searchName = "";

    private String delsuccess;
    private String message;
    private boolean success;

    //****************Edit**********************
    private int upProfileID;
    private int upStatus;
    private String upName;
    private int upuserTypeId;
    private Map<Integer, String> userTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> upStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> swipeTarMap = new HashMap<Integer, String>();
    private Map<Integer, String> fallBackMap = new HashMap<Integer, String>();
    private Map<Integer, String> nfcBasedMap = new HashMap<Integer, String>();
    private Map<Integer, String> PinPerformkMap = new HashMap<Integer, String>();
    private Map<Integer, String> fromMap = new HashMap<Integer, String>(){{
        for (int i = 0; i < 24 ; i++) {
            put(i,Integer.toString(i));
        }
    }};
    private Map<Integer, String> toMap = new HashMap<Integer, String>(){{
        for (int i = 0; i < 24 ; i++) {
            put(i,Integer.toString(i));
        }
    }};
    
    private boolean isChecked;
    private boolean isUpdated;
    private String msg;
    private String getPages;
    private String upRepetedNewPw;

    //****************delete**********************
    private String DbinId;
    private boolean isDeleted;
    private String dmessage;

    private String PagePath = "";
    private String PageCode = "";

    //***************Working Path*************
    private String Module = "";
    private String Section = "";

    //***************Token************************
    private String Token;

    
    /**
     * @return the add
     */
    public boolean isAdd() {
        return add;
    }

    /**
     * @param add the add to set
     */
    public void setAdd(boolean add) {
        this.add = add;
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

    /**
     * @return the update
     */
    public boolean isUpdate() {
        return update;
    }

    /**
     * @param update the update to set
     */
    public void setUpdate(boolean update) {
        this.update = update;
    }

    /**
     * @return the gridModel
     */
    public List<RefTerminalBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<RefTerminalBean> gridModel) {
        this.gridModel = gridModel;
    }

    /**
     * @return the rows
     */
    public Integer getRows() {
        return rows;
    }

    /**
     * @param rows the rows to set
     */
    public void setRows(Integer rows) {
        this.rows = rows;
    }

    /**
     * @return the page
     */
    public Integer getPage() {
        return page;
    }

    /**
     * @param page the page to set
     */
    public void setPage(Integer page) {
        this.page = page;
    }

    /**
     * @return the total
     */
    public Integer getTotal() {
        return total;
    }

    /**
     * @param total the total to set
     */
    public void setTotal(Integer total) {
        this.total = total;
    }

    /**
     * @return the records
     */
    public Long getRecords() {
        return records;
    }

    /**
     * @param records the records to set
     */
    public void setRecords(Long records) {
        this.records = records;
    }

    /**
     * @return the sord
     */
    public String getSord() {
        return sord;
    }

    /**
     * @param sord the sord to set
     */
    public void setSord(String sord) {
        this.sord = sord;
    }

    /**
     * @return the sidx
     */
    public String getSidx() {
        return sidx;
    }

    /**
     * @param sidx the sidx to set
     */
    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    /**
     * @return the searchField
     */
    public String getSearchField() {
        return searchField;
    }

    /**
     * @param searchField the searchField to set
     */
    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    /**
     * @return the searchString
     */
    public String getSearchString() {
        return searchString;
    }

    /**
     * @param searchString the searchString to set
     */
    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    /**
     * @return the searchOper
     */
    public String getSearchOper() {
        return searchOper;
    }

    /**
     * @param searchOper the searchOper to set
     */
    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    /**
     * @return the loadonce
     */
    public boolean isLoadonce() {
        return loadonce;
    }

    /**
     * @param loadonce the loadonce to set
     */
    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    /**
     * @return the searchName
     */
    public String getSearchName() {
        return searchName;
    }

    /**
     * @param searchName the searchName to set
     */
    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    /**
     * @return the fullCount
     */
    public long getFullCount() {
        return fullCount;
    }

    /**
     * @param fullCount the fullCount to set
     */
    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    /**
     * @return the delsuccess
     */
    public String getDelsuccess() {
        return delsuccess;
    }

    /**
     * @param delsuccess the delsuccess to set
     */
    public void setDelsuccess(String delsuccess) {
        this.delsuccess = delsuccess;
    }

    /**
     * @return the message
     */
    public String getMessage() {
        return message;
    }

    /**
     * @param message the message to set
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * @return the success
     */
    public boolean isSuccess() {
        return success;
    }

    /**
     * @param success the success to set
     */
    public void setSuccess(boolean success) {
        this.success = success;
    }

    /**
     * @return the upName
     */
    public String getUpName() {
        return upName;
    }

    /**
     * @param upName the upName to set
     */
    public void setUpName(String upName) {
        this.upName = upName;
    }

    /**
     * @return the upuserTypeId
     */
    public int getUpuserTypeId() {
        return upuserTypeId;
    }

    /**
     * @param upuserTypeId the upuserTypeId to set
     */
    public void setUpuserTypeId(int upuserTypeId) {
        this.upuserTypeId = upuserTypeId;
    }

    /**
     * @return the userTypeMap
     */
    public Map<Integer, String> getUserTypeMap() {
        return userTypeMap;
    }

    /**
     * @param userTypeMap the userTypeMap to set
     */
    public void setUserTypeMap(Map<Integer, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }

    /**
     * @return the isChecked
     */
    public boolean isIsChecked() {
        return isChecked;
    }

    /**
     * @param isChecked the isChecked to set
     */
    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    /**
     * @return the upNewPw
     */
    public String getPages() {
        return getPages;
    }

    /**
     * @param upNewPw the upNewPw to set
     */
    public void setPages(String getPages) {
        this.getPages = getPages;
    }

    /**
     * @return the upRepetedNewPw
     */
    public String getUpRepetedNewPw() {
        return upRepetedNewPw;
    }

    /**
     * @param upRepetedNewPw the upRepetedNewPw to set
     */
    public void setUpRepetedNewPw(String upRepetedNewPw) {
        this.upRepetedNewPw = upRepetedNewPw;
    }

    /**
     * @return the upProfileID
     */
    public int getUpProfileID() {
        return upProfileID;
    }

    /**
     * @param upProfileID the upProfileID to set
     */
    public void setUpProfileID(int upProfileID) {
        this.upProfileID = upProfileID;
    }
   

    /**
     * @return the isDeleted
     */
    public boolean isIsDeleted() {
        return isDeleted;
    }

    /**
     * @param isDeleted the isDeleted to set
     */
    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    /**
     * @return the dmessage
     */
    public String getDmessage() {
        return dmessage;
    }

    /**
     * @param dmessage the dmessage to set
     */
    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
    }

    /**
     * @return the DbinId
     */
    public String getDbinId() {
        return DbinId;
    }

    /**
     * @param DbinId the DbinId to set
     */
    public void setDbinId(String DbinId) {
        this.DbinId = DbinId;
    }

    /**
     * @return the upStatus
     */
    public int getUpStatus() {
        return upStatus;
    }

    /**
     * @param upStatus the upStatus to set
     */
    public void setUpStatus(int upStatus) {
        this.upStatus = upStatus;
    }

    

    /**
     * @return the isUpdated
     */
    public boolean isIsUpdated() {
        return isUpdated;
    }

    /**
     * @param isUpdated the isUpdated to set
     */
    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    /**
     * @return the msg
     */
    public String getMsg() {
        return msg;
    }

    /**
     * @param msg the msg to set
     */
    public void setMsg(String msg) {
        this.msg = msg;
    }

    
    /**
     * @return the PagePath
     */
    public String getPagePath() {
        return PagePath;
    }

    /**
     * @param PagePath the PagePath to set
     */
    public void setPagePath(String PagePath) {
        this.PagePath = PagePath;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(String minAmount) {
        this.minAmount = minAmount;
    }

    public String getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(String maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getSwipeStatus() {
        return swipeStatus;
    }

    public void setSwipeStatus(String swipeStatus) {
        this.swipeStatus = swipeStatus;
    }

    public String getFallBackStatus() {
        return fallBackStatus;
    }

    public void setFallBackStatus(String fallBackStatus) {
        this.fallBackStatus = fallBackStatus;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUpname() {
        return upname;
    }

    public void setUpname(String upname) {
        this.upname = upname;
    }

    public String getUpminAmount() {
        return upminAmount;
    }

    public void setUpminAmount(String upminAmount) {
        this.upminAmount = upminAmount;
    }

    public String getUpmaxAmount() {
        return upmaxAmount;
    }

    public void setUpmaxAmount(String upmaxAmount) {
        this.upmaxAmount = upmaxAmount;
    }

    public String getUpfrom() {
        return upfrom;
    }

    public void setUpfrom(String upfrom) {
        this.upfrom = upfrom;
    }

    public String getUpto() {
        return upto;
    }

    public void setUpto(String upto) {
        this.upto = upto;
    }

    public String getUpswipeStatus() {
        return upswipeStatus;
    }

    public void setUpswipeStatus(String upswipeStatus) {
        this.upswipeStatus = upswipeStatus;
    }

    public String getUpfallBackStatus() {
        return upfallBackStatus;
    }

    public void setUpfallBackStatus(String upfallBackStatus) {
        this.upfallBackStatus = upfallBackStatus;
    }

    public String getPstatus() {
        return pstatus;
    }

    public void setPstatus(String pstatus) {
        this.pstatus = pstatus;
    }

    public Map<Integer, String> getUpStatusMap() {
        return upStatusMap;
    }

    public void setUpStatusMap(Map<Integer, String> upStatusMap) {
        this.upStatusMap = upStatusMap;
    }

    public Map<Integer, String> getSwipeTarMap() {
        return swipeTarMap;
    }

    public void setSwipeTarMap(Map<Integer, String> swipeTarMap) {
        this.swipeTarMap = swipeTarMap;
    }

    public Map<Integer, String> getFallBackMap() {
        return fallBackMap;
    }

    public void setFallBackMap(Map<Integer, String> fallBackMap) {
        this.fallBackMap = fallBackMap;
    }

    public Map<Integer, String> getFromMap() {
        return fromMap;
    }

    public void setFromMap(Map<Integer, String> fromMap) {
        this.fromMap = fromMap;
    }

    public Map<Integer, String> getToMap() {
        return toMap;
    }

    public void setToMap(Map<Integer, String> toMap) {
        this.toMap = toMap;
    }

    public String getGetPages() {
        return getPages;
    }

    public void setGetPages(String getPages) {
        this.getPages = getPages;
    }

    /**
     * @return the nfcBasedMap
     */
    public Map<Integer, String> getNfcBasedMap() {
        return nfcBasedMap;
    }

    /**
     * @param nfcBasedMap the nfcBasedMap to set
     */
    public void setNfcBasedMap(Map<Integer, String> nfcBasedMap) {
        this.nfcBasedMap = nfcBasedMap;
    }

    /**
     * @return the PinPerformkMap
     */
    public Map<Integer, String> getPinPerformkMap() {
        return PinPerformkMap;
    }

    /**
     * @param PinPerformkMap the PinPerformkMap to set
     */
    public void setPinPerformkMap(Map<Integer, String> PinPerformkMap) {
        this.PinPerformkMap = PinPerformkMap;
    }

    /**
     * @return the PinPerformStatus
     */
    public String getPinPerformStatus() {
        return PinPerformStatus;
    }

    /**
     * @param PinPerformStatus the PinPerformStatus to set
     */
    public void setPinPerformStatus(String PinPerformStatus) {
        this.PinPerformStatus = PinPerformStatus;
    }

    /**
     * @return the nfcBasedStatus
     */
    public String getNfcBasedStatus() {
        return nfcBasedStatus;
    }

    /**
     * @param nfcBasedStatus the nfcBasedStatus to set
     */
    public void setNfcBasedStatus(String nfcBasedStatus) {
        this.nfcBasedStatus = nfcBasedStatus;
    }

    /**
     * @return the upnfcBasedStatus
     */
    public String getUpnfcBasedStatus() {
        return upnfcBasedStatus;
    }

    /**
     * @param upnfcBasedStatus the upnfcBasedStatus to set
     */
    public void setUpnfcBasedStatus(String upnfcBasedStatus) {
        this.upnfcBasedStatus = upnfcBasedStatus;
    }

    /**
     * @return the upPinPerformStatus
     */
    public String getUpPinPerformStatus() {
        return upPinPerformStatus;
    }

    /**
     * @param upPinPerformStatus the upPinPerformStatus to set
     */
    public void setUpPinPerformStatus(String upPinPerformStatus) {
        this.upPinPerformStatus = upPinPerformStatus;
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
