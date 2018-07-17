/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.RefTerminalBean;
import java.util.List;

/**
 *
 * @author chalaka_n
 */
public interface RefTerminalProfileInf {
    
    public List<RefTerminalBean> loadRefProfile(RefTerminalBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertRefProfile(RefTerminalBean inputBean)throws Exception;
    
    public void getUpdateData(RefTerminalBean inputBean)throws Exception;
    
    public boolean  deleteRefProfile(RefTerminalBean inputBean) throws Exception;
    
    public boolean updateRefProfile(RefTerminalBean inputBean) throws Exception;
    
    public RefTerminalBean getPagePath(String page, RefTerminalBean inputBean)throws Exception;
    
    public String GetResult(RefTerminalBean inputBean) throws Exception;
}
