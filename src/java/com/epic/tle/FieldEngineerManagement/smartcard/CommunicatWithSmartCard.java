package com.epic.tle.FieldEngineerManagement.smartcard;

import java.security.Key;
import java.security.Provider;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

import org.jpos.iso.ISOUtil;

import com.epic.tle.smartcard.SmartCard;

public class CommunicatWithSmartCard {

    private static Process_KEYBean processObject = null;

    public static Process_KEYBean getProcessObject() {
        return processObject;
    }

    public static void setProcessObject(Process_KEYBean processObject) {
        CommunicatWithSmartCard.processObject = processObject;
    }


    public static String getManualRequest(Process_KEYBean p) throws Exception {
        return p.getTMK() + p.getTMK_KVC() + p.getMEK() + p.getMEK_KVC() + p.getKEYID();
    }

    public static boolean manualPINVerfication(String PIN, String PINB) throws Exception {

        byte PINBLOCK[] = encrypt((SecretKey) HSMConnector.getLMK(), ISOUtil.hex2byte(PIN), HSMConnector.getPROVIDER(), "ECB", new byte[8]);

        if (PINB.equals(ISOUtil.hexdump(PINBLOCK))) {
            return true;
        } else {
            return false;
        }
    }

    private static String generateClear_PIN() throws Exception {

        Random ran = new Random();
        String ranv = ran.nextInt(1000000) + "";
        return ISOUtil.zeropad(ranv, 6);
    }

    public static boolean doProcess(String device_mode, String verfy_mode, String enc_mode, String serialNo, Process_KEYBean object) throws Exception {

        System.out.println("Device Mode: "+device_mode+" | Verfy Mode : "+verfy_mode+" | Serial Number : "+serialNo+" | Process Key Bean : "+object);
        
//        serialNumber,(01,02)encrption mode,(02,01)-onile/offline,PIN,Des-key

        if (Mode.DEV_SMART.equals(device_mode)) {
            if (Mode.ALGO_3DES.equals(enc_mode)) {
                Key genkey = genKey();
                byte eFWK[] = getEncrFWK(genkey);
                byte KVC[] = createKVC((SecretKey) genkey);
                byte FWK[] = getClearFWK(eFWK);
                String PIN = generateClear_PIN();
                
                
                
                boolean k = SmartCard.initializedCard(serialNo, enc_mode, verfy_mode, PIN, ISOUtil.hexString(FWK));
                if (k) {
                    object.setEFWK(ISOUtil.hexString(eFWK));
                    object.setFWK_KVC(ISOUtil.hexString(KVC));
                    object.setPIN(PIN);
                    setProcessObject(object);


                    byte y[] = new byte[5];
                    byte x[] = ISOUtil.hex2byte(PIN);
                    byte z[] = ISOUtil.concat(x, y);
                    System.out.println(ISOUtil.hexString(z));
                    byte PINBLOCK[] = encrypt((SecretKey) genkey, z, HSMConnector.getPROVIDER(), "ECB", new byte[8]);


                    object.setPINBLOCK(ISOUtil.hexString(PINBLOCK));
                    return true;

                } else {

                    return false;
                }


            } else {
                
                Key genkey = genKey();
                byte eFWK[] = getEncrFWK(genkey);
                byte KVC[] = createKVC((SecretKey) genkey);
                byte FWK[] = getClearFWK(eFWK);
                String PIN = generateClear_PIN();

                boolean k = SmartCard.initializedCard(serialNo, enc_mode, verfy_mode, PIN, ISOUtil.hexString(FWK));

                if (k) {
                    object.setEFWK(ISOUtil.hexString(eFWK));
                    object.setFWK_KVC(ISOUtil.hexString(KVC));
                    object.setPIN(PIN);
                    object.setRSA_EXPORNET(SmartCard.getPublicKeyeExponent());
                    object.setRSA_MODULE(SmartCard.getPublicKeyeModules());
                    setProcessObject(object);

                    byte y[] = new byte[5];
                    byte x[] = ISOUtil.hex2byte(PIN);
                    byte z[] = ISOUtil.concat(x, y);
                    System.out.println(ISOUtil.hexString(z));
                    byte PINBLOCK[] = encrypt((SecretKey) genkey, z, HSMConnector.getPROVIDER(), "ECB", new byte[8]);


                    object.setPINBLOCK(ISOUtil.hexString(PINBLOCK));
                    return true;
                } else {

                    return false;
                }

            }
        }
        if (Mode.DEV_KEYINJECTIONTERMINAL.equals(device_mode)) {
            if (Mode.ALGO_3DES.equals(enc_mode)) {

             
                
                Key genkey = genKey();
                byte eFWK[] = getEncrFWK(genkey);
                byte KVC[] = createKVC((SecretKey) genkey);
                byte FWK[] = getClearFWK(eFWK);
               


                object.setEFWK(ISOUtil.hexString(eFWK));
                object.setFWK_KVC(ISOUtil.hexString(KVC));

                object.setFWK(ISOUtil.hexString(FWK));
                setProcessObject(object);
                return true;

            }
        }


        if (Mode.DEV_MANUAL.equals(device_mode)) {
            if (Mode.ALGO_3DES.equals(enc_mode)) {



                String PIN = generateClear_PIN();
                byte y[] = new byte[5];
                byte x[] = ISOUtil.hex2byte(PIN);
                byte z[] = ISOUtil.concat(x, y);
                System.out.println(ISOUtil.hexString(z));
                byte PINBLOCK[] = encrypt((SecretKey) HSMConnector.getLMK(), z, HSMConnector.getPROVIDER(), "ECB", new byte[8]);

                object.setPIN(PIN);
                object.setPINBLOCK(ISOUtil.hexString(PINBLOCK));

                setProcessObject(object);
                return true;



            }
        }
        return false;

    }

