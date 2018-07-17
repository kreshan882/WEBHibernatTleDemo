/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

import com.epic.tle.util.Util;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author dimuthu_h
 */
public class ServerConfigurationBean {

// check access
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vview;

    private String PageCode;
    private String Module;
    private String Section;

//  common configuration
    private String thredMaxPool;
    private String thredMinPool;
    private String backLogSize;
    private String maxQueueSize;
    private int vipStatus;
    private String vip;
    private String iv;
    private int hostFailStatus;
    private int replayAttackLevel;
    private int adVerifyStatus;
    private int autoRegStatus;

    private Map<Integer, String> vipStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> hostFailStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> replayAttackLevelMap = new HashMap<Integer, String>();

//    log configuration
    private int logLevel;
    private String logPath;
    private String numLogFiles;
    private int logBackupStatus;
    private String logFileName;
    private int coreDebugStatus;
    private int numOfHistory;

    private Map<Integer, String> logBackupStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> logLevelMap = new HashMap<Integer, String>();
    private Map<Integer, String> coreDebugStatusMap = new HashMap<Integer, String>();

//    hsm configuration
    private String psgPassword;
    private String hsmSlot;
    private int esmStatus;

//    dashboard confuguration
    private String monIp;
    private String port;
    private int monStatus;
    private String timeOut;
    private Map<Integer, String> monStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> hsmSlotMap = new HashMap<Integer, String>();

    private int smsNotifyStatus;
    private int ukptStat;
    private Map<Integer, String> smsNotify = new HashMap<Integer, String>();
    private Map<Integer, String> ukptStatus = new HashMap<Integer, String>();

//    other configuration
    private String smsUrl;
    private String smsTimeout;
    private String emailUrl;
    private String servicePort;
    private int serviceStatus;
    private String smsUsername;
    private String smsPassword;
    private String clientTimeout;
    private String emailgwurl;
    private int emailgwport;
    private String emailgwuser;
    private String emailgwpassword;
    private String confemailgwpassword;
    private boolean isChecked;
    private boolean isCheckedsms;
    private String adUrl;
    private String adUsername;
    private String adPassword;
    private String buffer;
    private int smsPort;

//  session confuguration
    private String pinCounter;

    private String formtag;
    private Map<Integer, String> serviceStatusMap = new HashMap<Integer, String>();

    //***************Token************************
    private String Token;

    public int getSmsPort() {
        return smsPort;
    }

    public void setSmsPort(int smsPort) {
        this.smsPort = smsPort;
    }
    
    

    public int getNumOfHistory() {
        return numOfHistory;
    }

    public String getBuffer() {
        return buffer;
    }

    public String getSmsTimeout() {
        return smsTimeout;
    }

    public void setSmsTimeout(String smsTimeout) {
        this.smsTimeout = smsTimeout;
    }

    public void setBuffer(String buffer) {
        this.buffer = buffer;
    }

    public Map<Integer, String> getHsmSlotMap() {
        return hsmSlotMap;
    }

    public void setHsmSlotMap(Map<Integer, String> hsmSlotMap) {
        this.hsmSlotMap = hsmSlotMap;
    }

    public void setNumOfHistory(int numOfHistory) {
        this.numOfHistory = numOfHistory;
    }

    public int getAdVerifyStatus() {
        return adVerifyStatus;
    }

    public void setAdVerifyStatus(int adVerifyStatus) {
        this.adVerifyStatus = adVerifyStatus;
    }

    public int getAutoRegStatus() {
        return autoRegStatus;
    }

    public void setAutoRegStatus(int autoRegStatus) {
        this.autoRegStatus = autoRegStatus;
    }

    public String getAdUrl() {
        return adUrl;
    }

    public void setAdUrl(String adUrl) {
        this.adUrl = adUrl;
    }

    public String getAdUsername() {
        return adUsername;
    }

    public void setAdUsername(String adUsername) {
        this.adUsername = adUsername;
    }

    public String getAdPassword() {
        return adPassword;
    }

