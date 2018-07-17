/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util.constant;

/**
 *
 * @author chalaka_n
 */
public class SwitchConfBean {
    private String ip;
    private int port;
    private int timeout;
    private int hsmSlot;
    private int versionNo;
    

    public int getHsmSlot() {
        return hsmSlot;
    }

    public void setHsmSlot(int hsmSlot) {
        this.hsmSlot = hsmSlot;
    }

    public int getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(int versionNo) {
        this.versionNo = versionNo;
    }

    
    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
    

    public int getTimeout() {
        return timeout;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    
    
    
    
}
