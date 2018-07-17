/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.host.service;

import com.epic.tle.host.bean.ChannelManageDataBean;
import com.epic.tle.host.bean.ChannelManageInputBean;
import com.epic.tle.host.bean.ListenerManageBean;
import com.epic.tle.host.bean.ListenerManageDataBean;
import com.epic.tle.host.bean.ListenerManageInputBean;
import com.epic.tle.util.CommonAccessInterface;
import com.epic.tle.util.HibernateInit;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public interface HostManagementInf extends CommonAccessInterface{

    public List<ListenerManageDataBean> loadListnerData(ListenerManageInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public void getMapmapnii(ListenerManageBean inputBean) throws Exception;

    public void findListener(ListenerManageInputBean inputBean) throws Exception;

    public boolean checkNii(String anii) throws Exception;

    public boolean checkMapNii(String amapnii) throws Exception;

    //public boolean insertnii(ListenerManageBean inputBean) throws Exception;

    public boolean insertmapnii(ListenerManageBean inputBean) throws Exception;

    public boolean updateListener(ListenerManageInputBean inputBean) throws Exception;

    public boolean deleteListener(ListenerManageInputBean inputBean) throws Exception;

    public List<ChannelManageDataBean> loadChannelData(ChannelManageInputBean inputBean, int max, int first, String orderBy) throws Exception;

    public void findNII(ChannelManageInputBean inputBean) throws Exception;

    public boolean insertnii(ChannelManageInputBean inputBean) throws Exception;

    public boolean updateNII(ChannelManageInputBean inputBean) throws Exception;

    public boolean deleteNII(ChannelManageInputBean inputBean) throws Exception;

    public boolean insertListener(ListenerManageInputBean bean) throws Exception;

    public ChannelManageInputBean getPagePath(String page, ChannelManageInputBean inputBean) throws Exception;

    public ListenerManageInputBean getPagePath(String page, ListenerManageInputBean inputBean) throws Exception;
    
    public String GetResult(ChannelManageInputBean inputBean, String field) throws Exception;
    public String GetResult(ListenerManageInputBean inputBean, String field) throws Exception;
    
    public  Map<Integer, String> getConnecType()throws Exception;
    public  Map<Integer, String> getForwardMethod()throws Exception;
    public  Map<Integer, String> getHeaderFormat()throws Exception;
    public  Map<Integer, String> getHeaderSize()throws Exception;
    public  Map<Integer, String> getOperMethod()throws Exception;
    public  Map<Integer, String> getIsoFormat()throws Exception;
    
}
