/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ridmi_g
 */
public class FilterMessageBean {
    private int profileid;
    private Date date;
    private int id;
    
    private String message;
    
  
    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;
    private long fullCount;
    
    //table
    private List<FilterMessageBean> gridModel;
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
//    private String message;
    private boolean success;

    //****************Edit**********************
    private int upProfileID;
    private String upName;
    private String upBinName;
    private int upuserTypeId;
    private Map<Integer, String> userTypeMap = new HashMap<Integer, String>();
    private boolean isChecked;
    private String getPages;
    private String upRepetedNewPw;

    //****************delete**********************
    private String DbinId;
    private boolean isDeleted;
    private String dmessage;

    private String PagePath="";
    private String PageCode="";
    
    //***************Token************************
    private String Token;

    public int getProfileid() {
        return profileid;
    }

    public void setProfileid(int profileid) {
        this.profileid = profileid;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

   
    
    
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isDelete() {
        return delete;
    }

    public void setDelete(boolean delete) {
        this.delete = delete;
    }

    public boolean isView() {
        return view;
    }

    public void setView(boolean view) {
        this.view = view;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public List<FilterMessageBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<FilterMessageBean> gridModel) {
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

    public String getSearchName() {
        return searchName;
    }

    public void setSearchName(String searchName) {
        this.searchName = searchName;
    }

    public String getDelsuccess() {
        return delsuccess;
    }

    public void setDelsuccess(String delsuccess) {
        this.delsuccess = delsuccess;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getUpProfileID() {
        return upProfileID;
    }

    public void setUpProfileID(int upProfileID) {
        this.upProfileID = upProfileID;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
    }

    public String getUpBinName() {
        return upBinName;
    }

    public void setUpBinName(String upBinName) {
        this.upBinName = upBinName;
    }

    public int getUpuserTypeId() {
        return upuserTypeId;
    }

    public void setUpuserTypeId(int upuserTypeId) {
        this.upuserTypeId = upuserTypeId;
    }

    public Map<Integer, String> getUserTypeMap() {
        return userTypeMap;
    }

    public void setUserTypeMap(Map<Integer, String> userTypeMap) {
        this.userTypeMap = userTypeMap;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getGetPages() {
        return getPages;
    }

    public void setGetPages(String getPages) {
        this.getPages = getPages;
    }

    public String getUpRepetedNewPw() {
        return upRepetedNewPw;
    }

    public void setUpRepetedNewPw(String upRepetedNewPw) {
        this.upRepetedNewPw = upRepetedNewPw;
    }

    public String getDbinId() {
        return DbinId;
    }

    public void setDbinId(String DbinId) {
        this.DbinId = DbinId;
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

    public String getPagePath() {
        return PagePath;
    }

    public void setPagePath(String PagePath) {
        this.PagePath = PagePath;
    }

    public String getPageCode() {
        return PageCode;
    }

    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
    
    
    
    
}
