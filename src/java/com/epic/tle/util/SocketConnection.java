/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketAddress;
/**
 *
 * @author KRESHAN
 */
public class SocketConnection {

    private static BufferedReader bir;

    public static boolean restartServer() {
        //Tle server reboot
        return sendCom("10A9E6AB0A1DF741DEE43CB564F7F1BE8A1509D5");
    }

    public static boolean stopServer() {
        //tle server stop
        return sendCom("10A9F1BE8A1509D5F7E6AB0A1DCB564F741DEE43");

    }

    public static boolean stopSession() {
        //Tle service stop
//        return sendCom("F7F1BE8A1509D5F741DEE4310A9E6AB0A1DCB564");
        return doOper("service epictle stop");
    }

    public static boolean startSession() {
        // bground start
        return doOper("service epictle start");

    }

    public static boolean restartSession() {
        //Session restart
        return sendCom("10A9E6AB0A1DCB564F7F1BE8A1509D5F741DEE43");

    }
    
    public static boolean isServerStart(){
         return sendCom("00");
    }

    private static boolean sendCom(String c) {
        Socket sock = null;
        java.io.PrintWriter pw = null;
        String msg = "";

        try {

            InetAddress addr = InetAddress.getByName("127.0.0.1");

            SocketAddress sockaddr = new InetSocketAddress(addr, 3435);

            sock = new Socket();

            sock.connect(sockaddr, 5000);
            pw = new java.io.PrintWriter(sock.getOutputStream(), true);
            bir = new BufferedReader(new InputStreamReader(sock.getInputStream()));


            pw.println(c);
            msg = bir.readLine();

//            System.out.println("Response : " + msg);

            if ("00".equals(msg)) {
                return true;
            }


        } catch (Exception ioe) {

            //log Ex
        } finally {
            if (pw != null) {
                pw.flush();
            }
            if (pw != null) {
                pw.close();
            }
            pw = null;
            sock = null;
        }
        return false;

    }

    public static boolean doOper(String cmd) {
        boolean ok = false;
        Runtime rt = null;
        Process pr = null;
        BufferedReader input = null;
        InputStreamReader inst = null;
        try {
            rt = Runtime.getRuntime();
            pr = rt.exec(cmd);
            inst = new InputStreamReader(pr.getInputStream());
            input = new BufferedReader(inst);
            String buffer = "";
            while ((buffer = input.readLine()) != null) {
                System.out.println(buffer);
            }
            int exitVal = pr.waitFor();
            if (exitVal == 0) {
                ok = true;
            }
        } catch (Exception e) {
            //write log
        } finally {
            if (input != null) {
                try {
                    input.close();
                } catch (IOException ex) {
                }
            }
            if (inst != null) {
                try {
                    inst.close();
                } catch (IOException ex) {
                }
            }
            if (pr != null) {
                pr.destroy();
            }
            input = null;
            inst = null;
            pr = null;
            rt = null;
        }
        return ok;
    }
    
}

