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
public class SMSInfoFactory {
    private final SMSInfoInf smsInfoInf;

   public SMSInfoFactory(){
        this.smsInfoInf =  new SMSInfoService();
    }

    public SMSInfoInf getSmsInfoInf() {
        return smsInfoInf;
    }
   
    
}
