package com.job.executor.datasource;

public enum DataSourceType {

	// 主表
	Master("master"),
	// 从表1
	Slave1("slave1"),
	// 从表2
	Slave2("slave2");

	private String name;

	private DataSourceType(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
