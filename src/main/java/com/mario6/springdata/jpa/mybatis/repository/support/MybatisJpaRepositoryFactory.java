package com.mario6.springdata.jpa.mybatis.repository.support;

import com.mario6.springdata.jpa.mybatis.repository.query.MyBatisJpaQueryLookupStrategy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.EvaluationContextProvider;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;

public class MybatisJpaRepositoryFactory extends JpaRepositoryFactory {

    private SqlSessionTemplate sessionTemplate;

    public MybatisJpaRepositoryFactory(EntityManager entityManager, SqlSessionTemplate sessionTemplate) {
        super(entityManager);
        Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null");
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    protected QueryLookupStrategy getQueryLookupStrategy(QueryLookupStrategy.Key key, EvaluationContextProvider evaluationContextProvider) {
        QueryLookupStrategy jpaQueryLookupStrategy = super.getQueryLookupStrategy(key, evaluationContextProvider);
        return MyBatisJpaQueryLookupStrategy.create(jpaQueryLookupStrategy, sessionTemplate);
    }
}
