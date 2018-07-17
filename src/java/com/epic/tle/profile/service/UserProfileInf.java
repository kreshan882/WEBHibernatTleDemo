/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.profile.service;

import com.epic.tle.profile.bean.UserProfileBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import java.util.List;

/**
 *
 * @author chalaka_n
 */
public interface UserProfileInf {
    public int insertUserProfile(UserProfileInputBean inputBean) throws Exception;
    public int insertDefaultUserProfilePrivilages(int profileid) throws Exception;
    public List<UserProfileBean> loadSearchUserProfile(UserProfileInputBean inputBean, String orderBy, int first, int max) throws Exception;
    public boolean userRoleDelete(UserProfileInputBean Bean) throws Exception;
    public boolean isAnyUserUsingThisProfile(UserProfileInputBean Bean) throws Exception;
    public void getmodulemap(UserProfileInputBean inputBean)throws Exception;
    public void getmodulePagemap(UserProfileInputBean inputBean)throws Exception;
    public void getPageTaskmap(UserProfileInputBean inputBean) throws Exception;

    public boolean alredyInsertProfile(String prifileName) throws Exception;

    public void insertUserProfileModule(int profileID, UserProfileInputBean input) throws Exception;

    public void getUpdateData(UserProfileInputBean inputBean)throws Exception;

    public boolean updateUserProfile(UserProfileInputBean inputBean)throws Exception;
    
    public UserProfileInputBean getPagePath(String page,UserProfileInputBean inputBean)throws Exception;
}
