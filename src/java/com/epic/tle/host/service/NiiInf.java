/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.binManagement.service.*;
import com.epic.tle.binManagement.bean.BinBean;
import com.epic.tle.host.bean.NIIBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface NiiInf {
    
    public List<NIIBean> loadNii(NIIBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertNii(NIIBean inputBean)throws Exception;
    
    public boolean  deleteNii(NIIBean inputBean) throws Exception;


}
