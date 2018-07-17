/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.profile.service.*;
import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author chalaka_n
 */
public class SessionConfFactory {
    private ServerConfInf factory;

    public SessionConfFactory() {
        this.factory = new SessionConfigurationService();
    }

    public ServerConfInf getSessionService() {
        return factory;
    }

}
