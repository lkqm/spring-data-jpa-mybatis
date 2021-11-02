package com.github.lkqm.spring.jpa.repository.demo;

import com.github.lkqm.spring.jpa.repository.config.EnableMybatisJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMybatisJpaRepositories
public class MybatisJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisJpaApplication.class);
    }

}
