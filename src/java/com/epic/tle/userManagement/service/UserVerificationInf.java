/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.userManagement.service;

import com.epic.tle.userManagement.bean.UserVerificationBean;
import java.util.List;

/**
 *
 * @author danushka_r
 */
public interface UserVerificationInf {
    public List<UserVerificationBean> loadUsers(UserVerificationBean inputBean, int max, int first, String orderBy) throws Exception ;
    public boolean activeUser(String userName) throws Exception;
    public boolean deleteUser(String userName) throws Exception;
    
    
}
