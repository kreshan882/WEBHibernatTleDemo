/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

import com.epic.tle.binManagement.bean.BinProfileBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ridmi_g
 */
public class SMSProfileBean {
    
    private Integer id;
    private String test;
    private String gn;
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
    private List<SMSProfileBean> gridModel;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    

  

    public Integer getBinId() {
        return binId;
    }

    public void setBinId(Integer binId) {
        this.binId = binId;
    }

    public String getcBinProfile() {
        return cBinProfile;
    }

    public void setcBinProfile(String cBinProfile) {
        this.cBinProfile = cBinProfile;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
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

    public List<SMSProfileBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<SMSProfileBean> gridModel) {
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

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
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

    public int getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(int upStatus) {
        this.upStatus = upStatus;
    }

    public String getUpName() {
        return upName;
    }

    public void setUpName(String upName) {
        this.upName = upName;
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

    public Map<Integer, String> getUpBinStatusMap() {
        return upBinStatusMap;
    }

    public void setUpBinStatusMap(Map<Integer, String> upBinStatusMap) {
        this.upBinStatusMap = upBinStatusMap;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public boolean isIsUpdated() {
        return isUpdated;
    }

    public void setIsUpdated(boolean isUpdated) {
        this.isUpdated = isUpdated;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
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

    public String getModule() {
        return Module;
    }

    public void setModule(String Module) {
        this.Module = Module;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getTest() {
        return test;
    }

    public void setTest(String test) {
        this.test = test;
    }

    public String getGn() {
        return gn;
    }

    public void setGn(String gn) {
        this.gn = gn;
    }
    
    
    
}
