package com.cnc.inner.sdk.op_log;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface OpLog {
    String opName() default ""; // 操作名称

    String systemCode() default ""; // 子系统码

    String moduleCode() default ""; // 子系统模块码
}
