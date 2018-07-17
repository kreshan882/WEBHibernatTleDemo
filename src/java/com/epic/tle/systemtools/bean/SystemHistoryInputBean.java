/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.bean;

import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dimuthu_h
 */
public class SystemHistoryInputBean {
    private List<SystemHistoryDataBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    
    private boolean search;

    

    ///////////////////////////////////////////////
    /// search values
    //////////////////////////////////////////////
    
    
    private Map<String,String> operationTypeMap=new HashMap<String, String>();
    private Map<String,String> moduleMap = new HashMap<String, String>();
    private Map<String,String> taskMap = new HashMap<String, String>();
    private Map<String,String> sectionMap = new HashMap<String, String>();
    private Map<Integer,String> nodes = new HashMap<Integer, String>();
    private String operationType;
    private String fromdate;
    private String todate;
    private String selectNode;
    private String usrName;
    private String serial;
    private String selectMod;
    private String searchUserName;
    private String searchTask;
    
      //**************Export XSL***************
    private ByteArrayInputStream excelStream;
    
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean view;
    private String PageCode="";
    
         //***************Working Path*************
    private String Module="";
    private String Section="";
    
    
    private String Token;
    
    private HashMap<String,String[]> oldValueMap;
    private HashMap<String,String[]> newValueMap;
    
    public boolean isVadd() {
        return vadd;
    }

    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    public boolean isVupdate() {
        return vupdate;
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
    
    
    public List<SystemHistoryDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<SystemHistoryDataBean> gridModel) {
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
    
    public boolean isSearch() {
        return search;
    }

    public void setSearch(boolean search) {
        this.search = search;
    }

    public Map<String, String> getOperationTypeMap() {
        return operationTypeMap;
    }

    public void setOperationTypeMap(Map<String, String> operationTypeMap) {
        this.operationTypeMap = operationTypeMap;
    }

    public Map<String, String> getModuleMap() {
        return moduleMap;
    }

    public void setModuleMap(Map<String, String> moduleMap) {
        this.moduleMap = moduleMap;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
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

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
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
     * @return the usrName
     */
    public String getUsrName() {
        return usrName;
    }

    /**
     * @param usrName the usrName to set
     */
    public void setUsrName(String usrName) {
        this.usrName = usrName;
    }

    /**
     * @return the serial
     */
    public String getSerial() {
        return serial;
    }

    /**
     * @param serial the serial to set
     */
    public void setSerial(String serial) {
        this.serial = serial;
    }

    /**
     * @return the vdownload
     */
    public boolean isVdownload() {
        return vdownload;
    }

    /**
     * @param vdownload the vdownload to set
     */
    public void setVdownload(boolean vdownload) {
        this.vdownload = vdownload;
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
     * @return the nodes
     */
    public Map<Integer,String> getNodes() {
        return nodes;
    }

    /**
     * @param nodes the nodes to set
     */
    public void setNodes(Map<Integer,String> nodes) {
        this.nodes = nodes;
    }

    /**
     * @return the selectNode
     */
    public String getSelectNode() {
        return selectNode;
    }

    /**
     * @param selectNode the selectNode to set
     */
    public void setSelectNode(String selectNode) {
        this.selectNode = selectNode;
    }

    public Map<String, String> getSectionMap() {
        return sectionMap;
    }

    public void setSectionMap(Map<String, String> sectionMap) {
        this.sectionMap = sectionMap;
    }  

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public HashMap<String, String[]> getOldValueMap() {
        return oldValueMap;
    }

    public void setOldValueMap(HashMap<String, String[]> oldValueMap) {
        this.oldValueMap = oldValueMap;
    }

    public HashMap<String, String[]> getNewValueMap() {
        return newValueMap;
    }

    public void setNewValueMap(HashMap<String, String[]> newValueMap) {
        this.newValueMap = newValueMap;
    }

    public String getSelectMod() {
        return selectMod;
    }

    public void setSelectMod(String selectMod) {
        this.selectMod = selectMod;
    }

    public String getSearchUserName() {
        return searchUserName;
    }

    public void setSearchUserName(String searchUserName) {
        this.searchUserName = searchUserName;
    }

    public Map<String, String> getTaskMap() {
        return taskMap;
    }

    public void setTaskMap(Map<String, String> taskMap) {
        this.taskMap = taskMap;
    }

    public String getSearchTask() {
        return searchTask;
    }

    public void setSearchTask(String searchTask) {
        this.searchTask = searchTask;
    }

    @Override
    public String toString() {
        return "SystemHistoryInputBean{" + "gridModel=" + gridModel + ", rows=" + rows + ", page=" + page + ", total=" + total + ", records=" + records + ", sord=" + sord + ", sidx=" + sidx + ", searchField=" + searchField + ", searchString=" + searchString + ", searchOper=" + searchOper + ", search=" + search + ", operationTypeMap=" + operationTypeMap + ", moduleMap=" + moduleMap + ", taskMap=" + taskMap + ", sectionMap=" + sectionMap + ", nodes=" + nodes + ", operationType=" + operationType + ", fromdate=" + fromdate + ", todate=" + todate + ", selectNode=" + selectNode + ", usrName=" + usrName + ", serial=" + serial + ", selectMod=" + selectMod + ", searchUserName=" + searchUserName + ", searchTask=" + searchTask + ", excelStream=" + excelStream + ", vadd=" + vadd + ", vupdate=" + vupdate + ", vdelete=" + vdelete + ", vdownload=" + vdownload + ", view=" + view + ", PageCode=" + PageCode + ", Module=" + Module + ", Section=" + Section + ", Token=" + Token + ", oldValueMap=" + oldValueMap + ", newValueMap=" + newValueMap + '}';
    }
   
}
