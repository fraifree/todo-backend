package ru.javabegin.backend.todo.aop;

import lombok.extern.java.Log;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
@Log
public class LoggingAspect {

    @Around("execution(* ru.javabegin.backend.todo.controller..*(..)))")
    public Object profileControllerMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable{
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();

        //получить информацию о том, какой класс и метод выполняются
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();

        log.info("--------Executing " + className + " " + methodName + " ----------");

        final StopWatch stopWatch = new StopWatch();
        //засекаем время
        stopWatch.start();
        //выполнить сам метод
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        log.info("--------Executing " + className + " " + methodName + " :: " + stopWatch.getTotalTimeMillis() + " ----------");
        return result;
    }
}
