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
public class SystemTnxFailInputBean {

    private List<SystemTnxFailDataBean> gridModel;
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
    ///search value
    ////////////////////////////
    private String tid;
    private String serial;
    private String respCode;
    private String fromdate;
    private String todate;
    private String selectNode;

    /////////////////////////////
    ///load data to map
    /////////////////////////////
    Map<Integer, String> txnTypeMap = new HashMap<Integer, String>();
    Map<Integer, String> encModeMap = new HashMap<Integer, String>();
    Map<Integer, String> encAlgoMap = new HashMap<Integer, String>();
    private Map<Integer,String> nodes = new HashMap<Integer, String>();

    //**************Export XSL***************
    private ByteArrayInputStream excelStream;
    private ByteArrayInputStream zipStream;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private String PageCode;
    private String Code;

    //***************Working Path*************
    private String Module = "";
    private String Section = "";

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

    public Map<Integer, String> getTxnTypeMap() {
        return txnTypeMap;
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

    public void setTxnTypeMap(Map<Integer, String> txnTypeMap) {
        this.txnTypeMap = txnTypeMap;
    }

    public Map<Integer, String> getEncModeMap() {
        return encModeMap;
    }

    public void setEncModeMap(Map<Integer, String> encModeMap) {
        this.encModeMap = encModeMap;
    }

    public Map<Integer, String> getEncAlgoMap() {
        return encAlgoMap;
    }

    public void setEncAlgoMap(Map<Integer, String> encAlgoMap) {
        this.encAlgoMap = encAlgoMap;
    }

    public List<SystemTnxFailDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<SystemTnxFailDataBean> gridModel) {
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

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
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
     * @return the respCode
     */
    public String getRespCode() {
        return respCode;
    }

    /**
     * @param respCode the respCode to set
     */
    public void setRespCode(String respCode) {
        this.respCode = respCode;
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
     * @return the Code
     */
    public String getCode() {
        return Code;
    }

    /**
     * @param Code the Code to set
     */
    public void setCode(String Code) {
        this.Code = Code;
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
