package com.github.lkqm.springdata.jpa.mybatis.repository.query;

import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryMethod;

import java.lang.reflect.Method;

/**
 * 查询方法的抽象
 */
public class MyBatisQueryMethod extends QueryMethod {

    private final Class<?> mapperInterface;
    private final Method method;

    public MyBatisQueryMethod(Method method, RepositoryMetadata metadata, ProjectionFactory factory) {
        super(method, metadata, factory);
        this.method = method;
        mapperInterface = metadata.getRepositoryInterface();
    }

    public Class<?> getRepositoryInterface() {
        return mapperInterface;
    }

    public Method getMethod() {
        return method;
    }

    public String getNamedQueryName() {
        return null;
    }

}
