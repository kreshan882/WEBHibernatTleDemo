/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.login.action;

import com.epic.tle.operationManagement.service.OperationManageFactory;
import com.epic.tle.util.LogFileCreator;
import com.epic.tle.util.OperationListenerMonitor;
import com.epic.tle.util.constant.Configurations;


/**
 *
 * @author chalaka_n
 */


public class MonitorServerStatus implements Runnable{
    
    

    @Override
    public  void run() {
    
        int c = 0;
        while (true){
            try {
               
                
                String in = Configurations.SWITCH_ECHO;
                
                String response = OperationListenerMonitor.send(in.trim());
                

                if (response != null && response.equals("00")) {
                   // System.out.println("Successfully send");
                    Configurations.SERVER_STATUS=true;
                    c = 0;
                } else {
//                    System.out.println("send fail");
                    Configurations.SERVER_STATUS=false;
                   c++;
                    
                }
                
                
            } catch (Exception ex) {
                c++;
                System.out.println(ex.getMessage());
                LogFileCreator.writeErrorToLog(ex);
                Configurations.SERVER_STATUS=false;
               
            }finally{
                try {
                    Thread.sleep(3000);
                    if (c > 3){
                        getServiceOpr().getOperationManageInf().inactiveBindStatus();
                        c = 0;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                   
                }
            }
        }
    }
    
       
    
    public OperationManageFactory getServiceOpr() {
        return new OperationManageFactory();
    }
    
}
