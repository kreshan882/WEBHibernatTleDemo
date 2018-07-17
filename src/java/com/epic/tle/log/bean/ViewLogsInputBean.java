/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.log.bean;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author nipun_t
 */
public class ViewLogsInputBean {
    /*------------------------list data table  ------------------------------*/

    private List<ViewLogsDataBean> gridModel;
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
    
    //************download***********************
    private InputStream inputStream;
    private String fileName;
    private long contentLength;
    private String date;

    private String filePath;
    private String logFileSelection;
    
    //************search***********************
    private Map<String, String> logTypesMap = new HashMap<String, String>();
    private String logTypes;
    
    //****************delete**********************  
    private boolean isDeleted;
    private String dmessage;
    
    
    private boolean download;
    private boolean delete;
    private boolean view;
    private boolean update;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    
    public boolean isDownload() {
        return download;
    }

    public void setDownload(boolean download) {
        this.download = download;
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

    public String getLogTypes() {
        return logTypes;
    }

    public void setLogTypes(String logTypes) {
        this.logTypes = logTypes;
    }
    


 

    public Map<String, String> getLogTypesMap() {
        return logTypesMap;
    }

    public void setLogTypesMap(Map<String, String> logTypesMap) {
        this.logTypesMap = logTypesMap;
    }   
   
    public List<ViewLogsDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ViewLogsDataBean> gridModel) {
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

    public InputStream getInputStream() {
        return inputStream;
    }

    public void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
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

}
