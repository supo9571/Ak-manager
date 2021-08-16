package com.job.executor.datasource;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

/**
 * 数据源配置
 */
@Configuration
@Slf4j
public class DataSourceConfig {

	@Bean(name = "master")
	@ConfigurationProperties(prefix = "datasource.master")
	public DataSource dataSource1() {
		log.info("主表配置");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "slave1")
	@ConfigurationProperties(prefix = "datasource.slave1")
	public DataSource dataSource2() {
		log.info("从表1配置");
		return DataSourceBuilder.create().build();
	}

	@Bean(name = "slave2")
	@ConfigurationProperties(prefix = "datasource.slave2")
	public DataSource dataSource3() {
		log.info("从表2配置");
		return DataSourceBuilder.create().build();
	}

	@Bean(name="dynamicDataSource")
	@Primary	//优先使用，多数据源
	public DataSource dataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();
		DataSource master = dataSource1();
		DataSource slave1 = dataSource2();
		DataSource slave2 = dataSource3();
		//设置默认数据源
		dynamicDataSource.setDefaultTargetDataSource(master);

		//配置多数据源
		Map<Object,Object> map = new HashMap<>();
		map.put(DataSourceType.Master.getName(), master);	//key需要跟ThreadLocal中的值对应
		map.put(DataSourceType.Slave1.getName(), slave1);
		map.put(DataSourceType.Slave2.getName(), slave2);
		dynamicDataSource.setTargetDataSources(map);
		return dynamicDataSource;
	}

}
