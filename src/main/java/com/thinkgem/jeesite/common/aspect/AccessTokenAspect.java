package com.thinkgem.jeesite.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.thinkgem.jeesite.common.service.ApiService;

@Aspect
@Component
public class AccessTokenAspect {

	@Autowired
	private ApiService apiService;

	@Around("@annotation(com.thinkgem.jeesite.common.annotation.AccessToken)")
	public Object doAccessCheck(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		System.out.println("id: "+id + "-" + "token: "+token);
		Object object = pjp.proceed(); 
		return object;
	}
	public Object doAccessCheck2(ProceedingJoinPoint pjp) throws Throwable {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		String id = request.getParameter("id");
		String token = request.getParameter("token");
		boolean verify = apiService.verifyToken(id, token);
		if (verify) {
			Object object = pjp.proceed(); // 执行连接点方法
			// 获取执行方法的参数

			return object;
		} else {
			// return ResultApp.error(3,"token失效");
			return null;
		}
	}
}