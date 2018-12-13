package com.oppo.annotation;

/**
 * Created by JieChen on 2018/5/29.
 */

import java.lang.annotation.*;

@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Action {

    String value() default "";
}