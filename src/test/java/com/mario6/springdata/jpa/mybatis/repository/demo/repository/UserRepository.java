package com.mario6.springdata.jpa.mybatis.repository.demo.repository;


import com.mario6.springdata.jpa.mybatis.repository.MybatisQuery;
import com.mario6.springdata.jpa.mybatis.repository.demo.domain.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.repository.CrudRepository;

@Mapper
public interface UserRepository extends CrudRepository<User, Integer> {

    @MybatisQuery
    @Select("select * from user where account = #{account}")
    User selectByAccount(String account);

    @MybatisQuery
    int insert(User user);

}
