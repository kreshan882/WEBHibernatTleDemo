/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chalaka_n
 */
public class ListenerManageInputBean {
    private List<ListenerManageDataBean> gridModel;
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

    //**************** add nii map **********************
    private String anii;
    private String amapnii;
    private String aencStatus;
    
    private String PageCode="";
    
   //***************Working Path*************
    private String Module="";
    private String Section="";
    
    private Map<Integer, String> liststatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> listconTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> listKeepLiveMap = new HashMap<Integer, String>();
    private Map<Integer, String> listStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> listBindStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> listHeaderSizeMap = new HashMap<Integer, String>();
    private Map<Integer, String> listHeaderFormatMap = new HashMap<Integer, String>();
    private Map<Integer, String> ISOFormat = new HashMap<Integer, String>();

    //**************** update nii map **********************
    private String upnii;
    private String upmapnii;
    private String upencStatus;

    //**************** delete nii map **********************
    private String dnii;
    private boolean isDeleted;
    private String dmessage;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vview;
    
//    Add
    private int listId;
    private String listName;
    private String listPort;
    private String listConnectType;
    private String listTimeOut;
    private String listKeepLive;
    private String listHeaderFormat;
    private String listHeadSize;
    private String listStatus;
    private String listBindStatus;
    private String isofile;
    private String tpduStatus;
    
//    update
    
     private int uplistId;
    private String uplistName;
    private String uplistProt;
    private String uplistConnectType;
    private String uplistTimeOut;
    private String uplistKeepLive;
    private String uplistHeaderFormat;
    private String uplistHeadSize;
    private String uplistStatus;
    private String uplistBindStatus;
    private String upisofile;
    private String uptpduStatus;
    
    private String hidUplistName;
    private String hidUplistPort;
    
    //***************Token************************
    private String Token;

