/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

/**
 *
 * @author dimuthu_h
 */
public class PortConfigurationDataBean {
    private String description;
    private String datarate;
    private String databit;
    private String parity;
    private String stopbit;
    private String status;
    private String port;
    private String sid;
    private long fullCount;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }   

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    
}
