package com.job.executor.datasource;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * AOP根据注解给上下文赋值
 */
@Aspect
@Order(1)	//设置AOP执行顺序(需要在事务之前，否则事务只发生在默认库中)
@Component
@Slf4j
public class DataSourceAspect {
	//切点
	@Pointcut("execution(* com.job.executor.service..*.*(..)))")
	public void aspect() { }

	@Before("aspect()")
	private void before(JoinPoint point) {
		Object target = point.getTarget();
        String method = point.getSignature().getName();
        Class<?> classz = target.getClass();
        Class<?>[] parameterTypes = ((MethodSignature) point.getSignature())
                .getMethod().getParameterTypes();
        try {
            Method m = classz.getMethod(method, parameterTypes);
            if (m != null && m.isAnnotationPresent(DataSource.class)) {
            	DataSource data = m.getAnnotation(DataSource.class);
                JdbcContextHolder.putDataSource(data.value().getName());
                log.info("===============上下文赋值完成:{}",data.value().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

	}
}
