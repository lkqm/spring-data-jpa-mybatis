package com.github.lkqm.spring.jpa.repository.demo.repository;


import com.github.lkqm.spring.jpa.repository.MybatisQuery;
import com.github.lkqm.spring.jpa.repository.demo.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.JpaRepository;

@Mapper
public interface UserRepository extends JpaRepository<User, Integer> {

    @MybatisQuery
    @Select("select * from user where account = #{account} limit 1")
    User selectByAccount(String account);

    @MybatisQuery
    int insert(User user);

    default List<User> listUsers() {
        return this.findAll();
    }
}
