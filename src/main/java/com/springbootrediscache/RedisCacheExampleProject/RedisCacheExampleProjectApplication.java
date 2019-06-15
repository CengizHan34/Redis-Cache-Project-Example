package com.springbootrediscache.RedisCacheExampleProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class RedisCacheExampleProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(RedisCacheExampleProjectApplication.class, args);
    }

}
