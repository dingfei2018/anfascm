package com.thinkgem.jeesite.common.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import com.thinkgem.jeesite.common.annotation.DynamicSourcedb;
import com.thinkgem.jeesite.common.db.DynamicDataSource;

@Aspect
@Component
public class DynamicSourcedbAspect {
	@Around(value = "@annotation(dynamicSourcedb)")
	public Object dynamicdbAdvice(ProceedingJoinPoint proceedingJoinPoint, DynamicSourcedb dynamicSourcedb) throws Throwable {
		//System.out.println("Before invoking getName() method" + dynamicSourcedb.value());
		Object value = null;
		try {
			DynamicDataSource.setdataSource(dynamicSourcedb.value());
			value = proceedingJoinPoint.proceed();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DynamicDataSource.setdataSourceDefault();
		}
		//System.out.println("After invoking getName() method. Return value=" + value);
		return value;
	}

}
