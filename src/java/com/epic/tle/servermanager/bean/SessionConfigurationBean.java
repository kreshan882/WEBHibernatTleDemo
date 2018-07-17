/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.bean;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author dimuthu_h
 */
public class SessionConfigurationBean {

    private String max_pin_counter;
    private String host_pro_timeout;
    private String log_fname;
    private String pkt_size_listeners;
    private String pkt_size_channels;
    private String monitor_ip;
    private String monitor_port;
    private String monitor_timeout;
    private String channel_timeout;
    private String htest_time_period;
    private String replyAtchLvl;
    private String altNotificationStatus;
    private String txnFailNotificationStatus;
    private String logLevel;
    private String hsizeListeners;
    private String hsizeChannels;
    private String requestTPDU;
    private String responseTPDU;
    private String tpduModifyOption;
    private String tpduModifyPosition;

    private Map<String, String> replyAtchLvlMap = new HashMap<String, String>();
    private Map<String, String> altNotificationStatusMap = new HashMap<String, String>();
    private Map<String, String> txnFailNotificationStatusMap = new HashMap<String, String>();
    private Map<String, String> logLevelMap = new HashMap<String, String>();
    private Map<String, String> hsizeListenersMap = new HashMap<String, String>();
    private Map<String, String> hsizeChannelsMap = new HashMap<String, String>();
    private Map<String, String> requestTPDUMap = new HashMap<String, String>();
    private Map<String, String> responseTPDUMap = new HashMap<String, String>();
    private Map<String, String> tpduModifyOptionMap = new HashMap<String, String>();
    private Map<String, String> tpduModifyPositionMap = new HashMap<String, String>();

    
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
  
    
    
    public String getMax_pin_counter() {
        return max_pin_counter;
    }

    public void setMax_pin_counter(String max_pin_counter) {
        this.max_pin_counter = max_pin_counter;
    }

    public String getHost_pro_timeout() {
        return host_pro_timeout;
    }

    public void setHost_pro_timeout(String host_pro_timeout) {
        this.host_pro_timeout = host_pro_timeout;
    }

    public String getLog_fname() {
        return log_fname;
    }

    public void setLog_fname(String log_fname) {
        this.log_fname = log_fname;
    }

    public String getPkt_size_listeners() {
        return pkt_size_listeners;
    }

    public void setPkt_size_listeners(String pkt_size_listeners) {
        this.pkt_size_listeners = pkt_size_listeners;
    }

    public String getPkt_size_channels() {
        return pkt_size_channels;
    }

    public void setPkt_size_channels(String pkt_size_channels) {
        this.pkt_size_channels = pkt_size_channels;
    }

    public String getMonitor_ip() {
        return monitor_ip;
    }

    public void setMonitor_ip(String monitor_ip) {
        this.monitor_ip = monitor_ip;
    }

    public String getMonitor_port() {
        return monitor_port;
    }

    public void setMonitor_port(String monitor_port) {
        this.monitor_port = monitor_port;
    }

    public String getMonitor_timeout() {
        return monitor_timeout;
    }

    public void setMonitor_timeout(String monitor_timeout) {
        this.monitor_timeout = monitor_timeout;
    }

    public String getChannel_timeout() {
        return channel_timeout;
    }

    public void setChannel_timeout(String channel_timeout) {
        this.channel_timeout = channel_timeout;
    }

    public String getHtest_time_period() {
        return htest_time_period;
    }

    public void setHtest_time_period(String htest_time_period) {
        this.htest_time_period = htest_time_period;
    }

    public String getReplyAtchLvl() {
        return replyAtchLvl;
    }

    public void setReplyAtchLvl(String replyAtchLvl) {
        this.replyAtchLvl = replyAtchLvl;
    }

    public String getAltNotificationStatus() {
        return altNotificationStatus;
    }

    public void setAltNotificationStatus(String altNotificationStatus) {
        this.altNotificationStatus = altNotificationStatus;
    }

    public String getTxnFailNotificationStatus() {
        return txnFailNotificationStatus;
    }

