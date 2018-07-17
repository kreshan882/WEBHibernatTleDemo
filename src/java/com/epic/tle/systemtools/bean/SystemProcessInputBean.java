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
public class SystemProcessInputBean {

    private List<SystemProcessDataBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String from;
    private String to;
    private String TransTID;

    private boolean search;

    ///////////////////////////////////////////////
    /// search values
    //////////////////////////////////////////////
    private Map<Integer, String> fromTime = new HashMap<Integer, String>();
    private Map<Integer, String> toTime = new HashMap<Integer, String>();
    private Map<Integer,String> nodes = new HashMap<Integer, String>();

    private String operationType;
    private String fromdate;
    private String todate;
    private String usrName;
    private String serial;
    private String selectNode;
    //**************Export XSL***************
    private ByteArrayInputStream excelStream;
    private ByteArrayInputStream zipStream;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean view;
    private String PageCode = "";

    //***************Working Path*************
    private String Module = "";
    private String Section = "";

    /**
     * @return the gridModel
     */
    public List<SystemProcessDataBean> getGridModel() {
        return gridModel;
    }

    /**
     * @param gridModel the gridModel to set
     */
    public void setGridModel(List<SystemProcessDataBean> gridModel) {
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
     * @return the search
     */
    public boolean isSearch() {
        return search;
    }

    /**
     * @param search the search to set
     */
    public void setSearch(boolean search) {
        this.search = search;
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
     * @return the excelStream
     */
    public ByteArrayInputStream getExcelStream() {
        return excelStream;
    }

    /**
     * @param excelStream the excelStream to set
     */
    public void setExcelStream(ByteArrayInputStream excelStream) {
        this.excelStream = excelStream;
    }

    /**
     * @return the vadd
     */
    public boolean isVadd() {
        return vadd;
    }

    /**
     * @param vadd the vadd to set
     */
    public void setVadd(boolean vadd) {
        this.vadd = vadd;
    }

    /**
     * @return the vupdate
     */
    public boolean isVupdate() {
        return vupdate;
    }

    /**
     * @param vupdate the vupdate to set
     */
    public void setVupdate(boolean vupdate) {
        this.vupdate = vupdate;
    }

    /**
     * @return the vdelete
     */
    public boolean isVdelete() {
        return vdelete;
    }

    /**
     * @param vdelete the vdelete to set
     */
    public void setVdelete(boolean vdelete) {
        this.vdelete = vdelete;
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
     * @return the operationType
     */
    public String getOperationType() {
        return operationType;
    }

    /**
     * @param operationType the operationType to set
     */
    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    /**
     * @return the fromdate
     */
    public String getFromdate() {
        return fromdate;
    }

    /**
     * @param fromdate the fromdate to set
     */
    public void setFromdate(String fromdate) {
        this.fromdate = fromdate;
    }

    /**
     * @return the todate
     */
    public String getTodate() {
        return todate;
    }

    /**
     * @param todate the todate to set
     */
    public void setTodate(String todate) {
        this.todate = todate;
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
     * @return the from
     */
    public String getFrom() {
        return from;
    }

    /**
     * @param from the from to set
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * @return the to
     */
    public String getTo() {
        return to;
    }

    /**
     * @param to the to to set
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * @return the fromTime
     */
    public Map<Integer, String> getFromTime() {
        return fromTime;
    }

    /**
     * @param fromTime the fromTime to set
     */
    public void setFromTime(Map<Integer, String> fromTime) {
        this.fromTime = fromTime;
    }

    /**
     * @return the toTime
     */
    public Map<Integer, String> getToTime() {
        return toTime;
    }

    /**
     * @param toTime the toTime to set
     */
    public void setToTime(Map<Integer, String> toTime) {
        this.toTime = toTime;
    }

    /**
     * @return the zipStream
     */
    public ByteArrayInputStream getZipStream() {
        return zipStream;
    }

    /**
     * @param zipStream the zipStream to set
     */
    public void setZipStream(ByteArrayInputStream zipStream) {
        this.zipStream = zipStream;
    }

    /**
     * @return the TransTID
     */
    public String getTransTID() {
        return TransTID;
    }

    /**
     * @param TransTID the TransTID to set
     */
    public void setTransTID(String TransTID) {
        this.TransTID = TransTID;
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
