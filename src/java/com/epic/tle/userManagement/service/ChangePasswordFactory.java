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
public class ChangePasswordFactory {
    private final ChangePasswordServiceInf changePasswordServiceInf;

    public ChangePasswordFactory() {
        this.changePasswordServiceInf = new ChangePasswordService();
    }

    public ChangePasswordServiceInf getChangePasswordServiceInf() {
        return changePasswordServiceInf;
    }
    
    
}
