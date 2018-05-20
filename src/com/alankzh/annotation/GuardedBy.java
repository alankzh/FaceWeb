package com.alankzh.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表明一个状态变量被哪些锁控制
 * @author alankzh
 *
 */
@Target({ElementType.FIELD,ElementType.METHOD })
@Retention(RetentionPolicy.CLASS)  
public @interface GuardedBy {
    String value();
}
