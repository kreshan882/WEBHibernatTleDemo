/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author chalaka_n
 */
public class NonFunctionTerminaFactory {
     private final RegisterTerminalServiceInf registerTerminalServiceInf;

    public NonFunctionTerminaFactory() {
        this.registerTerminalServiceInf = new NonFunctionTerminalService();
    }

    public RegisterTerminalServiceInf getRegisterTerminalServiceInf() {
        return registerTerminalServiceInf;
    }

    
}
