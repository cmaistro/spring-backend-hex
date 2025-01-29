package com.example.backend.infrastructure.log;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectLogging {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private final String defaultPointcut="execution(* com.example.backend..*.*(..))";
    private final String exceptionPointcut ="execution(* com.example.logger.springlogger.*.*.*(..))";

    @Before(defaultPointcut)
    public void logBefore(JoinPoint joinPoint) {
        logger.info("Method execution started: {} - {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @After(defaultPointcut)
    public void logAfter(JoinPoint joinPoint) {
        logger.info("Method execution completed: {} - {}", joinPoint.getSignature(), joinPoint.getArgs());
    }

    @AfterThrowing(value = exceptionPointcut ,throwing = "exception")
    public void logsExceptionsFromAnyLocations(JoinPoint joinPoint,Throwable exception) throws Throwable {
        logger.error("We have error in this method {}",joinPoint.getSignature().getName());
        logger.error("The exception message: {} - {}", exception.getMessage(), exception.getStackTrace());
    }

    @AfterReturning(value = defaultPointcut,returning = "returnValue")
    public void logEachReturningValue(JoinPoint joinPoint,Object returnValue){
        logger.info("the value returned from method: {} - {}", joinPoint.getSignature().getName(), (returnValue != null ? returnValue.toString(): "null"));
    }
}

