/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.bean;

/**
 *
 * @author kasun_k
 */
public class RegisterFieldEngineerBean {
    private String sid;
    private String serialno;
    private String selecteddevice;
    private String officername;
    private String bankname;
    private String location;
    private String regdate;
    private String maxtmkdownlod;
    private String avaliabletmkdownlod;
    private String status;
    private String algorithem;
    private String pinVerfi;
    private String bdkindex;
 
    private long fullCount;

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getSerialno() {
        return serialno;
    }

    public void setSerialno(String serialno) {
        this.serialno = serialno;
    }

    public String getSelecteddevice() {
        return selecteddevice;
    }

    public void setSelecteddevice(String selecteddevice) {
        this.selecteddevice = selecteddevice;
    }

    public String getOfficername() {
        return officername;
    }

    public void setOfficername(String officername) {
        this.officername = officername;
    }

    public String getBankname() {
        return bankname;
    }

    public void setBankname(String bankname) {
        this.bankname = bankname;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegdate() {
        return regdate;
    }

    public void setRegdate(String regdate) {
        this.regdate = regdate;
    }

    public String getMaxtmkdownlod() {
        return maxtmkdownlod;
    }

    public void setMaxtmkdownlod(String maxtmkdownlod) {
        this.maxtmkdownlod = maxtmkdownlod;
    }

    public String getAvaliabletmkdownlod() {
        return avaliabletmkdownlod;
    }

    public void setAvaliabletmkdownlod(String avaliabletmkdownlod) {
        this.avaliabletmkdownlod = avaliabletmkdownlod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    /**
     * @return the algorithem
     */
    public String getAlgorithem() {
        return algorithem;
    }

    /**
     * @param algorithem the algorithem to set
     */
    public void setAlgorithem(String algorithem) {
        this.algorithem = algorithem;
    }

    /**
     * @return the pinVerfi
     */
    public String getPinVerfi() {
        return pinVerfi;
    }

    /**
     * @param pinVerfi the pinVerfi to set
     */
    public void setPinVerfi(String pinVerfi) {
        this.pinVerfi = pinVerfi;
    }

    public String getBdkindex() {
        return bdkindex;
    }

    public void setBdkindex(String bdkindex) {
        this.bdkindex = bdkindex;
    }

    
}
