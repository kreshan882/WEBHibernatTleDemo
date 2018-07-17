package com.epic.tle.FieldEngineerManagement.smartcard;

import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.Security;
import java.security.spec.RSAPublicKeySpec;

public class RsaOperation {

    private static final BigInteger emptyBigInt = new BigInteger("0");
    private BigInteger mModulus;
    private BigInteger mExponent;

    private RsaOperation(BigInteger pModulus, BigInteger pExponent) {
        mModulus = pModulus;
        mExponent = pExponent;
    }

    public static PublicKey getPublicKey(String e, String m) throws Exception {

        Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());

        BigInteger keyModulus = new BigInteger(m, 16);
        BigInteger keyExponent = new BigInteger(e, 16);

        RsaOperation rsaop = new RsaOperation(keyModulus, keyExponent);

        RSAPublicKeySpec publicSpec = new RSAPublicKeySpec(rsaop.getModulus(),
                rsaop.getExponent());
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
        PublicKey publicKey = keyFactory.generatePublic(publicSpec);

        return publicKey;
    }

    private BigInteger getModulus() {
        if (mModulus == null) {
            return emptyBigInt;
        } else {
            return mModulus;
        }
    }

    private BigInteger getExponent() {
        if (mExponent == null) {
            return emptyBigInt;
        } else {
            return mExponent;
        }
    }

    public String toString() {
        return mModulus.toString(16).toUpperCase() + "\n" + mExponent.toString(16).toUpperCase();
    }
}
