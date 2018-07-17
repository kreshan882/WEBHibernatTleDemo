/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 *
 * @author chalaka_n
 */
public class ProcessingBean {
//    private int totalTime;
    private double totalTime;
    private int hostTime;
    private String datetime;
    private int tleTime;
    private int Hour;

    public int getHostTime() {
        return hostTime;
    }

    public void setHostTime(int hostTime) {
        this.hostTime = hostTime;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }


    public int getTleTime() {
        return tleTime;
    }

    public void setTleTime(int tleTime) {
        this.tleTime = tleTime;
    }

    /**
     * @return the totalTime
     */
    public double getTotalTime() {
        return totalTime;
    }

    /**
     * @param totalTime the totalTime to set
     */
    public void setTotalTime(double totalTime) {
        this.totalTime = totalTime;
    }

    /**
     * @return the Hour
     */
    public int getHour() {
        return Hour;
    }

    /**
     * @param Hour the Hour to set
     */
    public void setHour(int Hour) {
        this.Hour = Hour;
    }

   
    
     
}
