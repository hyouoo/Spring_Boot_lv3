package com.sparta.lv3;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class Lv3Application {

    public static void main(String[] args) {
        SpringApplication.run(Lv3Application.class, args);
    }

}
