package com.job.executor.datasource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

@Slf4j
public class DynamicDataSource extends AbstractRoutingDataSource {

	@Override
	protected Object determineCurrentLookupKey() {
		log.info("数据源为{}",JdbcContextHolder.getDataSource());
		return JdbcContextHolder.getDataSource();
	}

}
