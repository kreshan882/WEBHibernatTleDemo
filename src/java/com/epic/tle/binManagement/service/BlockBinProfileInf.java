/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.BinProfileBean;
import com.epic.tle.profile.bean.UserProfileInputBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */

public interface BlockBinProfileInf {
    
    public List<BinProfileBean> loadBinProfile(BinProfileBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertBlockBinProfile(BinProfileBean inputBean)throws Exception;
    
    public void getUpdateData(BinProfileBean inputBean)throws Exception;
    
    public boolean  deleteBin(BinProfileBean inputBean) throws Exception;
    
    public boolean updatebin(BinProfileBean inputBean) throws Exception;
    
    public BinProfileBean getPagePath(String page, BinProfileBean inputBean)throws Exception;
    
    public String GetResult(BinProfileBean inputBean) throws Exception;

}
