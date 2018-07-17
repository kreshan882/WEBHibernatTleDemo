/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author KRESHAN
 */
public class ListenerManageBean {

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
    private Map<String, String> aencStatusMap = new HashMap<String, String>();
    private Map<String, String> mapniiMap = new HashMap<String, String>();

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

    public String getDmessage() {
        return dmessage;
    }

    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
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

    public String getUpnii() {
        return upnii;
    }

    public void setUpnii(String upnii) {
        this.upnii = upnii;
    }

    public String getUpencStatus() {
        return upencStatus;
    }

    public void setUpencStatus(String upencStatus) {
        this.upencStatus = upencStatus;
    }

    public String getUpmapnii() {
        return upmapnii;
    }

    public void setUpmapnii(String upmapnii) {
        this.upmapnii = upmapnii;
    }

    public Map<String, String> getMapniiMap() {
        return mapniiMap;
    }

    public void setMapniiMap(Map<String, String> mapniiMap) {
        this.mapniiMap = mapniiMap;
    }

    public String getAmapnii() {
        return amapnii;
    }

    public void setAmapnii(String amapnii) {
        this.amapnii = amapnii;
    }

    public String getAnii() {
        return anii;
    }

    public void setAnii(String anii) {
        this.anii = anii;
    }

    public String getAencStatus() {
        return aencStatus;
    }

    public void setAencStatus(String aencStatus) {
        this.aencStatus = aencStatus;
    }

    public Map<String, String> getAencStatusMap() {
        return aencStatusMap;
    }

    public void setAencStatusMap(Map<String, String> aencStatusMap) {
        this.aencStatusMap = aencStatusMap;
    }

    public List<ListenerManageDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ListenerManageDataBean> gridModel) {
        this.gridModel = gridModel;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
