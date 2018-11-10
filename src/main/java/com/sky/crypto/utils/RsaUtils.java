package com.sky.crypto.utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * @author CarryJey
 * @since 2018-11-10
 */
public class RsaUtils {

    private static final int ENCRYPT_MAX_LENGTH = 245;

    private static final int DECRYPT_MAX_LENGTH = 256;

    private RsaUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static PublicKey getPublicKey(String publicKeyString) {
        try {
            byte[] publicKeyBytes = Base64.getDecoder().decode(publicKeyString.replace("\n", ""));
            X509EncodedKeySpec spec = new X509EncodedKeySpec(publicKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePublic(spec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static PrivateKey getPrivateKey(String privateKeyString) {
        try {
            byte[] privateKeyBytes = Base64.getDecoder().decode(privateKeyString.replace("\n", ""));
            PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
            KeyFactory kf = KeyFactory.getInstance("RSA");
            return kf.generatePrivate(spec);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] encrypt(PublicKey publicKey, String message) {
        return encrypt(publicKey, message.getBytes(StandardCharsets.UTF_8));
    }

    public static byte[] encrypt(PublicKey publicKey, byte[] message) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            if (message.length <= ENCRYPT_MAX_LENGTH) {
                return cipher.doFinal(message);
            }

            return sectionDoFinal(cipher, message, ENCRYPT_MAX_LENGTH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static byte[] decrypt(PrivateKey privateKey, byte[] encrypted) {
        try {
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, privateKey);
            if (encrypted.length <= DECRYPT_MAX_LENGTH) {
                return cipher.doFinal(encrypted);
            }

            return sectionDoFinal(cipher, encrypted, DECRYPT_MAX_LENGTH);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    //分段处理
    private static byte[] sectionDoFinal(Cipher cipher, byte[] message, int maxLength) throws Exception {
        //偏移量
        int offset = 0;
        //未解密长度 初期就是数据的长度
        int left = message.length;
        try (ByteArrayOutputStream bops = new ByteArrayOutputStream()) {
            //分段解密
            while (left > 0) {
                byte[] cache;
                if (left > maxLength) {
                    cache = cipher.doFinal(message, offset, maxLength);
                } else {
                    cache = cipher.doFinal(message, offset, left);
                }
                bops.write(cache);
                left -= maxLength;
                offset += maxLength;
            }
            return bops.toByteArray();
        }
    }
}
