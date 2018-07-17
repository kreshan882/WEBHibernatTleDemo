package com.epic.tle.terminalManagement.bean;

import java.util.Date;

/**
 *
 * @author nipun_t
 */
public class NonFunctionTerminalDataBean {

    private String terminalId;
    private String tid;
    private String mid;
    private String serialNo;
    private String terminalBrand;
    private String bank;
    private String name;
    private String location;
    private String registerDate;
    private String lastTransDate;
    private String encryptionStatus;
    private String status;
    private long fullCount;

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public String getLastTransDate() {
        return lastTransDate;
    }

    public void setLastTransDate(String lastTransDate) {
        this.lastTransDate = lastTransDate;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    public String getMid() {
        return mid;
    }

    public void setMid(String mid) {
        this.mid = mid;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getTerminalBrand() {
        return terminalBrand;
    }

    public void setTerminalBrand(String terminalBrand) {
        this.terminalBrand = terminalBrand;
    }

    public String getBank() {
        return bank;
    }

    public void setBank(String bank) {
        this.bank = bank;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public String getEncryptionStatus() {
        return encryptionStatus;
    }

    public void setEncryptionStatus(String encryptionStatus) {
        this.encryptionStatus = encryptionStatus;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
