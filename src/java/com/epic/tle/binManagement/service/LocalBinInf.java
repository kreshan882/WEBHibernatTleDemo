/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.binManagement.service;

import com.epic.tle.binManagement.bean.LocalBinBean;
import java.util.List;

/**
 *
 * @author thilina_t
 */
public interface LocalBinInf {

    public List<LocalBinBean> loadLocalBin(LocalBinBean inputBean, int max, int first, String orderBy) throws Exception;

    public boolean insertLocalBin(LocalBinBean inputBean) throws Exception;

    public boolean deleteLocalBin(LocalBinBean inputBean) throws Exception;

    public boolean checkBin(LocalBinBean inputBean) throws Exception;

    public LocalBinBean getPagePath(String page, LocalBinBean inputBean) throws Exception;

}
