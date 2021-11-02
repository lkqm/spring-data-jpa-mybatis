package com.github.lkqm.spring.jpa.repository.support;

import com.github.lkqm.spring.jpa.repository.query.MyBatisJpaQueryLookupStrategy;
import java.util.Optional;
import javax.persistence.EntityManager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactory;
import org.springframework.data.repository.query.QueryLookupStrategy;
import org.springframework.data.repository.query.QueryMethodEvaluationContextProvider;
import org.springframework.util.Assert;

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
