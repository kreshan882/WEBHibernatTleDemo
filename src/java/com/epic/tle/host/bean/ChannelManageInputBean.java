/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.bean;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan
 */
public class ChannelManageInputBean {

    private List<ChannelManageDataBean> gridModel;
    private Integer rows = 0;
    private Integer page = 0;
    private Integer total = 0;
    private Long records = 0L;
    private String sord;
    private String sidx;
    private String searchField;
    private String searchString;
    private String searchOper;
    private boolean loadonce = false;

    //Add new channel
    private String agroupName;
    private String channelName;
    private int channelId;
    private String aip;
    private String aport;
    private String sip;
    private String sport;
    private String contime;
    private String readtime;
    private String loadBalance;
    private String isoFile;
    private String tpdu;
    private String ecnryptZPK;
    private String operMethod;
    private String PinTrans;
    private String KeepAlive;

    private int headerFormat;
    private int headerSize;

    private String astatus;
    private String sstatus;
    private String aconType;
    private String aforwerdMathod;
    private Map<Integer, String> astatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> aconTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> aforwerdMathodMap = new HashMap<Integer, String>();
    private Map<Integer, String> atestPackStatusMap = new HashMap<Integer, String>();
    private Map<Integer, String> headerSizeMap = new HashMap<Integer, String>();
    private Map<Integer, String> headerFormatMap = new HashMap<Integer, String>();
    private Map<Integer, String> isoTypeMap = new HashMap<Integer, String>();
    private Map<Integer, String> operMethodMap = new HashMap<Integer, String>();
    private Map<Integer, String> keepAliveStatus = new HashMap<Integer, String>();

    private String PageCode;

    //***************Working Path*************
    private String Module;
    private String Section;
    
    //***************Token************************
    private String Token;

    public Map<Integer, String> getHeaderSizeMap() {
        return headerSizeMap;
    }

    public Map<Integer, String> getHeaderFormatMap() {
        return headerFormatMap;
    }

    //Update channel
    private String upgroupName;
    private String upchannelName;
    private int upchannelId;
    private String upip;
    private String upport;
    private String upcontime;
    private String upreadtime;

    private String upstatus;
    private String upconType;
    private String upforwerdMathod;
    private String uptestPackStatus;

    private int upheaderFormat;
    private int upheaderSize;
    
    private String upisoFile;
    private String upTPDUStatus;
    private String uploadBalance;
    private String upsecIp;
    private String upsecPort;
    private String upsecStatus;
    private String upenzpk;
    private String upoperMethod;
    private String upPinTrans;
    private int upKeepAlive;

    //Hedden Field 
    private String HidUpName;
    private String HidUpip;
    private String hidUpPort;
    private String HidUpsecIp;
    private String HidUpsecPort;
    
    //Delete channel
    private String dnii;
    private boolean isDeleted;
    private String dmessage;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vview;

    public int getUpheaderFormat() {
        return upheaderFormat;
    }

    public void setUpheaderFormat(int upheaderFormat) {
        this.upheaderFormat = upheaderFormat;
    }

    public int getUpheaderSize() {
        return upheaderSize;
    }

    public void setUpheaderSize(int upheaderSize) {
        this.upheaderSize = upheaderSize;
    }

    public int getChannelId() {
        return channelId;
    }

    public void setChannelId(int channelId) {
        this.channelId = channelId;
    }

    public int getUpchannelId() {
        return upchannelId;
    }

    public void setUpchannelId(int upchannelId) {
        this.upchannelId = upchannelId;
    }

    public int getHeaderFormat() {
        return headerFormat;
    }

    public void setHeaderFormat(int headerFormat) {
        this.headerFormat = headerFormat;
    }

    public int getHeaderSize() {
        return headerSize;
    }

