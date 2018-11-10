package com.sky.crypto.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.lang.invoke.MethodHandles;

/**
 * @author CarryJey
 * @since 2018/11/10
 */
//测试时在dubbo.xml配置中添加reference配置
@SpringBootTest
@RunWith(SpringRunner.class)
@Component
public class TestCryptoServiceImpl {

    //    private static final Logger logger = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    //
    //    @Resource(name = "cryptoService1")
    //    private CryptoService cryptoService;
    //
    //    @Test
    //    public void testCryptoServiceImpl() {
    //        String data = "123456789132456789";
    //        byte[] bytes = cryptoService.rsaEncrypt(CryptoService.CRYPTO_SCENE_CONTACTS_MATCH, data.getBytes());
    //        logger.info("test encrypt success!");
    //        byte[] bytes1 = cryptoService.rsaDecrypt(CryptoService.CRYPTO_SCENE_CONTACTS_MATCH, bytes);
    //        try {
    //            String data1 = new String(bytes1, "utf-8");
    //            logger.info("test decrypt success: " + data1);
    //        } catch (Exception e) {
    //            logger.info("test decrpt failed!");
    //        }
    //    }
}