    public void setAdPassword(String adPassword) {
        this.adPassword = adPassword;
    }

    public String getPinCounter() {
        return pinCounter;
    }

    public void setPinCounter(String pinCounter) {
        this.pinCounter = pinCounter;
    }

    public String getFormtag() {
        return formtag;
    }

    public void setFormtag(String formtag) {
        this.formtag = formtag;
    }

    public String getThredMaxPool() {
        return thredMaxPool;
    }

    public void setThredMaxPool(String thredMaxPool) {
        this.thredMaxPool = thredMaxPool;
    }

    public String getThredMinPool() {
        return thredMinPool;
    }

    public void setThredMinPool(String thredMinPool) {
        this.thredMinPool = thredMinPool;
    }

    public String getBackLogSize() {
        return backLogSize;
    }

    public void setBackLogSize(String backLogSize) {
        this.backLogSize = backLogSize;
    }

    public String getMaxQueueSize() {
        return maxQueueSize;
    }

    public void setMaxQueueSize(String maxQueueSize) {
        this.maxQueueSize = maxQueueSize;
    }

    public String getNumLogFiles() {
        return numLogFiles;
    }

    public void setNumLogFiles(String numLogFiles) {
        this.numLogFiles = numLogFiles;
    }

    public String getHsmSlot() {
        return hsmSlot;
    }

