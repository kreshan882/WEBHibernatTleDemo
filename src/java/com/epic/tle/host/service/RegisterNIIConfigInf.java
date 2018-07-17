/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.binManagement.service.*;
import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.host.bean.NIIConfigBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import com.epic.tle.util.CommonAccessInterface;
import java.util.List;

/**
 *
 * @author thilina_t
 */

public interface RegisterNIIConfigInf extends CommonAccessInterface{
    
    
    public List<NIIConfigBean> loadNII(NIIConfigBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertNIIGroup(NIIConfigBean inputBean)throws Exception;
    
    public void getUpdateData(NIIConfigBean inputBean)throws Exception;
    
    public boolean  deleteNII(NIIConfigBean inputBean) throws Exception;
    
    public boolean updateNII(NIIConfigBean inputBean) throws Exception;
    
    public NIIConfigBean getPagePath(String page, NIIConfigBean inputBean) throws Exception;
    
    public String GetResult(NIIConfigBean inputBean)throws Exception;
    

}
