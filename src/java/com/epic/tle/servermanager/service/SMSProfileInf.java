/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.servermanager.bean.SMSProfileBean;
import java.util.List;

/**
 *
 * @author ridmi_g
 */
public interface SMSProfileInf {
    
    public List<SMSProfileBean> loadSMSProfile(SMSProfileBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertSMSProfile(SMSProfileBean inputBean)throws Exception;
  
    public void getUpdateData(SMSProfileBean inputBean)throws Exception;
   
    public boolean  deleteProfile(SMSProfileBean inputBean) throws Exception;
   
    public boolean updateprofile(SMSProfileBean inputBean) throws Exception;
    
    public SMSProfileBean getPagePath(String page, SMSProfileBean inputBean) throws Exception;
    
    public String GetResult(SMSProfileBean inputBean) throws Exception;
    
}
