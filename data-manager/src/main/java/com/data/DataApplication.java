package com.data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableFeignClients
public class DataApplication {

	public static void main(String[] args) {
        SpringApplication.run(DataApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  数据统计系统启动成功!!!   ლ(´ڡ`ლ)ﾞ");
	}

}
