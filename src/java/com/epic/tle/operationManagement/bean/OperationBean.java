/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.bean;

/**
 *
 * @author chalaka_n
 */
public class OperationBean {
    private String operation;
    private String username;
    private String ip;
    private String message;

    public OperationBean(String operation, String username, String ip, String message) {
        this.operation = operation;
        this.username = username;
        this.ip = ip;
        this.message = message;
    }

    
    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    
}
