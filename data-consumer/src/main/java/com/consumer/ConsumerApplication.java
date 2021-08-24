package com.consumer;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@MapperScan("com.consumer.mapper")
public class ConsumerApplication {

	public static void main(String[] args) {
        SpringApplication.run(ConsumerApplication.class, args);
		System.out.println("(♥◠‿◠)ﾉﾞ  kafka消费者启动成功!!!   ლ(´ڡ`ლ)ﾞ");
	}

}
