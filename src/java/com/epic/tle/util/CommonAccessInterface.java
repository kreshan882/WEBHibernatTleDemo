/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author chalaka_n
 */
public interface CommonAccessInterface {
    public  Map<Integer, String> getStatusValues(int from, int to);
    public  List getMasterValues(int from, int to,String table);
}
