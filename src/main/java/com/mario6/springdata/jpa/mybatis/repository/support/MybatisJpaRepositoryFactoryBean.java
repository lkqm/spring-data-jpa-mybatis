package com.mario6.springdata.jpa.mybatis.repository.support;

import lombok.Setter;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.jpa.repository.support.JpaRepositoryFactoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.core.support.RepositoryFactorySupport;

import javax.persistence.EntityManager;
import java.io.Serializable;

public class MybatisJpaRepositoryFactoryBean<T extends Repository<S, ID>, S, ID extends Serializable>
        extends JpaRepositoryFactoryBean<T, S, ID> {

    @Setter
    private SqlSessionTemplate sqlSessionTemplate;

    public MybatisJpaRepositoryFactoryBean(Class<? extends T> repositoryInterface) {
        super(repositoryInterface);
    }

    @Override
    protected RepositoryFactorySupport createRepositoryFactory(EntityManager em) {
        return new MybatisJpaRepositoryFactory(em, sqlSessionTemplate);
    }
}