/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.bean;

/**
 *
 * @author kreshan
 */
public class HomeValues {
    private String sysFailCount;
    private String sysAlertCount;
    private boolean sysRunStatus;
    private boolean runNode;

    public boolean isRunNode() {
        return runNode;
    }

    public void setRunNode(boolean runNode) {
        this.runNode = runNode;
    }

    public String getSysAlertCount() {
        return sysAlertCount;
    }

    public void setSysAlertCount(String sysAlertCount) {
        this.sysAlertCount = sysAlertCount;
    }

    public String getSysFailCount() {
        return sysFailCount;
    }

    public void setSysFailCount(String sysFailCount) {
        this.sysFailCount = sysFailCount;
    }

    public boolean isSysRunStatus() {
        return sysRunStatus;
    }

    public void setSysRunStatus(boolean sysRunStatus) {
        this.sysRunStatus = sysRunStatus;
    }
    
    
    
}
