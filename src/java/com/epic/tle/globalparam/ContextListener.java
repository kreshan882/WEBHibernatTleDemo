/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.globalparam;

import com.epic.tle.FieldEngineerManagement.smartcard.HSMConnector;
import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.HibernateInit;
import com.epic.tle.util.OperationListenerMonitor;
import com.epic.tle.util.Util;
import com.epic.tle.util.constant.SwitchConfBean;
import com.epic.tle.util.constant.SystemMessage;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 *
 * @author kreshan
 */
public class ContextListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            if (System.getenv(Configurations.ENV_VARIABLE_CONFIG) != null) {
                //intialize hibernate
                Configurations.PATH_ROOT = System.getenv(Configurations.ENV_VARIABLE_CONFIG);                
                HibernateInit hibernateInit = new HibernateInit();
                hibernateInit.initialize();
                SwitchConfBean bean = Util.getSwitchConfiguration();
                OperationListenerMonitor.init(bean.getIp(), bean.getPort(), bean.getTimeout());
                
                if (!Configurations.HSMCONFIG) {
                    System.out.println("Hsm init 2....");
                //Configurations.HSMCONFIG = CommunicatWithSmartCard.init();              
                    HSMConnector.init(Configurations.HSM_SLOT,Configurations.HSM_PASSWORD);
                }
            } else {
                System.out.println(SystemMessage.INITIAL_ERROR);
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        try {
            System.out.println("Global Variable Destroyed.");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
