/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author thilina_t
 */
public class SystemProcessTimeFactory {

    private SystemProcessTimeFactoryInf factory;

    public SystemProcessTimeFactory() {
        this.factory = new SystemProcessService();

    }

    public SystemProcessTimeFactoryInf getSessionService() {
        return factory;
    }

}
