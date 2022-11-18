package com.example.outfit7;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
@SpringBootApplication
public class Outfit7Application {

    public static void main(String[] args) {
        SpringApplication.run(Outfit7Application.class, args);
    }
}
