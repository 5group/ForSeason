package com.shop.frame;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class CryptoUtil {
    public static String encryptAES256(String str, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {
        byte[] keyBytes = new byte[16];
        System.arraycopy(key.getBytes("utf-8"), 0, keyBytes, 0, keyBytes.length);
        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");
        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String iv = key.substring(0, 16);
        byte[] ivBytes = new byte[16];
        System.arraycopy(iv.getBytes("utf-8"), 0, ivBytes, 0, ivBytes.length);
        c.init(Cipher.ENCRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));
        byte[] encrypted = c.doFinal(str.getBytes("utf-8"));
        String enStr = Base64.getEncoder().encodeToString(encrypted);
        return enStr;
    }

    public static String decryptAES256(String str, String key)
            throws UnsupportedEncodingException, NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException,
            InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException {

        byte[] keyBytes = new byte[16];

        System.arraycopy(key.getBytes("utf-8"), 0, keyBytes, 0, keyBytes.length);

        SecretKeySpec keySpec = new SecretKeySpec(keyBytes, "AES");

        Cipher c = Cipher.getInstance("AES/CBC/PKCS5Padding");

        String iv = key.substring(0, 16);

        byte[] ivBytes = new byte[16];
        System.arraycopy(iv.getBytes("utf-8"), 0, ivBytes, 0, ivBytes.length);

        c.init(Cipher.DECRYPT_MODE, keySpec, new IvParameterSpec(ivBytes));

        // 암호화된 데이터를 decoding 한다.
        byte[] byteStr = Base64.getDecoder().decode(str);

        return new String(c.doFinal(byteStr), "utf-8");

    }
}
