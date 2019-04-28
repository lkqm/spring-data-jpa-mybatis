package com.mario6.springdata.jpa.mybatis.repository.demo.domain;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 */
@Data
@Entity
public class User implements Serializable {

    @Id
    @GeneratedValue
    private Integer id;

    /**
     * 账户
     */
    private String account;

    /**
     * 密码
     */
    private String password;

    /**
     * 名称
     */
    private String name;

    /**
     * 创建日期
     */
    private Date createTime;

}
