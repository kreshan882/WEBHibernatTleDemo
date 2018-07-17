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
public class SystemTnxFailDataBean {
    private String sid;
    private String tid;
    private String serialno;
    private Date datetime;
    private String description;
    private String tnxtpe;
    private String responsecode;
    private String encmode;
    private String encalgo;
    private String diviceip;
    private String connectionip;
    private long fullCount;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTnxtpe() {
        return tnxtpe;
    }

    public void setTnxtpe(String tnxtpe) {
        this.tnxtpe = tnxtpe;
    }

    public String getResponsecode() {
        return responsecode;
    }

    public void setResponsecode(String responsecode) {
        this.responsecode = responsecode;
    }

    public String getEncmode() {
        return encmode;
    }

    public void setEncmode(String encmode) {
        this.encmode = encmode;
    }

    public String getEncalgo() {
        return encalgo;
    }

    public void setEncalgo(String encalgo) {
        this.encalgo = encalgo;
    }

    public String getDiviceip() {
        return diviceip;
    }

    public void setDiviceip(String diviceip) {
        this.diviceip = diviceip;
    }

    public String getConnectionip() {
        return connectionip;
    }

    public void setConnectionip(String connectionip) {
        this.connectionip = connectionip;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

   
}
