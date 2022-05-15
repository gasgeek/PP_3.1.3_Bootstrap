package ru.kata.spring.boot_security.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootSecurityDemoApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(SpringBootSecurityDemoApplication.class, args);
    }
}