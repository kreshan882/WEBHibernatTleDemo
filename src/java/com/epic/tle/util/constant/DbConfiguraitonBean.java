/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util.constant;

/**
 *
 * @author chalaka_n
 */
public class DbConfiguraitonBean {
   private String dialect;
   private String driverClass;
   private String url;
   private String username;
   private String password;
   private String zeroDateTimeBehavior;
   private String showSql;
   private String dbpooltype;
   private String dbpoolmin;
   private String poolSize;
   private String dbpoolmax;
   private String dbpooltimeout;
   private String dbpoolsource;

    public String getDbpoolsource() {
        return dbpoolsource;
    }

    public void setDbpoolsource(String dbpoolsource) {
        this.dbpoolsource = dbpoolsource;
    }

   
    public String getDbpoolmin() {
        return dbpoolmin;
    }

    public void setDbpoolmin(String dbpoolmin) {
        this.dbpoolmin = dbpoolmin;
    }

    public String getDbpoolmax() {
        return dbpoolmax;
    }

    public void setDbpoolmax(String dbpoolmax) {
        this.dbpoolmax = dbpoolmax;
    }

    public String getDbpooltimeout() {
        return dbpooltimeout;
    }

    public void setDbpooltimeout(String dbpooltimeout) {
        this.dbpooltimeout = dbpooltimeout;
    }
   

   
    public String getDbpooltype() {
        return dbpooltype;
    }

    public void setDbpooltype(String dbpooltype) {
        this.dbpooltype = dbpooltype;
    }

    public DbConfiguraitonBean() {
    }
   
   

    public DbConfiguraitonBean(String dialect, String driverClass, String url, String username, String password, String poolSize, String zeroDateTimeBehavior, String showSql) {
        this.dialect = dialect;
        this.driverClass = driverClass;
        this.url = url;
        this.username = username;
        this.password = password;
        this.poolSize = poolSize;
        this.zeroDateTimeBehavior = zeroDateTimeBehavior;
        this.showSql = showSql;
    }

    public void setDialect(String dialect) {
        this.dialect = dialect;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPoolSize(String poolSize) {
        this.poolSize = poolSize;
    }

    public void setZeroDateTimeBehavior(String zeroDateTimeBehavior) {
        this.zeroDateTimeBehavior = zeroDateTimeBehavior;
    }

    public void setShowSql(String showSql) {
        this.showSql = showSql;
    }
    

    public String getDialect() {
        return dialect;
    }

    public String getDriverClass() {
        return driverClass;
    }

    public String getUrl() {
        return url;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getPoolSize() {
        return poolSize;
    }

    public String getZeroDateTimeBehavior() {
        return zeroDateTimeBehavior;
    }

    public String getShowSql() {
        return showSql;
    }
   
   
   
}
