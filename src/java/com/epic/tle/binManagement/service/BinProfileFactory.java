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
public class BinProfileFactory {
    
    private final BlockBinProfileInf blockBinProfileInf;
    
    public BinProfileFactory() {
        this.blockBinProfileInf = new BlockBinProfileService();
    }

    /**
     * @return the blockBinProfileInf
     */
    public BlockBinProfileInf getBlockBinProfileInf() {
        return blockBinProfileInf;
    }
    
    
       
}
