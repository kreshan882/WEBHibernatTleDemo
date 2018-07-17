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
public enum TaskVariableList {
    ADD("01","add"),
    DELETE("02","delete"),
    UPDATE("03","update"),
    VIEW("04","view"),
    DOWNLOAD("05","download"),
    UPLOAD("06","upload"),
    CONFIRM("07","confirm"),
    REJECT("08","reject"),
    EXECUTE("09","execute");
    
    private String taskID;
    private String taskName;

    public String getTaskID() {
        return taskID;
    }

    public void setTaskID(String taskID) {
        this.taskID = taskID;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    private TaskVariableList(String taskID, String taskName) {
        this.taskID = taskID;
        this.taskName = taskName;
    }
    
    
    
}
