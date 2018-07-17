/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.systemtools.service;

import com.epic.tle.mapping.EpicTleModule;
import com.epic.tle.systemtools.bean.SystemAlertsDataBean;
import com.epic.tle.systemtools.bean.SystemAlertsInputBean;
import com.epic.tle.systemtools.bean.SystemHistoryDataBean;
import com.epic.tle.systemtools.bean.SystemHistoryInputBean;
import com.epic.tle.systemtools.bean.SystemTnxFailDataBean;
import com.epic.tle.systemtools.bean.SystemTnxFailInputBean;
import com.epic.tle.util.CommonAccessInterface;
import com.epic.tle.util.HibernateInit;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public interface SystemTnxFailingFactoryInf extends CommonAccessInterface {

    public SystemTnxFailInputBean loadDataIntoMap(SystemTnxFailInputBean inputBean) throws Exception;

    public List<SystemTnxFailDataBean> loadTnxFialDetails(SystemTnxFailInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public SystemTnxFailInputBean getPagePath(String page, SystemTnxFailInputBean inputBean) throws Exception;

    public List<SystemAlertsDataBean> loadAlertsDetails(SystemAlertsInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public SystemAlertsInputBean getPagePath(String page, SystemAlertsInputBean inputBean) throws Exception;

    public SystemHistoryInputBean loadMap(SystemHistoryInputBean inputBean) throws Exception;

    public SystemHistoryInputBean getPagePath(String page, SystemHistoryInputBean inputBean) throws Exception;

    public List<SystemHistoryDataBean> loadHistoryDetails(SystemHistoryInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public Map<Integer, String> getAlertType() throws Exception;

    public Map<Integer, String> getRisklevel() throws Exception;

    public List<SystemAlertsDataBean> loadAlertsDetailsTrans(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception;

    public List<SystemAlertsDataBean> loadAlertsDetailsSystem(SystemAlertsInputBean inputBean, int rows, int from, String orderBy) throws Exception;
}
