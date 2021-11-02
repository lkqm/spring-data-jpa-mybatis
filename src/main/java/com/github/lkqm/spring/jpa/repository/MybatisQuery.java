package com.github.lkqm.spring.jpa.repository;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.data.annotation.QueryAnnotation;

/**
 * 标记方法由mybatis执行方法
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@QueryAnnotation
@Documented
public @interface MybatisQuery {
}
