/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.service;

import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerBean;
import com.epic.tle.FieldEngineerManagement.bean.RegisterFieldEngineerInputBean;
import com.epic.tle.FieldEngineerManagement.bean.FieldEngineer;
import java.util.List;

/**
 *
 * @author kasun_k
 */
public interface RegisterFieldEngineerServiceInf {
    public void getdevivetype(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public void getAlgoMap(RegisterFieldEngineerInputBean inputBean)  throws Exception;
    public void getPinVerTypeMap(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public void getPinMailerParems() throws Exception;
    public boolean isSerialNumExist(String sERIALNO) throws Exception;
    public boolean  insertFieldEngineer(FieldEngineer fieldEng) throws Exception;
    public void getKeyInjectParems() throws Exception;
    public List<RegisterFieldEngineerBean> loadFldEngCardHolderDetail(RegisterFieldEngineerInputBean inputBean,int max, int first, String orderBy) throws Exception;
    public boolean  deleteFE(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public void findFieldEngineer(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public boolean updateFieldEng(RegisterFieldEngineerInputBean inputBean) throws Exception;
    public void getfieldEngData(String upserialNumber, FieldEngineer fieldEng) throws Exception;
    public boolean  updateFieldEngineer(FieldEngineer fieldEng) throws Exception;
    public RegisterFieldEngineerInputBean getPagePath(String page,RegisterFieldEngineerInputBean inputBean)throws Exception;
}
