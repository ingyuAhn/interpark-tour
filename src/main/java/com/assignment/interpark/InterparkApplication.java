package com.assignment.interpark;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication(scanBasePackages = "com.assignment.*")
public class InterparkApplication {

    public static void main(String[] args) {
        SpringApplication.run(InterparkApplication.class, args);
    }

}
