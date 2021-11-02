/*
 * Copyright 2012-2019 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.github.lkqm.spring.jpa.repository.config;

import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.core.annotation.AnnotationAttributes;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.data.repository.config.AnnotationRepositoryConfigurationSource;
import org.springframework.util.StringUtils;

/**
 * JPA-MYBATIS自定义扩展配置
 */
public class MybatisJpaRepositoryConfigExtension extends JpaRepositoryConfigExtension {

    @Override
    public void postProcess(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {
        super.postProcess(builder, config);
        postProcessMybatisJpa(builder, config);
    }

    private void postProcessMybatisJpa(BeanDefinitionBuilder builder, AnnotationRepositoryConfigurationSource config) {
        AnnotationAttributes attributes = config.getAttributes();
        String sqlSessionTemplate = attributes.getString("sqlSessionTemplateRef");
        if (!StringUtils.hasText(sqlSessionTemplate)) {
            sqlSessionTemplate = "sqlSessionTemplate";
        }
        builder.addPropertyReference("sqlSessionTemplate", sqlSessionTemplate);
    }

}
