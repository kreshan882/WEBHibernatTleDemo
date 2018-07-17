/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epic.tle.FieldEngineerManagement.smartcard;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.smartcardio.Card;
import javax.smartcardio.CardChannel;
import javax.smartcardio.CardException;
import javax.smartcardio.CardTerminal;
import javax.smartcardio.CardTerminals;
import javax.smartcardio.CardTerminals.State;
import javax.smartcardio.CommandAPDU;
import javax.smartcardio.ResponseAPDU;
import javax.smartcardio.TerminalFactory;

/**
 *
 * @author rasika_h
 *
 * Smart Card API
 * Return Messages
 * Message format
 *         XX         XXXXXXXXXXXXX
 *      Message ID       Message
 * Formatting Card
 * 00 - Successfully formatted.
 * 01 - Error Connecting to Card
 * 02 - Error in formatting card
 * Initializing Card
 * 00 - Successfully Initialized
 * 01 - Error Connecting to Card
 * 03 - Invalid Serial No
 * 04 - Error Initializing Card
 * Initializing Key
 * 00 - Successfully Initialized Key
 * 01 - Error Connecting to Card
 * 05 - Invalid Key
 * 06 - Error Initializing Key
 */
public class CardProcess {

    private static Card card = null;
    private static CardChannel ch = null;

    static boolean connect() {
        try {
            List<CardTerminal> readerList = null;
            TerminalFactory tf = TerminalFactory.getDefault();
            CardTerminals ct = tf.terminals();

            readerList = ct.list(State.ALL);
            for (int i = 0; i < readerList.size(); i++) {
                System.out.println(readerList.get(i).getName());
            }

            System.out.println(readerList.size() + " Card Readers Found");

            CardTerminal cardTerminal = readerList.get(0);
            if (cardTerminal.isCardPresent()) {
                if ((card = cardTerminal.connect("*")) != null) {
                    ch = card.getBasicChannel();
                    return true;
                }
                System.out.println("ERROR: Failed to connect to card!");
            }

        } catch (Exception e) {
        }
        return false;
    }

    static void disconnect() throws Exception {
        card.disconnect(true);
        return;
    }

    static String sendCommand(String hexCommand) throws Exception {
        byte[] AID  = getBytes(hexCommand);
        CommandAPDU apdu = new CommandAPDU(AID);
        ResponseAPDU ra = ch.transmit(apdu);
        System.out.println("Command >" + hexCommand);
        System.out.println("Response >" + byteArrayToHexString(ra.getBytes()) + "\n");
        return byteArrayToHexString(ra.getBytes());
    }

    static byte[] getBytes(String key) throws Exception {
        byte bkey[] = new byte[key.length() / 2];
        int c = 0;
        for (int x = 0; x <
                key.length(); x++) {
            bkey[c] = (byte) Integer.parseInt(key.substring(x++, x + 1), 16);
            c++;
        }
        return bkey;
    }

    static String byteArrayToHexString(byte in[]) {
        byte chr = 0x00;
        int i = 0;
        if (in == null || in.length <= 0) {
            return null;
        }
        String pseudo[] = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F"};
        StringBuffer out = new StringBuffer(in.length * 2);
        while (i < in.length) {
            chr = (byte) (in[i] & 0xF0); // Strip off high nibble

            chr = (byte) (chr >>> 4);     // shift the bits down

            chr = (byte) (chr & 0x0F);    // must do this is high order bit is on!

            out.append(pseudo[(int) chr]); // convert the nibble to a String Character

            chr = (byte) (in[i] & 0x0F); // Strip off low nibble

            out.append(pseudo[(int) chr]); // convert the nibble to a String Character

            i++;
        }
        String rslt = new String(out);
        return rslt;
    }

    static String threeDES(String hexKey, String hexData) {

        byte[] doubleLengthKey = null;
        byte[] data = null;
        try {
            doubleLengthKey = getBytes(hexKey);
            data = getBytes(hexData);
        } catch (Exception ex) {
            Logger.getLogger(CardProcess.class.getName()).log(Level.SEVERE, null, ex);
        }
        String response = null;
        byte[] cper = new byte[8];
        try {
            byte[] leftkey = new byte[8];
            byte[] rightkey = new byte[8];
            for (int i = 0; i < 8; i++) {
                leftkey[i] = doubleLengthKey[i];
                rightkey[i] = doubleLengthKey[i + 8];
            }
            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DES");
            Cipher desCiper = Cipher.getInstance("DES/ECB/NoPadding");

            DESKeySpec leftKeySpec = new DESKeySpec(leftkey);
            SecretKey leftKey = keyFactory.generateSecret(leftKeySpec);

            DESKeySpec rightKeySpec = new DESKeySpec(rightkey);
            SecretKey rightKey = keyFactory.generateSecret(rightKeySpec);

            desCiper.init(Cipher.ENCRYPT_MODE, leftKey);
            cper = desCiper.doFinal(data);

            desCiper.init(Cipher.DECRYPT_MODE, rightKey);
            cper = desCiper.doFinal(cper);

            desCiper.init(Cipher.ENCRYPT_MODE, leftKey);
            cper = desCiper.doFinal(cper);

        } catch (Exception e) {
            e.printStackTrace();
        }
        response = byteArrayToHexString(cper);
        return response;
    }


}
