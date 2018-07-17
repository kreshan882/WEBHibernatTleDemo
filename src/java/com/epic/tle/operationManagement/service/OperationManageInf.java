/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.service;

import com.epic.tle.mapping.EpicTleOperationAlerts;
import com.epic.tle.operationManagement.bean.OperationBean;
import com.epic.tle.operationManagement.bean.OperationManageBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface OperationManageInf {
    
    public void getOperations(OperationManageBean inputBean) throws Exception;

    public List<OperationManageBean> loadListnerData(OperationManageBean inputBean, int rows, int from, String orderBy, boolean isFromDashBorad)throws Exception;

    public void getMessage(OperationManageBean inputBean)throws Exception;

    public void getReportDetails(OperationManageBean inputBean) throws Exception;

    public void inactiveBindStatus()throws Exception;
    
    public OperationManageBean getPagePath(String page, OperationManageBean inputBean) throws Exception;
    public void insertOperationAlerts(OperationBean alerts) throws Exception;

    
}