    public void setHsmSlot(String hsmSlot) {
        this.hsmSlot = hsmSlot;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(String timeOut) {
        this.timeOut = timeOut;
    }

    public String getServicePort() {
        return servicePort;
    }

    public void setServicePort(String servicePort) {
        this.servicePort = servicePort;
    }

    public String getSmsUrl() {
        return smsUrl;
    }

    public void setSmsUrl(String smsUrl) {
        this.smsUrl = smsUrl;
    }

    public String getEmailUrl() {
        return emailUrl;
    }

    public void setEmailUrl(String emailUrl) {
        this.emailUrl = emailUrl;
    }

    public int getServiceStatus() {
        return serviceStatus;
    }

    public void setServiceStatus(int serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    public String getSmsUsername() {
        return smsUsername;
    }

    public void setSmsUsername(String smsUsername) {
        this.smsUsername = smsUsername;
    }

    public String getSmsPassword() {
        return smsPassword;
    }

    public void setSmsPassword(String smsPassword) {
        this.smsPassword = smsPassword;
    }

    public String getClientTimeout() {
        return clientTimeout;
    }

    public void setClientTimeout(String clientTimeout) {
        this.clientTimeout = clientTimeout;
    }

    public Map<Integer, String> getServiceStatusMap() {
        return serviceStatusMap;
    }

    public void setServiceStatusMap(Map<Integer, String> serviceStatusMap) {
        this.serviceStatusMap = serviceStatusMap;
    }

    public Map<Integer, String> getMonStatusMap() {
        return monStatusMap;
    }

    public void setMonStatusMap(Map<Integer, String> monStatusMap) {
        this.monStatusMap = monStatusMap;
    }

    public String getMonIp() {
        return monIp;
    }

    public void setMonIp(String monIp) {
        this.monIp = monIp;
    }

    public int getMonStatus() {
        return monStatus;
    }

    public void setMonStatus(int monStatus) {
        this.monStatus = monStatus;
    }

    public String getPsgPassword() {
        return psgPassword;
    }

    public void setPsgPassword(String psgPassword) {
        this.psgPassword = psgPassword;
    }

    public int getEsmStatus() {
        return esmStatus;
    }

    public void setEsmStatus(int esmStatus) {
        this.esmStatus = esmStatus;
    }

    public int getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(int logLevel) {
        this.logLevel = logLevel;
    }

    public String getLogPath() {
        return logPath;
    }

    public void setLogPath(String logPath) {
        this.logPath = logPath;
    }

    public int getLogBackupStatus() {
        return logBackupStatus;
    }

    public void setLogBackupStatus(int logBackupStatus) {
        this.logBackupStatus = logBackupStatus;
    }

    public String getLogFileName() {
        return logFileName;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public int getCoreDebugStatus() {
        return coreDebugStatus;
    }

    public void setCoreDebugStatus(int coreDebugStatus) {
        this.coreDebugStatus = coreDebugStatus;
    }

    public Map<Integer, String> getLogBackupStatusMap() {
        return logBackupStatusMap;
    }

    public void setLogBackupStatusMap(Map<Integer, String> logBackupStatusMap) {
        this.logBackupStatusMap = logBackupStatusMap;
    }

    public Map<Integer, String> getLogLevelMap() {
        return logLevelMap;
    }

    public void setLogLevelMap(Map<Integer, String> logLevelMap) {
        this.logLevelMap = logLevelMap;
    }

    public Map<Integer, String> getCoreDebugStatusMap() {
        return coreDebugStatusMap;
    }

    public void setCoreDebugStatusMap(Map<Integer, String> coreDebugStatusMap) {
        this.coreDebugStatusMap = coreDebugStatusMap;
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

    public String getPageCode() {
        return PageCode;
    }

    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    public String getModule() {
        return Module;
    }

    public void setModule(String Module) {
        this.Module = Module;
    }

    public String getSection() {
        return Section;
    }

    public void setSection(String Section) {
        this.Section = Section;
    }

    public int getVipStatus() {
        return vipStatus;
    }

    public void setVipStatus(int vipStatus) {
        this.vipStatus = vipStatus;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getIv() {
        return iv;
    }

    public void setIv(String iv) {
        this.iv = iv;
    }

    public int getHostFailStatus() {
        return hostFailStatus;
    }

    public void setHostFailStatus(int hostFailStatus) {
        this.hostFailStatus = hostFailStatus;
    }

    public int getReplayAttackLevel() {
        return replayAttackLevel;
    }

    public void setReplayAttackLevel(int replayAttackLevel) {
        this.replayAttackLevel = replayAttackLevel;
    }

    public Map<Integer, String> getVipStatusMap() {
        return vipStatusMap;
    }

    public void setVipStatusMap(Map<Integer, String> vipStatusMap) {
        this.vipStatusMap = vipStatusMap;
    }

    public Map<Integer, String> getHostFailStatusMap() {
        return hostFailStatusMap;
    }

    public void setHostFailStatusMap(Map<Integer, String> hostFailStatusMap) {
        this.hostFailStatusMap = hostFailStatusMap;
    }

    public Map<Integer, String> getReplayAttackLevelMap() {
        return replayAttackLevelMap;
    }

    public void setReplayAttackLevelMap(Map<Integer, String> replayAttackLevelMap) {
        this.replayAttackLevelMap = replayAttackLevelMap;
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
     * @return the smsNotify
     */
    public Map<Integer, String> getSmsNotify() {
        return smsNotify;
    }

    /**
     * @param smsNotify the smsNotify to set
     */
    public void setSmsNotify(Map<Integer, String> smsNotify) {
        this.smsNotify = smsNotify;
    }

    /**
     * @return the ukptStatus
     */
    public Map<Integer, String> getUkptStatus() {
        return ukptStatus;
    }

    /**
     * @param ukptStatus the ukptStatus to set
     */
    public void setUkptStatus(Map<Integer, String> ukptStatus) {
        this.ukptStatus = ukptStatus;
    }

    /**
     * @return the smsNotifyStatus
     */
    public int getSmsNotifyStatus() {
        return smsNotifyStatus;
    }

    /**
     * @param smsNotifyStatus the smsNotifyStatus to set
     */
    public void setSmsNotifyStatus(int smsNotifyStatus) {
        this.smsNotifyStatus = smsNotifyStatus;
    }

    /**
     * @return the ukptStat
     */
    public int getUkptStat() {
        return ukptStat;
    }

    /**
     * @param ukptStat the ukptStat to set
     */
    public void setUkptStat(int ukptStat) {
        this.ukptStat = ukptStat;
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

    public String getEmailgwurl() {
        return emailgwurl;
    }

    public void setEmailgwurl(String emailgwurl) {
        this.emailgwurl = emailgwurl;
    }

    public int getEmailgwport() {
        return emailgwport;
    }

    public void setEmailgwport(int emailgwport) {
        this.emailgwport = emailgwport;
    }

    public String getEmailgwuser() {
        return emailgwuser;
    }

    public void setEmailgwuser(String emailgwuser) {
        this.emailgwuser = emailgwuser;
    }

    public String getEmailgwpassword() {
        return emailgwpassword;
    }

    public void setEmailgwpassword(String emailgwpassword) {
        this.emailgwpassword = emailgwpassword;
    }

    public String getConfemailgwpassword() {
        return confemailgwpassword;
    }

    public void setConfemailgwpassword(String confemailgwpassword) {
        this.confemailgwpassword = confemailgwpassword;
    }

    public boolean isIsChecked() {
        return isChecked;
    }

    public void setIsChecked(boolean isChecked) {
        this.isChecked = isChecked;
    }

    @Override
    public String toString() {
        return "ServerConfigurationBean{" + "vadd=" + vadd + ", vupdate=" + vupdate + ", vdelete=" + vdelete + ", vview=" + vview + ", PageCode=" + PageCode + ", Module=" + Module + ", Section=" + Section + ", thredMaxPool=" + thredMaxPool + ", thredMinPool=" + thredMinPool + ", backLogSize=" + backLogSize + ", maxQueueSize=" + maxQueueSize + ", vipStatus=" + vipStatus + ", vip=" + vip + ", iv=" + iv + ", hostFailStatus=" + hostFailStatus + ", replayAttackLevel=" + replayAttackLevel + ", adVerifyStatus=" + adVerifyStatus + ", autoRegStatus=" + autoRegStatus + ", vipStatusMap=" + vipStatusMap + ", hostFailStatusMap=" + hostFailStatusMap + ", replayAttackLevelMap=" + replayAttackLevelMap + ", logLevel=" + logLevel + ", logPath=" + logPath + ", numLogFiles=" + numLogFiles + ", logBackupStatus=" + logBackupStatus + ", logFileName=" + logFileName + ", coreDebugStatus=" + coreDebugStatus + ", numOfHistory=" + numOfHistory + ", logBackupStatusMap=" + logBackupStatusMap + ", logLevelMap=" + logLevelMap + ", coreDebugStatusMap=" + coreDebugStatusMap + ", psgPassword=" + psgPassword + ", hsmSlot=" + hsmSlot + ", esmStatus=" + esmStatus + ", monIp=" + monIp + ", port=" + port + ", monStatus=" + monStatus + ", timeOut=" + timeOut + ", monStatusMap=" + monStatusMap + ", hsmSlotMap=" + hsmSlotMap + ", smsNotifyStatus=" + smsNotifyStatus + ", ukptStat=" + ukptStat + ", smsNotify=" + smsNotify + ", ukptStatus=" + ukptStatus + ", smsUrl=" + smsUrl + ", smsTimeout=" + smsTimeout + ", emailUrl=" + emailUrl + ", servicePort=" + servicePort + ", serviceStatus=" + serviceStatus + ", smsUsername=" + smsUsername + ", smsPassword=" + smsPassword + ", clientTimeout=" + clientTimeout + ", emailgwurl=" + emailgwurl + ", emailgwport=" + emailgwport + ", emailgwuser=" + emailgwuser + ", emailgwpassword=" + emailgwpassword + ", confemailgwpassword=" + confemailgwpassword + ", isChecked=" + isChecked + ", isCheckedsms=" + isCheckedsms + ", adUrl=" + adUrl + ", adUsername=" + adUsername + ", adPassword=" + adPassword + ", buffer=" + buffer + ", pinCounter=" + pinCounter + ", formtag=" + formtag + ", serviceStatusMap=" + serviceStatusMap + ", Token=" + Token + '}';
    }

    

    public boolean isIsCheckedsms() {
        return isCheckedsms;
    }

    public void setIsCheckedsms(boolean isCheckedsms) {
        this.isCheckedsms = isCheckedsms;
    }

   

   

    

}
