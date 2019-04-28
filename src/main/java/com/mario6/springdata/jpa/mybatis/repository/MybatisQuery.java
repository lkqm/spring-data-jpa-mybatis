package com.mario6.springdata.jpa.mybatis.repository;

import org.springframework.data.annotation.QueryAnnotation;

import java.lang.annotation.*;

/**
 * 标记方法由mybatis执行方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.METHOD })
@QueryAnnotation
@Documented
public @interface MybatisQuery {
}
