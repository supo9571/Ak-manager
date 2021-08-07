package com.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 启动程序
 * 
 * @author marvin
 */
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class })
public class ManagerApplication
{
    public static void main(String[] args)
    {
        // System.setProperty("spring.devtools.restart.enabled", "false");
        SpringApplication.run(ManagerApplication.class, args);
        System.out.println("(♥◠‿◠)ﾉﾞ  后台管理系统启动成功   ლ(´ڡ`ლ)ﾞ");
    }
}
