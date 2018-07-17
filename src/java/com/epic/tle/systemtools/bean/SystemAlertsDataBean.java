/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.bean;

import java.util.Date;

/**
 *
 * @author dimuthu_h
 */
public class SystemAlertsDataBean {

    private String sid;
    private String tid;
    private String serialno;
    private String alerts;
    private String datetime;
    private String diviceip;
    private String connectionip;
    private String alertType;
    private String risklevl;
    private String cardBin;
    private String tleStatus;
    private String respCode;
    private String mti;
    private String node;
    
    private long fullCount;

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

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }
    

    /**
     * @return the serialno
     */
    public String getSerialno() {
        return serialno;
    }

    /**
     * @param serialno the serialno to set
     */
    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    /**
     * @return the alerts
     */
    public String getAlerts() {
        return alerts;
    }

    /**
     * @param alerts the alerts to set
     */
    public void setAlerts(String alerts) {
        this.alerts = alerts;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    

    /**
     * @return the diviceip
     */
    public String getDiviceip() {
        return diviceip;
    }

    /**
     * @param diviceip the diviceip to set
     */
    public void setDiviceip(String diviceip) {
        this.diviceip = diviceip;
    }

    /**
     * @return the connectionip
     */
    public String getConnectionip() {
        return connectionip;
    }

    /**
     * @param connectionip the connectionip to set
     */
    public void setConnectionip(String connectionip) {
        this.connectionip = connectionip;
    }

    /**
     * @return the alertType
     */
    public String getAlertType() {
        return alertType;
    }

    /**
     * @param alertType the alertType to set
     */
    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    /**
     * @return the cardBin
     */
    public String getCardBin() {
        return cardBin;
    }

    /**
     * @param cardBin the cardBin to set
     */
    public void setCardBin(String cardBin) {
        this.cardBin = cardBin;
    }

    /**
     * @return the tleStatus
     */
    public String getTleStatus() {
        return tleStatus;
    }

    /**
     * @param tleStatus the tleStatus to set
     */
    public void setTleStatus(String tleStatus) {
        this.tleStatus = tleStatus;
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
     * @return the mti
     */
    public String getMti() {
        return mti;
    }

    /**
     * @param mti the mti to set
     */
    public void setMti(String mti) {
        this.mti = mti;
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
     * @return the risklevl
     */
    public String getRisklevl() {
        return risklevl;
    }

    /**
     * @param risklevl the risklevl to set
     */
    public void setRisklevl(String risklevl) {
        this.risklevl = risklevl;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


}
