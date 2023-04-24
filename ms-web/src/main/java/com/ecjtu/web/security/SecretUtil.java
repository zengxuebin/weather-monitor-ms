package com.ecjtu.web.security;

import org.apache.tomcat.util.codec.binary.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * @Description: 前端加密解密
 * @Author: ZengXueBin
 * @Date: 2023/4/22 22:15
 */
public class SecretUtil {

    /**
     * 密钥
     */
    private static final String KEY = "7ahf7d0zhhviyqaz";

    /**
     * 加密
     * @param data 明文
     * @return 密文
     */
    public static String encrypt(String data){
        try {
            // 算法/模式/补码方式
            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            int blockSize = cipher.getBlockSize();

            byte[] dataBytes = data.getBytes();
            int plaintextLength = dataBytes.length;
            if (plaintextLength % blockSize != 0) {
                plaintextLength = plaintextLength + (blockSize - (plaintextLength % blockSize));
            }

            byte[] plaintext = new byte[plaintextLength];
            System.arraycopy(dataBytes, 0, plaintext, 0, dataBytes.length);

            SecretKeySpec keySpec = new SecretKeySpec(SecretUtil.KEY.getBytes(), "AES");

            cipher.init(Cipher.ENCRYPT_MODE, keySpec);
            byte[] encrypted = cipher.doFinal(plaintext);

            return new Base64().encodeAsString(encrypted);

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     * @param data 密文
     * @return 明文
     */
    public static String desEncrypt(String data){
        try {
            byte[] encrypted = new Base64().decode(data);

            Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
            SecretKeySpec keySpec = new SecretKeySpec(SecretUtil.KEY.getBytes(), "AES");

            cipher.init(Cipher.DECRYPT_MODE, keySpec);
            byte[] original = cipher.doFinal(encrypted);
            return new String(original).trim();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
