package com.github.lkqm.springdata.jpa.mybatis.repository.demo;

import com.github.lkqm.springdata.jpa.mybatis.repository.config.EnableMybatisJpaRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMybatisJpaRepositories
public class MybatisJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisJpaApplication.class);
    }

}
