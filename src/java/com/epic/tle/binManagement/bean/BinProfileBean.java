package com.epic.tle.binManagement.bean;

import com.epic.tle.userManagement.bean.RegisterUserBean;
import java.util.Date;
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
 * @author thilina_t
 */
public class BinProfileBean {

    private Integer id;
    private String binProfileDes;
    private Integer binId;
    private String cBinProfile;
    private String datetime;
    private int status;

    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;

    private long fullCount;

    //table
    private List<BinProfileBean> gridModel;
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
    private Map<Integer, String> upBinStatusMap = new HashMap<Integer, String>();
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
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return the description
     */
    public String getBinProfileDes() {
        return binProfileDes;
    }

    /**
     * @param description the description to set
     */
    public void setBinProfileDes(String BinProfileDes) {
        this.binProfileDes = BinProfileDes;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

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
    public List<BinProfileBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<BinProfileBean> gridModel) {
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
     * @return the upBinStatusMap
     */
    public Map<Integer, String> getUpBinStatusMap() {
        return upBinStatusMap;
    }

    /**
     * @param upBinStatusMap the upBinStatusMap to set
     */
    public void setUpBinStatusMap(Map<Integer, String> upBinStatusMap) {
        this.upBinStatusMap = upBinStatusMap;
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
     * @return the binId
     */
    public Integer getBinId() {
        return binId;
    }

    /**
     * @param binId the binId to set
     */
    public void setBinId(Integer binId) {
        this.binId = binId;
    }

    /**
     * @return the cBinProfile
     */
    public String getcBinProfile() {
        return cBinProfile;
    }

    /**
     * @param cBinProfile the cBinProfile to set
     */
    public void setcBinProfile(String cBinProfile) {
        this.cBinProfile = cBinProfile;
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
     * @return the status
     */
    public int getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(int status) {
        this.status = status;
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
