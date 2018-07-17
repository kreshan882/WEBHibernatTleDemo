/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.bean;

/**
 *
 * @author thilina_t
 */
public class SystemProcessDataBean {

    private String id;
    private String epicTleStatus;
    private String epicTleTxntypes;
    private String totalTime;
    private String hostTime;
    private String datetime;
    private String tleTime;
    private String tid;
    private String responseCode;
    private String bin;
    private String traceNo;
    private String node;

    private long fullCount;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the epicTleStatus
     */
    public String getEpicTleStatus() {
        return epicTleStatus;
    }

    /**
     * @param epicTleStatus the epicTleStatus to set
     */
    public void setEpicTleStatus(String epicTleStatus) {
        this.epicTleStatus = epicTleStatus;
    }

    /**
     * @return the epicTleTxntypes
     */
    public String getEpicTleTxntypes() {
        return epicTleTxntypes;
    }

    /**
     * @param epicTleTxntypes the epicTleTxntypes to set
     */
    public void setEpicTleTxntypes(String epicTleTxntypes) {
        this.epicTleTxntypes = epicTleTxntypes;
    }

    /**
     * @return the totalTime
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @return the hostTime
     */
    public String getHostTime() {
        return hostTime;
    }

    /**
     * @param hostTime the hostTime to set
     */
    public void setHostTime(String hostTime) {
        this.hostTime = hostTime;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the tleTime
     */
    public String getTleTime() {
        return tleTime;
    }

    /**
     * @param tleTime the tleTime to set
     */
    public void setTleTime(String tleTime) {
        this.tleTime = tleTime;
    }

    /**
     * @return the tid
     */
    public String getTid() {
        return tid;
    }

    /**
     * @param tid the tid to set
     */
    public void setTid(String tid) {
        this.tid = tid;
    }

    /**
     * @return the responseCode
     */
    public String getResponseCode() {
        return responseCode;
    }

    /**
     * @param responseCode the responseCode to set
     */
    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    /**
     * @return the bin
     */
    public String getBin() {
        return bin;
    }

    /**
     * @param bin the bin to set
     */
    public void setBin(String bin) {
        this.bin = bin;
    }

    /**
     * @return the traceNo
     */
    public String getTraceNo() {
        return traceNo;
    }

    /**
     * @param traceNo the traceNo to set
     */
    public void setTraceNo(String traceNo) {
        this.traceNo = traceNo;
    }

    /**
     * @return the fullCount
     */
    public long getFullCount() {
        return fullCount;
    }

    /**
     * @param fullCount the fullCount to set
     */
    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    /**
     * @return the node
     */
    public String getNode() {
        return node;
    }

    /**
     * @param node the node to set
     */
    public void setNode(String node) {
        this.node = node;
    }
}
