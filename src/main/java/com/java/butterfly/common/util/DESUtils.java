package com.java.butterfly.common.util;

import java.security.Key;
import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;

import org.apache.commons.codec.binary.Base64;

public class DESUtils {
    private static Key key;
    
    private static String KEY_STR = "CMSDBENCRYPTKEY";
    static {
        try {
            KeyGenerator generator = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(KEY_STR.getBytes());
            generator.init(secureRandom);
            key = generator.generateKey();
            generator = null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    /**
     * 对字符串进行加密，返回BASE64的加密字符串 <功能详细描述>
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("static-access")
    public static String getEncryptString(String str) {
        Base64 base64Encoder = new Base64();
        // System.out.println(key);
        try {
            byte[] strBytes = str.getBytes("UTF-8");
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            String s = new String(base64Encoder.encodeBase64(encryptStrBytes, true));
            return s;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    /**
     * 对BASE64加密字符串进行解密 <功能详细描述>
     * 
     * @param str
     * @return
     * @see [类、类#方法、类#成员]
     */
    public static String getDecryptString(String str) {
        
        try {
            byte[] strBytes = Base64.decodeBase64(str.getBytes());
            Cipher cipher = Cipher.getInstance("DES");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] encryptStrBytes = cipher.doFinal(strBytes);
            return new String(encryptStrBytes, "UTF-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }
    
    public static void main(String[] args) {
        String name = "sitclf";
        String password = "eiwEj6sY8mY9sUne";
        String encryname = getEncryptString(name);
        String encrypassword = getEncryptString(password);
        System.out.println(encryname);
        System.out.println(encrypassword);
        System.out.println(getDecryptString(encryname));
        System.out.println(getDecryptString(encrypassword));
    }
}