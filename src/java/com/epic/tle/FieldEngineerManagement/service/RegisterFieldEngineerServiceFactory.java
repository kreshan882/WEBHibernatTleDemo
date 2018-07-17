/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.login.service.LoginService;
import com.epic.tle.util.HibernateInit;

/**
 *
 * @author kasun_k
 */
public class RegisterFieldEngineerServiceFactory {
    private RegisterFieldEngineerServiceInf regEngInf;
    
    public RegisterFieldEngineerServiceFactory(){
        this.regEngInf =  new RegisterFieldEngineerService();
    }

    public RegisterFieldEngineerServiceInf getRegEngInf() {
        return regEngInf;
    }
    
    
}
