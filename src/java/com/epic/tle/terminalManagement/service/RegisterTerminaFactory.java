/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author danushka_r
 */
public class RegisterTerminaFactory {
     private final RegisterTerminalServiceInf registerTerminalServiceInf;

    public RegisterTerminaFactory() {
        this.registerTerminalServiceInf = new RegisterTerminalService();
    }

    public RegisterTerminalServiceInf getRegisterTerminalServiceInf() {
        return registerTerminalServiceInf;
    }

    
}
