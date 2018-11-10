package com.sky.crypto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

/**
 * @author CarryJey
 * @since 2018/11/1
 */
@SpringBootApplication
@ImportResource("classpath:dubbo.xml")
public class CryptoService {
    public static void main(String... args) {
        SpringApplication.run(CryptoService.class, args);
    }
}
