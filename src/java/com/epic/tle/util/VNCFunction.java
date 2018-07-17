/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import java.io.IOException;

/**
 *
 * @author KRESHAN
 */
public class VNCFunction extends Thread {

    Process process;

    public void run() {
        try {
            String cmd = "lnstartvnc.sh";
            process = Runtime.getRuntime().exec(cmd);

            Thread.sleep(30 * 60000);
        } catch (Exception e) {
        } finally {
            try {
                String cmd = "lnstopvnc.sh";
                Process proc = Runtime.getRuntime().exec(cmd);
            } catch (IOException ex) {
            }
        }
    }

    public int getProcessCode() {
        if (process != null) {
            try {
                process.waitFor();
            } catch (InterruptedException ex) {
            }
            System.out.println(process.exitValue());
            return process.exitValue();
        } else {
            return 1;
        }

    }
}
