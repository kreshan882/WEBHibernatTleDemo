/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface ReportSystemHistoryInf {
    
    public Object generateExcelReport(SystemHistoryInputBean inputBean) throws Exception;
    
    public SystemHistoryInputBean loadMap(SystemHistoryInputBean inputBean) throws Exception;
    public SystemHistoryInputBean loadSectionMap(SystemHistoryInputBean inputBean) throws Exception;
    public SystemHistoryInputBean getPagePath(String page, SystemHistoryInputBean inputBean) throws Exception;    
    public List<SystemHistoryDataBean> loadHistoryDetails(SystemHistoryInputBean inputBean, int max, int first, String orderBy) throws Exception;
    public SystemHistoryInputBean loadMoreHistoryDetails(SystemHistoryInputBean inputBean) throws Exception;
    
}
