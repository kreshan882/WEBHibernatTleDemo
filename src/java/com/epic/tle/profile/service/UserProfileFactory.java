/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.profile.service;

import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;

/**
 *
 * @author chalaka_n
 */
public class UserProfileFactory {
    private UserProfileInf factory;

    public UserProfileFactory() {
        this.factory = new UserProfileService();
    }

    public UserProfileInf getProfileService() {
        return factory;
    }

}
