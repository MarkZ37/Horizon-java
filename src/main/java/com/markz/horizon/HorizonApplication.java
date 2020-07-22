package com.markz.horizon;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.markz.horizon.mapper")
public class HorizonApplication {

    public static void main(String[] args) {
        SpringApplication.run(HorizonApplication.class, args);
    }

}
