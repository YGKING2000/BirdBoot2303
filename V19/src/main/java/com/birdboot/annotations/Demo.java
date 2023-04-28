package com.birdboot.annotations;

import java.lang.annotation.*;

@Target({ElementType.TYPE, ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface Demo {
    // String value() default "";
    // int value() default 123;
    enum value {} enum Test {};
    // class value {}  class Test {};
    // @interface value {} @interface test {}
}
