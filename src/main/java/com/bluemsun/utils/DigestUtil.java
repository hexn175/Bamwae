package com.bluemsun.utils;

import org.bouncycastle.jce.provider.BouncyCastleProvider;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.AlgorithmParameters;
import java.security.Key;
import java.security.Security;
import java.util.Base64;

public class DigestUtil {
    public String decrypt(String session_key, String iv, String encryptData) {

        String decryptString = "";
        //解码经过 base64 编码的字符串
        byte[] sessionKeyByte = Base64.getDecoder().decode(session_key);
        byte[] ivByte = Base64.getDecoder().decode(iv);
        byte[] encryptDataByte = Base64.getDecoder().decode(encryptData);

        try {
            Security.addProvider(new BouncyCastleProvider());
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS7Padding");
            //得到密钥
            Key key = new SecretKeySpec(sessionKeyByte, "AES");
            //AES 加密算法
            AlgorithmParameters algorithmParameters = AlgorithmParameters.getInstance("AES");
            algorithmParameters.init(new IvParameterSpec(ivByte));
            cipher.init(Cipher.DECRYPT_MODE, key, algorithmParameters);
            byte[] bytes = cipher.doFinal(encryptDataByte);
            decryptString = new String(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return decryptString;
    }

}
