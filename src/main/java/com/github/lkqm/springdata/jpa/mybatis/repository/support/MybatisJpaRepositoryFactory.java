package com.github.lkqm.springdata.jpa.mybatis.repository.support;

import com.github.lkqm.springdata.jpa.mybatis.repository.query.MyBatisJpaQueryLookupStrategy;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.util.Optional;

public class MybatisJpaRepositoryFactory extends JpaRepositoryFactory {

    private SqlSessionTemplate sessionTemplate;

    public MybatisJpaRepositoryFactory(EntityManager entityManager, SqlSessionTemplate sessionTemplate) {
        super(entityManager);
        Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null");
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    protected Optional<QueryLookupStrategy> getQueryLookupStrategy(QueryLookupStrategy.Key key, QueryMethodEvaluationContextProvider evaluationContextProvider) {
        Optional<QueryLookupStrategy> jpaQueryLookupStrategy = super.getQueryLookupStrategy(key, evaluationContextProvider);
        return Optional.of(MyBatisJpaQueryLookupStrategy.create(jpaQueryLookupStrategy.get(), sessionTemplate));
    }
}
