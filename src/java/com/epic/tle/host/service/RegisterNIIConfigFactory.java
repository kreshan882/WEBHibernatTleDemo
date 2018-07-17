/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.binManagement.service.*;
import com.epic.tle.util.HibernateInit;

/**
 *
 * @author thilina_t
 */
public class RegisterNIIConfigFactory {

    private final RegisterNIIConfigInf registerNIIConfigInf;
    
    public RegisterNIIConfigFactory(){
        this.registerNIIConfigInf =  new NIIConfigService();
    }

    /**
     * @return the binProfileInf
     */
    public RegisterNIIConfigInf getRegisterNIIConfigInf() {
        return registerNIIConfigInf;
    }
    
}
