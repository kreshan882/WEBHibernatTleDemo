/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.servermanager.service;

import com.epic.tle.servermanager.bean.FilterMessageBean;
import java.util.List;

/**
 *
 * @author ridmi_g
 */
public interface FilterMessageInf {
      public List<FilterMessageBean> loadFilterSMS(FilterMessageBean inputBean, int max, int first, String orderBy) throws Exception;
 
    public boolean insertSMS(FilterMessageBean inputBean)throws Exception;

    public boolean  deleteMessage(FilterMessageBean inputBean) throws Exception;
//   
    public String GetResult(FilterMessageBean inputBean) throws Exception;
    
}
