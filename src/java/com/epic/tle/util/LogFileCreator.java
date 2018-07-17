/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import com.epic.tle.util.constant.Configurations;
import com.epic.tle.login.bean.SessionUserBean;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.GregorianCalendar;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.apache.struts2.ServletActionContext;

/**
 *
 * @author nipun_t
 */
public class LogFileCreator {


    public static void writeErrorToLog(Throwable aThrowable) {
       // HttpSession session = ServletActionContext.getRequest().getSession(false);

        BufferedWriter bw = null;
        String msg = "";
        String newLine = "";
        String errpath =Util.getWebLogPath("errors");
        try {

            String filename = Util.getOSLogPath(errpath) + Util.getLocalDate() + "_E_LES_WebApp_error.TXT";
            msg = newLine + "\n" + getTime() + "\n" + getStackTrace(aThrowable);
            bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(msg);
            bw.newLine();
            bw.flush();

        } catch (Exception ioe) {   
            ioe.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) {
                }
            }
        }
    }
    
    public static void writeInforToLog(String msgIn) {
        HttpSession session = ServletActionContext.getRequest().getSession(false);
        HttpServletRequest request = ServletActionContext.getRequest();
        SessionUserBean sub;
        if(session!=null){
            sub = (SessionUserBean) session.getAttribute("SessionObject");
        }else{
            sub = new SessionUserBean();
            sub.setUserName("");
        }
    
        String msg,ip;
        ip = request.getRemoteAddr();
        BufferedWriter bw = null;
        String newLine = "";
        String infoPath = Util.getWebLogPath("infors");
        try {
            String userName=null==sub?"null":sub.getUserName();
            String filename = Util.getOSLogPath(infoPath) + Util.getLocalDate() + "_E_LES_WebApp_infor.TXT";
            msg = newLine + "\n" + getTime() + "\n" +"   LOGIN USER : "+userName+"  USER IP : "+ip+" "+msgIn;
            bw = new BufferedWriter(new FileWriter(filename, true));
            bw.write(msg);
            bw.newLine();
            bw.flush();

        } catch (Exception ioe) {              
            ioe.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException ioe2) {
                }
            }
        }
    }


    private static String getTime() {
        GregorianCalendar now = new GregorianCalendar();
        return now.getTime().toString();
    }


    private static String getStackTrace(Throwable aThrowable) throws Exception {
        String re = "";
        Writer result = null;
        PrintWriter printWriter = null;
        try {
            result = new StringWriter();
            printWriter = new PrintWriter(result);

            aThrowable.printStackTrace(printWriter);
            re = result.toString();
            result.close();
            printWriter.close();

        } catch (Exception e) {
            throw e;
        } finally {

            try {
                if (result != null) {
                    result.close();
                }
                if (printWriter != null) {
                    printWriter.close();
                }

            } catch (IOException e) {
                throw e;
            }
        }
        return re;
    }
}
