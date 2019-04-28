package com.mario6.springdata.jpa.mybatis.repository.demo;

import com.mario6.springdata.jpa.mybatis.repository.config.EnableMybatisJpaRepositories;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableMybatisJpaRepositories
// @MapperScan("com.mario6.springdata.jpa.mybatis.repository.demo.repository")
public class MybatisJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MybatisJpaApplication.class);
    }

}