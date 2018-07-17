/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.bean;

import com.epic.tle.mapping.EpicTleUserPasswordHistory;
import com.epic.tle.util.constant.Configurations;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author kreshan
 */
public class UserLoginBean {

    private String userName;
    private String email;
    private String password;
    private String nodestatus = Configurations.NODE_STATUS;
    private String serverNode;
    private int id;
    private String message;
    private String serviceStatus;
    private String Database;

    private String DBpassword;
    private String DBuserName;
    private String DBname;
    private String userRole;
    private int userRoleStatus;//user role status active or inactive
    private int DBuserappCode; //admin,fieldManager,...
    private int DBuserStatus;
    private String created_date_time;
    private String last_password_updated_date;
    private int failure_login_count;
    private Date last_raw_update_date_time;
    private Date last_password_reset_date_time;
    private List<String> previous_passwords;

    List<ProcessingBean> chartMap = new ArrayList<ProcessingBean>();
    private String FromDate;
    private String ToDate;
    

    public int getUserRoleStatus() {
        return userRoleStatus;
    }

    public void setUserRoleStatus(int userRoleStatus) {
        this.userRoleStatus = userRoleStatus;
    }

   
    public String getServiceStatus() {
        return serviceStatus;
    }

    public String getServerNode() {
        return serverNode;
    }

    public void setServerNode(String serverNode) {
        this.serverNode = serverNode;
    }
    
    

    public void setServiceStatus(String serviceStatus) {
        this.serviceStatus = serviceStatus;
    }

    
    public String getNodestatus() {
        return nodestatus;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }
    

    public void setNodestatus(String nodestatus) {
        this.nodestatus = nodestatus;
    }

    public List<ProcessingBean> getChartMap() {
        return chartMap;
    }

    public void setChartMap(List<ProcessingBean> chartMap) {
        this.chartMap = chartMap;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDBname() {
        return DBname;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setDBname(String DBname) {
        this.DBname = DBname;
    }

    public String getDBpassword() {
        return DBpassword;
    }

    public void setDBpassword(String DBpassword) {
        this.DBpassword = DBpassword;
    }

    public String getDBuserName() {
        return DBuserName;
    }

    public void setDBuserName(String DBuserName) {
        this.DBuserName = DBuserName;
    }

    public int getDBuserStatus() {
        return DBuserStatus;
    }

    public void setDBuserStatus(int DBuserStatus) {
        this.DBuserStatus = DBuserStatus;
    }

    public int getDBuserappCode() {
        return DBuserappCode;
    }

    public void setDBuserappCode(int DBuserappCode) {
        this.DBuserappCode = DBuserappCode;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the FromDate
     */
    public String getFromDate() {
        return FromDate;
    }

    /**
     * @param FromDate the FromDate to set
     */
    public void setFromDate(String FromDate) {
        this.FromDate = FromDate;
    }

    /**
     * @return the ToDate
     */
    public String getToDate() {
        return ToDate;
    }

    /**
     * @param ToDate the ToDate to set
     */
    public void setToDate(String ToDate) {
        this.ToDate = ToDate;
    }

    /**
     * @return the Database
     */
    public String getDatabase() {
        return Database;
    }

    /**
     * @param Database the Database to set
     */
    public void setDatabase(String Database) {
        this.Database = Database;
    }

    public String getCreated_date_time() {
        return created_date_time;
    }

    public void setCreated_date_time(String created_date_time) {
        this.created_date_time = created_date_time;
    }

    public int getFailure_login_count() {
        return failure_login_count;
    }

    public void setFailure_login_count(int failure_login_count) {
        this.failure_login_count = failure_login_count;
    }

    public Date getLast_raw_update_date_time() {
        return last_raw_update_date_time;
    }

    public void setLast_raw_update_date_time(Date last_raw_update_date_time) {
        this.last_raw_update_date_time = last_raw_update_date_time;
    }

    @Override
    public String toString() {
        return "UserLoginBean{" + "userName=" + userName + ", password=" + password + ", nodestatus=" + nodestatus + ", serverNode=" + serverNode + ", id=" + id + ", message=" + message + ", serviceStatus=" + serviceStatus + ", Database=" + Database + ", DBpassword=" + DBpassword + ", DBuserName=" + DBuserName + ", DBname=" + DBname + ", userRole=" + userRole + ", userRoleStatus=" + userRoleStatus + ", DBuserappCode=" + DBuserappCode + ", DBuserStatus=" + DBuserStatus + ", created_date_time=" + created_date_time + ", failure_login_count=" + failure_login_count + ", last_raw_update_date_time=" + last_raw_update_date_time + ", previous_passwords=" + previous_passwords + ", chartMap=" + chartMap + ", FromDate=" + FromDate + ", ToDate=" + ToDate + '}';
    }

    public List<String> getPrevious_passwords() {
        return previous_passwords;
    }

    public void setPrevious_passwords(List<String> previous_passwords) {
        this.previous_passwords = previous_passwords;
    }

    public String getLast_password_updated_date() {
        return last_password_updated_date;
    }

    public void setLast_password_updated_date(String last_password_updated_date) {
        this.last_password_updated_date = last_password_updated_date;
    }

    public Date getLast_password_reset_date_time() {
        return last_password_reset_date_time;
    }

    public void setLast_password_reset_date_time(Date last_password_reset_date_time) {
        this.last_password_reset_date_time = last_password_reset_date_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


   

}
