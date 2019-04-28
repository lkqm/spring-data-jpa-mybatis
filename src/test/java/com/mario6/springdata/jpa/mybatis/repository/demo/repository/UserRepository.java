package com.mario6.springdata.jpa.mybatis.repository.demo.repository;


import com.mario6.springdata.jpa.mybatis.repository.MybatisQuery;
import com.mario6.springdata.jpa.mybatis.repository.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

    @MybatisQuery
    User selectByAccount(String account);

}
