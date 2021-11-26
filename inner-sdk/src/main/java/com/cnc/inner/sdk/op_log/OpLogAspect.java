package com.cnc.inner.sdk.op_log;

import com.cnc.provider.api.oplog.OpLogService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
public class OpLogAspect {

    @DubboReference
    OpLogService opLogService;

    @Pointcut("@annotation(com.cnc.inner.sdk.op_log.OpLog)")
    public void pointcut() {
    }

    @After("pointcut()")
    public void before(ProceedingJoinPoint point) {
        try {
            // 执行方法
            point.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    private void saveLog(ProceedingJoinPoint joinPoint) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        OpLog logAnnotation = method.getAnnotation(OpLog.class);

    }


}
