package com.job.executor.service.impl;

import com.job.executor.datasource.DataSourceType;
import com.job.executor.datasource.MyDataSource;
import com.job.executor.mapper.TestMapper;
import com.job.executor.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class TestServiceImpl implements TestService {
	@Autowired
	private TestMapper testMapper;

	@Override
	@MyDataSource(DataSourceType.Master)
	public Integer queryCountByMester() {
		return testMapper.queryCount();
	}


	@Override
	@MyDataSource(DataSourceType.Slave)
	@Transactional
	public Integer queryCountBySavle() {
		//测试事务
//		testMapper.updateAdminByName("666");
		Integer rows = testMapper.updateAdminByName("777");
//		if(rows<=0) {	//更新小于1 执行回滚
//			throw new RuntimeException();
//		}
		return rows;
	}

}
