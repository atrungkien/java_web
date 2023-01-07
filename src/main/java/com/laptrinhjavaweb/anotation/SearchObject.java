package com.laptrinhjavaweb.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)///danh dau anotation
@Target(ElementType.TYPE)
public @interface SearchObject {
    String tableName() default "";
    String aliasValue() default "";
    boolean groupBy() default false;
}
