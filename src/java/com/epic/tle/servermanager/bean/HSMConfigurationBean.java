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
public class HSMConfigurationBean {

    private String mdl;
    private String batch;
    private String mdate;
    private String srlNo;
    private String adpClock;
    private String brdRev;
    private String firmVersion;
    private String cprVersion;
    private String hwstatus;
    private String freeMemo;
    private String total;
    private String secuMode;
    private String transMode;
    private String fmsupport;
    private String fmstatus;
    private String osesCount;
    private String nofSlots;
    private String rtcAdj;
    private String hwinfo;
    private String tknPin;

    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    
    private String PageCode="";
    
         //***************Working Path*************
    private String Module="";
    private String Section="";

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

    public String getMdl() {
        return mdl;
    }

    public void setMdl(String mdl) {
        this.mdl = mdl;
    }

    public String getBatch() {
        return batch;
    }

    public void setBatch(String batch) {
        this.batch = batch;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getSrlNo() {
        return srlNo;
    }

    public void setSrlNo(String srlNo) {
        this.srlNo = srlNo;
    }

    public String getAdpClock() {
        return adpClock;
    }

    public void setAdpClock(String adpClock) {
        this.adpClock = adpClock;
    }

    public String getBrdRev() {
        return brdRev;
    }

    public void setBrdRev(String brdRev) {
        this.brdRev = brdRev;
    }

    public String getFirmVersion() {
        return firmVersion;
    }

    public void setFirmVersion(String firmVersion) {
        this.firmVersion = firmVersion;
    }

    public String getCprVersion() {
        return cprVersion;
    }

    public void setCprVersion(String cprVersion) {
        this.cprVersion = cprVersion;
    }

    public String getHwstatus() {
        return hwstatus;
    }

    public void setHwstatus(String hwstatus) {
        this.hwstatus = hwstatus;
    }

    public String getFreeMemo() {
        return freeMemo;
    }

    public void setFreeMemo(String freeMemo) {
        this.freeMemo = freeMemo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSecuMode() {
        return secuMode;
    }

    public void setSecuMode(String secuMode) {
        this.secuMode = secuMode;
    }

    public String getTransMode() {
        return transMode;
    }

    public void setTransMode(String transMode) {
        this.transMode = transMode;
    }

    public String getFmsupport() {
        return fmsupport;
    }

    public void setFmsupport(String fmsupport) {
        this.fmsupport = fmsupport;
    }

    public String getFmstatus() {
        return fmstatus;
    }

    public void setFmstatus(String fmstatus) {
        this.fmstatus = fmstatus;
    }

    public String getOsesCount() {
        return osesCount;
    }

    public void setOsesCount(String osesCount) {
        this.osesCount = osesCount;
    }

    public String getNofSlots() {
        return nofSlots;
    }

    public void setNofSlots(String nofSlots) {
        this.nofSlots = nofSlots;
    }

    public String getRtcAdj() {
        return rtcAdj;
    }

    public void setRtcAdj(String rtcAdj) {
        this.rtcAdj = rtcAdj;
    }

    public String getHwinfo() {
        return hwinfo;
    }

    public void setHwinfo(String hwinfo) {
        this.hwinfo = hwinfo;
    }

    public String getTknPin() {
        return tknPin;
    }

    public void setTknPin(String tknPin) {
        this.tknPin = tknPin;
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

}
