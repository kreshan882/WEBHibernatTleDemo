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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class KeyInjectionBean {
    
    private String bankaname;
    private String terminalid;
    private String merchantname;
    private String mek;
    private String issuedDate;
    private String keyid;
    private String tmk;
    private String filename;        
    
    
    private String terminalKI;
    private String terminalKM;

    private String message;
    private boolean successmsg;
    
    
    //jesper report
    Map parameterMap = new HashMap();
    List reportdatalist = new ArrayList();

    public String getBankaname() {
        return bankaname;
    }

    public boolean isSuccessmsg() {
        return successmsg;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
    

    public void setSuccessmsg(boolean successmsg) {
        this.successmsg = successmsg;
    }
    

    public void setBankaname(String bankaname) {
        this.bankaname = bankaname;
    }

    public String getTerminalid() {
        return terminalid;
    }

    public void setTerminalid(String terminalid) {
        this.terminalid = terminalid;
    }

    public String getMerchantname() {
        return merchantname;
    }

    public void setMerchantname(String merchantname) {
        this.merchantname = merchantname;
    }

    public String getMek() {
        return mek;
    }

    public void setMek(String mek) {
        this.mek = mek;
    }

    public String getIssuedDate() {
        return issuedDate;
    }

    public void setIssuedDate(String issuedDate) {
        this.issuedDate = issuedDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    
    public String getKeyid() {
        return keyid;
    }

    public void setKeyid(String keyid) {
        this.keyid = keyid;
    }

    public String getTmk() {
        return tmk;
    }

    public void setTmk(String tmk) {
        this.tmk = tmk;
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

    public boolean isVdownload() {
        return vdownload;
    }

    public void setVdownload(boolean vdownload) {
        this.vdownload = vdownload;
    }

    public boolean isVresetpass() {
        return vresetpass;
    }

    public void setVresetpass(boolean vresetpass) {
        this.vresetpass = vresetpass;
    }
    
      /*-------for access control-----------*/
    private boolean vadd;
    private boolean vupdate;
    private boolean vdelete;
    private boolean vdownload;
    private boolean vresetpass;
    //    private boolean vactdct;
    /*-------for access control-----------*/


    public Map getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map parameterMap) {
        this.parameterMap = parameterMap;
    }

    public List getReportdatalist() {
        return reportdatalist;
    }

    
    public void setReportdatalist(List reportdatalist) {
        this.reportdatalist = reportdatalist;
    }
    
    
    public String getTerminalKI() {
        return terminalKI;
    }

    public void setTerminalKI(String terminalKI) {
        this.terminalKI = terminalKI;
    }

    public String getTerminalKM() {
        return terminalKM;
    }

    public void setTerminalKM(String terminalKM) {
        this.terminalKM = terminalKM;
    }
    
    
    
}

