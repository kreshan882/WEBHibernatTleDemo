/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.smartcard;

import java.security.PublicKey;

/**
 *
 * @author rasika_h
 */
public class SmartCard {

    public static boolean initializedCard(String pin, String serial) throws Exception {

        if (pin == null || serial == null) {
            CardProcess.disconnect();
            return false;
        }

        String dataString = pin + serial;
        if (dataString.length() != 16) {
            CardProcess.disconnect();
            return false;
        }

        if (CardProcess.connect()) {
            String response = null;
            response = CardProcess.sendCommand("00A404000B0102030405060708090001");
            if (null == response || (!response.endsWith("9000"))) {
                System.out.println("Error When Selecting Applet");
                CardProcess.disconnect();
                return false;
            }

            response = CardProcess.sendCommand("0C09000008" + dataString);
            if (null == response || (!response.endsWith("9000"))) {
                System.out.println("Error When Selecting Applet");
                CardProcess.disconnect();
                return false;
            }
        } else {
            return false;
        }
        CardProcess.disconnect();
        return true;
    }

    public static PublicKey getPublicKey() throws Exception {

        String exponent = null;
        String modules = null;

        if (CardProcess.connect()) {

            String response = null;
            response = CardProcess.sendCommand("00A404000B0102030405060708090001");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return null;
            }

            response = CardProcess.sendCommand("0C07000000");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return null;
            }

            modules = response.substring(0, response.length() - 4);

            response = CardProcess.sendCommand("0C08000000");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return null;
            }

            exponent = response.substring(0, response.length() - 4);


            response = CardProcess.sendCommand("0C0301000102");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return null;
            }
            CardProcess.disconnect();
            return RsaOperation.getPublicKey(exponent, modules);

        } else {
            return null;
        }
    }

    public static boolean setDesKey(String DesKey) throws Exception {


        if (CardProcess.connect()) {

            String response = null;
            response = CardProcess.sendCommand("00A404000B0102030405060708090001");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

            if (DesKey.length() != 32) {
                CardProcess.disconnect();
                return false;
            }

            response = CardProcess.sendCommand("0C06000010" + DesKey);
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

            response = CardProcess.sendCommand("0C0301000101");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

        } else {
            return false;
        }
        CardProcess.disconnect();
        return true;
    }


        public static boolean testCard(String pin) throws Exception {

//        0C0600001012341234678934561234123487893456
        if (CardProcess.connect()) {

            String response = null;
            response = CardProcess.sendCommand("00A404000B0102030405060708090001");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }


            response = CardProcess.sendCommand("0C02000003" + pin);
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

            response = CardProcess.sendCommand("0C01000000");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

            response = CardProcess.sendCommand("0C03000000");
            if (null == response || (!response.endsWith("9000"))) {
                CardProcess.disconnect();
                return false;
            }

        } else {
            return false;
        }
        CardProcess.disconnect();
        return true;
    }

    public static void main(String args[]) throws Exception {
        initializedCard("123456", "1234567890");
        setDesKey("01020304050601020304050607080900");
//        getPublicKey();

        testCard("123456");
    }
}
