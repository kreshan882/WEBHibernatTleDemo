/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.bean;

import java.util.Date;

/**
 *
 * @author dimuthu_h
 */
public class PasswordPolicyBean {
    
    private int sid;
    private String max_len;
    private String min_len;
    private String allowspecialcharacters;
    private String min_spcl_chars;
    private String max_spcl_chars;
    private String min_upercase;
    private String min_lowcase;
    private String min_numerics;
    private Date lasupdatedate;
    
    private boolean reset;
    private boolean update;

    @Override
    public String toString() {
        return "PasswordPolicyBean{" + "sid=" + sid + ", max_len=" + max_len + ", min_len=" + min_len + ", allowspecialcharacters=" + allowspecialcharacters + ", min_spcl_chars=" + min_spcl_chars + ", max_spcl_chars=" + max_spcl_chars + ", min_upercase=" + min_upercase + ", min_lowcase=" + min_lowcase + ", min_numerics=" + min_numerics + ", lasupdatedate=" + lasupdatedate + ", reset=" + reset + ", update=" + update + '}';
    }

    public boolean isReset() {
        return reset;
    }

    public void setReset(boolean reset) {
        this.reset = reset;
    }

    public boolean isUpdate() {
        return update;
    }

    public void setUpdate(boolean update) {
        this.update = update;
    }

    public int getSid() {
        return sid;
    }

    public void setSid(int sid) {
        this.sid = sid;
    }

    public String getMax_len() {
        return max_len;
    }

    public void setMax_len(String max_len) {
        this.max_len = max_len;
    }

    public String getMin_len() {
        return min_len;
    }

    public void setMin_len(String min_len) {
        this.min_len = min_len;
    }

    public String getAllowspecialcharacters() {
        return allowspecialcharacters;
    }

    public void setAllowspecialcharacters(String allowspecialcharacters) {
        this.allowspecialcharacters = allowspecialcharacters;
    }

    public String getMin_spcl_chars() {
        return min_spcl_chars;
    }

    public void setMin_spcl_chars(String min_spcl_chars) {
        this.min_spcl_chars = min_spcl_chars;
    }

    public String getMax_spcl_chars() {
        return max_spcl_chars;
    }

    public void setMax_spcl_chars(String max_spcl_chars) {
        this.max_spcl_chars = max_spcl_chars;
    }

    public String getMin_upercase() {
        return min_upercase;
    }

    public void setMin_upercase(String min_upercase) {
        this.min_upercase = min_upercase;
    }

    public String getMin_lowcase() {
        return min_lowcase;
    }

    public void setMin_lowcase(String min_lowcase) {
        this.min_lowcase = min_lowcase;
    }

    public String getMin_numerics() {
        return min_numerics;
    }

    public void setMin_numerics(String min_numerics) {
        this.min_numerics = min_numerics;
    }

    public Date getLasupdatedate() {
        return lasupdatedate;
    }

    public void setLasupdatedate(Date lasupdatedate) {
        this.lasupdatedate = lasupdatedate;
    }
    

}