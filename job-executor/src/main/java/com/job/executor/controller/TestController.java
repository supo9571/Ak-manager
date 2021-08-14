package com.job.executor.controller;

import com.job.executor.service.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@Slf4j
public class TestController {


	@Autowired
	private TestService testService;

	@RequestMapping("/test")
	public String index() {
		log.debug("测试信息：{}","welcome log world");
		return "主表："+testService.queryCountByMester();
	}

	@RequestMapping("/test1")
	public String test() {
		return "从表："+testService.queryCountBySavle();
	}


}
