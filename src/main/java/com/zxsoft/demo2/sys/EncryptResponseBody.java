package com.zxsoft.demo2.sys;

import org.springframework.core.annotation.AliasFor;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

import java.lang.annotation.*;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@ResponseBody
public @interface EncryptResponseBody {

        @AliasFor("encryt")
        boolean value() default true;

        @AliasFor("value")
        boolean encryt() default true;
}
