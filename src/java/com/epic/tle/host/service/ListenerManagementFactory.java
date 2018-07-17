/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author chalaka_n
 */
public class ListenerManagementFactory {
    private HostManagementInf loginInf;

    public ListenerManagementFactory() {
        this.loginInf = new ListenerManageService();
    }

    public HostManagementInf getSessionService() {
        return loginInf;
    }
}
