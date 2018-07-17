/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import com.epic.tle.login.bean.PageBean;
import java.util.Comparator;

/**
 *
 * @author chalaka_n
 */
public class SectionComparator implements Comparator<PageBean> {

    @Override
    public int compare(PageBean o1, PageBean o2) {
        return o1.getPAGE_ID().compareTo(o2.getPAGE_ID());
    }
    
}
