/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author chalaka_n
 */
public class RefTerminalProfileFactory {
    private final RefTerminalProfileInf refTerminalProfileInf;
    
    public RefTerminalProfileFactory() {
        this.refTerminalProfileInf =  new RefTerminalProfileService();
    }

    public RefTerminalProfileInf getRefTerminalProfileInf() {
        return refTerminalProfileInf;
    }
    
    
}