    public static boolean doManualKeyInjection(Process_KEYBean object) throws Exception {

        boolean ok = false;

        Key genkey_tmk = genKey();
        byte eTMK[] = getEncrTMK(genkey_tmk);
        byte KVC_tmk[] = createKVC((SecretKey) genkey_tmk);

        byte[] tmk = decrypt((SecretKey) HSMConnector.getLMK(), eTMK, HSMConnector.getPROVIDER(), "ECB", new byte[8]);


        Key genkey_mek = genKey();
        byte eMEK[] = getEncrMEK(genkey_mek);
        byte KVC_mek[] = createKVC((SecretKey) genkey_mek);

        byte[] mek = decrypt((SecretKey) HSMConnector.getLMK(), eMEK, HSMConnector.getPROVIDER(), "ECB", new byte[8]);

        String key_id = generateClear_PIN() + generateClear_PIN();

        object.setETMK(ISOUtil.hexString(eTMK));
        object.setTMK_KVC(ISOUtil.hexString(KVC_tmk));
        object.setEMEK(ISOUtil.hexString(eMEK));
        object.setMEK_KVC(ISOUtil.hexString(KVC_mek));
        object.setKEYID(key_id);
        object.setTMK(ISOUtil.hexString(tmk));
        object.setMEK(ISOUtil.hexString(mek));
        setProcessObject(object);
        ok = true;

        return ok;

    }

    private static Key genKey() throws Exception {
        KeyGenerator desKeyGen = KeyGenerator.getInstance("DESede", HSMConnector.getPROVIDER());
        desKeyGen.init(128);
        return desKeyGen.generateKey();
    }

    private static byte[] getEncrFWK(Key des2Key) throws Exception {
        return HSMConnector.getWRAPKEYSTORE().wrapKey(HSMConnector.getLMK(), "DESede/ECB/NoPadding", des2Key);
    }


    private static byte[] getEncrTMK(Key des2Key) throws Exception {
        return HSMConnector.getWRAPKEYSTORE().wrapKey(HSMConnector.getBDK(), "DESede/ECB/NoPadding", des2Key);
    }

    private static byte[] getEncrMEK(Key des2Key) throws Exception {
        return HSMConnector.getWRAPKEYSTORE().wrapKey(HSMConnector.getBDK(), "DESede/ECB/NoPadding", des2Key);
    }

    private static byte[] createKVC(SecretKey skey) throws Exception {
        byte kvc[] = encrypt(skey, new byte[24], HSMConnector.getPROVIDER(), "ECB", new byte[8]);
        return ISOUtil.hex2byte(ISOUtil.hexString(kvc, 0, 3));
    }

    private static byte[] getClearFWK(byte eISKsessionkey[]) throws Exception {
        return decrypt((SecretKey) HSMConnector.getLMK(), eISKsessionkey, HSMConnector.getPROVIDER(), "ECB", new byte[8]);
    }

    private static byte[] decrypt(SecretKey secretKey, byte[] ciperBytes,
            Provider p, String mode, byte[] IV) throws Exception {

        if ("CBC".equals(mode)) {
            Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding", p.getName());
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(
                    IV));
            return cipher.doFinal(ciperBytes);
        } else {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding", p.getName());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return cipher.doFinal(ciperBytes);
        }

    }

    private static byte[] encrypt(SecretKey secretKey, byte[] clearBytes,
            Provider p, String mode, byte[] IV) throws Exception {

        if ("CBC".equals(mode)) {
            Cipher cipher = Cipher.getInstance("DESede/CBC/NoPadding", p.getName());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, new IvParameterSpec(
                    IV));
            return cipher.doFinal(clearBytes);
        } else {
            Cipher cipher = Cipher.getInstance("DESede/ECB/NoPadding", p.getName());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return cipher.doFinal(clearBytes);
        }

    }

    public static String generatSerialNumber() throws Exception {
        Random ran = new Random();
        String ranv = ran.nextInt(1000000) + "";
        String ranv1 = ran.nextInt(1000000) + "";
        return ISOUtil.zeropad(ranv + ranv1, 12).substring(0, 10);

    }
}
