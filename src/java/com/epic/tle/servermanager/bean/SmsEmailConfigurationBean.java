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
public class SmsEmailConfigurationBean {
    
    private String smsip;
    private String smsport;
    private String smstimeout;
    private String emailip;
    private String emailport;
    private String emaildomain;

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
    
    
    
    public String getSmsip() {
        return smsip;
    }

    public void setSmsip(String smsip) {
        this.smsip = smsip;
    }

    public String getSmsport() {
        return smsport;
    }

    public void setSmsport(String smsport) {
        this.smsport = smsport;
    }

    public String getSmstimeout() {
        return smstimeout;
    }

    public void setSmstimeout(String smstimeout) {
        this.smstimeout = smstimeout;
    }

    public String getEmailip() {
        return emailip;
    }

    public void setEmailip(String emailip) {
        this.emailip = emailip;
    }

    public String getEmailport() {
        return emailport;
    }

    public void setEmailport(String eamilport) {
        this.emailport = eamilport;
    }

    public String getEmaildomain() {
        return emaildomain;
    }

    public void setEmaildomain(String emaildomain) {
        this.emaildomain = emaildomain;
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
