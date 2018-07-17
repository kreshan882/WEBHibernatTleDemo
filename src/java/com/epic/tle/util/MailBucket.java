/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import javax.mail.internet.InternetAddress;

/**
 *
 * @author gayan_s
 */
public class MailBucket {

    private String to_mail_address;
    private String from_mail_address;
    private String message;
    private String subject;
    private InternetAddress[] addresses;
    private boolean isHtml;
    private boolean debug;

    public MailBucket() {
    }

    public String getTo_mail_address() {
        return to_mail_address;
    }

    public void setTo_mail_address(String to_mail_address) {
        this.to_mail_address = to_mail_address;
    }

    public String getFrom_mail_address() {
        return from_mail_address;
    }

    public void setFrom_mail_address(String from_mail_address) {
        this.from_mail_address = from_mail_address;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public InternetAddress[] getAddresses() {
        return addresses;
    }

    public void setAddresses(InternetAddress[] addresses) {
        this.addresses = addresses;
    }

    public boolean isIsHtml() {
        return isHtml;
    }

    public void setIsHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }

    public boolean isDebug() {
        return debug;
    }

    public void setDebug(boolean debug) {
        this.debug = debug;
    }

    @Override
    public String toString() {
        return "MailBucket{" + "to_mail_address=" + to_mail_address + ", from_mail_address=" + from_mail_address + ", message=" + message + ", subject=" + subject + ", addresses=" + addresses + ", isHtml=" + isHtml + ", debug=" + debug + '}';
    }

    public MailBucket(String to_mail_address, String from_mail_address, String message, String subject, InternetAddress[] addresses, boolean isHtml, boolean debug) {
        this.to_mail_address = to_mail_address;
        this.from_mail_address = from_mail_address;
        this.message = message;
        this.subject = subject;
        this.addresses = addresses;
        this.isHtml = isHtml;
        this.debug = debug;
    }

  
}
