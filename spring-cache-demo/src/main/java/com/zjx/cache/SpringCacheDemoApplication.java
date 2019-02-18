package com.zjx.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableCaching
@EnableTransactionManagement
public class SpringCacheDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringCacheDemoApplication.class, args);
    }

}

