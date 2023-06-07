package org.example.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})             // Class, interface, enum, record 대상
@Retention(RetentionPolicy.RUNTIME)     // 런타임 동안 실행
public @interface Controller {
}
