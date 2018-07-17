/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.BinBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface BinProfileInf {
    
    public List<BinBean> loadBin(BinBean inputBean, int max, int first, String orderBy) throws Exception;
    
    public boolean insertBin(BinBean inputBean)throws Exception;
    
    public boolean  deleteBin(BinBean inputBean) throws Exception;
    
    public String GetResult(BinBean inputBean) throws Exception;
    public boolean checkRange(BinBean inputBean) throws Exception;
    
}
