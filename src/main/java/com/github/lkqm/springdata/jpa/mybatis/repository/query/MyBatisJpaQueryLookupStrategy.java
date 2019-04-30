package com.github.lkqm.springdata.jpa.mybatis.repository.query;

import com.github.lkqm.springdata.jpa.mybatis.repository.MybatisQuery;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;

import java.lang.reflect.Method;


public class MyBatisJpaQueryLookupStrategy implements QueryLookupStrategy {

    private final SqlSessionTemplate sessionTemplate;
    private final QueryLookupStrategy jpaQueryLookupStrategy;

    public MyBatisJpaQueryLookupStrategy(SqlSessionTemplate sessionTemplate, QueryLookupStrategy jpaQueryLookupStrategy) {
        Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null");
        Assert.notNull(jpaQueryLookupStrategy, "QueryLookupStrategy must not be null");
        this.sessionTemplate = sessionTemplate;
        this.jpaQueryLookupStrategy = jpaQueryLookupStrategy;
    }

    @Override
    public RepositoryQuery resolveQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        if (isMethodToMybatisHandle(method)) {
            return resolveMybatisQuery(method, metadata, factory, namedQueries);
        }
        return jpaQueryLookupStrategy.resolveQuery(method, metadata, factory, namedQueries);
    }

    private boolean isMethodToMybatisHandle(Method method) {
        MybatisQuery mybatisQuery = method.getAnnotation(MybatisQuery.class);
        return mybatisQuery != null;
    }

    private RepositoryQuery resolveMybatisQuery(Method method, RepositoryMetadata metadata, ProjectionFactory factory, NamedQueries namedQueries) {
        MyBatisQueryMethod queryMethod = new MyBatisQueryMethod(method, metadata, factory);
        return new MyBatisRepositoryQuery(queryMethod, sessionTemplate);
    }

    /**
     * 创建查询策略
     */
    public static QueryLookupStrategy create(QueryLookupStrategy jpaQueryLookupStrategy, SqlSessionTemplate sessionTemplate) {
        return new MyBatisJpaQueryLookupStrategy(sessionTemplate, jpaQueryLookupStrategy);
    }
}
