package com.mario6.springdata.jpa.mybatis.repository.query;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.data.repository.query.QueryMethod;
import org.springframework.data.repository.query.RepositoryQuery;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class MyBatisRepositoryQuery implements RepositoryQuery {
	
	private final MyBatisQueryMethod queryMethod;
	private final SqlSessionTemplate sessionTemplate;
	
	public MyBatisRepositoryQuery(MyBatisQueryMethod queryMethod, SqlSessionTemplate sessionTemplate) {
        Assert.notNull(queryMethod, "Illegal argument queryMethod can't be null");
        Assert.notNull(sessionTemplate, "Illegal argument sessionTemplate can't be null");
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
