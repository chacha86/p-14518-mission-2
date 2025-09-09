package com.sbb_1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing //날짜 자동으로
public class Sbb1Application {

    public static void main(String[] args) {
        SpringApplication.run(Sbb1Application.class, args);
    }

}
