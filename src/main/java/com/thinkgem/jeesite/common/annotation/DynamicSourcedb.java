package com.thinkgem.jeesite.common.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(value = { ElementType.METHOD, ElementType.TYPE })
@Inherited
@Retention(RetentionPolicy.RUNTIME)
public @interface DynamicSourcedb {
	String value() default "default";
}
