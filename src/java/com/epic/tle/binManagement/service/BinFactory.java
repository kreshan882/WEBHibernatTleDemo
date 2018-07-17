/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author thilina_t
 */
public class BinFactory {

    private final BinProfileInf binProfileInf;
    
    public BinFactory(){
        this.binProfileInf =  new BinProfileService();
    }

    /**
     * @return the binProfileInf
     */
    public BinProfileInf getBinProfileInf() {
        return binProfileInf;
    }
    
}
