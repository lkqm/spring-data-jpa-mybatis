package com.github.lkqm.spring.jpa.repository.support;

import java.io.Serializable;
import javax.persistence.EntityManager;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;
import org.springframework.util.Assert;

public class MybatisJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    private SqlSessionTemplate sqlSessionTemplate;

    public MybatisJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new MybatisJpaRepositoryFactory(em, sqlSessionTemplate);
    }

    public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
        this.sqlSessionTemplate = sqlSessionTemplate;
    }

    @Override
    public void afterPropertiesSet() {
        super.afterPropertiesSet();
        Assert.state(this.sqlSessionTemplate != null, "SqlSessionTemplate must not be null!");
    }
}
