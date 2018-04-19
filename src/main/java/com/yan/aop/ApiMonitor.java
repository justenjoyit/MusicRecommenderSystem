package com.yan.aop;

import com.yan.aop.service.RecordService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;


/**
 * Created by YZT on 2018/4/19.
 */
@Aspect
public class ApiMonitor {

    private long before;
    private long after;

    @Autowired
    private RecordService recordService;

    @Pointcut("execution(* com.yan.persist.service.impl.*.*(..))")
    private void pointCutMethod() {
    }

    //声明例外通知
    @AfterThrowing(pointcut = "pointCutMethod()", throwing = "e")
    public void doAfterThrowing(Exception e) {
        System.out.println(e.getMessage());
        after = System.currentTimeMillis();
        System.out.println(after - before);
    }

    //声明环绕通知
    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint pjp) throws Throwable {
        before = System.currentTimeMillis();
        Object o = pjp.proceed();
        after = System.currentTimeMillis();
        recordService.addRecord(((MethodSignature) pjp.getSignature()).getMethod().getDeclaringClass().getName(), (after - before));
        return o;
    }
}
