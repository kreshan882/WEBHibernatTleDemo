/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.servermanager.bean.SMSInfoBean;
import java.util.List;

/**
 *
 * @author ridmi_g
 */
public interface SMSInfoInf {
    
     public List<SMSInfoBean> loadInfo(SMSInfoBean inputBean, int max, int first, String orderBy) throws Exception;
   
    public boolean insertInfo(SMSInfoBean inputBean)throws Exception;
   
    public boolean  deleteInfo(SMSInfoBean inputBean) throws Exception;
 
    public String GetResult(SMSInfoBean inputBean) throws Exception;
    
    public String GetEmailResult(SMSInfoBean inputBean) throws Exception;
    
}
