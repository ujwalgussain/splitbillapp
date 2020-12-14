package com.app.splitbillapp

import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Pointcut
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint
import org.springframework.http.ResponseEntity

@Aspect
class Aspect(private val logger:Logger = LoggerFactory.getLogger(com.app.splitbillapp.Aspect::class.java)) {



   /* @Around("execution(* com.app.splitbillapp.*(..)")
    fun aroundAdvice(proceedingJoinPoint: ProceedingJoinPoint) : ResponseEntity<String>
    {
        logger.info("Received Request")
        val responseEntity = proceedingJoinPoint.proceed()

    }*/


}