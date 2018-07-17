/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.userManagement.bean.ChangePasswordBean;

/**
 *
 * @author danushka_r
 */
public interface ChangePasswordServiceInf {
    public boolean validateOldPassword(ChangePasswordBean changePasswordBean) throws Exception;
    public boolean updatePassword(ChangePasswordBean changePasswordBean) throws Exception;
    
}
