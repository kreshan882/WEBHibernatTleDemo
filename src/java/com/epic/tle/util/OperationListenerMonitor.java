/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.util;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ConnectException;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 *
 * @author chalaka_n
 */
public class OperationListenerMonitor {

    static int service_port;
    static String service_ip;
    static int con_timeout;
    

    public static void init(String ip, int port, int timeout) throws Exception {

        service_port = port;
        service_ip = ip;
        con_timeout = timeout;

    }

    private static Socket getSocket() throws Exception {
        InetAddress anetAdd = InetAddress.getByName(service_ip);

        InetSocketAddress sockAddress = new InetSocketAddress(anetAdd, service_port);

        Socket s = new Socket();

        s.setKeepAlive(true);
        try{
            s.connect(sockAddress, con_timeout);
        }
        catch(ConnectException e){
            throw new ConnectException(e.getMessage()+"\nConnection refused while connecting to socket. Address "+ sockAddress);
        }
        return s;

    }

    private static byte[] getHexLengthMessage(byte msg[]) throws Exception {

        byte L[] = hex2byte(zeropad(Integer.toHexString(msg.length), 4));

        return concat(L, msg);
    }

    public static byte[] concat(byte[] array1, byte[] array2) {
        byte[] concatArray = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, concatArray, 0, array1.length);
        System.arraycopy(array2, 0, concatArray, array1.length, array2.length);
        return concatArray;
    }

    public static byte[] hex2byte(String s) throws Exception {
        if (s.length() % 2 == 0) {
            return hex2byte(s.getBytes(), 0, s.length() >> 1);
        } else {
            return hex2byte("0" + s);
        }
    }

    public static byte[] hex2byte(byte[] b, int offset, int len) throws Exception {
        byte[] d = new byte[len];
        for (int i = 0; i < len * 2; i++) {
            int shift = i % 2 == 1 ? 0 : 4;
            d[i >> 1] |= Character.digit((char) b[offset + i], 16) << shift;
        }
        return d;
    }

    public static String zeropad(String s, int len) throws Exception {
        return padleft(s, len, '0');
    }

    public static String padleft(String s, int len, char c) throws Exception {
        s = s.trim();

        StringBuffer d = new StringBuffer(len);
        int fill = len - s.length();
        while (fill-- > 0) {
            d.append(c);
        }
        d.append(s);
        return d.toString();
    }

    public static String hexString(byte[] b) throws Exception {
        StringBuffer d = new StringBuffer(b.length * 2);
        for (int i = 0; i < b.length; i++) {
            char hi = Character.forDigit((b[i] >> 4) & 0x0F, 16);
            char lo = Character.forDigit(b[i] & 0x0F, 16);
            d.append(Character.toUpperCase(hi));
            d.append(Character.toUpperCase(lo));
        }
        return d.toString();
    }

    public static String send(String msg) throws Exception {

        Socket s = null;
        DataOutputStream out = null;
        DataInputStream in = null;
        String response = null;

        try {
            s = getSocket();
            s.setSoTimeout(con_timeout);
            out = new DataOutputStream(s.getOutputStream());
            
            
            out.write(getHexLengthMessage(msg.getBytes()));
            out.flush();

            
            in = new DataInputStream(s.getInputStream());

            byte HD[] = new byte[2];
            in.readFully(HD, 0, 2);
            int HD_L = Integer.parseInt(hexString(HD), 16);

            if (HD_L > 0) {

                byte buf[] = new byte[HD_L];
                s.setSoTimeout(con_timeout);
                in.readFully(buf, 0, HD_L);

                response = new String(buf);

            }

        } catch (Exception e) {

            throw e;

        } finally {

            try {

                if (s != null) {
                    s.close();
                }
                if (in != null) {
                    in.close();
                }
                if (out != null) {
                    out.close();
                }
            } catch (Exception e1) {

            }
        }

        return response;
    }

}
