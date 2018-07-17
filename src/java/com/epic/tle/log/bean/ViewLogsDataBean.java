/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.epic.tle.log.bean;

/**
 *
 * @author nipun_t
 */
public class ViewLogsDataBean {
    private String logFileName;
    private String view;
    private String size;
    private String path;
    private String logFileType;
    private String date;
    private long fullCount;     

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
    
    public String getLogFileName() {
        return logFileName;
    }

    public String getLogFileType() {
        return logFileType;
    }

    public void setLogFileType(String logFileType) {
        this.logFileType = logFileType;
    }

    public void setLogFileName(String logFileName) {
        this.logFileName = logFileName;
    }

    public String getView() {
        return view;
    }

    public void setView(String view) {
        this.view = view;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getFullCount() {
        return fullCount;
    }

    public void setFullCount(long fullCount) {
        this.fullCount = fullCount;
    }

    @Override
    public String toString() {
        return "ViewLogsDataBean{" + "logFileName=" + logFileName + ", view=" + view + ", size=" + size + ", path=" + path + ", logFileType=" + logFileType + ", date=" + date + ", fullCount=" + fullCount + '}';
    }
    
}
