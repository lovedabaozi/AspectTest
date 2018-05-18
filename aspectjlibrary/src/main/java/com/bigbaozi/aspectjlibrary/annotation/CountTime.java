package com.bigbaozi.aspectjlibrary.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Name: CountTime
 * Author: zhangfenglin
 * Email: zhfenglin@163.com
 * Comment: // 记时 注解
 * Date: 2018-05-17 11:54
 */

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface  CountTime {
    String[] value();

}
