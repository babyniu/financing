package com.github.financing.utils;

import org.bouncycastle.util.encoders.Base64;

import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;

/**
 * Created by Crazz on 2/2/16.
 */
public class CipherUtils {

    static {
        try {
            java.security.Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String rsaSign(String privateKeyStr,String strToSign){
        String result = null;
        try {
            byte[] tByte;
            Signature signature = Signature.getInstance("SHA1withRSA","BC");
            KeyFactory kf = KeyFactory.getInstance("RSA", "BC");
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.decode(privateKeyStr));
            PrivateKey privateKey = kf.generatePrivate(keySpec);
            signature.initSign(privateKey);
            signature.update(strToSign.getBytes("UTF-8"));
            tByte = signature.sign();
            result = new String(Base64.encode(tByte),"UTF-8");
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    public static boolean rsaVerify(String strToSign,String sign){
        return true;
    }
}