    public void setTxnFailNotificationStatus(String txnFailNotificationStatus) {
        this.txnFailNotificationStatus = txnFailNotificationStatus;
    }

    public String getLogLevel() {
        return logLevel;
    }

    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    public String getHsizeListeners() {
        return hsizeListeners;
    }

    public void setHsizeListeners(String hsizeListeners) {
        this.hsizeListeners = hsizeListeners;
    }

    public String getHsizeChannels() {
        return hsizeChannels;
    }

    public void setHsizeChannels(String hsizeChannels) {
        this.hsizeChannels = hsizeChannels;
    }

    public String getRequestTPDU() {
        return requestTPDU;
    }

    public void setRequestTPDU(String requestTPDU) {
        this.requestTPDU = requestTPDU;
    }

    public String getResponseTPDU() {
        return responseTPDU;
    }

    public void setResponseTPDU(String responseTPDU) {
        this.responseTPDU = responseTPDU;
    }

    public String getTpduModifyOption() {
        return tpduModifyOption;
    }

    public void setTpduModifyOption(String tpduModifyOption) {
        this.tpduModifyOption = tpduModifyOption;
    }

    public String getTpduModifyPosition() {
        return tpduModifyPosition;
    }

    public void setTpduModifyPosition(String tpduModifyPosition) {
        this.tpduModifyPosition = tpduModifyPosition;
    }

    public Map<String, String> getReplyAtchLvlMap() {
        return replyAtchLvlMap;
    }

    public void setReplyAtchLvlMap(Map<String, String> replyAtchLvlMap) {
        this.replyAtchLvlMap = replyAtchLvlMap;
    }

    public Map<String, String> getAltNotificationStatusMap() {
        return altNotificationStatusMap;
    }

    public void setAltNotificationStatusMap(Map<String, String> altNotificationStatusMap) {
        this.altNotificationStatusMap = altNotificationStatusMap;
    }

    public Map<String, String> getTxnFailNotificationStatusMap() {
        return txnFailNotificationStatusMap;
    }

    public void setTxnFailNotificationStatusMap(Map<String, String> txnFailNotificationStatusMap) {
        this.txnFailNotificationStatusMap = txnFailNotificationStatusMap;
    }

    public Map<String, String> getLogLevelMap() {
        return logLevelMap;
    }

    public void setLogLevelMap(Map<String, String> logLevelMap) {
        this.logLevelMap = logLevelMap;
    }

    public Map<String, String> getHsizeListenersMap() {
        return hsizeListenersMap;
    }

    public void setHsizeListenersMap(Map<String, String> hsizeListenersMap) {
        this.hsizeListenersMap = hsizeListenersMap;
    }

    public Map<String, String> getHsizeChannelsMap() {
        return hsizeChannelsMap;
    }

    public void setHsizeChannelsMap(Map<String, String> hsizeChannelsMap) {
        this.hsizeChannelsMap = hsizeChannelsMap;
    }

    public Map<String, String> getRequestTPDUMap() {
        return requestTPDUMap;
    }

    public void setRequestTPDUMap(Map<String, String> requestTPDUMap) {
        this.requestTPDUMap = requestTPDUMap;
    }

    public Map<String, String> getResponseTPDUMap() {
        return responseTPDUMap;
    }

    public void setResponseTPDUMap(Map<String, String> responseTPDUMap) {
        this.responseTPDUMap = responseTPDUMap;
    }

    public Map<String, String> getTpduModifyOptionMap() {
        return tpduModifyOptionMap;
    }

    public void setTpduModifyOptionMap(Map<String, String> tpduModifyOptionMap) {
        this.tpduModifyOptionMap = tpduModifyOptionMap;
    }

    public Map<String, String> getTpduModifyPositionMap() {
        return tpduModifyPositionMap;
    }

    public void setTpduModifyPositionMap(Map<String, String> tpduModifyPositionMap) {
        this.tpduModifyPositionMap = tpduModifyPositionMap;
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
