/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

/**
 *
 * @author kreshan
 */
public class ChannelManageDataBean {
        
    private String groupName;
    private String hostName;
    private String ip;
    private String port;
    private String connectiontype;
    private String status;
    private String routemethod;
    private int headerSize;
    private int headerFormat;
    private int channelId;
    private String channelName;
    private String bindStatus;
    private String contime;
    private String readtime;
    private String isoFile;
    private String TPDUStatus;
    private String loadBalance;
    private String secIp;
    private String secPort;
    private String secStatus;
    private String operMethod;
    private String pinTranslate;
    private String encryptZpk;
    private String KeepAlive;

    public int getHeaderSize() {
        return headerSize;
    }

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
    }

    public int getHeaderFormat() {
        return headerFormat;
    }

    public void setHeaderFormat(int headerFormat) {
        this.headerFormat = headerFormat;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    
    private long fullCount;

    public String getConnectiontype() {
        return connectiontype;
    }

    public void setConnectiontype(String connectiontype) {
        this.connectiontype = connectiontype;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getRoutemethod() {
        return routemethod;
    }

    public void setRoutemethod(String routemethod) {
        this.routemethod = routemethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    /**
     * @return the bindStatus
     */
    public String getBindStatus() {
        return bindStatus;
    }

    /**
     * @param bindStatus the bindStatus to set
     */
    public void setBindStatus(String bindStatus) {
        this.bindStatus = bindStatus;
    }

    /**
     * @return the contime
     */
    public String getContime() {
        return contime;
    }

    /**
     * @param contime the contime to set
     */
    public void setContime(String contime) {
        this.contime = contime;
    }

    /**
     * @return the readtime
     */
    public String getReadtime() {
        return readtime;
    }

    /**
     * @param readtime the readtime to set
     */
    public void setReadtime(String readtime) {
        this.readtime = readtime;
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

    /**
     * @return the TPDUStatus
     */
    public String getTPDUStatus() {
        return TPDUStatus;
    }

    /**
     * @param TPDUStatus the TPDUStatus to set
     */
    public void setTPDUStatus(String TPDUStatus) {
        this.TPDUStatus = TPDUStatus;
    }

    /**
     * @return the loadBalance
     */
    public String getLoadBalance() {
        return loadBalance;
    }

    /**
     * @param loadBalance the loadBalance to set
     */
    public void setLoadBalance(String loadBalance) {
        this.loadBalance = loadBalance;
    }

    /**
     * @return the secIp
     */
    public String getSecIp() {
        return secIp;
    }

    /**
     * @param secIp the secIp to set
     */
    public void setSecIp(String secIp) {
        this.secIp = secIp;
    }

    /**
     * @return the secPort
     */
    public String getSecPort() {
        return secPort;
    }

    /**
     * @param secPort the secPort to set
     */
    public void setSecPort(String secPort) {
        this.secPort = secPort;
    }

    /**
     * @return the secStatus
     */
    public String getSecStatus() {
        return secStatus;
    }

    /**
     * @param secStatus the secStatus to set
     */
    public void setSecStatus(String secStatus) {
        this.secStatus = secStatus;
    }

    /**
     * @return the operMethod
     */
    public String getOperMethod() {
        return operMethod;
    }

    /**
     * @param operMethod the operMethod to set
     */
    public void setOperMethod(String operMethod) {
        this.operMethod = operMethod;
    }

    /**
     * @return the pinTranslate
     */
    public String getPinTranslate() {
        return pinTranslate;
    }

    /**
     * @param pinTranslate the pinTranslate to set
     */
    public void setPinTranslate(String pinTranslate) {
        this.pinTranslate = pinTranslate;
    }

    /**
     * @return the encryptZpk
     */
    public String getEncryptZpk() {
        return encryptZpk;
    }

    /**
     * @param encryptZpk the encryptZpk to set
     */
    public void setEncryptZpk(String encryptZpk) {
        this.encryptZpk = encryptZpk;
    }

    /**
     * @return the KeepAlive
     */
    public String getKeepAlive() {
        return KeepAlive;
    }

    /**
     * @param KeepAlive the KeepAlive to set
     */
    public void setKeepAlive(String KeepAlive) {
        this.KeepAlive = KeepAlive;
    }
    
    
}
