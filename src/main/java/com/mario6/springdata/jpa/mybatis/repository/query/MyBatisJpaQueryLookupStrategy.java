package com.mario6.springdata.jpa.mybatis.repository.query;

import com.mario6.springdata.jpa.mybatis.repository.MybatisQuery;
import com.sun.istack.internal.Nullable;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.provider.QueryExtractor;
import org.springframework.data.jpa.repository.query.JpaQueryLookupStrategy;
import org.springframework.data.projection.ProjectionFactory;
import org.springframework.data.repository.core.NamedQueries;
import org.springframework.data.repository.core.RepositoryMetadata;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.RepositoryQuery;

import javax.persistence.EntityManager;
import java.lang.reflect.Method;

/**
 * 动态查询方法
 */
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class MyBatisJpaQueryLookupStrategy implements QueryLookupStrategy {

    private final SqlSessionTemplate sessionTemplate;
    private final QueryLookupStrategy jpaQueryLookupStrategy;

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
