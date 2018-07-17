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
public class LocalBinFactory {

    private final LocalBinInf localBinInf;

    public LocalBinFactory() {
        this.localBinInf = new LocalBinService();
    }

    public LocalBinInf getLocalBinInf() {
        return localBinInf;
    }

}
