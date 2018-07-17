/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

/**
 *
 * @author KRESHAN
 */
public class ListenerManageDataBean {
    private String encStatus;
    private String niiVal;
    private String mapNiiVal;
    
    
    
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
    private String tpduStatus;
    private String isoFile;

    
    private long fullCount;

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

    
    public String getEncStatus() {
        return encStatus;
    }

    public void setEncStatus(String encStatus) {
        this.encStatus = encStatus;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public String getMapNiiVal() {
        return mapNiiVal;
    }

    public void setMapNiiVal(String mapNiiVal) {
        this.mapNiiVal = mapNiiVal;
    }

    public String getNiiVal() {
        return niiVal;
    }

    public void setNiiVal(String niiVal) {
        this.niiVal = niiVal;
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
     * @return the isoFile
     */
    public String getIsoFile() {
        return isoFile;
    }

    /**
     * @param isoFile the isoFile to set
     */
    public void setIsoFile(String isoFile) {
        this.isoFile = isoFile;
    }

    
}
