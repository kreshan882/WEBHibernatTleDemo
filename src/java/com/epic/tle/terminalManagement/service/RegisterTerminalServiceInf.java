/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.terminalManagement.service;

import com.epic.tle.terminalManagement.bean.NonFunctionTerminalDataBean;
import com.epic.tle.terminalManagement.bean.NonFunctionTerminalInputBean;
import com.epic.tle.terminalManagement.bean.RegisterTerminalBean;
import com.epic.tle.userManagement.bean.RegisterUserBean;
import java.util.List;

/**
 *
 * @author danushka_r
 */
public interface RegisterTerminalServiceInf {

    public void setETypeDropDown(RegisterTerminalBean inputBean) throws Exception;

    public void BinPrfDropDown(RegisterTerminalBean inputBean) throws Exception;

    public boolean addTerminal(RegisterTerminalBean inputBean) throws Exception;

    public List getEncryptionStatus() throws Exception;

    public boolean deleteTerminalUser(String dtid) throws Exception;

    public void findUser(RegisterTerminalBean inputBean) throws Exception;

    public void initiateValuesToMap(RegisterTerminalBean inputBean) throws Exception;

    public boolean updateTerminal(RegisterTerminalBean inputBean) throws Exception;

    public List<RegisterTerminalBean> loadTerminalUsers(RegisterTerminalBean inputBean, int max, int first, String orderBy) throws Exception;

    public RegisterTerminalBean getPagePath(String page, RegisterTerminalBean inputBean) throws Exception;

    public Object generateExcelReport() throws Exception;

    public List<NonFunctionTerminalDataBean> loadTerminalUsers(NonFunctionTerminalInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public void RepPrfDropDown(RegisterTerminalBean inputBean) throws Exception;
    
    public String GetResult(RegisterTerminalBean inputBean) throws Exception;

}
