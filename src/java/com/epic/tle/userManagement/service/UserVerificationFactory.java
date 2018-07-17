/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author danushka_r
 */
public class UserVerificationFactory {
    private final UserVerificationInf userVerificationInf;
    
    public UserVerificationFactory(){
        this.userVerificationInf = new UserVerificationService();
    }

    public UserVerificationInf getUserVerificationInf() {
        return userVerificationInf;
    }

    
}