    public List<ListenerManageDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ListenerManageDataBean> gridModel) {
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

    public String getAnii() {
        return anii;
    }

    public void setAnii(String anii) {
        this.anii = anii;
    }

    public String getAmapnii() {
        return amapnii;
    }

    public void setAmapnii(String amapnii) {
        this.amapnii = amapnii;
    }

    public String getAencStatus() {
        return aencStatus;
    }

    public void setAencStatus(String aencStatus) {
        this.aencStatus = aencStatus;
    }

    public Map<Integer, String> getListstatusMap() {
        return liststatusMap;
    }

    public void setListstatusMap(Map<Integer, String> liststatusMap) {
        this.liststatusMap = liststatusMap;
    }

    public Map<Integer, String> getListconTypeMap() {
        return listconTypeMap;
    }

    public void setListconTypeMap(Map<Integer, String> listconTypeMap) {
        this.listconTypeMap = listconTypeMap;
    }

    public Map<Integer, String> getListKeepLiveMap() {
        return listKeepLiveMap;
    }

    public void setListKeepLiveMap(Map<Integer, String> listKeepLiveMap) {
        this.listKeepLiveMap = listKeepLiveMap;
    }

    public Map<Integer, String> getListStatusMap() {
        return listStatusMap;
    }

    public void setListStatusMap(Map<Integer, String> listStatusMap) {
        this.listStatusMap = listStatusMap;
    }

    public Map<Integer, String> getListBindStatusMap() {
        return listBindStatusMap;
    }

    public void setListBindStatusMap(Map<Integer, String> listBindStatusMap) {
        this.listBindStatusMap = listBindStatusMap;
    }

    public Map<Integer, String> getListHeaderSizeMap() {
        return listHeaderSizeMap;
    }

    public void setListHeaderSizeMap(Map<Integer, String> listHeaderSizeMap) {
        this.listHeaderSizeMap = listHeaderSizeMap;
    }

    public Map<Integer, String> getListHeaderFormatMap() {
        return listHeaderFormatMap;
    }

    public void setListHeaderFormatMap(Map<Integer, String> listHeaderFormatMap) {
        this.listHeaderFormatMap = listHeaderFormatMap;
    }

    public String getUpnii() {
        return upnii;
    }

    public void setUpnii(String upnii) {
        this.upnii = upnii;
    }

    public String getUpmapnii() {
        return upmapnii;
    }

    public void setUpmapnii(String upmapnii) {
        this.upmapnii = upmapnii;
    }

    public String getUpencStatus() {
        return upencStatus;
    }

    public void setUpencStatus(String upencStatus) {
        this.upencStatus = upencStatus;
    }

    public String getDnii() {
        return dnii;
    }

    public void setDnii(String dnii) {
        this.dnii = dnii;
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

    public int getListId() {
        return listId;
    }

    public void setListId(int listId) {
        this.listId = listId;
    }

    public String getListName() {
        return listName;
    }

    public void setListName(String listName) {
        this.listName = listName;
    }

    public String getListPort() {
        return listPort;
    }

    public void setListPort(String listPort) {
        this.listPort = listPort;
    }

    

    public String getListConnectType() {
        return listConnectType;
    }

    public void setListConnectType(String listConnectType) {
        this.listConnectType = listConnectType;
    }

    public String getListTimeOut() {
        return listTimeOut;
    }

    public void setListTimeOut(String listTimeOut) {
        this.listTimeOut = listTimeOut;
    }

    public String getListKeepLive() {
        return listKeepLive;
    }

    public void setListKeepLive(String listKeepLive) {
        this.listKeepLive = listKeepLive;
    }

    public String getListHeaderFormat() {
        return listHeaderFormat;
    }

    public void setListHeaderFormat(String listHeaderFormat) {
        this.listHeaderFormat = listHeaderFormat;
    }

    public String getListHeadSize() {
        return listHeadSize;
    }

    public void setListHeadSize(String listHeadSize) {
        this.listHeadSize = listHeadSize;
    }

    public String getListStatus() {
        return listStatus;
    }

    public void setListStatus(String listStatus) {
        this.listStatus = listStatus;
    }

    public String getListBindStatus() {
        return listBindStatus;
    }

    public void setListBindStatus(String listBindStatus) {
        this.listBindStatus = listBindStatus;
    }

    public int getUplistId() {
        return uplistId;
    }

    public void setUplistId(int uplistId) {
        this.uplistId = uplistId;
    }

    public String getUplistName() {
        return uplistName;
    }

    public void setUplistName(String uplistName) {
        this.uplistName = uplistName;
    }

    public String getUplistProt() {
        return uplistProt;
    }

    public void setUplistProt(String uplistProt) {
        this.uplistProt = uplistProt;
    }

    public String getUplistConnectType() {
        return uplistConnectType;
    }

    public void setUplistConnectType(String uplistConnectType) {
        this.uplistConnectType = uplistConnectType;
    }

    public String getUplistTimeOut() {
        return uplistTimeOut;
    }

    public void setUplistTimeOut(String uplistTimeOut) {
        this.uplistTimeOut = uplistTimeOut;
    }

    public String getUplistKeepLive() {
        return uplistKeepLive;
    }

    public void setUplistKeepLive(String uplistKeepLive) {
        this.uplistKeepLive = uplistKeepLive;
    }

    public String getUplistHeaderFormat() {
        return uplistHeaderFormat;
    }

    public void setUplistHeaderFormat(String uplistHeaderFormat) {
        this.uplistHeaderFormat = uplistHeaderFormat;
    }

    public String getUplistHeadSize() {
        return uplistHeadSize;
    }

    public void setUplistHeadSize(String uplistHeadSize) {
        this.uplistHeadSize = uplistHeadSize;
    }

    public String getUplistStatus() {
        return uplistStatus;
    }

    public void setUplistStatus(String uplistStatus) {
        this.uplistStatus = uplistStatus;
    }

    public String getUplistBindStatus() {
        return uplistBindStatus;
    }

    public void setUplistBindStatus(String uplistBindStatus) {
        this.uplistBindStatus = uplistBindStatus;
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
     * @return the isofile
     */
    public String getIsofile() {
        return isofile;
    }

    /**
     * @param isofile the isofile to set
     */
    public void setIsofile(String isofile) {
        this.isofile = isofile;
    }

    /**
     * @return the upisofile
     */
    public String getUpisofile() {
        return upisofile;
    }

    /**
     * @param upisofile the upisofile to set
     */
    public void setUpisofile(String upisofile) {
        this.upisofile = upisofile;
    }

    /**
     * @return the ISOFormat
     */
    public Map<Integer, String> getISOFormat() {
        return ISOFormat;
    }

    /**
     * @param ISOFormat the ISOFormat to set
     */
    public void setISOFormat(Map<Integer, String> ISOFormat) {
        this.ISOFormat = ISOFormat;
    }

    /**
     * @return the tpduStatus
     */
    public String getTpduStatus() {
        return tpduStatus;
    }

    /**
     * @param tpduStatus the tpduStatus to set
     */
    public void setTpduStatus(String tpduStatus) {
        this.tpduStatus = tpduStatus;
    }

    /**
     * @return the uptpduStatus
     */
    public String getUptpduStatus() {
        return uptpduStatus;
    }

    /**
     * @param uptpduStatus the uptpduStatus to set
     */
    public void setUptpduStatus(String uptpduStatus) {
        this.uptpduStatus = uptpduStatus;
    }

    /**
     * @return the vview
     */
    public boolean isVview() {
        return vview;
    }

    /**
     * @param vview the vview to set
     */
    public void setVview(boolean vview) {
        this.vview = vview;
    }

    /**
     * @return the hidUplistName
     */
    public String getHidUplistName() {
        return hidUplistName;
    }

    /**
     * @param hidUplistName the hidUplistName to set
     */
    public void setHidUplistName(String hidUplistName) {
        this.hidUplistName = hidUplistName;
    }

    /**
     * @return the hidUplistPort
     */
    public String getHidUplistPort() {
        return hidUplistPort;
    }

    /**
     * @param hidUplistPort the hidUplistPort to set
     */
    public void setHidUplistPort(String hidUplistPort) {
        this.hidUplistPort = hidUplistPort;
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
