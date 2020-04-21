package org.microservice.monitoring.services.infra.repository.impl;

import org.microservice.monitoring.services.domain.entity.Example;
import org.microservice.monitoring.services.domain.repository.ExampleRepository;
import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.springframework.stereotype.Repository;

/**
 * Repository Impl
 */
@Repository
public class ExampleRepositoryImpl extends BaseRepositoryImpl<Example> implements ExampleRepository {

}
