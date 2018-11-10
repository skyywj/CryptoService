package com.sky.crypto.service;

import com.sky.crypto.utils.RsaUtils;
import com.sky.hypro.service.exception.ErrorCode;
import com.sky.hypro.service.exception.SkyException;
import com.sky.hypro.service.tools.CryptoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * @author yanwenjie
 * @since 2018/11/1
 */
@Component
public class CryptoServiceImpl implements CryptoService {

    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    private PublicKey contactMatchPublicKey;
    private PrivateKey contactMatchPrivateKey;

    @Value("${sky.test.public-key}")
    public void setContactMatchPublicKey(String contactMatchPublicKey) {
        this.contactMatchPublicKey = RsaUtils.getPublicKey(contactMatchPublicKey);
    }

    @Value("${sky.test.private-key}")
    public void setContactMatchPrivateKey(String contactMatchPrivateKey) {
        this.contactMatchPrivateKey = RsaUtils.getPrivateKey(contactMatchPrivateKey);
    }

    @Override
    public byte[] rsaDecrypt(int cryptoScene, byte[] data) {
        PrivateKey key = selectPrivateKey(cryptoScene);
        try {
            byte[] decryptData = RsaUtils.decrypt(key, data);
            return decryptData;
        } catch (Exception e) {
            logger.warn("Rsa Decrypt error,scene: {},data: {}",cryptoScene,data);
            throw new SkyException(ErrorCode.CRYPTO_ERROR);
        }
    }

    @Override
    public byte[] rsaEncrypt(int cryptoScene, byte[] data) {
        PublicKey key = selectPublicKey(cryptoScene);
        try {
            byte[] encryptData = (byte[]) RsaUtils.encrypt(key, data);
            return encryptData;
        } catch (Exception e) {
            logger.warn("Rsa Encrypt error,scene: {},data: {}",cryptoScene,data);
            throw new SkyException(ErrorCode.CRYPTO_ERROR);
        }
    }

    @Override
    public byte[] rsaEncryptString(int cryptoScene, String data) {
        PublicKey key = selectPublicKey(cryptoScene);
        try {
            byte[] encryptData = RsaUtils.encrypt(key, data);
            return encryptData;
        } catch (Exception e) {
            logger.warn("Rsa Encrypt error,scene: {},data: {}",cryptoScene,data);
            throw new SkyException(ErrorCode.CRYPTO_ERROR);
        }
    }

    /**
     * 根据不同的场景选取不同的解密私钥
     */
    public PublicKey selectPublicKey(int cryptoScene) {
        if (cryptoScene == CRYPTO_SCENE_TEST) {
            return this.contactMatchPublicKey;
        } else {
            logger.warn("Unknown Scene For Encrypt: ",cryptoScene);
            throw new SkyException(ErrorCode.CRYPTO_ERROR);
        }
    }

    /**
     * 根据不同的场景选取不同的解密私钥
     */
    public PrivateKey selectPrivateKey(int cryptoScene) {
        if (cryptoScene == CRYPTO_SCENE_TEST) {
            return this.contactMatchPrivateKey;
        } else {
            logger.warn("Unknown Scene For Decrypt: {} ",cryptoScene);
            throw new SkyException(ErrorCode.CRYPTO_ERROR);
        }
    }
}
