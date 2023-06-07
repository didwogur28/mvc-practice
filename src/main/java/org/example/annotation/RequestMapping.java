package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.METHOD})     // Class, interface, enum, record 또는 Method 대상
@Retention(RetentionPolicy.RUNTIME)                 // 런타임 동안 실행
public @interface RequestMapping {

    String value() default  "";

    RequestMethod[] method() default {};
}
