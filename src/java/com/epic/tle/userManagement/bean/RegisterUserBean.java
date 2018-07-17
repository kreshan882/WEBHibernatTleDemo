/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.bean;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author danushka_r
 */
public class RegisterUserBean {

    private String name;
    private String userName;
    private String email;
    private String password;
    private String confirmPassword;
    private String userType;
    private String statusType;
    private String userTypeId;
    private String date;
    private boolean isNewMember;

    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;
    private boolean reset;

    private boolean IsTempPassSent;
    private String mailMessage;

    private long fullCount;

    //table
    private List<RegisterUserBean> gridModel;
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
    private String PageCode = "";
    private String userRole = "";

    //***************Working Path*************
    private String Module = "";
    private String Section = "";
    private String Task = "";

    //****************Edit**********************
    private String upuserName;
    private String upuserType;
    private int upstatus;
    private String upName;
    private String upEmail;
    private int upuserTypeId;
    private Map<Integer, String> userTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> statusTypeMap = new HashMap<Integer, String>();
    private boolean isChecked;
    private String upNewPw;
    private String upRepetedNewPw;

    //****************delete**********************
    private String duserName;
    private String duserTypeId;
    private boolean isDeleted;
    private String dmessage;

    //***************Token************************
    private String Token;
    private String randomVal;

    private ByteArrayInputStream excelStream;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserName() {
        return userName;
    }

    public List<RegisterUserBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<RegisterUserBean> gridModel) {
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

    public String getUpuserName() {
        return upuserName;
    }

    public void setUpuserName(String upuserName) {
        this.upuserName = upuserName;
    }

    public String getUpuserType() {
        return upuserType;
    }

    public void setUpuserType(String upuserType) {
        this.upuserType = upuserType;
    }

    public int getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(int upstatus) {
        this.upstatus = upstatus;
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

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    public String getUpNewPw() {
        return upNewPw;
    }

    public void setUpNewPw(String upNewPw) {
        this.upNewPw = upNewPw;
    }

    public String getUpRepetedNewPw() {
        return upRepetedNewPw;
    }

    public void setUpRepetedNewPw(String upRepetedNewPw) {
        this.upRepetedNewPw = upRepetedNewPw;
    }

    public String getDuserName() {
        return duserName;
    }

    public void setDuserName(String duserName) {
        this.duserName = duserName;
    }

    public String getDuserTypeId() {
        return duserTypeId;
    }

    public void setDuserTypeId(String duserTypeId) {
        this.duserTypeId = duserTypeId;
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

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(String userTypeId) {
        this.userTypeId = userTypeId;
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

    /**
     * @return the statusTypeMap
     */
    public Map<Integer, String> getStatusTypeMap() {
        return statusTypeMap;
    }

    /**
     * @param statusTypeMap the statusTypeMap to set
     */
    public void setStatusTypeMap(Map<Integer, String> statusTypeMap) {
        this.statusTypeMap = statusTypeMap;
    }

    /**
     * @return the statusType
     */
    public String getStatusType() {
        return statusType;
    }

    /**
     * @param statusType the statusType to set
     */
    public void setStatusType(String statusType) {
        this.statusType = statusType;
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
     * @return the Task
     */
    public String getTask() {
        return Task;
    }

    /**
     * @param Task the Task to set
     */
    public void setTask(String Task) {
        this.Task = Task;
    }

    /**
     * @return the date
     */
    public String getDate() {
        return date;
    }

    /**
     * @param date the date to set
     */
    public void setDate(String date) {
        this.date = date;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUpEmail() {
        return upEmail;
    }

    public void setUpEmail(String upEmail) {
        this.upEmail = upEmail;
    }

    @Override
    public String toString() {
        return "RegisterUserBean{" + "name=" + name + ", userName=" + userName + ", email=" + email + ", password=" + password + ", confirmPassword=" + confirmPassword + ", userType=" + userType + ", statusType=" + statusType + ", userTypeId=" + userTypeId + ", date=" + date + ", add=" + add + ", delete=" + delete + ", view=" + view + ", update=" + update + ", fullCount=" + fullCount + ", gridModel=" + gridModel + ", rows=" + rows + ", page=" + page + ", total=" + total + ", records=" + records + ", sord=" + sord + ", sidx=" + sidx + ", searchField=" + searchField + ", searchString=" + searchString + ", searchOper=" + searchOper + ", loadonce=" + loadonce + ", searchName=" + searchName + ", PageCode=" + PageCode + ", userRole=" + userRole + ", Module=" + Module + ", Section=" + Section + ", Task=" + Task + ", upuserName=" + upuserName + ", upuserType=" + upuserType + ", upstatus=" + upstatus + ", upName=" + upName + ", upEmail=" + upEmail + ", upuserTypeId=" + upuserTypeId + ", userTypeMap=" + userTypeMap + ", statusTypeMap=" + statusTypeMap + ", isChecked=" + isChecked + ", upNewPw=" + upNewPw + ", upRepetedNewPw=" + upRepetedNewPw + ", duserName=" + duserName + ", duserTypeId=" + duserTypeId + ", isDeleted=" + isDeleted + ", dmessage=" + dmessage + ", Token=" + Token + ", excelStream=" + excelStream + '}';
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isIsTempPassSent() {
        return IsTempPassSent;
    }

    public void setIsTempPassSent(boolean IsTempPassSent) {
        this.IsTempPassSent = IsTempPassSent;
    }

    public String getMailMessage() {
        return mailMessage;
    }

    public void setMailMessage(String mailMessage) {
        this.mailMessage = mailMessage;
    }

    public boolean isIsNewMember() {
        return isNewMember;
    }

    public void setIsNewMember(boolean isNewMember) {
        this.isNewMember = isNewMember;
    }

    public String getRandomVal() {
        return randomVal;
    }

    public void setRandomVal(String randomVal) {
        this.randomVal = randomVal;
    }
    
    

}
