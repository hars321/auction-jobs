package com.intuit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.intuit")
public class JobsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(JobsServiceApplication.class, args);
    }
}
