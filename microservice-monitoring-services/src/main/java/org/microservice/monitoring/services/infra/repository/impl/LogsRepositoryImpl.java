package org.microservice.monitoring.services.infra.repository.impl;

import org.hzero.mybatis.base.impl.BaseRepositoryImpl;
import org.microservice.monitoring.services.domain.entity.Logs;
import org.microservice.monitoring.services.domain.repository.LogsRepository;
import org.springframework.stereotype.Component;

/**
 *  资源库实现
 *
 * @author miaoyang.chen@hand-china.com 2020-04-22 13:30:06
 */
@Component
public class LogsRepositoryImpl extends BaseRepositoryImpl<Logs> implements LogsRepository {

  
}
