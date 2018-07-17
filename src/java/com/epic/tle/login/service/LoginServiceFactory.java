/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.service;

import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author chalaka_n
 */
public class LoginServiceFactory {
    private LoginServiceInf loginInf;

    public LoginServiceFactory() {
        this.loginInf = new LoginService();
    }

    public LoginServiceInf getLoginService() {
        return loginInf;
    }
    
    
}
