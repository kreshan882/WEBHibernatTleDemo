/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import com.epic.tle.login.bean.ModuleBean;
import java.util.Comparator;

/**
 *
 * @author kreshan
 */
public class ModuleComparator implements Comparator<ModuleBean>{
    
        public int compare(ModuleBean _first, ModuleBean _second) {
            return _first.getMODULE_ID().compareTo(_second.getMODULE_ID());
    }

}
