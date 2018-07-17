/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.servermanager.service.ResponseConfigurationService;
import com.epic.tle.servermanager.service.ServerConfInf;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author chalaka_n
 */
public class SystemTnxFailingFactory {
    private SystemTnxFailingFactoryInf factory;

    public SystemTnxFailingFactory() {
        this.factory =  new SystemTnxFailingService();

    }

    public SystemTnxFailingFactoryInf getSessionService() {
        return factory;
    }
}
