/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.profile.service.*;
import com.epic.tle.profile.bean.UserProfileBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.servermanager.bean.HSMConfigurationBean;
import com.epic.tle.servermanager.bean.PortConfigurationDataBean;
import com.epic.tle.servermanager.bean.PortConfigurationInputBean;
import com.epic.tle.servermanager.bean.ResponseConfigurationBean;
import com.epic.tle.servermanager.bean.ServerConfigurationBean;
import com.epic.tle.servermanager.bean.SessionConfigurationBean;
import com.epic.tle.servermanager.bean.SmsEmailConfigurationBean;
import com.epic.tle.util.CommonAccessInterface;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.Configurations;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public interface ServerConfInf extends CommonAccessInterface{
    public SessionConfigurationBean viewSessionConfDetails(SessionConfigurationBean inputBean) throws Exception;
    public boolean updateSessionConfDetails(SessionConfigurationBean inputBean) throws Exception;
    public SessionConfigurationBean getPagePath(String page, SessionConfigurationBean inputBean) throws Exception;
    
    public ResponseConfigurationBean viewResponseConfDetails(ResponseConfigurationBean inputBean) throws Exception;
    public boolean updateResponseConf(ResponseConfigurationBean inputBean) throws Exception;
    public boolean updateResponseConf(SessionConfigurationBean inputBean) throws Exception;
    public ResponseConfigurationBean getPagePath(String page, ResponseConfigurationBean inputBean) throws Exception;
    
    public ServerConfigurationBean viewServerConfDetails(ServerConfigurationBean inputBean) throws Exception;
    public boolean updateServerConf(ServerConfigurationBean inputBean) throws Exception;
    public ServerConfigurationBean getPagePath(String page, ServerConfigurationBean inputBean) throws Exception;
    
    public List<PortConfigurationDataBean> loadPortConfDetails(PortConfigurationInputBean inputBean, int max, int first, String orderBy) throws Exception;
    public void findPortDetails(PortConfigurationInputBean inputBean) throws Exception;
    public boolean updatePortConfiguration(PortConfigurationInputBean inputBean) throws Exception;
    public PortConfigurationInputBean getPagePath(String page, PortConfigurationInputBean inputBean) throws Exception;
    
    public SmsEmailConfigurationBean viewSmsEmailConfDetails(SmsEmailConfigurationBean inputBean) throws Exception;
    public boolean updateSmsEmailConf(SmsEmailConfigurationBean inputBean) throws Exception;
    public SmsEmailConfigurationBean getPagePath(String page, SmsEmailConfigurationBean inputBean) throws Exception;
    
    public HSMConfigurationBean viewTextContent(HSMConfigurationBean inpuBean) throws Exception;
    public boolean updateHSMConf(HSMConfigurationBean inputBean) throws Exception;
    public HSMConfigurationBean getPagePath(String page, HSMConfigurationBean inputBean) throws Exception;

    public Map<Integer,String> getlogLevelMap() throws Exception;
    public Map<Integer,String> getAttackLevel() throws Exception;

    public void loadIniconfig(ServerConfigurationBean inputBean) throws Exception;


}
