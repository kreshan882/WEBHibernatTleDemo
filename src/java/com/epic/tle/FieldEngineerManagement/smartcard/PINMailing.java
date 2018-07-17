package com.epic.tle.FieldEngineerManagement.smartcard;

import java.io.*;
import java.util.*;
import gnu.io.*;

public class PINMailing {

    static CommPortIdentifier portId;
    static CommPortIdentifier saveportId;
    static Enumeration portList;
    static ParallelPort parallPort;
    static SerialPort serialPort;
    static InputStream inputStream;
    static OutputStream outputStream;
    static boolean outputBufferEmptyFlag = false;

    public static void PINprint(String pin, String serial, String name, String date, String bankname) throws Exception {

        try {

            CommDriver RXTXDriver = (CommDriver) Class.forName("gnu.io.RXTXCommDriver").newInstance();
            RXTXDriver.initialize();


        } catch (Throwable e) {
            System.err.println(e + " thrown while loading " + "gnu.io.RXTXCommDriver");
        }





        String defaultPort = "";

        if ("COM".equals(PrinterConfig.PORT)) {
            try {
                defaultPort = "/dev/ttyS0";

                portList = CommPortIdentifier.getPortIdentifiers();
                while (portList.hasMoreElements()) {
                    portId = (CommPortIdentifier) portList.nextElement();
                    if (portId.getPortType() == CommPortIdentifier.PORT_SERIAL) {
                        if (portId.getName().equals(defaultPort)) {
                            System.out.println("Found port: " + defaultPort);
                            serialPort = (SerialPort) portId.open("SimpleReadApp", 2000);
                            inputStream = serialPort.getInputStream();
                            serialPort.notifyOnDataAvailable(true);
                            serialPort.setSerialPortParams(PrinterConfig.DATA_RATE,
                                    PrinterConfig.DATABITS, PrinterConfig.STOPBITS,
                                    PrinterConfig.PARITY);


                            outputStream = serialPort.getOutputStream();
                            serialPort.notifyOnOutputEmpty(true);
                            char pinc[] = pin.toCharArray();
                            StringBuffer buf = new StringBuffer();
                            for (int i = 0; i < pinc.length; i++) {
                                if (i == 6) {
                                    buf.append(pinc[i]);
                                } else {
                                    buf.append(pinc[i] + " ");
                                }

                            }
                            String mss = "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t	          Mailing smart card PINs for field officers  " +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t This document contains confidential information. Disposed onece exploited " +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\tBank Name      : " + bankname +
                                    "\r\n\r\t\tMerchant Name  : " + name +
                                    "\r\n\r\t\tCard serial No : " + serial +
                                    "\r\n\r\t\tIssue date     : " + date +
                                    "\r\n\r\t\tUser PIN	       " + buf.toString() +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t           (C) Epic Terminal Line Encryption (Epic-TLE)" +
                                    "\r\n\r\t\t____________________________________________________________________________";



                            outputStream.write(mss.getBytes());

//                            System.out.println("Successfully completed..");
                            Thread.sleep(3000);
                            break;



                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;

            } finally {
                if (serialPort != null) {
                    serialPort.close();
                }
                try {
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

        } else {
            defaultPort = "/dev/lp0";

            try {
                portList = CommPortIdentifier.getPortIdentifiers();
                while (portList.hasMoreElements()) {
                    portId = (CommPortIdentifier) portList.nextElement();
                    if (portId.getPortType() == CommPortIdentifier.PORT_PARALLEL) {
                        if (portId.getName().equals(defaultPort)) {
//                            System.out.println("Found port: " + defaultPort);

                            parallPort = (ParallelPort) portId.open("SimpleReadApp", 30000);
                            inputStream = parallPort.getInputStream();



                            int mode = parallPort.getMode();
                            System.out.println("Mode " + mode);

                            if (mode == 0) {
                                mode = 1;
                            }
                            switch (mode) {
                                case ParallelPort.LPT_MODE_ECP:
//                                    System.out.println("Mode is: ECP");
                                    break;
                                case ParallelPort.LPT_MODE_EPP:
//                                    System.out.println("Mode is: EPP");
                                    break;
                                case ParallelPort.LPT_MODE_NIBBLE:
//                                    System.out.println("Mode is: Nibble Mode.");
                                    break;
                                case ParallelPort.LPT_MODE_PS2:
//                                    System.out.println("Mode is: Byte mode.");
                                    break;
                                case ParallelPort.LPT_MODE_SPP:
//                                    System.out.println("Mode is: Compatibility mode.");
                                    break;

                                default:
                                    throw new IllegalStateException("Parallel mode " + mode + " invalid.");
                            }




                            outputStream = parallPort.getOutputStream();
                            char pinc[] = pin.toCharArray();
                            StringBuffer buf = new StringBuffer();
                            for (int i = 0; i < pinc.length; i++) {
                                if (i == 6) {
                                    buf.append(pinc[i]);
                                } else {
                                    buf.append(pinc[i] + " ");
                                }

                            }

                            String mss = "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t	          Mailing smart card PINs for field officers  " +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t This document contains confidential information. Disposed onece exploited " +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\tBank Name      : " + bankname +
                                    "\r\n\r\t\tMerchant Name  : " + name +
                                    "\r\n\r\t\tCard serial No : " + serial +
                                    "\r\n\r\t\tIssue date     : " + date +
                                    "\r\n\r\t\tUser PIN	       " + buf.toString() +
                                    "\r\n\r\t\t____________________________________________________________________________" +
                                    "\r\n\r\t\t           (C)Epic Terminal Line Encryption (Epic-TLE)" +
                                    "\r\n\r\t\t____________________________________________________________________________";






                            outputStream.write(mss.getBytes());
                            outputStream.close();
//                            System.out.println("Successfully completed..");
                            break;
                        }
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
                throw e;
            } finally {
                if (parallPort != null) {
                    parallPort.close();
                }
                try {
                    if (outputStream != null) {
                        outputStream.close();
                    }
                    if (inputStream != null) {
                        inputStream.close();
                    }
                } catch (IOException e) {

                    e.printStackTrace();
                }
            }


        }


    }
}
