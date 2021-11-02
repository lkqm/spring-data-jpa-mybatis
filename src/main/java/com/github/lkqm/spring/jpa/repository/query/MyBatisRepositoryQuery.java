package com.github.lkqm.spring.jpa.repository.query;

import java.lang.reflect.Method;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

/**
 * Mybatis的查询逻辑
 */
public class MyBatisRepositoryQuery implements RepositoryQuery {

    private final MyBatisQueryMethod queryMethod;
    private final SqlSessionTemplate sessionTemplate;

    public MyBatisRepositoryQuery(MyBatisQueryMethod queryMethod, SqlSessionTemplate sessionTemplate) {
        Assert.notNull(queryMethod, "MyBatisQueryMethod must not be null");
        Assert.notNull(sessionTemplate, "SqlSessionTemplate must not be null");
        this.queryMethod = queryMethod;
        this.sessionTemplate = sessionTemplate;
    }

    @Override
    public Object execute(Object[] parameters) {
        Method method = queryMethod.getMethod();
        Object mapper = sessionTemplate.getMapper(queryMethod.getRepositoryInterface());
        return ReflectionUtils.invokeMethod(method, mapper, parameters);
    }

    @Override
    public QueryMethod getQueryMethod() {
        return queryMethod;
    }
}
