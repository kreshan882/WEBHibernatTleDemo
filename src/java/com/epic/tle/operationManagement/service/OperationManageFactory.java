/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.operationManagement.service;

import com.epic.tle.util.HibernateInit;

/**
 *
 * @author thilina_t
 */
public class OperationManageFactory {
    
    final private OperationManageInf operationManageInf;

    public OperationManageFactory() {
        this.operationManageInf =  new OperationService();
    }

    /**
     * @return the operationManageInf
     */
    public OperationManageInf getOperationManageInf() {
        return operationManageInf;
    }

    
}