    public void setHeaderSize(int headerSize) {
        this.headerSize = headerSize;
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

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getDmessage() {
        return dmessage;
    }

    public void setDmessage(String dmessage) {
        this.dmessage = dmessage;
    }

    public String getDnii() {
        return dnii;
    }

    public void setDnii(String dnii) {
        this.dnii = dnii;
    }

    public String getUpgroupName() {
        return upgroupName;
    }

    public void setUpgroupName(String upgroupName) {
        this.upgroupName = upgroupName;
    }

    public String getUpchannelName() {
        return upchannelName;
    }

    public void setUpchannelName(String upchannelName) {
        this.upchannelName = upchannelName;
    }

    public String getUpip() {
        return upip;
    }

    public void setUpip(String upip) {
        this.upip = upip;
    }

    public String getUpport() {
        return upport;
    }

    public void setUpport(String upport) {
        this.upport = upport;
    }

    public String getUpstatus() {
        return upstatus;
    }

    public void setUpstatus(String upstatus) {
        this.upstatus = upstatus;
    }

    public String getUpconType() {
        return upconType;
    }

    public void setUpconType(String upconType) {
        this.upconType = upconType;
    }

    public String getUpforwerdMathod() {
        return upforwerdMathod;
    }

    public void setUpforwerdMathod(String upforwerdMathod) {
        this.upforwerdMathod = upforwerdMathod;
    }

    public String getUptestPackStatus() {
        return uptestPackStatus;
    }

    public void setUptestPackStatus(String uptestPackStatus) {
        this.uptestPackStatus = uptestPackStatus;
    }

    public List<ChannelManageDataBean> getGridModel() {
        return gridModel;
    }

    public void setGridModel(List<ChannelManageDataBean> gridModel) {
        this.gridModel = gridModel;
    }

    public boolean isLoadonce() {
        return loadonce;
    }

    public void setLoadonce(boolean loadonce) {
        this.loadonce = loadonce;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Long getRecords() {
        return records;
    }

    public void setRecords(Long records) {
        this.records = records;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSearchField() {
        return searchField;
    }

    public void setSearchField(String searchField) {
        this.searchField = searchField;
    }

    public String getSearchOper() {
        return searchOper;
    }

    public void setSearchOper(String searchOper) {
        this.searchOper = searchOper;
    }

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    public String getSidx() {
        return sidx;
    }

    public void setSidx(String sidx) {
        this.sidx = sidx;
    }

    public String getSord() {
        return sord;
    }

    public void setSord(String sord) {
        this.sord = sord;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getAconType() {
        return aconType;
    }

    public void setAconType(String aconType) {
        this.aconType = aconType;
    }

    public Map<Integer, String> getAconTypeMap() {
        return aconTypeMap;
    }

    public void setAconTypeMap(Map<Integer, String> aconTypeMap) {
        this.aconTypeMap = aconTypeMap;
    }

    public String getAforwerdMathod() {
        return aforwerdMathod;
    }

    public void setAforwerdMathod(String aforwerdMathod) {
        this.aforwerdMathod = aforwerdMathod;
    }

    public Map<Integer, String> getAforwerdMathodMap() {
        return aforwerdMathodMap;
    }

    public void setAforwerdMathodMap(Map<Integer, String> aforwerdMathodMap) {
        this.aforwerdMathodMap = aforwerdMathodMap;
    }

    public String getAgroupName() {
        return agroupName;
    }

    public void setAgroupName(String agroupName) {
        this.agroupName = agroupName;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getAip() {
        return aip;
    }

    public void setAip(String aip) {
        this.aip = aip;
    }

    public String getAport() {
        return aport;
    }

    public void setAport(String aport) {
        this.aport = aport;
    }

    public String getAstatus() {
        return astatus;
    }

    public void setAstatus(String astatus) {
        this.astatus = astatus;
    }

    public Map<Integer, String> getAstatusMap() {
        return astatusMap;
    }

    public void setAstatusMap(Map<Integer, String> astatusMap) {
        this.astatusMap = astatusMap;
    }

    public Map<Integer, String> getAtestPackStatusMap() {
        return atestPackStatusMap;
    }

    public void setAtestPackStatusMap(Map<Integer, String> atestPackStatusMap) {
        this.atestPackStatusMap = atestPackStatusMap;
    }

    /**
     * @return the PageCode
     */
    public String getPageCode() {
        return PageCode;
    }

    /**
     * @param PageCode the PageCode to set
     */
    public void setPageCode(String PageCode) {
        this.PageCode = PageCode;
    }

    /**
     * @return the Module
     */
    public String getModule() {
        return Module;
    }

    /**
     * @param Module the Module to set
     */
    public void setModule(String Module) {
        this.Module = Module;
    }

    /**
     * @return the Section
     */
    public String getSection() {
        return Section;
    }

    /**
     * @param Section the Section to set
     */
    public void setSection(String Section) {
        this.Section = Section;
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
     * @return the upcontime
     */
    public String getUpcontime() {
        return upcontime;
    }

    /**
     * @param upcontime the upcontime to set
     */
    public void setUpcontime(String upcontime) {
        this.upcontime = upcontime;
    }

    /**
     * @return the upreadtime
     */
    public String getUpreadtime() {
        return upreadtime;
    }

    /**
     * @param upreadtime the upreadtime to set
     */
    public void setUpreadtime(String upreadtime) {
        this.upreadtime = upreadtime;
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
     * @return the sip
     */
    public String getSip() {
        return sip;
    }

    /**
     * @param sip the sip to set
     */
    public void setSip(String sip) {
        this.sip = sip;
    }

    /**
     * @return the sport
     */
    public String getSport() {
        return sport;
    }

    /**
     * @param sport the sport to set
     */
    public void setSport(String sport) {
        this.sport = sport;
    }

    /**
     * @return the sstatus
     */
    public String getSstatus() {
        return sstatus;
    }

    /**
     * @param sstatus the sstatus to set
     */
    public void setSstatus(String sstatus) {
        this.sstatus = sstatus;
    }

    /**
     * @return the tpdu
     */
    public String getTpdu() {
        return tpdu;
    }

    /**
     * @param tpdu the tpdu to set
     */
    public void setTpdu(String tpdu) {
        this.tpdu = tpdu;
    }

    /**
     * @return the upisoFile
     */
    public String getUpisoFile() {
        return upisoFile;
    }

    /**
     * @param upisoFile the upisoFile to set
     */
    public void setUpisoFile(String upisoFile) {
        this.upisoFile = upisoFile;
    }

    /**
     * @return the upTPDUStatus
     */
    public String getUpTPDUStatus() {
        return upTPDUStatus;
    }

    /**
     * @param upTPDUStatus the upTPDUStatus to set
     */
    public void setUpTPDUStatus(String upTPDUStatus) {
        this.upTPDUStatus = upTPDUStatus;
    }

    /**
     * @return the uploadBalance
     */
    public String getUploadBalance() {
        return uploadBalance;
    }

    /**
     * @param uploadBalance the uploadBalance to set
     */
    public void setUploadBalance(String uploadBalance) {
        this.uploadBalance = uploadBalance;
    }

    /**
     * @return the upsecIp
     */
    public String getUpsecIp() {
        return upsecIp;
    }

    /**
     * @param upsecIp the upsecIp to set
     */
    public void setUpsecIp(String upsecIp) {
        this.upsecIp = upsecIp;
    }

    /**
     * @return the upsecPort
     */
    public String getUpsecPort() {
        return upsecPort;
    }

    /**
     * @param upsecPort the upsecPort to set
     */
    public void setUpsecPort(String upsecPort) {
        this.upsecPort = upsecPort;
    }

    /**
     * @return the upsecStatus
     */
    public String getUpsecStatus() {
        return upsecStatus;
    }

    /**
     * @param upsecStatus the upsecStatus to set
     */
    public void setUpsecStatus(String upsecStatus) {
        this.upsecStatus = upsecStatus;
    }

    /**
     * @param headerSizeMap the headerSizeMap to set
     */
    public void setHeaderSizeMap(Map<Integer, String> headerSizeMap) {
        this.headerSizeMap = headerSizeMap;
    }

    /**
     * @param headerFormatMap the headerFormatMap to set
     */
    public void setHeaderFormatMap(Map<Integer, String> headerFormatMap) {
        this.headerFormatMap = headerFormatMap;
    }

    /**
     * @return the isoTypeMap
     */
    public Map<Integer, String> getIsoTypeMap() {
        return isoTypeMap;
    }

    /**
     * @param isoTypeMap the isoTypeMap to set
     */
    public void setIsoTypeMap(Map<Integer, String> isoTypeMap) {
        this.isoTypeMap = isoTypeMap;
    }

    /**
     * @return the operMethodMap
     */
    public Map<Integer, String> getOperMethodMap() {
        return operMethodMap;
    }

    /**
     * @param operMethodMap the operMethodMap to set
     */
    public void setOperMethodMap(Map<Integer, String> operMethodMap) {
        this.operMethodMap = operMethodMap;
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
     * @return the ecnryptZPK
     */
    public String getEcnryptZPK() {
        return ecnryptZPK;
    }

    /**
     * @param ecnryptZPK the ecnryptZPK to set
     */
    public void setEcnryptZPK(String ecnryptZPK) {
        this.ecnryptZPK = ecnryptZPK;
    }

    /**
     * @return the PinTrans
     */
    public String getPinTrans() {
        return PinTrans;
    }

    /**
     * @param PinTrans the PinTrans to set
     */
    public void setPinTrans(String PinTrans) {
        this.PinTrans = PinTrans;
    }

    /**
     * @return the upenzpk
     */
    public String getUpenzpk() {
        return upenzpk;
    }

    /**
     * @param upenzpk the upenzpk to set
     */
    public void setUpenzpk(String upenzpk) {
        this.upenzpk = upenzpk;
    }

    /**
     * @return the upoperMethod
     */
    public String getUpoperMethod() {
        return upoperMethod;
    }

    /**
     * @param upoperMethod the upoperMethod to set
     */
    public void setUpoperMethod(String upoperMethod) {
        this.upoperMethod = upoperMethod;
    }

    /**
     * @return the upPinTrans
     */
    public String getUpPinTrans() {
        return upPinTrans;
    }

    /**
     * @param upPinTrans the upPinTrans to set
     */
    public void setUpPinTrans(String upPinTrans) {
        this.upPinTrans = upPinTrans;
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
     * @return the keepAliveStatus
     */
    public Map<Integer, String> getKeepAliveStatus() {
        return keepAliveStatus;
    }

    /**
     * @param keepAliveStatus the keepAliveStatus to set
     */
    public void setKeepAliveStatus(Map<Integer, String> keepAliveStatus) {
        this.keepAliveStatus = keepAliveStatus;
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

    /**
     * @return the upKeepAlive
     */
    public int getUpKeepAlive() {
        return upKeepAlive;
    }

    /**
     * @param upKeepAlive the upKeepAlive to set
     */
    public void setUpKeepAlive(int upKeepAlive) {
        this.upKeepAlive = upKeepAlive;
    }

    /**
     * @return the HidUpip
     */
    public String getHidUpip() {
        return HidUpip;
    }

    /**
     * @param HidUpip the HidUpip to set
     */
    public void setHidUpip(String HidUpip) {
        this.HidUpip = HidUpip;
    }

    /**
     * @return the hidUpPort
     */
    public String getHidUpPort() {
        return hidUpPort;
    }

    /**
     * @param hidUpPort the hidUpPort to set
     */
    public void setHidUpPort(String hidUpPort) {
        this.hidUpPort = hidUpPort;
    }

    /**
     * @return the HidUpsecIp
     */
    public String getHidUpsecIp() {
        return HidUpsecIp;
    }

    /**
     * @param HidUpsecIp the HidUpsecIp to set
     */
    public void setHidUpsecIp(String HidUpsecIp) {
        this.HidUpsecIp = HidUpsecIp;
    }

    /**
     * @return the HidUpsecPort
     */
    public String getHidUpsecPort() {
        return HidUpsecPort;
    }

    /**
     * @param HidUpsecPort the HidUpsecPort to set
     */
    public void setHidUpsecPort(String HidUpsecPort) {
        this.HidUpsecPort = HidUpsecPort;
    }

    /**
     * @return the HidUpName
     */
    public String getHidUpName() {
        return HidUpName;
    }

    /**
     * @param HidUpName the HidUpName to set
     */
    public void setHidUpName(String HidUpName) {
        this.HidUpName = HidUpName;
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
}
