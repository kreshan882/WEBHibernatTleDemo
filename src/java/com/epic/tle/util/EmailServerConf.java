/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

/**
 *
 * @author gayan_s
 */
public class EmailServerConf {

    private String user_name;
    private String password;
    private String url;
    private String host;
    private int port;
    private int bank_code;

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public EmailServerConf() {
    }

    public EmailServerConf(String user_name, String password, String url, String host, int port, int bank_code) {
        this.user_name = user_name;
        this.password = password;
        this.url = url;
        this.host = host;
        this.port = port;
        this.bank_code = bank_code;
    }

    @Override
    public String toString() {
        return "EmailServerConf{" + "user_name=" + user_name + ", password=" + password + ", url=" + url + ", host=" + host + ", port=" + port + ", bank_code=" + bank_code + '}';
    }

   

    
    public int getBank_code() {
        return bank_code;
    }

    public void setBank_code(int bank_code) {
        this.bank_code = bank_code;
    }

}
