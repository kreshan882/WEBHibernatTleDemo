/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

/**
 *
 * @author ridmi_g
 */
public class SMSProfileFactory {

    private final SMSProfileInf SMSProfileInf;

    public SMSProfileFactory(SMSProfileInf SMSProfileInf) {
        this.SMSProfileInf = SMSProfileInf;
    }

    public SMSProfileFactory() {
       
        this.SMSProfileInf = new SMSProfileService();
    }

    /**
     * @return the SMSProfileInf
     */
    public SMSProfileInf getSMSProfileInf() {
        return SMSProfileInf;
    }

}
