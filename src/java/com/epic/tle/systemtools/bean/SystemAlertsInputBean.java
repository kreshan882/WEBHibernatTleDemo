/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.bean;

import com.epic.tle.log.bean.ViewLogsDataBean;
import java.io.ByteArrayInputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dimuthu_h
 */
public class SystemAlertsInputBean {

    private List<SystemAlertsDataBean> gridModel;
    private Map<Integer,String> node=new HashMap<Integer, String>();;
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
    
    private Map<Integer, String> AlertMap = new HashMap<Integer, String>();
    private Map<Integer, String> RiskMap = new HashMap<Integer, String>();
    private Map<Integer,String> nodes = new HashMap<Integer, String>();
    ///////////////////////////////////////////////
    /// search values
    //////////////////////////////////////////////
    private String fromdate;
    private String todate;
    private String tid;
    private String sid;
    private String selectNode;
    //**************Export XSL***************
    private ByteArrayInputStream excelStream;
    private ByteArrayInputStream zipStream;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean view;
    private String PageCode;

    //***************Working Path*************
    private String Module;
    private String Section;

    public Map<Integer, String> getNode() {
        return node;
    }

    public void setNode(Map<Integer, String> node) {
        this.node = node;
    }

    
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

    public String getFromdate() {
        return fromdate;
    }

    public ByteArrayInputStream getZipStream() {
        return zipStream;
    }

    public void setZipStream(ByteArrayInputStream zipStream) {
        this.zipStream = zipStream;
    }

    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public List<SystemAlertsDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<SystemAlertsDataBean> gridModel) {
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
     * @return the sid
     */
    public String getSid() {
        return sid;
    }

    /**
     * @param sid the sid to set
     */
    public void setSid(String sid) {
        this.sid = sid;
    }

    /**
     * @return the AlertMap
     */
    public Map<Integer, String> getAlertMap() {
        return AlertMap;
    }

    /**
     * @param AlertMap the AlertMap to set
     */
    public void setAlertMap(Map<Integer, String> AlertMap) {
        this.AlertMap = AlertMap;
    }

    /**
     * @return the RiskMap
     */
    public Map<Integer, String> getRiskMap() {
        return RiskMap;
    }

    /**
     * @param RiskMap the RiskMap to set
     */
    public void setRiskMap(Map<Integer, String> RiskMap) {
        this.RiskMap = RiskMap;
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

}
