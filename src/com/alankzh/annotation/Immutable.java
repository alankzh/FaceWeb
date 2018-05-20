package com.alankzh.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;



/**
 * 表示一个类是不可变的，包括线程安全的含义
 * @author alankzh
 *
 */
@Documented  
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.CLASS)  
public @interface Immutable {

}
