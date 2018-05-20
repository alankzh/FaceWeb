package com.alankzh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 表示一个类是线程安全的
 * @author alankzh
 *
 */
@Documented  
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)  
public @interface ThreadSafe {

}
