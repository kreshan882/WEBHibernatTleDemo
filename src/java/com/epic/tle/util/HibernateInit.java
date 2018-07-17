/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import com.epic.tle.util.constant.Configurations;
import com.epic.tle.util.constant.DbConfiguraitonBean;
import com.epic.tle.util.constant.SystemMessage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.rpc.ServiceFactory;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

/**
 *
 * @author kreshan
 */
public class HibernateInit {

    public static SessionFactory sessionFactory;
    //public static String dbConfig;

    {
        if (sessionFactory == null || sessionFactory.isClosed()) {
            initialize();
        }
    }

    private void setDbConfig(Configuration config) {
        String path = Configurations.PATH_ROOT + Configurations.PATH_CONFIG;
        DbConfiguraitonBean dbConfigBean = new DbConfiguraitonBean();
        try {
            dbConfigBean = Util.xmlConfiguraion(path);
           // HibernateInit.dbConfig = dbConfigBean.getDbpooltype();
        } catch (Exception ex) {
            ex.printStackTrace();
            System.out.println(SystemMessage.CONFIGURAITON_ERROR);
        }
            config.configure("hibernate.cfg.xml");
            
            config.setProperty("hibernate.dialect", dbConfigBean.getDialect());
            config.setProperty("hibernate.connection.driver_class",dbConfigBean.getDriverClass());
            config.setProperty("hibernate.connection.datasource",dbConfigBean.getDbpoolsource());
            config.setProperty("hibernate.connection.zeroDateTimeBehavior",dbConfigBean.getZeroDateTimeBehavior());
            config.setProperty("hibernate.show_sql", "false");
             
    }

    public SessionFactory initialize() {
        if (this.sessionFactory == null || this.sessionFactory.isClosed()) {
            Configuration configuration = new Configuration();
            setDbConfig(configuration);
            ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();
            this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        }
        return this.sessionFactory;
    }
}
