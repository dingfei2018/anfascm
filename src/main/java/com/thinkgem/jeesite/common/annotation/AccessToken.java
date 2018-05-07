package com.thinkgem.jeesite.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/** 
 * 权限注解 
 * Created by Hamming on 2016/12/26. 
 */  
@Target(ElementType.METHOD)//这个注解是应用在方法上  
@Retention(RetentionPolicy.RUNTIME)  
public @interface AccessToken {  
/*    String userId(); 
    String token();*/  
}  
