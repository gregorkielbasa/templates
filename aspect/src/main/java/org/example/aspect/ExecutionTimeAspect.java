package org.example.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Aspect
@Component
public class ExecutionTimeAspect {

    @Around("execution(* org.example.aspect.MyClass.methodeOne())")
    public void logExecutionTime1(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();
        joinPoint.proceed();
        long end = System.currentTimeMillis();
        System.out.println("Execution time=" + (end - start) + "ms");
    }

//    @Around("execution(* org.example.aspect.MyClass.methodeTwo(..))")
//    public Object logParametersAndReturnValue(ProceedingJoinPoint joinPoint) throws Throwable {
//        Object result = joinPoint.proceed();
//
//        System.out.println("Method called: " + joinPoint.getSignature().getName());
//        System.out.println("Input parameters: " + java.util.Arrays.toString(joinPoint.getArgs()));
//        System.out.println("Return value: " + result);
//
//        return result;
//    }

    @Around("execution(* org.example.aspect.MyClass.methodeTwo(..))")
    public Object logParametersAndReturnValue(ProceedingJoinPoint joinPoint) throws Throwable {

        List<String> result = (List<String>)joinPoint.proceed();

        List<String> newResult = new ArrayList<>();
        newResult.addAll(result);
        newResult.add("Hahaha");
        newResult.add("Injected");

        return newResult;
    }
}









