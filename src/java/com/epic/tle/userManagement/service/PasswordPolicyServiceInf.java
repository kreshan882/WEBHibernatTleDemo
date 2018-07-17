/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.userManagement.bean.PasswordPolicyBean;

/**
 *
 * @author danushka_r
 */
public interface PasswordPolicyServiceInf {
    public PasswordPolicyBean viewPasswordPolicyDetails(PasswordPolicyBean inputbean) throws Exception;
    public boolean updatePwdPolicy(PasswordPolicyBean pwdbean) throws Exception;
    
}
