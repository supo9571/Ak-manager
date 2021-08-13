package com.job.executor.handler;

import com.job.core.handler.annotation.XxlJob;
import org.springframework.stereotype.Component;

@Component
public class job {

    /**
     * 1、简单任务示例（Bean模式）
     */
    @XxlJob("test001")
    public void demoJobHandler(){
        System.out.println("测试001");
    }
}
