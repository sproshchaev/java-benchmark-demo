package com.prosoft.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.prosoft.core", "com.prosoft.springboot"})
public class SpringBootBenchmarkApp {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootBenchmarkApp.class, args);
    }
}