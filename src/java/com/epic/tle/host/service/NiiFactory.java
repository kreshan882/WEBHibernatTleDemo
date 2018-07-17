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
public class NiiFactory {

    private final NiiInf niiInf;
    
    public NiiFactory(){
        this.niiInf =  new NiiService();
    }

    /**
     * @return the binProfileInf
     */
    public NiiInf getNiiInf() {
        return niiInf;
    }
    
}
