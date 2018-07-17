/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.bean;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author thilina_t
 */
public class OperationManageBean {

    private int listOpr;
    private boolean success;
    private boolean send;
    private boolean add;
    private boolean delete;
    private boolean view;
    private boolean update;
    private boolean search;
    private int regStatus;
    private String node;
    
    private String PageCode;    
    
    private Map<Integer, String> operationList = new HashMap<Integer, String>();

    //table
    private List<OperationManageBean> gridModel;
    private List<OperationManageBean> reportdatalist = new ArrayList<OperationManageBean>();
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
    private long fullCount;
    
    private String operaiton;
    private String username;
    private String ip;
    private int stauts;
    private String message;
    private String dateTime;
    private String statusStr;
    private String oprMessage;
    private String colorCode;
    
    private String fromdate;
    private String todate;
    
    
//    REPORT    
    private String OPERATION;
    private String IP;
    private String MESSAGE;
    private String STATUS;
    private String DATETIME;
    private String USERNAME;
    
    //***************Working Path*************
    private String Module;
    private String Section;
    private String Task;
    
    //***************Token************************
    private String Token;

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public String getPageCode() {
        return PageCode;
    }

    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    
    public String getUSERNAME() {
        return USERNAME;
    }

    public int getRegStatus() {
        return regStatus;
    }

    public void setRegStatus(int regStatus) {
        this.regStatus = regStatus;
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

    public String getTask() {
        return Task;
    }

    public void setTask(String Task) {
        this.Task = Task;
    }
    
    

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    
    public List<OperationManageBean> getReportdatalist() {
        return reportdatalist;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public void setReportdatalist(List<OperationManageBean> reportdatalist) {
        this.reportdatalist = reportdatalist;
    }
    
    

    public String getOPERATION() {
        return OPERATION;
    }

    public void setOPERATION(String OPERATION) {
        this.OPERATION = OPERATION;
    }

    public String getIP() {
        return IP;
    }

    public void setIP(String IP) {
        this.IP = IP;
    }

    public String getMESSAGE() {
        return MESSAGE;
    }

    public void setMESSAGE(String MESSAGE) {
        this.MESSAGE = MESSAGE;
    }

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public String getDATETIME() {
        return DATETIME;
    }

    public void setDATETIME(String DATETIME) {
        this.DATETIME = DATETIME;
    }
    

    
    public String getFromdate() {
        return fromdate;
    }

    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    public String getTodate() {
        return todate;
    }

    public void setTodate(String todate) {
        this.todate = todate;
    }

    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }
       
    
    public String getOprMessage() {
        return oprMessage;
    }

    public void setOprMessage(String oprMessage) {
        this.oprMessage = oprMessage;
    }
    
    public String getStatusStr() {
        return statusStr;
    }

    public void setStatusStr(String statusStr) {
        this.statusStr = statusStr;
    }
    
    
    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }
    
    public String getOperaiton() {
        return operaiton;
    }

    public void setOperaiton(String operaiton) {
        this.operaiton = operaiton;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getStauts() {
        return stauts;
    }

    public void setStauts(int stauts) {
        this.stauts = stauts;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
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
     * @return the operationList
     */
    public Map<Integer, String> getOperationList() {
        return operationList;
    }

    /**
     * @param operationList the operationList to set
     */
    public void setOperationList(Map<Integer, String> operationList) {
        this.operationList = operationList;
    }

    /**
     * @return the gridModel
     */
    public List<OperationManageBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<OperationManageBean> gridModel) {
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
     * @return the listOpr
     */
    public int getListOpr() {
        return listOpr;
    }

    /**
     * @param listOpr the listOpr to set
     */
    public void setListOpr(int listOpr) {
        this.listOpr = listOpr;
    }

    /**
     * @return the colorCode
     */
    public String getColorCode() {
        return colorCode;
    }

    /**
     * @param colorCode the colorCode to set
     */
    public void setColorCode(String colorCode) {
        this.colorCode = colorCode;
    }

    @Override
    public String toString() {
        return "OperationManageBean{" + "listOpr=" + listOpr + ", success=" + success + ", send=" + send + ", add=" + add + ", delete=" + delete + ", view=" + view + ", update=" + update + ", search=" + search + ", regStatus=" + regStatus + ", node=" + node + ", PageCode=" + PageCode + ", operationList=" + operationList + ", gridModel=" + gridModel + ", reportdatalist=" + reportdatalist + ", rows=" + rows + ", page=" + page + ", total=" + total + ", records=" + records + ", sord=" + sord + ", sidx=" + sidx + ", searchField=" + searchField + ", searchString=" + searchString + ", searchOper=" + searchOper + ", loadonce=" + loadonce + ", fullCount=" + fullCount + ", operaiton=" + operaiton + ", username=" + username + ", ip=" + ip + ", stauts=" + stauts + ", message=" + message + ", dateTime=" + dateTime + ", statusStr=" + statusStr + ", oprMessage=" + oprMessage + ", colorCode=" + colorCode + ", fromdate=" + fromdate + ", todate=" + todate + ", OPERATION=" + OPERATION + ", IP=" + IP + ", MESSAGE=" + MESSAGE + ", STATUS=" + STATUS + ", DATETIME=" + DATETIME + ", USERNAME=" + USERNAME + ", Module=" + Module + ", Section=" + Section + ", Task=" + Task + '}';
    }

    public boolean isSend() {
        return send;
    }
    public void setSend(boolean send) {
        this.send = send;
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
