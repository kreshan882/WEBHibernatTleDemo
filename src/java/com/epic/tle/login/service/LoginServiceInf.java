/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.service;

import com.epic.tle.login.bean.HomeValues;
import com.epic.tle.login.bean.ModuleBean;
import com.epic.tle.login.bean.PageBean;
import com.epic.tle.login.bean.TaskBean;
import com.epic.tle.login.bean.UserLoginBean;
import com.epic.tle.userManagement.bean.ChangePasswordBean;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author chalaka_n
 */
public interface LoginServiceInf {
    public UserLoginBean getDbUserPassword(UserLoginBean userLoginBean) throws Exception;
    public boolean checkUsername(UserLoginBean userLoginBean) throws Exception;
    public boolean varifilogin(UserLoginBean userLoginBean) throws Exception ;
    public List<String> getUserprofilePageidList(int dbUserprofile)   throws Exception ;
    public Map<ModuleBean, List<PageBean>> getModulePageByUser(int dBuserappCode) throws Exception;
    public void getHomeValues(HomeValues homeValues) throws Exception;
    public Map<ModuleBean,List<PageBean>> getModulePageTaskByUser(int dBuserappCode);
    public HashMap<String, List<TaskBean>> getAllPageTask(int profileID) throws Exception;
    public void getProcessingData(UserLoginBean userLoginBean)throws Exception;
    public void getFilterData(UserLoginBean userLoginBean)throws Exception;
    public boolean updateUserStatus(int status,int userId)throws Exception;
    public boolean updateUserStatusByUserName(int status,String userName)throws Exception;
    public boolean updateRemainigPasswordAttempt(int value , int userId)throws Exception;
    public boolean updateRemainigPasswordAttemptByUserName(int value , String userName)throws Exception;
    public boolean updateLastPasswordChangeDate(int userId)throws Exception;
    public boolean updateLastPasswordChangeDateByUsername(String userName)throws Exception;
    public boolean updateLastRawUpdateDateTime(int userId)throws Exception;
    public int getRemainigPasswordAttempt(int userId)throws Exception;
    public boolean validateOldPassword(ChangePasswordBean changePasswordBean,String username) throws Exception;
    public boolean updatePassword(ChangePasswordBean changePasswordBean,String username) throws Exception;
    public boolean updateLastPaswwordResetDate(String userName)throws Exception;
//    public void inactiveBindStatus()throws Exception;
}
