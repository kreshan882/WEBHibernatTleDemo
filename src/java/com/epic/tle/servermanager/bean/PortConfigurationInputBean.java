/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author dimuthu_h
 */
public class PortConfigurationInputBean {

    private List<PortConfigurationDataBean> gridModel;
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

    ////////////////////////////
    ///load map
    /////////////////////////////
    private Map<String, String> datarateMap = new TreeMap<String, String>();
    private Map<String, String> databitMap = new TreeMap<String, String>();
    private Map<String, String> parityMap = new TreeMap<String, String>();
    private Map<String, String> stopbitMap = new TreeMap<String, String>();
    private Map<String, String> portMap = new TreeMap<String, String>();
    private Map<String, String> statusMap = new TreeMap<String, String>();

//***************Update Value******************
    private String sid;
    private String datarate;
    private String databit;
    private String parity;
    private String stopbit;
    private String port;
    private String status;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean view;
    
    private String PageCode="";
    
         //***************Working Path*************
    private String Module="";
    private String Section="";
    
    //***************Token************************
    private String Token;

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

    
    public List<PortConfigurationDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<PortConfigurationDataBean> gridModel) {
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

    public Map<String, String> getDatarateMap() {
        return datarateMap;
    }

    public void setDatarateMap(Map<String, String> datarateMap) {
        this.datarateMap = datarateMap;
    }

    public Map<String, String> getDatabitMap() {
        return databitMap;
    }

    public void setDatabitMap(Map<String, String> databitMap) {
        this.databitMap = databitMap;
    }

    public Map<String, String> getParityMap() {
        return parityMap;
    }

    public void setParityMap(Map<String, String> parityMap) {
        this.parityMap = parityMap;
    }

    public Map<String, String> getStopbitMap() {
        return stopbitMap;
    }

    public void setStopbitMap(Map<String, String> stopbitMap) {
        this.stopbitMap = stopbitMap;
    }

    public Map<String, String> getPortMap() {
        return portMap;
    }

    public void setPortMap(Map<String, String> portMap) {
        this.portMap = portMap;
    }

    public Map<String, String> getStatusMap() {
        return statusMap;
    }

    public void setStatusMap(Map<String, String> statusMap) {
        this.statusMap = statusMap;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDatarate() {
        return datarate;
    }

    public void setDatarate(String datarate) {
        this.datarate = datarate;
    }

    public String getDatabit() {
        return databit;
    }

    public void setDatabit(String databit) {
        this.databit = databit;
    }

    public String getParity() {
        return parity;
    }

    public void setParity(String parity) {
        this.parity = parity;
    }

    public String getStopbit() {
        return stopbit;
    }

    public void setStopbit(String stopbit) {
        this.stopbit = stopbit;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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
