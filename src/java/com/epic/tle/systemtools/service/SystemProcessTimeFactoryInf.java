/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.systemtools.bean.SystemProcessDataBean;
import com.epic.tle.systemtools.bean.SystemProcessInputBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface SystemProcessTimeFactoryInf {

    public List<SystemProcessDataBean> loadProcessDetails(SystemProcessInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public SystemProcessInputBean getPagePath(String page, SystemProcessInputBean inputBean) throws Exception;

    public SystemProcessInputBean loadMap(SystemProcessInputBean inputBean) throws Exception;
}
